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

/**
 *
 * @author Fenil Admin
 */
public class SearchBorrowerServlet extends HttpServlet {

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
            String card, fname, lname, check, page;
            check = request.getParameter("check");
            if (check.equals("1")) {
                page = "searchborrower.jsp";
            } else {
                page = "searchborrower2.jsp";
            }

            card = request.getParameter("card");
            fname = request.getParameter("fname");
            lname = request.getParameter("lname");

            if ((card == null && fname == null && lname == null)) {
                request.setAttribute("msg", "Please enter atleast one of the three options.");
            } else {

                String query = "select card_no, fname, lname from borrower where card_no like '%" + card + "%' and fname like '%" + fname + "%' and lname like '%" + lname + "%'; ";

                Class.forName("com.mysql.jdbc.Driver");
                Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/library", "root", "");

                Statement st1 = con.createStatement();
                ResultSet rs1 = st1.executeQuery(query);
                ArrayList<Borrower> blist = new ArrayList<>();

                while (rs1.next()) {
                    Borrower b = new Borrower();
                    b.setCard(rs1.getInt(1));
                    b.setFname(rs1.getString(2));
                    b.setLname(rs1.getString(3));
                    blist.add(b);
                }
                if (blist.isEmpty()) {
                    request.setAttribute("msg", "No matching borrower found.");
                }

                request.setAttribute("list", blist);
                rs1.close();
                con.close();
            }

            rd = request.getRequestDispatcher(page);
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
