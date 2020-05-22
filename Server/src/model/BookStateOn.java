package model;

import java.io.Serializable;


/**
 * * A class to set state of the book to not available
 * *implementing State, Serializable
 * */

class BookStateOn implements State, Serializable
{
    /**
     * Setting the book state to not available
     * @param  book taking the book to set it state to not available
     * @param  available
     */

    @Override
    public void click(Book book, Boolean available) {
        System.out.println(available);
        book.setState(this);
    }

    /**
     * Returning true for book state
     * @return true
     */

    @Override
    public boolean available()
    {
        return true;
    }
}