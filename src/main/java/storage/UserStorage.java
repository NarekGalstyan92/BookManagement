package storage;

import model.User;

public class UserStorage {
    private User[] array = new User[10];
    private int size = 0;

    public void add(User user) {
        if (array.length == size) {
            increaseArray();
        }
        array[size++] = user;
    }

    public void print() {
        for (int i = 0; i < size; i++) {
            System.out.println(i + ". " + array[i]);
        }
    }

    private void increaseArray() {
        User[] temp = new User[array.length + 10];
        for (int i = 0; i < array.length; i++) {
            temp[i] = array[i];
        }
        array = temp;
    }

    public void deleteByIndex(int index) {
        if (index < 0 || index >= size) {
            System.out.println("Invalid index");
        } else {
            for (int i = index; i < size; i++) {
                array[i] = array[i + 1];
            }
            size--;
        }
    }

    public void printUserByLogin(String login) {
        for (int i = 0; i < size; i++) {
            if (array[i].getEmail().equals(login)) {
                System.out.println(array[i]);
            }
        }
    }

    public int getSize() {
        return size;
    }

    /* Not working code*/

//    public void setAccount(String login, String password) {
//        if (login < 0 || login >= size) {
//            System.out.println("Invalid index");
//        } else {
//            array[login].setLogin(login);
//
//        }
//    }

    public User getUserByIndex(int index) {
        if (index < 0 || index > size) {
            return null;
        } else {
            return array[index];
        }
    }

    public User getUserByEmail(String email) {
        for (int i = 0; i < size; i++) {
            if (array[i].getEmail().equals(email)) {
                return array[i];
            }
        }
        return null;
    }
}

