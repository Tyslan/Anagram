package persistence;

import java.io.*;
import java.util.*;

/**
 * Created by Lorenz on 15/11/14.
 */
public class PersistenceController {
    public PersistenceController(){

    }

    public Map<String, List<String>> giveMapWords(String path){
        Map<String,List<String>> map = new TreeMap<>();

        try(BufferedReader br = new BufferedReader(new FileReader(path))) {
            for(String line; (line = br.readLine()) != null; ) {
                char[] chars = line.toCharArray();
                Arrays.sort(chars);
                String sorted = new String(chars);
                if(!map.containsKey(sorted))
                    map.put(sorted,new ArrayList<>());
                map.get(sorted).add(line);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return map;
    }


}
