import model.Item;
import org.json.simple.JSONObject;
import processor.JSONProcessor;
import repository.ItemRepository;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws Exception {

        if(args.length == 0){
            System.exit(0);
        }

        File folder = new File(args[0]);

        JSONProcessor jsonProcessor = new JSONProcessor();

        jsonProcessor.readFolder(folder);

        List<Item> items = new ArrayList<Item>();

        for(JSONObject jsonObject : jsonProcessor.getItems()){
            items.add(new Item(jsonObject));
        }

        ItemRepository itemRepository = new ItemRepository();

        itemRepository.saveAll(items);

        System.exit(0);

    }

}
