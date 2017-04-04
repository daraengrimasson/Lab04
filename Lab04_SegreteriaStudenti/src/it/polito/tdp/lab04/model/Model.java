package it.polito.tdp.lab04.model;
import java.util.*;
import it.polito.tdp.lab04.model.Studente;
import it.polito.tdp.lab04.DAO.CorsoDAO;
import it.polito.tdp.lab04.DAO.StudenteDAO;

public class Model {
	List<Corso>corsi = new LinkedList<Corso>();
	//List<Studente> studenti= new LinkedList<Studente>();
	
	public List<String> getListaCorsi(){
		List<String> listaCodici=new ArrayList<String>();
		CorsoDAO dao=new CorsoDAO();
		for(Corso c: dao.getTuttiICorsi()){
			listaCodici.add(c.getNome());
			corsi.add(c);
		}
		return listaCodici;
	}
	
	public Studente trovaStudente(String matricola) {
		StudenteDAO dao = new StudenteDAO();
		Studente s= dao.cercaStudente(Integer.parseInt(matricola));
		return s;
	}

	public List<Studente> getIscrittiCorso(String nomeCorso) {
		Corso corso= null;
		CorsoDAO dao= new CorsoDAO();
		for(Corso c : corsi){
			if(c.getNome().compareTo(nomeCorso)==0){
				corso= c;
				break;
			}
		}
		return dao.getStudentiIscrittiAlCorso(corso);
	}

	

	public List<Corso> getCorsiDiStudente(String matricola) {
		List<Corso> corsiDiStu= new LinkedList<Corso>();
		CorsoDAO dao= new CorsoDAO();
		Corso c= null;
		if(dao.corsiPerStudente(matricola)!= null){
			for(String codC : dao.corsiPerStudente(matricola)){
				c= dao.getCorso(codC);
				corsiDiStu.add(c);
			}
			return corsiDiStu;
		}
		return null;
	}

	
	
	/**data una matricola di studente, ciclo su tutti i corsi che essa segue
	 * metto questi corsi dentro ad una lista di corsi
	 * restituisco true se lo studente  già iscritto al corso passato 
	 * */
	public boolean controlStudenteiscritto(String matricola, String nomeCorso) {
		CorsoDAO dao= new CorsoDAO();
		List<Corso> corsiDiStu = new LinkedList<Corso>();
		Corso c=null;
		
		if(dao.corsiPerStudente(matricola)!=null){
			for(String codC : dao.corsiPerStudente(matricola)){
				c=dao.getCorso(codC);
				corsiDiStu.add(c);
	
			}
			for(Corso cc : corsiDiStu){
				if(cc.getNome().compareTo(nomeCorso)==0){
					return true;
				}			
			}
		}
		return false;
	}

	
	/**
	 * Iscrive lo studente (data la sua matricola id) al corso selezionato dalla checkbox
	 * @param matricola
	 * @param nomeCorso
	 */
	public void iscriviStudente(String matricola, String nomeCorso) {
		StudenteDAO dao= new StudenteDAO();
		dao.iscriviStudenteACorso(matricola, nomeCorso);
	}

	/**
	 * Controlla, ciclando sulla lista di tutti gli studenti, se la matricola che inseriamo
	 * corrisponde nella tabella 'studente' ad uno studente già memorizzato
	 * Se cosi è, viene ritornato true
	 * @param parseInt
	 * @return
	 */
	public boolean controlStudentePresente(int matricola) {
		StudenteDAO dao= new StudenteDAO();
		Studente s= dao.cercaStudente(matricola);
		if(s!=null)
				return true;
		else
			return false;
	}
	
}
