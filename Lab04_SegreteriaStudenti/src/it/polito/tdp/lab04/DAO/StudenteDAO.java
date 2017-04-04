package it.polito.tdp.lab04.DAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import it.polito.tdp.lab04.model.Studente;



public class StudenteDAO {
	

	public Studente cercaStudente(int matricola) {
		final String sql= "SELECT * FROM studente WHERE matricola=?";
		try{
			
			Connection conn= ConnectDB.getConnection();
			PreparedStatement st= conn.prepareStatement(sql);
			
			st.setInt(1, matricola);
			
			ResultSet rs= st.executeQuery();
			
			Studente s= null;
			
			if(rs.next()){
				String cognome=rs.getString("cognome");
				String nome=rs.getString("nome");
				String cds=rs.getString("cds");
				s= new Studente(matricola, cognome, nome, cds);
				
			}else
				s=null;
			//conn.close();
			
		return s;	
		} catch(SQLException e){
			e.printStackTrace();
			return null;
		}
		
	}
	
	
	/**
	 * Data una matricola ed il codice insegnamento,
	 * iscrivi lo studente al corso.
	 */
	public boolean iscriviStudenteACorso(String matricola, String nomeCorso) {
		final String sql="INSERT INTO iscrizione VALUES (?,(SELECT codins FROM corso WHERE nome=?))";
		
		try{
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, Integer.parseInt(matricola));
			st.setString(2, nomeCorso);
	
			ResultSet res = st.executeQuery();
			if(res.next())
				return true;
			return false;
		}catch (SQLException e){
			// e.printStackTrace();
			throw new RuntimeException("Errore Db");
		}

	}

}
