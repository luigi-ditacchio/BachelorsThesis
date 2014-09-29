package DCS;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
import java.util.LinkedList;

import utility.ConnectionManager;
import utility.EccezioneMuseo;
import dominio.CentroRestauro.CentroRestauro;


public class CentroRestauroDCS {
	
	
	private CentroRestauroDCS() {}
	
	private static final String TUTTI_CENTRO_RESTAURO = "SELECT * FROM CentroRestauro";
	
	public static Collection<CentroRestauro> tuttiCentroRestauro() throws EccezioneMuseo {
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		LinkedList<CentroRestauro> list= null;
		CentroRestauro centro = null;
		
		try {
			conn = ConnectionManager.getConnection();
			stmt = conn.createStatement();
			conn.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
			rs = stmt.executeQuery(TUTTI_CENTRO_RESTAURO);
			list = new LinkedList<CentroRestauro>();
			while (rs.next()) {
				centro = new CentroRestauro(rs.getString("codice"));
				list.add(centro);
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
					return (Collection<CentroRestauro>)list;
				}
				catch (SQLException sqle) {
					throw new EccezioneMuseo(sqle.getMessage());
				}
			}
		}
		return null;
		
	}

}
