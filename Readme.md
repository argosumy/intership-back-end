## How to start this project application locally using Docker 

Prerequisites - Required software to be installed: 
- [Docker Desktop](https://docs.docker.com/docker-for-windows/install/) (for Windows 10 or MacOS)
- [Docker](https://docs.docker.com/engine/install/) and [Docker Compose](https://docs.docker.com/compose/install/) (for Linux)

Additionally, for Windows 10 Home _(Note: WSL2 is included in Windows 10 Pro by default):_
- [Windows Subsystem for Linux v.2 (WSL2)](https://docs.microsoft.com/en-us/windows/wsl/install-win10) (for Windows 10 Home only)
- optional [Windows Terminal](https://docs.microsoft.com/en-us/windows/terminal/get-started)
 
### Steps to run the application using Windows Terminal or PowerShell:
1. Download the project source code from GitLab repository:
   `git clone https://gitlab.com/spd-marketplace/back-end.git`


2. Go to the folder with downloaded source code:
   `cd back-end`


3. Checkout to a necessary branch, e.g.:
   `git checkout hotfix/p-007`
   

4. Run the application.

`docker-compose up --build`

5. Log in to pgAdmin page at [http://localhost:5050](http://localhost:5050) using credentials:
- **Username:** admin
- **Password:** 12345
   
_**Note:**_ Please feel free to try [pgAdmin interactive online tutorial](https://www.pgadmin.org/try/).

6. Configure a connection to a PostgreSQL server in pgAdmin. 
   
_Usually this step should be done only once when **docker-compose** is run for the first time._
- Click on **Add New Server** at **Quick Links** section
- On tab **General** enter a name for a new server, e.g. **PostgreSQL**
- On tab **Connection**: 
   - enter a hostname: **database** _(please use exactly this hostname)_
   - keep default values for:
        - port: **5432**
        - maintenance database: **postgres**
        - username: **baraholka**
        - password: **baraholka**
   - check **Save password** option
   - click on **Save** button

7. You can find a database created in step 4 on a left sidebar of pgAdmin page: Servers -> PostgreSQL (a server name from the step 6) -> Databases -> baraholka -> Schemas -> public -> Tables.

You can use Tools -> Query Tool for executing SQL queries in pgAdmin.

8. To stop the application, database and pgAdmin press **Ctrl+C** in a command prompt


9. If you want to clean a database run:
`docker-compose down`


10. _(Optional)_ If needed, you can add your SQL requests to populate a database:
    - by editing a file **V333333333333333_populate_db.sql** in a folder **test-data**
    - by adding one or several new SQL files in a folder **test-data**

_**Note:**_ Please make sure to empty database by running `docker-compose down` before `docker-compose up --build`

**Data import from SQL files in a folder `test-data` will be done only if a database is empty.**

_**Note:**_ SQL files will be imported into a database in ascending order. Please use in filenames numbers greater than in a default file, e.g **V456785685297021_do_something.sql**

### The back-end application API will accept requests at URL [http://localhost:8080](http://localhost:8080)

### pgAdmin tool to access a database will be available at URL [http://localhost:5050](http://localhost:5050)


### Default parameters to access a database:

- Database name:     **baraholka**
- Username:          **baraholka**
- Password password: **baraholka**
