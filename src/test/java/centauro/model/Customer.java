package centauro.model;

import java.io.FileReader;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import com.github.javafaker.Faker;
import java.util.Random;


public class Customer {
	public final String name;
	public final String lastName;
	public final String cep;
	public final String cpf;
	public final String phone;
	public final String birthdate;
	public final String email;
	public final String address_number;
	public final String address_complement;
	public final String password;
	private Faker faker;
	
	
	public Customer() {
		faker = new Faker();
		name = faker.name().firstName();
		lastName = faker.name().lastName();
		cpf = new GeraCpf().geraCPFFinal();
		phone = returnFromJsonData("phone");
		birthdate = returnFromJsonData("birthdate");
		cep = returnFromJsonData("cep");
		address_number = String.valueOf(numeroAleatorio(300, 429));
		address_complement = String.valueOf(numeroAleatorio(1, 94));
		email = faker.internet().emailAddress();
		password = returnFromJsonData("password");
	}

	public int numeroAleatorio(int min, int max){
		Random rand = new Random();
		int randomNum = rand.nextInt((max - min) + 1) + min;
		return randomNum;
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


