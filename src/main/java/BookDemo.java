import commands.Commands;
import exceptions.AuthorNotFoundException;
import model.Author;
import model.Book;
import model.User;
import model.UserType;
import storage.AuthorStorage;
import storage.BookStorage;
import storage.UserStorage;

import java.util.Date;

import java.util.Scanner;

import static util.DateUtil.stringToDate;

public class BookDemo implements Commands {
    private static Scanner scanner = new Scanner(System.in);
    private static BookStorage bookStorage = new BookStorage();
    private static AuthorStorage authorStorage = new AuthorStorage();

    private static UserStorage userStorage = new UserStorage();

    private static User currentUser = null;

    public static void main(String[] args) {

        initData();

        boolean run = true;
        while (run) {
            int command;
            try {
                Commands.printLoginCommands();
                command = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Please choose correct number");
                command = -1;
            }
            switch (command) {
                case EXIT:
                    run = false;
                    break;
                case LOGIN:
                    login();
                    break;
                case REGISTER:
                    register();
                    break;
                default:
                    System.out.println("Invalid command");
                    System.out.println();
            }
        }
    }

    private static void register() {
        System.out.println("Please enter name, surname, email, password");
        String userDataStr = scanner.nextLine();
        String[] userData = userDataStr.split(",");
        if (userData.length < 4) {
            System.out.println("Please input correct data!");
        } else {
            if (userStorage.getUserByEmail(userData[0]) == null) {
                User user = new User();
                user.setName(userData[0]);
                user.setSurname(userData[1]);
                user.setEmail(userData[2]);
                user.setPassword(userData[3]);
                user.setUserType(UserType.USER);
                userStorage.add(user);
                System.out.println("User created");
            } else {
                System.out.println("User with " + userData[0] + " already exist!");
            }
        }
    }

