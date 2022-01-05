Feature: crear usuarios
        Yo como administrador del sistema
        Quiero poder registrar usuario con sus roles
        Para contabilizar la cantidad de usuarios

  Scenario: creación de usuario exitoso
    When el administrador del sistema ingrese "morpheus" y "leader" como nuevas credenciales
    Then se deberá mostrar como nombre "morpheus" y "leader" como trabajo con un ID asignado

  Scenario: error en petición
    When el administrador del sistema envíe de forma incorrecta el cuerpo de la petición
    Then se deberá presentar un error en la petición