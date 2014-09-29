package DCS;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
import java.util.LinkedList;

import dominio.EccezionePrecondizioni;
import dominio.PrestitoEffettuato;
import dominio.TipoLinkPermanentePrestito;
import dominio.Museo_Ente.Museo_Ente;
import dominio.OggettoPermanente.OggettoPermanente;

import utility.ConnectionManager;
import utility.EccezioneMuseo;


public class PrestitoEffettuatoDCS {
	
	
	private PrestitoEffettuatoDCS() {}
	
	private static final String TUTTI_PRESTITO_EFFETTUATO = "SELECT * FROM PrestitoEffettuato";
	
	public static Collection<PrestitoEffettuato> tuttiPrestitoEffettuato() throws EccezioneMuseo {
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		LinkedList<PrestitoEffettuato> list= null;
		PrestitoEffettuato prestito = null;
		
		try {
			conn = ConnectionManager.getConnection();
			stmt = conn.createStatement();
			conn.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
			rs = stmt.executeQuery(TUTTI_PRESTITO_EFFETTUATO);
			list = new LinkedList<PrestitoEffettuato>();
			while (rs.next()) {
				prestito = new PrestitoEffettuato(rs.getDate("dataInizio"));
				prestito.addEntePrestito(new Museo_Ente(rs.getString("codiceMuseo")) );
				prestito.addLinkPermanentePrestito(new TipoLinkPermanentePrestito(new OggettoPermanente(rs.getString("codiceOggetto") ), prestito) );
				list.add(prestito);
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
					return (Collection<PrestitoEffettuato>)list;
				}
				catch (SQLException sqle) {
					throw new EccezioneMuseo(sqle.getMessage());
				}
			}
		}
		return null;
		
	}


}
