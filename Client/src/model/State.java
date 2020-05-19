package model;

interface State {
    void click(Book book, Boolean available);
    boolean available();
}