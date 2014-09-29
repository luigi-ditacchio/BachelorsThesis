package DAO;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import utility.ConnectionManager;
import utility.EccezioneMuseo;


import dominio.EccezionePrecondizioni;
import dominio.Sala;
import dominio.Sezione;
import dominio.TipoLinkAppartenenza;
import dominio.TipoLinkCollocazione;
import dominio.Fotografia.Fotografia;
import dominio.Quadro.Quadro;
import dominio.RepertoArcheologico.RepertoArcheologico;
import dominio.Statua.Statua;

public class SalaDAO {
	
	
	private SalaDAO() {}
	
	
	private static final String LOAD = "SELECT * FROM Sala s WHERE s.numero = ? and s.piano = ?";
	private static final String LOAD_APPARTENENZA = "SELECT * FROM Appartenenza WHERE numSala = ? and pianoSala = ?";
	private static final String LOAD_COLLOCAZIONE_REPERTO = "SELECT codice FROM RepertoArcheologico, Collocazione WHERE codice = codiceOggetto and numSala = ? and pianoSala = ?";
	private static final String LOAD_COLLOCAZIONE_FOTOGRAFIA = "SELECT codice FROM Fotografia, Collocazione WHERE codice = codiceOggetto and numSala = ? and pianoSala = ?";
	private static final String LOAD_COLLOCAZIONE_QUADRO = "SELECT codice FROM Quadro, Collocazione WHERE codice = codiceOggetto and numSala = ? and pianoSala = ?";
	private static final String LOAD_COLLOCAZIONE_STATUA = "SELECT codice FROM Statua, Collocazione WHERE codice = codiceOggetto and numSala = ? and pianoSala = ?";
	
	
	
	public static void load(Sala sala) throws EccezioneMuseo {
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = ConnectionManager.getConnection();
			stmt = conn.prepareStatement(LOAD);
			stmt.setInt(1, sala.getNumero());
			stmt.setInt(2, sala.getPiano());
			conn.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
			rs = stmt.executeQuery();
			if (!rs.next()) {
				throw new EccezioneMuseo("Sala inesistente");
			}
			else {
				sala.setDescrizione(rs.getString("descrizione"));
				sala.setNumOggetti(rs.getInt("numOggetti"));
				
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
	
	
	
	public static void load_appartenenza(Sala sala) throws EccezioneMuseo {
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = ConnectionManager.getConnection();
			stmt = conn.prepareStatement(LOAD_APPARTENENZA);
			stmt.setInt(1, sala.getNumero());
			stmt.setInt(2, sala.getPiano());
			conn.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
			rs = stmt.executeQuery();
			while (rs.next()) {
				sala.addLinkAppartenenza(new TipoLinkAppartenenza(sala, new Sezione(rs.getString("sezione")) )  );
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
	
	
	
public static void load_collocazione(Sala sala) throws EccezioneMuseo {
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = ConnectionManager.getConnection();
			stmt = conn.prepareStatement(LOAD_COLLOCAZIONE_REPERTO);
			stmt.setInt(1, sala.getNumero());
			stmt.setInt(2, sala.getPiano());
			conn.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
			rs = stmt.executeQuery();
			while (rs.next()) {
				sala.addLinkCollocazione(new TipoLinkCollocazione(new RepertoArcheologico(rs.getString("codiceOggetto")), sala )  );
			}
			
			stmt = conn.prepareStatement(LOAD_COLLOCAZIONE_FOTOGRAFIA);
			stmt.setInt(1, sala.getNumero());
			stmt.setInt(2, sala.getPiano());
			rs = stmt.executeQuery();
			while (rs.next()) {
				sala.addLinkCollocazione(new TipoLinkCollocazione(new Fotografia(rs.getString("codiceOggetto")), sala )  );
			}
			
			stmt = conn.prepareStatement(LOAD_COLLOCAZIONE_QUADRO);
			stmt.setInt(1, sala.getNumero());
			stmt.setInt(2, sala.getPiano());
			rs = stmt.executeQuery();
			while (rs.next()) {
				sala.addLinkCollocazione(new TipoLinkCollocazione(new Quadro(rs.getString("codiceOggetto")), sala )  );
			}
			
			stmt = conn.prepareStatement(LOAD_COLLOCAZIONE_STATUA);
			stmt.setInt(1, sala.getNumero());
			stmt.setInt(2, sala.getPiano());
			rs = stmt.executeQuery();
			while (rs.next()) {
				sala.addLinkCollocazione(new TipoLinkCollocazione(new Statua(rs.getString("codiceOggetto")), sala )  );
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
