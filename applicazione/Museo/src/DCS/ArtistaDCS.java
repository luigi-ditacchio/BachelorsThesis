package DCS;


import java.sql.Connection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
import java.util.LinkedList;

import dominio.Artista;

import utility.ConnectionManager;
import utility.EccezioneMuseo;

public class ArtistaDCS {
	
	private ArtistaDCS() {}
	
	private static final String TUTTI_ARTISTA = "SELECT * FROM ArtistaGenerali";
	
	public static Collection<Artista> tuttiArtistaGenerali() throws EccezioneMuseo {
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		LinkedList<Artista> list= null;
		Artista artista = null;
		
		try {
			conn = ConnectionManager.getConnection();
			stmt = conn.createStatement();
			conn.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
			rs = stmt.executeQuery(TUTTI_ARTISTA);
			list = new LinkedList<Artista>();
			while (rs.next()) {
				artista = new Artista(rs.getString("codice"));
				artista.setNome(rs.getString("nome"));
				artista.setCognome(rs.getString("cognome"));
				artista.setNomeArte(rs.getString("nomeArte"));
				artista.setDataNascita(rs.getDate("dataNascita"));
				artista.setDataMorte(rs.getDate("dataMorte"));
				list.add(artista);
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
					return (Collection<Artista>)list;
				}
				catch (SQLException sqle) {
					throw new EccezioneMuseo(sqle.getMessage());
				}
			}
		}
		return null;
		
	}

}
