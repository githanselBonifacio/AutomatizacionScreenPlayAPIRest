Feature: busqueda de pokemón
    Yo como maestro pokemón
    quiero poder consultar las habilidades de mi pokemón
    para planear mis estrategias de batalla

    Scenario: Busqueda sin coinsidencias
        When el maestro pokemón ejecute la petición con el nombre "anubis"  para buscar un pokemón
        Then el maestro pokemón no obtendrá coincidencia en la busqueda

    Scenario Outline:busqueda de pokemon por nombre
        When el maestro pokemón ejecute la petición con el nombre "<pokemonName>"  para buscar un pokemón
        Then el maestro pokemón debería obtener "<pokemonName>" en la especie e información adicional
        Examples:
            | pokemonName |
            | kabutops    |
            | anorith     |





