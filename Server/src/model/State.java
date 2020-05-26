package model;

import java.io.Serializable;

interface State extends Serializable
{
    void setAvailable(Book book);
    void setBorrowed(Book book);
    boolean available();
}