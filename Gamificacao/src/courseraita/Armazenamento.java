
package courseraita;

import java.io.IOException;
import java.util.HashSet;
import java.util.LinkedList;

public class Armazenamento implements IArmazenamento {

	private static IDriverArquivo driverArq;	
	private static LinkedList<PontuacaoUsuarios> _pontuacaoUsuarios = new LinkedList<PontuacaoUsuarios>();
	private static HashSet<String> _diferentesTiposDePontos = new HashSet<String>();

	public Armazenamento (IDriverArquivo d) throws Exception, IOException  {

		driverArq = d;		
		_pontuacaoUsuarios = driverArq.cargaEmCacheApartirArquivo();	
		
		if ( _pontuacaoUsuarios.size() != 0) {
			
			_diferentesTiposDePontos.clear();
			
			for (int i=0; i < _pontuacaoUsuarios.size(); i++) {		
				
				if ( _pontuacaoUsuarios.get(i).getPontos() != 0 && 
						
					!_diferentesTiposDePontos.contains(_pontuacaoUsuarios.get(i).getTipoPonto()))					
  				    _diferentesTiposDePontos.add (_pontuacaoUsuarios.get(i).getTipoPonto());
			}
		}		
	}

	@Override
	public void armazenarPontuacaoDeUmUsuario( PontuacaoUsuarios p) 
			                                       throws IOException, Exception {
		
		_pontuacaoUsuarios.add (p);		
		
		if ( p.getPontos() != 0 && !_diferentesTiposDePontos.contains(p._tipoPonto))
			
			_diferentesTiposDePontos.add (p._tipoPonto);
		
		driverArq.persisteDadosNoArquivo(p);		
	}

	@Override
	public HashSet<String> retornarTiposDePontosJaRegistrados() throws Exception {		
		
		driverArq.cargaEmCacheApartirArquivo();
		
		return _diferentesTiposDePontos;
	}

	@Override
	public LinkedList<PontuacaoUsuarios> retornarUsuariosComAlgumTipodePonto() 
			                                                         throws Exception {
		
		driverArq.cargaEmCacheApartirArquivo();
		
		if (_pontuacaoUsuarios.isEmpty())
			
			throw new Exception ("Error: Arquivo Vazio - Nenhuma Pontuacao de Usuarios");		
		
		return _pontuacaoUsuarios;
	}
 
	@Override
	public int recuperarTotaisDePontosDeUmTipoDeUmUsuario(
			                    String tipoPonto, String usuario) throws Exception {
		
		driverArq.cargaEmCacheApartirArquivo();
		
		if (_pontuacaoUsuarios.isEmpty())
			driverArq.cargaEmCacheApartirArquivo();
		
		int _totalPontos = 0;
		boolean usuarioNaoEncontrado = true;	
		
		for (int i = 0; i < _pontuacaoUsuarios.size(); i++) {	
			
			if (((String) _pontuacaoUsuarios.get(i).getUsuario()).contains(usuario)) {	
				
				usuarioNaoEncontrado = false;				
				
				if (((String) _pontuacaoUsuarios.get(i).getTipoPonto()).contains(tipoPonto)) 
					
					_totalPontos += (int)    _pontuacaoUsuarios.get(i).getPontos();
			}
		}
		if (usuarioNaoEncontrado) {			
			throw new Exception ("Error: Usuario Nao Encontrado!!! ");
		}
		return _totalPontos;
	}

}

