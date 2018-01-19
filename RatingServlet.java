/*
*# Copyright (c) 2016 Akanksha and Smitha
*# This code is available under the "MIT License".
*# Please see the file LICENSE in this distribution
*# for license terms 
 */
package RatingServlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet used to rate and insert value to the database(Update)
 * @author A
 */
@WebServlet(name = "RatingServlet", urlPatterns = {"/RatingServlet"})
public class RatingServlet extends HttpServlet {
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
            out.println("<title>Servlet RatingServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet RatingServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method
     * Take all the values from compare or search page and navigates to the rating jsp
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //processRequest(request, response);
         String aptName = request.getParameter("aptName").trim();
      String bed=request.getParameter("bed");
      String rent = request.getParameter("rent").trim();
      String contact=request.getParameter("contact");  
      String ID=request.getParameter("id");
      request.setAttribute("name",aptName);
      request.setAttribute("bed",bed);
      request.setAttribute("rent",rent);
      request.setAttribute("contact",contact);
      request.setAttribute("Id",ID);
      RequestDispatcher rd=request.getServletContext().getRequestDispatcher("/Rating.jsp");
      rd.forward(request,response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     * On click of rating button click update the rate field and then insert value to the table
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //processRequest(request, response);
        String aptName = request.getParameter("aptName").trim();
      String bed=request.getParameter("bed");
      String rent = request.getParameter("rent").trim();
      String contact=request.getParameter("contact");  
      String ID=request.getParameter("id");
      String rate=request.getParameter("Rate");
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
                if(rate.trim()!=null && !rate.trim().isEmpty()){
                System.out.println("connected successfully");                
                //System.out.println(user_id+ "  "+password);
                // Select user from database to check user login id and password
                PrintWriter pw=response.getWriter();
                //querySmt = connection.createStatement();
                //result = querySmt.executeQuery("select * from Table1 where User = '"+ user_id + "'");
                //String updateTableSQL = "UPDATE DBUSER SET USERNAME = ? WHERE USER_ID = ?";
                System.out.println(ID +" "+rate);
PreparedStatement preparedStatement = connection.prepareStatement("UPDATE details SET Rating = ? WHERE ID = ?");
preparedStatement.setInt(1,Integer.parseInt(rate));
preparedStatement.setInt(2, Integer.parseInt(ID));
// execute insert SQL stetement
preparedStatement .executeUpdate();
                pw.println("Rating successful...Click the below link to Search more");  
                response.setContentType("text/html");
                pw.println("<html>");
                pw.println("<body bgcolor=\"white\">");
                pw.println("<head>");

        //pw.println("<title> From servlet to applet </title>");
        pw.println("</head>");
        pw.println("<body>");	
        pw.println("<form action=\"BookingServlet\" method=\"get\">");	
	pw.println("<input type=\"submit\" value=\"Continue Search...\">");	
        pw.println("</form>");
        pw.println("</body>");
        pw.println("</html>");
                }else
                {
                    PrintWriter pw=response.getWriter();
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
