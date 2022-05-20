package com.ryan.redlight.entity;

public class Client extends User {
    public Client(User user) {
        setUserId(user.getUserId());
        setNickName(user.getNickName());
        setEmail(user.getEmail());
        setLocation(user.getLocation());
        setPassword(user.getPassword());
        setPhone(user.getPhone());
        setUsername(user.getUsername());
    }

    public Client() {
    }

    @Override
    public String toString() {
        return super.toString();
    }
}