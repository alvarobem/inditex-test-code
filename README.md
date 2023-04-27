# Tabla de contenido 
1. [Enunciado](#enunciado)
2. [Stack tecnológico](#stack)
3. [API](#api)
4. [Arquitectura propuesta](#arquitectura-propuesta)
5. [Arrancar el proyecto](#arrancar-el-proyecto)
6. [Testing](#testing)
   1. [Test unitarios](#test-unitarios)
   2. [Test de integración](#test-de-integración)
7. [Versionado de la Base de Datos](#versionado-base-de-datos)
8. [Internalización](#internalización)


# Enunciado

En la base de datos de comercio electrónico de la compañía disponemos de la tabla PRICES que refleja el precio final (pvp) y la tarifa que aplica a un producto de una cadena entre unas fechas determinadas. A continuación se muestra un ejemplo de la tabla con los campos relevantes:

PRICES

|BRAND_ID   | START_DATE           | END_DATE             | PRICE_LIST |    PRODUCT_ID    | PRIORITY  |           PRICE           |   CURR   |
| :--------:|:--------------------:|:--------------------:|:----------:|:----------------:|:---------:|:-------------------------:|:--------:|
|  1        | 2020-06-14-00.00.00  | 2020-12-31-23.59.59  |     1      |      35455       |     0     |           35.50           |   EUR    |
|  1        | 2020-06-14-15.00.00  | 2020-06-14-18.30.00  |     2      |      35455       |     1     |           25.45           |   EUR    |
|  1        | 2020-06-15-00.00.00  | 2020-06-15-11.00.00  |     3      |      35455       |     1     |           30.50           |   EUR    |
|  1        | 2020-06-15-16.00.00  | 2020-12-31-23.59.59  |     4      |      35455       |     1     |           38.95           |   EUR    |


Campos:

**BRAND_ID**: foreign key de la cadena del grupo (1 = ZARA).

**START_DATE** , END_DATE: rango de fechas en el que aplica el precio tarifa indicado.

**PRICE_LIST**: Identificador de la tarifa de precios aplicable.

**PRODUCT_ID**: Identificador código de producto.

**PRIORITY**: Desambiguador de aplicación de precios. Si dos tarifas coinciden en un rago de fechas se aplica la de mayor prioridad (mayor valor numérico).

**PRICE**: precio final de venta.

**CURR**: iso de la moneda.



Se pide:

Construir una aplicación/servicio en SpringBoot que provea una end point rest de consulta  tal que:
- Acepte como parámetros de entrada: fecha de aplicación, identificador de producto, identificador de cadena.
- Devuelva como datos de salida: identificador de producto, identificador de cadena, tarifa a aplicar, fechas de aplicación y precio final a aplicar.
- Se debe utilizar una base de datos en memoria (tipo h2) e inicializar con los datos del ejemplo, (se pueden cambiar el nombre de los campos y añadir otros nuevos si se quiere, elegir el tipo de dato que se considere adecuado para los mismos).
- Desarrollar unos test al endpoint rest que  validen las siguientes peticiones al servicio con los datos del ejemplo:


-          Test 1: petición a las 10:00 del día 14 del producto 35455   para la brand 1 (ZARA)

-          Test 2: petición a las 16:00 del día 14 del producto 35455   para la brand 1 (ZARA)

-          Test 3: petición a las 21:00 del día 14 del producto 35455   para la brand 1 (ZARA)

-          Test 4: petición a las 10:00 del día 15 del producto 35455   para la brand 1 (ZARA)

-          Test 5: petición a las 21:00 del día 16 del producto 35455   para la brand 1 (ZARA)


Se valorará:


Diseño y construcción del servicio.
Calidad de Código.
Resultados correctos en los tests.

# Stack

- Java 17
- Spring boot 3
- OpenAPI Specification
- Newman
- JUnit
- Mockito
- H2
- Flyway
- Docker

# API

Pese que es un proyecto muy pequeño de se ha seguido la metodología de trabajo API fist, documentando el api antes de comenzar el desarrollo.
Para esta documentación se ha usado el estandard OpenAPI y se puede encontrar en la ruta /etc/oas/oas.yml



# Arquitectura propuesta

Pese a que el codigo que se necesita es bastante sencillo, y sabiendo que para una aplicación tan sencilla no sería necesario una arquitectura tan 
grande, he optado por usar una arquitectura hexagonal ya que lo he planteado como si de una aplicación real se tratara ya que en este
paradigma este tipo de arquitecturas ofrecen muchas ventajas.

Podemos distinguir 3 grandes capas

**infraestructure**: Capa donde se encuentra todo lo relacionado la infraestructura y framework. (endpoints, base de datos, configuración de la
capa de dominio para aislarlo del framework)

**application**; Capa donde se estarían los diferentes casos de uso de nuestra aplicación y gestión de la transacionalidad. 
En este caso de uso tan sencillo lo único que hace es hacer de "proxy" a al siguiente capa adaptando los DTOs. 

**domain**: Capa más interna de la aplicación donde encontraremos las interfaces de los repositorios, el modelo de datos y 
la lógica de negocio (dentro de lo que he llamado DomainService). Se puede observar que he decidido aislar del framework esta capa
(algo que en bastantes casos no se hace pero es más purista). Se ha movido la creación de los beans necesarios a la capa de infraestructura.


De esta forma podemos separar en diferentes capas las diferentes responsabilidades teniendo en el punto central nuestro negocio 
de forma aislada a tecnologías y frameworks consiguiendo una aplicación más robusta y cumpliendo principios SOLID como el 
Open-close y el Single Responsability.


# Arrancar el proyecto

El proyecto puede ser arrancado de diferentes formas

### Maven 

Se puede arrancar el proyecto con el siguiente comando: 

```
mvn spring-boot:run
```

### Docker

Construir la imagen con el siguiente comando: 

```
docker build -f etc/docker/Dockerfile . -t inditex-challenge
```

Ejecutar la aplicación:

```
docker run -p 8080:8080 inditex-challenge
```


# Testing

En el apartado de testing he realizado dos tipos de test
 
## Test unitarios

Se han desarrollado los tests unitarios usando JUnit y Mockito además de Insatancio para la generación de POJOs con datos aleatorios.

## Test de integración

Los tests de integración los he realizado con newman de tal forma que podría integrarse en un pipeline (Jenkins, Bamboo, Gitlab CI...)
de forma muy sencilla y rápida. 

En estos tests se encuentran los tests solicitados en el enunciado garantizando asi que la aplicación funciona correctamente
y aegurando la integración entre los diferentes componentes (p.e.: BD).

La colección postman que contienen estos tests puede encontrarse en etc/newman y pueden ser ejecutados con el siguiente comando 
(el proyecto debe estar arrancado [ver como arrancar la aplicación](#arrancar-el-proyecto))

```
newman run etc/newman/Inditex\ code\ challenge.postman_collection.json
```

# Versionado Base de Datos

Igualmente he planteado el uso de Flyway para el versionado de los esquemas de base de datos. 
Al ser un proyecto muy pequeño simplemente he separado en dos versiones. La primera de ellas solo crea
un esquema dentro de la base de datos. El segundo por su parte se encarga de crear la tabla 
necesaria de precios. 

Estas migraciones se pueden encontrar en /src/main/resources/db.migration


# Internalización

He preparado la aplicación para que devuelva los errores en español o inglés in función del locale por lo que
en función de la configuración podría contestar en uno de los dos idiomas.
