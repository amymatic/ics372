import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/*
NOTES:
-Shipment class' attributes has to be in same format as json file.
    ->ex. String warehouse_id , String shipment_method, int weight etc.

-To test in driver:

            WarehouseManager um = new WarehouseManager();
            um.readInJson();
            um.writeToJson();



-At the moment this only works if the json file is below(without the outer part '{ "warehouse_contents":  }:

[
  {
    "warehouse_id": "12513",
    "shipment_method": "air",
    "shipment_id": "48934j",
    "weight": 84,
    "receipt_date": 1515354694451
  },
  {
    "warehouse_id": "15566",
    "shipment_method": "truck",
    "shipment_id": "1adf4",
    "weight": 354,
    "receipt_date": 1515354694451
  },
  {
    "warehouse_id": "15566",
    "shipment_method": "ship",
    "shipment_id": "1a545",
    "weight": 20.6,
    "receipt_date": 1515354694451
  },
  {
    "warehouse_id": "336558",
    "shipment_method": "rail",
    "shipment_id": "85545",
    "weight": 760,
    "receipt_date": 1515354694451
  }
]
 */

public class WarehouseManager {

    Gson gson;
    Shipment[] shipments;
    Path path;

    public WarehouseManager(){
        gson = new GsonBuilder().setPrettyPrinting().create();
    }

    public void readInJson() throws Exception {

        //text file to read in json
        String inputFile = "example.json";
        path = new File(inputFile).toPath();

        //read json and store in to list of Shipment objects
        try (Reader reader = Files.newBufferedReader(path, StandardCharsets.UTF_8)) {
            shipments =  gson.fromJson(reader, Shipment[].class);

            //prints shipment objects(testing purpose, can be removed afterward)
            for (int i = 0; i < shipments.length; i++) {
                System.out.println(shipments[i]);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void writeToJson() throws Exception{

        //text file to export json
        String fileName2 = "exampleOutput.json";
        path = Paths.get(fileName2);

        //write json file
        try (Writer writer = Files.newBufferedWriter(path, StandardCharsets.UTF_8)) {
            gson.toJson(shipments, writer);
        }
    }
}
