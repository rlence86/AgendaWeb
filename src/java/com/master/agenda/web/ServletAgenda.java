/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.master.agenda.web;

import com.master.agenda.data.ContactoDAO;
import com.master.agenda.utils.ConstantesAgenda;
import com.ramonlence.agenda.logica.Contacto;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
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
public class ServletAgenda extends HttpServlet {

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
            String operacionSeleccionada = request.getParameter("op");
            if (cookieActiva) {
                if (operacionSeleccionada.equals(ConstantesAgenda.OPERACION_VER_TODOS_CONTACTOS)) {
                    //Ver todos los contactos
                    //Buscar todos los contactos
                    List<Contacto> contactos = ContactoDAO.getInstance().consultarTodos();
                    //Añadir todos los contactos al request
                    request.setAttribute(ConstantesAgenda.ATRIBUTO_CONTACTOS, contactos);
                    //Redirigir petición para mostrar los contactos
                    RequestDispatcher rd = request.getRequestDispatcher("/verContactos.jsp");
                    rd.forward(request, response);
                } else if (operacionSeleccionada.equals(ConstantesAgenda.OPERACION_BUSCAR_UN_CONTACTO)) {
                    //Mostrar formulario de búsqueda de usuario
                } else if (operacionSeleccionada.equals(ConstantesAgenda.OPERACION_ANADIR_CONTACTO_NUEVO)) {
                    //Añadir contacto nuevo
                } else if (operacionSeleccionada.equals(ConstantesAgenda.OPERACION_ELIMINAR_CONTACTO)) {
                    //Eliminar contacto
                } else if (operacionSeleccionada.equals(ConstantesAgenda.OPERACION_MODIFICAR_CONTACTO)) {
                    //Modificar contacto
                } else {
                    //Otra operación (error)
                }
                //Si la cookie nombreUsuario no está activa, se redirige al formulario de login
            } else {
                //Comprobamos si se está recibiendo el nombre de usuario desde login
                if (operacionSeleccionada.equals("0")) {
                    //Si la operación es 0, se reciben los datos desde login
                    String nombreUsuario = request.getParameter("nombreUsuario");
                    //Se crea la cookie con el valor recogido del formulario
                    Cookie cookie = new Cookie("nombreUsuario", nombreUsuario);
                    //Se le indica un máximo de duración y se añade a response
                    int unaSemana = 7*24*60*60;
                    cookie.setMaxAge(unaSemana);
                    response.addCookie(cookie);
                    //Se vuelve al índice
                    RequestDispatcher rd = request.getRequestDispatcher("/index.jsp");
                    rd.forward(request, response);
                } else {
                    RequestDispatcher rd = request.getRequestDispatcher("/login.jsp");
                    rd.forward(request, response);
                }
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
