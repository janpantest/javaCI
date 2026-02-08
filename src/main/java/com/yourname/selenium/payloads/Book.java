package com.yourname.selenium.payloads;    

public class Book {

    private String isbn;
    private String title;  // Assuming the book also has a title

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    // Override the toString() method to provide a meaningful string representation of the object
    @Override
    public String toString() {
        return "Book{isbn='" + isbn + "', title='" + title + "'}";
    }
}

