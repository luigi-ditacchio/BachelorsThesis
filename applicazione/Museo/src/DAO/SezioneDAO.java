package DAO;

import java.sql.*;


import utility.*;


import dominio.EccezionePrecondizioni;
import dominio.Sala;
import dominio.Sezione;
import dominio.TipoLinkAppartenenza;


public class SezioneDAO {
	
	
	private SezioneDAO() {}
	
	private static final String LOAD = "SELECT * FROM Sezione s WHERE s.nome = ?";
	private static final String LOAD_APPARTENENZA = "SELECT * FROM Appartenenza a WHERE a.sezione = ?";
	
	
	
	public static void load(Sezione sezione) throws EccezioneMuseo {
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = ConnectionManager.getConnection();
			stmt = conn.prepareStatement(LOAD);
			stmt.setString(1, sezione.getNome());
			conn.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
			rs = stmt.executeQuery();
			if (!rs.next()) {
				throw new EccezioneMuseo("Sezione inesistente");
			}
			else {
				sezione.setDescrizione(rs.getString("descrizione"));
				sezione.setNumSale(rs.getInt("numSale"));
				
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
	
	
	
	public static void load_appartenenza(Sezione sezione) throws EccezioneMuseo {
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = ConnectionManager.getConnection();
			stmt = conn.prepareStatement(LOAD_APPARTENENZA);
			stmt.setString(1, sezione.getNome());
			conn.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
			rs = stmt.executeQuery();
			while (rs.next()) {
				
				sezione.addLinkAppartenenza(new TipoLinkAppartenenza(new Sala(rs.getInt("numSala"), rs.getInt("pianoSala")), sezione) );
				
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
