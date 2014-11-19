package gui;

import domain.DomainController;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import observerPattern.Observer;

import java.io.IOException;
import java.util.ResourceBundle;
import java.util.function.ObjDoubleConsumer;

public class AnagramFrame extends GridPane implements Observer{
    private DomainController domainController;
    private ResourceBundle rb;

    @FXML
    private Button btnToEnglish;
    @FXML
    private Button btnToDutch;
    @FXML
    private Button btnSolve;
    @FXML
    private ListView lstVwSolution;
    @FXML
    private Label lblSolutionLabel;
    @FXML
    private Label lblAnagram;
    @FXML
    private TextField txtAnagram;

    public AnagramFrame(DomainController dc){
        domainController=dc;
        dc.addObserver(this);
        rb = domainController.getLanguage();

        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AnagramFrame.fxml"));
            loader.setRoot(this);
            loader.setController(this);
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        lstVwSolution.setItems(domainController.getSolution());
        update();
    }

    @FXML
    public void solve(){
        domainController.findWord(txtAnagram.getText().trim());
    }
    @FXML
    public void toDutch(){
        domainController.setDictionaryToDutch();
        txtAnagram.setText("");
    }
    public void toEnglish(){
        domainController.setDictionaryToEnglish();
        txtAnagram.setText("");
    }

    @Override
    public void update() {
        rb=domainController.getLanguage();
        btnToEnglish.setText(rb.getString("english"));
        btnToDutch.setText(rb.getString("dutch"));
        btnSolve.setText(rb.getString("solve"));
        lblSolutionLabel.setText(rb.getString("solution"));
        lblAnagram.setText(rb.getString("anagram"));
    }
}
