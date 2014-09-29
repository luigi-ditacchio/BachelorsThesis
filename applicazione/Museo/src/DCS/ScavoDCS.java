package DCS;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
import java.util.LinkedList;

import utility.ConnectionManager;
import utility.EccezioneMuseo;
import dominio.Scavo;


public class ScavoDCS {
	
	
	private ScavoDCS() {}
	
	private static final String TUTTI_SCAVO = "SELECT * FROM Scavo";
	
	public static Collection<Scavo> tuttiScavo() throws EccezioneMuseo {
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		LinkedList<Scavo> list= null;
		Scavo scavo = null;
		
		try {
			conn = ConnectionManager.getConnection();
			stmt = conn.createStatement();
			conn.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
			rs = stmt.executeQuery(TUTTI_SCAVO);
			list = new LinkedList<Scavo>();
			while (rs.next()) {
				scavo = new Scavo(rs.getString("nome"), rs.getString("luogo"));
				list.add(scavo);
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
					return (Collection<Scavo>)list;
				}
				catch (SQLException sqle) {
					throw new EccezioneMuseo(sqle.getMessage());
				}
			}
		}
		return null;
		
	}

}
