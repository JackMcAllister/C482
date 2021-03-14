package Application_C482;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;

import java.text.DecimalFormat;
import java.text.ParsePosition;
import java.util.Arrays;
import java.util.Iterator;

public class ProductFormController {
    Inventory inventory;
    Scene mainScene;
    ObservableList<Part> addedAssocParts;
    Product productBeingModified = null;

    @FXML TextField productNameTextBox;
    @FXML TextField stockTextBox;
    @FXML TextField priceTextBox;
    @FXML TextField maxTextBox;
    @FXML TextField minTextBox;
    @FXML TableView<Part> assocPartsTableView;
    @FXML TableColumn<Part, String> assocPartsIdColumn;
    @FXML TableColumn<Part, String> assocPartsNameColumn;
    @FXML TableColumn<Part, String> assocPartsStockColumn;
    @FXML TableColumn<Part, String> assocPartsPriceColumn;
    @FXML TableView<Part> allPartsTableView;
    @FXML TableColumn<Part, String> allPartsIdColumn;
    @FXML TableColumn<Part, String> allPartsNameColumn;
    @FXML TableColumn<Part, String> allPartsStockColumn;
    @FXML TableColumn<Part, String> allPartsPriceColumn;
    @FXML Button addAssocPartButton;
    @FXML Button removeAssocPartButton;
    @FXML Button cancelButton;
    @FXML Button saveProductButton;
    @FXML Text addOrModifyText;
    @FXML TextField productIdTextBox;
    @FXML TextField partSearchProductForm;



    public void setInventory(Inventory inventoryparameter){
        this.inventory = inventoryparameter;
        allPartsTableView.setItems(inventory.getAllParts());

        allPartsIdColumn.setCellValueFactory((c) -> new SimpleStringProperty(String.valueOf(c.getValue().getId())));
        allPartsNameColumn.setCellValueFactory((c) -> new SimpleStringProperty(String.valueOf(c.getValue().getName())));
        allPartsStockColumn.setCellValueFactory((c) -> new SimpleStringProperty(String.valueOf(c.getValue().getStock())));
        allPartsPriceColumn.setCellValueFactory((c) -> new SimpleStringProperty(String.valueOf(c.getValue().getPrice())));



    }
    public void setMainScene(Scene mainScene){
        this.mainScene = mainScene;
    }


    private void setupAssociatePatsTable(){
        assocPartsTableView.setItems(addedAssocParts); //binding empty list to tableview
        assocPartsIdColumn.setCellValueFactory((c) -> new SimpleStringProperty(String.valueOf(c.getValue().getId())));
        assocPartsNameColumn.setCellValueFactory((c) -> new SimpleStringProperty(String.valueOf(c.getValue().getName())));
        assocPartsStockColumn.setCellValueFactory((c) -> new SimpleStringProperty(String.valueOf(c.getValue().getStock())));
        assocPartsPriceColumn.setCellValueFactory((c) -> new SimpleStringProperty(String.valueOf(c.getValue().getPrice())));

    }
    //This one is for Add Product Button
    public void setupFormElementsForAddProducts(){
        addedAssocParts = FXCollections.observableArrayList();
        this.setupAssociatePatsTable();
        addOrModifyText.setText("Add Product");
        productIdTextBox.setText("Auto-Generated");
        productNameTextBox.setText("");
        stockTextBox.setText("");
        priceTextBox.setText("");
        maxTextBox.setText("");
        minTextBox.setText("");

    }

    //This one is for Modify Product Button
    public void setupFormElementsForModifyProduct(Product product){
        productBeingModified = product;
        addedAssocParts =product.getAllAssociatedParts();
        this.setupAssociatePatsTable();
        addOrModifyText.setText("Modify Product");
        productIdTextBox.setText(String.valueOf(product.getId()));
        priceTextBox.setText(String.valueOf(product.getPrice()));
        productNameTextBox.setText(product.getName());
        stockTextBox.setText(String.valueOf(product.getStock()));
        maxTextBox.setText(String.valueOf(product.getMax()));
        minTextBox.setText(String.valueOf(product.getMin()));

    }

    //Save Product Button
    public void saveProductClick(MouseEvent mouseEvent) {
        try{
            String name= productNameTextBox.getText();
            double price = Double.parseDouble(priceTextBox.getText());
            int stock = Integer.parseInt(stockTextBox.getText());
            int max = Integer.parseInt(maxTextBox.getText());
            int min = Integer.parseInt(minTextBox.getText());

            //If saving added product
            if(productBeingModified==null){
                Product addedProduct = new Product(name, price, stock, min, max);
                addedProduct.setAssociatedParts(addedAssocParts);
                inventory.addProduct(addedProduct);
            }
            // Saving modified product
            else{
                int id = productBeingModified.getId();
                inventory.deleteProduct(productBeingModified);
                Product addedProduct = new Product(id, name, price, stock, min, max);
                addedProduct.setAssociatedParts(addedAssocParts);
                inventory.addProduct(addedProduct);
            }
            //Goes back to Main
            Stage stage = (Stage)((Node)mouseEvent.getSource()).getScene().getWindow();
            stage.setScene(mainScene);
        }
        catch (Exception e) {

        }



    }
    // Cancel Mouse Click
    public void cancelClick(MouseEvent mouseEvent) {
        productBeingModified = null;
        Stage stage = (Stage)((Node)mouseEvent.getSource()).getScene().getWindow();
        stage.setScene(mainScene);
    }
    //Add Button in Modify/Add Product View
    public void addAssocPartClick(MouseEvent mouseEvent) {
        if (!addedAssocParts.contains(allPartsTableView.getSelectionModel().getSelectedItem())){
            addedAssocParts.add(allPartsTableView.getSelectionModel().getSelectedItem());
        }
    }

    //Remove Assoc. Part Button in Product View
    public void removeAssocClick(MouseEvent mouseEvent) {
        if (addedAssocParts.toArray().length == 1) {
            addedAssocParts.clear();
        } else {
            addedAssocParts.remove(allPartsTableView.getSelectionModel().getSelectedItem());

        }
    }

    public void filterPart(KeyEvent keyEvent) {
        if(partSearchProductForm.getText().isEmpty()){
            allPartsTableView.setItems(inventory.getAllParts());
        }
        else{
            allPartsTableView.setItems(inventory.lookupPart(partSearchProductForm.getText()));
        }
    }
}

