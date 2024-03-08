package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Menu();
    }

    public static void Menu() {
        Scanner kb = new Scanner(System.in);
        System.out.print("1: Get a list of all gems.\n2: Get a gem by its ID.\n" +
                "3: Delete a gem by its ID.\n4: Add a gem to the list.\n");
        int f = kb.nextInt();

        int id;

        switch(f) {
            case 1:
//                getAllGems(); break;
            case 2:
                System.out.print("Enter ID: ");
                id = kb.nextInt();
//                getGemById(id); break;
            case 3:
                System.out.print("Enter ID: ");
                id = kb.nextInt();
//                deleteGemById(id); break;
            case 4:
//                insertGem(); break;
            default:
                System.out.println("Invalid Input.");
                break;
        }
    }
}