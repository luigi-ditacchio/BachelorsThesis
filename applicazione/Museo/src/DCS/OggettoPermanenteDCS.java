package DCS;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
import java.util.LinkedList;

import utility.ConnectionManager;
import utility.EccezioneMuseo;
import dominio.OggettoPermanente.OggettoPermanente;


public class OggettoPermanenteDCS {
	
	
	private OggettoPermanenteDCS() {}
	
	private static final String TUTTI_OGGETTO_PERMANENTE = "SELECT * FROM OggettoPermanente";
	
	public static Collection<OggettoPermanente> tuttiOggettoPermanente() throws EccezioneMuseo {
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		LinkedList<OggettoPermanente> list= null;
		OggettoPermanente oggetto = null;
		
		try {
			conn = ConnectionManager.getConnection();
			stmt = conn.createStatement();
			conn.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
			rs = stmt.executeQuery(TUTTI_OGGETTO_PERMANENTE);
			list = new LinkedList<OggettoPermanente>();
			while (rs.next()) {
				oggetto = new OggettoPermanente(rs.getString("codice"));
				oggetto.setStato(rs.getString("stato"));
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
					return (Collection<OggettoPermanente>)list;
				}
				catch (SQLException sqle) {
					throw new EccezioneMuseo(sqle.getMessage());
				}
			}
		}
		return null;
		
	}

}
