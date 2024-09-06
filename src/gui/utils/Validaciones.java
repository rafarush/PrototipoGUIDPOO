package gui.utils;

public class Validaciones {
	
	public static boolean todoLetra(String cadena){
		boolean val = true;
		String regex = "^[a-zA-Z]+$";
		
		if(cadena.matches(regex))
			val = false;
		
		return val;
	}
	
	public static boolean todoNum(String cadena){
		boolean val = true;
		String regex = "^[0-9]+$";
		
		if(cadena.matches(regex))
			val = false;
		
		return val;
	}
}
