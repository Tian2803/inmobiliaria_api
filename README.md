# mife-backend

API REST desarrollada en Spring Boot con MySQL para gestión de productos.

## Requisitos previos
- **Java**: JDK 17 o superior
- **Maven**: 3.8.0 o superior
- **MySQL**: 8.0 o superior
- **Variables de entorno**:
  - Configura `application.properties`, hay un archivo de ejemplo en la ruta ./src/main/resources/
## Instalación
1. Clona el repositorio:
   ```bash
   git clone <URL-del-repositorio>
   cd inmobiliaria_api
   ```
2. Configura la base de datos MySQL:
   - Crea una base de datos llamada `inmobiliaria`.
   - Asegúrate de que MySQL esté ejecutándose.
3. Instala las dependencias:
   ```bash
   mvn clean install
   ```

## Ejecución
1. Inicia la aplicación:
   ```bash
   mvn spring-boot:run
   ```
2. La API estará disponible en `http://localhost:8080`.

## Nota
- Usa un cliente como Postman para probar los endpoints.