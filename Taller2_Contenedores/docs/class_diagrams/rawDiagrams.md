# Raw Class Diagrams

To generate diagrams go to http://www.plantuml.com/

### Producto

```
@startuml
abstract class Producto {
 # nombre: String
 # peso: double
 # volumen: double
 + getNombre(): String
 + getVolumen(): double
 + getPeso(): double
 + setNombre(): String
 + setVolumen(): double
 + setPeso(): double
}

class PerecederoRefrigeracion {
 - maxTemp: double
 + getMaxTemp(): int
 + setMaxTemp(): void
 + toString(): String
 + equals(Producto): boolean
}

class PerecederoNoRefrigeracion {
 - resisteCalor: boolean
 + getResisteCalor(): boolean
 + setResisteCalor(): void
 + toString(): String
 + equals(Producto): boolean
}

class NoPerecedero {
 - toxicidad: int
 - MAX_TEMP: double 50.0
 + getMAX_TEMP(): double
 + getToxicidad(): int
 + setToxicidad(int): void
 + toString(): String
 + equals(Producto): boolean
}

Producto <|-- PerecederoRefrigeracion
Producto <|-- NoPerecedero
Producto <|-- PerecederoNoRefrigeracion
@enduml
```

### Contenedor

```
@startuml
class Contenedor {
 - cargamentos: ArrayList<Cargamento>
 - capacidadVolumétrica: double
 - capacidadPorPeso: double
 - esExclusivo: boolean
 - obtenerCapacidadUsada(): []double
 - verificarCapacidad(double, double): boolean
 + agregarCargamento(Cargamento): void
 + eliminarCargamento(String): void
 + generarManifiesto(): String
 + toString(): String
}

class Cargamento {
 - producto: Producto
 - propietario: String
 - id: String
 - unidadesProducto: int
 +getUnidadesProducto(): int
 +getId(): String
 +getPropietario(): String
 +getProducto(): Producto
}

Contenedor *-- Cargamento: contains
@enduml
```
