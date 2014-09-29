package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import utility.ConnectionManager;
import utility.EccezioneMuseo;

import dominio.Struttura.Struttura;

public class StrutturaDAO {
	
	
	private StrutturaDAO() {}
	
	private static final String LOAD = "SELECT * FROM Struttura WHERE codice = ?";
	private static final String LOAD_TELEFONO = "SELECT * FROM TelefonoStruttura WHERE codiceStruttura = ?";
	
	
	public static void load(Struttura struttura) throws EccezioneMuseo {
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = ConnectionManager.getConnection();
			stmt = conn.prepareStatement(LOAD);
			stmt.setString(1, struttura.getCodice());
			conn.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
			rs = stmt.executeQuery();
			if (!rs.next()) {
				throw new EccezioneMuseo("Struttura inesistente");
			}
			else {
				struttura.setNome(rs.getString("nome"));
				struttura.setCitta(rs.getString("citta"));
				struttura.setVia(rs.getString("via"));
				struttura.setNumero(rs.getInt("numero"));
				struttura.setCAP(rs.getString("CAP"));
				
				stmt = conn.prepareStatement(LOAD_TELEFONO);
				stmt.setString(1, struttura.getCodice());
				rs = stmt.executeQuery();
				while (rs.next()) {
					struttura.addTelefono(rs.getString("telefono"));
				}
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

}
