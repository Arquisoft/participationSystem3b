package es.uniovi.asw.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

import es.uniovi.asw.model.Citizen;

/*
 * Consola de albutil
 */
public class Console {
	protected static BufferedReader kbd = new BufferedReader(
			new InputStreamReader(System.in));
	

	public static void println(String string) {
		System.out.println(string);
	}

	public static void print(String string) {
		System.out.print(string);
	}
	
	public static void printList(List<Citizen> citizens) {
		int cont = 1;
		for (Object l: citizens) {
			System.out.println(" Posicion "+cont+" "+l.toString()+"-");
		}
	}

}
