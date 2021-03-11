# Sequence Diagrams

On this section, you can find images and code for sequence diagrams used on project.

## Raw Sequence Diagrams

To generate diagrams go to http://www.plantuml.com/

### Diagrama de secuencia de la interacción de avance diario

```
@startuml

group Inicialización
Jugador -> Motor: new Ciudad(fecha, presupuesto, habitantes) 
activate Jugador

Motor -> Motor: new Grilla(tamaño)
loop tamaño veces
Motor -> Motor: new CasillaLibre()
end
end
loop true
Motor -> Motor:calcularGastoTotal()
Motor -> Motor:recaudarImpuestos()
Motor -> Motor:calcularFlujoDeHabitantes():
Motor -> Motor: actualizarEstado()
alt Se termina un día
Jugador <-- Motor: response reporte
else Jugador hace acción
Jugador -> Motor: cambiarPorcentajeImpuestos()
Jugador -> Motor: cambiarPresupuestoDeSectores()
alt jugador crea una nueva edificación
Motor -> Motor: getCoordenadas()
Motor -> Motor: constructorTipoCasillaSeleccionada()
end
end
end

Jugador -> Motor: quit game

deactivate Jugador

@enduml
```