
package courseraita;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

@FixMethodOrder (MethodSorters.NAME_ASCENDING)
class PlacarTest {

	MockArmazenamento _mock = new MockArmazenamento();
	Placar _placar;
	 
	@Test
	void testSet001() throws Exception {

		// Conferindo inclusão de varias pontuações de um mesmo usuario

    	_placar = new Placar (_mock);
		_placar.registrarPontosDoUsuario(new PontuacaoUsuarios("francisco", "estrela", 50));
		_placar.registrarPontosDoUsuario(new PontuacaoUsuarios("francisco", "comentarios", 80));
		_placar.registrarPontosDoUsuario(new PontuacaoUsuarios("francisco", "vida", 19));
		_placar.registrarPontosDoUsuario(new PontuacaoUsuarios("francisco", "energia", 30));
		_placar.registrarPontosDoUsuario(new PontuacaoUsuarios("francisco", "curtida", 28));
		_placar.registrarPontosDoUsuario(new PontuacaoUsuarios("francisco", "moeda", 44));
		_placar.registrarPontosDoUsuario(new PontuacaoUsuarios("francisco", "estrela", 100));
		_placar.registrarPontosDoUsuario(new PontuacaoUsuarios("francisco", "estrela", -50));

		// Conferindo a recuperação das varias pontuações de um mesmo usuario

		HashMap<String,String> _pontosUsuario = _placar.retornarPontosDoUsuario("francisco");
		assertEquals (false,_pontosUsuario.isEmpty());
		assertEquals (true,_pontosUsuario.containsKey("moeda"));  
		assertEquals (true,_pontosUsuario.containsValue("44"));
		
		assertEquals (true,_pontosUsuario.containsKey("estrela"));
		assertEquals (true,_pontosUsuario.containsValue("100"));    // Como teve 50 + 100 - 50 o saldo de pontos = 100
		
		assertEquals (true,_pontosUsuario.containsKey("vida"));
		assertEquals (true,_pontosUsuario.containsValue("19"));
		
		assertEquals (true,_pontosUsuario.containsKey("energia"));
		assertEquals (true,_pontosUsuario.containsValue("30"));

		assertEquals (true,_pontosUsuario.containsKey("comentarios"));
		assertEquals (true,_pontosUsuario.containsValue("80"));

		assertEquals (true,_pontosUsuario.containsKey("curtida"));
		assertEquals (true,_pontosUsuario.containsValue("28"));
		
		// Conferindo a recuperação do Placar e do  Ranking de Pontos do Tipo  "vida" de um Mesmo Usuario
		
		List<RankingPontos> _rankingDecrescente = _placar.retornarRankingUsuariosDeUmTipoDePonto ("vida");
		assertEquals (false,_rankingDecrescente.isEmpty());
		assertEquals (1,_rankingDecrescente.size());
		exibeRankingPontos(_rankingDecrescente);
		assertEquals (true,_rankingDecrescente.get(0).getUsuario().equals("francisco"));
		assertEquals (19,_rankingDecrescente.get(0).getPontos());

		// Conferindo a recuperação do Placar e do  Ranking de Pontos do Tipo  "estrela"
		
		_rankingDecrescente = _placar.retornarRankingUsuariosDeUmTipoDePonto ("estrela");
		assertEquals (false,_rankingDecrescente.isEmpty());
		assertEquals (1,_rankingDecrescente.size());
		exibeRankingPontos(_rankingDecrescente);
		assertEquals (true,_rankingDecrescente.get(0).getUsuario().equals("francisco"));
		assertEquals (100,_rankingDecrescente.get(0).getPontos());
	}
 
