#language:pt
@Compras
Funcionalidade: Compra de produtos na loja

Contexto: efetuando o acesso ao aplicativo
Dado que usuário efetue o login com sucesso
    
  @CT-CP-01  
  Cenário: Comprando via boleto bancario
     Dado que eu quero comprar algum produto na loja
     Quando adiciono um produto ao carrinho de compras
     E realizo a compra via boleto bancario
     Então deve visualizar o meu boleto para pagamento
     E valido os dados do boleto
