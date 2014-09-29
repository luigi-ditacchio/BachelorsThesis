package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import utility.ConnectionManager;
import utility.EccezioneMuseo;
import dominio.EccezionePrecondizioni;
import dominio.Sala;
import dominio.TipoLinkCollocazione;
import dominio.Oggetto.Oggetto;


public class OggettoDAO {
	
	private OggettoDAO() {}
	
	private static final String LOAD_GENERALI = "SELECT * FROM OggettoGenerali WHERE codice = ?";
	private static final String LOAD_PARTICOLARI = "SELECT * FROM OggettoParticolari WHERE codice = ?";
	private static final String LOAD_COLLOCAZIONE = "SELECT * FROM Collocazione WHERE codiceOggetto = ?";

	
	public static void load_generali(Oggetto oggetto) throws EccezioneMuseo {
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = ConnectionManager.getConnection();
			stmt = conn.prepareStatement(LOAD_GENERALI);
			stmt.setString(1, oggetto.getCodice());
			conn.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
			rs = stmt.executeQuery();
			if (!rs.next()) {
				throw new EccezioneMuseo("Oggetto inesistente");
			}
			else {
				oggetto.setNome(rs.getString("nome"));
				oggetto.setAnnoPeriodo(rs.getString("anno_periodo"));
				oggetto.setDescrizione(rs.getString("descrizione"));
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
	
	
	public static void load_particolari(Oggetto oggetto) throws EccezioneMuseo {
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = ConnectionManager.getConnection();
			stmt = conn.prepareStatement(LOAD_PARTICOLARI);
			stmt.setString(1, oggetto.getCodice());
			conn.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
			rs = stmt.executeQuery();
			if (!rs.next()) {
				throw new EccezioneMuseo("Oggetto inesistente");
			}
			else {
				oggetto.setLunghezza(rs.getFloat("lunghezza"));
				oggetto.setLarghezza(rs.getFloat("larghezza"));
				oggetto.setProfondita(rs.getFloat("profondita"));
				oggetto.setProvenienza(rs.getString("provenienza"));
				oggetto.setCuriosita(rs.getString("curiosita"));
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
	
	
	
public static void load_collocazione(Oggetto oggetto) throws EccezioneMuseo {
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = ConnectionManager.getConnection();
			stmt = conn.prepareStatement(LOAD_COLLOCAZIONE);
			stmt.setString(1, oggetto.getCodice());
			conn.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
			rs = stmt.executeQuery();
			if (!rs.next()) {
				throw new EccezioneMuseo("Oggetto inesistente");
			}
			else {
				oggetto.addLinkCollocazione(new TipoLinkCollocazione(oggetto, new Sala(rs.getInt("numSala"), rs.getInt("pianoSala")) ) );
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
