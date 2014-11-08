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
public class GetPartialFinesServlet extends HttpServlet {

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
            int cardno = (Integer.parseInt(request.getParameter("cardno")));

            Class.forName("com.mysql.jdbc.Driver");
            Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/library", "root", "");

            String query = "select bl.loan_id , b.title, bl.branch_id , bl.date_out, bl.date_in, bl.due_date, f.fine_amt from book as b, book_loans as bl, fines as f where bl.card_no like '" + cardno + "' and b.book_id = bl.book_id and bl.loan_id = f.loan_id and f.paid = '0';";
            Statement st1 = con.createStatement();
            ArrayList<FineDetail> fdlist1 = new ArrayList<>();

            try (ResultSet rs1 = st1.executeQuery(query)) {

                while (rs1.next()) {
                    FineDetail d = new FineDetail();
                    d.setLoanid(rs1.getInt(1));
                    d.setTile(rs1.getString(2));
                    d.setBranch_id(rs1.getInt(3));
                    d.setDate_out(rs1.getString(4));
                    d.setDate_in(rs1.getString(5));
                    d.setDate_due(rs1.getString(6));
                    d.setFine(rs1.getFloat(7));

                    fdlist1.add(d);
                }
                request.setAttribute("dueFineList", fdlist1);
                rs1.close();
            }

            query = "select b.title, bl.branch_id , bl.date_out, bl.date_in, bl.due_date, f.fine_amt from book as b, book_loans as bl, fines as f where bl.card_no ='" + cardno + "' and b.book_id = bl.book_id and bl.loan_id = f.loan_id and f.paid = '1';";
            Statement st2 = con.createStatement();
            ResultSet rs2 = st2.executeQuery(query);
            ArrayList<FineDetail> fdlist = new ArrayList<>();
            while (rs2.next()) {
                FineDetail fd = new FineDetail();
                fd.setTile(rs2.getString(1));
                fd.setBranch_id(rs2.getInt(2));
                fd.setDate_out(rs2.getString(3));
                fd.setDate_in(rs2.getString(4));
                fd.setDate_due(rs2.getString(5));
                fd.setFine(rs2.getFloat(6));

                fdlist.add(fd);
            }
            rs2.close();
            con.close();
            request.setAttribute("finelist", fdlist);
            rd = request.getRequestDispatcher("paydetail1.jsp");
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
