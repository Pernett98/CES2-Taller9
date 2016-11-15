/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.poli.ces2.impl;

import co.edu.poli.ces2.crud.IColumna;
import static co.edu.poli.ces2.util.ConexionDB.getConexion;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 *
 * @author sebastian
 */
public class tbl_general {

  public tbl_general() {
  }

  private String getSQLInsert() {
    String tabla = this.getClass().getSimpleName();
    String campos = "";
    String interroga = "";
    IColumna col;
    for (Field atributo : this.getClass().getDeclaredFields()) {
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

    return "INSERT INTO " + tabla + "(" + campos + ") VALUES (" + interroga + ")";

  }

  private String getSQLDelete() {
    String tabla = this.getClass().getSimpleName();
    String campos = "";
    String interroga = "";
    for (Field atributo : this.getClass().getDeclaredFields()) {
      atributo.setAccessible(true);
      campos += "," + atributo.getName();
      interroga += ",?";
    }
    campos = campos.substring(1);
    interroga = interroga.substring(1);

    return "INSERT INTO " + tabla + "(" + campos + ") VALUES (" + interroga + ")";
  }

  public int insertar() {
    int res = 0;
    try {
      Connection con = getConexion();
//
      String sql = getSQLInsert();
      PreparedStatement cmd = con.prepareStatement(sql);
      int ind = 1;
      for (Field atributo : this.getClass().getDeclaredFields()) {
        cmd.setObject(ind++, atributo.get(this.getClass()));

      }
      res = cmd.executeUpdate();
    } catch (Exception ex) {
      ex.printStackTrace();
    }
    return res;
  }

  public int eliminar() {
    int res = 0;
    try {
      Connection con = getConexion();
//
      String sql = getSQLDelete();
      PreparedStatement cmd = con.prepareStatement(sql);
      int ind = 1;
      for (Field atributo : this.getClass().getDeclaredFields()) {
        cmd.setObject(ind++, atributo.get(this));

      }
      res = cmd.executeUpdate();
    } catch (Exception ex) {
      ex.printStackTrace();
    }
    return res;
  }

}
