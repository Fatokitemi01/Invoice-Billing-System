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
 * @author Flocks Connect
 */
public class RecordStore {
    private final SimpleIntegerProperty ID;
    private final SimpleStringProperty CustomerName;
    private final SimpleIntegerProperty Phoneno;
    private final SimpleStringProperty Email;
    private final SimpleStringProperty PurchaseDate;
    private final SimpleStringProperty PaidDate;
    private final SimpleStringProperty TotalAmt;
    private final SimpleStringProperty TotalNumItems;
    private final SimpleStringProperty Status;

    public RecordStore(Integer ID, String CustomerName, Integer Phoneno, String Email, String PurchaseDate, String PaidDate, String TotalAmt, String TotalNumItems, String Status) {
        this.ID = new SimpleIntegerProperty(ID);
        this.CustomerName = new SimpleStringProperty(CustomerName);
        this.Phoneno = new SimpleIntegerProperty(Phoneno);
        this.Email = new SimpleStringProperty(Email);
        this.PurchaseDate = new SimpleStringProperty(PurchaseDate);
        this.PaidDate = new SimpleStringProperty(PaidDate);
        this.TotalAmt = new SimpleStringProperty(TotalAmt);
        this.TotalNumItems = new SimpleStringProperty(TotalNumItems);
        this.Status = new SimpleStringProperty(Status);
    }

    public Integer getID() {
        return ID.get();
    }

    public String getCustomerName() {
        return CustomerName.get();
    }
    
    public Integer getPhoneno() {
        return Phoneno.get();
    }

    public String getEmail() {
        return Email.get();
    }

    public String getPurchaseDate() {
        return PurchaseDate.get();
    }

    public String getPaidDate() {
        return PaidDate.get();
    }

    public String getTotalAmt() {
        return TotalAmt.get();
    }

    public String getTotalNumItems() {
        return TotalNumItems.get();
    }

    public String getStatus() {
        return Status.get();
    }
    
    
}
