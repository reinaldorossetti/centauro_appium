#language:pt
@NovaConta
Funcionalidade: Criação de Novas Contas.

  @CT-NC-01
  Cenário: Criando uma nova conta.
    Dado que o cliente acesse a tela de cadastro de usuario
    Quando preencher os dados do formulario e realizo seu processamento
    Então devo realizar o login com sucesso
    #E apresentar o menu de usuario
