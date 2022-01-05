Feature: buscar porkemos por habilidad

  Scenario Outline: busqueda de pokemones por habilidades
      When el maestro pokemón ejecute la petición con la habilidad "<ability>"  para buscar pokemones
      Then el maestro pokemón debería obtener los pokemones con la habilidad "<ability>"
      Examples:
        | ability    |
        | shadow-tag |
        | drought    |

  Scenario: busqueda sin coincidencias
    When el maestro pokemón ejecute la petición con la habilidad "carnage"  para buscar pokemones
    Then el maestro pokemón no obtendrá coincidencia en la busqueda de esa habilidad