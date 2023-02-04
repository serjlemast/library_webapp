Info about web project
JEE (Servlet API - JSTL, JSP)
JDK 11
JDBC
REST
Lombok
JS/HTML/AJAX

***
### PROJECT DESCRIPTION


The task of the final project is to develop a web application that supports the functionality according to the task variant.

Variants

Library	The reader registers in the system and then can:
- search (by author / title);
- place an order for a Book from the Catalog.
  An unregistered reader cannot order a book.
  For the catalog the ability to sort books has to be implemented:
- by name;
- by author;
- by publication;
- by date of publication.
  The librarian gives the reader a book on a subscription or to the reading room. The book is issued to the reader for a certain period. If the book is not returned within the specified period, the reader will be fined. The book may be present in the library in one or more copies. The system keeps track of the available number of books. Each user has a personal account that displays registration information
  And also:
1) for reader:
- list of books on the subscription and date of possible return (if the date is overdue, the amount of the fine is displayed);
2) for librarian:
- list of readers' orders;
- list of readers and their subscriptions.
  The system administrator has the rights:
- adding / deleting a book, editing information about the book;
- create / delete librarian;
- blocking / unblocking the user.

***
### Tomcat

link: https://tomcat.apache.org/tomcat-10.0-doc/index.html

version - 10.0.27

Include Tomcat to IDE and add path

Deployment section add artifact(projectName.war)

***

### How to add ENV variable to IDE

1. Got to IDE >> Edit configuration
2. Modify options >> select in list - Environment variables
3. set ENV variables as in the example below

example:
<code>
MONGODB_DATABASE=sampleDB;MONGODB_URI=mongodb+srv://admin:
pWCbN0STJVmDmdDs@cluster0.ckcsd.mongodb.net/myFirstDatabase?retryWrites=true&w=majority
</code>

***

### Docker

link: https://hub.docker.com/_/mysql

Environment Variables:
MYSQL_ROOT_PASSWORD = 12345
DEFAULT user name to database = root


Run command in terminal:

docker run -d -p 3306:3306 --name some-mysql -e MYSQL_ROOT_PASSWORD=some-password -d mysql:tag

connect to Mysql:

1. run command:
   docker exec -it some-mysql bash

2. run command:
   mysql -p

3. enter password - some-password

4. create or connect to schema

***

### DataBase export from dumps

Load a MySQL dump from MySQL Workbench
To load a MySQL dump file:

1. Connect to your MySQL database.
2. Click Server on the main tool bar.
3. Select Data Import.
4. You should see a link to the default dump folder, typically your Documents folder in a subfolder titled dumps.
5. Click the ... and navigate to where your MySQL backup file is located, select the backup you want to load, and click OK.
6. The schema names in your dump should appear on the left-hand side, at the bottom. Select the schemas that need to be restored.
7. Select Start Import on the bottom right.
