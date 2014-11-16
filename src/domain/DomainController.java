package domain;

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
    String solution;
    ResourceBundle language;

    public DomainController(){
        observers=new ArrayList<>();
        persistenceController = new PersistenceController();
        setDictionaryToEnglish();
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
        char[] chars = anagram.toCharArray();
        Arrays.sort(chars);
        String sorted = new String(chars);
        List<String> resultList = dictionary.get(sorted);
        if(resultList==null)
            setSolution(language.getString("nosolution"));
        else{
            StringBuilder result = new StringBuilder();
            resultList.forEach(str->result.append(str).append(" "));
            setSolution(result.toString());
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

    public String getSolution(){
        return solution;
    }

    private void setSolution(String solution) {
        this.solution = solution;
        notifyObservers();
    }

    public ResourceBundle getLanguage(){
        return language;
    }
}
