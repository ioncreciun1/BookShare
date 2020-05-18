package model;

public class Book {
    private String description;
    private String username;
    private AbstractBook book;
    private State state;

    public Book(String username, String title, String author, String language, String description, String category){
        this.state = new BookStateOff();
        this.description = description;
        book = AbstractBookFactory.getBookFly(title,author,language,category);
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
        return book.getLanguage();
    }

    public String getCategory(){
        return book.getBook_type();
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
