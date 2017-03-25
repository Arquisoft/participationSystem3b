package es.uniovi.asw.util;

import java.util.Date;

import es.uniovi.asw.model.exception.BusinessException;

public class Checker {
	
	public static String name(String name,int row,int column,String fichero) throws BusinessException {
		String dato = "nombre";
		if (isNull(name))
			error(dato,name+" no puede ser un dato nulo",row,column,fichero);
		
		if(!isAllText(name))
			error(dato,name+ " tiene que ser un todo texto",row,column,fichero);
		
		return name;
	}
	
	public static String surname(String surname,int row,int column,String fichero) throws Exception {
		String dato = "apellido";
		if (isNull(surname))
			error(dato,surname+" no puede ser un dato nulo",row,column,fichero);
		
		if(!isAllText(surname))
			error(dato,surname+" tiene que ser un todo texto",row,column,fichero);
		
		return surname;
	}
	
	public static String mail(String mail, int row, int column, String fichero) throws Exception {
		String dato = "mail";
		if (isNull(mail))
			error(dato, mail+" no puede ser un dato nulo",row,column,fichero);
		if (!mail.contains("@"))
			error(dato,mail+" debe incluir el simbolo '@'",row,column,fichero);
		
		//String finales = ".es.com.org";
		//if(!finales.contains(mail.split("@")[1].split("\\.")[1]))
		if(!mail.contains(".es")&&!mail.contains(".com")&&!mail.contains(".org"))
			//error(dato,mail+" debe incluir una de estas cadenas despues del '.' -> ",row,column,fichero);
			error(dato,mail+" debe terminar en .es, .com o .org",row,column,fichero);
		
		return mail;
	}
	
	public static Date date(Date date,int row,int column, String fichero) throws Exception {
		String dato = "fecha";
		if (isNull(date))
			error(dato,date+" no puede ser un dato nulo",row,column,fichero);
		
		return date;
	}
	
	public static String address(String address,int row,int column,String fichero) throws Exception {
		String dato = "direccion";
		if (isNull(address))
			error(dato,address+" no puede ser un dato nulo",row,column,fichero);
		
		return address;
	}
	
	public static String nationality(String nationality,int row, int column, String fichero) throws Exception {
		String dato = "nacionalidad";
		if (isNull(nationality))
			error(dato,nationality+" no puede ser un dato nulo",row,column,fichero);
		
		if(!isAllText(nationality))
			error(dato,nationality+" tiene que ser un todo texto",row,column,fichero);
		
		return nationality;
	}
	
	public static String dni(String dni,int row,int column,String fichero) throws Exception {
		String dato = "dni";
		if (isNull(dni)) 
			error(dato,dni+" no puede ser un dato nulo",row,column,fichero);
		
		return dni;
	}
	
	private static boolean isNull(Object obj) {
		return (obj == null || obj.toString().equals(""));
	}
	
	private static boolean isAllText(String prueba) {
		char[] characters = prueba.toCharArray();
		for (char c : characters) 
			if (!Character.isLetter(c) && !Character.isWhitespace(c))
				return false;

		return true;
	}
	
	private static void error(String dato, String message, int fila, int columna, String fichero) throws BusinessException {
		String mess = "Datos incorrectos ---> "
				+"El "+dato+" "+message+" [fila:"+fila+" columna:"+columna+"] de "+fichero;
		throw new BusinessException(mess);
	}
}
