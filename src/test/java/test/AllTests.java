package test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
	@SuiteClasses({ 
		CitizenServiceTest.class,
		AdminServiceTest.class, 
		ModelTest.class})
	public class AllTests {
	}

