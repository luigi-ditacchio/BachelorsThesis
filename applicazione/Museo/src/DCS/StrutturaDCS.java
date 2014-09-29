package DCS;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
import java.util.LinkedList;

import utility.ConnectionManager;
import utility.EccezioneMuseo;

import dominio.Struttura.Struttura;

public class StrutturaDCS {
	
	
	private StrutturaDCS() {}
	
	private static final String TUTTI_STRUTTURA = "SELECT * FROM Struttura";
	private static final String TUTTI_TELEFONO = "SELECT * FROM TelefonoStruttura WHERE codiceStruttura = ?";
	
	public static Collection<Struttura> tuttiStruttura() throws EccezioneMuseo {
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		PreparedStatement prep_stmt = null;
		ResultSet rs_telefono = null;
		
		LinkedList<Struttura> list= null;
		Struttura struttura = null;
		
		try {
			conn = ConnectionManager.getConnection();
			stmt = conn.createStatement();
			prep_stmt = conn.prepareStatement(TUTTI_TELEFONO);
			conn.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
			rs = stmt.executeQuery(TUTTI_STRUTTURA);
			list = new LinkedList<Struttura>();
			while (rs.next()) {
				struttura = new Struttura(rs.getString("codice"));
				struttura.setNome(rs.getString("nome"));
				struttura.setCitta(rs.getString("citta"));
				struttura.setVia(rs.getString("via"));
				struttura.setNumero(rs.getInt("numero"));
				
				prep_stmt.setString(1, struttura.getCodice());
				rs_telefono = prep_stmt.executeQuery();
				while (rs_telefono.next()) {
					struttura.addTelefono(rs_telefono.getString("telefono"));
				}
				list.add(struttura);
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
					if (rs_telefono != null) rs_telefono.close();
					if (prep_stmt != null) prep_stmt.close();
					conn.setTransactionIsolation(Connection.TRANSACTION_REPEATABLE_READ);
					conn.close();
					return (Collection<Struttura>)list;
				}
				catch (SQLException sqle) {
					throw new EccezioneMuseo(sqle.getMessage());
				}
			}
		}
		return null;
		
	}


}
