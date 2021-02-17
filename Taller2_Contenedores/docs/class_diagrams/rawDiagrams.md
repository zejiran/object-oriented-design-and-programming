# Raw Class Diagrams

To generate diagrams go to http://www.plantuml.com/

### Producto

```
@startuml
abstract class Producto {
 # nombre: String
 # peso: double
 # volumen: double
 +toString(): String
 +equals(Producto): boolean
}

class PerecederoRefrigeracion {
 -maxTemp: double
}

class PerecederoNoRefrigeracion {
 - resisteCalor: boolean
}

class NoPerecedero {
 - toxicidad: int
 - MAX_TEMP: int 50
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
 - capacidadVolumétrica: int
 - capacidadPorPeso: int
 - esExclusivo: bool
 + agregarCargamento(): void
 + generarManifiesto(): String
 + toString(): String
}

class Cargamento {
 - producto: Producto
 - propietario: String
 - id: String
 - unidadesProducto: int
}

Contenedor *-- Cargamento: contains
@enduml
```

