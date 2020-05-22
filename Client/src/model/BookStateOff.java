package model;

import java.io.Serializable;

class BookStateOff implements State, Serializable
{
    @Override
    public void click(Book book, Boolean available) {
        System.out.println(available);
        book.setState(new BookStateOn());
    }

    @Override
    public boolean available() {
        return false;
    }
}