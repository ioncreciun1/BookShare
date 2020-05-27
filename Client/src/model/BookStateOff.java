package model;

import java.io.Serializable;

/**
 * * A class to set state of the book to available
 * *implementing State, Serializable
 * */

class BookStateOff implements State
{

    /**
     * set book status to available
     * @param book specific book
     */
    @Override public void setAvailable(Book book)
    {
        book.setState(new BookStateOn());
    }

    /**
     * this method is doing nothing
     * @param book
     */
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