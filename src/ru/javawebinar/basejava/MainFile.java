package ru.javawebinar.basejava;

import java.io.File;

public class MainFile {
    public static void main(String[] args) {
        dir(".\\", "");
    }

    private static void dir(String path, String offset) {
        File file = new File(path);
        if (file.isDirectory()) {
            String[] list = file.list();
            if (list != null) {
                System.out.println(offset + file.getName().toString());
                for (String name : list) {
                    dir(path+"\\"+name, offset + "\t");
                }
            }
        } else {
            System.out.println(offset + file.getName());
        }
    }
}