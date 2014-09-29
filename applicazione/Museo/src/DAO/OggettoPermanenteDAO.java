package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import utility.ConnectionManager;
import utility.EccezioneMuseo;
import dominio.EccezionePrecondizioni;
import dominio.PrestitoEffettuato;
import dominio.TipoLinkPermanentePrestito;

import dominio.OggettoPermanente.OggettoPermanente;


public class OggettoPermanenteDAO {
	
	
	private OggettoPermanenteDAO() {}
	
	private static final String LOAD = "SELECT * FROM OggettoPermanente WHERE codice = ?";
	private static final String LOAD_PRESTITO_EFFETTUATO = "SELECT * FROM PrestitoEffettuato WHERE codiceOggetto = ?";
	
	
	public static void load(OggettoPermanente oggetto) throws EccezioneMuseo {
		
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
			else {
				oggetto.setStato(rs.getString("stato"));
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
	
	
	
public static void load_prestito_effettuato(OggettoPermanente oggetto) throws EccezioneMuseo {
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = ConnectionManager.getConnection();
			stmt = conn.prepareStatement(LOAD_PRESTITO_EFFETTUATO);
			stmt.setString(1, oggetto.getCodice());
			conn.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
			rs = stmt.executeQuery();
			while (rs.next()) {
				oggetto.addLinkPermanentePrestito( new TipoLinkPermanentePrestito(oggetto, new PrestitoEffettuato(rs.getDate("dataInizio")) ) ) ;
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
