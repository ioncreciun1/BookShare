package model;

import java.io.Serializable;

public class Book  implements Serializable
{
    private String description;
    private String username;
    private AbstractBook book;
    private String language;
    private String category;
    private State state;

    public Book(String username, String title, String author, String language, String description, String category){
        this.state = new BookStateOn();
        this.description = description;
        this.language = language;
        this.category = category;
        book = AbstractBookFactory.getBookFly(title,author);
        this.username = username;
    }

    public String getUsername(){
        return username;
    }

    public String getTitle(){
        return book.getTitle();
    }

    public String getAuthor(){
        return book.getAuthor();
    }

    public String getLanguage(){
        return language;
    }

    public String getCategory(){
        return category;
    }

    public String getDescription(){
        return description;
    }

    void setState(State newState) {
        state = newState;
    }

    public boolean available(){
        return state.available();
    }
}
