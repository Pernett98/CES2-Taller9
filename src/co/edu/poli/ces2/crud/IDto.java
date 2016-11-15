package co.edu.poli.ces2.crud;

import java.io.Serializable;

/**
 * Data Transfer Object Para representar una tabla de la base de datos, para
 * indicar que un atributo representa una columna de la tabla se debe utilizar
 * la anotacion {@linkplain IColumna} 
 *
 * @author thomas
 */
public interface IDto extends Serializable {

    /**
     * Obtiene el nombre de la tabla que representa el DTO
     *
     * @return
     */
    public String get_Tabla();
}
