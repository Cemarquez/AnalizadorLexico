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

import java.util.ArrayList;


/**
 * Clase que modela un analizador léxico
 */

public class AnalizadorLexico {
    
    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Extrae los tokens de un código fuente dado
     * @param cod - código al cual se le van a extraer los tokens - !=null
     * @return vector con los tokens
     */
    public ArrayList extraerTokens( String cod )
    {
    	Token token;
    	ArrayList vectorTokens = new ArrayList();

	    // El primer token se extrae a partir de posición cero
    	int i = 0;

    	// Ciclo para extraer todos los tokens
    	while( i < cod.length() )
		{
	        // Extrae el token de la posición i
			token = extraerSiguienteToken( cod, i);
	        vectorTokens.add( token );
	        i = token.darIndiceSiguiente();
    	}
		return vectorTokens;
    }

    /**
     * Extrae el token de la cadena cod a partir de la posición i, basándose en el Autómata
     * @param cod - código al cual se le va a extraer un token - codigo!=null
     * @param i - posición a partir de la cual se va a extraer el token  - i>=0
     * @return token que se extrajo de la cadena
     */
    public Token extraerSiguienteToken( String cod, int i )
    {
    	Token token;

    	// Intenta extraer un operador de asignación
    	token = extraerOperadorAsignacion( cod, i);
    	if ( token != null )
    		return token;
    	
    	// Intenta extraer un operador aritmetico
		token = extraerOperadorAritmetico( cod, i);
		if ( token != null )
			return token;
		
		// Intenta extraer un operador relacional
		token = extraerOperadorRelacional(cod, i);
		if ( token != null )
			return token;
		
		//Intenta extaer un operador lógico
		token = extraerOperadorLogico(cod, i);
		if ( token != null )
			return token;
			      
		
		//Intenta extaer un simbolo de apertura
		token = extraerSimboloAbrir(cod, i);
		if ( token != null )
			return token;
				
		//Intenta extaer un simbolo de cierre
		token = extraerSimboloCerrar(cod, i);
		if ( token != null )
			return token;
		
		// Extrae un token no reconocido
		token = extraerNoReconocido( cod, i);
		return token;
    }


	
	/**
     * Intenta extraer un operador aritmetico de la cadena cod a partir de la posición i,
     * basándose en el Autómata
     * @param cod - código al cual se le va a intentar extraer el operador de asignación  - codigo!=null
     * @param i - posición a partir de la cual se va a intentar extraer el operador de asingación  - 0<=i<codigo.length()
     * @return el token operador asignación o NULL, si el token en la posición dada no es un operador de asignación. El Token se compone de 
     * el lexema, el tipo y la posición del siguiente lexema.
     */
	public Token extraerOperadorAritmetico(String cod, int i) {
		if(cod.charAt(i) == 'P' && (i+3) < cod.length()) {
			int j= i + 4;
			String lex = cod.substring(i,  j);
			if (cod.charAt(i+1) == 'L' && cod.charAt(i+2) == 'U' && cod.charAt(i+3) == 'S' ) {
				Token token = new Token( lex, Token.OPERADORARITMETICO, j );
				return token;
			} else if(cod.charAt(i+1) == 'R' && cod.charAt(i+2) == 'O' && cod.charAt(i+3) == 'D') {
				Token token = new Token( lex, Token.OPERADORARITMETICO, j );
				return token;
			}
		}else if(cod.charAt(i) == 'D'  && (i+1) < cod.length()) {
			if (cod.charAt(i+1) == 'I') {
				
				if( (i+3) < cod.length()) {
					if(cod.charAt(i+2) == 'F' && cod.charAt(i+3) == 'F' ){
						int j=i+4;
				        String lex =  cod.substring( i, j);			 
						Token token = new Token( lex, Token.OPERADORARITMETICO, j );
						return token;
					}
					
					
				}else if( (i+2) < cod.length()) {
					if(cod.charAt(i+2) == 'V') {
						int j=i+3;
					    String lex =  cod.substring( i, j);			 
						Token token = new Token( lex, Token.OPERADORARITMETICO, j );
						return token;
					}
				}
			}
		}
		
		
		return null;
	}
	
	
	  /**
     * Intenta extraer un operador relacional de la cadena cod a partir de la posición i,
     * basándose en el Autómata
     * @param cod - código al cual se le va a intentar extraer el operador de asignación  - codigo!=null
     * @param i - posición a partir de la cual se va a intentar extraer el operador de asingación  - 0<=i<codigo.length()
     * @return el token operador asignación o NULL, si el token en la posición dada no es un operador de asignación. El Token se compone de 
     * el lexema, el tipo y la posición del siguiente lexema.
     */
	public Token extraerOperadorRelacional(String cod, int i) {
		if(cod.charAt(i) == '↑') {
			
			if(cod.charAt(i+1) == '↔') {
				int j=i+2;
		        String lex =  cod.substring( i, j);			 
				Token token = new Token( lex, Token.OPERADORRELACIONAL, j );
				return token;
			}
			
			int j=i+1;
	        String lex =  cod.substring( i, j);			 
			Token token = new Token( lex, Token.OPERADORRELACIONAL, j );
			return token;
		
		}else if(cod.charAt(i) == '↓') {
			
			if(cod.charAt(i+1) == '↔') {
				int j=i+2;
		        String lex =  cod.substring( i, j);			 
				Token token = new Token( lex, Token.OPERADORRELACIONAL, j );
				return token;
			}
			
			int j=i+1;
	        String lex =  cod.substring( i, j);			 
			Token token = new Token( lex, Token.OPERADORRELACIONAL, j );
			return token;
			
		}else if(cod.charAt(i) == '↔' && (i+1) < cod.length()) {
			
			if(cod.charAt(i+1) == '↔') {
				int j=i+2;
		        String lex =  cod.substring( i, j);			 
				Token token = new Token( lex, Token.OPERADORRELACIONAL, j );
				return token;
			}
			
		}else if(cod.charAt(i) == '/') {
			if(cod.charAt(i+1) == '↔') {
				int j=i+2;
		        String lex =  cod.substring( i, j);			 
				Token token = new Token( lex, Token.OPERADORRELACIONAL, j );
				return token;
			}
		}
		
		return null;
	}

