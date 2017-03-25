package es.uniovi.asw.util;

import java.util.Random;

public class Generator {
	
	public static String password(int length,int fila) {
		String pass = "";
		//long tmp = new java.util.GregorianCalendar().getTimeInMillis();
		Random r = new Random(fila);
		
		while(pass.length()<length) {
			char c = (char) r.nextInt(255);
			if ((c >= '0' && c <= '9') || (c >= 'A' && c <= 'Z')) 
				pass += c;
		}
		
		return pass;
	}
	
	public static String username(String name, String mail) {
		return name+"_"+mail.split("@")[0];
	}

}
