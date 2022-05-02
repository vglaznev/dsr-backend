##DSR project backend

---

###Note:

This application doesn't have any api-docs or client-side yet (temporary). So if you want to test some methods, please use postman or curl.

`http-get:` `localhost:8080/api/v1/short-url/<url>` - short url that redirects you to original resources;

`http-post:` `localhost:8080/api/v1/short-url/create` - create short url from original resources, put original url in request body;

###How to start

To start this application run following command in cmd:

`mvn spring-boot:run`

###Version
The latest version is v1.0-dev.

###History
* v1.0-dev:
    
    * Can create short urls;
    * Redirecting from short urls to original resources;