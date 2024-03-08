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

        int id, inStock;
        double size, clarity;
        String name, category;

        switch(f) {
            case 1:
//                getAllGems(); break;
            case 2:
                System.out.print("Enter ID: "); id = kb.nextInt();
//                getGemById(id); break;
            case 3:
                System.out.print("Enter ID: "); id = kb.nextInt();
//                deleteGemById(id); break;
            case 4:
                System.out.print("Gem's ID: "); id = kb.nextInt();
                System.out.print("Gem's Name: "); name = kb.next();
                System.out.print("Gem's Category: "); category = kb.next();
                System.out.print("Gem's Size: "); size = kb.nextDouble();
                System.out.print("Gem's Clarity: "); clarity = kb.nextDouble();
                System.out.print("Gems In Stock: "); inStock = kb.nextInt();
//                Gem gem = new Gem(id, name, category, size, clarity, inStock);
//                insertGem(); break;
            default:
                System.out.println("Invalid Input.");
                break;
        }
    }
}