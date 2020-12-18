package ru.javawebinar.basejava;

import ru.javawebinar.basejava.model.Resume;
import ru.javawebinar.basejava.storage.AbstractFileStorage;
import ru.javawebinar.basejava.storage.AbstractStorage;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class MainFile {
    public static void main(String[] args) {
        dir(".\\");
    }

    private static void dir(String path) {
        File file = new File(path);
        if (file.isDirectory()) {
            String[] list = file.list();
            if (list != null) {
                System.out.println("(D)" + file.toString());
                for (String name : list) {
                    dir(path+"\\"+name);
                }
            }
        } else {
            System.out.println("(f)" + file.getName());
        }
    }
}