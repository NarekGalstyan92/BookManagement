package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

import static util.DateUtil.dateToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book {
    private String title;
    private Author author;
    private double price;
    private int count;
    private String genre;
    private User registeredUser;
    private Date registerDate;

    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", author=" + '\'' + author.getSurname() + '\'' +
                ", price=" + price +
                ", count=" + count +
                ", genre='" + genre + '\'' +
                ", book added by " + '\'' + registeredUser.getName() + '\'' +
                ", registration date " + '\'' + dateToString(registerDate) + '\'' +
                '}';
    }


}
