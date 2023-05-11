
# Moj Doklad Backend Documentation

## Table of Contents
1. [Introduction](#1-introduction)
2. [Backend Architecture](#2-backend-architecutre)
3. [Authentication](#3-authentication)
4. [Authorization](#4-authorization)
5. [Backend Endpoints](#5-backend-endpoints)
    1. [User Endpoints](#51-user-endpoints-apiusers)
    2. [Document Endpoints](#52-document-endpoints-apidocuments)
    3. [Notification Endpoints](#53-notification-endpoints-apinotifications)
    4. [Order Endpoints](#54-order-endpoints-apiorders)
    5. [StatusController Endpoints](#55-statuscontroller-endpoints-apistatus)
    6. [AuthController Endpoints](#56-authcontroller-endpoints-apiauth)
    7. [ServiceController Endpoints](#57-servicecontroller-endpoints-apiservices)
    8. [SecretController Endpoints](#58-secretcontroller-endpoints-apisecret)
    9. [DocumentDataController](#59-documentdatacontroller-endpoints-apidocuments-data)
   10. [NotificationDataController](#510-notificationdatacontroller-endpoints-apinotifications-data)
   11. [UserDataController](#511-userdatacontroller-endpoints-apiusers-data)
   12. [OrderDataController](#512-orderdatacontroller-endpoints-apiorders-data)
   13. [OrderDataController](#513-orderdatacontroller-endpoints-apiorders-data)
6. [Database](#6-database)
7. [Maven Dependencies](#7-maven-dependencies)

# 1. Introduction
>This Backend goal is to provide API for Moj Doklad application. It is written in Java 17 using Spring Boot framework.
It is using PostgreSQL database.
> 

# 2. Backend Architecutre
> 2.1 Backend is divided into 3 layers: Controller, Service and Repository. <br/>
> 2.2 Service layer is using DTOs to communicate with Controller layer. Service layer is using Entities to communicate with Repository layer.
> <br/>2.3 Repository layer is using JPA to communicate with database. <br/>
> 2.4 Controller layer is using DTOs to communicate with Service layer. <br/>
> 2.5 DTOs are mapped to Entities using MapStruct library. <br/>

# 3. Authentication
> 3.1 Authentication is done using JWT. <br/>
> 3.2 JWT is generated using RSA algorithm. <br/>
> 3.3 JWT is stored in HttpOnly cookie. <br/>
> 3.4 JWT is valid for 1 hour. <br/>
>

# 4. Authorization
> 4.1 Authorization is done using Spring Security. <br/>
> 4.2 Authorization is done using roles. <br/>
> 4.3 Roles are: ROLE_USER, ROLE_ADMIN, ROLE_STAFF. <br/>
> 4.4 ROLE_USER is assigned to every user. <br/>
> 4.5 ROLE_ADMIN is assigned to user and staff with admin privileges. <br/>
> 4.6 ROLE_STAFF is assigned to user and staff with staff privileges. <br/>

# 5. Backend Endpoints
## 5.1 User Endpoints ```/api/users```
> 5.1.1 ```GET /``` is return list of users with 200 HTTP Status code <br/>
> 5.1.2 ```GET /{id}``` is return user with 200 HTTP Status code <br/>
> 5.1.3 ```POST /``` is create and return user with 200 HTTP Status code <br/>
> 5.1.4 ```PUT /{id}``` is update user and return with 200 HTTP Status code <br/>
> 5.1.5 ```DELETE /{id}``` is delete user with 200 HTTP Status code <br/>
> 5.1.6 ```GET /{username}``` is return user by username with 200 HTTP Status code <br/>
> 5.1.7 ```GET /username/{username}``` is return user by username with 200 HTTP Status code <br/>
> 5.1.8 ```GET /documents/{id}``` is return list of documents by user id with 200 HTTP Status code <br/>
> 5.1.9 ```GET /documents/``` is return of documents by authenticated user with 200 HTTP Status code <br/>

## 5.2 Document Endpoints ```/api/documents```
> 5.2.1 ```GET /``` is return list of documents with 200 HTTP Status code <br/>
> 5.2.2 ```GET /{id}``` is return document with 200 HTTP Status code <br/>
> 5.2.3 ```POST /``` is create and return document with 200 HTTP Status code <br/>
> 5.2.4 ```PUT /{id}``` is update document and return with 200 HTTP Status code <br/>
> 5.2.5 ```DELETE /{id}``` is delete document with 200 HTTP Status code <br/>

## 5.3 Notification Endpoints ```/api/notifications```
> 5.3.1 ```GET /``` is return list of notifications with 200 HTTP Status code <br/>
> 5.3.2 ```GET /{id}``` is return notification with 200 HTTP Status code <br/>
> 5.3.3 ```POST /``` is create and return notification with 200 HTTP Status code <br/>
> 5.3.4 ```PUT /{id}``` is update notification and return with 200 HTTP Status code <br/>
> 5.3.5 ```DELETE /{id}``` is delete notification with 200 HTTP Status code <br/>

## 5.4 Order Endpoints ```/api/orders```
> 5.4.1 ```GET /``` is return list of orders with 200 HTTP Status code <br/>
> 5.4.2 ```GET /{id}``` is return order with 200 HTTP Status code <br/>
> 5.4.3 ```POST /``` is create and return order with 200 HTTP Status code <br/>
> 5.4.4 ```PUT /{id}``` is update order and return with 200 HTTP Status code <br/>
> 5.4.5 ```DELETE /{id}``` is delete order with 200 HTTP Status code <br/>


## 5.5 StatusController Endpoints ```/api/status```
> 5.5.1 ```GET /``` is return list of statuses with 200 HTTP Status code <br/>
> 5.5.2 ```GET /{id}``` is return status with 200 HTTP Status code <br/>
> 5.5.3 ```POST /``` is create and return status with 200 HTTP Status code <br/>
> 5.5.4 ```PUT /{id}``` is update status and return with 200 HTTP Status code <br/>
> 5.5.5 ```DELETE /{id}``` is delete status with 200 HTTP Status code <br/>

## 5.6 AuthController Endpoints ```/api/auth```
> 5.6.1 ```POST /login``` is login user and return with 200 HTTP Status code <br/>
> 5.6.2 ```POST /register``` is register user and return with 200 HTTP Status code <br/>

## 5.7 ServiceController Endpoints ```/api/services```
> 5.7.1 ```GET /``` is return list of services with 200 HTTP Status code <br/>
> 5.7.2 ```GET /{id}``` is return service with 200 HTTP Status code <br/>
> 5.7.3 ```POST /``` is create and return service with 200 HTTP Status code <br/>
> 5.7.4 ```PUT /{id}``` is update service and return with 200 HTTP Status code <br/>
> 5.7.5 ```DELETE /{id}``` is delete service with 200 HTTP Status code <br/>

## 5.8 SecretController Endpoints ```/api/secret```
> 5.8.1 ```GET /``` is return list of secrets with 200 HTTP Status code <br/>
> 5.8.2 ```POST /``` is create and return secret with 200 HTTP Status code <br/>
> 
## 5.9 DocumentDataController Endpoints ```/api/documents-data```
> 5.9.1 ```GET /create?count=1``` is generating documents with random data to simulate real life situations.<br/>

## 5.10 NotificationDataController Endpoints ```/api/notifications-data```
> 5.10.1 ```GET /create?count=1``` is generating notifications with random data to simulate real life situations.<br/>

## 5.11 UserDataController Endpoints ```/api/users-data```
> 5.11.1 ```GET /create?count=1``` is generating users with random data to simulate real life situations.<br/>
> 

## 5.12 StatusDataController Endpoints ```/api/status-data```
> 5.12.1 ```GET /create?count=1``` is generating statuses with random data to simulate real life situations.<br/>

## 5.13 OrderDataController Endpoints ```/api/orders-data```
> 5.13.1 ```GET /create?count=1``` is generating orders with random data to simulate real life situations.<br/>


## 6. Database
> 6.1 Database is PostgreSQL. <br/>
> 6.2 Database is hosted on Heroku. <br/>
> 6.3 Database is using 1 table per entity. <br/>
> 

## 7. Maven Dependencies
```
 <dependency>
          <groupId>org.springframework.boot</groupId>
          <artifactId>spring-boot-starter-data-jpa</artifactId>
      </dependency>
      <dependency>
          <groupId>org.springframework.boot</groupId>
          <artifactId>spring-boot-starter-security</artifactId>
      </dependency>
      <dependency>
          <groupId>org.modelmapper</groupId>
          <artifactId>modelmapper</artifactId>
          <version>3.1.1</version>
      </dependency>
      <dependency>
          <groupId>org.springframework.boot</groupId>
          <artifactId>spring-boot-starter-validation</artifactId>
      </dependency>
      <dependency>
          <groupId>org.springframework.boot</groupId>
          <artifactId>spring-boot-starter-web</artifactId>
      </dependency>

      <dependency>
          <groupId>org.postgresql</groupId>
          <artifactId>postgresql</artifactId>
          <scope>runtime</scope>
      </dependency>
      <dependency>
          <groupId>org.springframework.boot</groupId>
          <artifactId>spring-boot-starter-test</artifactId>
          <scope>test</scope>
      </dependency>
      <dependency>
          <groupId>org.springframework.security</groupId>
          <artifactId>spring-security-test</artifactId>
          <scope>test</scope>
      </dependency>

      <dependency>
      <groupId>com.github.javafaker</groupId>
          <artifactId>javafaker</artifactId>
          <version>1.0.2</version>
      </dependency>
      <dependency>
          <groupId>com.fasterxml.jackson.core</groupId>
          <artifactId>jackson-databind</artifactId>
          <version>2.13.3</version>
      </dependency>
      <dependency>
          <groupId>com.auth0</groupId>
          <artifactId>java-jwt</artifactId>
          <version>4.0.0</version>
      </dependency>
      <dependency>
          <groupId>org.springframework.boot</groupId>
          <artifactId>spring-boot-starter-data-jpa</artifactId>
      </dependency>
      <dependency>
          <groupId>org.springframework.boot</groupId>
          <artifactId>spring-boot-starter-validation</artifactId>
      </dependency>
      <dependency>
          <groupId>org.springframework.boot</groupId>
          <artifactId>spring-boot-starter-web</artifactId>
      </dependency>

      <!-- https://mvnrepository.com/artifact/org.mockito/mockito-core -->
      <dependency>
          <groupId>org.mockito</groupId>
          <artifactId>mockito-core</artifactId>
          <version>5.3.1</version>
          <scope>test</scope>
      </dependency>


      <dependency>
          <groupId>org.springframework.boot</groupId>
          <artifactId>spring-boot-starter-test</artifactId>
          <scope>test</scope>
      </dependency>
      <dependency>
          <groupId>org.junit.jupiter</groupId>
          <artifactId>junit-jupiter-api</artifactId>
          <version>5.9.2</version>
          <scope>test</scope>
      </dependency>


```



