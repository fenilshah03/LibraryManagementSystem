/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Fenil Admin
 */
public class SearchServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher rd;
        HttpSession session = request.getSession();

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/library", "root", "");
            String bookid = request.getParameter("bookid").trim();
            String aname = request.getParameter("aname").trim();
            String title = request.getParameter("title").trim();

            String query = "SELECT t.book_id, t.title, t.author_name, t.branch_id, t.no_of_copies AS Total_copies, (t.no_of_copies - temp.gone) AS Available FROM (SELECT b.title, b.book_id, GROUP_CONCAT(ba.author_name) AS author_name, bc.branch_id, bc.no_of_copies     FROM         book AS b, book_authors AS ba, book_copies AS bc     WHERE         b.book_id = ba.book_id             AND bc.book_id = b.book_id     GROUP BY b.book_id , bc.branch_id) AS t         LEFT OUTER JOIN     (SELECT          book_id, branch_id, COUNT(*) AS gone     FROM         book_loans     WHERE         book_loans.date_in IS NULL     GROUP BY book_loans.book_id , book_loans.branch_id) AS temp ON (temp.book_id = t.book_id AND temp.branch_id = t.branch_id) WHERE t.book_id LIKE '%"+bookid+"%' AND t.title LIKE '%"+title+"%' AND t.author_name LIKE '%"+aname+"%';";

            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);

            ArrayList<BookSearch> bklst = new ArrayList<>();

            while (rs.next()) {
                BookSearch bs = new BookSearch();

                bs.setBookid(rs.getString(1));
                bs.setTile(rs.getString(2));
                bs.setAname(rs.getString(3));
                bs.setBranchid(rs.getInt(4));
                bs.setTotal(rs.getInt(5));

                String t = rs.getString(6);
                if (t == null) {
                    int i = bs.getTotal();
                    bs.setAvailable(i);

                } else {
                    int i = Integer.parseInt(t);
                    bs.setAvailable(i);
                }
                bklst.add(bs);

            }
            rs.close();
            if (bklst.isEmpty()) {
                request.setAttribute("msg", "No books found.");
            }
            con.close();
            request.setAttribute("BookList", bklst);
            rd = request.getRequestDispatcher("search.jsp");
            rd.forward(request, response);

        } catch (Exception e) {

            System.out.println("Error:" + e.toString());
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
