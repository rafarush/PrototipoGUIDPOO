package gui.utils;

public class Validaciones {
	
	
	/**
	 * Valida que la cadena recibida solo contenga letras (Mayusculas o minusculas)
	 * @param cadena
	 * @return true, si lo es
	 */
	public static boolean todoLetra(String cadena){
		boolean val = false;
		String regex = "^[a-zA-Z]+$";
		
		if(cadena.matches(regex))
			val = true;
		
		return val;
	}
	
	
	/**
	 * Valida que la cadena recibida solo contenga numeros
	 * @param cadena
	 * @return true, si lo es
	 */
	public static boolean todoNum(String cadena){
		boolean val = false;
		String regex = "^[0-9]+$";
		
		if(cadena.matches(regex))
			val = true;
		
		return val;
	}
	
	/**
	 * Devuelve la cadena sin espacios
	 * @param cadena
	 * @return String
	 */
	public static String getCadenaSinEspacios(String cadena){
		String aux = cadena.replace(" ", "");
		
		return aux;
	}
	
	/**
	 * Verifica que un numero sea par
	 * @param num
	 * @return
	 */
	public static boolean esPar(int num){
		boolean val = false;
		
		if(num%2 == 0)
			val = true;
		
		return val;
	}
	
	/**
	 * Verifica que un año sea bisiesto
	 * @param anno
	 * @return
	 */
	public static boolean esBisiesto(int anno){
		boolean val = false;
		
		if (anno%4 == 0){
			if (anno%100 != 0){
				val = true;
			}else{
				if (anno%400 == 0){
					val = true;
				}
			}
		}
		
		return val;
	}
	
	/**
	 * Verifica que la persona no sea ni muy pequeña o demasiado mayor
	 * @param anno
	 * @return
	 */
	public static boolean esValAnno(int anno){
		boolean val = true;
		
		if (anno > 2006 || anno < 1934)
			val = false;
		
		return val;
	}
	
	/**
	 * Verifica que sea un mes valido
	 * @param anno
	 * @return
	 */
	public static boolean esValMes(int mes){
		boolean val = false;
		
		if (mes >= 1 && mes <= 12)
			val = true;
		
		return val;
	}
	
	/**
	 * Verifica que sea un dia valido
	 * @param anno
	 * @return
	 */
	public static boolean esValDia(int dia, int mes, int anno){
		boolean val = true;
	
		if (dia > 0 && dia < 32){
			
			if (mes == 4 || mes == 6 || mes == 9 || mes == 11){
				if(dia>30)
					val = false;
				
			}else if (mes == 2){
				
				if(esBisiesto(anno)){
					if(dia>29)
						val = false;
				}else{
					if(dia>28)
						val = false;
				}
				
			}else{
				if(dia>31)
					val = false;
			}
			
		}else{
			val = false;
		}
			
		
		return val;
	}
	
	
	/**
	 * Devuelve el año completo de nacimiento de la persona
	 * @param anno
	 * @param siglo
	 * @return int
	 */
	public static int completarAnno(String anno, char siglo){
		String annoCompleto = "";
		int annoInt = 0;
		
		if(siglo >= '0' && siglo <= '5'){
			annoCompleto = "19"+anno;
		}else if(siglo >= '6' && siglo <= '8'){
			annoCompleto = "20"+anno;
		}else{
			annoCompleto = "18"+anno;
		}
		
		if(!annoCompleto.isEmpty())
			annoInt = Integer.valueOf(getCadenaSinEspacios(annoCompleto));
		
		return annoInt;
	}
	
	
	/**
	 * Valida la entrada de carne de identidad
	 * @param cI
	 * @return
	 */
	public static boolean valCI(String id){
		boolean val = false;
		boolean valAnno = false;
		boolean valMes = false;
		boolean valDia = false;
		String cI = getCadenaSinEspacios(id);
		
		if (todoNum(cI) && cI.length() == 11){
			int mes = Integer.valueOf(cI.substring(2, 4));
			int dia = Integer.valueOf(cI.substring(4, 6));
			char siglo = cI.charAt(6);
			int annoCompleto = completarAnno(cI.substring(0, 2), siglo);
			
			if (esValAnno(annoCompleto))
				valAnno = true;
			
			if (esValMes(mes))
				valMes = true;
			
			if (esValDia(dia, mes, annoCompleto))
				valDia = true;
		}
		
		if(valAnno && valMes && valDia)
			val = true;
		
		return val;
	}
	
	
	
}
