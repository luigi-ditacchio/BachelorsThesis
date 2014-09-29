package DCS;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
import java.util.LinkedList;

import utility.ConnectionManager;
import utility.EccezioneMuseo;
import dominio.Fotografia.Fotografia;


public class FotografiaDCS {
	
	
	private FotografiaDCS() {}
	
	private static final String TUTTI_FOTOGRAFIA = "SELECT * FROM Fotografia";
	
	public static Collection<Fotografia> tuttiFotografia() throws EccezioneMuseo {
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		LinkedList<Fotografia> list= null;
		Fotografia fotografia = null;
		
		try {
			conn = ConnectionManager.getConnection();
			stmt = conn.createStatement();
			conn.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
			rs = stmt.executeQuery(TUTTI_FOTOGRAFIA);
			list = new LinkedList<Fotografia>();
			while (rs.next()) {
				fotografia = new Fotografia(rs.getString("codice"));
				fotografia.setTipo(rs.getString("tipo"));
				list.add(fotografia);
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
					return (Collection<Fotografia>)list;
				}
				catch (SQLException sqle) {
					throw new EccezioneMuseo(sqle.getMessage());
				}
			}
		}
		return null;
		
	}

}
