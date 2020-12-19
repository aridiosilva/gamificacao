package courseraita;

import org.junit.jupiter.api.Test;

import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

@FixMethodOrder (MethodSorters.NAME_ASCENDING)

public class ArmazenamentoMySQLTest {
	
	@Test
	void testSet001() throws Exception {
		
		IDriverArquivo _driver = new DriverMySQL8Database(false); 
		Armazenamento _a = new Armazenamento(_driver);
		
		_a.armazenarPontuacaoDeUmUsuario(new PontuacaoUsuarios("joana", "estrela",    50));
//		_a.armazenarPontuacaoDeUmUsuario(new PontuacaoUsuarios("joana", "estrela",    50));
//		_a.armazenarPontuacaoDeUmUsuario(new PontuacaoUsuarios("joana", "comentarios",80));
		
	}

}
