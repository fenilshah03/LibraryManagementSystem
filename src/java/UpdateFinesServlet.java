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
import java.util.Date;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Fenil Admin
 */
public class UpdateFinesServlet extends HttpServlet {

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
            int diff, i;
            float fine;
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/library", "root", "");

            Statement st1 = con.createStatement();
            Statement st2 = con.createStatement();
            String query = "select loan.loan_id, fines.paid, loan.date_in, loan.due_date from fines join (select * from book_loans where date_in > due_date or (date_in is null and curdate() > due_date)) as loan on fines.loan_id = loan.loan_id ;";

            ArrayList<LoanDetail> lst = new ArrayList<>();

            ResultSet rs1 = st1.executeQuery(query);
            while (rs1.next()) {
                LoanDetail ld = new LoanDetail();
                ld.setLoanid(rs1.getInt(1));
                ld.setPaid(rs1.getInt(2));
                ld.setIn(rs1.getDate(3));
                ld.setDue(rs1.getDate(4));

                lst.add(ld);
            }

            for (LoanDetail lst1 : lst) {
                if (lst1.getPaid() == 0) {

                    if (lst1.getIn() == null) {
                        diff = (int) (-(lst1.getDue().getTime() - new Date().getTime()) / (1000 * 60 * 60 * 24));

                    } else {

                        diff = (int) (-(lst1.getDue().getTime() - lst1.getIn().getTime()) / (1000 * 60 * 60 * 24));

                    }
                    fine = (float) (diff * 0.25);
                    query = "update fines set fine_amt = '" + fine + "' where loan_id = '" + lst1.getLoanid() + "' ;";
                    i = st2.executeUpdate(query);
                    System.out.println("" + i);
                }

            }
            rs1.close();

            query = "select loan_id, date_in, due_date from book_loans where (date_in > due_date or (date_in is null and curdate() > due_date)) and loan_id not in ( select loan_id from fines );";
            Statement st3 = con.createStatement();
            Statement st4 = con.createStatement();

            ResultSet rs2 = st3.executeQuery(query);
            ArrayList<LoanDetail> list = new ArrayList<LoanDetail>();
            while (rs2.next()) {

                LoanDetail l = new LoanDetail();
                l.setLoanid(rs2.getInt(1));
                l.setIn(rs2.getDate(2));
                l.setDue(rs2.getDate(3));

                list.add(l);
            }

            for (LoanDetail list1 : list) {
                if (list1.getIn() == null) {
                    diff = (int) (-(list1.getDue().getTime() - new Date().getTime()) / (1000 * 60 * 60 * 24));

                } else {
                    diff = (int) (-(list1.getDue().getTime() - list1.getIn().getTime()) / (1000 * 60 * 60 * 24));
                }
                fine = (float) (diff * 0.25);
                query = "insert into fines values('" + list1.getLoanid() + "','" + fine + "','" + 0 + "');";
                i = st4.executeUpdate(query);
                System.out.println("" + i);
            }

            rs2.close();
            con.close();
            request.setAttribute("msg", "Fines updated");
            rd = request.getRequestDispatcher("fine.jsp");
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
