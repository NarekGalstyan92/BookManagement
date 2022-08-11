package commands;

public interface Commands {
    int LOGOUT = 0;
    int ADD_BOOK = 1;
    int PRINT_ALL_BOOKS = 2;
    int PRINT_BOOKS_BY_AUTHOR_SURNAME = 3;
    int PRINT_BOOKS_BY_GENRE = 4;
    int PRINT_BOOKS_BY_PRICE_RANGE = 5;
    int SHOW_COUNT_OF_BOOKS_BY_AUTHOR = 6;
    int ADD_AUTHOR = 7;
    int PRINT_ALL_AUTHORS = 8;

    int EXIT = 0;
    int LOGIN = 1;
    int REGISTER = 2;

    static void printAdminCommands() {
        System.out.println("Please input " + LOGOUT + " to exit");
        System.out.println("Please input " + ADD_BOOK + " to add book");
        System.out.println("Please input " + PRINT_ALL_BOOKS + " to print all books");
        System.out.println("Please input " + PRINT_BOOKS_BY_AUTHOR_SURNAME + " to print books by author surname");
        System.out.println("Please input " + PRINT_BOOKS_BY_GENRE + " to print books by genre");
        System.out.println("Please input " + PRINT_BOOKS_BY_PRICE_RANGE + " to print books by price range");
        System.out.println("Please input " + SHOW_COUNT_OF_BOOKS_BY_AUTHOR + " to show count of the book");
        System.out.println("Please input " + ADD_AUTHOR + " to add author");
        System.out.println("Please input " + PRINT_ALL_AUTHORS + " to show all authors");
    }

    static void printUserCommands() {
        System.out.println("Please input " + LOGOUT + " to exit");
        System.out.println("Please input " + PRINT_ALL_BOOKS + " to print all books");
        System.out.println("Please input " + PRINT_BOOKS_BY_AUTHOR_SURNAME + " to print books by author surname");
        System.out.println("Please input " + PRINT_BOOKS_BY_GENRE + " to print books by genre");
        System.out.println("Please input " + PRINT_BOOKS_BY_PRICE_RANGE + " to print books by price range");
        System.out.println("Please input " + SHOW_COUNT_OF_BOOKS_BY_AUTHOR + " to show count of the book");
        System.out.println("Please input " + PRINT_ALL_AUTHORS + " to show all authors");
    }

    static void printLoginCommands() {
        System.out.println("Please input " + EXIT + " to exit");
        System.out.println("Please input " + LOGIN + " to login");
        System.out.println("Please input " + REGISTER + " to register");
    }
}
