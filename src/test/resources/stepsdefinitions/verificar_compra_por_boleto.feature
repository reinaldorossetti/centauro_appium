#language:pt
@OrdemDePagamento
Funcionalidade: Validar minha ordem de pagamento

Contexto: efetuando o acesso ao aplicativo
Dado que usuário efetue o login com sucesso
      
  Cenário: Validando ordem de pagamento via boleto bancario
     Dado que eu tenha efetuado um pagamento via boleto bancario
     Quando acesso a tela de ordem de pagamento
     E seleciono a ordem de pagamento efetuada
     Então devo visualizar os dados boleto para pagamento
     E valido os dados do boleto
