package model;

class BookState implements State {
    @Override
    public void writeName(Book book, String name) {
        System.out.println(name.toLowerCase());
        book.setState(this);
    }
}