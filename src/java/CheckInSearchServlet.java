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
public class CheckInSearchServlet extends HttpServlet {

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
            String fname = request.getParameter("fname").trim();
            String lname = request.getParameter("lname").trim();
            String cardno = request.getParameter("card").trim();

            int branchid = (int) (session.getAttribute("branchid"));
            String query = "select bl.loan_id, bl.book_id, b.card_no, b.fname, b.lname, bl.date_out, bl.due_date from book_loans as bl, borrower as b where bl.card_no = b.card_no and bl. date_in is null and bl.branch_id='" + branchid + "' "
                    + "and bl.book_id like '%" + bookid + "%' and b.card_no like '%" + cardno + "%' and ( b.fname like '%" + fname + "%' and b.lname like '%" + lname + "%' ) ;";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);

            ArrayList<CheckInBookDetail> lst = new ArrayList<>();

            while (rs.next()) {
                CheckInBookDetail bk = new CheckInBookDetail();
                bk.setLoanid(rs.getInt(1));
                bk.setBookid(rs.getString(2));
                bk.setCardno(rs.getInt(3));
                bk.setFname(rs.getString(4));
                bk.setLname(rs.getString(5));
                bk.setDateout(rs.getString(6));
                bk.setDatedue(rs.getString(7));

                lst.add(bk);
            }
            if (lst.isEmpty()) {
                request.setAttribute("msg", "No such book loan found.");
            }

            rs.close();
            con.close();

            request.setAttribute("list", lst);
            rd = request.getRequestDispatcher("checkin.jsp");
            rd.forward(request, response);

        } catch (Exception e) {
            System.out.println("Error: " + e.toString());
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
