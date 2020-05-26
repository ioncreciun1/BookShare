package model;

import java.io.Serializable;

/**
 * * A class to set state of the book to available  *
 * * @author Johnny Creciun *
 * *@version 1.0 – May 2020
 * *implementing State, Serializable
 * */

class BookStateOff implements State, Serializable
{
    /**
     * Setting the book state to available
     * @param book taking the book to set it state to available
     * @param available
     */

    @Override
    public void click(Book book, Boolean available) {
        System.out.println(available);
        book.setState(new BookStateOn());
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