
package courseraita;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class Placar {

	private  IArmazenamento _armazenamento; 

	public Placar (IArmazenamento a) {				
		this._armazenamento = a;
	}

	public void registrarPontosDoUsuario(PontuacaoUsuarios u) throws IOException, Exception {	
		_armazenamento.armazenarPontuacaoDeUmUsuario(u);
	}

	public HashMap<String,String> retornarPontosDoUsuario (String usuario) throws Exception {

		LinkedList<PontuacaoUsuarios> _pu = _armazenamento.retornarUsuariosComAlgumTipodePonto();
		HashMap<String,String>  _novaLista = new HashMap<String,String>();
		for (int i = 0; i < _pu.size(); i++) {	

			if (_pu.get(i).getPontos() != 0   && 
				_pu.get(i).getUsuario().contains(usuario))  { 		

				if ( _novaLista.containsKey( _pu.get(i).getTipoPonto() )) {
					
					int np = _pu.get(i).getPontos() + Integer.parseInt(_novaLista.get(_pu.get(i).getTipoPonto()));					
					_novaLista.put(	_pu.get(i).getTipoPonto(),Integer.toString(np));

				} else {
					_novaLista.put(_pu.get(i).getTipoPonto(),_pu.get(i).getPontosEmString());
				}
			}
		}
		if (_novaLista.isEmpty()) {
			throw new Exception ("Usuario [" + usuario + "] Sem Pontuacao - Nada a Fazer !!!");
		}
		return _novaLista;  	
	}

	public List<RankingPontos> retornarRankingUsuariosDeUmTipoDePonto (String tipoPonto) throws Exception {	

		ArrayList<RankingPontos> _ranking = getRankingNaoClassificado (tipoPonto);

		Comparator<RankingPontos> compareByPoints = 
				Comparator.comparing(RankingPontos::getPontos).reversed().
				thenComparing(RankingPontos::getUsuario);

		List<RankingPontos> _sortedRanking = 
				_ranking.stream().sorted(compareByPoints).collect(Collectors.toList());

		return _sortedRanking;
	}

	private ArrayList<RankingPontos> getRankingNaoClassificado(String tipoPonto) throws Exception {

		HashMap<String, String> _mapaTemporario = sumarizaPontosUsuariosPorTipo(tipoPonto);
		LinkedList<PontuacaoUsuarios> _pontuacaoUsuarios = new LinkedList<PontuacaoUsuarios>();

		for (Entry<String, String> map : _mapaTemporario.entrySet()) {

			String user =  map.getKey();
			int points = Integer.parseInt(map.getValue());
			_pontuacaoUsuarios.add(	new PontuacaoUsuarios ( user, tipoPonto, points));
		}

		ArrayList<RankingPontos> _list = converteListaPontosParaRanking(tipoPonto, _pontuacaoUsuarios);		
		return _list;
	}

	private ArrayList<RankingPontos> converteListaPontosParaRanking(
			String tipoPonto, LinkedList<PontuacaoUsuarios> _pontuacaoUsuarios) {

		ArrayList<RankingPontos> _list = new ArrayList<>();
		for (int i=0; i < _pontuacaoUsuarios.size(); i++ ) {

			String name       = (String) _pontuacaoUsuarios.get(i).getUsuario();
			String typePoints = (String) _pontuacaoUsuarios.get(i).getTipoPonto();
			int points        = (int)  _pontuacaoUsuarios.get(i).getPontos();

			if (typePoints.contains(tipoPonto)) 	
				_list.add( new RankingPontos(name, points));
		}
		return _list;
	}

	private HashMap<String, String> sumarizaPontosUsuariosPorTipo(String tipoPonto) throws Exception {

		LinkedList<PontuacaoUsuarios> _ptsSumarizar = _armazenamento.retornarUsuariosComAlgumTipodePonto();
		HashMap<String,String>  _mapaTemporario = new HashMap<String,String>();

		for ( int i =0;  i < _ptsSumarizar.size(); i++) {

			String nomeUsuario = _ptsSumarizar.get(i).getUsuario();
			String tipo        = _ptsSumarizar.get(i).getTipoPonto();
			int pontos         = _ptsSumarizar.get(i).getPontos();

			if (pontos != 0 && tipo.contains(tipoPonto) ) {
				if ( _mapaTemporario.containsKey( nomeUsuario )) {

					int ptsMapa =  Integer.parseInt(_mapaTemporario.get(nomeUsuario));
					int np = pontos + ptsMapa;
					_mapaTemporario.put(nomeUsuario, Integer.toString(np));

				} else {
					_mapaTemporario.put(nomeUsuario, Integer.toString(pontos));
				}
			}
		}
		if (_mapaTemporario.isEmpty())
			throw new Exception ("Error: Nenhum Ponto Registrado ainda - Nenhum Ranking!!!");

		return _mapaTemporario;
	}

}


