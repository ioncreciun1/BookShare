package model;

import java.io.Serializable;

class BookStateOn implements State, Serializable
{
    @Override
    public void click(Book book, Boolean available) {
        System.out.println(available);
        book.setState(new BookStateOff());
    }

    @Override
    public boolean available() {
        return true;
    }
}