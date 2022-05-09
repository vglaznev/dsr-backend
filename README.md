## DSR project backend

---
### How to start

To start this application run following command in cmd:

`mvn spring-boot:run`

### Api-Docs

You can check api-documentation:

`https://localhost:8080/swagger-ui`

It doesn't have any comments yet, but you can try-out some methods.

`http-get:` `localhost:8080/api/v1/short-url/<url>` - short url that redirects you to original resources;

`http-post:` `localhost:8080/api/v1/short-url/create` - create short url from original resources, put original url in request body;

### Version
The latest version is v1.1.

### History
* v1.1:
    * migrate to Postgresql
    * add unit-tests
    * add api-docs  


* v1.0-dev:
    
    * Can create short urls;
    * Redirecting from short urls to original resources;