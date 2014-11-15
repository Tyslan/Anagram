package persistence;

import java.io.*;
import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by Lorenz on 15/11/14.
 */
public class PersistenceController {
    public PersistenceController(){

    }

    public Map<String, String> giveMapWords(String path){
        Map<String,String> map = new TreeMap<>();

        try(BufferedReader br = new BufferedReader(new FileReader(path))) {
            for(String line; (line = br.readLine()) != null; ) {
                char[] chars = line.toCharArray();
                Arrays.sort(chars);
                String sorted = new String(chars);
                map.put(sorted,line);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return map;
    }


}
