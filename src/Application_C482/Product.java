package Application_C482;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Product {
    private static int nextIDavailable = 1000;
    private ObservableList<Part> associatedParts;
    private int id;
    private String name;
    private double price;
    private int stock;
    private int min;
    private int max;


    public Product(String name, double price, int stock, int min, int max) {
        this.id = nextIDavailable;
        this.name = name;
        this.price = price;
        this.min = min;
        this.max = max;
        if(stock>max){
            this.stock = max;
        }
        else if (stock< min){
            stock = min;
        }
        else{
            this.stock = stock;

        }
        associatedParts = FXCollections.observableArrayList();
        nextIDavailable++;
    }
    public Product(int id,String name, double price, int stock, int min, int max) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.min = min;
        this.max = max;
        associatedParts = FXCollections.observableArrayList();

    }

    public static int getNextIDavailable() {
        return nextIDavailable;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }


    /**
     * @return the price
     */
    public double getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * @return the stock
     */
    public int getStock() {
        return stock;
    }

    /**
     * @param stock the stock to set
     */
    public void setStock(int stock) {
        this.stock = stock;
    }

    /**
     * @return the min
     */
    public int getMin() {
        return min;
    }

    /**
     * @param min the min to set
     */
    public void setMin(int min) {
        this.min = min;
    }

    /**
     * @return the max
     */
    public int getMax() {
        return max;
    }

    /**
     * @param max the max to set
     */
    public void setMax(int max) {
        this.max = max;
    }


    public void addAssociatedPart (Part part) {
        associatedParts.add(part);
    }

    public boolean deleteAssociatedPart(Part part){
        return associatedParts.remove(part);
    }


    public ObservableList<Part> getAllAssociatedParts() {
        return associatedParts;
    }

    public void setAssociatedParts (ObservableList<Part> assocParts){
        this.associatedParts = assocParts;
    }
}