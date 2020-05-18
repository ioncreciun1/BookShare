package model;

public class Book {
    private int bookId;
    private String title;
    private String author;
    private String language;
    private String description;
    private String category;
    private State state;

    public Book(int bookId, String title, String author, String language, String description, String category, State state){
        this.state = state;
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.language = language;
        this.description = description;
        this.category = category;
    }

    void setState(State newState) {
        state = newState;
    }

    public void writeName(String name) {
        state.writeName(this, name);
    }
}
