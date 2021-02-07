package com.example.indievents.DAO;

import java.text.ParseException;
import java.util.ArrayList;


public interface PojoDAO {

    public long add(Object obj);
    public int update(Object obj);
    public void delete(Object obj);
    public Object search(Object obj) throws ParseException;
    public ArrayList getAll() throws ParseException;
}
