/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id$
 * Universidad del Quind�o (Armenia - Colombia)
 * Programa de Ingenier�a de Sistemas y Computaci�n
 *
 * Asignatura: Teor�a de Lenguajes Formales
 * Ejercicio: AnalizadorLexico
 * Dise�o original por: Leonardo A. Hern�ndez R. - Agosto 2008 - Marzo 2009
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
    final public static String OPERADOR_ASIGNACION = "Operador de asignaci�n";
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
     * posici�n del siguiente lexema
     */
    private int indiceSiguiente;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------
    /**
     * Constructor de un token
     * @param elLexema - cadena - laCadena != null
     * @param elTipo - tipo del token - elTipo != null
     * @param elIndiceSiguiente - posici�n del siguiente token - laPosicionSiguiente > 0
     */
    public Token( String elLexema, String elTipo, int elIndiceSiguiente )
    {
        lexema = elLexema;
        tipo = elTipo;
        indiceSiguiente = elIndiceSiguiente;
    }

    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------

    /**
     * Entrega la informaci�n del token
     * @return Descripci�n del token
     */
    public String darDescripcion( )
    {
        return "Token: " + lexema + "     Tipo: " + tipo + "     �ndice del siguiente: " + indiceSiguiente;
    }

    /**
     * M�todo que retorna el lexema del token
     * @return el lexema del token
     */
    public String darLexema( )
    {
        return lexema;
    }

    /**
     * M�todo que retorna la posici�n del siguiente lexema
     * @return posici�n del siguiente token
     */
    public int darIndiceSiguiente( )
    {
        return indiceSiguiente;
    }

    /**
     * M�todo que retorna el tipo del token
     * @return el tipo del token
     */
    public String darTipo( )
    {
        return tipo;
    }
}