	@Test
	void testSet002() throws Exception {
   	
		// Conferindo inclusão de diversas pontuações de diversos usuarios - acrescendo ao Set Anterior

		_placar = new Placar (_mock);
		_placar.registrarPontosDoUsuario(new PontuacaoUsuarios("fernanda", "estrela", 50));
		_placar.registrarPontosDoUsuario(new PontuacaoUsuarios("fernanda", "estrela", 100));
		_placar.registrarPontosDoUsuario(new PontuacaoUsuarios("fernanda", "estrela", -140));
		_placar.registrarPontosDoUsuario(new PontuacaoUsuarios("antonio", "vida", 20));
		_placar.registrarPontosDoUsuario(new PontuacaoUsuarios("antonio", "curtida", 45));
		_placar.registrarPontosDoUsuario(new PontuacaoUsuarios("antonio", "moeda", 30));
		_placar.registrarPontosDoUsuario(new PontuacaoUsuarios("pedro", "vida", 19));
		_placar.registrarPontosDoUsuario(new PontuacaoUsuarios("pedro", "vida", 20));
		_placar.registrarPontosDoUsuario(new PontuacaoUsuarios("toco", "energia", 30));
		_placar.registrarPontosDoUsuario(new PontuacaoUsuarios("rodrigo", "curtida", 28));
		_placar.registrarPontosDoUsuario(new PontuacaoUsuarios("maria", "moeda", 44));
		_placar.registrarPontosDoUsuario(new PontuacaoUsuarios("maria", "estrela", 100));
		_placar.registrarPontosDoUsuario(new PontuacaoUsuarios("rafael", "vida", -50));
		
		// Conferindo a recuperação dos Pontos da Fernanda

		HashMap<String,String> _pontosUsuario = _placar.retornarPontosDoUsuario("fernanda");
		assertEquals (false,_pontosUsuario.isEmpty());
		assertEquals (true,_pontosUsuario.containsKey("estrela"));  
		assertEquals (true,_pontosUsuario.containsValue("10"));          // 50 + 100 - 140 = 10 (saldo de pontos p/estrela) 

		// Conferindo a recuperação dos Pontos do Antonio 

		_pontosUsuario = _placar.retornarPontosDoUsuario("antonio");
		assertEquals (false,_pontosUsuario.isEmpty());
		assertEquals (true,_pontosUsuario.containsKey("vida"));
		assertEquals (true,_pontosUsuario.containsValue("20"));    
		assertEquals (true,_pontosUsuario.containsKey("curtida"));
		assertEquals (true,_pontosUsuario.containsValue("45"));
		assertEquals (true,_pontosUsuario.containsKey("moeda"));
		assertEquals (true,_pontosUsuario.containsValue("30"));

		// Conferindo a recuperação dos Pontos do Pedro 

		_pontosUsuario = _placar.retornarPontosDoUsuario("pedro");
		assertEquals (false,_pontosUsuario.isEmpty());
		assertEquals (true,_pontosUsuario.containsKey("vida"));
		assertEquals (true,_pontosUsuario.containsValue("39"));

		// Conferindo a recuperação dos Pontos do Toco 

		_pontosUsuario = _placar.retornarPontosDoUsuario("toco");
		assertEquals (false,_pontosUsuario.isEmpty());
		assertEquals (true,_pontosUsuario.containsKey("energia"));
		assertEquals (true,_pontosUsuario.containsValue("30"));

		// Conferindo a recuperação dos Pontos do Rodrigo

		_pontosUsuario = _placar.retornarPontosDoUsuario("rodrigo");
		assertEquals (false,_pontosUsuario.isEmpty());
		assertEquals (true,_pontosUsuario.containsKey("curtida"));
		assertEquals (true,_pontosUsuario.containsValue("28"));

		// Conferindo a recuperação dos Pontos da Maria

		_pontosUsuario = _placar.retornarPontosDoUsuario("maria");
		assertEquals (false,_pontosUsuario.isEmpty());
		assertEquals (true,_pontosUsuario.containsKey("moeda"));
		assertEquals (true,_pontosUsuario.containsValue("44"));
		assertEquals (true,_pontosUsuario.containsKey("estrela"));
		assertEquals (true,_pontosUsuario.containsValue("100"));
	
		// Conferindo a recuperação dos Pontos do Francisco inseridos no TesteSet001
		
		_pontosUsuario = _placar.retornarPontosDoUsuario("francisco");
		assertEquals (false,_pontosUsuario.isEmpty());
		
		assertEquals (true,_pontosUsuario.containsKey("moeda"));
		assertEquals (true,_pontosUsuario.containsValue("44"));
		
		assertEquals (true,_pontosUsuario.containsKey("estrela"));
		assertEquals (true,_pontosUsuario.containsValue("100"));       // 50 + 100 - 140 = 10 (saldo de pontos p/estrela)
		
		assertEquals (true,_pontosUsuario.containsKey("vida"));
		assertEquals (true,_pontosUsuario.containsValue("19"));
		
		assertEquals (true,_pontosUsuario.containsKey("energia"));
		assertEquals (true,_pontosUsuario.containsValue("30"));
		
		assertEquals (true,_pontosUsuario.containsKey("comentarios"));
		assertEquals (true,_pontosUsuario.containsValue("80"));
		
		assertEquals (true,_pontosUsuario.containsKey("curtida"));
		assertEquals (true,_pontosUsuario.containsValue("28"));
			 
	
		// Conferindo a recuperação do Placar e do Ranking de Pontos do Tipo  "vida"
		
		List<RankingPontos> _rankingDecrescente = _placar.retornarRankingUsuariosDeUmTipoDePonto ("vida");
		assertEquals (false,_rankingDecrescente.isEmpty());
		assertEquals (4,_rankingDecrescente.size());
		exibeRankingPontos(_rankingDecrescente);
		assertEquals (true,_rankingDecrescente.get(0).getUsuario().equals("pedro"));
		assertEquals (39,_rankingDecrescente.get(0).getPontos());
		assertEquals (true,_rankingDecrescente.get(1).getUsuario().equals("antonio"));
		assertEquals (20,_rankingDecrescente.get(1).getPontos());
		assertEquals (true,_rankingDecrescente.get(2).getUsuario().equals("francisco"));
		assertEquals (19,_rankingDecrescente.get(2).getPontos());
		assertEquals (true,_rankingDecrescente.get(3).getUsuario().equals("rafael"));
		assertEquals (-50,_rankingDecrescente.get(3).getPontos());

		// Conferindo a recuperação do Placar e do  Ranking de Pontos do Tipo  "estrela"
		
		_rankingDecrescente = _placar.retornarRankingUsuariosDeUmTipoDePonto ("estrela");
		assertEquals (false,_rankingDecrescente.isEmpty());
		assertEquals (3,_rankingDecrescente.size());
		exibeRankingPontos(_rankingDecrescente);
		assertEquals (true,_rankingDecrescente.get(0).getUsuario().equals("francisco"));
		assertEquals (100,_rankingDecrescente.get(0).getPontos());
		assertEquals (true,_rankingDecrescente.get(1).getUsuario().equals("maria"));
		assertEquals (100,_rankingDecrescente.get(1).getPontos());
		assertEquals (true,_rankingDecrescente.get(2).getUsuario().equals("fernanda"));
		assertEquals (10,_rankingDecrescente.get(2).getPontos());

		// Conferindo a recuperação do Placar e do  Ranking de Pontos do Tipo  "curtida"
		
		_rankingDecrescente = _placar.retornarRankingUsuariosDeUmTipoDePonto ("curtida");
		assertEquals (false,_rankingDecrescente.isEmpty());
		assertEquals (3,_rankingDecrescente.size());
		exibeRankingPontos(_rankingDecrescente);
		assertEquals (true,_rankingDecrescente.get(0).getUsuario().equals("antonio"));
		assertEquals (45,_rankingDecrescente.get(0).getPontos());
		assertEquals (true,_rankingDecrescente.get(1).getUsuario().equals("francisco"));
		assertEquals (28,_rankingDecrescente.get(1).getPontos());
		assertEquals (true,_rankingDecrescente.get(2).getUsuario().equals("rodrigo"));
		assertEquals (28,_rankingDecrescente.get(2).getPontos());

		// Conferindo a recuperação do Placar e do  Ranking de Pontos do Tipo  "moeda"
		
		_rankingDecrescente = _placar.retornarRankingUsuariosDeUmTipoDePonto ("moeda");
		assertEquals (false,_rankingDecrescente.isEmpty());
		assertEquals (3,_rankingDecrescente.size());
		exibeRankingPontos(_rankingDecrescente);
		assertEquals (true,_rankingDecrescente.get(0).getUsuario().equals("francisco"));
		assertEquals (44,_rankingDecrescente.get(0).getPontos());
		assertEquals (true,_rankingDecrescente.get(1).getUsuario().equals("maria"));
		assertEquals (44,_rankingDecrescente.get(1).getPontos());
		assertEquals (true,_rankingDecrescente.get(2).getUsuario().equals("antonio"));
		assertEquals (30,_rankingDecrescente.get(2).getPontos());
	}
	
	private void exibeListaDePontuacao (PontuacaoUsuarios p) {	
		System.out.print("(1) " + p.getUsuario() + ", " + p.getTipoPonto() + ", " + p.getPontos() + "\n");
	}
	
	private void exibePontuacoesDeUmUsuario (HashMap<String,String> h) {	
		System.out.println ("\n\n TESTANDO PLACAR ");
		for (String i : h.keySet()) {
			System.out.print("\n  key: " + i + " value: " + h.get(i));
		}
	}
	
	private void exibePontuacao (LinkedList<PontuacaoUsuarios> pu) {		
		System.out.println ("\n\n TESTANDO PLACAR  ");		
		for (int i=0; i < pu.size(); i++) {			
			exibeListaDePontuacao (new PontuacaoUsuarios(pu.get(i).getUsuario(),pu.get(i).getTipoPonto(),pu.get(i).getPontos()));
		}
	}
	private void exibeRankingPontos (List<RankingPontos> r) {		
		System.out.println ("\n\n TESTANDO PLACAR  ");
		for (int i=0; i < r.size(); i++) {			
			System.out.println (" tp= " + r.get(i).getUsuario() + " p= " + r.get(i).getPontos());
		}
	}

}


