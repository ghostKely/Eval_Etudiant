/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import etudiant.Etudiant;
import etudiant.Promotion;
import function.Connect;
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

/**
 *
 * @author ETU1886-Fanirina
 */
@WebServlet(name = "C_EtudiantLicence_R", urlPatterns = {"/C_EtudiantLicence_R"})
public class C_EtudiantLicence_R extends HttpServlet {

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
            out.println("<title>Servlet C_EtudiantLicence_R</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet C_EtudiantLicence_R at " + request.getContextPath() + "</h1>");
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
            out.println("GET C_EtudiantLicence_R -----------------------------------------------");
            
            String destinationPage = "Home_Admin.jsp";
                request.setAttribute("content", "../contentAdmin/etudiant/Etudiant_Licence.jsp");
            
            int successOrFail = Integer.valueOf(request.getParameter("licence"));
                
            String[] appreciation = { "Ajourné", "Pass", "Compensé" };
            int pass = 10;
            double moyenneGeneralNeeded = 10;
            Etudiant etudiant = new Etudiant();
            List<Etudiant> listeEtudiant = etudiant.getListeEtudiant(connection);
            List<Etudiant> licenceOrNot = new ArrayList<Etudiant>();
            if (successOrFail == 1) {
                licenceOrNot = etudiant.getEtudiantWithLicence(connection, listeEtudiant, appreciation, pass, moyenneGeneralNeeded);
                request.setAttribute("licencePassed", true);
            } else {
                licenceOrNot = etudiant.getEtudiantWithoutLicence(connection, listeEtudiant, appreciation, pass, moyenneGeneralNeeded);
                request.setAttribute("licencePassed", false);
            }
                request.setAttribute("liste_Etudiant", licenceOrNot);
                
            RequestDispatcher dispat = request.getRequestDispatcher("home/"+destinationPage);
            dispat.forward(request,response);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(C_EtudiantLicence_R.class.getName()).log(Level.SEVERE, null, ex);
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
        processRequest(request, response);
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
