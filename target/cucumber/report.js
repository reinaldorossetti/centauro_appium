$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("classpath:stepsdefinitions/verificar_compra_por_boleto.feature");
formatter.feature({
  "name": "Verify order Billet payment method",
  "description": "",
  "keyword": "Feature"
});
formatter.scenario({
  "name": "paying method Billet",
  "description": "",
  "keyword": "Scenario",
  "tags": [
    {
      "name": "@billet"
    }
  ]
});
formatter.step({
  "name": "I have an order",
  "keyword": "Given "
});
formatter.match({
  "location": "stepsdefinitions.VerificarCompraPorBoletoSteps.i_have_an_order()"
});
formatter.result({
  "status": "passed"
});
formatter.afterstep({
  "status": "passed"
});
formatter.step({
  "name": "I check my last order",
  "keyword": "When "
});
formatter.match({
  "location": "stepsdefinitions.VerificarCompraPorBoletoSteps.i_check_my_last_order()"
});
formatter.result({
  "status": "passed"
});
formatter.afterstep({
  "status": "passed"
});
formatter.step({
  "name": "I shall verify the billet is right",
  "keyword": "Then "
});
formatter.match({
  "location": "stepsdefinitions.VerificarCompraPorBoletoSteps.i_shall_verify_the_billet_is_right()"
});
formatter.result({
  "status": "passed"
});
formatter.afterstep({
  "status": "passed"
});
formatter.after({
  "status": "passed"
});
});