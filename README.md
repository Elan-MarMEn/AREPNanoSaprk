# NanoSpark (own implementation)

This project focuses on the implementation and use of a framework implement by the author that simulates Spark to web development, using Sockets,Handlers and Buffer readers to handle a request and show and existent response or value whether html, js,css or lambda functions.  

## Continuous Integration

## Getting Started

Open your comand console or cmd and write or copy the comand below.

```
git clone https://github.com/Elan-MarMEn/AREPNanoSaprk.git
```

### Prerequisites

Make sure that you already have install Maven,java kit Development and GIT correctly. in he case that you don`t konow how to install use the links below:

* [How to install Maven](https://www.youtube.com/watch?v=RfCWg5ay5B0)
* [How to install JDK 11](https://www.youtube.com/watch?v=IJ-PJbvJBGs)
* [How to install GIT](https://git-scm.com/book/en/v2/Getting-Started-Installing-Git)
* [How to install herokuCLI](https://co.video.search.yahoo.com/search/video?fr=mcafee&ei=UTF-8&p=how+to+install+heroku+cli&type=E211CO885G91370#id=1&vid=85b4e7e52251aea122733ac858dfb9bf&action=click)

## Compile and Running
Get in to your cmd and use the commands below

```
mvn clean install

mvn package
```

For get the javadoc use:

```
mvn javadoc:javadoc
```

Once you run the project you can use the URI´s below to access to all allowed(Existing) requests:

For static files:
```
.../Arep/index.html

.../Arep/styles.css

.../Arep/app.js
```

For lambda functions:
```
.../Apps/customers
```
the rest of the request will be access to a default response. 

## Running the tests

If you want to run the appweb on a local port, you migth install herokuCli and then use the comand below.

```
heroku local web
```
or for windows:
```
java  -cp target/classes;target/dependency/* edu.escuelaing.arep.heroku.app.SparkWebApp
```
for Unix:
```
java $JAVA_OPTS -cp target/classes:target/dependency/* edu.escuelaing.arep.heroku.app.SparkWebApp
```

Use the next comand to know the result of the tests

```
mvn test
```

## Built With

* [Heroku](https://dashboard.heroku.com/apps) - Deployment
* [Java 8](https://www.java.com/es/about/whatis_java.jsp) 
* [Maven](https://maven.apache.org/) - Dependency Management


## Authors

* **Alan Marin** - *Initial work* - [Elan-MarMEn](https://github.com/Elan-MarMEn)


## License

This project is licensed under the General Public License (GNU) - see the [LICENSE.md](LICENSE.md) file for details
