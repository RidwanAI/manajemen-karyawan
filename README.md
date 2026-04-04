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

## Requirement

- Java (versi 25)
- Maven (versi 3.9.14)
- PostgreSQL

## Konfigurasi Database

- Buat database di PostgreSQL:
  CREATE DATABASE manajemen-karyawan;

- Restore database dengan file .sql yang ada di folder src\main\java\manajemen\karyawan\manajemen_karyawan\database

- Konfigurasi file application.properties:
  spring.application.name=manajemen-karyawan
  server.port=8081
  spring.datasource.url=jdbc:postgresql://localhost:5432/manajemen-karyawan
  spring.datasource.username=postgres
  spring.datasource.password=root
  
  spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect
  spring.jpa.hibernate.ddl-auto=update
  spring.jpa.show-sql=true
  spring.jpa.properties.hibernate.format_sql=true
  
  spring.datasource.driver-class-name=org.postgresql.Driver

## Cara Menjalankan Aplikasi

- Clone Project
  git clone ??

- Build Project
  mvn clean install

- Jalankan Aplikasi
  mvn spring-boot:run

## Akses URL

- Base URL
  http://localhost:8081/api/employees

- Endpoint API
  - Get All Employees
    /api/employees

  - Create Employee
    /api/employees

  - Update Employee
    /api/employees/{id}

  - Delete Employee
    /api/employees/{id}

  - Search Employee
    /api/employees/search
    * Berdasarkan id :
        /api/employees/search?id=1
    * Berdasarkan nama :
        /api/employees/search?name=ridwan
    * Berdasarkan email :
        /api/employees/search?email=example@example.com
    * Berdasarkan position :
        /api/employees/search?position=positionExample
    * Berdasarkan salary :
        /api/employees/search?salary=5000000
    * Berdasarkan range salary :
        /api/employees/search?minSalary=5000000&maxSalary=10000000

## Validasi & Global Error Handling
Validasi Input Menggunakan @Valid
- name, email, position → tidak boleh kosong
- salary → wajib dan harus > 0

Global Error Handling Menggunakan @ControllerAdvice
{
    "path": "/api/employees/search",
    "error": "Not Found",
    "message": "Data karyawan tidak ditemukan",
    "timestamp": "2026-04-04T08:25:02.1005583",
    "status": 404
}

## Struktur Project
src                                                       
├─ main                                                   
│  ├─ java                                                
│  │  └─ manajemen                                        
│  │     └─ karyawan                                      
│  │        └─ manajemen_karyawan                         
│  │           ├─ controller                              
│  │           │  └─ EmployeeController.java              
│  │           ├─ database                                
│  │           │  └─ manajemen-karyawan.sql               
│  │           ├─ entitiy                                 
│  │           │  └─ EmployeeEntity.java                  
│  │           ├─ exception                               
│  │           │  ├─ BadRequestException.java             
│  │           │  ├─ DataNotFoundException.java           
│  │           │  └─ GlobalExceptionHandler.java          
│  │           ├─ repository                              
│  │           │  └─ EmployeeRepository.java              
│  │           ├─ service                                 
│  │           │  └─ EmployeeService.java                 
│  │           └─ ManajemenKaryawanApplication.java       
│  └─ resources                                           
│     ├─ static                                           
│     ├─ templates                                        
│     └─ application.properties                           
└─ test                                                   
   └─ java                                                
      └─ manajemen                                        
         └─ karyawan                                      
            └─ manajemen_karyawan                         
               └─ ManajemenKaryawanApplicationTests.java  
