
package courseraita;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;

public class MockArmazenamento implements IArmazenamento {

	private static LinkedList<PontuacaoUsuarios> _pontuacaoUsuarios = new LinkedList<PontuacaoUsuarios>();
	private static HashSet<String>         _diferentesTiposDePontos = new HashSet<String>();

	@Override
	public void armazenarPontuacaoDeUmUsuario(PontuacaoUsuarios p) {
		
		_pontuacaoUsuarios.add (p);		
		if ( p.getPontos() != 0 && !_diferentesTiposDePontos.contains(p._tipoPonto))			
			_diferentesTiposDePontos.add (p._tipoPonto);
	}

	@Override
	public HashSet<String> retornarTiposDePontosJaRegistrados() {		
		return _diferentesTiposDePontos;
	}

	@Override
	public LinkedList<PontuacaoUsuarios> retornarUsuariosComAlgumTipodePonto() throws Exception {
		if (_pontuacaoUsuarios.isEmpty())
			throw new Exception ("Error: Arquivo Vazio - Nenhuma Pontuacao de Usuarios");
		
		return _pontuacaoUsuarios;
	}
 
	@Override
	public int recuperarTotaisDePontosDeUmTipoDeUmUsuario(String tipoPonto, String usuario) throws Exception {	
		if (_pontuacaoUsuarios.isEmpty())
			throw new Exception ("Error: Arquivo Vazio - Nenhuma Pontuacao de Usuarios");

		int _totalPontos = 0;
		boolean usuarioNaoEncontrado = true;	
		for (int i = 0; i < _pontuacaoUsuarios.size(); i++) {
		
			if (((String) _pontuacaoUsuarios.get(i).getUsuario()).contains(usuario)) {
				usuarioNaoEncontrado = false;
			
				if (((String) _pontuacaoUsuarios.get(i).getTipoPonto()).contains(tipoPonto)) 
					_totalPontos += (int) _pontuacaoUsuarios.get(i).getPontos();
			}
		}
		if (usuarioNaoEncontrado) {
			throw new Exception ("Error: Usuario Nao Encontrado!!! ");
		}
		return _totalPontos;
	}

}

