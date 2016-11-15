/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.poli.ces2.init;

import co.edu.poli.ces2.impl.crud.Crud;
import java.io.File;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.spi.DirStateFactory;

/**
 *
 * @author sebastian
 */
public class InitC {

    public InitC() {
    }

    private final String catalog = null;

    public void InitClases(Connection con) {
        try {
            ResultSet tablas = con.getMetaData().getTables(catalog, "public", null, new String[]{"TABLE"});
            while (tablas.next()) {
                String TableName = tablas.getString("TABLE_NAME");
                InfoTabla tbl = new InfoTabla();
                tbl.setNombre(tablas.getString("TABLE_NAME"));
                String path = new File("src/co/edu/poli/ces2/generated/" + tablas.getString("TABLE_NAME") + ".java").getAbsolutePath();

                ResultSet campos = con.getMetaData().getColumns(catalog, null, tablas.getString("TABLE_NAME"), null);
                ResultSet rsCampos = con.createStatement().executeQuery("SELECT * FROM " + tablas.getString("TABLE_NAME") + " WHERE 1 = 2;");
                ResultSet primaries = con.getMetaData().getPrimaryKeys(catalog, null, tablas.getString("TABLE_NAME"));

                ResultSetMetaData rsmd = rsCampos.getMetaData();
                ArrayList<InfoColumna> colmObj = new ArrayList<>();

                int i = 1;

                while (campos.next()) {
                    InfoColumna objC = new InfoColumna();
                    objC.setNombre(campos.getString("COLUMN_NAME"));
                    objC.setAutomatica(campos.getString("IS_AUTOINCREMENT").contains("NO"));
                    objC.setAutomatica(campos.getString("IS_NULLABLE").contains("YES"));
                    objC.setColumnType(rsmd.getColumnClassName(i++));

                    while (primaries.next()) {
                        if (primaries.getString("COLUMN_NAME").equals(objC.getNombre())) {
                            objC.setPrimaryKey(true);
                        }
                    }

                    colmObj.add(objC);
                    objC = null;
                }

                tbl.setColumnas(colmObj);

                i = 1;

                writeClassFiles(InfoTabla.generateCodeClass(tbl), path);

            }

        } catch (SQLException ex) {
            Logger.getLogger(Crud.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void writeClassFiles(String lines, String path) {
        try {
            PrintWriter writer = new PrintWriter(path, "UTF-8");

            writer.println(lines);
            writer.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
