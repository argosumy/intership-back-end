## How to start this project application locally using Docker 

Prerequisites - Required software to be installed: 
- [Docker Desktop](https://docs.docker.com/docker-for-windows/install/) (for Windows or MacOS)
- [Docker](https://docs.docker.com/engine/install/) and [Docker Compose](https://docs.docker.com/compose/install/) (for Linux)

### Steps to run the application using command prompt:
1. Download the project source code from GitLab repository:
   `git clone https://gitlab.com/spd-marketplace/back-end.git`


2. Go to the folder with downloaded source code:
   `cd back-end`


3. Checkout to a necessary branch, e.g.:
   `git checkout hotfix/p-007`
   

5. Run the application using command:

`DB_NAME=<database_name> DB_USERNAME=<database_user> DB_PASSWORD=<database_password> docker-compose up --build` (replace parameters **<database_name>**, **<database_user>**, **<database_password>** with any text values) 

_**Note:**_ Please memorize provided values for **<database_name>**, **<database_user>**, **<database_password>**. These values will be required further in the step 7.

Example:

`DB_NAME=mydb DB_USERNAME=user DB_PASSWORD=qwerty docker-compose up --build`

_**Note:**_ This command will start PostgreSQL server and pgAdmin tool, create a database and run the application afterwards.


6. Log in to pgAdmin page at [http://localhost:5050](http://localhost:5050) using credentials:
- **Username:** admin
- **Password:** 12345
   
_**Note:**_ Please feel free to try [pgAdmin interactive online tutorial](https://www.pgadmin.org/try/).

7. Configure a connection to a PostgreSQL server in pgAdmin using **DB_NAME**, **DB_USERNAME**, **DB_PASSWORD** parameters from the step 5. 
   
_Usually this step should be done only once when **docker-compose** is run for the first time._
- Click on **Add New Server** at **Quick Links** section
- On tab **General** enter a name for a new server, e.g. **PostgreSQL**
- On tab **Connection**: 
   - enter a hostname: **database** _(please use exactly this hostname)_
   - keep default values for:
        - port: **5432**
        - maintenance database: **postgres**
   - enter **username** from the step 5 (e.g. user)
   - enter **password** from the step 5 (e.g. qwerty)
   - check **Save password** option
   - click on **Save** button

_**Important note:**_ All database data will be stored locally in a subfolder **db-data** in the project folder **back-end**. Thus, all data previously inserted into a database will be available next time you start the application. If needed to purge all database data from previous tests, you can delete **db-data** folder after stopping the application. Alternatively you can provide another **DB_NAME** parameter eg. `DB_NAME=mynewdb` in step 5 to start the application with a new empty database.

8. You can find a database created in step 5 on a left sidebar of pgAdmin page: Servers -> PostgreSQL (a server name from the step 7) -> Databases -> mydb (<database_name> from the step 5) -> Schemas -> public -> Tables.

You can use Tools -> Query Tool for executing SQL queries in pgAdmin.

9. To stop the application, database and pgAdmin press **Ctrl+C** in a command prompt

### The back-end application API will accept requests at URL [http://localhost:8080](http://localhost:8080)

### pgAdmin tool to access a database will be available at URL [http://localhost:5050](http://localhost:5050)
