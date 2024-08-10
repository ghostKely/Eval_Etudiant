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
import log.ProfilUser;

/**
 *
 * @author ETU1886-Fanirina
 */
@WebServlet(name = "C_HomeUser_R", urlPatterns = {"/C_HomeUser_R"})
public class C_HomeUser_R extends HttpServlet {

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
            out.println("<title>Servlet C_HomeUser_R</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet C_HomeUser_R at " + request.getContextPath() + "</h1>");
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
            out.println("GET C_HomeUser_R -----------------------------------------------");
            HttpSession session = request.getSession();
            String destinationPage = "Home_User.jsp";
                session.setAttribute("destinationPage", destinationPage);
            
                session.setAttribute("navbar", "../component/navbar/Navbar_User.jsp");
                request.setAttribute("content", "../contentUser/Welcome_User.jsp");
            
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
            out.println("POST C_HomeUser_R -----------------------------------------------");
            
            ProfilUser user = (ProfilUser) request.getAttribute("userInfo");
            System.out.println(user.getEtudiant().getEtu());
            HttpSession session = request.getSession();
            session.setAttribute("userLogged", user);

            String destinationPage = "Home_User.jsp";
                //SESSION UTILISER PAR LES SERVLETS POUR AVOIR LA DESTINATION A CHAQUE FOIS
                session.setAttribute("destinationPage", destinationPage);
                session.setAttribute("admin", false);
            
                session.setAttribute("navbar", "../component/navbar/Navbar_User.jsp");
                request.setAttribute("content", "../contentUser/Welcome_User.jsp");
            
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
