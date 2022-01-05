Feature: obtener lista de recursos
       Yo  usuario
       Quiero poder consultar la lista de recursos disponible
       Para utilizar la información en mis informes

  Scenario: obtener lista de recursos
    When el usuario ejecute la petición para obtener la lista completa de recursos
    Then el usuario deberá obtener la lista completa de recursos

  Scenario: error en la petición
    When el usuario ejecute la petición con "$&_:sdh&%$7*" como criterio de busqueda
    Then el usuario deberá obtener un errror