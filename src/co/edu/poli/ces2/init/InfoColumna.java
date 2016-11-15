/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.poli.ces2.init;

import java.util.ArrayList;

/**
 *
 * @author sebastian
 */
public class InfoColumna {

    private String nombre;
    private boolean obligatoria;
    private boolean automatica;
    private boolean primaryKey;
    private String columnType;

    public InfoColumna() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean isObligatoria() {
        return obligatoria;
    }

    public void setObligatoria(boolean obligatoria) {
        this.obligatoria = obligatoria;
    }

    public boolean isAutomatica() {
        return automatica;
    }

    public void setAutomatica(boolean automatica) {
        this.automatica = automatica;
    }

    public boolean isPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(boolean primaryKey) {
        this.primaryKey = primaryKey;
    }

    public String getColumnType() {
        return columnType;
    }

    public void setColumnType(String columnType) {
        this.columnType = columnType;
    }

    public static ArrayList<String> generateInfo(InfoColumna col) {
        ArrayList<String> info = new ArrayList<String>();

        String head = "@IColumna(pk = " + col.isPrimaryKey() + ", automatica = " + col.isAutomatica() + ", obligatoria = " + col.isObligatoria() + ")\n"
            + "private " + col.getColumnType() + " " + col.nombre + ";\n";

        String body = "public " + col.getColumnType() + " get" + camelCase(col.getNombre()) + "() {\n"
            + "    return " + col.nombre + ";\n"
            + "  }\n\n"
            + "  public void set" + camelCase(col.getNombre()) + "(" + col.getColumnType() + " " + col.nombre + ") {\n"
            + "    this." + col.nombre + " = " + col.nombre + ";\n"
            + "  }\n\n";

        info.add(head);
        info.add(body);

        return info;
    }

    public static String camelCase(String str) {
        return str.substring(0, 1).toUpperCase() + str.substring(1).toLowerCase();
    }
;

}
