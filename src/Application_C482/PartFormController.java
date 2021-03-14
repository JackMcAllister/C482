package Application_C482;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;

import java.text.DecimalFormat;
import java.text.ParsePosition;
import java.util.Arrays;
import java.util.Iterator;

public class PartFormController {
    Inventory inventory;
    Scene mainScene;
    Part partbeingmodified = null;
    @FXML TextField idTextBox;
    @FXML TextField nameTextBox;
    @FXML TextField priceTextBox;
    @FXML TextField invTextBox;
    @FXML TextField minTextBox;
    @FXML TextField maxTextBox;
    @FXML TextField partSpecificTextBox;
    @FXML ToggleGroup manufacturer;
    @FXML Text partOriginTextLabel;
    @FXML RadioButton inHouseRadioButton;
    @FXML RadioButton outsourcedRadioButton;
    @FXML Text addOrModifyText;


    public void setInventory(Inventory inventoryparameter){
        this.inventory = inventoryparameter;
    }
    public void setMainScene(Scene mainScene){
        this.mainScene = mainScene;
    }

    // This one is for the modify parts button
    public void setupFormElementsForModifyPart(Part part){
        addOrModifyText.setText("Modify Part");
        partbeingmodified = part;
        if(part.getType().equals("Outsourced")){
            outsourcedRadioButton.setSelected(true);
            partSpecificTextBox.setText(((Outsourced)part).getCompanyName());
            partOriginTextLabel.setText("Company Name");
        }
        else {
            inHouseRadioButton.setSelected(true);
            partSpecificTextBox.setText(String.valueOf(((InHouse)part).getMachineId()));
            partOriginTextLabel.setText("Machine ID");
        }
        idTextBox.setText(String.valueOf(part.getId()));
        nameTextBox.setText(String.valueOf(part.getName()));
        invTextBox.setText(String.valueOf(part.getStock()));
        priceTextBox.setText(String.valueOf(part.getPrice()));
        minTextBox.setText(String.valueOf(part.getMin()));
        maxTextBox.setText(String.valueOf(part.getMax()));

    }
    // This one is for the add parts button
    public void setupFormElementsForAddPart(){
        inHouseRadioButton.setSelected(true);
        addOrModifyText.setText("Add Part");
        idTextBox.setText("auto-generated");
        nameTextBox.setText("");
        invTextBox.setText("");
        priceTextBox.setText("");
        minTextBox.setText("");
        maxTextBox.setText("");
        partSpecificTextBox.setText("");




    }

    public void savePartClick (MouseEvent mouseEvent) {
        try {
            // declaring local variables
            RadioButton selectedRadioButton = (RadioButton)(manufacturer.getSelectedToggle());
            double price = Double.parseDouble(priceTextBox.getText());
            String name  = nameTextBox.getText();
            int stock = Integer.parseInt(invTextBox.getText());
            int min = Integer.parseInt(minTextBox.getText());
            int max = Integer.parseInt(maxTextBox.getText());



            if(partbeingmodified == null)
            {
                if(selectedRadioButton.getText().equals("In-House")){
                    InHouse newpart = new InHouse(name, price, stock, min, max, Integer.parseInt(partSpecificTextBox.getText()));
                    inventory.addPart(newpart);
                }
                else{
                    Outsourced newpart = new Outsourced(name, price, stock, min, max, partSpecificTextBox.getText());
                    inventory.addPart(newpart);
                }
                partbeingmodified = null;

            }
            else{
                int id = Integer.parseInt(idTextBox.getText());
                //If the type of the part being modified has not changed
                if(partbeingmodified.getType().equals(selectedRadioButton.getText())){
                    partbeingmodified.setMax(Integer.parseInt(maxTextBox.getText()));
                    partbeingmodified.setMin(Integer.parseInt(minTextBox.getText()));
                    partbeingmodified.setName(nameTextBox.getText());
                    partbeingmodified.setPrice(Double.parseDouble(priceTextBox.getText()));
                    partbeingmodified.setStock(Integer.parseInt(invTextBox.getText()));
                    if(selectedRadioButton.getText().equals("In-House")){
                        ((InHouse)partbeingmodified).setMachineId(Integer.parseInt(partSpecificTextBox.getText()));
                    }
                    else{
                        ((Outsourced)partbeingmodified).setCompanyName(partSpecificTextBox.getText());
                    }
                }
                //If the type of the part being modified has changed
                else{
                    inventory.deletePart(partbeingmodified);
                    //Changed from outsourced to in-house
                    if(selectedRadioButton.getText().equals("In-House")){
                        int machineid =(Integer.parseInt(partSpecificTextBox.getText()));
                        inventory.addPart(new InHouse(id, name, price, stock, min, max, machineid));

                    }
                    //Changed from In-house to Outsourced
                    else{
                        String companyName = partSpecificTextBox.getText();
                        inventory.addPart(new Outsourced(id, name, price, stock, min, max, companyName));
                    }

                }

            }
            Stage stage = (Stage)((Node)mouseEvent.getSource()).getScene().getWindow();
            stage.setScene(mainScene);
        }
        catch (Exception e){

        }


    }

    public void cancelClick (MouseEvent mouseEvent){
        partbeingmodified = null;
        Stage stage = (Stage)((Node)mouseEvent.getSource()).getScene().getWindow();
        stage.setScene(mainScene);


    }

    public void togglePart(MouseEvent mouseEvent) {
        RadioButton selectedRadioButton = (RadioButton)(manufacturer.getSelectedToggle());
        if(selectedRadioButton.getText().equals("In-House")){
            partOriginTextLabel.setText("Machine ID");
        }
        else {
            partOriginTextLabel.setText("Company Name");
        }
    }
}
