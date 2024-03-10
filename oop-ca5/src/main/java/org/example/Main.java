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

        int id, stock;
        double weight, price, clarity;
        String name, type, colour;

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
                System.out.print("Gem's Type: "); type = kb.next();
                System.out.print("Gem's Weight: "); weight = kb.nextDouble();
                System.out.print("Gem's Price: "); price = kb.nextDouble();
                System.out.print("Gem's Clarity: "); clarity = kb.nextDouble();
                System.out.print("Gem's Stock: "); stock = kb.nextInt();
                System.out.print("Gem's Colour: "); colour = kb.next();
//                Gem gem = new Gem(id, name, type, weight, price, clarity, stock, colour);
//                insertGem(gem); break;
            default:
                System.out.println("Invalid Input.");
                break;
        }
    }

    public static void deleteGemById(int id){

    }
}