package de.reutow.phonebook.entity;

public enum Group {

    PRIVATE("private"),
    HOME("home"),
    OTHER("other");

    Group(String group) {
        this.groupName = group;
    }

    private final String groupName;

    public String getGroupName() {
        return groupName;
    }

}
