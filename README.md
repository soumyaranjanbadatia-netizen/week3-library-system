# Library Management System

## Project Description

The Library Management System is a console-based application developed using Java. It helps manage books, library members, and book borrowing and returning operations.

The project demonstrates core Java concepts, Object-Oriented Programming (OOP), collections, file handling, exception handling, and input validation.

## Features

- Add, view, search, and remove books.
- Filter books by availability.
- Register and view library members.
- Search for members.
- Borrow and return books.
- View active loans and overdue books.
- Calculate library statistics.
- Save and load library data using file handling.
- Handle invalid input and common errors.
- Maintain data between program executions.

## Technologies Used

- Java
- Object-Oriented Programming (OOP)
- ArrayList and Collections
- File Handling and Serialization
- Exception Handling
- Visual Studio Code

## Project Structure

```text
LibraryManagement/
├── src/
│   └── Library/
│       ├── Book.java
│       ├── Member.java
│       ├── Loan.java
│       ├── Library.java
│       ├── FileHandler.java
│       └── Main.java
├── bin/
├── lib/
├── library.dat
└── README.md
```

**Note:** `library.dat` is generated when the application saves its data. The `bin` folder contains compiled Java classes. The package name and folder capitalisation must match your actual source code.

## Prerequisites

- Java Development Kit (JDK), preferably JDK 17 or later.
- Visual Studio Code or another Java-compatible IDE.
- Java compiler (`javac`) and Java runtime (`java`) configured in your system PATH.

## Installation and Setup

1. Clone or download this project.
2. Open the `LibraryManagement` folder in Visual Studio Code.
3. Open the integrated terminal.
4. Compile the Java source files:

```powershell
javac -d bin src/Library/*.java
```

5. Run the application:

```powershell
java -cp bin Library.Main
```

These commands assume the package declaration in all source files is `package Library;`. If your package is named `library`, use it `library.Main` instead.

## How to Use

1. Start the application using the commands above.
2. Select an option from the displayed menu.
3. Follow the prompts to enter book or member information.
4. Use the borrowing and returning options to manage loans.
5. View statistics and overdue loans when required.
6. Select `0` to exit the application.

## Data Storage

The application uses Java object serialisation to save library data in a local file named `library.dat`.

Saved data can be loaded when the application starts again, allowing books, members, and loan records to persist between sessions.

## Testing

The application can be tested using the following scenarios:

- Add a book and verify it appears in the book list.
- Register a member and verify the member details.
- Borrow an available book and confirm its availability changes.
- Return a borrowed book and confirm it becomes available again.
- Search for existing and nonexistent books or members.
- Enter invalid menu options and invalid data.
- Exit and restart the application to verify saved data persists.

## Learning Outcomes

- Understanding Java classes, objects, constructors, and encapsulation.
- Applying OOP concepts to a practical project.
- Working with ArrayLists and collections.
- Reading and writing data using file handling.
- Handling exceptions and validating user input.
- Implementing search, filtering, and CRUD operations.
- Developing a menu-driven Java application.

## Future Enhancements

- Develop a graphical user interface.
- Integrate a MySQL database.
- Add login and authentication.
- Build a web-based version using Java Spring Boot.

## Author

Developed as a Java learning project to practise core programming concepts and build a practical library management system. Object-Orientated
