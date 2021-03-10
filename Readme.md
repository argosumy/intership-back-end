## How to start this project application locally

### Option 1: Without Docker
System Prerequisites - Required software to be installed: 
- [Java SE 11 JDK](https://www.oracle.com/java/technologies/javase-downloads.html) (_**Note:**_ Please make sure to set the **JAVA_HOME** and the **PATH** environment variables)
- [PostgreSQL 12.6 or higher](https://www.postgresql.org/download/) (_**Note:**_ Please keep default port **5432** unchanged during installation)
- [Git](https://git-scm.com/downloads)
- optionally [FlyWay 7.6 or higher](https://flywaydb.org/download/community) 

_**Useful links:**_
- [Setting the **PATH** Environment Variable in Windows](https://docs.oracle.com/en/java/javase/11/install/installation-jdk-microsoft-windows-platforms.html#GUID-96EB3876-8C7A-4A25-9F3A-A2983FEC016A)
- [Setting the **JAVA_HOME** Variable in Windows](https://confluence.atlassian.com/doc/setting-the-java_home-variable-in-windows-8895.html)
  (_**Note:**_ Also in Windows environment variables may be set as follows:
  Right click on Computer -> Advanced system settings -> Select Advanced tab -> Click on Environment variables)
- [PostgreSQL installation on Windows](https://www.postgresqltutorial.com/install-postgresql/)

System Prerequisites - Configuration to be done using commandline:
- [Create a PostgreSQL database](https://www.postgresql.org/docs/current/tutorial-createdb.html) (if it does not exist): 
`createdb mydatabase`  
- [Create a user to access a database](https://www.enterprisedb.com/postgres-tutorials/how-create-postgresql-database-and-users-using-psql-and-pgadmin):
  - `psql` or `psql -U postgres`
  - `create user myuser with password 'mypassword';`
  - `grant all priviliges on database mydatabase to myuser;`
  - `quit`

### Steps to run the project application using commandline
1. Download the project source code from GitLab repository:
  `git clone https://gitlab.com/spd-marketplace/back-end.git`
   
   
2. Go to the folder with downloaded source code:
  `cd back-end`
   

3. Checkout to a necessary branch, e.g.:
  `git checkout 2-be-001-setup-documentation-for-qa`
   

4. Set environment variables for all variables defined in a file **src/main/resources/application.properties**, e.g.:
  - `DB_NAME`
  - `DB_USER`
  - `DB_PASSWORD`
  - `GOOGLE_CLIENT_ID`
  - `GOOGLE_CLIENT_SECRET`
    
  _**Note:**_ An example of setting an environment variable in Windows via commandline: `setx DB_NAME "mydatabase"`
Also in Windows environment variables may be set as follows.
  Right click on Computer -> Advanced system settings -> Select Advanced tab -> Click on Environment variables.

5. Check that PostgreSQL service is running. If you installed pgAdmin during PostgreSQL installation you can check it via commandline:
`pg_ctl status`

_**Note:**_ Provided pgAdmin is installed, you can start PostgreSQL service using command `pg_ctl start` if needed.
   
_**Note:**_ If needed, you can connect to the database via commandline:
`psql -h localhost -p 5432 -d mydatabase -U mydbuser`


6. Populate a database
`gradlew.bat flywayMigrate -i` (for Windows) or
`./gradlew flywayMigrate -i` (for Linux)


7. Run the application
`gradlew.bat bootRun -i` (for Windows) or 
`./gradlew bootRun -i` (for Linux)
   
   
8. Open URL [http://localhost:8080](http://localhost:8080) in a web browser. If needed, you can connect to a database via commandline:
   `psql -h localhost -p 5432 -d mydatabase -U mydbuser`


9. To stop the application press **Ctrl+C** in a command prompt.


### Option 2: Using Docker 
_(applicable only if files **Dockerfile** and **docker-compose.yaml** are present in the project folder)_

Prerequisites - Required software to be installed: 
- [Docker Desktop](https://docs.docker.com/docker-for-windows/install/) (for Windows or MacOS)
- [Docker](https://docs.docker.com/engine/install/) and [Docker Compose](https://docs.docker.com/compose/install/) (for Linux)

### Steps to run the project application using commandline
1. Download the project source code from GitLab repository:
   `git clone https://gitlab.com/spd-marketplace/back-end.git`


2. Go to the folder with downloaded source code:
   `cd back-end`


3. Checkout to a necessary branch, e.g.:
   `git checkout 2-be-001-setup-documentation-for-qa`


4. Set environment variables for all variables defined in a file **src/main/resources/application.properties**:
- `DB_NAME`
- `DB_USER`
- `DB_PASSWORD`
- `GOOGLE_CLIENT_ID`
- `GOOGLE_CLIENT_SECRET`
- `AWS_ACCESS_KEY_ID`
- `AWS_SECRET_ACCESS_KEY`

_**Note:**_ An example of setting an environment variable in Windows via commandline: `setx DB_NAME "mydatabase"`
Also in Windows environment variables may be set as follows:
Right click on Computer -> Advanced system settings -> Select Advanced tab -> Click on Environment variables.

5. Run the project application via command prompt:

`docker-compose up --build`

_**Note:**_ a command `docker-compose up --build` will start PostgreSQL service, create a database, populate it and run the project application afterwards.

6. Open URL [http://localhost:8080](http://localhost:8080) in a web browser. If needed, you can connect to the database via commandline:
   `psql -h localhost -p 5432 -d mydatabase -U mydbuser`

_**Note:**_ Database data will be stored locally in a folder **db-data**. If needed to purge all data from previous tests, you can delete this folder after stopping the application.

7. To stop the application press **Ctrl+C** in a command prompt and type command `docker-compose down`