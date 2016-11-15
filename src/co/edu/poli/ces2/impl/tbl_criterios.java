package co.edu.poli.ces2.impl;

import co.edu.poli.ces2.crud.IColumna;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Timestamp;

/**
 *
 * @author thomas
 */
public class tbl_criterios extends tbl_general {

    @IColumna(pk = true, automatica = true)
    private java.lang.Integer Id_criterio;
    @IColumna
    private String Nombre;
    @IColumna
    private String Descripcion;
    @IColumna
    private Integer Modifica_usuario;
    @IColumna
    private Integer Id_criterio_tipo;
    @IColumna
    private Timestamp Modifica_fecha;

    public tbl_criterios() {
    }

    public Integer getId_criterio() {
        return Id_criterio;
    }

    public Integer getModifica_usuario() {
        return Modifica_usuario;
    }

    public void setModifica_usuario(Integer Modifica_usuario) {
        this.Modifica_usuario = Modifica_usuario;
    }

    public void setModifica_fecha(Timestamp Modifica_fecha) {
        this.Modifica_fecha = Modifica_fecha;
    }

    public void setId_criterio_tipo(Integer Id_criterio_tipo) {
        this.Id_criterio_tipo = Id_criterio_tipo;
    }

    public Integer getId_criterio_tipo() {
        return Id_criterio_tipo;
    }

    public Timestamp getModifica_fecha() {
        return Modifica_fecha;
    }

    public void setId_criterio(Integer Id_criterio) {
        this.Id_criterio = Id_criterio;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String Descripcion) {
        this.Descripcion = Descripcion;
    }

    public Integer getActualiza_usuario() {
        return Modifica_usuario;
    }

    public void setActualiza_usuario(Integer Actualiza_usuario) {
        this.Modifica_usuario = Actualiza_usuario;
    }

    public Timestamp getActualiza_fecha() {
        return Modifica_fecha;
    }

    public void setActualiza_fecha(Timestamp Actualiza_fecha) {
        this.Modifica_fecha = Actualiza_fecha;
    }

    public boolean actualizar() {
        return false;
    }

    private Connection getConexion() {
        Connection con = null;
        String usuario = "ppit";
        String clave = "ppit";
        String url = "jdbc:postgresql://10.126.13.78:5432/ppit";
        String driver = "org.postgresql.Driver";
        try {
            Class.forName(driver);
            con = DriverManager.getConnection(url, usuario, clave);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return con;

    }
}
