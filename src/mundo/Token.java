/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id$
 * Universidad del Quindío (Armenia - Colombia)
 * Programa de Ingeniería de Sistemas y Computación
 *
 * Asignatura: Teoría de Lenguajes Formales
 * Ejercicio: AnalizadorLexico
 * Diseño original por: Leonardo A. Hernández R. - Agosto 2008 - Marzo 2009
 * Modificado y usado por: Claudia E. Quiceno R- Julio 2021
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package mundo;

/**
 * Clase que modela un token
 */

public class Token {
    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------
    /**
     * Constantes para modelar los posibles tipos de token que se van a analizar
     */
    final public static String OPERADOR_ARITMETICO = "Operador aritmetico";
    final public static String OPERADOR_RELACIONAL = "Operador relacional";
    final public static String OPERADOR_LOGICO= "Operador logico";
    final public static String OPERADOR_ASIGNACION = "Operador de asignación";
    final public static String SIMBOLO_ABRIR = "Simbolo de abrir";
    final public static String SIMBOLO_CERRAR = "Simbolo de cerrar";
    final public static String SIMBOLO_TERMINAL = "Simbolo terminal";
    final public static String SEPARADOR_SENTENCIA = "Separador de sentencia";

    final public static String PALABRA_RESERVADA_BUCLE_CICLO = "Palabra reservada para bucle o ciclo";
    final public static String PALABRA_RESERVADA_DECISION = "Palabra reservada para decision";
    final public static String PALABRA_RESERVADA_CLASE = "Palabra reservada para la clase";
    
    final public static String IDENTIFICADOR_NOMBRE_VARIABLE = "Identificador para los nombres de las variables";
    final public static String IDENTIFICADOR_NOMBRE_METODO = "Identificador para los nombres de los metodos";
    final public static String IDENTIFICADOR_NOMBRE_CLASE = "Identificador para los nombres de las clases";
    
    final public static String PALABRA_RESERVADA_ENTEROS = "Palabra reservada para enteros";
    final public static String PALABRA_RESERVADA_REALES = "Palabra reservada para reales";
    final public static String PALABRA_RESERVADA_CADENAS = "Palabra reservada para cadenas de caracteres";
    final public static String PALABRA_RESERVADA_CARACTERES = "Palabra reservada para caracteres";
    
    final public static String PALABRA_RESERVADA_ESTRUCTURA_CONDICIONAL_MULTIPLE = "Palabra reservada para la estructura condicional multiple";
    
    final public static String NO_RECONOCIDO = "No reconocido";
    
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------
    /**
     * Lexema
     */
    private String lexema;

    /**
     * tipo
     */
    private String tipo;

    /**
     * posición del siguiente lexema
     */
    private int indiceSiguiente;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------
    /**
     * Constructor de un token
     * @param elLexema - cadena - laCadena != null
     * @param elTipo - tipo del token - elTipo != null
     * @param elIndiceSiguiente - posición del siguiente token - laPosicionSiguiente > 0
     */
    public Token( String elLexema, String elTipo, int elIndiceSiguiente )
    {
        lexema = elLexema;
        tipo = elTipo;
        indiceSiguiente = elIndiceSiguiente;
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Entrega la información del token
     * @return Descripción del token
     */
    public String darDescripcion( )
    {
        return "Token: " + lexema + "     Tipo: " + tipo + "     Índice del siguiente: " + indiceSiguiente;
    }

    /**
     * Método que retorna el lexema del token
     * @return el lexema del token
     */
    public String darLexema( )
    {
        return lexema;
    }

    /**
     * Método que retorna la posición del siguiente lexema
     * @return posición del siguiente token
     */
    public int darIndiceSiguiente( )
    {
        return indiceSiguiente;
    }

    /**
     * Método que retorna el tipo del token
     * @return el tipo del token
     */
    public String darTipo( )
    {
        return tipo;
    }
}
