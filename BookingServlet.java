/*
*# Copyright (c) 2016 Akanksha and Smitha
*# This code is available under the "MIT License".
*# Please see the file LICENSE in this distribution
*# for license terms 
 */
package BookingServlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.mail.*;  
import javax.mail.internet.*;  
import javax.servlet.RequestDispatcher;

/**
 *Servlet to book the Apartment
 * @author A
 */
@WebServlet(name = "BookingServlet", urlPatterns = {"/BookingServlet"})
public class BookingServlet extends HttpServlet {
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
            out.println("<title>Servlet BookingServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet BookingServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *Fetches all the Areas and Beds size to be populated into the dropdown for further search
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //processRequest(request, response);
          
        try{
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
            rd.forward(request, response);;
    }
        catch (Exception exception) {
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
     * Handles the HTTP <code>POST</code> method.
     * Books the Apartment by inserting all the values reading from the jsp page into the Database
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
        String bed = request.getParameter("bed").trim();
        String rent=request.getParameter("rent").trim();
        String contact = request.getParameter("contact").trim();
        String email = request.getParameter("email").trim();
        String address=request.getParameter("address").trim();
        String urcontact = request.getParameter("urcontact").trim();
        String shiftingdate = request.getParameter("shiftingdate").trim();
        String meetingdate=request.getParameter("meetingdate").trim();
        String bName=request.getParameter("Name").trim();
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
                if(aptName.trim()!=null && !aptName.trim().isEmpty()&&bed.trim()!=null && !bed.trim().isEmpty()&& rent.trim()!=null&& !rent.trim().isEmpty()&& contact.trim()!=null&& !contact.trim().isEmpty()&& email.trim()!=null&& !email.trim().isEmpty()&& address.trim()!=null&& !address.trim().isEmpty()&& urcontact.trim()!=null&& !urcontact.trim().isEmpty()&& shiftingdate.trim()!=null&& !shiftingdate.trim().isEmpty()&& meetingdate.trim()!=null&& !meetingdate.trim().isEmpty())
                {
                int i=querySmt.executeUpdate("insert into Booking (ApartmentName,Bed,Rent,OwnerContact,Email,Address,BookingContact,ShiftingDate,MeetingDate,BookeeName) values('" + aptName + "','" + bed +"','" + rent +"','" + contact +"','" + email +"','" + address +"','" + urcontact +"','" + shiftingdate +"','" + meetingdate +"','" + bName+"')");
                 pw.println("Booking Successfull,Confirmation sent to your email,Click the back button link to search more or logout");  
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
                //RequestDispatcher rd = getServletContext().getRequestDispatcher("/DropDown.jsp");
                //rd.forward(request, response);
                }else
                {
                pw.println("<script type=\"text/javascript\">");
                pw.println("alert('Fields cannot be empty....And enter all Valid information');");
                pw.println("location='Booking.jsp';");
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
