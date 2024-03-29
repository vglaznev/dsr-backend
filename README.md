# DSR project backend


### Project description

---

This service allows you to create a short url based on a link you provided.

For example:

  You enter: `https://www.youtube.com/watch?v=H0TNRBI8ZaM&ab_channel=BBCRadio1VEVO`
    
  Service gives you: `http://<hostname>/H0TNRBI8` 


### How to start

---

1. Clone this project on your computer by running the following command in terminal (please, make sure you have git installed):

    `git clone https://github.com/vglaznev/dsr-backend.git`


2. Go to project root directory: 

    `cd dsr-backend`


3. You can run test by command `./mvnw test` to make sure everything is ok.

   (if you use Windows system, run `mvnw.cmd` instead of `./mvnw`, or you can use `mvn` on all systems, if you have Maven installed)

### Configure database

---

Before run the application you need to define datasource properties. To do this, you need to change `application.yml` file:

`cd src/main/resources/application.yml`

Then you will see:

```
spring:
    datasource:
        url: jdbc:postgresql://localhost:<port>/<database_name>
        username: <username>
        password: <password>
        driver-class-name: org.postgresql.Driver 
```
You need to specify those values:
* `port` - port the database server listens on. By default, it's `5432`, if your server sets on another port, change this field;
* `database_name` - name of database used by app. You can set the default one by PostgreSQL: `postgres` or create your own database:
`https://www.postgresql.org/docs/current/app-createdb.html`;

* `username` - name of your PostgreSQL user. You can use the default one by PostgreSQL: `postgres`;  
* `password` - PostgreSQL user password;

### Run the application

---

#### Without docker



Please, make sure you have the latest versions of `Java JDK` and `PostgreSQL`. You can download them from:

`Java`: https://www.oracle.com/java/technologies/downloads/#java17

`PostgreSQL`: https://www.postgresql.org/download/

To run the application, execute following command in project root directory:

`./mvnw spring-boot:run`


#### With docker



>Will be added soon


### Api-Docs

---

You can check api-documentation:

`http://localhost:8080/swagger-ui/index.html`


### Version

---

The latest version is v1.3-rel.

### History

---

>Will be added soon
