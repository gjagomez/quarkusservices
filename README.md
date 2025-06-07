# Documentación Proyecto Quarkus API REST

![Quarkus API Demo](https://github.com/gjagomez/quarkusservices/blob/main/img/1.png)

## 📋 Información General

**Proyecto:** quarkus-api  
**Framework:** Quarkus 3.3.0  
**Base de Datos:** PostgreSQL  
**Lenguaje:** Java 17+  
**Tipo:** API REST con JPA/Hibernate  

## 🎯 Descripción

API REST que gestiona **Personas** y **Direcciones** con operaciones CRUD completas. Desarrollada con Quarkus para alta performance y bajo consumo de memoria.

## 🚀 Capturas del Sistema

![Funcionamiento API](https://github.com/gjagomez/quarkusservices/blob/main/img/2.png)

![Swagger UI](https://github.com/gjagomez/quarkusservices/blob/main/img/3.png)

![Base de Datos](https://github.com/gjagomez/quarkusservices/blob/main/img/4.png)

## 📁 Estructura del Proyecto

```
quarkus-api/
├── src/main/java/com/beesion/ms/
│   ├── model/
│   │   ├── Person.java              # Entidad Persona
│   │   └── Address.java             # Entidad Dirección
│   └── test/
│       ├── repository/
│       │   ├── PersonRepo.java      # Repositorio Persona
│       │   └── AddressRepo.java     # Repositorio Dirección
│       └── resource/
│           ├── PersonResource.java  # REST Controller Persona
│           └── AddressResource.java # REST Controller Dirección
└── src/main/resources/
    └── application.properties       # Configuración
```

## 🗄️ Modelo de Datos

### Entidad Person
```java
@Entity
@Table(name = "person")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "name")
    private String name;
}
```

### Entidad Address
```java
@Entity
@Table(name = "address")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String street;
    private String city;
    private String state;
    private String postalCode;
    private String country;
    
    @Column(name = "person_id")
    private Long personId;  // Relación con Person
}
```

## 🔧 Instalación Rápida

### 1. Prerrequisitos
- Java 17+
- Maven 3.8+
- PostgreSQL 12+

### 2. Configurar Base de Datos
```bash
# Crear base de datos
sudo -u postgres createdb postgres
```

### 3. Configurar application.properties
```properties
quarkus.http.port=8080
quarkus.datasource.db-kind=postgresql
quarkus.datasource.jdbc.url=jdbc:postgresql://localhost:5432/postgres
quarkus.datasource.username=postgres
quarkus.datasource.password=chivete89
quarkus.hibernate-orm.database.generation=drop-and-create
```

### 4. Ejecutar
```bash
mvn quarkus:dev
```

**URL:** http://localhost:8080

## 🌐 Endpoints de la API

### Person Endpoints

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| GET | `/persons` | Listar todas las personas |
| GET | `/persons/{id}` | Obtener persona por ID |
| POST | `/persons` | Crear nueva persona |
| PUT | `/persons/{id}` | Actualizar persona |
| DELETE | `/persons/{id}` | Eliminar persona |

### Address Endpoints

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| GET | `/addresses` | Listar todas las direcciones |
| GET | `/addresses/{id}` | Obtener dirección por ID |
| POST | `/addresses` | Crear nueva dirección |
| PUT | `/addresses/{id}` | Actualizar dirección |
| DELETE | `/addresses/{id}` | Eliminar dirección |

## 📝 Ejemplos de Uso

### Crear Persona
```bash
curl -X POST http://localhost:8080/persons \
  -H "Content-Type: application/json" \
  -d '{"name": "Juan Pérez"}'
```

**Respuesta:**
```json
{
    "id": 1,
    "name": "Juan Pérez"
}
```

### Crear Dirección
```bash
curl -X POST http://localhost:8080/addresses \
  -H "Content-Type: application/json" \
  -d '{
    "street": "Calle Principal 123",
    "city": "Antigua Guatemala",
    "state": "Sacatepéquez",
    "postalCode": "03001",
    "country": "Guatemala",
    "personId": 1
  }'
```

### Listar Personas
```bash
curl http://localhost:8080/persons
```

**Respuesta:**
```json
[
    {
        "id": 1,
        "name": "Juan Pérez"
    }
]
```

## 🛠️ Herramientas de Desarrollo

- **Swagger UI:** http://localhost:8080/q/swagger-ui
- **Dev UI:** http://localhost:8080/q/dev
- **Health Check:** http://localhost:8080/q/health

## 📊 Códigos de Estado

| Código | Descripción |
|--------|-------------|
| 200 | OK - Operación exitosa |
| 201 | Created - Recurso creado |
| 204 | No Content - Eliminación exitosa |
| 400 | Bad Request - Datos inválidos |
| 404 | Not Found - Recurso no encontrado |
| 500 | Internal Server Error |

## 🔧 Troubleshooting

### Error de Conexión BD
```bash
# Verificar PostgreSQL
sudo systemctl status postgresql

# Verificar puerto
netstat -an | grep 5432
```

### Puerto 8080 Ocupado
```properties
# Cambiar en application.properties
quarkus.http.port=8081
```

### Recompilar en caso de errores
```bash
mvn clean compile
mvn quarkus:dev
```


## 📊 Diagrama de Tablas

```
┌─────────────────────────────────────┐    ┌─────────────────────────────────────┐
│              PERSON                 │    │             ADDRESS                 │
├─────────────────────────────────────┤    ├─────────────────────────────────────┤
│ 🔑 id          | BIGINT | PK        │    │ 🔑 id          | BIGINT | PK        │
│ 📝 name        | VARCHAR(100)       │    │ 🏠 street      | VARCHAR(255)       │
│                                     │    │ 🏙️ city        | VARCHAR(100)       │
│                                     │    │ 📍 state       | VARCHAR(100)       │
│                                     │    │ 📮 postal_code | VARCHAR(20)        │
│                                     │    │ 🌍 country     | VARCHAR(100)       │
│                                     │    │ 👤 person_id   | BIGINT | FK        │
└─────────────────────────────────────┘    └─────────────────────────────────────┘
                     │                                            │
                     │                                            │
                     └──────────── 1:N ────────────────────────────┘
                        Una persona puede tener múltiples direcciones
```

## 📋 Tabla PERSON

| Campo | Tipo | Restricciones | Descripción |
|-------|------|---------------|-------------|
| **id** | `BIGINT` | `PRIMARY KEY`, `AUTO_INCREMENT` | Identificador único de la persona |
| **name** | `VARCHAR(100)` | `NOT NULL` | Nombre completo de la persona |



## 🏠 Tabla ADDRESS

| Campo | Tipo | Restricciones | Descripción |
|-------|------|---------------|-------------|
| **id** | `BIGINT` | `PRIMARY KEY`, `AUTO_INCREMENT` | Identificador único de la dirección |
| **street** | `VARCHAR(255)` | - | Calle y número |
| **city** | `VARCHAR(100)` | - | Ciudad |
| **state** | `VARCHAR(100)` | - | Estado o departamento |
| **postal_code** | `VARCHAR(20)` | - | Código postal |
| **country** | `VARCHAR(100)` | - | País |
| **person_id** | `BIGINT` | `FOREIGN KEY` | Referencia a la tabla person |

### SQL de Creación:




### Relación Person → Address (1:N)

```
PERSON (1) ────────────────── (N) ADDRESS
    │                             │
    id ←──────────────────────── person_id
```

- **Tipo:** One-to-Many (Uno a Muchos)
- **Descripción:** Una persona puede tener múltiples direcciones
- **Clave Foránea:** `address.person_id` → `person.id`
- **Eliminación:** Si se elimina una persona, se deben eliminar sus direcciones (CASCADE)



### Tabla PERSON
```
┌────┬─────────────────┐
│ id │      name       │
├────┼─────────────────┤
│ 1  │ Juan Pérez      │
│ 2  │ María García    │
│ 3  │ Carlos López    │
└────┴─────────────────┘
```

### Tabla ADDRESS
```
┌────┬──────────────────────────┬───────────────────┬──────────────┬─────────────┬───────────┬───────────┐
│ id │         street           │       city        │    state     │ postal_code │  country  │ person_id │
├────┼──────────────────────────┼───────────────────┼──────────────┼─────────────┼───────────┼───────────┤
│ 1  │ Calle Principal 123      │ Antigua Guatemala │ Sacatepéquez │    03001    │ Guatemala │    1      │
│ 2  │ 5a Avenida Norte 25      │ Antigua Guatemala │ Sacatepéquez │    03001    │ Guatemala │    1      │
│ 3  │ Boulevard Los Próceres 15│ Guatemala         │ Guatemala    │    01001    │ Guatemala │    2      │
│ 4  │ Calzada Roosevelt 45     │ Guatemala         │ Guatemala    │    01010    │ Guatemala │    3      │
└────┴──────────────────────────┴───────────────────┴──────────────┴─────────────┴───────────┴───────────┘
```




## 📚 Tecnologías

- **Quarkus 3.3.0** - Framework principal
- **Hibernate ORM Panache** - Acceso a datos
- **PostgreSQL** - Base de datos
- **RESTEasy Reactive** - API REST
- **Jackson** - Serialización JSON
- **Maven** - Gestión de dependencias


*Documentación generada para el proyecto Quarkus API REST*
