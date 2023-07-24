package com.epam.mjc.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;


public class FileReader {

    public Profile getDataFromFile(File file) throws IOException {
        try (BufferedReader reader = new BufferedReader(new java.io.FileReader(file))) {
            String name = null;
            Integer age = null;
            String email = null;
            Long phone = null;

            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("Name:")) {
                    name = line.substring(6).trim();
                } else if (line.startsWith("Age:")) {
                    age = Integer.parseInt(line.substring(5).trim());
                } else if (line.startsWith("Email:")) {
                    email = line.substring(7).trim();
                } else if (line.startsWith("Phone:")) {
                    phone = Long.parseLong(line.substring(7).trim());
                }
            }

            return new Profile(name, age, email, phone);
        }
    }

    public static void main(String[] args) throws IOException {
        String fileName = "Profile.txt";
        File file = new File(FileReader.class.getResource("/" + fileName).getFile());
        FileReader fileReader = new FileReader();

        Profile profile = fileReader.getDataFromFile(file);
        System.out.println("Profile data:");
        System.out.println("Name: " + profile.getName());
        System.out.println("Age: " + profile.getAge());
        System.out.println("Email: " + profile.getEmail());
        System.out.println("Phone: " + profile.getPhone());
    }
}