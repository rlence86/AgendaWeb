/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.master.agenda.web;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Ramón Lence <rlence86@gmail.com>
 */
public class ServletMenu extends HttpServlet {

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            //Comprobar si tiene la cookie de usuario almacenada
            Cookie[] cookies = request.getCookies();
            boolean cookieActiva = false;
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("nombreUsuario")) {
                    cookieActiva = true;
                }
            }
            //Si la cookie nombreUsuario está activa, se redirige a la página que se busca
            if (cookieActiva) {
                if (request.getParameter("op").equals("1")) {
                } else if (request.getParameter("op").equals("2")) {
                } else if (request.getParameter("op").equals("3")) {
                } else if (request.getParameter("op").equals("4")) {
                } else if (request.getParameter("op").equals("5")) {
                } else {
                }
                //Si la cookie nombreUsuario no está activa, se redirige al formulario de login
            } else {
                RequestDispatcher rd = request.getRequestDispatcher("/login.jsp");
                rd.forward(request, response);
            }
        } finally {
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
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
     * Handles the HTTP
     * <code>POST</code> method.
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
