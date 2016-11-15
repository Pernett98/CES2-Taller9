package co.edu.poli.ces2.generated;

import co.edu.poli.ces2.crud.IColumna;

import co.edu.poli.ces2.crud.IDto;

public class tbl_usuarios implements IDto {

@IColumna(pk = true, automatica = false, obligatoria = false)
private java.lang.String id_usuario;
@IColumna(pk = false, automatica = false, obligatoria = false)
private java.lang.String nombre;
@IColumna(pk = false, automatica = false, obligatoria = false)
private java.lang.String apellido;
@IColumna(pk = false, automatica = true, obligatoria = false)
private java.lang.String correo;
@IColumna(pk = false, automatica = false, obligatoria = false)
private java.lang.String clave;
@IColumna(pk = false, automatica = false, obligatoria = false)
private java.lang.String identificacion;
@IColumna(pk = false, automatica = false, obligatoria = false)
private java.lang.String mod_usuario;
@IColumna(pk = false, automatica = false, obligatoria = false)
private java.sql.Timestamp mod_fecha;

public java.lang.String getId_usuario() {
    return id_usuario;
  }

  public void setId_usuario(java.lang.String id_usuario) {
    this.id_usuario = id_usuario;
  }

public java.lang.String getNombre() {
    return nombre;
  }

  public void setNombre(java.lang.String nombre) {
    this.nombre = nombre;
  }

public java.lang.String getApellido() {
    return apellido;
  }

  public void setApellido(java.lang.String apellido) {
    this.apellido = apellido;
  }

public java.lang.String getCorreo() {
    return correo;
  }

  public void setCorreo(java.lang.String correo) {
    this.correo = correo;
  }

public java.lang.String getClave() {
    return clave;
  }

  public void setClave(java.lang.String clave) {
    this.clave = clave;
  }

public java.lang.String getIdentificacion() {
    return identificacion;
  }

  public void setIdentificacion(java.lang.String identificacion) {
    this.identificacion = identificacion;
  }

public java.lang.String getMod_usuario() {
    return mod_usuario;
  }

  public void setMod_usuario(java.lang.String mod_usuario) {
    this.mod_usuario = mod_usuario;
  }

public java.sql.Timestamp getMod_fecha() {
    return mod_fecha;
  }

  public void setMod_fecha(java.sql.Timestamp mod_fecha) {
    this.mod_fecha = mod_fecha;
  }


@Override
	public String get_Tabla() {
		return this.getClass().getSimpleName();
	}
}
