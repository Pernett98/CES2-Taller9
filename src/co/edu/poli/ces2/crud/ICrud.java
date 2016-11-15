package co.edu.poli.ces2.crud;

import java.sql.Connection;
import java.util.List;

/**
 *
 * @author Thomas
 */
public interface ICrud {

    /**
     * Ingresa la información del DTO utilizando reflection en la tabla
     * correspondiente en la base de datos
     *
     * @param dto objeto a insertar
     * @return número de registros insertados
     */
    public int insert(IDto dto);

    /**
     * Actualiza los campos de la tabla que no son primarios teniendo como
     * criterio (where) los campos primarios en el DTO que NO son nulos
     *
     * @param dto objeto a actualizar
     * @return numero de registros actualizados
     */
    public int update(IDto dto);

    /**
     * Elimina de la tabla los registros bajo los criterios especificados por
     * los valores de la clave primaria en el DTO
     *
     * @param dto Objeto a eliminar
     * @return numero de registros eliminados
     */
    public int delete(IDto dto);

    /**
     * Ejecuta una sentencia en la base de datos, esta sentencias deben ser de
     * tipo update, insert, delete
     *
     * @param sql sentencia a ejecutar, especificar up con ?
     * @param parametros los valores de los parametros de la sentencia
     * @return
     */
    public int execute(String sql, Object... parametros);

    /**
     * Inserta o actualiza la informacion del DTO
     *
     * @param dto Objeto a guardar
     * @return Numero de registros guardados
     * @see ICrud#insert(co.edu.poli.ces2.crud.IDto)
     * @see ICrud#update(co.edu.poli.ces2.crud.IDto)
     */
    public int save(IDto dto);

    /**
     * Ejecuta una consulta en la base de datos basada en los campos no nulos
     * del DTO y toma los valores del DTO para generar el WHERE. Se puede
     * parametrizar el comportamiento de la consulta a traves de los metodos:
     * <br> {@linkplain ICrud#set_Cantidad(int) }
     * <br> {@linkplain ICrud#set_Posicion(int) }
     *
     * @param <DTO> Clase que implementa implementa IDto
     * @param dto Objeto que tiene la información para realizar al consulta
     * @see #set_Cantidad(int)
     * @see #set_Posicion(int)
     * @return
     */
    public <DTO extends IDto> List<DTO> select(DTO dto);

    /**
     * Ejecuta una consulta en la base de datos se puede parametrizar el
     * comportamiento de la consulta a traves de los metodos:
     * <br> {@linkplain ICrud#set_Cantidad(int) }
     * <br> {@linkplain ICrud#set_Posicion(int) }
     *
     * @param <DTO> Clase que implementa implementa IDto
     * @param dto Objeto que tiene la información para realizar al consulta
     * @see #set_Cantidad(int)
     * @see #set_Posicion(int)
     * @param sql consulta a ejecutar en la base de datos
     * @param params valores de la consulta a ejecutar en la base de datos
     * @return Lista de objetos equivalente a los registros de la base de datos
     */
    public <DTO extends IDto> List<DTO> select(DTO dto, String sql, Object... params);

    /**
     * Cuando la conexion automática y es está nula, genera una conexion a la
     * base de datos y la retorna en un objeto Connection
     *
     * @return Conexion a la base de datos
     * @see ICrud#isConexionAutomatica()
     */
    public Connection get_Conexion();

    /**
     * Asigna la conexion a la base de datos, automaticamente la marca como una
     * conexion externa, con el fin de que no la cierren desde los métodos de
     * esta clase
     *
     * @param Conexion
     */
    public void set_Conexion(Connection Conexion);

    /**
     * Indica si la conexion se genera automaticamente o no. Cuando la conexion
     * NO es automatica, desde la clase que implemente ICrud no se puede cerrar
     * la conexion a la base de datos ya que esto es tarea de la clase que crea
     * la conexión.
     *
     * @return si la conexion es automática o no.
     */
    public boolean isConexionAutomatica();

    /**
     * Obtiene la Fila desde la cual se comienzan a traer registros en el Select
     *
     * @return Posicion desde la cual se deben traer los registros
     * @see ICrud#select(co.edu.poli.ces2.crud.IDto)
     * @see ICrud#select(co.edu.poli.ces2.crud.IDto, java.lang.String,
     * java.lang.Object...)
     */
    public int get_Posicion();

    /**
     * Asigna la Fila desde la cual se comienzan a traer registros en el Select
     *
     * @see #select() (int)
     * @see #select(java.lang.String, java.lang.Object[])
     * @param posicion
     * @see ICrud#select(co.edu.poli.ces2.crud.IDto)
     * @see ICrud#select(co.edu.poli.ces2.crud.IDto,
     * java.lang.String,java.lang.Object...)
     *
     */
    public void set_Posicion(int posicion);

    /**
     * Obtiene la cantidad de registros que se deben traer a partir de la
     * Posicion dada
     *
     * @see #get_Cantidad()
     * @see #get_Posicion()
     * @see #select(co.edu.poli.ces2.crud.IDto)
     * @see #select(co.edu.poli.ces2.crud.IDto, java.lang.String,
     * java.lang.Object...)
     * @return Cantidada de registro a traer
     */
    public int get_Cantidad();

    /**
     * Asigna la cantidad de registros que se deben traer a partir de la
     * Posicion dada
     *
     * @see #get_Cantidad()
     * @see #get_Posicion()
     * @see #select(co.edu.poli.ces2.crud.IDto)
     * @see #select(co.edu.poli.ces2.crud.IDto, java.lang.String,
     * java.lang.Object...)
     * @param cantidad, entero indicando la cantidad de registros a traer
     */
    public void set_Cantidad(int cantidad);

    /**
     * Obtiene el número de filas que trae la consulta. <br/> Solo trae
     * información cuando los métodos {@linkplain ICrud#set_Cantidad(int) } y {@linkplain ICrud#set_Posicion(int)
     * } devuelven valores mayores a cero
     *
     * @return Un número entero con la cantidad total de registros que trae la
     * consulta
     * @see #get_Cantidad()
     * @see #get_Posicion()
     * @see #select(co.edu.poli.ces2.crud.IDto)
     * @see #select(co.edu.poli.ces2.crud.IDto, java.lang.String,
     * java.lang.Object...)
     */
    public int get_Filas();
}
