/*
*# Copyright (c) 2016 Akanksha and Smitha
*# This code is available under the "MIT License".
*# Please see the file LICENSE in this distribution
*# for license terms 
 */

package CompareServlet;

import AreaApartmentServlet.Details;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *Servlet to display result of the comparision
 * @author A
 */
@WebServlet(name = "CompareServlet", urlPatterns = {"/CompareServlet"})
public class CompareServlet extends HttpServlet {

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
            out.println("<title>Servlet CompareServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CompareServlet at " + request.getContextPath() + "</h1>");
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
     * Fetches the Comparision results from the database and navigates to the compare result jsp page
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
        String a[]=request.getParameterValues("Multi");
        String a1=a[0];
        String a2=a[1];
        String b[]=a1.split(",");
        String aptName1=b[0];
        String Id1=b[1];
        String b1[]=a2.split(",");
        String aptName2=b1[0];
        String Id2=b1[1];
        System.out.println(aptName1+Id1+aptName2+Id2);
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
                PreparedStatement statement=connection.prepareStatement("Select * from details where Apartmentname= ? And ID = ?");
                statement.setString(1, aptName1);
                statement.setInt(2, Integer.parseInt(Id1));
                ResultSet r= statement.executeQuery();
                ArrayList<Details> details=new ArrayList<Details>();
                PreparedStatement statement1=connection.prepareStatement("Select * from details where Apartmentname= ? And ID = ?");
                statement1.setString(1, aptName2);
                statement1.setInt(2, Integer.parseInt(Id2));
                ResultSet r1= statement1.executeQuery();
                ArrayList<Details> details1=new ArrayList<Details>();
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
                    
                   for(int i=1;1<details.size();i++) 
                   {
                       System.out.println(details.get(i));
                   }
                }
