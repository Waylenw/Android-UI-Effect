package com.listindex.project;

import com.listindex.project.view.IndexString;

/**
 * Created by waylenwang on 2015/8/27.
 */
public class Item implements IndexString {

    private String name;
    private int pic;

    public Item(String name, int pic) {
        super();
        this.name = name;
        this.pic = pic;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPic() {
        return pic;
    }

    public void setPic(int pic) {
        this.pic = pic;
    }

    @Override
    public char getFirstChar() {
        char c = name.charAt(0);
        if (c >= 'a' && c <= 'z') {
            c = (char) (c - 'a' + 'A');
        }
        return c;
    }

    @Override
    public String getString() {
        return name;
    }

}