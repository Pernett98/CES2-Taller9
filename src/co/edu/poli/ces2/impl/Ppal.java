/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.poli.ces2.impl;

import co.edu.poli.ces2.impl.crud.Crud;
import co.edu.poli.ces2.init.InitC;
import co.edu.poli.ces2.util.ConexionDB;
import static co.edu.poli.ces2.util.ConexionDB.getConexion;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author thomas
 */
public class Ppal {
    public static void main(String[] args) {
        Calendar cal = Calendar.getInstance();
        Crud myCrud = new Crud();
        InitC myInitC = new InitC();
        myInitC.InitClases(myCrud.get_Conexion());
//        myCrud.get_Tablas();
//        try {
//            myCrud.get_Conexion().setAutoCommit(true);
//        } catch (SQLException ex) {
//            Logger.getLogger(Ppal.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        myCrud.isConexionAutomatica();
//        tbl_usuarios tusariario = new tbl_usuarios("02", "ces2", "ces2", "ces2", "ces2", "cesw2", "ces2", Timestamp.valueOf("2016-10-20 23:59:26"));
//        
//        tbl_usuarios tolosita = tusariario;  
//        myCrud.save(tolosita);
//        
//        myCrud.set_Cantidad(0);
//        List<tbl_usuarios> lista1 = myCrud.select(tolosita, "SELECT * FROM tbl_usuarios" );
//        for (tbl_usuarios usuario : lista1) {
//            System.out.println(usuario.toString());
//            System.out.println("id: " + usuario.getId_usuario() + ", apellido: " + usuario.getApellido());
//        }
//        System.out.println(myCrud.get_Filas());
//        
//        
//        List<tbl_usuarios> lista2 = myCrud.select(tolosita);
//        for (tbl_usuarios usuario : lista2) {
//            System.out.println(usuario.toString());
//            System.out.println("id: " + usuario.getId_usuario() + ", apellido: " + usuario.getApellido());
//        }
        
//System.out.println(myCrud.toString());
        //myCrud.execute("DELETE FROM tbl_usuarios WHERE id_usuario = ?", "023");
//        tbl_criterios cri = new tbl_criterios();
//        cri.setId_criterio(654);
//        cri.setId_criterio_tipo(1);
//        cri.setNombre("Criterio ces2");
//        cri.setDescripcion("desc ces2");
//        cri.setActualiza_fecha(new Timestamp(cal.getTimeInMillis()));
//        cri.setActualiza_usuario(1);
//        System.out.println("Inserta "+cri.insertar());
    }
}
