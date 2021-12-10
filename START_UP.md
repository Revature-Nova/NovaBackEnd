#### Clone the repo
```git clone https://github.com/Revature-Nova/NovaBackEnd```

#### Environment Set up Steps
1. Open project in a Java IDE, preferrably IntelliJ
2. Set up an application.properties file in each service with the follwing categories:
  * Spring Data Source Information //Should contain information to connect to your database server
  * Spring JPA & Hibernate //Should describe the Database platform, and how you want Hibernate and Jackson to function
  * Configure Eureka Server url //This should have the Eureka Client url, port, name and memory-size
  * Logging //This will contain the status of hibernate's logging as well as it's type
  * Web Security (If tokens are being used) //This will contain the tokens header and prefix

NOTE: Please remember to open these services following microservice guidelines i.e discovery service, then zuul-service, and finally: product service, cart service, user-service (These three can be done in any order).

## Disclosure
This main [branch](https://github.com/Revature-Nova/NovaBackEnd/tree/main) contains the final product, with bugs. <br />
It is your choice to decide which branch you would like run from.
