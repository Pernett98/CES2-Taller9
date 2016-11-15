/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.poli.ces2.impl;

import co.edu.poli.ces2.crud.IDto;
import co.edu.poli.ces2.crud.IColumna;
import java.sql.Timestamp;
/**
 *
 * @author sebastian
 */
public class tbl_usuarios implements IDto {
  
  @IColumna (pk = true)
  private String id_usuario;
  @IColumna
  private String nombre;
  @IColumna
  private String apellido;
  @IColumna
  private String correo;
  @IColumna
  private String clave;
  @IColumna
  private String identificacion;
  @IColumna
  private String mod_usuario;
  @IColumna
  private Timestamp mod_fecha;

  public tbl_usuarios() {
  }

  public tbl_usuarios(String id_usuario, String nombre, String apellido, String correo, String clave, String identificacion, String mod_usuario, Timestamp mod_fecha) {
    this.id_usuario = id_usuario;
    this.nombre = nombre;
    this.apellido = apellido;
    this.correo = correo;
    this.clave = clave;
    this.identificacion = identificacion;
    this.mod_usuario = mod_usuario;
    this.mod_fecha = mod_fecha;
  }
  
  

  public String getId_usuario() {
    return id_usuario;
  }

  public void setId_usuario(String id_usuario) {
    this.id_usuario = id_usuario;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public String getApellido() {
    return apellido;
  }

  public void setApellido(String apellido) {
    this.apellido = apellido;
  }

  public String getCorreo() {
    return correo;
  }

  public void setCorreo(String correo) {
    this.correo = correo;
  }

  public String getClave() {
    return clave;
  }

  public void setClave(String clave) {
    this.clave = clave;
  }

  public String getIdentificacion() {
    return identificacion;
  }

  public void setIdentificacion(String identificacion) {
    this.identificacion = identificacion;
  }

  public String getMod_usuario() {
    return mod_usuario;
  }

  public void setMod_usuario(String mod_usuario) {
    this.mod_usuario = mod_usuario;
  }

  public Timestamp getMod_fecha() {
    return mod_fecha;
  }

  public void setMod_fecha(Timestamp mod_fecha) {
    this.mod_fecha = mod_fecha;
  }    
  
  @Override
  public String get_Tabla() {
    return this.getClass().getSimpleName();
  }
   
}
