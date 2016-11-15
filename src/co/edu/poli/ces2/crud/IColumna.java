package co.edu.poli.ces2.crud;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Contiene informacion adicional sobre la columna en la DB
 * @author thomas
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface IColumna {
    /**
     * Indica si la columna es clave primaria
     * @return 
     */
    boolean pk() default false;
    /**
     * Indica si la columna se genera automaticamente
     * @return 
     */
    boolean automatica() default false;
    
    /**
    *Indica si la columna es obligatoria
    *@return 
    **/
    boolean obligatoria () default false;
    

}
