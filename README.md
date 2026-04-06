# REST API Manajemen Karyawan (Spring Boot)

Aplikasi ini merupakan REST API sederhana untuk manajemen data karyawan menggunakan Spring Boot dan PostgreSQL. API ini mendukung operasi CRUD serta pencarian data dengan berbagai parameter.

## Fitur Utama

- CRUD data karyawan
- Validasi input menggunakan jakarta.validation
- Pencarian fleksibel (id, nama, email, posisi, salary range)
- Global exception handling
- Integrasi dengan PostgreSQL

## Tech Stack yang Digunakan

- Java 25
- Spring Boot
- Spring Web
- Spring Data JPA
- PostgreSQL Driver
- Hibernate Validator
- Docker
- Swagger

## Requirement

- Java (versi 25)
- Maven (versi 3.9.14)
- PostgreSQL

## Struktur Project

src  
├─ main  
│ ├─ java  
│ │ └─ manajemen  
│ │ └─ karyawan  
│ │ └─ manajemen_karyawan  
│ │ ├─ controller  
│ │ │ └─ EmployeeController.java  
│ │ ├─ database  
│ │ │ └─ manajemen-karyawan.sql  
│ │ ├─ entitiy  
│ │ │ └─ EmployeeEntity.java  
│ │ ├─ exception  
│ │ │ ├─ BadRequestException.java  
│ │ │ ├─ DataNotFoundException.java  
│ │ │ └─ GlobalExceptionHandler.java  
│ │ ├─ repository  
│ │ │ └─ EmployeeRepository.java  
│ │ ├─ service  
│ │ │ └─ EmployeeService.java  
│ │ └─ ManajemenKaryawanApplication.java  
│ └─ resources  
│ ├─ static  
│ ├─ templates  
│ └─ application.properties  
└─ test  
 └─ java  
 └─ manajemen  
 └─ karyawan  
 └─ manajemen_karyawan  
 └─ ManajemenKaryawanApplicationTests.java

# Cara Menjalankan Aplikasi

## 1. Clone Project

```bash
git clone https://github.com/RidwanAI/manajemen-karyawan.git
```

## 2. Konfigurasi Database

- Buat database di PostgreSQL :

  ```sql
  CREATE DATABASE manajemen-karyawan;
  ```

- Restore database dengan file .sql yang ada di folder => src\main\java\manajemen\karyawan\manajemen_karyawan\database

- Konfigurasi file application.properties yang ada di folder => src\main\resources :

  ```properties
  spring.application.name=manajemen-karyawan
  server.port=8081
  spring.datasource.url=jdbc:postgresql://localhost:5432/manajemen-karyawan
  spring.datasource.username=postgres
  spring.datasource.password=root #sesuaikan dengan password postgresql anda

  spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect
  spring.jpa.hibernate.ddl-auto=update
  spring.jpa.show-sql=true
  spring.jpa.properties.hibernate.format_sql=true

  spring.datasource.driver-class-name=org.postgresql.Driver
  ```

## 3. Build Project & Running

- Build Project pada terminal menggunakan mvn :

  ```console
  mvn clean install
  ```

- Jalankan Aplikasi :
  ```console
  mvn spring-boot:run
  ```

## 4. Cara Akses Swagger

- http://localhost:8081/swagger-ui/index.html

## Map Akses URL

- Base URL :  
  http://localhost:8081/api/employees

- Endpoint API
  - Get All Employees :  
    http://localhost:8081/api/employees

  - Create Employee :  
    http://localhost:8081/api/employees

  - Update Employee :  
    http://localhost:8081/api/employees/{id}

  - Delete Employee :  
    http://localhost:8081/api/employees/{id}

  - Search Employee :  
    http://localhost:8081/api/employees/search
    - Berdasarkan id :  
      http://localhost:8081/api/employees/search?id=1
    - Berdasarkan nama :  
      http://localhost:8081/api/employees/search?name=ridwan
    - Berdasarkan email :  
      http://localhost:8081/api/employees/search?email=example@example.com
    - Berdasarkan position :  
      http://localhost:8081/api/employees/search?position=positionExample
    - Berdasarkan salary :  
      http://localhost:8081/api/employees/search?salary=5000000
    - Berdasarkan range salary :  
      http://localhost:8081/api/employees/search?minSalary=5000000&maxSalary=10000000