	 /**
     * Intenta extraer un operador lógico de la cadena cod a partir de la posición i,
     * basándose en el Autómata
     * @param cod - código al cual se le va a intentar extraer el operador de asignación  - codigo!=null
     * @param i - posición a partir de la cual se va a intentar extraer el operador de asingación  - 0<=i<codigo.length()
     * @return el token operador asignación o NULL, si el token en la posición dada no es un operador de asignación. El Token se compone de 
     * el lexema, el tipo y la posición del siguiente lexema.
     */
	public Token extraerOperadorLogico(String cod, int i) {
		if(cod.charAt(i) == '~') {
			int j=i+1;
	        String lex =  cod.substring( i, j);			 
			Token token = new Token( lex, Token.OPERADORLOGICO, j );
			return token;
			
		}else if(cod.charAt(i) == 'ö') {
			int j=i+1;
	        String lex =  cod.substring( i, j);			 
			Token token = new Token( lex, Token.OPERADORLOGICO, j );
			
			return token;
		}else if(cod.charAt(i) == 'ä') {
			int j=i+1;
	        String lex =  cod.substring( i, j);			 
			Token token = new Token( lex, Token.OPERADORLOGICO, j );
			return token;
			
		}
		
		return null;
	}
	
    /**
     * Intenta extraer un operador de asignación de la cadena cod a partir de la posición i,
     * basándose en el Autómata
     * @param cod - código al cual se le va a intentar extraer el operador de asignación  - codigo!=null
     * @param i - posición a partir de la cual se va a intentar extraer el operador de asingación  - 0<=i<codigo.length()
     * @return el token operador asignación o NULL, si el token en la posición dada no es un operador de asignación. El Token se compone de 
     * el lexema, el tipo y la posición del siguiente lexema.
     */
	public Token extraerOperadorAsignacion ( String cod, int i )
	{
		if(cod.charAt(i) == 'P' && (i+4) < cod.length()) {
			
			if (cod.charAt(i+1) == 'L' && cod.charAt(i+2) == 'U' && cod.charAt(i+3) == 'S' ) {
				if(cod.charAt(i+4) == '↔') {
					int j= i + 5;
					String lex = cod.substring(i,  j);
					Token token = new Token( lex, Token.OPERADORASIGNACION, j );
					return token;
					
				}
				
			} 
		}else if(cod.charAt(i) == 'D' && (i+4) < cod.length()) {
			if (cod.charAt(i+1) == 'I') {
				if(cod.charAt(i+2) == 'F' && cod.charAt(i+3) == 'F' ){
					if(cod.charAt(i+4) == '↔') {
						int j= i + 5;
						String lex = cod.substring(i,  j);
						Token token = new Token( lex, Token.OPERADORASIGNACION, j );
						return token;
						
					}
				}
			}
		}
		
		
		return null;
		
	}
	
