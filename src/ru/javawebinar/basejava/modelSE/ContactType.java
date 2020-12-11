package ru.javawebinar.basejava.modelSE;

public enum ContactType {
    PHONE("Телефон"),
    SKYPE("Skype"),
    EMAIL("email"),
    LINKEDIN("Профиль LinkedIn"),
    GITHUB("Профиль GitHub"),
    STACKOVERFLOW("Профиль StackOverflow"),
    HOMEPAGE("Домашняя страница");

    private String title;

    ContactType(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
