package Application_C482;

import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;

public class ProductFormController {
    Inventory inventory;
    Scene mainScene;

    public void setInventory(Inventory inventoryparameter){
        this.inventory = inventoryparameter;

    }

    public void setMainScene (Scene MainScene){
        this.mainScene = mainScene;
    }

    public void saveProduct(MouseEvent saveProduct) {
       // Product p = new Product();
      //  inventory.addProduct(p);
    }
}
