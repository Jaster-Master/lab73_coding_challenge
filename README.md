# lab73 Coding-Challenge #

## Backend ##

The main class of the backend is located at "net.zecher.backend.BackendApplication" in the source code. To start it either use an IDE like IntelliJ IDEA or the .jar file in the releases of this repository. The backend was written in Java 21 with Spring Boot. Furthermore it uses MariaDB to store the observations and users.

### Endpoints ###

The backend has four important endpoints. (base-url: http://localhost:8080)

- POST /register
- POST /login

These requests need a json with userName and password.

- GET  /api/traffic-sign-observations
- POST /api/traffic-sign-observations

The get request can be filtered with type and value as query parameter. The post request needs a json with latitude, longitude, heading, type and value.

## Frontend ##

To start the frontend either use the "dev" npm script or the web files in the releases of this repository. The frontend was written in TypeScript with React.

## Script ##

To start the script to upload the observations of "sign_data.csv" to the backend simply start the backend and use Python to execute the script. The script was written in Python 3.12.4.
