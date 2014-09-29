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
import dominio.TipoLinkRealizzazione;

import dominio.TipoLinkAdesione;
import dominio.Quadro.Quadro;
import dominio.Statua.Statua;


public class ArtistaDAO {
	
	private ArtistaDAO() {}
	
	private static final String LOAD_GENERALI = "SELECT * FROM ArtistaGenerali WHERE codice = ?";
	private static final String LOAD_PARTICOLARI = "SELECT * FROM ArtistaParticolari WHERE codice = ?";
	private static final String LOAD_ADESIONE = "SELECT * FROM Adesione WHERE codiceArtista = ?";
	private static final String LOAD_REALIZZAZIONE_QUADRI = "SELECT q.codice as codice FROM Quadro q, OperaArte o WHERE o.codiceArtista = ? and q.codice = o.codice";
	private static final String LOAD_REALIZZAZIONE_STATUE = "SELECT s.codice as codice FROM Statua s, OperaArte o WHERE o.codiceArtista = ? and s.codice = o.codice";

	
	public static void load_generali(Artista artista) throws EccezioneMuseo {
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = ConnectionManager.getConnection();
			stmt = conn.prepareStatement(LOAD_GENERALI);
			stmt.setString(1, artista.getCodice());
			conn.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
			rs = stmt.executeQuery();
			if (!rs.next()) {
				throw new EccezioneMuseo("Artista inesistente");
			}
			else {
				artista.setNome(rs.getString("nome"));
				artista.setCognome(rs.getString("cognome"));
				artista.setNomeArte(rs.getString("nomeArte"));
				artista.setDataNascita(rs.getDate("dataNascita"));
				artista.setDataMorte(rs.getDate("dataMorte"));
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
	
	
	public static void load_particolari(Artista artista) throws EccezioneMuseo {
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = ConnectionManager.getConnection();
			stmt = conn.prepareStatement(LOAD_PARTICOLARI);
			stmt.setString(1, artista.getCodice());
			conn.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
			rs = stmt.executeQuery();
			if (!rs.next()) {
				throw new EccezioneMuseo("Artista inesistente");
			}
			else {
				artista.setLuogoNascita(rs.getString("luogoNascita"));
				artista.setLuogoMorte(rs.getString("luogoMorte"));
				artista.setVita(rs.getString("vita"));
				artista.setStile(rs.getString("stile"));
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
	
	
	
	public static void load_adesione(Artista artista) throws EccezioneMuseo {
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = ConnectionManager.getConnection();
			stmt = conn.prepareStatement(LOAD_ADESIONE);
			stmt.setString(1, artista.getCodice());
			conn.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
			rs = stmt.executeQuery();
			while (rs.next()) {
				artista.addLinkAdesione(new TipoLinkAdesione(artista, new MovimentoArtistico(rs.getString("movimentoArtistico"))  )   );
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
	
	
	
	
	
	public static void load_realizzazione(Artista artista) throws EccezioneMuseo {
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = ConnectionManager.getConnection();
			
			stmt = conn.prepareStatement(LOAD_REALIZZAZIONE_QUADRI);
			stmt.setString(1, artista.getCodice());
			conn.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
			rs = stmt.executeQuery();
			while (rs.next()) {
				artista.addLinkRealizzazione(new TipoLinkRealizzazione(new Quadro(rs.getString("codice")), artista )   );
			}
			
			stmt = conn.prepareStatement(LOAD_REALIZZAZIONE_STATUE);
			stmt.setString(1, artista.getCodice());
			rs = stmt.executeQuery();
			while (rs.next()) {
				artista.addLinkRealizzazione(new TipoLinkRealizzazione(new Statua(rs.getString("codice")), artista  )   );
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
