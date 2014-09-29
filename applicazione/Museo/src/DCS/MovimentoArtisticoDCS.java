package DCS;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
import java.util.LinkedList;

import utility.ConnectionManager;
import utility.EccezioneMuseo;

import dominio.MovimentoArtistico;

public class MovimentoArtisticoDCS {
	
	
	private MovimentoArtisticoDCS() {}
	
	private static final String TUTTI_MOVIMENTO = "SELECT * FROM MovimentoArtistico";
	
	public static Collection<MovimentoArtistico> tuttiMovimentoArtistico() throws EccezioneMuseo {
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		LinkedList<MovimentoArtistico> list= null;
		MovimentoArtistico movimento = null;
		
		try {
			conn = ConnectionManager.getConnection();
			stmt = conn.createStatement();
			conn.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
			rs = stmt.executeQuery(TUTTI_MOVIMENTO);
			list = new LinkedList<MovimentoArtistico>();
			while (rs.next()) {
				movimento = new MovimentoArtistico(rs.getString("nome"));
				movimento.setPeriodo(rs.getString("periodoStorico"));
				movimento.setDescrizione(rs.getString("descrizione"));
				list.add(movimento);
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
					return (Collection<MovimentoArtistico>)list;
				}
				catch (SQLException sqle) {
					throw new EccezioneMuseo(sqle.getMessage());
				}
			}
		}
		return null;
		
	}

}
