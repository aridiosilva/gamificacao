
package courseraita;

import java.io.EOFException;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.LinkedList;

public class DriverArquivoTXT implements IDriverArquivo {
	
	private static File file = new File( "pontuacao.txt" );
	private final  String SEPARADOR_CAMPOS = "!";
	
	public DriverArquivoTXT (boolean deletarArquivo) throws Exception {
		
		if (deletarArquivo) {
			
			deleteArquivo();
		}
	}
	
	public LinkedList<PontuacaoUsuarios> cargaEmCacheApartirArquivo() throws Exception {
		
		LinkedList<PontuacaoUsuarios> _pontuacaoCache = new LinkedList<PontuacaoUsuarios>();
		try {
			if (file.exists()) { 
				
				RandomAccessFile fileTXT = new RandomAccessFile(file, "rw");				
				String bufferLeitura; 
				
				while (fileTXT.getFilePointer() < fileTXT.length()) { 
					
					bufferLeitura = fileTXT.readLine(); 
					String[] splits = bufferLeitura.split("!");
					_pontuacaoCache.add(new PontuacaoUsuarios (splits[0], splits[1] , Integer.parseInt(splits[2] )));
				} 
				fileTXT.close(); 
			}
			else System.out.println(" Arquivo TXT VAZIO - Nada a colocar em CACHE!!!"); 
		} 
		catch (EOFException ex) 
		{ 
			ex.printStackTrace(); 
			throw new Exception ("Fim de Arquivo TXT");
		} 		
		catch (IOException ex) 
		{ 
			ex.printStackTrace(); 
			throw new Exception ("Problema em I/o no Arquivo TXT");
		}
		return _pontuacaoCache; 
	}
 
	public void persisteDadosNoArquivo(PontuacaoUsuarios p) throws Exception, IOException {		
		try { 
			File file = new File("pontuacao.txt"); 
			
			if (!file.exists()) { 
				file.createNewFile(); 
			} 
			
			RandomAccessFile fileTXT = new RandomAccessFile(file, "rw"); 
			String buffeGravacao = p.getUsuario() + SEPARADOR_CAMPOS + p.getTipoPonto() 
			                       + SEPARADOR_CAMPOS + String.valueOf(p.getPontos() + SEPARADOR_CAMPOS);
			
			fileTXT.seek(fileTXT.length());
			fileTXT.writeBytes(buffeGravacao); 
			fileTXT.writeBytes(System.lineSeparator()); 
			fileTXT.close(); 
		} 
		catch (IOException ioe) { 
			System.out.println(ioe); 
		} 
    }

	public void deleteArquivo() throws Exception {

		if (file.exists() ) {
			  System.out.println("\n size = " + (int) file.length() + " path= " + file.getAbsolutePath() );
			  if(file.delete()) 
			      System.out.println("File deleted successfully"); 
			  else
			      System.out.println("Failed to delete the file");
		}
	}
}

