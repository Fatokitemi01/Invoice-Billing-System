/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package billinginvoice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.swing.JOptionPane;

/**
 *
 * @author Flocks Connect
 */
class InvoiceDatabase {
    Connection conn = null;
    public static Connection ConnectDb(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/bill_invoice_database?useTimezone=true&serverTimezone=UTC","root","");
                    return conn;                   
        } catch(Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
    }
    
    public static ObservableList<RecordStore> getRecordList(){
        Connection conn = ConnectDb();
        ObservableList<RecordStore> data = FXCollections.observableArrayList(); 
        try{
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM salesrecord");
            ResultSet rs = ps.executeQuery();
            //RecordStore salesrecord;
            while(rs.next()){
                data.add(new RecordStore(rs.getInt(1), 
                        rs.getString(2), rs.getInt(3),
                        rs.getString(4), rs.getString(5), 
                        rs.getString(6), rs.getString(7),
                        rs.getString(8), rs.getString(9))
                );  
            }
        }catch(Exception e){    
        }
        return data;
    }
    
    public static ObservableList<StoreGoods> getStoreList(){
        Connection conn = ConnectDb();
        ObservableList<StoreGoods> strd = FXCollections.observableArrayList(); 
        try{
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM store_stock");
            ResultSet rs = ps.executeQuery();
            //RecordStore salesrecord;
            while(rs.next()){
                strd.add(new StoreGoods(rs.getString(1), 
                        rs.getString(2), rs.getString(3),
                        rs.getString(4), rs.getInt(5), 
                        rs.getInt(6), rs.getInt(7))
                );  
            }
        }catch(Exception e){    
        }
        return strd;
    }
}
