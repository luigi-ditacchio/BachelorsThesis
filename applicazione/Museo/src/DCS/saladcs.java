package DCS;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
import java.util.LinkedList;

import utility.ConnectionManager;
import utility.EccezioneMuseo;
import dominio.Sala;


public class SalaDCS {
	
	private SalaDCS() {}
	
	private static final String TUTTI_SALA = "SELECT * FROM Sala";
	private static final String TUTTI_PIANI = "SELECT distinct piano FROM Sala";
	private static final String TUTTI_SALA_SU_PIANO = "SELECT numero FROM Sala WHERE piano = ?";
	private static final String TUTTE_OPERE_IN_SALA = "SELECT codiceOggetto FROM Collocazione WHERE numSala = ? and pianoSala = ?";
	
	
	
	public static Collection<Sala> tuttiSala() throws EccezioneMuseo {
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		LinkedList<Sala> list= null;
		Sala sala = null;
		
		try {
			conn = ConnectionManager.getConnection();
			stmt = conn.createStatement();
			conn.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
			rs = stmt.executeQuery(TUTTI_SALA);
			list = new LinkedList<Sala>();
			while (rs.next()) {
				sala = new Sala(rs.getInt("numero"), rs.getInt("piano"));
				sala.setDescrizione(rs.getString("descrizione"));
				sala.setNumOggetti(rs.getInt("numOggetti"));
				list.add(sala);
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
					return (Collection<Sala>)list;
				}
				catch (SQLException sqle) {
					throw new EccezioneMuseo(sqle.getMessage());
				}
			}
		}
		return null;
		
	}
	
	
	
	
	public static Collection<Integer> tuttiPiani() throws EccezioneMuseo {
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		LinkedList<Integer> list= null;
		
		try {
			conn = ConnectionManager.getConnection();
			stmt = conn.createStatement();
			conn.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
			rs = stmt.executeQuery(TUTTI_PIANI);
			list = new LinkedList<Integer>();
			while (rs.next()) {
				list.add(new Integer(rs.getInt("piano")) );
				
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
					return (Collection<Integer>)list;
				}
				catch (SQLException sqle) {
					throw new EccezioneMuseo(sqle.getMessage());
				}
			}
		}
		return null;
		
	}
	
	
	
	public static Collection<Integer> tuttiSalasuPiano(int piano) throws EccezioneMuseo {
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		LinkedList<Integer> list= null;
		
		try {
			conn = ConnectionManager.getConnection();
			stmt = conn.prepareStatement(TUTTI_SALA_SU_PIANO);
			stmt.setInt(1, piano);
			conn.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
			rs = stmt.executeQuery();
			list = new LinkedList<Integer>();
			while (rs.next()) {
				list.add(new Integer(rs.getInt("numero")) );
				
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
					return (Collection<Integer>)list;
				}
				catch (SQLException sqle) {
					throw new EccezioneMuseo(sqle.getMessage());
				}
			}
		}
		return null;
		
	}
	
	
	
	public static Collection<String> tutteOpereinSala(int numero, int piano) throws EccezioneMuseo {
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		LinkedList<String> list= null;
		
		try {
			conn = ConnectionManager.getConnection();
			stmt = conn.prepareStatement(TUTTE_OPERE_IN_SALA);
			stmt.setInt(1, numero);
			stmt.setInt(2, piano);
			conn.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
			rs = stmt.executeQuery();
			list = new LinkedList<String>();
			while (rs.next()) {
				list.add(new String(rs.getString("codiceOggetto")) );
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
					return (Collection<String>)list;
				}
				catch (SQLException sqle) {
					throw new EccezioneMuseo(sqle.getMessage());
				}
			}
		}
		return null;
		
	}
	
	

}
