package com.app.task;

public class Book {
    private String title;
    private String author;
    private String price;
    public Book(String title, String author, String price) {
        this.title = title;
        this.author = author;
        this.price = price;
    }
    public String getTitle() {
        return title;
    }
    public String getAuthor() {
        return author;
    }
    public String getPrice() {
        return price;
    }
    public String toString() {
        return  "Title  : " + title + "\n" +  "Author : " + author + "\n" +"Price  : " + price;}
    public static void main(String[] args) {
        Book b1 = new Book("Basics", "Kunal garg", "1000");
        System.out.println(b1);
    }
}
