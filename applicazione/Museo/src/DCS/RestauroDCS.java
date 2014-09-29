package DCS;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
import java.util.LinkedList;

import utility.ConnectionManager;
import utility.EccezioneMuseo;
import dominio.EccezionePrecondizioni;

import dominio.Restauro;

import dominio.TipoLinkPermanenteRestauro;
import dominio.CentroRestauro.CentroRestauro;

import dominio.OggettoPermanente.OggettoPermanente;

public class RestauroDCS {
	
	
	private RestauroDCS() {}
	
	private static final String TUTTI_RESTAURO = "SELECT * FROM Restauro";
	
	public static Collection<Restauro> tuttiRestauro() throws EccezioneMuseo {
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		LinkedList<Restauro> list= null;
		Restauro restauro = null;
		
		try {
			conn = ConnectionManager.getConnection();
			stmt = conn.createStatement();
			conn.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
			rs = stmt.executeQuery(TUTTI_RESTAURO);
			list = new LinkedList<Restauro>();
			while (rs.next()) {
				restauro = new Restauro(rs.getDate("dataInizio"));
				restauro.addLocazioneRestauro(new CentroRestauro(rs.getString("codiceCentro")) );
				restauro.addLinkPermanenteRestauro(new TipoLinkPermanenteRestauro(new OggettoPermanente(rs.getString("codiceOggetto") ), restauro) );
				list.add(restauro);
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
					return (Collection<Restauro>)list;
				}
				catch (SQLException sqle) {
					throw new EccezioneMuseo(sqle.getMessage());
				}
			}
		}
		return null;
		
	}


}
