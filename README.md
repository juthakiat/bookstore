# RESTful API for a bookstore

The API that allows users to login, perform user related tasks, view a list of books and place book orders.

# Database Design

![Database Design](https://github.com/juthakiat/temp/blob/master/db_design/DB_DESIGN.png?raw=true "Database Design")

# Sequence Diagram

**GET /books**
Gets a list of books from an external book publisher’s web services and returns the list sorted alphabetically with the recommended books always appears first
![GET /books](https://github.com/juthakiat/temp/blob/master/sequence_diagram/GET%20:books.png?raw=true "GET /books")

**POST /user**
Create a user account and store user’s information in Users table (DB).
![POST /users](https://github.com/juthakiat/temp/blob/master/sequence_diagram/POST%20:users.png?raw=true "POST /users")

**POST /login**
User login authentication API.
![POST /login](https://github.com/juthakiat/temp/blob/master/sequence_diagram/POST%20:login.png?raw=true "POST /login")

**GET /users**
Gets information about the logged in user. A successfully authenticated request returns information related to the user and the books ordered
![GET /users](https://github.com/juthakiat/temp/blob/master/sequence_diagram/POST%20:login.png?raw=true "GET /users")

**POST /users/orders**
Order books and store order information in Orders table (BOOK_ORDER). This returns the total price for a successful order
![POST /users/orders](https://github.com/juthakiat/temp/blob/master/sequence_diagram/POST%20:users:orders.png?raw=true "POST /users/orders")

**DELETE /users**
Delete logged in user’s record and order history.
![DELETE /users](https://github.com/juthakiat/temp/blob/master/sequence_diagram/DELETE%20:users.png?raw=true "DELETE /users")

# API Document

You can find the [API Document](https://raw.githubusercontent.com/juthakiat/temp/master/api-docs.json) here.
