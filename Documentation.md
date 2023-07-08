# Getting Started

## :notebook_with_decorative_cover: Table of Contents

- [Getting Started](#getting-started)
  - [:wrench: Prerequisites](#wrench-prerequisites)
  - [Installation](#installation)
    - [Database](#database)
    - [Setup run configurations (IntelliJ IDEA)](#setup-run-configurations-intellij-idea)
  - [API unit tests (Postman)](#api-unit-tests-postman)

## :wrench: Prerequisites

Please make sure you have the following installed on your machine:

- [Java 11 (recommended)](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html)
- [Gradle](https://gradle.org/install/)

For database, you can use any DBMS you want, but I'd used MySQL for this project.

- [MySQL Community Server (DBMS)](https://dev.mysql.com/downloads/mysql/)
- [MySQL Workbench (GUI) ](https://dev.mysql.com/downloads/workbench/)

For IDE, you can use any IDE you want, but I'd used IntelliJ IDEA for this project (Community Edition is enough).

- [IntelliJ IDEA](https://www.jetbrains.com/idea/download/)

API Testing Tool:

- [Postman](https://www.postman.com/downloads/) (recommended)

## Installation

### Get project local copy

1. Open your terminal/CMD and go to the directory where you want to clone this project by using `cd` command.

2. Clone this repository to your local machine.

```sh
git clone https://github.com/Esh07/Module-Timetable-Management-System-RESTful-API.git
```

3. Open the project in your IDE.

### Database

1. Open MySQL Workbench and connect to your local MySQL server. (If you don't have any server, you can create one by clicking on the `+` icon on the MySQL Connections tab.)

2. Create a new schema and name it `module_timetable_api`. (You can name it whatever you want, but you have to change the name in the `application.properties` file in the project. - [See below](#connecting-to-project-with-database))

3. Create a new user and name it `co2103` with password `co2103`. (You can name it whatever you want, but you have to change the name in the `application.properties` file in the project. - [See below](#connecting-to-project-with-database))

4. Grant all privileges to the user you just created. (You can do this by clicking on the `Users and Privileges` tab and double-clicking on the user you just created. Then, go to the `Administrative Roles` tab and check all the privileges.)

5. Click on the `Apply` button and then click on the `Finish` button.

6. Now, you can connect to the database using the user you just created.

#### Connecting to project with database

1. Open the project in your IDE.

2. Go to the `src/main/resources` directory and open the `application.properties` file.

3. Change the `spring.datasource.url` property to `jdbc:mysql://localhost:3306/module_timetable_api`.

   > **Note:** If you have changed the schema name, you have to change it here as well.
   > For example:
   > ![JDBC url path explanation](./docs/jdbc-url-path-explanation.png)
   >
   > - Keep protocol, hostname and port as it is.
   > - Change the schema name to the name you have given to the schema (database) you created. (keep the same, `module_timetable_api` if you haven't changed it in the [Database section](#database) above)

4. Change the `spring.datasource.username` property to `co2103`.

   > **Note:** If you have changed the username, you have to change it here as well.

5. Change the `spring.datasource.password` property to `co2103`.

   > **Note:** If you have changed the password, you have to change it here as well.

6. Save the file.

### Setup run configurations (IntelliJ IDEA)

1. Go to the [`src/main/java/edu/leicester/co2103/`](./src/main/java/edu/leicester/co2103/) directory and open the [`Part1Application.java`](./src/main/java/edu/leicester/co2103/Part1Application.java) file.

2. Right-click on the `Part1Application.java` file and select `Run 'Part1Application'`.

3. Go to the `Run` tab and click on the `Edit Configurations...` option.

4. Click on the `+` icon and select `Gradle`.

5. Change the `Name` to `Run Part1Application`.

6. Change the `Gradle project` to `Module-Timetable-Management-System-RESTful-API`.

7. Change the `Tasks` to `bootRun`.

8. Click on the `Apply` button and then click on the `OK` button.

9. Now, you can run the project by clicking on the `Run Part1Application` option in the `Run` tab.

### Testing the API (Postman)

> **Note:** Before testing the API, make sure you have run the project is running.

1. Open Postman.

2. Click on the `Collection` tab and create a new collection named `Module Timetable API`.

3. Now, click on the `File` tab and select `Import...`.

4. Go to the [`src/main/resources`](./src/main/resources) directory and select the [`part1.postman_collection.json`](./src/main/resources/part1.postman_collection.json) file.

   or

   you can drag and drop the [`part1.postman_collection.json`](./src/main/resources/part1.postman_collection.json) file to the Postman window.

If you have done everything correctly, you should see the `Timetable - REST API ` collection in the `Collections` tab.

You can now call the API endpoints by clicking on the endpoints in the `Collections` tab.

#### API unit tests (Postman)

Each endpoint has a test script that tests the endpoint and returns the result.

To run collection tests:

1. Click on the `Runner` tab and follow instructions shown on the new window.

2. Once you've imported the collection, click on the `Start Run` button.

3. It will run all the tests and show the results.

## Help/Issues

If you have any issues or need any help, please create an issue and I'll try to help you as soon as possible.
