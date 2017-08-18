/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Irindu
 */
public class UpdateGame extends HttpServlet {

    final GameStation theGameStation = new GameStation();//

    @Override
    public void init(ServletConfig config) {
    }

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
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
        // processRequest(request, response);
        response.setContentType("text/event-stream;charset=UTF-8");
        HttpSession session = request.getSession(false);//session object is to identify the user
        if (session == null) {//new user
            synchronized (theGameStation) {//lockobject
                session = request.getSession(true);
                session.setAttribute("playerId", theGameStation.initPlayer());
            }
        }
        //   System.out.println(gs.getResponse());
        try (PrintWriter out = response.getWriter()) {
            out.print("data:");
            out.println(theGameStation.getResponse());
            out.println();
            out.flush();
            while (!Thread.interrupted()) {
                try {
                    synchronized (theGameStation) {
                        theGameStation.wait();
                        out.print("data:");
                        out.println(theGameStation.getResponse());
                        out.println();
                        out.flush();
                    }
                    //  Thread.sleep(50);
                } catch (InterruptedException e) {
                    Logger.getGlobal().log(Level.INFO, "Stock updates terminated!");
                    break;
                }
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
        // processRequest(request, response);

        System.out.println(request.getParameter("keypress"));
        HttpSession session = request.getSession(false);
        String playerId = (String) session.getAttribute("playerId");
        synchronized (theGameStation) {
            theGameStation.updateGame(playerId, Integer.parseInt(request.getParameter("keypress")));
            theGameStation.notifyAll();
        }
        response.setContentType("text/html;charset=UTF-8");

        try (PrintWriter out = response.getWriter()) {
            out.print("data:");
            out.println();
            out.println();
            out.flush();

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
