
package courseraita;
import java.io.IOException;
import java.util.HashSet;
import java.util.LinkedList;

public interface IArmazenamento {

	public void armazenarPontuacaoDeUmUsuario (PontuacaoUsuarios u)  throws IOException, Exception;
	
	public HashSet<String> retornarTiposDePontosJaRegistrados () throws Exception;
	
	public LinkedList<PontuacaoUsuarios> retornarUsuariosComAlgumTipodePonto () throws Exception;
	
	public int recuperarTotaisDePontosDeUmTipoDeUmUsuario (String tipoPonto, String usuario) throws Exception;

}

