package courseraita;

import java.io.EOFException;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.sql.*;
import java.util.LinkedList;

public class DriverMySQL8Database implements IDriverArquivo {
	
	private static File file = new File( "pontuacao.txt" );
	private final  String SEPARADOR_CAMPOS = "!";
	
	Statement stmt;
	ResultSet rs;
	Connection con;
	
	public DriverMySQL8Database (boolean deletarFile) throws Exception {
		
		if (deletarFile) {
			try {
				emptyTablePontuacao();
			}
			catch (IOException ioe) { 
				System.out.println(ioe); 
			} 
		}
	}

	@Override
	public LinkedList<PontuacaoUsuarios> cargaEmCacheApartirArquivo() throws Exception {
		
		preparaConexaoComDatabase();	
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
			
				con.close();
			}
			else System.out.println(" Tabela Pontuacao VAZIA do Database MySQL - Nada a colocar em CACHE!!!"); 
		} 
		catch (EOFException ex) 
		{ 
			ex.printStackTrace(); 
			throw new Exception ("EOF da Tabela Pontuacao do Database MySQL");
		} 		
		catch (IOException ex) 
		{ 
			ex.printStackTrace(); 
			throw new Exception ("Problema em I/o na Tabela Pontuacao Database MySQL");
		}
		return _pontuacaoCache; 
	}
 
	@Override
	public void persisteDadosNoArquivo(PontuacaoUsuarios p) throws Exception, IOException {		
		try { 
			preparaConexaoComDatabase();	
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

			
			con.close();
		} 
		catch (IOException ioe) { 
			System.out.println(ioe); 
		} 
    }
	
	@Override
	public void deleteArquivo() throws Exception {
		emptyTablePontuacao();
	}

	private void emptyTablePontuacao() throws Exception {
		try{
			preparaConexaoComDatabase();	
			stmt.executeUpdate("DROP TABLE pontuacao");		
			stmt.executeUpdate(
			     	"CREATE TABLE pontuacao(regId int unsigned auto_increment," +
				    "usuario char(15) not null, "+
			     	"tipoPonto char (15) not nul," +
				    "numPontos char(15) not null)");			
			System.out.println("Tabela Pontuacao Esvaziada por DROP e CREATE");
			con.close();
			
		} catch (Exception e){
			
			System.out.print(e);
			System.out.println("problema no DROP e/ou CREATE da tabela de pontuacao");
		}
	}

	private void preparaConexaoComDatabase ()
			throws ClassNotFoundException, SQLException {

		//Register the JDBC driver for MySQL.
		Class.forName("com.mysql.cj.jdbc.Driver");  

		//Define URL of database server
		String url = "jdbc:mysql://localhost:3306/sakila";

		//Get connection to database for user root and no password
		Connection con = DriverManager.getConnection(url,"root", "");

		//Display URL and connection information
		System.out.println("URL: " + url + "Connection: " + con);

		//Get a Statement object
		stmt = con.createStatement();
	}

}
