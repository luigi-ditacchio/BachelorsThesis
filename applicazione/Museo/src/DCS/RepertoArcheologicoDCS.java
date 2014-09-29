package DCS;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
import java.util.LinkedList;

import utility.ConnectionManager;
import utility.EccezioneMuseo;

import dominio.RepertoArcheologico.RepertoArcheologico;

public class RepertoArcheologicoDCS {
	
	
	private RepertoArcheologicoDCS() {}
	
	private static final String TUTTI_REPERTO = "SELECT * FROM RepertoArcheologico";
	
	public static Collection<RepertoArcheologico> tuttiRepertoArcheologico() throws EccezioneMuseo {
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		LinkedList<RepertoArcheologico> list= null;
		RepertoArcheologico reperto = null;
		
		try {
			conn = ConnectionManager.getConnection();
			stmt = conn.createStatement();
			conn.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
			rs = stmt.executeQuery(TUTTI_REPERTO);
			list = new LinkedList<RepertoArcheologico>();
			while (rs.next()) {
				reperto = new RepertoArcheologico(rs.getString("codice"));
				list.add(reperto);
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
					return (Collection<RepertoArcheologico>)list;
				}
				catch (SQLException sqle) {
					throw new EccezioneMuseo(sqle.getMessage());
				}
			}
		}
		return null;
		
	}


}
