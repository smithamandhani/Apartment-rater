/*
*# Copyright (c) 2016 Akanksha and Smitha
*# This code is available under the "MIT License".
*# Please see the file LICENSE in this distribution
*# for license terms 
 */
package LoginServlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//import net.ucanaccess.jdbc.UcanaccessDriver;
import javax.servlet.RequestDispatcher;

/**
 * Login Logic and then fetching the details of all areas and no of beds and navigating to the drop down jsp 
 * @author A
 */
@WebServlet(name = "loginServlet", urlPatterns = {"/loginServlet"})
public class loginServlet extends HttpServlet {

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
            out.println("<title>Servlet loginServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet loginServlet at " + request.getContextPath() + "</h1>");
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
     * Checks of the entered login details are valid if yes then goes to the next page 
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //processRequest(request, response);
        PrintWriter pw=response.getWriter();
        String user_id = request.getParameter("id").trim();
        String password = request.getParameter("password").trim();
        // Database operations using JDBC
        try {
 
            //Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
            //String url = "jdbc:odbc:Driver={Microsoft Access Driver (*.mdb, *.accdb)};DBQ=\" + \"C:\\Users\\A\\Documents\\Login.accdb";
            String driver = "net.ucanaccess.jdbc.UcanaccessDriver";
            Class.forName(driver);
            String db = "jdbc:ucanaccess://C:\\Users\\A\\Documents\\apartmentdetails.accdb";            
            Connection connection = DriverManager.getConnection(db);            
            if (connection.equals(null)) {
                //System.out.println("connection was failed");                
            } else {
 
                //System.out.println("connected successfully");                
                //System.out.println(user_id+ "  "+password);
                // Select user from database to check user login id and password
                querySmt = connection.createStatement();
                //result = querySmt.executeQuery("select * from Table1 where User = '"+ user_id + "'");
                result=querySmt.executeQuery("Select * from loginpage");
                String page=null;
                if (!result.equals(null)) {
                    
                  //  System.out.println("here");
 //System.out.println(result.getString("User") + result.getString("Password")  );
 if (!result.next() ) {
    System.out.println("no data");
} else {
                    while (result.next()) {
                                            //System.out.println("Now here");
                        user_ID_from_DB = result.getString("username").trim();
                        user_password_from_DB = result.getString("password").trim();
                       // System.out.println(user_ID_from_DB + " "+ user_password_from_DB);
                        if(user_ID_from_DB.equals(user_id) && user_password_from_DB.equals(password))
                        {
                            //System.out.println("here");
                            //System.out.println(user_id +" "+ password);
                            //System.out.println(user_ID_from_DB +" "+ user_password_from_DB);
                       set=true; 
                       noset=false;
                       break;
                        }
                        else
                        {
                            set=false;
                            noset=true;
                            continue;
                        }
                    }
 }            // Database operations completed
                    if (set==true) {
                        page="DropDown.jsp";
                        //request.getRequestDispatcher(page).forward(request, response);
                       
                        String driver1 = "net.ucanaccess.jdbc.UcanaccessDriver";
            Class.forName(driver1);
            String db1 = "jdbc:ucanaccess://C:\\Users\\A\\Documents\\apartmentdetails.accdb";            
            Connection connection1 = DriverManager.getConnection(db1);                                     
             Statement querySmt1 = connection1.createStatement();
             ResultSet result1=querySmt1.executeQuery("Select distinct CityStateOrCode from details");
             ResultSet result2=querySmt1.executeQuery("Select distinct bed from details");
             List<String> a =null;
             a=new ArrayList<String>();
             List<Integer> b =null;
             b=new ArrayList<Integer>();
             String adding=null;
             int adding1=0;
             if(result1==null)
             {
             System.out.println("no data");
             }
            while(result1.next())
            {
               adding=result1.getString("CityStateOrCode");              
               a.add(adding);
               
            }
            while(result2.next())
            {
               adding1=result2.getInt("bed");
               b.add(adding1); 
            }
                       
            request.setAttribute("locations", a);
            request.setAttribute("beds", b);
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/DropDown.jsp");
            rd.forward(request, response);
                    } 
                    if(noset==true) {
                        //request.getRequestDispatcher("/fail.jsp").forward(request, response);
                        pw.println("<script type=\"text/javascript\">");
                        pw.println("alert('Login failed...please try again with correct credentials');");
                        pw.println("location='login.jsp';");
                        pw.println("</script>");
                    }
                } else {
                    pw.println("<script type=\"text/javascript\">");
                    pw.println("alert('Login failed...check your database connectivity');");
                    pw.println("location='login.jsp';");
                    pw.println("</script>");
                }
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        } finally {
            try {
                result.close();
                querySmt.close();
                //result1.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
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
