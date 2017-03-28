package es.uniovi.asw.business;

import es.uniovi.asw.business.impl.AdminServiceImpl;
import es.uniovi.asw.business.impl.CitizenServiceImpl;
import es.uniovi.asw.business.impl.SystemServiceImpl;

public class Services {
	
	public static CitizenService getCitizenServices() {
		return new CitizenServiceImpl();
	}
	
	public static AdminService getAdminServices() {
		return new AdminServiceImpl();
	}
	
	public static SystemService getSystemServices() {
		return new SystemServiceImpl();
	}

}
