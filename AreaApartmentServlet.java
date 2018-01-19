/*
*# Copyright (c) 2016 Akanksha and Smitha
*# This code is available under the "MIT License".
*# Please see the file LICENSE in this distribution
*# for license terms 
 */
package AreaApartmentServlet;

//import Details;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *This Servlet is used to search the records on the basis of the drop down selection and pass to the jsp
 * @author A
 */
@WebServlet(name = "AreaApartmentServlet", urlPatterns = {"/AreaApartmentServlet"})
public class AreaApartmentServlet extends HttpServlet {
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
            out.println("<title>Servlet AreaApartmentServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AreaApartmentServlet at " + request.getContextPath() + "</h1>");
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
     *It fetches the data from the Database details table and passes it to the DisplayDetails servlet
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
        //String user_id = request.getParameter("id").trim();
        String Area = request.getParameter("Location").trim();        
        String beds = request.getParameter("Beds").trim();        
        //pw.println(Area);
        
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
                //System.out.println(Area);
                String a[]=Area.split(",");
                //System.out.println(a[0]);
                int bed=Integer.valueOf(beds);
                
                PreparedStatement statement=connection.prepareStatement("Select * from details where CityStateOrCode LIKE ? And bed = ?");
                statement.setString(1, a[0]+"%");
                statement.setInt(2, bed);
                ResultSet r= statement.executeQuery();
                ArrayList<Details> details=new ArrayList<Details>();
                
