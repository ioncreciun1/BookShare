package model;

class BookStateOff implements State {
    @Override
    public void click(Book book, Boolean available) {
        System.out.println(available);
        book.setState(this);
    }

    @Override
    public boolean available() {
        return false;
    }
}