/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import log.Log;

/**
 *
 * @author ETU1886-Fanirina
 */
@WebServlet(name = "C_HomeAdmin_R", urlPatterns = {"/C_HomeAdmin_R"})
public class C_HomeAdmin_R extends HttpServlet {

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
            out.println("<title>Servlet C_HomeAdmin_R</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet C_HomeAdmin_R at " + request.getContextPath() + "</h1>");
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
        try {
            PrintWriter out = response.getWriter();
            out.println("GET C_HomeAdmin_R -----------------------------------------------");
            Log user = new Log();
            HttpSession session = request.getSession();
            String destinationPage = "Home_Admin.jsp";
                session.setAttribute("destinationPage", destinationPage);
            
                session.setAttribute("navbar", "../component/navbar/Navbar_Admin.jsp");
                request.setAttribute("content", "../contentAdmin/Welcome_Admin.jsp");
            
            boolean doResetExist = request.getAttribute("checkResetBase") != null;
                if (doResetExist == true) {
                    boolean isReseted = (boolean) request.getAttribute("checkResetBase");
                    request.setAttribute("validationReset", isReseted);
                }
                
                RequestDispatcher dispatcher = request.getRequestDispatcher("home/"+destinationPage);
                    dispatcher.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
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
        try {
            PrintWriter out = response.getWriter();
            out.println("POST C_HomeAdmin_R -----------------------------------------------");
            
            Log user = (Log) request.getAttribute("userInfo");
            HttpSession session = request.getSession();
            session.setAttribute("userLogged", user);

            String destinationPage = "Home_Admin.jsp";
                //SESSION UTILISER PAR LES SERVLETS POUR AVOIR LA DESTINATION A CHAQUE FOIS
                session.setAttribute("destinationPage", destinationPage);
                session.setAttribute("admin", true);
            
                session.setAttribute("navbar", "../component/navbar/Navbar_Admin.jsp");
                request.setAttribute("content", "../contentAdmin/Welcome_Admin.jsp");
            
                RequestDispatcher dispatcher = request.getRequestDispatcher("home/"+destinationPage);
                    dispatcher.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
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
