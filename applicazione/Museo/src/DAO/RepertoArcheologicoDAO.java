package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dominio.EccezionePrecondizioni;
import dominio.Scavo;
import dominio.TipoLinkRitrovamento;
import dominio.RepertoArcheologico.RepertoArcheologico;

import utility.ConnectionManager;
import utility.EccezioneMuseo;

public class RepertoArcheologicoDAO {
	
	private RepertoArcheologicoDAO() {}
	
	private static final String LOAD = "SELECT * FROM RepertoArcheologico WHERE codice = ?";
	private static final String LOAD_RITROVAMENTO = "SELECT * FROM Ritrovamento WHERE codiceReperto = ?";
	
	
	
	public static void load(RepertoArcheologico reperto) throws EccezioneMuseo {
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = ConnectionManager.getConnection();
			stmt = conn.prepareStatement(LOAD);
			stmt.setString(1, reperto.getCodice());
			conn.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
			rs = stmt.executeQuery();
			if (!rs.next()) {
				throw new EccezioneMuseo("Reperto inesistente");
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
	
	
	
	
	
	
	public static void load_ritrovamento(RepertoArcheologico reperto) throws EccezioneMuseo {
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = ConnectionManager.getConnection();
			stmt = conn.prepareStatement(LOAD_RITROVAMENTO);
			stmt.setString(1, reperto.getCodice());
			conn.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
			rs = stmt.executeQuery();
			if (!rs.next()) {
				throw new EccezioneMuseo("Reperto inesistente");
			}
			else {
				reperto.addLinkRitrovamento( new TipoLinkRitrovamento( reperto, new Scavo(rs.getString("nomeScavo"), rs.getString("luogoScavo") ), rs.getDate("data")) );
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
