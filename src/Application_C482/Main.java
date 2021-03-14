package Application_C482;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Main extends Application {

    Scene mainForm,productForm, partForm;
    MainFormController mainFormController;
    ProductFormController productFormController;
    PartFormController partFormController;
    Inventory inventory;

    private void initializeScene (){
        try {
            inventory = new Inventory();


            FXMLLoader loader = new FXMLLoader();
            Pane p = loader.load(getClass().getResource("MainForm.fxml").openStream());
            mainFormController = (MainFormController) loader.getController();
            mainForm = new Scene(p);

            loader = new FXMLLoader();
            p = loader.load(getClass().getResource("ProductForm.fxml").openStream());
            productFormController = (ProductFormController) loader.getController();
            productForm = new Scene(p);

            loader = new FXMLLoader();
            p = loader.load(getClass().getResource("PartForm.fxml").openStream());
            partFormController = (PartFormController) loader.getController();
            partForm = new Scene(p);

            //Set reference to inventory in controllers
            mainFormController.setInventory(inventory);
            productFormController.setInventory(inventory);
            partFormController.setInventory(inventory);

            //set references between controllers for navigation
            mainFormController.setPartScene(partForm, partFormController);
            mainFormController.setProductScene(productForm,productFormController);
            productFormController.setMainScene(mainForm);
            partFormController.setMainScene(mainForm);

            //Prepoulate form
            this.setupTest();


        }
        catch (Exception e){
            System.out.println(e.getStackTrace().toString());
            ;
        }

    }
    public void setupTest(){
        inventory.addPart(new InHouse("test IH", 2.0, 2, 2, 2, 2));
        inventory.addPart(new Outsourced("test OS", 2.0, 2, 2, 2, "2"));

    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        this.initializeScene();
        primaryStage.setTitle("Main Form");
        primaryStage.setScene(mainForm);
        primaryStage.show();




        /**
        Product tricycle = new Product(1, "unicycle", 20.00, 1, 2, 3);
        System.out.println("Before any parts: " + Part.getNextIDavailable());

        System.out.println("brakes are created");
        InHouse brakes = new InHouse(Part.getNextIDavailable(), "brakes", 2.00, 3, 1, 2, 5);
        System.out.println("Next ID to be assigned :" + Part.getNextIDavailable());

        System.out.println("tires are created");
        InHouse tires = new InHouse(Part.getNextIDavailable(), "Tires", 2.00, 3, 1, 2, 5);
        System.out.println("Next ID to be assigned :" + Part.getNextIDavailable());

        System.out.println("rim are created");
        InHouse rim =  new InHouse(Part.getNextIDavailable(), "Rim", 2.00, 3, 1, 2, 5);
        System.out.println("Next ID to be assigned :" + Part.getNextIDavailable());

        tricycle.addAssociatedPart(brakes);
        tricycle.addAssociatedPart(tires);
        tricycle.addAssociatedPart(rim);
        tricycle.deleteAssociatedPart(brakes);

        System.out.println("handlebars are created");
        InHouse handlebars = new InHouse(Part.getNextIDavailable(), "HandleBar", 2.00, 3, 1, 2, 5);
        System.out.println("Next ID to be assigned :" + Part.getNextIDavailable());

        tricycle.addAssociatedPart(handlebars);


        System.out.println("Next ID to be assigned :" + Part.getNextIDavailable());
        for(Part temp: tricycle.getAllAssociatedParts()){
            System.out.println(temp.getName() + ": \tID: " + temp.getId());
        }
         **/

    }


    public static void main(String[] args)
    {
        launch(args);
    }
}
