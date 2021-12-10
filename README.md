# Nova

### Project Description
  NOVA Games is an e-Commerce application that gives users a platform to buy their favorite games and discover new games. Our intuitive search lets users discover a game by genre or name, and easily add it to their cart (or wishlist in future iterations). And because our Games are digital, we never run out of stock. NOVA is on the cutting edge of e-Commerce; try our service and you’ll be saying:
  
*“NOVA is out of this world!”*


### Features

## Technologies Used
* Java 8 
* JavaScript ES6
* HTML5 & CSS3 
* Bootstrap v5.1.3
* Apache Maven for dependencies and project management
* Mockito and Spring Boot Test for testing
* Git & GitHub for version control
* AWS PostgreSQL used with Hibernate deployed on AWS RDS for data persistence
* AWS EC2, ElasticBeanstalk, S3, CodeBuild, CodePipeline

## Getting Started

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

## Disclosure
This main [branch](https://github.com/Revature-Nova/NovaBackEnd/tree/main) contains the final product, with bugs. <br />
It is your choice to decide which branch you would like run from.

## Contributors
### DevOps Team
> Tyler Conner <br/>
Andrew Peterson
### Product Team
>Chris Oh <br/>
Brittany Lowell <br/>
Adam Dixon <br/>
Michael Reese <br/>
Jason Wozinak <br/>
### User Team
> Erika Johnson <br />
James Brown <br />
Kollier Martin <br />
Gregg Friedman <br />
Connor Phillips <br />
Emmanuel Tejeda <br />
Travis Hood 

