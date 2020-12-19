package courseraita;

import java.io.IOException;
import java.util.LinkedList;

public interface IDriverArquivo {
	
	public LinkedList<PontuacaoUsuarios> cargaEmCacheApartirArquivo() throws Exception;
	
	public void persisteDadosNoArquivo(PontuacaoUsuarios p) throws Exception, IOException;
	
	public void deleteArquivo() throws Exception;
	
}
