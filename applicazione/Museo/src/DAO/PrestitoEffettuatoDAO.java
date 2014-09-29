package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import utility.ConnectionManager;
import utility.EccezioneMuseo;
import dominio.EccezioneCardMin;

import dominio.PrestitoEffettuato;

import dominio.Museo_Ente.Museo_Ente;



public class PrestitoEffettuatoDAO {
	
	
	private PrestitoEffettuatoDAO() {}
	
	private static final String LOAD = "SELECT * FROM PrestitoEffettuato WHERE codiceOggetto = ? and dataInizio = ?";	
	
	public static void load(PrestitoEffettuato prestito) throws EccezioneMuseo {
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = ConnectionManager.getConnection();
			stmt = conn.prepareStatement(LOAD);
			stmt.setString(1, prestito.getLinkPermanentePrestito().getOggetto().getCodice());
			stmt.setDate(2, prestito.getDataInizio());
			conn.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
			rs = stmt.executeQuery();
			if (!rs.next()) {
				throw new EccezioneMuseo("PrestitoEffettuato inesistente");
			}
			else {
				prestito.setDataFine(rs.getDate("dataInizio"));
				prestito.addEntePrestito(new Museo_Ente(rs.getString("codiceMuseo")));
			}
			
		}
		
		catch (ClassNotFoundException cnfe) {
			throw new EccezioneMuseo(cnfe.getMessage());
		}
		catch (SQLException sqle) {
			throw new EccezioneMuseo(sqle.getMessage());
		}
		catch (EccezioneCardMin ecm) {
			throw new EccezioneMuseo(ecm.toString());
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
