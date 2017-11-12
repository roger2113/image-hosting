package gulmak.mak.imagehosting.domain;

import org.jetbrains.annotations.Contract;

public enum Role {

    USER("USER"), ADMIN("ADMIN");

    private String name;

    Role(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
