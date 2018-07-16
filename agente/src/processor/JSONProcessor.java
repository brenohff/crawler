package processor;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class JSONProcessor {

    private List<JSONObject> items = new ArrayList<JSONObject>();

    public void readFolder(final File folder) throws Exception {
        for (final File fileEntry : folder.listFiles()) {
            if(!fileEntry.isDirectory() && isJsonFile(fileEntry.getName())){
                readFile(fileEntry);
            }
        }
    }

    public void readFile(File file) throws Exception{
        Scanner sc = new Scanner(file);

        String json = "";
        while (sc.hasNextLine())
            json += sc.nextLine();

        JSONArray array = (JSONArray) JSONValue.parse(json);

        for(Object object : array){
            items.add((JSONObject) object);
        }
    }

    private boolean isJsonFile(String fileName){
        return fileName.endsWith("json");
    }

    public List<JSONObject> getItems(){
        return new ArrayList<JSONObject>(items);
    }

    public void setItems(List<JSONObject> jsonObjects){
        items = new ArrayList<JSONObject>(jsonObjects);
    }

}
