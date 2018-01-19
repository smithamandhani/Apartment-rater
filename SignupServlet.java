/*
*# Copyright (c) 2016 Akanksha and Smitha
*# This code is available under the "MIT License".
*# Please see the file LICENSE in this distribution
*# for license terms 
 */
package SignUpServlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javafx.print.Printer;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet for new sign up
 * @author A
 */
@WebServlet(name = "SignupServlet", urlPatterns = {"/SignupServlet"})
public class SignupServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    String user_ID_from_DB = "";
    String user_password_from_DB = "";
    Connection connection = null;
    Statement querySmt = null;
    ResultSet result = null;
    boolean set=false,noset=false;
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
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet SignupServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SignupServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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
     * if the signup details are valid insert those to the database and then navigates to the login page
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//        processRequest(request, response);
String user_id = request.getParameter("id").trim();
        String password = request.getParameter("password").trim();
        String confPassword=request.getParameter("Confirmpassword").trim();
        // Database operations using JDBC
        try {
 
            //Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
            //String url = "jdbc:odbc:Driver={Microsoft Access Driver (*.mdb, *.accdb)};DBQ=\" + \"C:\\Users\\A\\Documents\\Login.accdb";
            String driver = "net.ucanaccess.jdbc.UcanaccessDriver";
            Class.forName(driver);
            String db = "jdbc:ucanaccess://C:\\Users\\A\\Documents\\apartmentdetails.accdb";            
            Connection connection = DriverManager.getConnection(db);
            //System.out.println("Hello");
            if (connection.equals(null)) {
                System.out.println("connection was failed");                
            } else {
                System.out.println("connected successfully");                
                //System.out.println(user_id+ "  "+password);
                // Select user from database to check user login id and password
                PrintWriter pw=response.getWriter();
                querySmt = connection.createStatement();
                //result = querySmt.executeQuery("select * from Table1 where User = '"+ user_id + "'");
                if(password.equals(confPassword) && password.trim()!=null && !password.trim().isEmpty() && confPassword.trim()!=null && !confPassword.trim().isEmpty() && !user_id.trim().isEmpty() && user_id.trim()!=null)
                {
                int i=querySmt.executeUpdate("insert into loginpage (username,password) values('" + user_id + "','" + password +"')");
                pw.println("Sign up Successful...Click the below link to login");  
                response.setContentType("text/html");
                pw.println("<html>");
                pw.println("<body bgcolor=\"white\">");
                pw.println("<head>");

        //pw.println("<title> From servlet to applet </title>");
        pw.println("</head>");
        pw.println("<body>");	
	pw.println("<A HREF=\"login.jsp\">login</a>");	
        pw.println("</body>");
        pw.println("</html>");
                }else
                {
                pw.println("<script type=\"text/javascript\">");
                pw.println("alert('Fields cannot be empty....Also Please enter the same password in password and confirm password field');");
                pw.println("location='Signup.jsp';");
                pw.println("</script>");
                }
                String page=null;                
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        } finally {
            /*try {
                result.close();
                querySmt.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }*/
    }
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
