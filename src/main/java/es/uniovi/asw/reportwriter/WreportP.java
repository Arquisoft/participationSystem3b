package es.uniovi.asw.reportwriter;

public class WreportP implements WriteReport{

	@Override
	public void report(String... errors) {
		Log.info(errors);
	}

}
