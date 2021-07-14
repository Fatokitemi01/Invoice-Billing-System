package billinginvoice;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Azu-Benson Caleb
 */
public class itemsData {
    private final SimpleStringProperty item;
    private final SimpleStringProperty item_descprition;
    private final SimpleDoubleProperty Quantity;
    private final SimpleDoubleProperty Price;
    private final SimpleDoubleProperty Amount;

    public itemsData(String item, String item_descprition, Double Quantity, Double Price, Double Amount) {
        this.item = new SimpleStringProperty(item);
        this.item_descprition = new SimpleStringProperty(item_descprition);
        this.Quantity = new SimpleDoubleProperty(Quantity);
        this.Price = new SimpleDoubleProperty(Price);
        this.Amount = new SimpleDoubleProperty(Amount);
    }

    public String getItem() {
        return item.get();
    }

    public String getItem_descprition() {
        return item_descprition.get();
    }

    public Double getQuantity() {
        return Quantity.get();
    }

    public Double getPrice() {
        return Price.get();
    }

    public Double getAmount() {
        return Amount.get();
    }

    
  
    
}
