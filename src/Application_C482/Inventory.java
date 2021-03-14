package Application_C482;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Inventory {

    private ObservableList<Part> allParts;
    private ObservableList<Product> allProducts;

    public Inventory(){
        allParts = FXCollections.observableArrayList();
        allProducts = FXCollections.observableArrayList();
    }
//addPart(newPart:Part):void
    public void addPart(Part newPart){
        allParts.add(newPart);
    }
//+ addProduct(newProduct:Product):void
    public void addProduct(Product newProduct){
        allProducts.add(newProduct);
    }
//+ lookupPart(partId:int):Part
    public Part lookupPart(int partID){
        for(Part temp1:allParts){
            if (temp1.getId()== partID){
                return temp1;
            }
        }
        return null;
    }
    //+ lookupPart(partName:String):ObservableList<Part>
    public Part lookupPart(String partName){
        for(Part temp1:allParts){
            if (temp1.getName().toLowerCase().contains(partName.toLowerCase())) {
                return temp1;
            }
        }
        return null;
    }
//+ lookupProduct(productId:int):Product
    public Product lookupProduct(int productID){
        for(Product temp1:allProducts){
            if (temp1.getId()== productID){
                return temp1;
            }
        }
        return null;
    }

//+ lookupProduct(productName:String):ObservableList<Product>
    public Product lookupProduct(String productName){
        for(Product temp1:allProducts){
            if (temp1.getName().toLowerCase().contains(productName.toLowerCase())) {
                return temp1;
            }
        }
        return null;
    }

//+ updatePart(index:int, selectedPart:Part):void
    public void updatePart(int index, Part selectedPart){
        allParts.remove(allParts.get(index));
        allParts.add(index, selectedPart);
    }

//+ updateProduct(index:int, newProduct:Product):void
    public void updateProduct(int index, Product newProduct){
        allProducts.remove(allProducts.get(index));
        allProducts.add(index, newProduct);
    }

//+ deletePart(selectedPart:Part):boolean
    public boolean deletePart(Part selectedPart){
        return allParts.remove(selectedPart);
    }

//+ deleteProduct(selectedProduct:Product):
    public boolean deleteProduct(Product selectedProduct){
        return allProducts.remove(selectedProduct);
    }
//+ getAllParts():ObservableList<Part>
    public ObservableList<Part> getAllParts(){
        return allParts;
    }

//+ getAllProducts():ObservableList<Product>
    public ObservableList<Product> getAllProducts(){
        return allProducts;
    }

}
