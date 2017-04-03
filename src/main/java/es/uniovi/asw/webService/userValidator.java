package es.uniovi.asw.webService;

public class userValidator {
	public static boolean validate(String name,String passwd,String type){
		if (name.equals("admin"))
			if (type.equals("admin"))
				return true;
			else
				return false;
		return true;
	}
}
