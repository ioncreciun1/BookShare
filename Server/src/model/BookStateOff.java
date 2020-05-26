package model;

import java.io.Serializable;

/**
 * * A class to set state of the book to available
 * *implementing State, Serializable
 * */

class BookStateOff implements State
{

    @Override public void setAvailable(Book book)
    {
        book.setState(new BookStateOn());
    }

    @Override public void setBorrowed(Book book)
    {

    }

    /**
     * Returning false for book state
     * @return false
     */
    @Override
    public boolean available() {
        return false;
    }
}