	 /**
     * Intenta extraer un simbolo de apertura de la cadena cod a partir de la posición i,
     * basándose en el Autómata
     * @param cod - código al cual se le va a intentar extraer un identficador - codigo!=null
     * @param i - posición a partir de la cual se va a intentar extraer un identificador  - 0<=indice<codigo.length()
     * @return el token identificaror o NULL, si el token en la posición dada no es un identificador. El Token se compone de 
     * el lexema, el tipo y la posición del siguiente lexema.
     */
	public Token extraerSimboloAbrir ( String cod, int i )
	{
		if(cod.charAt(i) == '«') {
			int j= i + 1;
			String lex = cod.substring(i,  j);
			Token token = new Token( lex, Token.SIMBOLOABRIR, j );
			return token;
			
		}
		
		return null;
	}
	
	 /**
     * Intenta extraer un simbolo de cierre de la cadena cod a partir de la posición i,
     * basándose en el Autómata
     * @param cod - código al cual se le va a intentar extraer un identficador - codigo!=null
     * @param i - posición a partir de la cual se va a intentar extraer un identificador  - 0<=indice<codigo.length()
     * @return el token identificaror o NULL, si el token en la posición dada no es un identificador. El Token se compone de 
     * el lexema, el tipo y la posición del siguiente lexema.
     */
	public Token extraerSimboloCerrar ( String cod, int i )
	{
		if(cod.charAt(i) == '»') {
			int j= i + 1;
			String lex = cod.substring(i,  j);
			Token token = new Token( lex, Token.SIMBOLOCERRAR, j );
			return token;
			
		}
		
		return null;
	}
	
    /**
     * Intenta extraer un identificador de la cadena cod a partir de la posición i,
     * basándose en el Autómata
     * @param cod - código al cual se le va a intentar extraer un identficador - codigo!=null
     * @param i - posición a partir de la cual se va a intentar extraer un identificador  - 0<=indice<codigo.length()
     * @return el token identificaror o NULL, si el token en la posición dada no es un identificador. El Token se compone de 
     * el lexema, el tipo y la posición del siguiente lexema.
     */
	public Token extraerIdentificador ( String cod, int i)
	{
		if( cod.charAt(i)=='_' ){
			int j=i+1;
			while( j<cod.length() && esLetra(cod.charAt(j)) )		
			    	j++;
		    String lex =  cod.substring( i, j);			    
		    Token token = new Token( lex, Token.IDENTIFICADOR, j );
			return token;			
		}	
		return null;
	}

    /**
     * Extraer un lexema no reconocido de la cadena cod a partir de la posición i.
     * Antes de utilizar este método, debe haberse intentado todos los otros métodos para los otros tipos de token
     * @param cod - código al cual se le va a extraer el token no reconocido - codigo!=null
     * @param i - posición a partir de la cual se va a extraer el token no reconocido  - 0<=indice<codigo.length()
     * @return el token no reconocido. El Token se compone de lexema, el tipo y la posición del siguiente lexema.

     */
	public Token extraerNoReconocido ( String cod, int i)
	{
		String lexema =  cod.substring( i, i + 1);
		int j=i+1;
		Token token = new Token( lexema, Token.NORECONOCIDO, j );
		return token;
	}
	
	/**
     * Determina si un carácter es un dígito
     * @param caracter - Carácter que se va a analizar - caracter!=null,
     * @return true o false según el carácter sea un dígito o no
     */
	public boolean esDigito (char caracter )
	{
		return  caracter >= '0' && caracter <= '9';
	}

	/**
     * Determina si un carácter es una letra
     * @param caracter - Carácter que se va a analizar - caracter!=null,
     * @return true o false según el carácter sea una letra o no
     */
	public boolean esLetra (char caracter )
	{
		return  (caracter >= 'A' && caracter <= 'Z') || (caracter >= 'a' && caracter <= 'z');
	}

}
