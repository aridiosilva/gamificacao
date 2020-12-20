package courseraita;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.HashSet;
import java.util.LinkedList;

import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

@FixMethodOrder (MethodSorters.NAME_ASCENDING)

public class ArmazenamentoMySQLTest {
	
	@Test
	void testSet001() throws Exception {
		
		IDriverArquivo _driver = new DriverMySQL8Database(true); 
		Armazenamento _a = new Armazenamento(_driver);
		
		// Conferindo inclusão de varias pontuações inciais de um mesmo usuario
		
		_a.armazenarPontuacaoDeUmUsuario(new PontuacaoUsuarios("joana", "estrela",    50));
		_a.armazenarPontuacaoDeUmUsuario(new PontuacaoUsuarios("joana", "estrela",    50));
		_a.armazenarPontuacaoDeUmUsuario(new PontuacaoUsuarios("joana", "comentarios",80));

		// Conferindo recuperação das varias pontuações sumarizadas de um mesmo usuario

		assertEquals (100, _a.recuperarTotaisDePontosDeUmTipoDeUmUsuario("estrela",    "joana"));  
		assertEquals (80, _a.recuperarTotaisDePontosDeUmTipoDeUmUsuario("comentarios", "joana"));  

		// Conferindo recuperação das varias pontuações individuais de um mesmo usuario

		LinkedList<PontuacaoUsuarios> _listaPontos = _a.retornarUsuariosComAlgumTipodePonto();
		
		assertEquals ("joana",   _listaPontos.get(0).getUsuario()); 
		assertEquals ("estrela", _listaPontos.get(0).getTipoPonto());  
		assertEquals (50,        _listaPontos.get(0).getPontos());  

		assertEquals ("joana",   _listaPontos.get(1).getUsuario()); 
		assertEquals ("estrela", _listaPontos.get(1).getTipoPonto());  
		assertEquals (50,        _listaPontos.get(1).getPontos());  
		
		assertEquals ("joana",   _listaPontos.get(2).getUsuario()); 
		assertEquals ("comentarios", _listaPontos.get(2).getTipoPonto());  
		assertEquals (80,        _listaPontos.get(2).getPontos());  		

		// Conferindo inclusão subsequente de varias pontuações inciais de um mesmo usuario

		_a.armazenarPontuacaoDeUmUsuario(new PontuacaoUsuarios("joana", "energia",  130));
		_a.armazenarPontuacaoDeUmUsuario(new PontuacaoUsuarios("joana", "curtida",   58));
		_a.armazenarPontuacaoDeUmUsuario(new PontuacaoUsuarios("joana", "moeda",    132));
		_a.armazenarPontuacaoDeUmUsuario(new PontuacaoUsuarios("joana", "estrela",   10));
		_a.armazenarPontuacaoDeUmUsuario(new PontuacaoUsuarios("joana", "estrela",  -40));
		_a.armazenarPontuacaoDeUmUsuario(new PontuacaoUsuarios("joana", "vidadupla",120));
		
		// Conferindo recuperação das varias pontuações sumarizadas de um mesmo usuario
		
		assertEquals ( 70, _a.recuperarTotaisDePontosDeUmTipoDeUmUsuario("estrela",   "joana"));  
		assertEquals ( 58, _a.recuperarTotaisDePontosDeUmTipoDeUmUsuario("curtida",   "joana"));  
		assertEquals (132, _a.recuperarTotaisDePontosDeUmTipoDeUmUsuario("moeda",     "joana"));  
		assertEquals (120, _a.recuperarTotaisDePontosDeUmTipoDeUmUsuario("vidadupla", "joana"));  
		
		// Conferindo recuperação das varias pontuações individuais incluidas subsequentemente de um mesmo usuario
		
		_listaPontos = _a.retornarUsuariosComAlgumTipodePonto();

		assertEquals ("joana",  _listaPontos.get(3).getUsuario()); 
		assertEquals ("energia",_listaPontos.get(3).getTipoPonto());  
		assertEquals (130,      _listaPontos.get(3).getPontos());  

		assertEquals ("joana",  _listaPontos.get(4).getUsuario()); 
		assertEquals ("curtida",_listaPontos.get(4).getTipoPonto());  
		assertEquals (58,       _listaPontos.get(4).getPontos());  

		assertEquals ("joana",  _listaPontos.get(5).getUsuario()); 
		assertEquals ("moeda",  _listaPontos.get(5).getTipoPonto());  
		assertEquals (132,      _listaPontos.get(5).getPontos());  


		assertEquals ("joana",   _listaPontos.get(6).getUsuario()); 
		assertEquals ("estrela", _listaPontos.get(6).getTipoPonto());  
		assertEquals (10,        _listaPontos.get(6).getPontos());  

		assertEquals ("joana",   _listaPontos.get(7).getUsuario()); 
		assertEquals ("estrela", _listaPontos.get(7).getTipoPonto());  
		assertEquals (-40,       _listaPontos.get(7).getPontos());  

		assertEquals ("joana",    _listaPontos.get(8).getUsuario()); 
		assertEquals ("vidadupla",_listaPontos.get(8).getTipoPonto());  
		assertEquals (120,        _listaPontos.get(8).getPontos());  
		
		
		// Conferir Todos os Tipos de Pontos que Já Foram Registrados para Algum Usuario
		
		HashSet<String> _tiposDePontos = _a.retornarTiposDePontosJaRegistrados();
		
		assertEquals (true,_tiposDePontos.contains("estrela"));
		assertEquals (true,_tiposDePontos.contains("curtida"));
		assertEquals (true,_tiposDePontos.contains("moeda"));
		assertEquals (true,_tiposDePontos.contains("vidadupla")); 
		assertEquals (true,_tiposDePontos.contains("energia")); 
		assertEquals (true,_tiposDePontos.contains("comentarios")); 
	}

	
	@Test
	void testSet002() throws IOException, Exception {

		IDriverArquivo _driver = new DriverMySQL8Database(false); 
		Armazenamento _a = new Armazenamento(_driver);
		
		// Conferindo inclusão de varias pontuações inciaisde multiplos usuarios		
		
		_a.armazenarPontuacaoDeUmUsuario(new PontuacaoUsuarios("gabriela",  "estrela", 50));
		_a.armazenarPontuacaoDeUmUsuario(new PontuacaoUsuarios("gabriela",  "estrela", 100));
		_a.armazenarPontuacaoDeUmUsuario(new PontuacaoUsuarios("gabriela",  "estrela", -140));
		_a.armazenarPontuacaoDeUmUsuario(new PontuacaoUsuarios("alfredo",   "vida",    20));
		_a.armazenarPontuacaoDeUmUsuario(new PontuacaoUsuarios("alfredo",   "curtida", 45));
		_a.armazenarPontuacaoDeUmUsuario(new PontuacaoUsuarios("alfredo",   "moeda",   30));
		_a.armazenarPontuacaoDeUmUsuario(new PontuacaoUsuarios("sebastiao", "vida",    19));
		_a.armazenarPontuacaoDeUmUsuario(new PontuacaoUsuarios("sebastiao", "vida",    20));
		_a.armazenarPontuacaoDeUmUsuario(new PontuacaoUsuarios("xeila",     "energia", 30));
		_a.armazenarPontuacaoDeUmUsuario(new PontuacaoUsuarios("maristela", "curtida", 28));
		_a.armazenarPontuacaoDeUmUsuario(new PontuacaoUsuarios("cristina",  "moeda",   44));
		_a.armazenarPontuacaoDeUmUsuario(new PontuacaoUsuarios("cristina",  "estrela", 100));
		_a.armazenarPontuacaoDeUmUsuario(new PontuacaoUsuarios("antonieta", "vida",    -50));
		
		
		// Conferindo recuperação das varias pontuações sumarizadas de cada um dos usuarios

		assertEquals ( 10, _a.recuperarTotaisDePontosDeUmTipoDeUmUsuario("estrela",    "gabriela"));  // 50 + 100 - 140 = 10
		assertEquals ( 20, _a.recuperarTotaisDePontosDeUmTipoDeUmUsuario("vida",       "alfredo"));  
		assertEquals ( 45, _a.recuperarTotaisDePontosDeUmTipoDeUmUsuario("curtida",    "alfredo"));  
		assertEquals ( 39, _a.recuperarTotaisDePontosDeUmTipoDeUmUsuario("vida",       "sebastiao"));  // 19 + 20 = 39
		assertEquals ( 30, _a.recuperarTotaisDePontosDeUmTipoDeUmUsuario("energia",    "xeila"));  
		assertEquals ( 28, _a.recuperarTotaisDePontosDeUmTipoDeUmUsuario("curtida",    "maristela"));  
		assertEquals ( 44, _a.recuperarTotaisDePontosDeUmTipoDeUmUsuario("moeda",      "cristina"));  
		assertEquals (100, _a.recuperarTotaisDePontosDeUmTipoDeUmUsuario("estrela",    "cristina"));  
		assertEquals (-50, _a.recuperarTotaisDePontosDeUmTipoDeUmUsuario("vida",       "antonieta"));  
		assertEquals ( 70, _a.recuperarTotaisDePontosDeUmTipoDeUmUsuario("estrela",    "joana"));  
		assertEquals ( 58, _a.recuperarTotaisDePontosDeUmTipoDeUmUsuario("curtida",    "joana"));  
		assertEquals (132, _a.recuperarTotaisDePontosDeUmTipoDeUmUsuario("moeda",      "joana"));  
		assertEquals (120, _a.recuperarTotaisDePontosDeUmTipoDeUmUsuario("vidadupla",  "joana"));  
		
		// Conferindo recuperação das varias pontuações individuais de um mesmo usuario

		LinkedList<PontuacaoUsuarios> _listaPontos = _a.retornarUsuariosComAlgumTipodePonto();
		
		assertEquals ("joana",   _listaPontos.get(0).getUsuario()); 
		assertEquals ("estrela", _listaPontos.get(0).getTipoPonto());  
		assertEquals (50,        _listaPontos.get(0).getPontos());  

		assertEquals ("joana",   _listaPontos.get(1).getUsuario()); 
		assertEquals ("estrela", _listaPontos.get(1).getTipoPonto());  
		assertEquals (50,        _listaPontos.get(1).getPontos());  
		
		assertEquals ("joana",      _listaPontos.get(2).getUsuario()); 
		assertEquals ("comentarios",_listaPontos.get(2).getTipoPonto());  
		assertEquals (80,           _listaPontos.get(2).getPontos());  	
		
		assertEquals ("joana",  _listaPontos.get(3).getUsuario()); 
		assertEquals ("energia",_listaPontos.get(3).getTipoPonto());  
		assertEquals (130,      _listaPontos.get(3).getPontos());  

		assertEquals ("joana",  _listaPontos.get(4).getUsuario()); 
		assertEquals ("curtida",_listaPontos.get(4).getTipoPonto());  
		assertEquals (58,       _listaPontos.get(4).getPontos());  

		assertEquals ("joana",  _listaPontos.get(5).getUsuario()); 
		assertEquals ("moeda",  _listaPontos.get(5).getTipoPonto());  
		assertEquals (132,      _listaPontos.get(5).getPontos());  

		assertEquals ("joana",   _listaPontos.get(6).getUsuario()); 
		assertEquals ("estrela", _listaPontos.get(6).getTipoPonto());  
		assertEquals (10,        _listaPontos.get(6).getPontos());  

		assertEquals ("joana",   _listaPontos.get(7).getUsuario()); 
		assertEquals ("estrela", _listaPontos.get(7).getTipoPonto());  
		assertEquals (-40,       _listaPontos.get(7).getPontos());  

		assertEquals ("joana",    _listaPontos.get(8).getUsuario()); 
		assertEquals ("vidadupla",_listaPontos.get(8).getTipoPonto());  
		assertEquals (120,        _listaPontos.get(8).getPontos());  
 
		assertEquals ("gabriela",_listaPontos.get(9).getUsuario()); 
		assertEquals ("estrela", _listaPontos.get(9).getTipoPonto());  
		assertEquals (50,        _listaPontos.get(9).getPontos());  

		assertEquals ("gabriela",_listaPontos.get(10).getUsuario()); 
		assertEquals ("estrela", _listaPontos.get(10).getTipoPonto());  
		assertEquals (100,       _listaPontos.get(10).getPontos());  

		assertEquals ("gabriela",_listaPontos.get(11).getUsuario()); 
		assertEquals ("estrela", _listaPontos.get(11).getTipoPonto());  
		assertEquals (-140,      _listaPontos.get(11).getPontos());  
		
		assertEquals ("alfredo",_listaPontos.get(12).getUsuario()); 
		assertEquals ("vida",   _listaPontos.get(12).getTipoPonto());  
		assertEquals (20,       _listaPontos.get(12).getPontos());  		
		
		assertEquals ("alfredo",_listaPontos.get(13).getUsuario()); 
		assertEquals ("curtida",_listaPontos.get(13).getTipoPonto());  
		assertEquals (45,       _listaPontos.get(13).getPontos());  		
		
		assertEquals ("alfredo",_listaPontos.get(14).getUsuario()); 
		assertEquals ("moeda",  _listaPontos.get(14).getTipoPonto());  
		assertEquals (30,       _listaPontos.get(14).getPontos());  		
		
		assertEquals ("sebastiao",_listaPontos.get(15).getUsuario()); 
		assertEquals ("vida",     _listaPontos.get(15).getTipoPonto());  
		assertEquals (19,         _listaPontos.get(15).getPontos());  		
		
		assertEquals ("sebastiao",_listaPontos.get(16).getUsuario()); 
		assertEquals ("vida",     _listaPontos.get(16).getTipoPonto());  
		assertEquals (20,         _listaPontos.get(16).getPontos());  		
		
		assertEquals ("xeila",   _listaPontos.get(17).getUsuario()); 
		assertEquals ("energia", _listaPontos.get(17).getTipoPonto());  
		assertEquals (30,        _listaPontos.get(17).getPontos());  		
		
		assertEquals ("maristela",_listaPontos.get(18).getUsuario()); 
		assertEquals ("curtida",  _listaPontos.get(18).getTipoPonto());  
		assertEquals (28,         _listaPontos.get(18).getPontos());  		
		
		assertEquals ("cristina",_listaPontos.get(19).getUsuario()); 
		assertEquals ("moeda",   _listaPontos.get(19).getTipoPonto());  
		assertEquals (44,        _listaPontos.get(19).getPontos());  		
		
		assertEquals ("cristina",_listaPontos.get(20).getUsuario()); 
		assertEquals ("estrela", _listaPontos.get(20).getTipoPonto());  
		assertEquals (100,       _listaPontos.get(20).getPontos());  		
		
		assertEquals ("antonieta",_listaPontos.get(21).getUsuario()); 
		assertEquals ("vida",     _listaPontos.get(21).getTipoPonto());  
		assertEquals (-50,        _listaPontos.get(21).getPontos());  		
		
		
		// Conferir Todos os Tipos de Pontos que Já Foram Registrados para Algum Usuario
		
		HashSet<String> _tiposDePontos = _a.retornarTiposDePontosJaRegistrados();
		assertEquals (7,_tiposDePontos.size());
		assertEquals (true,_tiposDePontos.contains("estrela"));
		assertEquals (true,_tiposDePontos.contains("vida"));
		assertEquals (true,_tiposDePontos.contains("curtida"));
		assertEquals (true,_tiposDePontos.contains("moeda"));
		assertEquals (true,_tiposDePontos.contains("energia"));
		assertEquals (true,_tiposDePontos.contains("vidadupla"));
		assertEquals (true,_tiposDePontos.contains("comentarios"));

		// truncar o conteudo da tabela pontuacap p/possibilitar rodar quantas vezes desejar a BATERIA-TESTES UNITÁRIOS 
		// e de INTEGRAÇÃO


		IDriverArquivo _driver1 =  new DriverMySQL8Database(false);  // Ao instancia c/true p/truncar a tabela pontuacao
	}

}
