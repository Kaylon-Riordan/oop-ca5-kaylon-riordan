package org.example;

/**
 * Author: Kaylon Riordan
 * Refactored by Anastasia McCormac
 * Base entity class.
 */
public class Gem {
    private int id, stock;
    private double weight, price, clarity;
    private String name, type, colour;

    public Gem(int id, String name, String type, double weight, double price, double clarity, int stock, String colour) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.weight = weight;
        this.price = price;
        this.clarity = clarity;
        this.stock = stock;
        this.colour = colour;
    }

    public Gem() {
    }

    public int getId() {return id;}
    public int getStock() {return stock;}
    public double getWeight() {return weight;}
    public double getPrice() {return price;}
    public double getClarity() {return clarity;}
    public String getName() {return name;}
    public String getType() {return type;}
    public String getColour() {return colour;}

    public void setId(int id) {this.id = id;}
    public void setStock(int stock) {this.stock = stock;}
    public void setWeight(double weight) {this.weight = weight;}
    public void setPrice(double price) {this.price = price;}
    public void setClarity(double clarity) {this.clarity = clarity;}
    public void setName(String name) {this.name = name;}
    public void setType(String type) {this.type = type;}
    public void setColour(String colour) {this.colour = colour;}

    @Override
    public String toString() {
        return "Gem{ " +
                "id=" + id +
                ", stock=" + stock +
                ", weight=" + weight +
                ", price=" + price +
                ", clarity=" + clarity +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", colour='" + colour + '\'' +
                '}';
    }
}
