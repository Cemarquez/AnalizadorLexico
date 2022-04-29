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
	public ArrayList extraerTokens( String cod ) {
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
	public Token extraerSiguienteToken( String cod, int i ) {
		Token token;

		//Intenta extraer un operador de asignación
				token = extraerOperadorAsignacion( cod, i);
				if ( token != null )
					return token;
		
		
		//Intenta extraer un operador aritmetico
		token = extraerOperadorAritmetico( cod, i);
		if ( token != null )
			return token;

		//Intenta extraer un operador relacional
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

		//Intenta extaer un simbolo terminal
		token = extraerSimboloTerminal(cod, i);
		if ( token != null )
			return token;

		//Intenta extaer un separador de sentencia
		token = extraerSeparadorSentencia(cod, i);
		if ( token != null )
			return token;

		//Intenta extaer una palabra reservada para diferentes usos para bucle o ciclo
		token = extraerPalabraReservadaDiferentesUsosBucleCiclo(cod, i);
		if ( token != null )
			return token;

		//Intenta extaer una palabra reservada para diferentes usos para decisión
		token = extraerPalabraReservadaDiferentesUsosDecision(cod, i);
		if ( token != null )
			return token;
		
		//Intenta extaer una palabra reservada para diferentes usos para la clase
		token = extraerPalabraReservadaDiferentesUsosClase(cod, i);
		if ( token != null )
			return token;
		
		//Intenta extraer una palabra reservada de identificador para nombres de variables
		token = extraerIdentificadorNombreVariable(cod, i);
		if ( token != null )
			return token;

		//Intenta extraer una palabra reservada de identificador para nombres de métodos
		token = extraerIdentificadorNombreMetodo(cod, i);
		if ( token != null )
			return token;

		//Intenta extraer una palabra reservada de identificador para nombres de clases
		token = extraerIdentificadorNombreClase(cod, i);
		if ( token != null )
			return token;

		//Intenta extaer una palabra reservada para tipo de dato entero
		token = extraerPalabraReservadaTipoDatoEntero(cod, i);
		if ( token != null )
			return token;

		//Intenta extaer una palabra reservada para tipo de dato real
		token = extraerPalabraReservadaTipoDatoReal(cod, i);
		if ( token != null )
			return token;

		//Intenta extaer una palabra reservada para tipo de dato cadena
		token = extraerPalabraReservadaTipoDatoCadena(cod, i);
		if ( token != null )
			return token;

		//Intenta extaer una palabra reservada para tipo de dato caracter
		token = extraerPalabraReservadaTipoDatoCaracter(cod, i);
		if ( token != null )
			return token;
		
		//Intenta extraer una palabra reservada para estructura condicional multiple
		token = extraerPalabraReservadaEstructuraCondicionalMultiple(cod, i);
		if ( token != null )
			return token;

		//Extrae un token no reconocido
		token = extraerNoReconocido( cod, i);
		return token;
	}
	
	//---------------------------OPERADORES-------------------------------

	/**
	 * Intenta extraer un operador aritmetico de la cadena cod a partir de la posición i,
	 * basándose en el Autómata
	 * @param cod - código al cual se le va a intentar extraer el operador aritmetico  - codigo!=null
	 * @param i - posición a partir de la cual se va a intentar extraer el operador aritmetico  - 0<=i<codigo.length()
	 * @return el token operador aritmetico o NULL, si el token en la posición dada no es un operador aritmetico. El Token se compone de 
	 * el lexema, el tipo y la posición del siguiente lexema.
	 */
	public Token extraerOperadorAritmetico(String cod, int i) {
		if(cod.charAt(i) == 'P' && (i+3) < cod.length()) {
			int j= i + 4;
			String lex = cod.substring(i,  j);
			if(cod.charAt(i+1) == 'L' ) {
				if(cod.charAt(i+2) == 'U') {
					if(cod.charAt(i+3) == 'S') {
						Token token = new Token( lex, Token.OPERADOR_ARITMETICO, j );
						return token;
					}
				}
				
			}else if(cod.charAt(i+1) == 'R' ) {
				if(cod.charAt(i+2) == 'O') {
					if(cod.charAt(i+3) == 'D') {
						Token token = new Token( lex, Token.OPERADOR_ARITMETICO, j );
						return token;
					}
				}
				
			}
		}else if(cod.charAt(i) == 'D' && (i+1) < cod.length()) {
			if(cod.charAt(i+1) == 'I') {
				if((i+3) < cod.length()) {
					if(cod.charAt(i+2) == 'F' ) {
						if(cod.charAt(i+3) == 'F') {
							int j=i+4;
							String lex =  cod.substring( i, j);			 
							Token token = new Token( lex, Token.OPERADOR_ARITMETICO, j );
							return token;
						}
						
					}
				}else if((i+2) < cod.length()) {
					if(cod.charAt(i+2) == 'V') {
						int j=i+3;
						String lex = cod.substring(i, j);			 
						Token token = new Token( lex, Token.OPERADOR_ARITMETICO, j );
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
	 * @param cod - código al cual se le va a intentar extraer el operador relacional  - codigo!=null
	 * @param i - posición a partir de la cual se va a intentar extraer el operador relacional  - 0<=i<codigo.length()
	 * @return el token operador relacional o NULL, si el token en la posición dada no es un operador relacional. El Token se compone de 
	 * el lexema, el tipo y la posición del siguiente lexema.
	 */
	public Token extraerOperadorRelacional(String cod, int i) {
		if(cod.charAt(i) == '↑') {
			if(cod.charAt(i+1) == '↔') {
				int j=i+2;
				String lex =  cod.substring( i, j);			 
				Token token = new Token( lex, Token.OPERADOR_RELACIONAL, j );
				return token;
			}
			int j=i+1;
			String lex =  cod.substring( i, j);			 
			Token token = new Token( lex, Token.OPERADOR_RELACIONAL, j );
			return token;
		}else if(cod.charAt(i) == '↓') {
			if(cod.charAt(i+1) == '↔') {
				int j=i+2;
				String lex =  cod.substring( i, j);			 
				Token token = new Token( lex, Token.OPERADOR_RELACIONAL, j );
				return token;
			}
			int j=i+1;
			String lex =  cod.substring( i, j);			 
			Token token = new Token( lex, Token.OPERADOR_RELACIONAL, j );
			return token;
		}else if(cod.charAt(i) == '↔' && (i+1) < cod.length()) {
			if(cod.charAt(i+1) == '↔') {
				int j=i+2;
				String lex =  cod.substring( i, j);			 
				Token token = new Token( lex, Token.OPERADOR_RELACIONAL, j );
				return token;
			}
		}else if(cod.charAt(i) == '/') {
			if(cod.charAt(i+1) == '↔') {
				int j=i+2;
				String lex =  cod.substring( i, j);			 
				Token token = new Token( lex, Token.OPERADOR_RELACIONAL, j );
				return token;
			}
		}
		return null;
	}

	/**
	 * Intenta extraer un operador lógico de la cadena cod a partir de la posición i,
	 * basándose en el Autómata
	 * @param cod - código al cual se le va a intentar extraer el operador logico  - codigo!=null
	 * @param i - posición a partir de la cual se va a intentar extraer el operador logico  - 0<=i<codigo.length()
	 * @return el token operador logico o NULL, si el token en la posición dada no es un operador logico. El Token se compone de 
	 * el lexema, el tipo y la posición del siguiente lexema.
	 */
	public Token extraerOperadorLogico(String cod, int i) {
		if(cod.charAt(i) == '~') {
			if(verificarPalabraReservadaEstructuraCondicionalMultiple(cod, i)) {
				return extraerPalabraReservadaEstructuraCondicionalMultiple(cod, i);
			}else {
				int j=i+1;
				String lex = cod.substring( i, j);			 
				Token token = new Token( lex, Token.OPERADOR_LOGICO, j );
				return token;
			}
		}else if(cod.charAt(i) == 'ö') {
			int j=i+1;
			String lex = cod.substring( i, j);			 
			Token token = new Token( lex, Token.OPERADOR_LOGICO, j );
			return token;
		}else if(cod.charAt(i) == 'ä') {
			int j=i+1;
			String lex = cod.substring( i, j);			 
			Token token = new Token( lex, Token.OPERADOR_LOGICO, j );
			return token;	
		}
		return null;
	}

	/**
	 * Intenta extraer un operador de asignación de la cadena cod a partir de la posición i,
	 * basándose en el Autómata
	 * @param cod - código al cual se le va a intentar extraer el operador de asignación  - codigo!=null
	 * @param i - posición a partir de la cual se va a intentar extraer el operador de asignación  - 0<=i<codigo.length()
	 * @return el token operador asignación o NULL, si el token en la posición dada no es un operador de asignación. El Token se compone de 
	 * el lexema, el tipo y la posición del siguiente lexema.
	 */
	public Token extraerOperadorAsignacion(String cod, int i) {
		if(cod.charAt(i) == '↔') {
			int j= i + 1;
			String lex = cod.substring(i,  j);
			Token token = new Token( lex, Token.OPERADOR_ASIGNACION, j );
			return token;
		}else if(cod.charAt(i) == 'P' && (i+4) < cod.length()) {
			if (cod.charAt(i+1) == 'L' ) {
				if(cod.charAt(i+2) == 'U') {
					if(cod.charAt(i+3) == 'S' ) {
						if(cod.charAt(i+4) == '↔') {
							int j= i + 5;
							String lex = cod.substring(i,  j);
							Token token = new Token( lex, Token.OPERADOR_ASIGNACION, j );
							return token;
						}
					}
				}
				
			} 
		}else if(cod.charAt(i) == 'D' && (i+4) < cod.length()) {
			if(cod.charAt(i+1) == 'I') {
				if(cod.charAt(i+2) == 'F' ){
					if(cod.charAt(i+3) == 'F' )
					if(cod.charAt(i+4) == '↔') {
						int j= i + 5;
						String lex = cod.substring(i,  j);
						Token token = new Token( lex, Token.OPERADOR_ASIGNACION, j );
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
	 * @param cod - código al cual se le va a intentar extraer un simbolo de apertura - codigo!=null
	 * @param i - posición a partir de la cual se va a intentar extraer un simbolo de apertura  - 0<=indice<codigo.length()
	 * @return el token simbolo de apertura o NULL, si el token en la posición dada no es un simbolo de apertura. El Token se compone de 
	 * el lexema, el tipo y la posición del siguiente lexema.
	 */
	public Token extraerSimboloAbrir(String cod, int i) {
		if(cod.charAt(i) == '«') {
			int j= i + 1;
			String lex = cod.substring(i,  j);
			Token token = new Token( lex, Token.SIMBOLO_ABRIR, j );
			return token;
		}
		return null;
	}

	/**
	 * Intenta extraer un simbolo de cierre de la cadena cod a partir de la posición i,
	 * basándose en el Autómata
	 * @param cod - código al cual se le va a intentar extraer un simbolo de cierre - codigo!=null
	 * @param i - posición a partir de la cual se va a intentar extraer un simbolo de cierre  - 0<=indice<codigo.length()
	 * @return el token simbolo de cierre o NULL, si el token en la posición dada no es un simbolo de cierre. El Token se compone de 
	 * el lexema, el tipo y la posición del siguiente lexema.
	 */
	public Token extraerSimboloCerrar(String cod, int i) {
		if(cod.charAt(i) == '»') {
			int j= i + 1;
			String lex = cod.substring(i,  j);
			Token token = new Token( lex, Token.SIMBOLO_CERRAR, j );
			return token;
		}
		return null;
	}

	/**
	 * Intenta extraer un simbolo terminal de la cadena cod a partir de la posición i,
	 * basándose en el Autómata
	 * @param cod - código al cual se le va a intentar extraer un simbolo terminal - codigo!=null
	 * @param i - posición a partir de la cual se va a intentar extraer un simbolo terminal  - 0<=indice<codigo.length()
	 * @return el token simbolo terminal o NULL, si el token en la posición dada no es un simbolo terminal. El Token se compone de 
	 * el lexema, el tipo y la posición del siguiente lexema.
	 */
	public Token extraerSimboloTerminal(String cod, int i) {
		if( cod.charAt(i)=='¬' ) {
			int j=i+1;
			String lex =  cod.substring( i, j);			    
			Token token = new Token( lex, Token.SIMBOLO_TERMINAL, j );
			return token;			
		}	
		return null;
	}

	/**
	 * Intenta extraer un separador de sentencia de la cadena cod a partir de la posición i,
	 * basándose en el Autómata
	 * @param cod - código al cual se le va a intentar extraer un separador de sentencia - codigo!=null
	 * @param i - posición a partir de la cual se va a intentar extraer un separador de sentencia  - 0<=indice<codigo.length()
	 * @return el token separador de sentencia o NULL, si el token en la posición dada no es un separador de sentencia. El Token se compone de 
	 * el lexema, el tipo y la posición del siguiente lexema.
	 */
	public Token extraerSeparadorSentencia(String cod, int i) {
		if(cod.charAt(i)=='|') {
			int j=i+1;
			String lex =  cod.substring( i, j);			    
			Token token = new Token( lex, Token.SEPARADOR_SENTENCIA, j );
			return token;			
		}	
		return null;
	}

	//---------------------------PALABRAS RESERVADAS: DIFERENTES USOS--------------------------------
	
	/**
	 * Intenta extraer una palabra reservada para diferentes usos de bucle o ciclo de la cadena cod a partir de la posición i,
	 * basándose en el Autómata
	 * @param cod - código al cual se le va a intentar extraer la palabra reservada para diferentes usos - codigo!=null
	 * @param i - posición a partir de la cual se va a intentar extraer la palabra reservada para diferentes usos - 0<=i<codigo.length()
	 * @return el token palabra reservada diferentes usos o NULL, si el token en la posición dada no es una palabra reservada de diferentes usos. El Token se compone de 
	 * el lexema, el tipo y la posición del siguiente lexema.
	 */
	public Token extraerPalabraReservadaDiferentesUsosBucleCiclo(String cod, int i) {
		if(cod.charAt(i) == 'C' && (i+5) < cod.length()) {
			if (cod.charAt(i+1) == 'i' && cod.charAt(i+2) == 'c' && cod.charAt(i+3) == 'l' && cod.charAt(i+4) == 'o' && cod.charAt(i+5) == 'n') {
				int j= i + 6;
				String lex = cod.substring(i,  j);
				Token token = new Token( lex, Token.PALABRA_RESERVADA_BUCLE_CICLO, j );
				return token;
			}
		}
		return null;
	}
	
	/**
	 * Intenta extraer una palabra reservada para diferentes usos para decisión de la cadena cod a partir de la posición i,
	 * basándose en el Autómata
	 * @param cod - código al cual se le va a intentar extraer la palabra reservada para diferentes usos - codigo!=null
	 * @param i - posición a partir de la cual se va a intentar extraer la palabra reservada para diferentes usos - 0<=i<codigo.length()
	 * @return el token palabra reservada diferentes usos o NULL, si el token en la posición dada no es una palabra reservada de diferentes usos. El Token se compone de 
	 * el lexema, el tipo y la posición del siguiente lexema.
	 */
	public Token extraerPalabraReservadaDiferentesUsosDecision(String cod, int i) {
		if(cod.charAt(i) == 'W' && (i+5) < cod.length()) {
			if (cod.charAt(i+1) == 'h' && cod.charAt(i+2) == 'a' && cod.charAt(i+3) == 't' && cod.charAt(i+4) == 'i' && cod.charAt(i+5) == 'f') {
				int j= i + 6;
				String lex = cod.substring(i,  j);
				Token token = new Token( lex, Token.PALABRA_RESERVADA_DECISION, j );
				return token;
			}
		}
		return null;
	}
	
	/**
	 * Intenta extraer una palabra reservada para diferentes usos para la clase de la cadena cod a partir de la posición i,
	 * basándose en el Autómata
	 * @param cod - código al cual se le va a intentar extraer la palabra reservada para diferentes usos - codigo!=null
	 * @param i - posición a partir de la cual se va a intentar extraer la palabra reservada para diferentes usos - 0<=i<codigo.length()
	 * @return el token palabra reservada diferentes usos o NULL, si el token en la posición dada no es una palabra reservada de diferentes usos. El Token se compone de 
	 * el lexema, el tipo y la posición del siguiente lexema.
	 */
	public Token extraerPalabraReservadaDiferentesUsosClase(String cod, int i) {
		if(cod.charAt(i) == 'K' && (i+4) < cod.length()) {
			if (cod.charAt(i+1) == 'l' && cod.charAt(i+2) == 'a' && cod.charAt(i+3) == 's' && cod.charAt(i+4) == 's') {
				int j= i + 5;
				String lex = cod.substring(i,  j);
				Token token = new Token( lex, Token.PALABRA_RESERVADA_CLASE, j );
				return token;
			}
		}
		return null;
	}
	
	//---------------------------PALABRAS RESERVADAS: IDENTIFICADORES--------------------------------
	
	/**
	 * Intenta extraer un identificador para nombres de variables de la cadena cod a partir de la posición i,
	 * basándose en el Autómata
	 * @param cod - código al cual se le va a intentar extraer el identificador - codigo!=null
	 * @param i - posición a partir de la cual se va a intentar extraer el identificador  - 0<=i<codigo.length()
	 * @return el token identificador nombre variable o NULL, si el token en la posición dada no es un identificador de nombre de variables. El Token se compone de 
	 * el lexema, el tipo y la posición del siguiente lexema.
	 */
	public Token extraerIdentificadorNombreVariable(String cod, int i) {
		int j;
		String lex;
		if(cod.charAt(i) == '[') {
			j = i + 1;
			if(j < cod.length() && esLetraMayuscula(cod.charAt(j))) {
				j++;
				while(j < cod.length() && esLetra(cod.charAt(j)) || esDigito(cod.charAt(j))) {
					j++;
				}
				if(j < cod.length() && cod.charAt(j) == ']') {
					j++;
					lex = cod.substring(i, j);
					Token token = new Token(lex, Token.IDENTIFICADOR_NOMBRE_VARIABLE, j);
					return token;
				}
			}
		}
		return null;
	}

	/**
	 * Intenta extraer un identificador para nombres de métodos de la cadena cod a partir de la posición i,
	 * basándose en el Autómata
	 * @param cod - código al cual se le va a intentar extraer el identificador - codigo!=null
	 * @param i - posición a partir de la cual se va a intentar extraer el identificador  - 0<=i<codigo.length()
	 * @return el token identificador nombre método o NULL, si el token en la posición dada no es un identificador de nombre de métodos. El Token se compone de 
	 * el lexema, el tipo y la posición del siguiente lexema.
	 */
	public Token extraerIdentificadorNombreMetodo(String cod, int i) {
		int j;
		String lex;
		if(cod.charAt(i) == '#') {
			j = i + 1;
			if(j < cod.length() && esLetraMinuscula(cod.charAt(j))) {
				j++;
				while(j < cod.length() && esLetra(cod.charAt(j))) {
					j++;
				}
				if(j < cod.length() && cod.charAt(j) == '&') {
					j++;
					while(j < cod.length() && esLetra(cod.charAt(j)) || esDigito(cod.charAt(j))) {
						j++;
					}
					if(j < cod.length() && cod.charAt(j) == '#') {
						j++;
						lex = cod.substring(i, j);
						Token token = new Token(lex, Token.IDENTIFICADOR_NOMBRE_METODO, j);
						return token;
					}
				}
			}
		}
		return null;
	}

	/**
	 * Intenta extraer un identificador para nombres de clases de la cadena cod a partir de la posición i,
	 * basándose en el Autómata
	 * @param cod - código al cual se le va a intentar extraer el identificador - codigo!=null
	 * @param i - posición a partir de la cual se va a intentar extraer el identificador  - 0<=i<codigo.length()
	 * @return el token identificador nombre clase o NULL, si el token en la posición dada no es un identificador de nombre de clases. El Token se compone de 
	 * el lexema, el tipo y la posición del siguiente lexema.
	 */
	public Token extraerIdentificadorNombreClase(String cod, int i) {
		int j;
		String lex;
		if(cod.charAt(i) == '1') {
			j = i + 1;
			if(j < cod.length() && cod.charAt(j) == ':') {
				j++;
				if(j < cod.length() && esDigito(cod.charAt(j))) {
					j++;
					while(j < cod.length() && esDigito(cod.charAt(j))) {
						j++;
					}
					if(j < cod.length() && esLetra(cod.charAt(j))) {
						j++;
						while(j < cod.length() && esLetra(cod.charAt(j))) {
							j++;
						}
						if(j < cod.length() && cod.charAt(j) == '_') {
							j++;
							lex = cod.substring(i, j);
							Token token = new Token(lex, Token.IDENTIFICADOR_NOMBRE_CLASE, j);
							return token;
						}
					}
				}
			}
		}
		return null;
	}
	
	//---------------------------VALOR DE ASIGNACIÓN--------------------------------
	
	

	//---------------------------PALABRAS RESERVADAS: TIPO DE DATO--------------------------------
	
	/**
	 * Intenta extraer una palabra reservada para tipo de dato entero de la cadena cod a partir de la posición i,
	 * basándose en el Autómata
	 * @param cod - código al cual se le va a intentar extraer la palabra reservada - codigo!=null
	 * @param i - posición a partir de la cual se va a intentar extraer la palabra reservada - 0<=i<codigo.length()
	 * @return el token palabra reservada tipo de dato o NULL, si el token en la posición dada no es una palabra reservada de tipo de dato. El Token se compone de 
	 * el lexema, el tipo y la posición del siguiente lexema.
	 */
	public Token extraerPalabraReservadaTipoDatoEntero(String cod, int i) {
		if(cod.charAt(i) == 'e' && (i+2) < cod.length()) {
			if (cod.charAt(i+1) == 'n' && cod.charAt(i+2) == 't') {
				int j= i + 3;
				String lex = cod.substring(i,  j);
				Token token = new Token( lex, Token.PALABRA_RESERVADA_ENTEROS, j );
				return token;
			}
		}
		return null;
	}
	
	/**
	 * Intenta extraer una palabra reservada para tipo de dato real de la cadena cod a partir de la posición i,
	 * basándose en el Autómata
	 * @param cod - código al cual se le va a intentar extraer la palabra reservada - codigo!=null
	 * @param i - posición a partir de la cual se va a intentar extraer la palabra reservada - 0<=i<codigo.length()
	 * @return el token palabra reservada tipo de dato o NULL, si el token en la posición dada no es una palabra reservada de tipo de dato. El Token se compone de 
	 * el lexema, el tipo y la posición del siguiente lexema.
	 */
	public Token extraerPalabraReservadaTipoDatoReal(String cod, int i) {
		if(cod.charAt(i) == 'r' && (i+2) < cod.length()) {
			if (cod.charAt(i+1) == 'e' && cod.charAt(i+2) == 'l') {
				int j= i + 3;
				String lex = cod.substring(i,  j);
				Token token = new Token( lex, Token.PALABRA_RESERVADA_REALES, j );
				return token;
			}
		}
		return null;
	}

	/**
	 * Intenta extraer una palabra reservada para tipo de dato cadena de la cadena cod a partir de la posición i,
	 * basándose en el Autómata
	 * @param cod - código al cual se le va a intentar extraer la palabra reservada - codigo!=null
	 * @param i - posición a partir de la cual se va a intentar extraer la palabra reservada - 0<=i<codigo.length()
	 * @return el token palabra reservada tipo de dato o NULL, si el token en la posición dada no es una palabra reservada de tipo de dato. El Token se compone de 
	 * el lexema, el tipo y la posición del siguiente lexema.
	 */
	public Token extraerPalabraReservadaTipoDatoCadena(String cod, int i) {
		if(cod.charAt(i) == 'P' && (i+6) < cod.length()) {
			if (cod.charAt(i+1) == 'h' && cod.charAt(i+2) == 'r' && cod.charAt(i+3) == 'a' && cod.charAt(i+4) == 's' && cod.charAt(i+5) == 'e' && cod.charAt(i+6) == 's') {
				int j= i + 7;
				String lex = cod.substring(i,  j);
				Token token = new Token( lex, Token.PALABRA_RESERVADA_CADENAS, j );
				return token;
			}
		}
		return null;
	}
	
	/**
	 * Intenta extraer una palabra reservada para tipo de dato caracter de la cadena cod a partir de la posición i,
	 * basándose en el Autómata
	 * @param cod - código al cual se le va a intentar extraer la palabra reservada - codigo!=null
	 * @param i - posición a partir de la cual se va a intentar extraer la palabra reservada - 0<=i<codigo.length()
	 * @return el token palabra reservada tipo de dato o NULL, si el token en la posición dada no es una palabra reservada de tipo de dato. El Token se compone de 
	 * el lexema, el tipo y la posición del siguiente lexema.
	 */
	public Token extraerPalabraReservadaTipoDatoCaracter(String cod, int i) {
		if(cod.charAt(i) == 's' && (i+2) < cod.length()) {
			if (cod.charAt(i+1) == 'y' && cod.charAt(i+2) == 'm') {
				int j= i + 3;
				String lex = cod.substring(i,  j);
				Token token = new Token( lex, Token.PALABRA_RESERVADA_CARACTERES, j );
				return token;
			}
		}
		return null;
	}

	//---------------------------SIN CATEGORÍA--------------------------------
	
	/**
	 * Intenta extraer una palabra reservada para estructura condicional multiple de la cadena cod a partir de la posición i,
	 * basándose en el Autómata
	 * @param cod - código al cual se le va a intentar extraer la palabra reservada - codigo!=null
	 * @param i - posición a partir de la cual se va a intentar extraer la palabra reservada - 0<=i<codigo.length()
	 * @return el token identificador palabra reservada o NULL, si el token en la posición dada no es la palabra reservada. El Token se compone de 
	 * el lexema, el tipo y la posición del siguiente lexema.
	 */
	public Token extraerPalabraReservadaEstructuraCondicionalMultiple(String cod, int i) {
		int j;
		String lex;
		if(cod.charAt(i) == '~') {
			j = i + 1;
			if(j < cod.length() && cod.charAt(j) == 'H') {
				j++;
				if(j < cod.length() && cod.charAt(j) == 'A') {
					j++;
					if(j < cod.length() && cod.charAt(j) == 'N') {
						j++;
						if(j < cod.length() && cod.charAt(j) == 'D') {
							j++;
							if(j < cod.length() && cod.charAt(j) == 'L') {
								j++;
								if(j < cod.length() && cod.charAt(j) == 'E') {
									j++;
									if(j < cod.length() && cod.charAt(j) == '~') {
										j++;
										lex = cod.substring(i, j);
										Token token = new Token(lex, Token.PALABRA_RESERVADA_ESTRUCTURA_CONDICIONAL_MULTIPLE, j);
										return token;
									}
								}
							}
						}
					}
				}
			}
		}
		return null;
	}

	//---------------------------NO RECONOCIDO--------------------------------
	
	/**
	 * Extraer un lexema no reconocido de la cadena cod a partir de la posición i.
	 * Antes de utilizar este método, debe haberse intentado todos los otros métodos para los otros tipos de token
	 * @param cod - código al cual se le va a extraer el token no reconocido - codigo!=null
	 * @param i - posición a partir de la cual se va a extraer el token no reconocido  - 0<=indice<codigo.length()
	 * @return el token no reconocido. El Token se compone de lexema, el tipo y la posición del siguiente lexema.

	 */
	public Token extraerNoReconocido ( String cod, int i) {
		String lexema =  cod.substring( i, i + 1);
		int j=i+1;
		Token token = new Token( lexema, Token.NO_RECONOCIDO, j );
		return token;
	}

	//---------------------------CONVENCIONES--------------------------------
	
	/**
	 * Determina si un carácter es un dígito
	 * @param caracter - Carácter que se va a analizar - caracter!=null,
	 * @return true o false según el carácter sea un dígito o no
	 */
	public boolean esDigito (char caracter) {
		return  caracter >= '0' && caracter <= '9';
	}

	/**
	 * Determina si un carácter es una letra mayuscula
	 * @param caracter - Carácter que se va a analizar - caracter!=null,
	 * @return true o false según el carácter sea una letra mayuscula o no
	 */
	public boolean esLetraMayuscula (char caracter) {
		return  caracter >= 'A' && caracter <= 'Z';
	}

	/**
	 * Determina si un carácter es una letra minuscula
	 * @param caracter - Carácter que se va a analizar - caracter!=null,
	 * @return true o false según el carácter sea una letra minuscula o no
	 */
	public boolean esLetraMinuscula (char caracter) {
		return  caracter >= 'a' && caracter <= 'z';
	}

	/**
	 * Determina si un carácter es una letra
	 * @param caracter - Carácter que se va a analizar - caracter!=null,
	 * @return true o false según el carácter sea una letra o no
	 */
	public boolean esLetra (char caracter) {
		return  (caracter >= 'A' && caracter <= 'Z') || (caracter >= 'a' && caracter <= 'z');
	}
	
	//----------------------------MÉTODOS AUXILIARES--------------------------------
	
	/**
	 * Verifica que el token de la palabra reservada de la estructura condicional múltiple exista
	 * @param cod - código al cual se le va a intentar extraer la palabra reservada
	 * @param i - posición a partir de la cual se va a intentar extraer la palabra reservada
	 * @return Un valor booleano: true en caso de que si exista el token, false en caso contrario
	 */
	public boolean verificarPalabraReservadaEstructuraCondicionalMultiple(String cod, int i) {
		if(extraerPalabraReservadaEstructuraCondicionalMultiple(cod, i) != null) {
			return true;
		}
		return false;
	}
}
