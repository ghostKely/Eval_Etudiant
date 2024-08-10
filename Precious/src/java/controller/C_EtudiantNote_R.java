/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import etudiant.Etudiant;
import function.Connect;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
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
import note.Note;
import semestre.Semestre;

/**
 *
 * @author ETU1886-Fanirina
 */
@WebServlet(name = "C_EtudiantNote_R", urlPatterns = {"/C_EtudiantNote_R"})
public class C_EtudiantNote_R extends HttpServlet {

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
            out.println("<title>Servlet C_EtudiantNote_R</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet C_EtudiantNote_R at " + request.getContextPath() + "</h1>");
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
            System.out.println("GET C_EtudiantNote_R -----------------------------------------------");
            
            String destinationPage = "Home_Admin.jsp";
                request.setAttribute("content", "../contentAdmin/etudiant/Note_Etudiant.jsp");
                
            double moyenneMatiere = 10;
            double moyenneGeneralNeeded = 10;
            String[] appreciation = { "Ajourné", "Pass", "Compensé" };
            String etu = request.getParameter("etudiant");
            Etudiant etudiant = new Etudiant();
                etudiant = etudiant.getEtudiantByEtuForNote(connection, etu);
                etudiant = etudiant.setMoyenneEtudiant(connection, etudiant, 1, appreciation);
                etudiant = etudiant.setAppreciationMatiere(etudiant, moyenneMatiere, moyenneGeneralNeeded,  appreciation);
                request.setAttribute("etudiant", etudiant);
            
            //FONCTIONNALITE ADMIN
            HttpSession session = request.getSession(false);
            boolean isAdmin = (Boolean) session.getAttribute("admin");
            if (isAdmin == true) {
                request.setAttribute("semestreSelected", 1);
            }
                
            Semestre semestre = new Semestre();
            List<Semestre> listeSemestre = semestre.getSemestreEtudiantFromNote(connection, etudiant.getEtu());
                request.setAttribute("liste_Semestre", listeSemestre);
                
            RequestDispatcher dispatcher = request.getRequestDispatcher("home/"+destinationPage);
                dispatcher.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(C_EtudiantNote_R.class.getName()).log(Level.SEVERE, null, ex);
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
            System.out.println("POST C_EtudiantNote_R -----------------------------------------------");
            
            String destinationPage = "Home_Admin.jsp";
                request.setAttribute("content", "../contentAdmin/etudiant/Note_Etudiant.jsp");
            
            double moyenneMatiere = 10;
            double moyenneGeneralNeeded = 10;
            String[] appreciation = { "Ajourné", "Pass", "Compensé" };
            String etu = request.getParameter("etudiant");
            int idSemestre = Integer.parseInt(request.getParameter("semestre"));
            Etudiant etudiant = new Etudiant();
                etudiant = etudiant.getEtudiantByEtuForNote(connection, etu);
                etudiant = etudiant.setMoyenneEtudiant(connection, etudiant, idSemestre, appreciation);
                etudiant = etudiant.setAppreciationMatiere(etudiant, moyenneMatiere, moyenneGeneralNeeded,  appreciation);
                request.setAttribute("etudiant", etudiant);
                
            //FONCTIONNALITE ADMIN
            HttpSession session = request.getSession(false);
            boolean isAdmin = (Boolean) session.getAttribute("admin");
            if (isAdmin == true) {
                request.setAttribute("semestreSelected", idSemestre);
            }
                
            Semestre semestre = new Semestre();
            List<Semestre> listeSemestre = semestre.getSemestreEtudiantFromNote(connection, etudiant.getEtu());
                request.setAttribute("liste_Semestre", listeSemestre);
                
            RequestDispatcher dispatcher = request.getRequestDispatcher("home/"+destinationPage);
                dispatcher.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(C_Note_R.class.getName()).log(Level.SEVERE, null, ex);
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
