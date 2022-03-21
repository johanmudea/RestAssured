Feature: post de usuario
  como un usuario registrado de sistema
  necesita crear un con su nombre y trabajo
  para validar el exito de la creacion

  Scenario:
    Given el usuario esta en la pagina de creacion con el name "name" y job "leader"
    When el usuario hace la peticion de cracion de usuario
    Then el usuario debe ver un codigo de respuesta exitoso y un token de respuesta

