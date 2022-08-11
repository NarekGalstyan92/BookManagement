package storage;

import model.Book;


public class BookStorage {
    private Book[] array = new Book[10];
    private int size = 0;

    private void increaseArray() {
        Book[] temp = new Book[array.length + 10];
        for (int i = 0; i < array.length; i++) { // or instead of 'for' we can do
            temp[i] = array[i];                  // **System.arraycopy(array, 0, temp, 0, array.length);**
        }
        array = temp;
    }

    public void add(Book book) {
        if (array.length == size) {
            increaseArray();
        }
        array[size++] = book;
    }

    public void print() {
        for (int i = 0; i < size; i++) {
            System.out.println(i + ". " + array[i]);
        }
        System.out.println();
    }

    public void printBookByAuthorName(String authorSurame) {
        int count = 0;
        for (int i = 0; i < size; i++) {
            if (array[i].getAuthor().getSurname().equals(authorSurame)) {
                count++;
                System.out.println(array[i]);
            }
        }
        if (count == 0) {
            System.out.println("Author not found");
        }
    }

    public void printBookByGenre(String genre) {
        int count = 0;
        for (int i = 0; i < size; i++) {
            if (array[i].getGenre().toLowerCase().equals(genre)) {
                count++;
                System.out.println(array[i]);
            }
            if (count == 0) {
                System.out.println("Genre not found");
                break;
            }
        }
    }

//    public boolean checkSameAuthor(String name, String surname) {
//        for (int i = 0; i < size; i++) {
//            if (array[i].getAuthor().equals(name) && array[i].getAuthorSurName().equals(surname)) {
//                System.out.println("Author already exist");
//                return true;
//            }
//        }
//        return false;
//    }

    public void printBooksByPriceRange(double minPrice, double maxPrice) {
        int count = 0;
        for (int i = 0; i < size; i++) {
            if (array[i].getPrice() >= minPrice && array[i].getPrice() <= maxPrice) {
                count++;
                System.out.println(array[i]);
            }
            if (count == 0) {
                System.out.println("Sorry, no match");
                break;
            }
        }
    }

    public int bookCount(String title) {
        int a = 0;
        for (int i = 0; i < size; i++) {
            if (array[i].getTitle().equals(title)) {
                a = array[i].getCount();
            }
        }
        if (a == 0) {
            System.out.println("Wrong/missing author");
        }
        return a;
    }

}
