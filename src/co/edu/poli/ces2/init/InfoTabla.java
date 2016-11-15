/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.poli.ces2.init;

import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author sebastian
 */
public class InfoTabla {

    private String nombre;
    private ArrayList<InfoColumna> columnas;

    public InfoTabla() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public ArrayList<InfoColumna> getColumnas() {
        return columnas;
    }

    public void setColumnas(ArrayList<InfoColumna> columnas) {
        this.columnas = columnas;
    }

    public static String generateCodeClass(InfoTabla tbl) {
        String head = generateHead(tbl.nombre);

        String body = generateBody(tbl.getColumnas());

        String end = generateFinal();

        return head + body + end;
    }

    private static String generateHead(String NombreTabla) {
        return "package co.edu.poli.ces2.generated;\n\n"
            + "import co.edu.poli.ces2.crud.IColumna;\n\n"
            + "import co.edu.poli.ces2.crud.IDto;\n\n"
            + "public class " + NombreTabla.toLowerCase() + " implements IDto {\n\n";
    }

    private static String generateFinal() {
        return "@Override\n"
            + "\tpublic String get_Tabla() {\n"
            + "\t\treturn this.getClass().getSimpleName();\n"
            + "\t}\n}";
    }

    private static String generateBody(ArrayList<InfoColumna> cols) {
        String onlyString = "";
        String headers = "";
        String gettersAndSetters = "";

        for (int i = 0; i < cols.size(); i++) {
            ArrayList<String> info = InfoColumna.generateInfo(cols.get(i));
            headers += info.get(0);
            gettersAndSetters += info.get(1);
        }

        headers += "\n";
        gettersAndSetters += "\n";

        onlyString = headers + gettersAndSetters;

        return onlyString;
    }

}
