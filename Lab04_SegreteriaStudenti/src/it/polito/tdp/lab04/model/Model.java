package it.polito.tdp.lab04.model;
import java.util.*;
import it.polito.tdp.lab04.model.Studente;
import it.polito.tdp.lab04.DAO.CorsoDAO;
import it.polito.tdp.lab04.DAO.StudenteDAO;

public class Model {
	
	
	public List<String> getListaCorsi(){
		List<String> listaCodici=new ArrayList<String>();
		CorsoDAO cd=new CorsoDAO();
		for(Corso c: cd.getTuttiICorsi()){
			listaCodici.add(c.getNome());
		}
		return listaCodici;
	}
	
	public Studente cercaStudente(int matricola){
		StudenteDAO sd=new StudenteDAO();
		Studente ris= sd.cercaStudente(matricola);
		if(ris==null)
			return null;
		return ris;
	}
	
}
