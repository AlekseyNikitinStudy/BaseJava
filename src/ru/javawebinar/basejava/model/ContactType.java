package ru.javawebinar.basejava.model;

public enum ContactType {
    PHONE("Телефон"),
    SKYPE("Skype"),
    EMAIL("email"),
    LINKEDIN("Профиль LinkedIn"),
    GITHUB("Профиль GitHub"),
    STACKOVERFLOW("Профиль StackOverflow"),
    HOMEPAGE("Домашняя страница");

    private final String title;

    ContactType(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
