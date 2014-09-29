package DCS;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
import java.util.LinkedList;

import utility.ConnectionManager;
import utility.EccezioneMuseo;

import dominio.Museo_Ente.Museo_Ente;

public class Museo_EnteDCS {
	
	
	private Museo_EnteDCS() {}
	
	private static final String TUTTI_MUSEO = "SELECT * FROM Museo_Ente";
	
	public static Collection<Museo_Ente> tuttiMuseo_Ente() throws EccezioneMuseo {
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		LinkedList<Museo_Ente> list= null;
		Museo_Ente museo = null;
		
		try {
			conn = ConnectionManager.getConnection();
			stmt = conn.createStatement();
			conn.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
			rs = stmt.executeQuery(TUTTI_MUSEO);
			list = new LinkedList<Museo_Ente>();
			while (rs.next()) {
				museo = new Museo_Ente(rs.getString("codice"));
				list.add(museo);
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
					return (Collection<Museo_Ente>)list;
				}
				catch (SQLException sqle) {
					throw new EccezioneMuseo(sqle.getMessage());
				}
			}
		}
		return null;
		
	}


}