    private static void login() {
        System.out.println("Please input email,password");
        try {
            String emailPasswordStr = scanner.nextLine();
            String[] emailPassword = emailPasswordStr.split(",");
            User user = userStorage.getUserByEmail(emailPassword[0]);
            if (user == null) {
                System.out.println("User with " + emailPassword[0] + " does not exist!");
            } else {
                if (user.getPassword().equals(emailPassword[1])) {
                    currentUser = user;
                    if (user.getUserType() == UserType.ADMIN) {
                        loginAdmin();
                    } else if (user.getUserType() == UserType.USER) {
                        loginUser();
                    }
                } else {
                    System.out.println("Password is wrong!");
                }
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Don't forget to fully input email and password, split them just with \",\" without white space");
            login();
        }

    }

    private static void loginAdmin() {
        System.out.println("Welcome " + currentUser.getName());
        boolean run = true;
        while (run) {
            Commands.printAdminCommands();
            int command;
            try {
                command = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                command = -1;
            }
            switch (command) {
                case LOGOUT:
                    run = false;
                    break;
                case ADD_BOOK:
                    addBook();
                    break;
                case PRINT_ALL_BOOKS:
                    bookStorage.print();
                    break;
                case PRINT_BOOKS_BY_AUTHOR_SURNAME:
                    printBookByAuthorSurname();
                    break;
                case PRINT_BOOKS_BY_GENRE:
                    printBookByGenre();
                    break;
                case PRINT_BOOKS_BY_PRICE_RANGE:
                    printBooksByPriceRange();
                    break;
                case SHOW_COUNT_OF_BOOKS_BY_AUTHOR:
                    showCountOfBooks();
                    break;
                case ADD_AUTHOR:
                    addAuthor();
                    break;
                case PRINT_ALL_AUTHORS:
                    authorStorage.print();
                    break;
                default:
                    System.out.println("Invalid command");
                    System.out.println();
            }
        }
    }

    private static void loginUser() {
        System.out.println("Welcome " + currentUser.getName());
        boolean run = true;
        while (run) {
            Commands.printUserCommands();
            int command;
            try {
                command = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                command = -1;
            }
            switch (command) {
                case LOGOUT:
                    run = false;
                    break;
                case PRINT_ALL_BOOKS:
                    bookStorage.print();
                    break;
                case PRINT_BOOKS_BY_AUTHOR_SURNAME:
                    printBookByAuthorSurname();
                    break;
                case PRINT_BOOKS_BY_GENRE:
                    printBookByGenre();
                    break;
                case PRINT_BOOKS_BY_PRICE_RANGE:
                    printBooksByPriceRange();
                    break;
                case SHOW_COUNT_OF_BOOKS_BY_AUTHOR:
                    showCountOfBooks();
                    break;
                case PRINT_ALL_AUTHORS:
                    authorStorage.print();
                    break;
                default:
                    System.out.println("Invalid command");
                    System.out.println();
            }
        }
    }


    private static void initData() {
        User admin = new User("admin", "admin", "admin@gmail.com", "admin", UserType.ADMIN);
        User user1 = new User("user", "user", "user@gmail.com", "user", UserType.USER);
        userStorage.add(admin);
        userStorage.add(user1);


        Author Bulgakov = new Author("Mikhail", "Bulgakov", "abc@gmail.com", "male", stringToDate("06/01/2022"));
        Author Charents = new Author("Yeghishe", "Charents", "def@gmail.com", "male", stringToDate("05/15/2022"));
        Author Rowling = new Author("Joanne", "Rowling", "miban@mail.ru", "female", stringToDate("07/28/2022"));
        authorStorage.add(Bulgakov);
        authorStorage.add(Charents);
        authorStorage.add(Rowling);

        bookStorage.add(new Book("Master & Margarita", Bulgakov, 100, 1, "Novel", admin, new Date()));
        bookStorage.add(new Book("Poems", Charents, 50, 1, "Novel", admin, new Date()));
        bookStorage.add(new Book("Harry Potter", Rowling, 85, 20, "Fantasy", admin, new Date()));
    }

    private static void addAuthor() {

        System.out.println("Input author's name");
        String authorName = scanner.nextLine();
        System.out.println("Input author's surname");
        String authorSurname = scanner.nextLine();
        System.out.println("Input author's e-mail");
        String email = scanner.nextLine();
        System.out.println("Input author's gender (male or female)");
        String gender = scanner.nextLine();
        System.out.println("Please add registration date in MM/dd/yyyy format");
        String strDate = scanner.nextLine();

        if (authorName != null) {
            authorName = authorName.trim();
        }
        if (authorSurname != null) {
            authorSurname = authorSurname.trim();
        }
        if (email != null) {
            email = email.trim();
        }
        if (!email.contains("@") | !email.contains(".com") & !email.contains(".ru")) {
            System.out.println("E-mail must contain symbols '@', and domains '.com' or '.ru' or '.am' ");
            addAuthor();
        }
        if (gender != null) {
            gender = gender.trim();
        }
        if (!gender.contains("male") & !gender.contains("female")) {
            System.out.println("Please be sure you entered 'male' or 'female' ");
            addAuthor();
        }
        if (!strDate.contains("/")) {
            System.out.println("please divide month, day and day with /");
            addAuthor();
        }

        Author author = new Author(authorName, authorSurname, email, gender, stringToDate(strDate));
        authorStorage.add(author);
        System.out.println("Author " + author + " created");
        System.out.println();

    }

    private static void printBooksByPriceRange() {
        System.out.println("Please input book's minimum price");
        double minPrice = 0;
        try {
            minPrice = Double.parseDouble(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Please enter only numbers. They must be without any symbols, white spaces or letters, be positive");
        }

        System.out.println("Please input book's maximum price");
        double maxPrice = 0;
        try {
            maxPrice = Double.parseDouble(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Please enter only numbers. They must be without any symbols, white spaces or letters and be positive numbers");
        }
        bookStorage.printBooksByPriceRange(minPrice, maxPrice);
    }

    private static void printBookByGenre() {
        System.out.println("Please input book's genre");
        String genre = scanner.nextLine().toLowerCase();
        if (genre == null) {
            System.out.println("Please be sure you entered genre");
        } else {
            bookStorage.printBookByGenre(genre);
        }
    }

    private static void printBookByAuthorSurname() {
        if (authorStorage.getSize() != 0) {
            System.out.println("Please input author surname");
            String authorSurname = scanner.nextLine();
            if (authorSurname == null) {  // I have a problem here. if I enter nothing (just push Enter), this if doesn't work
                System.out.println("Please be sure you entered Author's name");
                printBookByAuthorSurname();
            } else {
                authorSurname.trim();
                bookStorage.printBookByAuthorName(authorSurname);
            }
        } else {
            System.out.println("You haven't any book in your storage");
        }
    }

    private static void showCountOfBooks() {
        System.out.println("Please enter book title");
        String title = scanner.nextLine();
        System.out.println(bookStorage.bookCount(title));
    }

    private static void addBook() {
        if (authorStorage.getSize() != 0) {
            System.out.println("Please choose author index");
            authorStorage.print();

            Author author = null;

            try {
                int authorIndex = Integer.parseInt(scanner.nextLine());
                if (authorIndex == authorStorage.getSize()) {
                    System.out.println("Invalid index");
                    return;
                }

                author = authorStorage.getAuthorByIndex(authorIndex);
                System.out.println("Please input books title");
                String title = scanner.nextLine();
                System.out.println("Please input book's price");
                int price = 0;
                try {
                    price = Integer.parseInt(scanner.nextLine());
                } catch (NumberFormatException e) {
                    System.out.println("Please enter only numbers. They must be without any symbols, white spaces or letters and be positive numbers");
                    addBook();
                }
                System.out.println("Please input book's count");
                int count = 0;
                try {
                    count = Integer.parseInt(scanner.nextLine());
                } catch (NumberFormatException e) {
                    System.out.println("Please enter only numbers. They must be without any symbols, white spaces or letters and be positive numbers");
                }

                System.out.println("Please input book's genre");
                String genre = scanner.nextLine();
                if (genre != null) {
                    genre.trim();
                } else {
                    System.out.println("Please input genre");
                    addBook();
                }

                Book book = new Book(title, author, price, count, genre, currentUser, new Date());
                bookStorage.add(book);
                System.out.println("book added successfully!");
                System.out.println(book);

            } catch (AuthorNotFoundException | NumberFormatException e) {
                System.out.println("Please choose correct index or input correct number!!!");
                addBook();
            }
        }
    }
}

