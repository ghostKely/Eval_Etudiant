/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import etudiant.Etudiant;
import etudiant.Promotion;
import function.Connect;
import function.Page;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import matiere.Matiere;

/**
 *
 * @author ETU1886-Fanirina
 */
@WebServlet(name = "C_Etudiant_L", urlPatterns = {"/C_Etudiant_L"})
public class C_Etudiant_L extends HttpServlet {

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
            out.println("<title>Servlet C_Etudiant_L</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet C_Etudiant_L at " + request.getContextPath() + "</h1>");
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
        Connect co = new Connect();
        Connection connection = null;
        try {
            connection = co.toConnect();
            PrintWriter out = response.getWriter();
            out.println("GET C_Etudiant_L -----------------------------------------------");
            
            String destinationPage = "Home_Admin.jsp";
                request.setAttribute("content", "../contentAdmin/etudiant/List_Etudiant.jsp");
            
            Etudiant etudiant = new Etudiant();
            List<Etudiant> listeEtudiant = etudiant.getListeEtudiant(connection);
                request.setAttribute("liste_Etudiant", listeEtudiant);
            
            Promotion promotion = new Promotion(); 
            List<Promotion> listePromotion = promotion.getListePromotion(connection);
                request.setAttribute("liste_Promotion", listePromotion);
                request.setAttribute("selectedPromo", 1);
                
            RequestDispatcher dispat = request.getRequestDispatcher("home/"+destinationPage);
            dispat.forward(request,response);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(C_Etudiant_L.class.getName()).log(Level.SEVERE, null, ex);
            }
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
        Connect co = new Connect();
        Connection connection = null;
        try {
            connection = co.toConnect();
            PrintWriter out = response.getWriter();
            out.println("POST C_Etudiant_L -----------------------------------------------");
            
            String destinationPage = "Home_Admin.jsp";
                request.setAttribute("content", "../contentAdmin/etudiant/List_Etudiant.jsp");
            
            List<Etudiant> listeEtudiant = (List<Etudiant>) request.getAttribute("listeEtudiantByPromo");
                request.setAttribute("liste_Etudiant", listeEtudiant);
            
            Promotion promotion = new Promotion(); 
            List<Promotion> listePromotion = promotion.getListePromotion(connection);
                request.setAttribute("liste_Promotion", listePromotion);
                
            int idPromo = (Integer) request.getAttribute("selectPromo");  
                request.setAttribute("selectedPromo", idPromo);
                
            RequestDispatcher dispat = request.getRequestDispatcher("home/"+destinationPage);
            dispat.forward(request,response);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(C_Etudiant_L.class.getName()).log(Level.SEVERE, null, ex);
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
