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
import log.Log;

/**
 *
 * @author ETU1886-Fanirina
 */
@WebServlet(name = "C_LoginAdmin_A", urlPatterns = {"/C_LoginAdmin_A"})
public class C_LoginAdmin_A extends HttpServlet {

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
            out.println("<title>Servlet C_LoginAdmin_A</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet C_LoginAdmin_A at " + request.getContextPath() + "</h1>");
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
            System.out.println("POST C_LoginAdmin_A -----------------------------------------------");
            
            String userName = request.getParameter("userName");
            String password = request.getParameter("password");
            out.println("USER : "+userName+" MDP : "+password);
            
            if ( userName.isEmpty() == true || password.isEmpty() == true ) {
                RequestDispatcher dispatcher = request.getRequestDispatcher("C_LoginAdmin_R");
                    dispatcher.forward(request, response);
            } else {
                Log log = new Log("profil", "username" , "password");
                Object[] baseUserInfo = log.checkUserAndPassword(userName, password);
                boolean checkBase = (Boolean) baseUserInfo[0];
                if (checkBase == false) {
                    RequestDispatcher dispatcher = request.getRequestDispatcher("C_LoginAdmin_R");
                        dispatcher.forward(request, response);
                } else {
                    Log user = (Log) baseUserInfo[1];
                    request.setAttribute("userInfo", user);
                    RequestDispatcher dispatcher = request.getRequestDispatcher("C_HomeAdmin_R");
                        dispatcher.forward(request, response);
                }
            }
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
