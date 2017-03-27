package es.uniovi.asw.reportwriter;

public class SingletonReporter {
	private static SingletonReporter instance = new SingletonReporter();
	private static WriteReport reporter = new WreportP();
	
	private SingletonReporter() {}
	
	public static SingletonReporter getInstance() {
		return instance;
	}
	
	public static WriteReport getWreportP() {
		return reporter;
	}

}