if( r1.equals(null))
                {
                    pw.println("no data");
                }
                else{
                    
                     while(r1.next())
                    {
                       Details d1=new Details();
                       d1.ID= r1.getInt("ID");
                       d1.location=r1.getString("location");
                       d1.apartmentName=r1.getString("ApartmentName");
                       d1.cityStateOrCode=r1.getString("CityStateOrCode");
                       d1.bath=r1.getInt("bath");
                       d1.bed=r1.getInt("bed");
                       d1.area=r1.getString("area(sqfeet)");
                       d1.patio=r1.getString("patio");
                       if(d1.patio.equalsIgnoreCase("true"))
                       {
                           d1.patio="Yes";
                       }else
                       {
                           d1.patio="No";
                       }
                       d1.pool=r1.getString("pool");
                       if(d1.pool.equalsIgnoreCase("true"))
                       {
                           d1.pool="Yes";
                       }else
                       {
                           d1.pool="No";
                       }
                       d1.rent=r1.getString("rent");
                       d1.gym=r1.getString("gym");
                        if(d1.gym.equalsIgnoreCase("true"))
                       {
                           d1.gym="Yes";
                       }else
                       {
                           d1.gym="No";
                       }
                       d1.washerDryer=r1.getString("washerDryer");
                        if(d1.washerDryer.equalsIgnoreCase("true"))
                       {
                           d1.washerDryer="Yes";
                       }else
                       {
                           d1.washerDryer="No";
                       }
                       d1.dishWasher=r1.getString("dishwasher");
                        if(d1.dishWasher.equalsIgnoreCase("true"))
                       {
                           d1.dishWasher="Yes";
                       }else
                       {
                           d1.dishWasher="No";
                       }
                       d1.review=r1.getString("review");
                       d1.coveredParking=r1.getString("coveredParking");
                        if(d1.coveredParking.equalsIgnoreCase("true"))
                       {
                           d1.coveredParking="Yes";
                       }else
                       {
                           d1.coveredParking="No";
                       }
                       d1.garageParking=r1.getString("GarageParking");
                        if(d1.garageParking.equalsIgnoreCase("true"))
                       {
                           d1.garageParking="Yes";
                       }else
                       {
                           d1.garageParking="No";
                       }
                       d1.catRent=r1.getString("catRent");
                        if(d1.catRent.equalsIgnoreCase("true"))
                       {
                           d1.catRent="Yes";
                       }else
                       {
                           d1.catRent="No";
                       }
                       d1.dogRent=r1.getString("dogRent");
                        if(d1.dogRent.equalsIgnoreCase("true"))
                       {
                           d1.dogRent="Yes";
                       }else
                       {
                           d1.dogRent="No";
                       }
                       d1.additionalStorage=r1.getString("additionalStorage");
                        if(d1.additionalStorage.equalsIgnoreCase("true"))
                       {
                           d1.additionalStorage="Yes";
                       }else
                       {
                           d1.additionalStorage="No";
                       }
                       d1.barbeque=r1.getString("barbeque");
                        if(d1.barbeque.equalsIgnoreCase("true"))
                       {
                           d1.barbeque="Yes";
                       }else
                       {
                           d1.barbeque="No";
                       }
                       d1.park=r1.getString("park");
                        if(d1.park.equalsIgnoreCase("true"))
                       {
                           d1.park="Yes";
                       }else
                       {
                           d1.park="No";
                       }
                       d1.firePlace=r1.getString("firePlace");
                        if(d1.firePlace.equalsIgnoreCase("true"))
                       {
                           d1.firePlace="Yes";
                       }else
                       {
                           d1.firePlace="No";
                       }
                       d1.leaseLength=r1.getString("leaseLength");
                       d1.microwave=r1.getString("microwave");
                        if(d1.microwave.equalsIgnoreCase("true"))
                       {
                           d1.microwave="Yes";
                       }else
                       {
                           d1.microwave="No";
                       }
                       d1.onsiteMaintenance=r1.getString("onsiteMaintanence");
                        if(d1.onsiteMaintenance.equalsIgnoreCase("true"))
                       {
                           d1.onsiteMaintenance="Yes";
                       }else
                       {
                           d1.onsiteMaintenance="No";
                       }
                       d1.onlineMaintenance=r1.getString("onlineMaintanenceRequest");
                        if(d1.onlineMaintenance.equalsIgnoreCase("true"))
                       {
                           d1.onlineMaintenance="Yes";
                       }else
                       {
                           d1.onlineMaintenance="No";
                       }
                       d1.highSpeedInternet=r1.getString("highSpeedInternetAccess");
                        if(d1.highSpeedInternet.equalsIgnoreCase("true"))
                       {
                           d1.highSpeedInternet="Yes";
                       }else
                       {
                           d1.highSpeedInternet="No";
                       }
                       d1.onlinePayment=r1.getString("onlinePaymentWithFreeECheckOpt");
                        if(d1.onlinePayment.equalsIgnoreCase("true"))
                       {
                           d1.onlinePayment="Yes";
                       }else
                       {
                           d1.onlinePayment="No";
                       }
                       d1.clubHouse=r1.getString("clubHouse");
                        if(d1.clubHouse.equalsIgnoreCase("true"))
                       {
                           d1.clubHouse="Yes";
                       }else
                       {
                           d1.clubHouse="No";
                       }
                       d1.lounge=r1.getString("lounge");
                        if(d1.lounge.equalsIgnoreCase("true"))
                       {
                           d1.lounge="Yes";
                       }else
                       {
                           d1.lounge="No";
                       }
                       d1.airConditioning=r1.getString("airConditioning");
                        if(d1.airConditioning.equalsIgnoreCase("true"))
                       {
                           d1.airConditioning="Yes";
                       }else
                       {
                           d1.airConditioning="No";
                       }
                       d1.heaters=r1.getString("heaters");
                        if(d1.heaters.equalsIgnoreCase("true"))
                       {
                           d1.heaters="Yes";
                       }else
                       {
                           d1.heaters="No";
                       }
                       d1.windowCovering=r1.getString("windowCovering");
                        if(d1.windowCovering.equalsIgnoreCase("true"))
                       {
                           d1.windowCovering="Yes";
                       }else
                       {
                           d1.windowCovering="No";
                       }
                       d1.flooring=r1.getString("flooring");
                       d1.contact=r1.getString("contact");
                       d1.vaccancies=r1.getInt("numberOfVacancies");
                       details1.add(d1);
                    }
                                       
                    request.setAttribute("DetailsList1",details);
                    request.setAttribute("DetailsList2",details1);
                    RequestDispatcher rd=request.getServletContext().getRequestDispatcher("/CompareResult.jsp");
                    rd.forward(request,response);}

            
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
