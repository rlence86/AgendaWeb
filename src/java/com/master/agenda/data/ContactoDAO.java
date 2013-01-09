/**
 * Clase que gestiona operaciones contra la BBDD Implementa un patrón Singleton
 * para usar siempre la misma isntancia
 */
package com.master.agenda.data;

import com.master.agenda.logica.Amigo;
import com.master.agenda.logica.Contacto;
import com.master.agenda.logica.Profesional;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ContactoDAO {

    private static ContactoDAO INSTANCIA;
    private Connection conexion;

    private ContactoDAO() {
    }

    public static ContactoDAO getInstance() {
        if (INSTANCIA == null) {
            INSTANCIA = new ContactoDAO();
        }
        return INSTANCIA;
    }

    /**
     * Inserta un contacto en la BBDD
     *
     * @param c El contacto
     */
    public int insertarContacto(Contacto c) {
        abrirConexion();
        try {
            String query;
            PreparedStatement stm;
            //Comprobamos el tipo de contacto para ver que datos se van a guardar
            if (c instanceof Amigo) {
                //preparamos la query para un amigo
                query = "INSERT INTO USUARIO.CONTACTOS (NOMBRE, EMAIL, TELEFONO, FECHACUMPLE) VALUES (?,?,?,?)";
                stm = conexion.prepareStatement(query);
                //a�adimos los elementos especificos
                Calendar cal = ((Amigo) c).getFechaCumpleCalendar();
                Date fecha = new Date(cal.getTime().getTime());
                stm.setDate(4, fecha);
            } else if (c instanceof Profesional) {
                //preparamos la query para un contacto profesional
                query = "INSERT INTO USUARIO.CONTACTOS (NOMBRE, EMAIL, TELEFONO, DIRECCION, EMPRESA) VALUES (?,?,?,?,?)";
                stm = conexion.prepareStatement(query);
                //a�adimos los elementos especificos
                stm.setString(4, ((Profesional) c).getDireccion());
                stm.setString(5, ((Profesional) c).getEmpresa());
            } else {
                //preparamos la query para un contacto generico (no deberia darse el caso)
                query = "INSERT INTO USUARIO.CONTACTOS (NOMBRE, EMAIL, TELEFONO) VALUES (?, ?, ?)";
                stm = conexion.prepareStatement(query);
            }
            //añadimos los elementos generales
            stm.setString(1, c.getNombre());
            stm.setString(2, c.getEmail());
            stm.setLong(3, c.getTelefono());
            //se ejecuta la query
            return stm.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
            return 0;
        } finally {
            cerrarConexion();
        }
    }

    /**
     * Elimina un contacto de la BBDD mediante su nombre
     *
     * @param nombre El nombre del contacto
     * @return El número de celdas afectadas por la operación.
     */
    public int borrarContacto(String nombre) {
        abrirConexion();
        try {
            String query = "DELETE FROM USUARIO.CONTACTOS WHERE NOMBRE = ?";
            PreparedStatement stm = conexion.prepareStatement(query);
            //asignar parámetro
            stm.setString(1, nombre);
            //ejecutar consulta
            return stm.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error en el statement");
            e.printStackTrace();
        } finally {
            cerrarConexion();
        }
        return 0;
    }

    /**
     * Busca un contacto por su nombre
     *
     * @param nombre El nombre del contacto
     * @return El contacto encontrado.
     */
    public Contacto buscarContacto(String nombre) {
        abrirConexion();
        Contacto resultado = null;
        try {
            String query = "SELECT * FROM USUARIO.CONTACTOS WHERE NOMBRE = ?";
            PreparedStatement stm = conexion.prepareStatement(query);
            //asignar parámetro
            stm.setString(1, nombre);
            //ejecutar consulta
            ResultSet resultados = stm.executeQuery();
            while (resultados.next()) {
                if (resultados.getDate("FECHACUMPLE") != null) {
                    //el contacto es un amigo
                    Date fecha = resultados.getDate("FECHACUMPLE");
                    Calendar cal = Calendar.getInstance();
                    cal.setTime(fecha);
                    resultado = new Amigo(resultados.getString("NOMBRE"), resultados.getString("EMAIL"), (long) resultados.getInt("TELEFONO"), cal);
                } else if (resultados.getString("DIRECCION") != null) {
                    //el contacto es profesional
                    resultado = new Profesional(resultados.getString("NOMBRE"), resultados.getString("EMAIL"), (long) resultados.getInt("TELEFONO"), resultados.getString("DIRECCION"), resultados.getString("EMPRESA"));
                }
            }
        } catch (SQLException e) {
            System.out.println("Error en el statement");
            e.printStackTrace();
        } finally {
            cerrarConexion();
        }
        return resultado;
    }

    /*
     * Consultar todos los contactos
     * @return Lista de Contactos en la base de datos
     * Comprueba si el contacto es un amigo o un contacto profesional según los datos almacenados
     * y lo añade a la lista con su clase correspondiente
     */
    public List<Contacto> consultarTodos() {
        abrirConexion();
        List<Contacto> contactos = new ArrayList<Contacto>();
        try {
            Statement stm = conexion.createStatement();
            ResultSet resultados = stm.executeQuery("SELECT * FROM USUARIO.CONTACTOS");
            while (resultados.next()) {
                if (resultados.getDate("FECHACUMPLE") != null) {
                    //el contacto es un amigo
                    Date fecha = resultados.getDate("FECHACUMPLE");
                    Calendar cal = Calendar.getInstance();
                    cal.setTime(fecha);
                    contactos.add(new Amigo(resultados.getString("NOMBRE"), resultados.getString("EMAIL"), (long) resultados.getInt("TELEFONO"), cal));
                } else if (resultados.getString("DIRECCION") != null) {
                    //el contacto es profesional
                    contactos.add(new Profesional(resultados.getString("NOMBRE"), resultados.getString("EMAIL"), (long) resultados.getInt("TELEFONO"), resultados.getString("DIRECCION"), resultados.getString("EMPRESA")));
                }
            }
        } catch (SQLException e) {
            System.out.println("Error en el statement");
            e.printStackTrace();
        } finally {
            cerrarConexion();
        }
        return contactos;
    }

    /**
     * Modifica el número de teléfono de un contacto determinado
     *
     * @param nombre El nombre del contacto
     * @param nuevoTelefono El nuevo número de teléfono
     * @return El número de celdas afectadas por la operación
     */
    public int modificarTelefono(String nombre, Long nuevoTelefono) {
        abrirConexion();
        try {
            String query = "UPDATE USUARIO.CONTACTOS SET TELEFONO=? WHERE NOMBRE = ?";
            PreparedStatement stm = conexion.prepareStatement(query);
            //asignar parámetro
            stm.setLong(1, nuevoTelefono);
            stm.setString(2, nombre);
            //ejecutar consulta
            return stm.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error en el statement");
            e.printStackTrace();
        } finally {
            cerrarConexion();
        }
        return 0;
    }

    /**
     * Modifica el mail de un contacto determinado
     *
     * @param nombre El nombre del contacto
     * @param nuevoEmail El nuevo mail del contacto
     * @return El número de celdas afectadas por la operación
     */
    public int modificarEmail(String nombre, String nuevoEmail) {
        abrirConexion();
        try {
            String query = "UPDATE USUARIO.CONTACTOS SET EMAIL=? WHERE NOMBRE = ?";
            PreparedStatement stm = conexion.prepareStatement(query);
            //asignar parámetro
            stm.setString(1, nuevoEmail);
            stm.setString(2, nombre);
            //ejecutar consulta
            return stm.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error en el statement");
            e.printStackTrace();
        } finally {
            cerrarConexion();
        }
        return 0;
    }

    /**
     * Abre una conexión con la Base de Datos
     */
    private void abrirConexion() {
        try {
            //Cargar el driver de Derby
            Class.forName("org.apache.derby.jdbc.ClientDriver");

            //Apertura de la conexión
            conexion = DriverManager.getConnection("jdbc:derby://localhost:1527/Agenda", "usuario", "user");
        } catch (SQLException ex) {
            System.out.println("Error al abrir la conexión");
            ex.printStackTrace();
        } catch (Exception e) {
            System.out.println("Error al cargar el driver");
        }
    }

    /**
     * Cierra la conexión con la Base de Datos
     */
    private void cerrarConexion() {
        try {
            conexion.close();
        } catch (SQLException ex) {
            System.out.println("Error al cerrar la conexión");
        }
    }
}
