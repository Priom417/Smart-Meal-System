package com.example.dipto.digitalmess;

/**
 * Created by Dipto on 11/19/2017.
 */

public class Present{
    public static Member logged_in_member;

    public static void setLogged_in_member(Member logged_in_member) {
        Present.logged_in_member = logged_in_member;
    }

    public static Member getLogged_in_member() {
        return logged_in_member;
    }
}
