package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import utility.ConnectionManager;
import utility.EccezioneMuseo;
import dominio.Artista;
import dominio.EccezionePrecondizioni;

import dominio.TipoLinkRealizzazione;

import dominio.OperaArte.OperaArte;

public class OperaArteDAO {
	
	private OperaArteDAO() {}
	
	private static final String LOAD = "SELECT * FROM OperaArte WHERE codice = ?";


	
	public static void load(OperaArte opera) throws EccezioneMuseo {
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = ConnectionManager.getConnection();
			stmt = conn.prepareStatement(LOAD);
			stmt.setString(1, opera.getCodice());
			conn.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
			rs = stmt.executeQuery();
			if (!rs.next()) {
				throw new EccezioneMuseo("Opera inesistente");
			}
			else {
				opera.addLinkRealizzazione(new TipoLinkRealizzazione(opera, new Artista(rs.getString("codiceArtista")) ) );
			}
			
		}
		
		catch (ClassNotFoundException cnfe) {
			throw new EccezioneMuseo(cnfe.getMessage());
		}
		catch (SQLException sqle) {
			throw new EccezioneMuseo(sqle.getMessage());
		}
		catch (EccezionePrecondizioni ep) {
			throw new EccezioneMuseo(ep.toString());
		}
		
		finally {
			if (conn != null) {
				try {
					if (rs != null) rs.close();
					if (stmt != null) stmt.close();
					conn.setTransactionIsolation(Connection.TRANSACTION_REPEATABLE_READ);
					conn.close();
				}
				catch (SQLException sqle) {
					throw new EccezioneMuseo(sqle.getMessage());
				}
			}
		}
	}
	
	

}
