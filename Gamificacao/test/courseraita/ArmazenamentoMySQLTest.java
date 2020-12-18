package courseraita;

import org.junit.jupiter.api.Test;

import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

@FixMethodOrder (MethodSorters.NAME_ASCENDING)

public class ArmazenamentoMySQLTest {
	
	@Test
	void testSet001() {
		
		DriverMySQL8Database _driver =  new DriverMySQL8Database(true); 
		Armazenamento _a = new Armazenamento(_driver);
		
	}

}
