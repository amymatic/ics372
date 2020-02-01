
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class FileIO {

    //file content, exists in resources folder for now
    String filename = "resources/example.json";

    //Array of Shipment objects to store the four shipment items
    ArrayList<Shipment> shipments = new ArrayList<>();

    //this method is helper for parseFile() method, it just reads Json file and stores into a String with help of StringBuilder
    public String readFile() {
        String result = "";
        try {
            BufferedReader br = new BufferedReader(new FileReader(filename));
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();
            while (line != null) {
                sb.append(line);
                line = br.readLine();
            }
            result = sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    //shows the list of Shipment objects
    public void showShipments() {
        //testing if objects stored in array list
        for (int i = 0; i < shipments.size(); i++) {
            System.out.println(shipments.get(i).toString());
        }
    }

    //here we parse json file and store into Java objects
    public void parseFile() throws Exception {

        //call helper method
        String resourceName = readFile();

        JSONObject obj = new JSONObject(resourceName);
        JSONArray arr = obj.getJSONArray("warehouse_contents");

        for (int i = 0; i < arr.length(); i++) {

            shipments.add(new Shipment(
                    arr.getJSONObject(i).getString("warehouse_id"),
                    arr.getJSONObject(i).getString("shipment_method"),
                    arr.getJSONObject(i).getString("shipment_id"),
                    arr.getJSONObject(i).getDouble("weight"),
                    arr.getJSONObject(i).getLong("receipt_date")
            ));
        }
    }

}


