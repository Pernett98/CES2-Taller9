/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.poli.ces2.impl.crud;

import co.edu.poli.ces2.crud.IColumna;
import co.edu.poli.ces2.crud.ICrud;
import co.edu.poli.ces2.crud.IDto;
import static co.edu.poli.ces2.util.ConexionDB.getConexion;
import java.beans.Statement;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import static co.edu.poli.ces2.util.ConexionDB.getConexion;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import jdk.nashorn.internal.codegen.TypeMap;

/**
 *
 * @author sebastian
 */
public class Crud implements ICrud {

    private int posicion, cantidad, filas;
    private boolean isConexionAutomatica = true;
    public Connection conexion;

    public Crud() {
    }

    @Override
    public int insert(IDto dto) {
        String tabla = dto.get_Tabla();
        String campos = "";
        String interroga = "";
        IColumna col;

        for (Field atributo : dto.getClass().getDeclaredFields()) {
            atributo.setAccessible(true);
            col = (IColumna) atributo.getAnnotation(IColumna.class);
            if (col == null || col.automatica()) {
                continue;
            }
            campos += "," + atributo.getName();
            interroga += ",?";
        }
        campos = campos.substring(1);
        interroga = interroga.substring(1);

        String sql = "INSERT INTO " + tabla + "(" + campos + ") VALUES (" + interroga + ")";

        int res = 0;
        try {
            Connection con = get_Conexion();

            PreparedStatement cmd = con.prepareStatement(sql, java.sql.Statement.RETURN_GENERATED_KEYS);
            int ind = 1;
            for (Field atributo : dto.getClass().getDeclaredFields()) {
                atributo.setAccessible(true);
                cmd.setObject(ind++, atributo.get(dto));
            }
            res = cmd.executeUpdate();
            ResultSet filas;
            filas = cmd.getGeneratedKeys();
            while (filas.next()) {
                for (Field atributo : dto.getClass().getDeclaredFields()) {
                    atributo.setAccessible(true);
                    col = atributo.getAnnotation(IColumna.class);
                    if (col == null || col.pk()) {
                        continue;
                    }

                    Object valor = filas.getObject(atributo.getName().toLowerCase());
                    atributo.set(dto, valor);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

    @Override
    public int update(IDto dto) {
        String tabla = dto.getClass().getSimpleName();
        String interroga = "";
        String iPrimaryKey = "";
        IColumna col;

        for (Field atributo : dto.getClass().getDeclaredFields()) {
            atributo.setAccessible(true);
            col = (IColumna) atributo.getAnnotation(IColumna.class);
            iPrimaryKey += col.pk() ? "," + atributo.getName() : "";
            if (col == null || col.automatica() || col.pk()) {
                continue;
            }
            interroga += ", " + atributo.getName();
            interroga += " = ?";
        }

        interroga = interroga.substring(1);
        iPrimaryKey = iPrimaryKey.substring(1);

        String sql = "UPDATE " + tabla + " SET " + interroga + " WHERE " + iPrimaryKey + " = ?";

        int res = 0;
        String primaryKey = "";
        try {
            Connection con = get_Conexion();
            PreparedStatement cmd = con.prepareStatement(sql, java.sql.Statement.RETURN_GENERATED_KEYS);
            int ind = 1;
            for (Field atributo : dto.getClass().getDeclaredFields()) {
                atributo.setAccessible(true);
                col = (IColumna) atributo.getAnnotation(IColumna.class);
                if (col.pk()) {
                    primaryKey += atributo.get(dto);
                    continue;
                }
                cmd.setObject(ind++, atributo.get(dto));
            }
            cmd.setObject(ind++, primaryKey);

            res = cmd.executeUpdate();
            ResultSet filas;
            filas = cmd.getGeneratedKeys();
            while (filas.next()) {
                for (Field atributo : dto.getClass().getDeclaredFields()) {
                    atributo.setAccessible(true);
                    col = atributo.getAnnotation(IColumna.class);
                    if (col == null || col.automatica()) {
                        continue;
                    }
                    Object valor = filas.getObject(atributo.getName().toLowerCase());
                    atributo.set(dto, valor);
                }
            }
        } catch (Exception e) {
            System.out.println("err" + e);
        }
        return res;
    }

    @Override
    public int delete(IDto dto) {
        String tabla = dto.getClass().getSimpleName();
        String interroga = "";
        IColumna col;

        for (Field atributo : dto.getClass().getDeclaredFields()) {
            atributo.setAccessible(true);
            col = (IColumna) atributo.getAnnotation(IColumna.class);
            interroga += col.pk() ? "," + atributo.getName() + " = ?" : "";
            if (col == null || col.automatica() || col.pk()) {
                continue;
            }
        }
        interroga = interroga.substring(1);

        String sql = "DELETE FROM " + tabla + " WHERE " + interroga;

        int res = 0;
        try {
            Connection con = get_Conexion();
            System.out.println(sql);
            PreparedStatement cmd = con.prepareStatement(sql, java.sql.Statement.RETURN_GENERATED_KEYS);
            int ind = 1;
            for (Field atributo : dto.getClass().getDeclaredFields()) {
                atributo.setAccessible(true);
                col = (IColumna) atributo.getAnnotation(IColumna.class);
                if (!col.pk()) {
                    continue;
                }
                cmd.setObject(ind++, atributo.get(dto));
            }

            res = cmd.executeUpdate();
            ResultSet filas;
            filas = cmd.getGeneratedKeys();
            while (filas.next()) {
                for (Field atributo : dto.getClass().getDeclaredFields()) {
                    atributo.setAccessible(true);
                    col = atributo.getAnnotation(IColumna.class);
                    if (col == null || col.automatica()) {
                        continue;
                    }
                    Object valor = filas.getObject(atributo.getName().toLowerCase());
                    atributo.set(dto, valor);
                }
            }
        } catch (Exception e) {
            System.out.println("err" + e);
        }
        return res;
    }

    @Override
    public int execute(String sql, Object... parametros) {
        int res = 0;
        try {
            Connection con = get_Conexion();

            PreparedStatement cmd = con.prepareStatement(sql, java.sql.Statement.RETURN_GENERATED_KEYS);
            int ind = 1;

            for (Object parametro : parametros) {
                cmd.setObject(ind++, parametro);
            }
            cmd.executeUpdate();
            res = cmd.getUpdateCount();
        } catch (Exception e) {
            System.out.println("err" + e);
        }
        return res;
    }

    @Override
    public int save(IDto dto) {
        String tabla = dto.getClass().getSimpleName();
        //String campos = "";
        String interroga = "";
        IColumna col;

        for (Field atributo : dto.getClass().getDeclaredFields()) {
            atributo.setAccessible(true);
            col = (IColumna) atributo.getAnnotation(IColumna.class);
            if (col == null || !col.pk()) {
                continue;
            }
            interroga += "," + atributo.getName() + " = ? ";
        }
        interroga = interroga.substring(1);

        String sql = "SELECT * FROM " + tabla + " WHERE " + interroga;

        int res = 0;
        try {
            Connection con = get_Conexion();

            PreparedStatement cmd = con.prepareStatement(sql, java.sql.Statement.RETURN_GENERATED_KEYS);
            int ind = 1;
            for (Field atributo : dto.getClass().getDeclaredFields()) {
                atributo.setAccessible(true);
                col = (IColumna) atributo.getAnnotation(IColumna.class);
                if (col == null || !col.pk()) {
                    continue;
                }
                cmd.setObject(ind++, atributo.get(dto));
            }

            ResultSet filas;
            filas = cmd.executeQuery();
            boolean flag = false;
            while (filas.next()) {
                flag = true;
            }
            res = flag ? this.update(dto) : this.insert(dto);
        } catch (Exception e) {
            System.out.println("err" + e);
        }
        return res;
    }

    @Override
    public <DTO extends IDto> List<DTO> select(DTO dto) {
        LinkedList<DTO> lista = new LinkedList<>();
        String tabla = dto.getClass().getSimpleName();

        try {
            Connection con = this.get_Conexion();

            String interroga = "";
            IColumna col;

            for (Field atributo : dto.getClass().getDeclaredFields()) {
                atributo.setAccessible(true);
                col = (IColumna) atributo.getAnnotation(IColumna.class);
                if (col == null || col.automatica() || col.pk()) {
                    continue;
                }
                interroga += "AND " + atributo.getName() + " = ? ";

            }
            interroga = interroga.substring(4);

            String sql = "SELECT * FROM " + tabla + " WHERE " + interroga;

            PreparedStatement cmd = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            int ind = 1;
            for (Field atributo : dto.getClass().getDeclaredFields()) {
                atributo.setAccessible(true);
                col = (IColumna) atributo.getAnnotation(IColumna.class);
                if (col == null || col.automatica() || col.pk()) {
                    continue;
                }
                cmd.setObject(ind++, atributo.get(dto));
            }
            ResultSet filas;
            int cantidadFilas = this.cantidad;

            filas = cmd.executeQuery();
            if (this.posicion > 0) {
                filas.absolute(this.posicion);
            }

            while (filas.next()) {
                DTO reg = (DTO) dto.getClass().newInstance();
                for (Field atributo : dto.getClass().getDeclaredFields()) {
                    atributo.setAccessible(true);
                    col = atributo.getAnnotation(IColumna.class);
                    if (col == null) {
                        continue;
                    }
                    Object valor = filas.getObject(atributo.getName().toLowerCase());
                    atributo.set(reg, valor);
                }
                lista.add(reg);
                this.filas = cantidad;

                if (cantidadFilas == this.cantidad) {
                    break;
                }
                cantidadFilas++;
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return lista;
    }

    @Override
    public <DTO extends IDto> List<DTO> select(DTO dto, String sql, Object... params) {
        LinkedList<DTO> lista = new LinkedList<>();
        try {
            Connection con = this.get_Conexion();

            PreparedStatement cmd = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            int ind = 1;
            for (Object val : params) {
                cmd.setObject(ind++, val);
            }

            ResultSet filas;
            filas = cmd.executeQuery();
            int cantidadFilas = 1;

            if (this.posicion > 0) {
                filas.absolute(this.posicion);
            }

            while (filas.next()) {
                DTO reg = (DTO) dto.getClass().newInstance();
                IColumna col;
                for (Field atributo : dto.getClass().getDeclaredFields()) {
                    atributo.setAccessible(true);
                    col = atributo.getAnnotation(IColumna.class);
                    if (col == null) {
                        continue;
                    }
                    Object valor = filas.getObject(atributo.getName().toLowerCase());
                    atributo.set(reg, valor);
                }
                lista.add(reg);
                this.filas = cantidadFilas;

                if (cantidadFilas == this.cantidad) {
                    break;
                }
                cantidadFilas++;
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return lista;
    }

    @Override
    public Connection get_Conexion() {
        if (this.conexion == null) {
            this.conexion = getConexion();
        }
        return this.conexion;
    }

    @Override
    public void set_Conexion(Connection Conexion) {
        try {
            this.conexion = Conexion;
            Conexion.setAutoCommit(this.isConexionAutomatica);
        } catch (SQLException ex) {
            Logger.getLogger(Crud.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public boolean isConexionAutomatica() {
        try {
            return this.conexion.getAutoCommit();
        } catch (SQLException ex) {
            Logger.getLogger(Crud.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    @Override
    public int get_Posicion() {
        return this.posicion;
    }

    @Override
    public void set_Posicion(int posicion) {
        this.posicion = posicion;
    }

    @Override
    public int get_Cantidad() {
        return this.cantidad;
    }

    @Override
    public void set_Cantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    @Override
    public int get_Filas() {
        return this.filas;
    }

    public void get_Tablas() {
        try {
            ResultSet tablas = this.conexion.getMetaData().getTables("ces2", "public", null, new String[]{"TABLE"});

            while (tablas.next()) {
                System.out.println(tablas.getString("TABLE_NAME"));
                System.out.println(tablas.getString("TABLE_TYPE"));

                ResultSet campos = this.conexion.getMetaData().getColumns("ces2", null, tablas.getString("TABLE_NAME"), null);
                ResultSet rsCampos = this.conexion.createStatement().executeQuery("SELECT * FROM " + tablas.getString("TABLE_NAME"));
                ResultSetMetaData rsmd = rsCampos.getMetaData();
                int i = 1;

                while (campos.next()) {

                    System.out.println("campos");
                    System.out.println(campos.getString("COLUMN_NAME") + " - " + campos.getString("TYPE_NAME"));
                    //System.out.println(rsmd.getColumnClassName(campos.getInt("DATA_TYPE")));
                    System.out.println(rsmd.getColumnClassName(i++) + "--------" + rsmd.getColumnName(i++));
                }
                
                i = 1;
                System.out.println("\n");

            }
            System.out.println("\n");
        } catch (SQLException ex) {
            Logger.getLogger(Crud.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
