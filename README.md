## Teste de fluxos de cadastro, compra por boleto e verificação de boleto gerado no pedido

### Aplicativo da Centauro, versão 1.9.30

Os usuários são gerados através da API Faker e uma classe com código gerador de CPF válido.

O projeto é feito em Java rodando com Maven e TestNG como TestRunner.

Considerando que o ambiente local tenha as dependencias adequadas, como JDK, Maven e Android SDK, na raiz do projeto o seguinte comando deve ser executado:

`mvn clean test`

Após execuções de teste, o relatório de execução pode ser consultado com o comando:

`allure serve`

Que vai rodar um servidor local e abrir uma página de relatório no browser padrão.

### Parâmetros de execução

O arquivo config.properties define dados de configuração: TIMEOUT, device e UDID.
O arquivo data.json define senha padrão, CEP, telefone e data de nascimento.


#### NOTAS SOBRE COMPORTAMENTO DOS TESTES E APLICAÇÃO

>  Devido a um problema de comportamento que causava inconsistência na execução do teste, o CEP ficou fixado.

> Dado há uma diferença de comportamento, a tela de consulta do pedido pode renderizar com o bloco de informações do pagamento no topo da tela ou mais abaixo. Possivelmente por conta de requisições assíncronas. Para isso foi colocado uma condição que se adapta ao caso nesse ponto, prevenindo quebra do fluxo.

> Ao selecionar a opção de ver boleto, uma página html será carregada. Pensando que o teste deve ser performático e o ambiente preparado adequadamente para integração contínua, não inclui a etapa de seleção do app padrão para essa tarefa e estou considerando o próprio aplicativo da Centauro, carregando uma webview. Assim, a primeira execução pode falhar num device que não tenha essa opção definida.


#### NOTAS SOBRE IMPLEMENTAÇÃO DE CÓDIGO

> Como o Cucumber cria uma nova instância de todas as classes definidas como step definitions, não é suportada herança. Para evitar boilerplate de métodos instanciando e derrubando driver por cenário, a abordagem utilizada foi de instanciar o PageObject HomeScreen em cada classe de Steps  e partir dele para as demais.
> Além do PageObjects, o projeto também utiliza uma adaptação do pattern Robot

#### Informações adicionais

Como a massa é gerada dinamicamente há um arquivo output_customer.txt onde são inseridos os emails e CPF das contas geradas, para posterior consulta se preciso.


#### Considerações finais

Ao longo da construção do projeto e execução dos testes passei por diversos problemas:
-  diferentes tempos de resposta pro mesma consulta de produto, que causava timeout e falha;
-  tela de detalhes do pedido, que hora apresentava o boleto no topo da tela e ora na parte inferior da tela;
-  e principalmente a diferença do comportamento no final do fluxo de compra, que ora mostra o botão de consultar boleto, ora mostra detalhes do pedido sem apresentar o boleto na tela. * Aqui, mesmo com a troca por alguns CEPs ou utilizando o CEP que funcionava anteriormente passou a não funcionar mais.


### Links:

- [Thread sobre a restrição de herança no Cucumber](https://groups.google.com/forum/#!topic/cukes/ke7MhnjqQGQ)
- [Robot Pattern](https://jakewharton.com/testing-robots/)