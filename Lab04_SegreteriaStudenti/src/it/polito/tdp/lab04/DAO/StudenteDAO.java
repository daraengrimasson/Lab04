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
			
			while(rs.next()){
				String cognome=rs.getString("cognome");
				String nome=rs.getString("nome");
				String cds=rs.getString("cds");
				s= new Studente(matricola, cognome, nome, cds);
			}
			conn.close();
			
		return s;	
		} catch(SQLException e){
			throw new RuntimeException("Errore DB");
		}
		
	}


}
