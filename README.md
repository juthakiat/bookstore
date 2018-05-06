# RESTful API for a bookstore

The API that allows users to login, perform user related tasks, view a list of books and place book orders.

# Database Design

![Database Design](https://github.com/juthakiat/temp/blob/master/db_design/DB_DESIGN.png?raw=true "Database Design")

# Sequence Diagram

**GET /books**
Gets a list of books from an external book publisher’s web services and returns the list sorted alphabetically with the recommended books always appears first
```mermaid
sequenceDiagram
Client ->> Server: GET /books
Server-->> Client: 200 SUCESS: { books: [ { }, ...] }
```

**POST /user**
Create a user account and store user’s information in Users table (DB).
```mermaid
sequenceDiagram
Client ->> Server: POST /users
Server ->> Server: Is parameters valid? (false)
Server-->> Client: 400 BAD REQUEST
Client ->> Server: POST /users
Server ->> Server: Is parameters valid? (true)
Server ->> Server: Is username already exists? (true)
Server-->> Client: 409 CONFLICT
Client ->> Server: POST /users
Server ->> Server: Is parameters valid? (true)
Server ->> Server: Is username already exists? (false)
Server-->> Client: 200 SUCCESS
```

**GET /login**
User login authentication API.
```mermaid
sequenceDiagram
Client ->> Server: GET /login
Server ->> Server: Is username/password correct? (false)
Server-->> Client: 401 UNAUTHORIZED
Client ->> Server: GET /users
Server ->> Server: Is username/password correct? (true)
Server-->> Client: 200 SUCESS
```

**GET /users**
Gets information about the logged in user. A successfully authenticated request returns information related to the user and the books ordered
```mermaid
sequenceDiagram
Client ->> Server: GET /users
Server ->> Server: Is permission granted? (false)
Server-->> Client: 401 UNAUTHORIZED
Client ->> Server: GET /users
Server ->> Server: Is permission granted? (true)
Server-->> Client: 200 SUCESS: { name: "John", ... }
```

**POST /users/orders**
Order books and store order information in Orders table (BOOK_ORDER). This returns the total price for a successful order
```mermaid
sequenceDiagram
Client ->> Server: POST /users/orders
Server ->> Server: Is the book exists? (false)
Server-->> Client: 200 SUCCESS: { price: 0.0 }
Client ->> Server: GET /users/orders
Server ->> Server: Is the book exists? (true)
Server ->> Server: Calculate the price?
Server-->> Client: 200 SUCCESS: { price: 250.50 }
```

**DELETE /users**
Delete logged in user’s record and order history.
```mermaid
sequenceDiagram
Client ->> Server: DELETE /users
Server ->> Server: Remove all related orders
Server ->> Server: Remove user
Server-->> Client: 200 SUCCESS
```

# API Document

You can find the [API Document](https://raw.githubusercontent.com/juthakiat/temp/master/api-docs.json) here.

