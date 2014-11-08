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
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Fenil Admin
 */
public class PayFineCheckServlet extends HttpServlet {

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

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/library", "root", "");

            int cardno = Integer.parseInt(request.getParameter("card"));
            String query = "select count(*) from book_loans where card_no = '" + cardno + "' and (date_in is null and due_date < curdate())";

            Statement st1 = con.createStatement();
            ResultSet rs1 = st1.executeQuery(query);
            int noOfBooks = 0;
            while (rs1.next()) {
                noOfBooks = rs1.getInt(1);
            }
            if (noOfBooks != 0) {
                request.setAttribute("msg", "You can not pay fine. You have not checked in " + noOfBooks + "overdue book(s).");

            } else if (noOfBooks == 0) {
                query = "select loan_id from book_loans where card_no = '" + cardno + "' and date_in > due_date;";
                Statement st2 = con.createStatement();
                ResultSet rs2 = st2.executeQuery(query);

                while (rs2.next()) {
                    query = "update fines set paid = '1' where loan_id = '" + rs2.getInt(1) + "';";
                    Statement st3 = con.createStatement();
                    st3.executeUpdate(query);
                    st3.close();
                }
                request.setAttribute("msg", "Payment successful.");
                rs2.close();
            }
            rs1.close();
            con.close();
            rd = request.getRequestDispatcher("paid.jsp");
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
