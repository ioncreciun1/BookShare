package model;

import java.io.Serializable;

/**
 * * A class to set state of the book to not available  *
 * * @author Johnny Creciun *
 * *@version 1.0 – May 2020
 * *implementing State, Serializable
 * */

class BookStateOn implements State
{

    @Override public void setAvailable(Book book)
    {
        //nothing
    }

    @Override public void setBorrowed(Book book)
    {
        book.setState(new BookStateOff());
    }

    /**
     * Returning true for book state
     * @return true
     */

    @Override
    public boolean available() {
        return true;
    }
}