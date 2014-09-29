package DCS;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
import java.util.LinkedList;

import utility.ConnectionManager;
import utility.EccezioneMuseo;

import dominio.OggettoTemporaneo.OggettoTemporaneo;

public class OggettoTemporaneoDCS {
	
	
	private OggettoTemporaneoDCS() {}
	
	private static final String TUTTI_OGGETTO_TEMPORANEO = "SELECT * FROM OggettoTemporaneo";
	
	public static Collection<OggettoTemporaneo> tuttiOggettoTemporaneo() throws EccezioneMuseo {
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		LinkedList<OggettoTemporaneo> list= null;
		OggettoTemporaneo oggetto = null;
		
		try {
			conn = ConnectionManager.getConnection();
			stmt = conn.createStatement();
			conn.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
			rs = stmt.executeQuery(TUTTI_OGGETTO_TEMPORANEO);
			list = new LinkedList<OggettoTemporaneo>();
			while (rs.next()) {
				oggetto = new OggettoTemporaneo(rs.getString("codice"));
				list.add(oggetto);
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
					return (Collection<OggettoTemporaneo>)list;
				}
				catch (SQLException sqle) {
					throw new EccezioneMuseo(sqle.getMessage());
				}
			}
		}
		return null;
		
	}


}
