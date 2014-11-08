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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
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
public class CheckOutServlet extends HttpServlet {

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
        int branchid = (int) session.getAttribute("branchid");
        String msg = "You have reached maximum limit of 3 book loans.";
        try {
            boolean flag = true;

            Class.forName("com.mysql.jdbc.Driver");
            Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/library", "root", "");
            String bookid = request.getParameter("bookid").trim();
            String cardno = request.getParameter("card").trim();

            String checkQuery = "select count(*) from book_loans as b, fines as f where b.loan_id = f.loan_id and f.paid = '0' and b.card_no = '" + cardno + "';";
            Statement st5 = con.createStatement();
            ResultSet rs5 = st5.executeQuery(checkQuery);
            if (rs5.next()) {
                if (rs5.getInt(1) > 0) {
                    flag = false;
                }
            }

            if (bookid.equals("") || cardno.equals("") || bookid.equals(null) || cardno.equals(null) || bookid.length() != 10) {
                msg = "Enter valid card no and/or book id.";

            } else if (flag) {

                String query = "select count(*) as total from book_loans where date_in is null and card_no = '" + cardno + "' group by card_no;";

                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery(query);

                if (!(rs.next())) {
                    msg = "Book not available at this branch. Try at another branch.";

                    DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
                    Date date_out = new Date();

                    Calendar c = new GregorianCalendar();
                    Date due_date = c.getTime();
                    System.out.println(dateFormat.format((due_date)));
                    c.add(Calendar.DATE, 14);
                    due_date = c.getTime();

                    query = "select bc.no_of_copies from (book_copies as bc) left outer join  (select book_id, branch_id, count(*) as total from book_loans where date_in is null group by book_id, branch_id) as t\n"
                            + "on (bc.book_id = t.book_id and bc.branch_id = t.branch_id)\n"
                            + "where (bc.no_of_copies > t.total or t.total is null) and bc.branch_id = '" + branchid + "' and bc.book_id='" + bookid + "';";

                    Statement st1 = con.createStatement();
                    ResultSet rs1 = st1.executeQuery(query);
                    if (rs1.next()) {
                        msg = "Error in creating book loan. Check card number.";
                        query = "insert into book_loans(book_id, branch_id, card_no, date_out, due_date) values ('" + bookid + "','" + branchid + "','" + cardno + "','" + dateFormat.format(date_out) + "','" + dateFormat.format(due_date) + "');";
                        Statement st2 = con.createStatement();
                        int i = st2.executeUpdate(query);
                        if (i == 1) {
                            msg = "New book loan created.";
                        }
                    }
                    rs1.close();

                }
                rs.close();
            }
            if (!flag) {
                msg = "You have unpaid fine. You can not check out more books.";
            }
            rs5.close();

            con.close();

        } catch (Exception e) {
            System.out.println("Error: " + e.toString());
        } finally {

            request.setAttribute("msg", msg);
            rd = request.getRequestDispatcher("checkout.jsp");
            rd.forward(request, response);
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
