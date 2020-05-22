package model;

import java.io.Serializable;

/**
 * * A class to store the title and author of the book *
 * * @author Johnny Creciun *
 * *@version 1.0 – May 2020
 * *implementing AbstractBook
 * */

class BookStateOff implements State, Serializable
{
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