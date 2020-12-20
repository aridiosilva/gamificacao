package courseraita;

import java.io.IOException;
import java.sql.*;
import java.util.LinkedList;

public class DriverMySQL8Database implements IDriverArquivo {
	
	private Statement stmt;
	private ResultSet rs;
	private Connection con;
	private DatabaseMetaData md;
	private static boolean firstTime = true;
	
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
		
		LinkedList<PontuacaoUsuarios> _pontuacaoCache = new LinkedList<PontuacaoUsuarios>();
		try {
			preparaConexaoComDatabase();
			veSeTabelaPontuacaoJaExiste();			
			String sqlSelectCmd = "SELECT * FROM pontuacao ORDER BY regId ASC";
			rs = stmt.executeQuery(sqlSelectCmd);
			int numRegsLidos = 0;
			while (rs.next() ) {				
				_pontuacaoCache.add(new PontuacaoUsuarios( 
						rs.getNString("usuario"),
						rs.getNString("tipoPontos"),
						Integer.parseInt(rs.getNString("numPontos"))));				
				numRegsLidos++;
			}
			con.close();
			if (numRegsLidos == 0)
				System.out.println(" Tabela Pontuacao VAZIA do Database MySQL - Nada a colocar em CACHE!!!"); 
		} 
		catch (SQLException ex) 
		{ 
			ex.printStackTrace(); 
			throw new Exception ("Problema em I/o na Tabela Pontuacao Database MySQL");
		}
		return _pontuacaoCache; 
	} 
	@Override
	public void persisteDadosNoArquivo(PontuacaoUsuarios p) 
			         throws SQLException, ClassNotFoundException {		
		try { 
			preparaConexaoComDatabase();			
			veSeTabelaPontuacaoJaExiste();
			String sqlCmdInsercaoNoDatabase = 
					"INSERT INTO pontuacao (usuario,tipoPontos,numPontos) VALUES ('" +
					p.getUsuario() + "','" + p.getTipoPonto() + "','" +
					String.valueOf(p.getPontos()) + "')";			
			System.out.println("===> " + "INSERT INTO pontuacao (usuario,tipoPontos, numPontos) VALUES ('" +
					p.getUsuario() + "','" + p.getTipoPonto() + "','" +
					String.valueOf(p.getPontos()) + "')");			
			stmt.executeUpdate(sqlCmdInsercaoNoDatabase);
			con.close();
		} 
		catch (SQLException ioe) { 
			System.out.println(ioe); 
			System.out.println("problema em persistir a nova pontuacao no Database");
		} 
    }	
	@Override
	public void deleteArquivo() throws Exception {
		emptyTablePontuacao();
	}
	private void veSeTabelaPontuacaoJaExiste() throws SQLException {
		try{
			String sqlCmdCreate =
					"CREATE TABLE IF NOT EXISTS pontuacao (" +
					"regId      INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY," +
					"usuario    CHAR(25) NOT NULL," +
					"tipoPontos CHAR(25) NOT NULL," +
					"numPontos  CHAR(10) NOT NULL," +
					"PRIMARY KEY (regID))";			
			stmt.executeUpdate(sqlCmdCreate);

		} catch (SQLException e){
			System.out.print(e);
			System.out.println("problema na Criação da tabela de pontuacao");
		}		
	}
	private void emptyTablePontuacao() throws Exception {
		try{
			preparaConexaoComDatabase();	
			stmt.executeUpdate("TRUNCATE TABLE pontuacao");		
			System.out.println("Tabela Pontuacao Esvaziada por TRUNCATE");
			con.close();
			
		} catch (Exception e){			
			System.out.print(e);
			System.out.println("problema no TRUNCATE da tabela de pontuacao");
		}
	}
	private void getSomeMetaDataDoDatabase() throws SQLException {
		if (firstTime) {
			 md = con.getMetaData();  
			 System.out.println("DatabaseProductName: " +  md.getDatabaseProductName() );
			 System.out.println("DatabaseProductVersion(: " +  md.getDatabaseProductVersion());
			 System.out.println("DriverName: " +  md.getDriverName());
			 System.out.println("URL: " +  md.getURL());
			 System.out.println("UserName: " +  md.getUserName());
			 System.out.println("AlterTableWithAddColumn: " +  md.supportsAlterTableWithAddColumn());
			 System.out.println("AlterTableWithDropColumn: " +  md.supportsAlterTableWithDropColumn());
			 System.out.println("ANSI92FullSQL: " +  md.supportsANSI92FullSQL());
			 System.out.println("BatchUpdates: " +  md.supportsBatchUpdates());
			 System.out.println("MixedCaseIdentifiers: " +  md.supportsMixedCaseIdentifiers());
			 System.out.println("MultipleTransactions: " +  md.supportsMultipleTransactions());
			 System.out.println("PositionedDelete: " +  md.supportsPositionedDelete());
			 System.out.println("PositionedUpdate: " +   md.supportsPositionedUpdate());
			 System.out.println("SchemasInDataManipulation: " +  md.supportsSchemasInDataManipulation());
			 System.out.println("Transactions: " +  md.supportsTransactions());
			 System.out.println("ResultSetType..TYPE_SCROLL_INSENSITIVE: " +  md.supportsResultSetType(ResultSet.TYPE_SCROLL_INSENSITIVE));
			 System.out.println("ResultSet.TYPE_SCROLL_SENSITIVE: " +  md.supportsResultSetType(ResultSet.TYPE_SCROLL_SENSITIVE));
			 System.out.println("ResultSet.TYPE_SCROLL_INSENSITIVE: " +  md.insertsAreDetected(ResultSet.TYPE_SCROLL_INSENSITIVE));
			 System.out.println("ResultSet.TYPE_SCROLL_INSENSITIVE: " +  md.updatesAreDetected(ResultSet.TYPE_SCROLL_INSENSITIVE));
			 firstTime = false;			
		}
	}
	private void preparaConexaoComDatabase ()
			throws ClassNotFoundException, SQLException {

		//Register the JDBC driver for MySQL.
		Class.forName("com.mysql.cj.jdbc.Driver");  

		String urlDatabaseServer = "jdbc:mysql://localhost:3306/sakila?useTimezone=true&serverTimezone=UTC";

		//Get connection to database
		String pw = "";		
		con = DriverManager.getConnection(urlDatabaseServer,"root", pw);

		// Mostra a URL e Conexão com o Database
		System.out.println("URL: " + urlDatabaseServer + "Connection: " + con);

		//Get a Statement object
		stmt = con.createStatement();
		
		getSomeMetaDataDoDatabase();
		
	}

}
