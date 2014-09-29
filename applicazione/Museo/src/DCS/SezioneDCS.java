package DCS;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
import java.util.LinkedList;

import utility.ConnectionManager;
import utility.EccezioneMuseo;

import dominio.Sezione;

public class SezioneDCS {
	
	
	private SezioneDCS() {}
	
	private static final String TUTTI_SEZIONE = "SELECT * FROM Sezione";
	
	public static Collection<Sezione> tuttiSezione() throws EccezioneMuseo {
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		LinkedList<Sezione> list= null;
		Sezione sezione = null;
		
		try {
			conn = ConnectionManager.getConnection();
			stmt = conn.createStatement();
			conn.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
			rs = stmt.executeQuery(TUTTI_SEZIONE);
			list = new LinkedList<Sezione>();
			while (rs.next()) {
				sezione = new Sezione(rs.getString("nome"));
				sezione.setDescrizione(rs.getString("descrizione"));
				sezione.setNumSale(rs.getInt("numSale"));
				list.add(sezione);
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
					return (Collection<Sezione>)list;
				}
				catch (SQLException sqle) {
					throw new EccezioneMuseo(sqle.getMessage());
				}
			}
		}
		return null;
		
	}

}
