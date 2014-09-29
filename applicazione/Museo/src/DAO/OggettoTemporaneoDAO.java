package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import utility.ConnectionManager;
import utility.EccezioneMuseo;
import dominio.EccezionePrecondizioni;
import dominio.TipoLinkPrestitoRicevuto;
import dominio.Museo_Ente.Museo_Ente;
import dominio.OggettoTemporaneo.OggettoTemporaneo;


public class OggettoTemporaneoDAO {
	
	
	private OggettoTemporaneoDAO() {}
	
	private static final String LOAD = "SELECT * FROM OggettoTemporaneo WHERE codice = ?";
	private static final String LOAD_PRESTITO_RICEVUTO = "SELECT * FROM PrestitoRicevuto WHERE codiceOggetto = ?";
	
	
	public static void load(OggettoTemporaneo oggetto) throws EccezioneMuseo {
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = ConnectionManager.getConnection();
			stmt = conn.prepareStatement(LOAD);
			stmt.setString(1, oggetto.getCodice());
			conn.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
			rs = stmt.executeQuery();
			if (!rs.next()) {
				throw new EccezioneMuseo("Oggetto inesistente");
			}
			
		}
		
		catch (ClassNotFoundException cnfe) {
			throw new EccezioneMuseo(cnfe.getMessage());
		}
		catch (SQLException sqle) {
			throw new EccezioneMuseo(sqle.getMessage());
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
	
	
	
public static void load_prestito_ricevuto(OggettoTemporaneo oggetto) throws EccezioneMuseo {
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = ConnectionManager.getConnection();
			stmt = conn.prepareStatement(LOAD_PRESTITO_RICEVUTO);
			stmt.setString(1, oggetto.getCodice());
			conn.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
			rs = stmt.executeQuery();
			while (rs.next()) {
				oggetto.addLinkPrestitoRicevuto(new TipoLinkPrestitoRicevuto(oggetto, new Museo_Ente(rs.getString("codiceMuseo")), rs.getDate("dataInizio"), rs.getDate("dataFine") )  ) ;
			}
			
		}
		
		catch (ClassNotFoundException cnfe) {
			throw new EccezioneMuseo(cnfe.getMessage());
		}
		catch (SQLException sqle) {
			throw new EccezioneMuseo(sqle.getMessage());
		}
		catch (EccezionePrecondizioni ep) {
			throw new EccezioneMuseo(ep.getMessage());
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
