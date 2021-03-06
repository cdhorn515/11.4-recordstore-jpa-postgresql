# RecordStore

This application allows users to search for and store their favorite bands and albums in a Postgres database. Additionally, users can add songs to the albums of their favorite artists. :notes:


## Built with

* Maven
* Java 8 (JDK 1.8.131) 
* HTML5
* CSS3
* Thymeleaf 
* Bootstrap
* PostgresSQL
 

This project is deployed using Heroku and can be viewed [here](https://glacial-caverns-98460.herokuapp.com/). 

To run this project locally:

You will need to create a Postgres database 

`createdb [dbname]`

`psql [dbname]`

with the following schema:

```
CREATE TABLE album (
    id SERIAL INTEGER NOT NULL PRIMARY KEY,
    title VARCHAR(255),
    year_released integer,
    band_id INTEGER REFERENCES band(id)
);

CREATE TABLE band (
    id SERIAL INTEGER NOT NULL PRIMARY KEY, 
    band_name VARCHAR(255),
    genre VARCHAR(255),
    website VARCHAR(255)
);

CREATE TABLE song (
    id SERIAL INTEGER NOT NULL PRIMARY KEY, 
    title VARCHAR(255),
    album_id INTEGER REFERENCES album(id),
    band_id INTEGER REFERENCES band(id)
);
```

* Clone this repository and in your terminal type `git clone` and paste the url that you copied. 
* Open the application.properties file and replace recordstore in the `spring.datasource.url` with your database name, and update the username and password if applicable to your database. 
* Run `mvn clean package` from your project directory (this will build the project and create a jar file in the target directory).


To run the project type: `java -jar target/recordstore-1.1.0-RELEASE.jar`, then open a browser window and go to `localhost:8080`.

