/*
*# Copyright (c) 2016 Akanksha and Smitha
*# This code is available under the "MIT License".
*# Please see the file LICENSE in this distribution
*# for license terms 
 */
package DetailsServlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet to display the result on the basis of search criteria and then navigate to either book or rate page 
 * @author A
 */
@WebServlet(name = "DetailsServlet", urlPatterns = {"/DetailsServlet"})
public class DetailsServlet extends HttpServlet {

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
            out.println("<title>Servlet DetailsServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet DetailsServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     * takes all teh values and navigate to booking page
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
      //  processRequest(request, response);
      PrintWriter pw=response.getWriter();
      String aptName = request.getParameter("aptName").trim();
      String bed=request.getParameter("bed");
      String rent = request.getParameter("rent").trim();
      String contact=request.getParameter("contact");      
      request.setAttribute("name",aptName);
      request.setAttribute("bed",bed);
      request.setAttribute("rent",rent);
      request.setAttribute("contact",contact);
      RequestDispatcher rd=request.getServletContext().getRequestDispatcher("/Booking.jsp");
      rd.forward(request,response);
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
        //processRequest(request, response);
         //PrintWriter pw=response.getWriter();
        //String aptName = request.getParameter("aptName").trim();
        //pw.println("Hello"+"-"+aptName);
    //System.out.println(aptName+"Hello");
    
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
