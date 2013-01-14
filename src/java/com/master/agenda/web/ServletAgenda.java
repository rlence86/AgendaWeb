/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.master.agenda.web;

import com.master.agenda.data.ContactoDAO;
import com.master.agenda.logica.Contacto;
import com.master.agenda.utils.ConstantesAgenda;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
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
            boolean cookieActiva = chequearRegistrado(request);
            //Si la cookie nombreUsuario está activa, se redirige a la página que se busca
            String operacionSeleccionada = request.getParameter("op");
            if (cookieActiva) {
                if (operacionSeleccionada.equals(ConstantesAgenda.OPERACION_VER_TODOS_CONTACTOS)) {
                    verTodosContactos(request, response);
                } else if (operacionSeleccionada.equals(ConstantesAgenda.OPERACION_BUSCAR_UN_CONTACTO)) {
                    //Se marca el objetivo como búsqueda
                    request.setAttribute("objetivo", "busqueda");
                    //Se muestra el formulario
                    mostrarFormularioBusqueda(request, response);
                } else if (operacionSeleccionada.equals(ConstantesAgenda.OPERACION_ANADIR_CONTACTO_NUEVO)) {
                    mostrarFormularioContactoNuevo(request,response);
                } else if (operacionSeleccionada.equals(ConstantesAgenda.OPERACION_ELIMINAR_CONTACTO)) {
                    //Se marca el objetivo como búsqueda
                    request.setAttribute("objetivo", "eliminar");
                    //Se muestra el formulario
                    mostrarFormularioBusqueda(request, response);
                } else if (operacionSeleccionada.equals(ConstantesAgenda.OPERACION_MODIFICAR_CONTACTO)) {
                    //Se marca el objetivo como búsqueda
                    request.setAttribute("objetivo", "modificar");
                    //Se muestra el formulario
                    mostrarFormularioBusqueda(request, response);
                } else if (operacionSeleccionada.equals(ConstantesAgenda.OPERACION_BUSCAR_CON_NOMBRE)) {
                    buscarUnContacto(request, response);
                } else if (operacionSeleccionada.equals(ConstantesAgenda.OPERACION_ELIMINAR_CON_NOMBRE)) {
                    eliminarUnContacto(request, response);
                } else if (operacionSeleccionada.equals(ConstantesAgenda.OPERACION_MODIFICAR_CON_NOMBRE)) {
                    mostrarFormularioModificar(request, response);
                } else if (operacionSeleccionada.equals(ConstantesAgenda.OPERACION_MODIFICAR_CON_DATOS)) {
                    modificarUnContacto(request,response);
                } else if (operacionSeleccionada.equals(ConstantesAgenda.OPERACION_ANADIR_AMIGO)) {
                    anadirNuevoAmigo(request,response);
                } else if (operacionSeleccionada.equals(ConstantesAgenda.OPERACION_ANADIR_PROFESIONAL)) {
                    anadirNuevoProfesional(request,response);
                } else {
                    //Otra operación (error)
                }
                //Si la cookie nombreUsuario no está activa, se redirige al formulario de login
            } else {
                //Comprobamos si se está recibiendo el nombre de usuario desde login
                if (operacionSeleccionada.equals(ConstantesAgenda.OPERACION_REGISTRAR_USUARIO)) {
                    registrarUsuario(request, response);
                } else {
                    //Se redirige a login
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

    private void verTodosContactos(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        //Ver todos los contactos
        //Buscar todos los contactos
        List<Contacto> contactos = ContactoDAO.getInstance().consultarTodos();
        //Añadir todos los contactos al request
        request.setAttribute(ConstantesAgenda.ATRIBUTO_CONTACTOS, contactos);
        //Redirigir petición para mostrar los contactos
        RequestDispatcher rd = request.getRequestDispatcher("/verContactos.jsp");
        rd.forward(request, response);
    }

    private void mostrarFormularioBusqueda(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        //Mostrar formulario de búsqueda de usuario
        RequestDispatcher rd = request.getRequestDispatcher("/formularioBusqueda.jsp");
        rd.forward(request, response);
    }

    private void buscarUnContacto(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Buscar con el nombre de usuario
        //Obtener el nombre de contacto a mostrar
        String nombreContacto = request.getParameter("nombreContacto");
        //Buscar contacto por nombre
        List<Contacto> contactos = new ArrayList<Contacto>();
        if(!nombreContacto.equals("")){
            contactos.add(ContactoDAO.getInstance().buscarContacto(nombreContacto));
        } else {
            //Se marca el objetivo como búsqueda
            request.setAttribute("objetivo", "busqueda");
            mostrarFormularioBusqueda(request, response);
        }                    
        //Añadir resultado a request
        request.setAttribute(ConstantesAgenda.ATRIBUTO_CONTACTOS, contactos);
        //Redirigir petición para mostrar los contactos
        RequestDispatcher rd = request.getRequestDispatcher("/verContactos.jsp");
        rd.forward(request, response);
    }

    private boolean chequearRegistrado(HttpServletRequest request) {
        //Comprobar si tiene la cookie de usuario almacenada
        Cookie[] cookies = request.getCookies();
        boolean cookieActiva = false;
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("nombreUsuario")) {
                cookieActiva = true;
            }
        }
        return cookieActiva;
    }

    private void registrarUsuario(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
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
    }

    private void eliminarUnContacto(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        //Buscar con el nombre de usuario
        //Obtener el nombre de contacto a mostrar
        String nombreContacto = request.getParameter("nombreContacto");
        if(!nombreContacto.equals("")){
            ContactoDAO.getInstance().borrarContacto(nombreContacto);
        } else {
            //Se marca el objetivo como eliminar
            request.setAttribute("objetivo", "eliminar");
            mostrarFormularioBusqueda(request, response);
        }
        //Redirigir petición para mostrar los contactos
        RequestDispatcher rd = request.getRequestDispatcher("/index.jsp");
        rd.forward(request, response);
    }

    private void mostrarFormularioModificar(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        //Buscar con el nombre de usuario
        //Obtener el nombre de contacto a mostrar
        String nombreContacto = request.getParameter("nombreContacto");
        Contacto con = null;
        if(!nombreContacto.equals("")){
            con = ContactoDAO.getInstance().buscarContacto(nombreContacto);
        } else {
            //Se marca el objetivo como eliminar
            request.setAttribute("objetivo", "modificar");
            mostrarFormularioBusqueda(request, response);
        }
        if(con!= null){
            request.setAttribute("contacto", con);
            //Redirigir petición para mostrar el formulario de modificación
            RequestDispatcher rd = request.getRequestDispatcher("/formularioModificacion.jsp");
            rd.forward(request, response);
        } else {
            System.out.println("no hay contacto con ese nombre");
        }
    }

    private void modificarUnContacto(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Buscar con el nombre de usuario
        //Obtener el nombre de contacto a mostrar
        String nombreContacto = request.getParameter("nombreContacto");
        //Obtener el nuevo número de teléfono
        String telefono = request.getParameter("telefonoText");
        Long numeroTelefono = Long.parseLong(telefono);
        //Obtener el nuevo mail
        String email = request.getParameter("emailText");
        if(!nombreContacto.equals("")){
            ContactoDAO.getInstance().modificarEmail(nombreContacto, email);
            ContactoDAO.getInstance().modificarTelefono(nombreContacto, numeroTelefono);
        } else {
            //Se marca el objetivo como eliminar
            request.setAttribute("objetivo", "eliminar");
            mostrarFormularioBusqueda(request, response);
        }
        //Redirigir petición para mostrar los contactos
        RequestDispatcher rd = request.getRequestDispatcher("/index.jsp");
        rd.forward(request, response);
    }

    private void mostrarFormularioContactoNuevo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Mostrar formulario de búsqueda de usuario
        RequestDispatcher rd = request.getRequestDispatcher("/formularioContactoNuevo.jsp");
        rd.forward(request, response);
    }

    private void anadirNuevoAmigo(HttpServletRequest request, HttpServletResponse response) {
        String nombreContacto = request.getParameter("nombreContacto");
        String correoElectronico = request.getParameter("correoElectronico");
        String telefonoText = request.getParameter("textoTelefono");
        String cumple = request.getParameter("fechaCumple");
        
        Long telefono = Long.parseLong(telefonoText);
    }

    private void anadirNuevoProfesional(HttpServletRequest request, HttpServletResponse response) {
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
