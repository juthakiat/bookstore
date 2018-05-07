# RESTful API for a bookstore

The API that allows users to login, perform user related tasks, view a list of books and place book orders.

# Setup Instruction
1.) Clone the repository by the following command. 
```
$ git clone https://github.com/juthakiat/bookstore.git
```
2.) Import the Maven project using your favorite IDE such as Eclipse, Netbean or Intellij

3.) Run the project.

- The database (H2) is created in memory through Spring Boot auto configuration. There is no additional setup.
- The tables are created on the entities defined. There is no additional setup as well.
- Since H2 is the in-memory database. Data is not persisted between restart. (Restrictions of using in-memory database)  

4.) Test REST APIs using your favorite REST client such as Postman by entering the service endpoint e.g. http://localhost:8080/books



# Database Design

![Database Design](https://github.com/juthakiat/temp/blob/master/db_design/DB_DESIGN.png?raw=true "Database Design")

# API Document

You can find the [API Document](https://raw.githubusercontent.com/juthakiat/temp/master/api-docs.json) here or [http://localhost:8080/v2/api-docs](http://localhost:8080/v2/api-docs)

# Sequence Diagram

**GET /books**

Gets a list of books from an external book publisher’s web services and returns the list sorted alphabetically with the recommended books always appears first.
<img src="https://github.com/juthakiat/temp/blob/master/sequence_diagram/GET%20:books.png?raw=true" width="400" alt="GET /book" />

**POST /user**

Create a user account and store user’s information in Users table (DB).
<img src="https://github.com/juthakiat/temp/blob/master/sequence_diagram/POST%20:users.png?raw=true" width="400" alt="POST /users" />

**POST /login**

User login authentication API.

<img src="https://github.com/juthakiat/temp/blob/master/sequence_diagram/POST%20:login.png?raw=true" width="400" alt="POST /login" />

**GET /users**

Gets information about the logged in user. A successfully authenticated request returns information related to the user and the books ordered

<img src="https://github.com/juthakiat/temp/blob/master/sequence_diagram/POST%20:login.png?raw=true" width="400" alt="GET /users" />

**POST /users/orders**

Order books and store order information in Orders table (BOOK_ORDER). This returns the total price for a successful order

<img src="https://github.com/juthakiat/temp/blob/master/sequence_diagram/POST%20:users:orders.png?raw=true" width="400" alt="POST /users/orders" />

**DELETE /users**

Delete logged in user’s record and order history.

<img src="https://github.com/juthakiat/temp/blob/master/sequence_diagram/DELETE%20:users.png?raw=true" width="400" alt="DELETE /users" />
