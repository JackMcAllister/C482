package Application_C482;

import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class MainFormController {
    Inventory inventory;
    Scene partScene;
    Scene productScene;
    PartFormController partFormController;

    @FXML TableView<Part> partsTable;
    @FXML TableColumn<Part, String> partIDColumn;
    @FXML TableColumn<Part, String> partNameColumn;
    @FXML TableColumn<Part, String> partInventoryColumn;
    @FXML TableColumn<Part, String> partPriceColumn;



    public void setInventory(Inventory inventoryparameter){
        this.inventory = inventoryparameter;

        partsTable.setItems(inventory.getAllParts());
        partIDColumn.setCellValueFactory((c) -> new SimpleStringProperty(String.valueOf(c.getValue().getId())));
        partNameColumn.setCellValueFactory((c) -> new SimpleStringProperty(String.valueOf(c.getValue().getName())));
        partInventoryColumn.setCellValueFactory((c) -> new SimpleStringProperty(String.valueOf(c.getValue().getStock())));
        partPriceColumn.setCellValueFactory((c) -> new SimpleStringProperty(String.valueOf(c.getValue().getPrice())));


    }
    public void setPartScene (Scene partScene, PartFormController partFormController){
        this.partScene = partScene;
        this.partFormController = partFormController;
    }
    public void setProductScene (Scene productScene){
        this.productScene = productScene;
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

    //Main Add Product button
    public void addProductClick(MouseEvent mouseEvent) {
        Stage stage = (Stage)((Node)mouseEvent.getSource()).getScene().getWindow();
        stage.setScene(productScene);
    }


}
