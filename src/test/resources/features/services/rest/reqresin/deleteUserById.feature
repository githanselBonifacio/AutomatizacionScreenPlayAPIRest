Feature: eliminar usuarios
  Yo como administrador del sistema
  Quiero poder eliminar un usuario por medio de su id
  Para depurar los usuarios inactivos

  Scenario: eliminar usuario
    When el administrador del sistema ingrese 2 como id para eliminar el registro del usuario
    Then el sistema debe borrar el registro satisfactoriamente