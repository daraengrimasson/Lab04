package it.polito.tdp.lab04.controller;

import java.net.URL;
import java.util.ResourceBundle;

import it.polito.tdp.lab04.model.Model;
import it.polito.tdp.lab04.model.Studente;
import it.polito.tdp.lab04.model.Corso;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class SegreteriaStudentiController {
	Model model;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<String> comboCorso;

    @FXML
    private Button btnCercaIscrittiCorso;

    @FXML
    private TextField txtMatricola;

    @FXML
    private ImageView btnCercaNome;

    @FXML
    private TextField txtNome;

    @FXML
    private TextField txtCognome;

    @FXML
    private Button btnCercaCorsi;

    @FXML
    private Button btnIscrivi;

    @FXML
    private TextArea txtResult;

    @FXML
    private Button btnReset;

    @FXML
    void doCercaCorsi(ActionEvent event) {
    	String matricola = txtMatricola.getText();
    	if(matricola.length()<6){
    		txtResult.appendText("Matricola non valida\n");
    		return;
    	}
    	Studente s= model.trovaStudente(matricola);
    	
    	if(s==null){
    		txtResult.appendText("Matricola "+matricola+" non trovata \n");
    	}
    	else{
    		for(Corso c : model.getCorsiDiStudente(matricola)){
    			txtResult.appendText(c.getCodice()+"\t"+c.getCrediti()+"\t"+c.getNome()+"\t"+c.getPd()+"\n");
    		}
    	}
    }

    
    /**
     * Implementare la ricerca degli studenti iscritti ad un corso
     * @param event
     */
    @FXML
    void doCercaIscrittiCorso(ActionEvent event) {
    	String nomeCorso=comboCorso.getValue();
    	if(nomeCorso.length()>2){
    	for(Studente s: model.getIscrittiCorso(nomeCorso)){
    		txtResult.appendText(+s.getMatricola()+"\t"+s.getNome()+"\t"+s.getCognome()+"\t"+s.getCDS()+"\n");
    	}
    	}else
    		txtResult.setText("Corso selezionato non valido"+"\n");
    	
    }

    @FXML
    void doCercaNome(MouseEvent event) {
    	if(!txtMatricola.getText().matches("[0-9]*") || txtMatricola.getText().length()<6){
    		txtResult.setText("Matricola non valida!");
    		return;
    	}
    	if(txtMatricola.getText().isEmpty()){
    		txtResult.setText("Inserisci matricola!");
    		return;
    	}
    	Studente stemp= model.trovaStudente(txtMatricola.getText() );
    	if(stemp==null){
    		txtResult.setText("Matricola non trovata!");
    		return;
    	} else{
    		if(comboCorso.getValue().compareTo("")==0){
    			txtNome.setText(stemp.getNome());
    	    	txtCognome.setText(stemp.getCognome());
    		} else{
    			if(model.controlStudenteiscritto(txtMatricola.getText(), comboCorso.getValue())){
    				txtResult.setText("Studente già iscritto al corso selezionato"+"\n");
    				txtNome.setText(stemp.getNome());
    	    		txtCognome.setText(stemp.getCognome());
    	    		}
    			else{
    				txtResult.setText("Studente non iscritto al corso selezionato"+"\n");
    				txtNome.setText(stemp.getNome());
    	    		txtCognome.setText(stemp.getCognome());
    			}
    		}
    	}
    }

    
    /**Inserisco lo studente nella base dati
	  *Se e solo se lo studente è già presente nella tabella 'studente'
	  *Se cosi è, riempio la tabella 'iscrizione'
	 **/
    @FXML
    void doIscrivi(ActionEvent event) {
    	if(comboCorso.getValue().compareTo("")!=0){
    		if(!txtMatricola.getText().matches("[0-9]*") || txtMatricola.getText().length()<6){
        		txtResult.setText("Matricola non valida!");
        		return;
        	}
        	if(txtMatricola.getText().isEmpty()){
        		txtResult.setText("Inserisci matricola!");
        		return;
        	}
        	if(model.controlStudenteiscritto(txtMatricola.getText(), comboCorso.getValue()))
				txtResult.setText("Studente già iscritto al corso selezionato"+"\n");
        	else{
        		if(model.controlStudentePresente( Integer.parseInt(txtMatricola.getText()) )){
        			model.iscriviStudente(txtMatricola.getText(), comboCorso.getValue());
        			txtResult.setText("Studente iscritto al corso!");
        		}else{
        			txtResult.setText("Studente non presente!");
        		}
        	}
    	}else{
    		txtResult.setText("Nessun corso inserito!");
    	}
    }

    @FXML
    void doReset(ActionEvent event) {
    	txtResult.clear();
    	txtMatricola.clear();
    	txtNome.clear();
    	txtCognome.clear();
    }

    @FXML
    void initialize() {
        assert comboCorso != null : "fx:id=\"comboCorso\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert btnCercaIscrittiCorso != null : "fx:id=\"btnCercaIscrittiCorso\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert txtMatricola != null : "fx:id=\"txtMatricola\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert btnCercaNome != null : "fx:id=\"btnCercaNome\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert txtNome != null : "fx:id=\"txtNome\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert txtCognome != null : "fx:id=\"txtCognome\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert btnCercaCorsi != null : "fx:id=\"btnCercaCorsi\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert btnIscrivi != null : "fx:id=\"btnIscrivi\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert btnReset != null : "fx:id=\"btnReset\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        
  
    }

	public void setModel(Model model) {
		this.model=model;
		comboCorso.getItems().addAll(model.getListaCorsi());
		
	}
}
