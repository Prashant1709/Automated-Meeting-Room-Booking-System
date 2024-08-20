package com.amrbsapp.entity;

import javax.management.relation.Role;

public class Member extends User {

    public Member(String userID, String name, String email, String phone, RoleType role) {
        super(userID, name, email, phone, role);
    }


}
