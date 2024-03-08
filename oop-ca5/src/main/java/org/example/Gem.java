package org.example;

public class Gem {
    int id, inStock;
    double size, clarity;
    String name, category;

    public Gem(int id, String name, String category, double size, double clarity, int inStock) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.size = size;
        this.clarity = clarity;
        this.inStock = inStock;
    }

    public int getId() {return id;}
    public int getInStock() {return inStock;}
    public double getSize() {return size;}
    public double getClarity() {return clarity;}
    public String getName() {return name;}
    public String getCategory() {return category;}

    public void setId(int id) {this.id = id;}
    public void setInStock(int inStock) {this.inStock = inStock;}
    public void setSize(double size) {this.size = size;}
    public void setClarity(double clarity) {this.clarity = clarity;}
    public void setName(String name) {this.name = name;}
    public void setCategory(String category) {this.category = category;}

    @Override
    public String toString() {
        return "Gem{" +
                "id=" + id +
                ", inStock=" + inStock +
                ", size=" + size +
                ", clarity=" + clarity +
                ", name='" + name + '\'' +
                ", category='" + category + '\'' +
                '}';
    }
}
