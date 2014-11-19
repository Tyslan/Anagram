package domain;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import observerPattern.Observer;
import observerPattern.Subject;
import persistence.PersistenceController;

import java.util.*;

/**
 * Created by Lorenz on 15/11/14.
 */

public class DomainController implements Subject{
    List<Observer> observers;
    PersistenceController persistenceController;
    Map<String,List<String>> dictionary;
    ObservableList<String> solution;
    ResourceBundle language;

    public DomainController(){
        observers=new ArrayList<>();
        persistenceController = new PersistenceController();
        setDictionaryToEnglish();
        solution=FXCollections.observableList(new ArrayList<String>());
    }

    public void setDictionaryToEnglish(){
        dictionary=persistenceController.giveMapWords("src/resources/english.txt");
        language=ResourceBundle.getBundle("resources.screen_en_US",new Locale("en","US"));
        notifyObservers();
    }

    public void setDictionaryToDutch(){
        dictionary=persistenceController.giveMapWords("src/resources/dutch.txt");
        language=ResourceBundle.getBundle("resources.screen_nl_BE",new Locale("nl","BE"));
        notifyObservers();
    }

    public void findWord(String anagram){
        char[] chars = anagram.toLowerCase().toCharArray();
        Arrays.sort(chars);
        String sorted = new String(chars);
        List<String> resultList = dictionary.get(sorted);
        if(resultList==null)
            setSolution(new ArrayList<>());
        else{
            setSolution(resultList);
        };
    }

    @Override
    public void addObserver(Observer O) {
        observers.add(O);
    }

    @Override
    public void removeObserver(Observer O) {
        observers.remove(O);
    }

    @Override
    public void notifyObservers() {
        observers.forEach(ob->ob.update());
    }

    public ObservableList<String> getSolution(){
        return solution;
    }

    private void setSolution(List solution) {
        this.solution.clear();
        this.solution.addAll(solution);
    }

    public ResourceBundle getLanguage(){
        return language;
    }
}
