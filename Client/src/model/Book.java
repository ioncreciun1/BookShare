package model;

import java.io.Serializable;

/*
 * implements serializable
 * *Serialization in Java is a mechanism of writing the state of an object into a byte-stream for RMI.
 * .*/
public class Book  implements Serializable
{
    private String description;
    private String username;
    private AbstractBook book;
    private String language;
    private String category;
    private State state;
    private String bookID;
    /*class constructor to create a book from input from user
    @param String username
        the users identification
    * @param String title
      the title of the book
       @param author
         the author of the book
       @param String language
             the language that is book is written selected by dropdown by user
        @param String description
           the description of the book entered by the user,
        @param String Category
             the category of the book selected by the user
        */
    public Book(String username, String title, String author, String language, String description, String category){
        this.state = new BookStateOn();
        this.description = description;
        this.language = language;
        this.category = category;
        book = AbstractBookFactory.getBookFly(title,author);
        this.username = username;
    }
    public Book(String username, String bookID, String title, String author, String language, String description, String category){
        this.state = new BookStateOn();
        this.description = description;
        this.language = language;
        this.category = category;
        this.bookID = bookID;
        book = AbstractBookFactory.getBookFly(title,author);
        this.username = username;
    }
    /*@return String username
     *      returns the username of the user*/
    public String getUsername(){
        return username;
    }
    /*@return String title
     *      returns the title of the book*/
    public String getTitle(){
        return book.getTitle();
    }
    /*@return String author
     *      returns the author of the book*/
    public String getAuthor(){
        return book.getAuthor();
    }
    /*@return String language
     *      returns the language of the book*/
    public String getLanguage(){
        return language;
    }
    /*@return String category
     *      returns the category of the book*/
    public String getCategory(){
        return category;
    }
    /*@return String title
     *      returns the title of the book*/
    public String getDescription(){
        return description;
    }
    /*sets the state of the book to default of available
     */
    public void setAvailable()
    {
        state.setAvailable(this);
    }
    public void setBorrowed()
    {
        state.setBorrowed(this);
    }
    public void setState(State newState) {
        state = newState;
    }
    /*@return boolean available
     *   returns a state of available*/
    public boolean available(){
        return state.available();
    }
}
