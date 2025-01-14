# Proyecto de Spring Boot

Instrucciones para correr el projecto:

Dirigirse a la carpeta raíz del projecto: ./csBankAccount y ejecutar el siguiente comando:

./gradlew bootRun

Proyecto en Gradle con Jar y Java 17
Dependencias: Spring Web, Spring Boot DevTools, Spring REST Docs, Spring Data JPA, h2database.

## Primera parte:
Refactorización de la clase BankAccount para el proyecto en Spring Boot con el controlador web para las peticiones http.

## Segunda parte:
Crear diagrama ER, integración de base de datos con H2 e implementacióon de las entidades USER, BANK_ACCOUNT y TRANSACTION con JPA.
![h2-console](h2-console.PNG)
![diagrama](ER-model.png)

## Tercera parte:
Agregar tests unitarios para los métodos de la clase BankAccount. Uso de Spring Boot Test con JUnit. Ejecución de los test utilizando el comando:
gradle test

![tests](bankAccountTests.PNG)

## Cuarta parte:
Se agrega Spring Boot Security configurado con JWT para la restricción de acceso a todas las rutas menos las de /auth/** y la de la consola h2.

# Pruebas en postman

## Crear usuario:
![crear-usuario](api_requets/1.PNG)

## Obtener usuarios:
![crear-usuario](api_requets/2.PNG)

## Obtener usuario:
![crear-usuario](api_requets/3.PNG)

## Crear cuenta:
![crear-usuario](api_requets/4.PNG)

## Obtener cuentas:
![crear-usuario](api_requets/5.PNG)

## Obtener cuenta:
![crear-usuario](api_requets/6.PNG)

## Depositar:
![crear-usuario](api_requets/7.PNG)

## Retirar:
![crear-usuario](api_requets/8.PNG)

## Obtener transacciones:
![crear-usuario](api_requets/9.PNG)

## Cuenta con transacciones:
![crear-usuario](api_requets/10.PNG)
