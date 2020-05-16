package centauro.model;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;

public class Product {

    public String product_name;

    public Product() {
        product_name = returnFromJsonData("product_name");
    }

    public String returnFromJsonData(String attribute) {
        JSONParser jsonParser = new JSONParser();
        String value = null;

        try {
            Object obj = jsonParser.parse(new FileReader(Customer.class.getClassLoader().getResource("data.json").getFile()));
            JSONObject jsonObject = (JSONObject)obj;
            value = (String) jsonObject.get(attribute);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return value;
    }

}
