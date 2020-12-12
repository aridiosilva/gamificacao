
package courseraita;

public class RankingPontos {

	String _usuario;
	int _pontos;

	public  RankingPontos (String u, int np) {
		System.out.println("Item do Ranking -> " + u + " , "  +  np);
		this._usuario   = u.toLowerCase();
		this._pontos    = np;
	}
	public String getUsuario () {		
		return _usuario;
	}
	public int getPontos () {		
		return _pontos; 
	}
}

