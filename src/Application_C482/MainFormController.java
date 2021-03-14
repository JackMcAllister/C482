package Application_C482;

import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class MainFormController
{
    Inventory inventory;
    Scene partScene;
    Scene productScene;
    PartFormController partFormController;
    ProductFormController productFormController;

    @FXML TableView<Part> partsTable;
    @FXML TableColumn<Part, String> partIDColumn;
    @FXML TableColumn<Part, String> partNameColumn;
    @FXML TableColumn<Part, String> partInventoryColumn;
    @FXML TableColumn<Part, String> partPriceColumn;
    @FXML Button addProductButton;
    @FXML Button modifyProductButton;
    @FXML Button deleteProductButton;
    @FXML TableView<Product> productsTableView;
    @FXML TableColumn<Product, String> productIdColumn;
    @FXML TableColumn<Product, String> productNameColumn;
    @FXML TableColumn<Product, String> productStockColumn;
    @FXML TableColumn<Product, String> productPriceColumn;
    @FXML Button exitButton;
    @FXML TextField partsSearch;
    @FXML TextField productSearch;



    public void setInventory(Inventory inventoryparameter){
        this.inventory = inventoryparameter;

        partsTable.setItems(inventory.getAllParts());
        partIDColumn.setCellValueFactory((c) -> new SimpleStringProperty(String.valueOf(c.getValue().getId())));
        partNameColumn.setCellValueFactory((c) -> new SimpleStringProperty(String.valueOf(c.getValue().getName())));
        partInventoryColumn.setCellValueFactory((c) -> new SimpleStringProperty(String.valueOf(c.getValue().getStock())));
        partPriceColumn.setCellValueFactory((c) -> new SimpleStringProperty(String.valueOf(c.getValue().getPrice())));

        productsTableView.setItems(inventory.getAllProducts());
        productIdColumn.setCellValueFactory((c) -> new SimpleStringProperty(String.valueOf(c.getValue().getId())));
        productNameColumn.setCellValueFactory((c) -> new SimpleStringProperty(String.valueOf(c.getValue().getName())));
        productStockColumn.setCellValueFactory((c) -> new SimpleStringProperty(String.valueOf(c.getValue().getStock())));
        productPriceColumn.setCellValueFactory((c) -> new SimpleStringProperty(String.valueOf(c.getValue().getPrice())));

    }
    public void setPartScene (Scene partScene, PartFormController partFormController){
        this.partScene = partScene;
        this.partFormController = partFormController;
    }
    public void setProductScene (Scene productScene, ProductFormController productFormController){
        this.productScene = productScene;
        this.productFormController= productFormController;
    }

    //Main Add Part button
       public void addPartClick(MouseEvent mouseEvent) {
        partFormController.setupFormElementsForAddPart();
        Stage stage = (Stage)((Node)mouseEvent.getSource()).getScene().getWindow();
        stage.setScene(partScene);

    }
    //Main Modify Part button
    public void modifyPartClick(MouseEvent mouseEvent) {
        partFormController.setupFormElementsForModifyPart(partsTable.getSelectionModel().getSelectedItem());
        Stage stage = (Stage)((Node)mouseEvent.getSource()).getScene().getWindow();
        stage.setScene(partScene);
    }
    //Main Delete Part button
    public void deletePart(MouseEvent mouseEvent) {
        if(partsTable.getSelectionModel().getSelectedItem() != null){
            inventory.deletePart(partsTable.getSelectionModel().getSelectedItem());
        }

    }

    //Main Add Product button
    public void addProductClick(MouseEvent mouseEvent) {
        productFormController.setupFormElementsForAddProducts();
        Stage stage = (Stage)((Node)mouseEvent.getSource()).getScene().getWindow();
        stage.setScene(productScene);
    }

    //Main Modify Product button
    public void modifyProductClick(MouseEvent mouseEvent) {
        productFormController.setupFormElementsForModifyProduct(productsTableView.getSelectionModel().getSelectedItem());
        Stage stage = (Stage)((Node)mouseEvent.getSource()).getScene().getWindow();
        stage.setScene(productScene);
    }

    //Main Delete Product Button
    public void deleteProductClick(MouseEvent mouseEvent) {
        if(productsTableView.getSelectionModel().getSelectedItem() != null){
            inventory.deleteProduct(productsTableView.getSelectionModel().getSelectedItem());
        }
    }

    public void exitClick(MouseEvent mouseEvent) {
        System.exit(0);
    }


    public void filterParts(KeyEvent keyEvent) {
        if(partsSearch.getText().isEmpty()){
            partsTable.setItems(inventory.getAllParts());
        }
        else{
            partsTable.setItems(inventory.lookupPart(partsSearch.getText()));
        }
    }

    public void filterProducts(KeyEvent keyEvent) {
        if(productSearch.getText().isEmpty()){
            productsTableView.setItems(inventory.getAllProducts());
        }
        else{
            productsTableView.setItems(inventory.lookupProduct(productSearch.getText()));
        }
    }
}

