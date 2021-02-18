# Class Diagrams

On this section, you can find images and code for class diagrams used on project.

## Raw Class Diagrams

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
 + setNombre(String): void
 + setVolumen(double): void
 + setPeso(double): void
}

class PerecederoRefrigeracion {
 - maxTemp: double
 + getMaxTemp(): int
 + setMaxTemp(double): void
 + toString(): String
 + equals(Producto): boolean
}

class PerecederoNoRefrigeracion {
 - resisteCalor: boolean
 + getResisteCalor(): boolean
 + setResisteCalor(boolean): void
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

### Extensión Producto

```
@startuml
abstract class Producto {
 # nombre: String
 # peso: double
 # volumen: double
 # inflamable: boolean
 + getNombre(): String
 + getVolumen(): double
 + getPeso(): double
 + getInflamabilidad(boolean): void
 + setNombre(String): void
 + setVolumen(double): void
 + setPeso(double): void
 + setInflamabilidad(boolean): void
}

class PerecederoRefrigeracion {
 - maxTemp: double
 - tipo: String
 - fechaVencimiento: String
 + getFechaVencimiento(): String
 + getTipo(): String
 + getMaxTemp(): int
 + setMaxTemp(double): void
 + setTipo(String): void
 + setFechaVencimiento(String): void
 + toString(): String
 + equals(Producto): boolean
}

class PerecederoNoRefrigeracion {
 - resisteCalor: boolean
 - tipo: String
 - fechaVencimiento: String
 + getFechaVencimiento(): String
 + getTipo(): String
 + getResisteCalor(): boolean
 + setResisteCalor(boolean): void
 + setTipo(String): void
 + setFechaVencimiento(String): void
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

### Extensión Contenedor

```
@startuml
class Contenedor {
 # cargamentos: ArrayList<Cargamento>
 # capacidadVolumétrica: double
 # capacidadPorPeso: double
 - esExclusivo: boolean
 # obtenerCapacidadUsada(): []double
 - verificarCapacidad(double, double): boolean
 + agregarCargamento(Cargamento): void
 + eliminarCargamento(String): void
 + generarManifiesto(): String
 + toString(): String
}

class ContenedorTopSecret {
 + generarManifiesto(): String
}

class ContenedorLifo {
 + eliminarCargamento(String): void
 + eliminarUltimoCargamento(): void
}

class Cargamento {
 - producto: Producto
 - propietario: String
 - id: String
 - unidadesProducto: int
 + getUnidadesProducto(): int
 + getId(): String
 + getPropietario(): String
 + getProducto(): Producto
}

Contenedor *-- Cargamento: contains
Contenedor <|-- ContenedorTopSecret: extends
Contenedor <|-- ContenedorLifo: extends
@enduml
```
