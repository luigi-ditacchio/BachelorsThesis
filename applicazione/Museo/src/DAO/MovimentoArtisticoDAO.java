package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import utility.ConnectionManager;
import utility.EccezioneMuseo;
import dominio.Artista;
import dominio.EccezionePrecondizioni;
import dominio.MovimentoArtistico;
import dominio.TipoLinkAdesione;

public class MovimentoArtisticoDAO {
	
	private MovimentoArtisticoDAO() {}
	
	private static final String LOAD = "SELECT * FROM MovimentoArtistico WHERE nome = ?";
	private static final String LOAD_ADESIONE = "SELECT * FROM Adesione WHERE movimentoArtistico = ?";

	
	public static void load(MovimentoArtistico movimento) throws EccezioneMuseo {
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = ConnectionManager.getConnection();
			stmt = conn.prepareStatement(LOAD);
			stmt.setString(1, movimento.getNome());
			conn.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
			rs = stmt.executeQuery();
			if (!rs.next()) {
				throw new EccezioneMuseo("Movimento inesistente");
			}
			else {
				movimento.setPeriodo(rs.getString("periodoStorico"));
				movimento.setDescrizione(rs.getString("descrizione"));
				
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
				}
				catch (SQLException sqle) {
					throw new EccezioneMuseo(sqle.getMessage());
				}
			}
		}
	}
	
	
	public static void load_adesione(MovimentoArtistico movimento) throws EccezioneMuseo {
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = ConnectionManager.getConnection();
			stmt = conn.prepareStatement(LOAD_ADESIONE);
			stmt.setString(1, movimento.getNome());
			conn.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
			rs = stmt.executeQuery();
			while (rs.next()) {
				
				movimento.addLinkAdesione(new TipoLinkAdesione(new Artista(rs.getString("codiceArtista")), movimento ) );
				
			}
		}
		
		catch (ClassNotFoundException cnfe) {
			throw new EccezioneMuseo(cnfe.getMessage());
		}
		catch (SQLException sqle) {
			throw new EccezioneMuseo(sqle.getMessage());
		}
		catch (EccezionePrecondizioni ep) {
			throw new EccezioneMuseo(ep.toString());
		}
		
		finally {
			if (conn != null) {
				try {
					if (rs != null) rs.close();
					if (stmt != null) stmt.close();
					conn.setTransactionIsolation(Connection.TRANSACTION_REPEATABLE_READ);
					conn.close();
				}
				catch (SQLException sqle) {
					throw new EccezioneMuseo(sqle.getMessage());
				}
			}
		}
	}

}
