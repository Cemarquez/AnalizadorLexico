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
    final public static String ENTERO = "Entero";
    final public static String OPERADORADITIVO = "Operador aditivo";
    final public static String OPERADORARITMETICO = "Operador aritmetico";
    final public static String OPERADORRELACIONAL = "Operador Relacional";
    final public static String OPERADORLOGICO= "Operador lógico";
    final public static String OPERADORASIGNACION = "Operador de asignación";
    final public static String SIMBOLOABRIR = "Simbolo de abrir";
    final public static String SIMBOLOCERRAR = "Simbolo de cerrar";
    final public static String SIMBOLOTERMINAL = "Simbolo terminal";
    final public static String SEPARADORSENTENCIA = "Separador de sentencia";
    final public static String IDENTIFICADOR = "Identificador";
    final public static String NORECONOCIDO = "No reconocido";

    final public static String PALABRA_RESERVADA_BUCLE = "Bucle o ciclo";
    final public static String PALABRA_RESERVADA_DECISION = "Decision";
    final public static String PALABRA_RESERVADA_CLASE = "Clase";
    
    final public static String PALABRA_RESERVADA_ENTEROS = "Palabra reservada para enteros";
    final public static String PALABRA_RESERVADA_REALES = "Palabra reservada para reales";
    final public static String PALABRA_RESERVADA_CADENAS = "Palabra reservada para cadenas";
    final public static String PALABRA_RESERVADA_CARACTERES = "Palabra reservada para caracteres";
    
 
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
