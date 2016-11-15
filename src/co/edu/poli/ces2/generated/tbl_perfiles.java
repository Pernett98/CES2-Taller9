package co.edu.poli.ces2.generated;

import co.edu.poli.ces2.crud.IColumna;

import co.edu.poli.ces2.crud.IDto;

public class tbl_perfiles implements IDto {

@IColumna(pk = true, automatica = false, obligatoria = false)
private java.math.BigInteger id_perfil;
@IColumna(pk = false, automatica = false, obligatoria = false)
private java.lang.String nombre;
@IColumna(pk = false, automatica = true, obligatoria = false)
private java.lang.String descripcion;
@IColumna(pk = false, automatica = false, obligatoria = false)
private java.lang.String mod_usuario;
@IColumna(pk = false, automatica = false, obligatoria = false)
private java.sql.Timestamp mod_fecha;

public java.math.BigInteger getId_perfil() {
    return id_perfil;
  }

  public void setId_perfil(java.math.BigInteger id_perfil) {
    this.id_perfil = id_perfil;
  }

public java.lang.String getNombre() {
    return nombre;
  }

  public void setNombre(java.lang.String nombre) {
    this.nombre = nombre;
  }

public java.lang.String getDescripcion() {
    return descripcion;
  }

  public void setDescripcion(java.lang.String descripcion) {
    this.descripcion = descripcion;
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
