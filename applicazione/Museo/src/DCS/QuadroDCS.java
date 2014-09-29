package DCS;

import java.sql.Connection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
import java.util.LinkedList;

import utility.ConnectionManager;
import utility.EccezioneMuseo;

import dominio.Quadro.Quadro;

public class QuadroDCS {
	
	
	private QuadroDCS() {}
	
	private static final String TUTTI_QUADRO = "SELECT * FROM Quadro";
	
	
	public static Collection<Quadro> tuttiQuadro() throws EccezioneMuseo {
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		LinkedList<Quadro> list= null;
		Quadro quadro = null;
		
		try {
			conn = ConnectionManager.getConnection();
			stmt = conn.createStatement();
			conn.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
			rs = stmt.executeQuery(TUTTI_QUADRO);
			list = new LinkedList<Quadro>();
			while (rs.next()) {
				quadro = new Quadro(rs.getString("codice"));
				quadro.setTecnica(rs.getString("tecnica"));
				list.add(quadro);
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
					return (Collection<Quadro>)list;
				}
				catch (SQLException sqle) {
					throw new EccezioneMuseo(sqle.getMessage());
				}
			}
		}
		return null;
		
	}
	
	
	
	


}
