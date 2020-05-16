package centauro.screen;

import java.util.List;

import centauro.model.Customer;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class RegisterScreen extends BaseScreen {

	public RegisterScreen(AppiumDriver<MobileElement> driver) {
		super(driver);
	}

	@AndroidFindBy(id = "br.com.sbf.centauro:id/pf_et_documento_cpf")
	private MobileElement inputCpf;
	
	@AndroidFindBy(id = "br.com.sbf.centauro:id/pf_et_nome_parte_um")
	private MobileElement inputNome;
	
	@AndroidFindBy(id = "br.com.sbf.centauro:id/pf_et_nome_parte_dois")
	private MobileElement inputSobrenome;
	
	@AndroidFindBy(id = "br.com.sbf.centauro:id/pf_et_genero")
	private MobileElement selectGenero;
	
	@AndroidFindBy(id = "br.com.sbf.centauro:id/pf_et_genero")
	private List<MobileElement> listGenero;
	
	@AndroidFindBy(id = "br.com.sbf.centauro:id/pf_et_data_nascimento")
	private MobileElement inputDataNascimento;
	
	@AndroidFindBy(id = "br.com.sbf.centauro:id/pf_et_telefone_principal")
	private MobileElement inputTelefone;
	
	@AndroidFindBy(id = "br.com.sbf.centauro:id/bt_continuar_cadastro_pf")
	private MobileElement btnContinuar;
	
	@AndroidFindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.ListView/android.widget.TextView[2]")
	private MobileElement masculino;

	@AndroidFindBy(id = "br.com.sbf.centauro:id/et_cep")
	private MobileElement inputCep;
	
	@AndroidFindBy(id = "br.com.sbf.centauro:id/et_endereco_rua")
	private MobileElement  inputEnderecoRua;
	
	@AndroidFindBy(id = "br.com.sbf.centauro:id/et_endereco_numero")
	private MobileElement inputEnderecoNumero;
	
	@AndroidFindBy(id = "br.com.sbf.centauro:id/et_endereco_complemento")
	private MobileElement inputEnderecoComplemento;
	
	@AndroidFindBy(id = "br.com.sbf.centauro:id/et_endereco_bairro")
	private MobileElement inputEnderecoBairro;
	
	@AndroidFindBy(id = "br.com.sbf.centauro:id/et_endereco_cidade")
	private MobileElement inputEnderecoCidade;
	
	@AndroidFindBy(id = "br.com.sbf.centauro:id/et_ponto_referencia")
	private MobileElement inputEnderecoReferencia;
	
	@AndroidFindBy(id = "br.com.sbf.centauro:id/et_acesso_email")
	private MobileElement inputEmail;
	
	@AndroidFindBy(id = "br.com.sbf.centauro:id/bt_finalizar_cadastro")
	private MobileElement btnFinalizar;
	
	@AndroidFindBy(id = "br.com.sbf.centauro:id/et_acesso_senha")
	private MobileElement inputSenha;
	
	public void fillForm(Customer customer) {
		fillFirstPage(customer);
		fillSecondPage(customer);
	}
	
	public void fillFirstPage(Customer customer) {
		sendValue(inputCpf, customer.cpf);
		sendValue(inputNome, customer.name);
		sendValue(inputSobrenome, customer.lastName);
		hideKeyboard();
		clickOn(selectGenero);
		clickOn(masculino);
		sendValue(inputTelefone, customer.phone);
		sendValue(inputDataNascimento, customer.birthdate);
		
		hideKeyboard();
		clickOn(btnContinuar);
	}
	
	public void fillSecondPage(Customer customer) {
		sendValue(inputCep, customer.cep); // Should auto fill street, neighborhood, city and state
		sendValue(inputEnderecoNumero, customer.address_number);
		sendValue(inputEnderecoComplemento, customer.address_complement);
		hideKeyboard();
		// errou aqui, adicionei um swipe em caso de erro.
		sendValue(inputEmail, customer.email);
		hideKeyboard();

		moveToBottom();
		sendValue(inputSenha, customer.password);
		System.out.println("Email: " + customer.email + " - Password: " + customer.password);
		hideKeyboard();
		clickOn(btnFinalizar);
	}
}
