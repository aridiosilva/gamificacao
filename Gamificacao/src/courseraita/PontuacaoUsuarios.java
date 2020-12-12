
package courseraita;

public class PontuacaoUsuarios {

	String _usuario;
	String _tipoPonto;
	String _pontos;

	public  PontuacaoUsuarios (String u, String tp, int p) {
		
		System.out.println("-> " + u + " , " + tp  + " , " +  p);
		
		this._usuario   = u.toLowerCase();
		this._tipoPonto = tp.toLowerCase();
		this._pontos    = Integer.toString(p);
	}

	public String getUsuario () {		
		return _usuario;
	}
	public String getTipoPonto () {		
		return _tipoPonto;
	}
	public int getPontos () {		
		return Integer.parseInt(_pontos); 
	}
	public String getPontosEmString () {		
		return _pontos; 
	}

}
 
