Feature: Actualizar usuario
  Yo como administrador del sistema
  Quiero poder actualizar un usuario por medio de su id
  Para tener la información al día

  Scenario: actualización de usuario exitoso
    When el administrador del sistema ingrese "morpheus" y "leader" como nuevas credenciales al usuario de id 3
    Then se deberá mostrar el nombre como "morpheus" y "leader" como trabajo