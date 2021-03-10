# Class Diagrams

On this section, you can find images and code for class diagrams used on project.

## Raw Class Diagrams

To generate diagrams go to http://www.plantuml.com/

### Modelo del mundo del problema

```
@startuml
package motor <<Folder>> {
  class Ciudad #aliceblue {
    + presupuestos: ArrayList<presupuestoSalud: double, presupuestoSeguridad: double, presupuestoTraficoYTransporte: double, presupuestoRecreacion: double, presupuestoEducacion: double>
    + presupuestoTotal: double
    + casillasLibres: int
    + porcentajeImpuestos: double
    + indicadoresCalidadYServicios: HashMap<String, int>
    + Ciudad(fechaInicial: Timestamp, habitantesIniciales: int, presupuestoInicial: double): void
    + cambiarPorcentajeImpuestos(double): void
    + cambiarPresupuestoDeSectores(double, double, double, double, double): void
    + recaudarImpuestos(): void
    + calcularGastoTotal(): double
    + tiempo: Timestamp
    + cantidadDeHabitantes: int
    + actualizarEstado(): void
    + calcularFlujoDeHabitantes(): int
  }

  class Grilla #aliceblue {
    ~ Grilla(tamaño: int): void
    - tamaño: int
  }

  abstract class Casilla #aliceblue {
    - tipoDeConstruccion: String
    - coordenadaX: int
    - coordenadaY: int
    - dineroRequerido: double
    + casillaTerminada : boolean
    + casillaEnProgreso: boolean
    + getCoordenadas(): ArrayList
    + getGastoActual(): double
  }

package casillas <<Folder>> {
  class CasillaLibre #aliceblue {
    + CasillaLibre(): void
    - dineroRequerido: double 0
    + casillaTerminada : boolean false
    + casillaEnProgreso: boolean false
  }

  class Residencial #aliceblue {
    + Residencial(tipoDeConstruccion: String): void
    - construirCasa(): void
    - construirEdificio(): void
  }

  class Comercial #aliceblue {
    + Comercial(tipoDeConstruccion: String): void
    - construirTienda(): void
    - construirCine(): void
    - construirRestaurante(): void
    - construirCentroComercial(): void
    - construirOficinaYSimilares(): void
  }

  class Industrial #aliceblue {
    + Industrial(tipoDeConstruccion: String): void
    - construirBodega(): void
    - construirFabrica(): void
  }

  class Calle #aliceblue {
    + Calle(tipoDeConstruccion: String): void
    + demolerCalle(): void
    - construirSencilla(): void
    - construirAvenida(): void
    - construirAutopista(): void
  }

  class EdificioPublico #aliceblue {
    - casillasRequeridas: int
    - calidad: String
    + influencia: String
    + areaDeInfluencia: int
    + EdificioPublico(tipoDeConstruccion: String): void
    - construirColegio(): void
    - construirParque(): void
    - construirHospital(): void
    - construirEstacionPolicia(): void
  }
}
}

Ciudad  o--  Grilla
Grilla  o-- Casilla: "  casillas: ArrayList<Casilla>"
Casilla <|-- CasillaLibre
Casilla <|-- Residencial
Casilla <|-- Comercial
Casilla <|-- Industrial
Casilla <|-- Calle
Casilla <|-- EdificioPublico
@enduml
```
