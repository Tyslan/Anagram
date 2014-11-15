package domain;

import observerPattern.Observer;
import observerPattern.Subject;
import persistence.PersistenceController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Created by Lorenz on 15/11/14.
 */

public class DomainController implements Subject{
    List<Observer> observers;
    PersistenceController persistenceController;
    Map<String,String> dictionary;
    String solution;
    String language;

    public DomainController(){
        observers=new ArrayList<>();
        persistenceController = new PersistenceController();
        setDictionaryToEnglish();
    }

    public void setDictionaryToEnglish(){
        dictionary=persistenceController.giveMapWords("src/resources/english.txt");
        setLanguage("English");
    }

    public void setDictionaryToDutch(){
        dictionary=persistenceController.giveMapWords("src/resources/dutch.txt");
        setLanguage("Dutch");
    }

    public void findWord(String anagram){
        char[] chars = anagram.toCharArray();
        Arrays.sort(chars);
        String sorted = new String(chars);
        String result = dictionary.get(sorted);
        if(result==null)
            setSolution("No solutions");
        else
            setSolution(result);
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

    public String getSolution(){
        return solution;
    }

    private void setSolution(String solution) {
        this.solution = solution;
        notifyObservers();
    }

    public String getLanguage(){
        return language;
    }

    private void setLanguage(String language){
        this.language=language;
        notifyObservers();
    }
}