                if( r.equals(null))
                {
                    pw.println("no data");
                }
                else{
                    while(r.next())
                    {
                       Details d=new Details();
                       d.ID= r.getInt("ID");
                       d.location=r.getString("location");
                       d.apartmentName=r.getString("ApartmentName");
                       d.cityStateOrCode=r.getString("CityStateOrCode");
                       d.bath=r.getInt("bath");
                       d.bed=r.getInt("bed");
                       d.area=r.getString("area(sqfeet)");
                       d.patio=r.getString("patio");
                       if(d.patio.equalsIgnoreCase("true"))
                       {
                           d.patio="Yes";
                       }else
                       {
                           d.patio="No";
                       }
                       d.pool=r.getString("pool");
                       if(d.pool.equalsIgnoreCase("true"))
                       {
                           d.pool="Yes";
                       }else
                       {
                           d.pool="No";
                       }
                       d.rent=r.getString("rent");
                       d.gym=r.getString("gym");
                        if(d.gym.equalsIgnoreCase("true"))
                       {
                           d.gym="Yes";
                       }else
                       {
                           d.gym="No";
                       }
                       d.washerDryer=r.getString("washerDryer");
                        if(d.washerDryer.equalsIgnoreCase("true"))
                       {
                           d.washerDryer="Yes";
                       }else
                       {
                           d.washerDryer="No";
                       }
                       d.dishWasher=r.getString("dishwasher");
                        if(d.dishWasher.equalsIgnoreCase("true"))
                       {
                           d.dishWasher="Yes";
                       }else
                       {
                           d.dishWasher="No";
                       }
                       d.review=r.getString("review");
                       d.coveredParking=r.getString("coveredParking");
                        if(d.coveredParking.equalsIgnoreCase("true"))
                       {
                           d.coveredParking="Yes";
                       }else
                       {
                           d.coveredParking="No";
                       }
                       d.garageParking=r.getString("GarageParking");
                        if(d.garageParking.equalsIgnoreCase("true"))
                       {
                           d.garageParking="Yes";
                       }else
                       {
                           d.garageParking="No";
                       }
                       d.catRent=r.getString("catRent");
                        if(d.catRent.equalsIgnoreCase("true"))
                       {
                           d.catRent="Yes";
                       }else
                       {
                           d.catRent="No";
                       }
                       d.dogRent=r.getString("dogRent");
                        if(d.dogRent.equalsIgnoreCase("true"))
                       {
                           d.dogRent="Yes";
                       }else
                       {
                           d.dogRent="No";
                       }
                       d.additionalStorage=r.getString("additionalStorage");
                        if(d.additionalStorage.equalsIgnoreCase("true"))
                       {
                           d.additionalStorage="Yes";
                       }else
                       {
                           d.additionalStorage="No";
                       }
                       d.barbeque=r.getString("barbeque");
                        if(d.barbeque.equalsIgnoreCase("true"))
                       {
                           d.barbeque="Yes";
                       }else
                       {
                           d.barbeque="No";
                       }
                       d.park=r.getString("park");
                        if(d.park.equalsIgnoreCase("true"))
                       {
                           d.park="Yes";
                       }else
                       {
                           d.park="No";
                       }
                       d.firePlace=r.getString("firePlace");
                        if(d.firePlace.equalsIgnoreCase("true"))
                       {
                           d.firePlace="Yes";
                       }else
                       {
                           d.firePlace="No";
                       }
                       d.leaseLength=r.getString("leaseLength");
                       d.microwave=r.getString("microwave");
                        if(d.microwave.equalsIgnoreCase("true"))
                       {
                           d.microwave="Yes";
                       }else
                       {
                           d.microwave="No";
                       }
                       d.onsiteMaintenance=r.getString("onsiteMaintanence");
                        if(d.onsiteMaintenance.equalsIgnoreCase("true"))
                       {
                           d.onsiteMaintenance="Yes";
                       }else
                       {
                           d.onsiteMaintenance="No";
                       }
                       d.onlineMaintenance=r.getString("onlineMaintanenceRequest");
                        if(d.onlineMaintenance.equalsIgnoreCase("true"))
                       {
                           d.onlineMaintenance="Yes";
                       }else
                       {
                           d.onlineMaintenance="No";
                       }
                       d.highSpeedInternet=r.getString("highSpeedInternetAccess");
                        if(d.highSpeedInternet.equalsIgnoreCase("true"))
                       {
                           d.highSpeedInternet="Yes";
                       }else
                       {
                           d.highSpeedInternet="No";
                       }
                       d.onlinePayment=r.getString("onlinePaymentWithFreeECheckOpt");
                        if(d.onlinePayment.equalsIgnoreCase("true"))
                       {
                           d.onlinePayment="Yes";
                       }else
                       {
                           d.onlinePayment="No";
                       }
                       d.clubHouse=r.getString("clubHouse");
                        if(d.clubHouse.equalsIgnoreCase("true"))
                       {
                           d.clubHouse="Yes";
                       }else
                       {
                           d.clubHouse="No";
                       }
                       d.lounge=r.getString("lounge");
                        if(d.lounge.equalsIgnoreCase("true"))
                       {
                           d.lounge="Yes";
                       }else
                       {
                           d.lounge="No";
                       }
                       d.airConditioning=r.getString("airConditioning");
                        if(d.airConditioning.equalsIgnoreCase("true"))
                       {
                           d.airConditioning="Yes";
                       }else
                       {
                           d.airConditioning="No";
                       }
                       d.heaters=r.getString("heaters");
                        if(d.heaters.equalsIgnoreCase("true"))
                       {
                           d.heaters="Yes";
                       }else
                       {
                           d.heaters="No";
                       }
                       d.windowCovering=r.getString("windowCovering");
                        if(d.windowCovering.equalsIgnoreCase("true"))
                       {
                           d.windowCovering="Yes";
                       }else
                       {
                           d.windowCovering="No";
                       }
                       d.flooring=r.getString("flooring");
                       d.contact=r.getString("contact");
                       d.vaccancies=r.getInt("numberOfVacancies");
                       details.add(d);
                    }                    
                    request.setAttribute("DetailsList",details);
if(request.getParameter("Search")!=null)                    
{
    RequestDispatcher rd=request.getServletContext().getRequestDispatcher("/DisplayDetails.jsp");
                    rd.forward(request,response);}
else
{
    PreparedStatement statement1=connection.prepareStatement("Select ID,ApartmentName from details where CityStateOrCode LIKE ? And bed = ?");
                statement1.setString(1, a[0]+"%");
                statement1.setInt(2, bed);
                ResultSet r1= statement1.executeQuery();
                List<String> a1 =null;
             a1=new ArrayList<String>();
             String adding=null;
             while(r1.next())
             {
                 adding=r1.getString("ApartmentName")+","+r1.getInt("ID");              
               a1.add(adding);
             }
            request.setAttribute("AptName",a1);
    RequestDispatcher rd=request.getServletContext().getRequestDispatcher("/Compare.jsp");
                    rd.forward(request,response);
}
                }                              
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        } finally {
            //try {
                //result.close();
                //querySmt.close();
                //result1.close();
            //} catch (SQLException e) {
                // TODO Auto-generated catch block
              //  e.printStackTrace();
            //}
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
