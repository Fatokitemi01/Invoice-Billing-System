/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package billinginvoice;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Caleb Azu-Benson
 */
public class StoreGoods {
    private final SimpleStringProperty Barcode;
    private final SimpleStringProperty storeitem;
    private final SimpleStringProperty storedes;
    private final SimpleStringProperty storeprice;
    private final SimpleIntegerProperty storestockin;
    private final SimpleIntegerProperty storestockout;
    private final SimpleIntegerProperty StoreStore;

    public StoreGoods(String Barcode, String storeitem, String storedes, String storeprice, Integer storestockin, Integer storestockout, Integer StoreStore) {
        this.Barcode = new SimpleStringProperty(Barcode);
        this.storeitem = new SimpleStringProperty(storeitem);
        this.storedes = new SimpleStringProperty(storedes);
        this.storeprice = new SimpleStringProperty(storeprice);
        this.storestockin = new SimpleIntegerProperty(storestockin);
        this.storestockout = new SimpleIntegerProperty(storestockout);
        this.StoreStore = new SimpleIntegerProperty(StoreStore);
    }

    public String getBarcode() {
        return Barcode.get();
    }

    public String getStoreitem() {
        return storeitem.get();
    }
    
    public String getStoredes() {
        return storedes.get();
    }

    public String getStoreprice() {
        return storeprice.get();
    }

    public Integer getStorestockin() {
        return storestockin.get();
    }

    public Integer getStorestockout() {
        return storestockout.get();
    }

    public Integer getStoreStore() {
        return StoreStore.get();
    }
}
