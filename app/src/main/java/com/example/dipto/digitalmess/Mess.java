package com.example.dipto.digitalmess;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dipto on 11/18/2017.
 */

public class Mess {
    private String name;
    private String mess_id;
    private String location;
    private String member_type;

    public void setName(String name) {
        this.name = name;
    }

    public void setMess_id(String mess_id) {
        this.mess_id = mess_id;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setMember_type(String member_type) {
        this.member_type = member_type;
    }

    public void setNumber_of_members(int number_of_members) {
        this.number_of_members = number_of_members;
    }

    public void setMembers_list(List<Member> members_list) {
        this.members_list = members_list;
    }

    private int number_of_members;
    private List<Member> members_list = new ArrayList<>();


    public String getName() {
        return name;
    }

    public String getId() {
        return mess_id;
    }

    public String getLocation() {
        return location;
    }

    public String getMember_type() {
        return member_type;
    }

    public int getNumber_of_members() {
        return number_of_members;
    }

    public List<Member> getMemberslist() {
        return members_list;
    }
}
