/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id$
 * Universidad del Quindío (Armenia - Colombia)
 * Programa de Ingeniería de Sistemas y Computación
 *
 * Asignatura: Teoría de Lenguajes Formales
 * Ejercicio: AnalizadorLexico
 * Diseño original por: Leonardo A. Hernández R. - Agosto 2008 - Marzo 2009
 * Modificado y usado por: Claudia E. Quiceno R- Julio 2021
 * 
 * Presentado por:
 * Mario Alejandro Tabares Ramírez
 * César Esteban Márquez
 * Brian David Giraldo
 * Jhan Carlos Martínez
 * 4/30/2022
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

		//Intenta extraer un operador aritmetico
		token = extraerOperadorAritmetico( cod, i);
		if ( token != null )
			return token;

		//Intenta extraer un operador relacional
		token = extraerOperadorRelacional(cod, i);
		if ( token != null )
			return token;

		//Intenta extraer un operador lógico
		token = extraerOperadorLogico(cod, i);
		if ( token != null )
			return token;

		//Intenta extraer un operador de asignación
		token = extraerOperadorAsignacion( cod, i);
		if ( token != null )
			return token;

		//Intenta extraer un simbolo de apertura
		token = extraerSimboloAbrir(cod, i);
		if ( token != null )
			return token;

		//Intenta extraer un simbolo de cierre
		token = extraerSimboloCerrar(cod, i);
		if ( token != null )
			return token;

		//Intenta extraer un simbolo terminal
		token = extraerSimboloTerminal(cod, i);
		if ( token != null )
			return token;

		//Intenta extraer un separador de sentencia
		token = extraerSeparadorSentencia(cod, i);
		if ( token != null )
			return token;

		//Intenta extraer una palabra reservada para diferentes usos para bucle o ciclo
		token = extraerPalabraReservadaDiferentesUsosBucleCiclo(cod, i);
		if ( token != null )
			return token;

		//Intenta extraer una palabra reservada para diferentes usos para decisión
		token = extraerPalabraReservadaDiferentesUsosDecision(cod, i);
		if ( token != null )
			return token;

		//Intenta extraer una palabra reservada para diferentes usos para la clase
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

		//Intenta extraer un valor de asignacion de un numero entero
		token = extraerValorAsignacionEntero(cod, i);
		if (token != null)
			return token;

		//Intenta extraer un valor de asignacion de un numero real
		token = extraerValorAsignacionReal(cod, i);
		if (token != null)
			return token;

		//Intenta extraer un valor de asignacion de una cadena de caracteres
		token = extraerValorAsignacionCadenaCaracteres(cod, i);
		if (token != null)
			return token;

		//Intenta extraer un valor de asignacion de un caracter
		token = extraerValorAsignacionCaracter(cod, i);
		if (token != null)
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
		int j;
		String lex;
		if(cod.charAt(i) == 'P') {
			j = i + 1;
			if(j < cod.length() && cod.charAt(j) == 'L') {
				j++;
				if(j < cod.length() && cod.charAt(j) == 'U') {
					j++;
					if(j < cod.length() && cod.charAt(j) == 'S') {
						j++;
						if(j < cod.length() && cod.charAt(j) == '↔') {
							return extraerOperadorAsignacion(cod, i);
						}
						lex = cod.substring(i, j);
						Token token = new Token( lex, Token.OPERADOR_ARITMETICO, j );
						return token;
					}
				}
			}else if(j < cod.length() && cod.charAt(j) == 'R') {
				j++;
				if(j < cod.length() && cod.charAt(j) == 'O') {
					j++;
					if(j < cod.length() && cod.charAt(j) == 'D') {
						j++;
						lex = cod.substring(i, j);
						Token token = new Token( lex, Token.OPERADOR_ARITMETICO, j );
						return token;
					}
				}
			}
		}else if(cod.charAt(i) == 'D') {
			j = i + 1;
			if(j < cod.length() && cod.charAt(j) == 'I') {
				j++;
				if(j < cod.length() && cod.charAt(j) == 'F') {
					j++;
					if(j < cod.length() && cod.charAt(j) == 'F') {
						j++;
						if(j < cod.length() && cod.charAt(j) == '↔') {
							return extraerOperadorAsignacion(cod, i);
						}
						lex = cod.substring( i, j);			 
						Token token = new Token( lex, Token.OPERADOR_ARITMETICO, j );
						return token;
					}
				}else if(j < cod.length() && cod.charAt(j) == 'V') {
					j++;
					lex = cod.substring(i, j);			 
					Token token = new Token( lex, Token.OPERADOR_ARITMETICO, j );
					return token;
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
		int j;
		String lex;
		if(cod.charAt(i) == '↑') {
			j = i + 1;
			if(j < cod.length() && cod.charAt(j) == '↔') {
				j++;
				lex = cod.substring(i, j);			 
				Token token = new Token( lex, Token.OPERADOR_RELACIONAL, j );
				return token;
			}
			lex = cod.substring(i, j);			 
			Token token = new Token( lex, Token.OPERADOR_RELACIONAL, j );
			return token;
		}else if(cod.charAt(i) == '↓') {
			j = i + 1;
			if(j < cod.length() && cod.charAt(j) == '↔') {
				j++;
				lex = cod.substring(i, j);			 
				Token token = new Token( lex, Token.OPERADOR_RELACIONAL, j );
				return token;
			}
			lex = cod.substring( i, j);			 
			Token token = new Token( lex, Token.OPERADOR_RELACIONAL, j );
			return token;
		}else if(cod.charAt(i) == '↔') {
			j = i + 1;
			if(j < cod.length() && cod.charAt(j) == '↔') {
				j++;
				lex = cod.substring(i, j);			 
				Token token = new Token( lex, Token.OPERADOR_RELACIONAL, j );
				return token;
			}
		}else if(cod.charAt(i) == '/') {
			j = i + 1;
			if(j < cod.length() && cod.charAt(j) == '↔') {
				j++;
				lex = cod.substring(i, j);			 
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
		int j;
		String lex;
		if(cod.charAt(i) == '~') {
			if(verificarPalabraReservadaEstructuraCondicionalMultiple(cod, i)) {
				return extraerPalabraReservadaEstructuraCondicionalMultiple(cod, i);
			}else {
				j = i + 1;
				lex = cod.substring(i, j);			 
				Token token = new Token( lex, Token.OPERADOR_LOGICO, j );
				return token;
			}
		}else if(cod.charAt(i) == 'ö') {
			j = i + 1;
			lex = cod.substring(i, j);			 
			Token token = new Token( lex, Token.OPERADOR_LOGICO, j );
			return token;
		}else if(cod.charAt(i) == 'ä') {
			j = i + 1;
			lex = cod.substring(i, j);			 
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
		int j;
		String lex;
		if(cod.charAt(i) == '↔') {
			j = i + 1;
			lex = cod.substring(i, j);
			Token token = new Token( lex, Token.OPERADOR_ASIGNACION, j );
			return token;
		}else if(cod.charAt(i) == 'P') {
			j = i + 1;
			if (j < cod.length() && cod.charAt(j) == 'L') {
				j++;
				if(j < cod.length() && cod.charAt(j) == 'U') {
					j++;
					if(j < cod.length() && cod.charAt(j) == 'S') {
						j++;
						if(j < cod.length() && cod.charAt(j) == '↔') {
							j++;
							lex = cod.substring(i, j);
							Token token = new Token( lex, Token.OPERADOR_ASIGNACION, j );
							return token;
						}
					}
				}
			} 
		}else if(cod.charAt(i) == 'D') {
			j = i + 1;
			if(j < cod.length() && cod.charAt(j) == 'I') {
				j++;
				if(j < cod.length() && cod.charAt(j) == 'F') {
					j++;
					if(j < cod.length() && cod.charAt(j) == 'F') {
						j++;
						if(j < cod.length() && cod.charAt(j) == '↔') {
							j++;
							lex = cod.substring(i, j);
							Token token = new Token( lex, Token.OPERADOR_ASIGNACION, j );
							return token;
						}
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
			int j = i + 1;
			String lex = cod.substring(i, j);
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
			int j = i + 1;
			String lex = cod.substring(i, j);
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
		if(cod.charAt(i) == '¬') {
			int j = i + 1;
			String lex = cod.substring(i, j);			    
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
		if(cod.charAt(i) == '|') {
			int j = i + 1;
			if(j < cod.length() && (cod.charAt(j) == 't' || cod.charAt(j) == 'l' || cod.charAt(j) == 'f')) {
				return null;
			}else {
				String lex = cod.substring(i, j);			    
				Token token = new Token( lex, Token.SEPARADOR_SENTENCIA, j );
				return token;
			}
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
		int j;
		String lex;
		if(cod.charAt(i) == 'C') {
			j = i + 1;
			if (j < cod.length() && cod.charAt(j) == 'i') {
				j++;
				if(j < cod.length() && cod.charAt(j) == 'c') {
					j++;
					if(j < cod.length() && cod.charAt(j) == 'l') {
						j++;
						if(j < cod.length() && cod.charAt(j) == 'o') {
							j++;
							if(j < cod.length() && cod.charAt(j) == 'n') {
								j++;
								lex = cod.substring(i, j);
								Token token = new Token( lex, Token.PALABRA_RESERVADA_BUCLE_CICLO, j );
								return token;
							}
						}
					}
				}
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
		int j;
		String lex;
		if(cod.charAt(i) == 'W') {
			j = i + 1;
			if (j < cod.length() && cod.charAt(j) == 'h') {
				j++;
				if(j < cod.length() && cod.charAt(j) == 'a') {
					j++;
					if(j < cod.length() && cod.charAt(j) == 't') {
						j++;
						if(j < cod.length() && cod.charAt(j) == 'i') {
							j++;
							if(j < cod.length() && cod.charAt(j) == 'f') {
								j++;
								lex = cod.substring(i, j);
								Token token = new Token( lex, Token.PALABRA_RESERVADA_DECISION, j );
								return token;
							}
						}
					}
				}
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
		int j;
		String lex;
		if(cod.charAt(i) == 'K') {
			j = i + 1;
			if (j < cod.length() && cod.charAt(j) == 'l') {
				j++;
				if(j < cod.length() && cod.charAt(j) == 'a') {
					j++;
					if(j < cod.length() && cod.charAt(j) == 's') {
						j++;
						if(j < cod.length() && cod.charAt(j) == 's') {
							j++;
							lex = cod.substring(i, j);
							Token token = new Token( lex, Token.PALABRA_RESERVADA_CLASE, j );
							return token;
						}
					}
				}
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

	/**
	 * Intenta extraer un valor de asignacion de un numero entero de la cadena cod a partir de la posición i,
	 * basándose en el Autómata
	 * @param cod - código al cual se le va a intentar extraer el valor de asignacion - codigo!=null
	 * @param i - posición a partir de la cual se va a intentar extraer el valor de asignacion - 0<=i<codigo.length()
	 * @return el token valor asignacion entero o NULL, si el token en la posición dada no es un valor de asignacion de un numero entero. El Token se compone de 
	 * el lexema, el tipo y la posición del siguiente lexema.
	 */
	public Token extraerValorAsignacionEntero(String cod, int i) {
		int j;
		String lex;
		if(cod.charAt(i) == 'I') {
			j = i + 1;
			if(j < cod.length() && cod.charAt(j) == '$') {
				j++;
				if(j < cod.length() && esDigito(cod.charAt(j))) {
					j++;
					while(j < cod.length() && esDigito(cod.charAt(j))) {
						j++;	
					}
					lex = cod.substring(i, j);			    
					Token token = new Token( lex, Token.VALOR_ASIGNACION_ENTEROS, j );
					return token;
				}
			}
		}
		return null;
	}

	/**
	 * Intenta extraer un valor de asignacion de un numero real de la cadena cod a partir de la posición i,
	 * basándose en el Autómata
	 * @param cod - código al cual se le va a intentar extraer el valor de asignacion - codigo!=null
	 * @param i - posición a partir de la cual se va a intentar extraer el valor de asignacion - 0<=i<codigo.length()
	 * @return el token valor asignacion real o NULL, si el token en la posición dada no es un valor de asignacion de un numero real. El Token se compone de 
	 * el lexema, el tipo y la posición del siguiente lexema.
	 */
	public Token extraerValorAsignacionReal(String cod, int i) {
		int j;
		String lex;
		if(cod.charAt(i) == 'R') {
			j = i + 1;
			if(j < cod.length() && cod.charAt(j) == '$') {
				j++;
				if(j < cod.length() && cod.charAt(j) == '-') {
					j++;
				}
				if(j < cod.length() && esDigito(cod.charAt(j))) {
					j++;
					while(j < cod.length() && esDigito(cod.charAt(j))) {
						j++;	
					}
					if(j < cod.length() && cod.charAt(j) == '.') {
						j++;
						if(j < cod.length() && esDigito(cod.charAt(j))) {
							j++;
							while(j < cod.length() && esDigito(cod.charAt(j))) {
								j++;	
							}
							lex = cod.substring(i, j);			    
							Token token = new Token( lex, Token.VALOR_ASIGNACION_REALES, j );
							return token;
						}
					}
				}
			}
		}
		return null;
	}

	/**
	 * Intenta extraer un valor de asignacion de una cadena de caracteres de la cadena cod a partir de la posición i,
	 * basándose en el Autómata
	 * @param cod - código al cual se le va a intentar extraer el valor de asignacion - codigo!=null
	 * @param i - posición a partir de la cual se va a intentar extraer el valor de asignacion - 0<=i<codigo.length()
	 * @return el token valor asignacion cadena o NULL, si el token en la posición dada no es un valor de asignacion de una cadena. El Token se compone de 
	 * el lexema, el tipo y la posición del siguiente lexema.
	 */
	public Token extraerValorAsignacionCadenaCaracteres(String cod, int i) {
		int j;
		String lex;
		if(cod.charAt(i) == 'C') {
			j = i + 1;
			if(j < cod.length() && cod.charAt(j) == 'C') {
				j++;
				if(j < cod.length() && cod.charAt(j) == '$') {
					j++;
					while(j < cod.length() && cod.charAt(j) != '$') {
						if(j < cod.length() && esCualquierSimbolo(cod.charAt(j))) {
							j++;
						}
					}
					if(j < cod.length() && cod.charAt(j) == '$') {
						j++;
						if(j < cod.length() && cod.charAt(j) == 'C') {
							j++;
							if(j < cod.length() && cod.charAt(j) == 'C') {
								j++;
								lex = cod.substring(i, j);			    
								Token token = new Token( lex, Token.VALOR_ASIGNACION_CADENA, j );
								return token;
							}
						}
					}
				}
			}
		}
		return null;
	}

	/**
	 * Intenta extraer un valor de asignacion de un caracter de la cadena cod a partir de la posición i,
	 * basándose en el Autómata
	 * @param cod - código al cual se le va a intentar extraer el valor de asignacion - codigo!=null
	 * @param i - posición a partir de la cual se va a intentar extraer el valor de asignacion - 0<=i<codigo.length()
	 * @return el token valor asignacion caracter o NULL, si el token en la posición dada no es un valor de asignacion de un caracter. El Token se compone de 
	 * el lexema, el tipo y la posición del siguiente lexema.
	 */
	public Token extraerValorAsignacionCaracter(String cod, int i) {
		int j;
		String lex;
		if(esCualquierSimbolo(cod.charAt(i))) {
			j = i + 1;
			if(cod.charAt(i) == '|') {
				if( j < cod.length() && (cod.charAt(j) == 't' || cod.charAt(j) == 'l' || cod.charAt(j) == 'f')) {
					j++;
				}
			}
			lex = cod.substring(i, j);			    
			Token token = new Token( lex, Token.VALOR_ASIGNACION_CARACTERES, j );
			return token;
		}
		return null;
	}

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
		int j;
		String lex;
		if(cod.charAt(i) == 'e') {
			j = i + 1;
			if (j < cod.length() && cod.charAt(j) == 'n') {
				j++;
				if(j < cod.length() && cod.charAt(j) == 't') {
					j++;
					lex = cod.substring(i, j);
					Token token = new Token( lex, Token.PALABRA_RESERVADA_ENTEROS, j );
					return token;
				}
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
		int j;
		String lex;
		if(cod.charAt(i) == 'r') {
			j = i + 1;
			if(j < cod.length() && cod.charAt(j) == 'e') {
				j++;
				if(j < cod.length() && cod.charAt(j) == 'l') {
					j++;
					lex = cod.substring(i, j);
					Token token = new Token( lex, Token.PALABRA_RESERVADA_REALES, j );
					return token;
				}
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
		int j;
		String lex;
		if(cod.charAt(i) == 'P') {
			j = i + 1;
			if (j < cod.length() && cod.charAt(j) == 'h') {
				j++;
				if(j < cod.length() && cod.charAt(j) == 'r') {
					j++;
					if(j < cod.length() && cod.charAt(j) == 'a') {
						j++;
						if(j < cod.length() && cod.charAt(j) == 's') {
							j++;
							if(j < cod.length() && cod.charAt(j) == 'e') {
								j++;
								if(j < cod.length() && cod.charAt(j) == 's') {
									j++;
									lex = cod.substring(i, j);
									Token token = new Token( lex, Token.PALABRA_RESERVADA_CADENAS, j );
									return token;
								}
							}
						}
					}
				}
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
		int j;
		String lex;
		if(cod.charAt(i) == 's') {
			j = i + 1;
			if (j < cod.length() && cod.charAt(j) == 'y') {
				j++;
				if(j < cod.length() && cod.charAt(j) == 'm') {
					j++;
					lex = cod.substring(i, j);
					Token token = new Token( lex, Token.PALABRA_RESERVADA_CARACTERES, j );
					return token;
				}
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
	public Token extraerNoReconocido(String cod, int i) {
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
	public boolean esDigito(char caracter) {
		return  caracter >= '0' && caracter <= '9';
	}

	/**
	 * Determina si un carácter es una letra mayuscula
	 * @param caracter - Carácter que se va a analizar - caracter!=null,
	 * @return true o false según el carácter sea una letra mayuscula o no
	 */
	public boolean esLetraMayuscula(char caracter) {
		return  caracter >= 'A' && caracter <= 'Z';
	}

	/**
	 * Determina si un carácter es una letra minuscula
	 * @param caracter - Carácter que se va a analizar - caracter!=null,
	 * @return true o false según el carácter sea una letra minuscula o no
	 */
	public boolean esLetraMinuscula(char caracter) {
		return  caracter >= 'a' && caracter <= 'z';
	}

	/**
	 * Determina si un carácter es una letra
	 * @param caracter - Carácter que se va a analizar - caracter!=null,
	 * @return true o false según el carácter sea una letra o no
	 */
	public boolean esLetra(char caracter) {
		return  (caracter >= 'A' && caracter <= 'Z') || (caracter >= 'a' && caracter <= 'z');
	}

	/**
	 * Determina si un carácter es cualquier simbolo del ASCII (incluido el espacio)
	 * @param caracter - Carácter que se va a analizar - caracter!=null,
	 * @return true o false según el carácter sea cualquier simbolo del ASCII o no
	 */
	public boolean esCualquierSimbolo(char caracter) {
		return  (caracter >= ' ' && caracter <= '~');
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
