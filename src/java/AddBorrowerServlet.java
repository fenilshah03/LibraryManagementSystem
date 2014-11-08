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
public class AddBorrowerServlet extends HttpServlet {

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
            String page = "borrower.jsp";
            String fname, lname, street, city, phone, state;
            String add;
            fname = request.getParameter("fname");
            lname = request.getParameter("lname");
            street = request.getParameter("st");
            city = request.getParameter("ct");
            state = request.getParameter("state");
            phone = request.getParameter("ph");
            add = street + ", " + city + ", " + state;
            if (fname == null || lname == null || street == null || city == null || state == null || fname.equals("") || lname.equals("") || street.equals("") || city.equals("") || state.equals("")) {
                request.setAttribute("error", "Enter all mandatory details");
                rd = request.getRequestDispatcher("borrower.jsp");
                rd.forward(request, response);
            } else {
                Class.forName("com.mysql.jdbc.Driver");
                Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/library", "root", "");
                String query;
                query = "select * from borrower;";
                Statement st1 = con.createStatement();
                ResultSet rs1 = st1.executeQuery(query);
                String flag = "not found";
                while (rs1.next()) {
                    if (fname.equalsIgnoreCase(rs1.getString(2)) && lname.equalsIgnoreCase(rs1.getString(3)) && add.equalsIgnoreCase(rs1.getString(4))) {
                        flag = "found";
                        page = "borrower.jsp";
                        request.setAttribute("error", "Person is already registered. Can not register again..");
                        break;

                    }

                }

                if (flag.equals("not found")) {
                    query = "insert into borrower(fname, lname, address, phone) values('" + fname + "','" + lname + "','" + street + ", " + city + ", " + state + "','" + phone + "');";

                    Statement st;
                    st = con.createStatement();
                    int i = st.executeUpdate(query);

                    request.setAttribute("msg", "Added Successfully.");

                    query = "select card_no from borrower where fname='" + fname + "' and lname ='" + lname + "' and address='" + street + ", " + city + ", " + state + "';";
                    Statement st2 = con.createStatement();
                    ResultSet rs = st2.executeQuery(query);
                    int cardno = 0;
                    while (rs.next()) {
                        cardno = rs.getInt(1);
                    }

                    request.setAttribute("msg2", cardno);
                    page = "returncardno.jsp";

                }
                rs1.close();
                con.close();

                rd = request.getRequestDispatcher(page);
                rd.forward(request, response);

            }
        } catch (Exception e) {
            System.out.println("Error" + e.toString());
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
