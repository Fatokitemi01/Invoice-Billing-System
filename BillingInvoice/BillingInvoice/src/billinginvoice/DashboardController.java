package billinginvoice;

import com.jfoenix.controls.JFXButton;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.application.Platform;
import javafx.beans.Observable;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Azu-Benson Caleb
 */
public class DashboardController implements Initializable {
    Scene fxmlFile;
    Parent root;
    Stage window;
    
    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pst = null;
    double FC;
    
    @FXML
    private JFXButton CreateInvoice;
    @FXML
    private JFXButton records;
    @FXML
    private Label UserNameLabel;
    
    //Invoice Page
    //Manage Invoice Table
    @FXML
    private TableView<itemsData> InvoiceTable;
    @FXML
    private TableColumn<itemsData, String> itemTable;
    @FXML
    private TableColumn<itemsData, String> desTable;
    @FXML
    private TableColumn<itemsData, Double> QtyTable;
    @FXML
    private TableColumn<itemsData, Double> priceTable;
    @FXML
    private TableColumn<itemsData, Double> AmountTable;
    //Manage Invoice Table Ends
    
    
    @FXML
    private Label subtotalLabel;
    @FXML
    private Label totalLabel;
    @FXML
    private JFXButton Save;
    @FXML
    private JFXButton Calculator;
    @FXML
    private TextField invoicenumber;
    private TextField senderEmail;
    @FXML
    private TextField Currrentcustomer;
    @FXML
    private DatePicker Date;
    @FXML
    private TextField CustomerPhoneNumber;
    @FXML
    private TextField customerEmail;
    @FXML
    private TextField itemBox;
    @FXML
    private TextField desBox;
    @FXML
    private TextField qtyBox;
    @FXML
    private TextField priceBox;
    @FXML
    private JFXButton Add;
    @FXML
    private JFXButton Delete;
    @FXML
    private Label ErrorCustomer;
    @FXML
    private Label ErrorDate;
    @FXML
    private Label ErrorItem;
    @FXML
    private Label ErrorPrice;
    @FXML
    private Label ErrorDes1;
    @FXML
    private AnchorPane InvoicePage;
    
    //Record Page
    @FXML
    private AnchorPane recordsPage;
    
    //Manage Record Table
    @FXML
    private TableView<RecordStore> RecordTable;
    @FXML
    private TableColumn<RecordStore, Integer> RecordNumberTable;
    @FXML
    private TableColumn<RecordStore, String> RecordCustmTable;
    @FXML
    private TableColumn<RecordStore, Integer> TablePhoneno;
    @FXML
    private TableColumn<RecordStore, String> RecordEmailTable;
    @FXML
    private TableColumn<RecordStore, LocalDate> RecordDPTable;
    @FXML
    private TableColumn<RecordStore, LocalDate> RecordDPaidTable;
    @FXML
    private TableColumn<RecordStore, Integer> RecordAmountTable;
    @FXML
    private TableColumn<RecordStore, Integer> RitemsNumTable;
    @FXML
    private TableColumn<RecordStore, String> RecoardStatusTable;
    //Manage Record Table
    
    @FXML
    private JFXButton RecordsDelete;
    @FXML
    private JFXButton RecordPay;
    @FXML
    private TextField BusinessName;
    @FXML
    private TextField RecordSearch;
    @FXML
    private Label Errorquantity;
    
    //Record Page Ends
    @FXML
    private TextField txt_checkID;
    /**
     * Initializes the controller class.
     * 
     */
 
    Notification noti = new Notification();
    ObservableList<RecordStore> data;
    ObservableList<StoreGoods> strd;
 
    int index = -1;
    int storeindex = -1;
    private int count = 0;
    private double FinalCost = 0.0;
    private double SubCost = 0.0;
    private double ttdaysales;
    
    private final DecimalFormat quantityFormat = new DecimalFormat("#,##0.00");
    final ObservableList<itemsData> list
            = FXCollections.<itemsData>observableArrayList((itemsData Double) -> {
                Observable[] array = new Observable[]{
                };
                return array;
            });
    
    private final DateTimeFormatter dateFormatter = 
            DateTimeFormatter.ofPattern("dd-MMM-yyyy");
    @FXML
    private AnchorPane PrintPage;
    @FXML
    private TextArea textfilled;
    @FXML
    private Button printoutt;
    @FXML
    private ChoiceBox choiceBox;
    @FXML
    private Label Labelday;
    @FXML
    private CheckBox checkBox;
    @FXML
    private JFXButton Preview;
    @FXML
    private Label ErrorPhoneno;
    @FXML
    private Label ErrorEmail;
    @FXML
    private ChoiceBox<String> taxbox;
    @FXML
    private AnchorPane StorePage;
    @FXML
    private TextField storebarcode;
    @FXML
    private TextField storeitem;
    @FXML
    private TextField storedes;
    @FXML
    private TextField storeprice;
    @FXML
    private TextField storestockin;
    
    @FXML
    private TableView<StoreGoods> StoreItems;
    @FXML
    private TableColumn<StoreGoods, String> Barcode;
    @FXML
    private TableColumn<StoreGoods, String> storeitemR;
    @FXML
    private TableColumn<StoreGoods, String> storedesR;
    @FXML
    private TableColumn<StoreGoods, String> storepriceR;
    @FXML
    private TableColumn<StoreGoods, Integer> StoreStore;
    @FXML
    private TableColumn<StoreGoods, Integer> storestockinR;
    @FXML
    private TableColumn<StoreGoods, Integer> storestockoutR;
    @FXML
    private TextField productcode;
   

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        qtyBox.setText("1");
        Date.setValue(LocalDate.now());
        DisablePastDate();
             
        itemBox.textProperty().addListener((observable, oldValue, newValue) -> {
            Add.setDisable(false);
        });
        
        desBox.textProperty().addListener((observable, oldValue, newValue) -> {
            Add.setDisable(false);
        });
        
        priceBox.textProperty().addListener((observable, oldValue, newValue) -> {
            Add.setDisable(false);
        });
        
        qtyBox.textProperty().addListener((observable, oldValue, newValue) -> {
            Add.setDisable(false);
        });
        
        itemTable.setCellValueFactory(new PropertyValueFactory<>("item"));
        desTable.setCellValueFactory(new PropertyValueFactory<>("item_descprition"));
        QtyTable.setCellValueFactory(new PropertyValueFactory<>("Quantity"));
        priceTable.setCellValueFactory(new PropertyValueFactory<>("Price"));
        AmountTable.setCellValueFactory(new PropertyValueFactory<>("Amount"));
        
        InvoiceTable.setItems(list);
        
        updateinvoicenumber();
        updatetable();
        search_customer();
        updatestore();
       
            
        choiceBox.getItems().addAll("GHS", "$", "₦", "Y");
        choiceBox.setValue("GHS");
        
        taxbox.getItems().addAll("0", "5", "10", "15", "20", "25", "30", "35", "40", "45", "50", "55", "60", "65", "70", "75", "80", "90");
        taxbox.setValue(null);
        
        taxbox.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends String> v, String oldValue, String newValue) -> {
            try{
                double DC;
                
                
                DC = SubCost / 100 * Double.valueOf(newValue);
                FC = SubCost + DC;
                totalLabel.setText(choiceBox.getValue() + " " + quantityFormat.format(FC));
            }catch (NumberFormatException e){                
            }
        });
        
        choiceBox.getSelectionModel().selectedItemProperty().addListener((v, oldValue, newValue) -> {
                if (list.isEmpty()){
                 subtotalLabel.setVisible(false);
                 totalLabel.setVisible(false);
                 }else {
                     SumOutput(FinalCost, SubCost);
                 }
        });
        
         
        
        Labelday.setText("Total Sales at: " + dateFormatter.format(LocalDate.now()));
    }
    
    private void updateinvoicenumber(){
        conn = InvoiceDatabase.ConnectDb();
        String sql = "SELECT id FROM invoicenumber where 1";
        try{
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            while(rs.next()){          
                invoicenumber.setText(rs.getString(1));
            }   
        }   catch (SQLException ex) {
            Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void GetUsersBusinessName(String currentmail, String type){
        conn = InvoiceDatabase.ConnectDb();
        String sql = "SELECT business_name, type FROM users where email = '"+currentmail+"' ";
        try{
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            while(rs.next()){          
                UserNameLabel.setText(rs.getString(1));
                
                if (rs.getString(2).equals("Admin")){
                    recordsPage.setDisable(false);
                }else if(rs.getString(2).equals("Cashier")){
                    RecordPay.setDisable(true);
                    RecordsDelete.setDisable(true);
                }
            }   
        }   catch (SQLException ex) {
            Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void updatetable(){
        RecordNumberTable.setCellValueFactory(new PropertyValueFactory<>("ID"));
        RecordCustmTable.setCellValueFactory(new PropertyValueFactory<>("CustomerName"));
        TablePhoneno.setCellValueFactory(new PropertyValueFactory<>("Phoneno"));
        RecordEmailTable.setCellValueFactory(new PropertyValueFactory<>("Email"));
        RecordDPTable.setCellValueFactory(new PropertyValueFactory<>("PurchaseDate"));
        RecordDPaidTable.setCellValueFactory(new PropertyValueFactory<>("PaidDate"));
        RecordAmountTable.setCellValueFactory(new PropertyValueFactory<>("TotalAmt"));
        RitemsNumTable.setCellValueFactory(new PropertyValueFactory<>("TotalNumItems"));
        RecoardStatusTable.setCellValueFactory(new PropertyValueFactory<>("Status"));
        
        data = InvoiceDatabase.getRecordList();
        RecordTable.setItems(data);
        search_customer();
    }
    
    private void edittable(){
        String status;
        LocalDate paiddate;
        
        status = "PAID";
        paiddate = LocalDate.now();
        
        try{
           conn = InvoiceDatabase.ConnectDb();  
           String value1 = paiddate.toString();
           String value2 = status;
           
           String sql = "UPDATE salesrecord set paid_date = '"+value1+"', status = '"+value2+"' where ID = ?";
      
           pst = conn.prepareStatement(sql);
           pst.setString(1, txt_checkID.getText()); 
           pst.execute();
           updatetable();
        }catch (SQLException e){   
        }
    }
    
    private void search_customer(){
        RecordNumberTable.setCellValueFactory(new PropertyValueFactory<>("ID"));
        RecordCustmTable.setCellValueFactory(new PropertyValueFactory<>("CustomerName"));
        TablePhoneno.setCellValueFactory(new PropertyValueFactory<>("Phoneno"));
        RecordEmailTable.setCellValueFactory(new PropertyValueFactory<>("Email"));
        RecordDPTable.setCellValueFactory(new PropertyValueFactory<>("PurchaseDate"));
        RecordDPaidTable.setCellValueFactory(new PropertyValueFactory<>("PaidDate"));
        RecordAmountTable.setCellValueFactory(new PropertyValueFactory<>("TotalAmt"));
        RitemsNumTable.setCellValueFactory(new PropertyValueFactory<>("TotalNumItems"));
        RecoardStatusTable.setCellValueFactory(new PropertyValueFactory<>("Status"));
        
        data = InvoiceDatabase.getRecordList();
        RecordTable.setItems(data);
        
        FilteredList<RecordStore> filteredData = new FilteredList<>(data, b -> true);
        RecordSearch.textProperty().addListener((observable, oldValue, newValue) -> {
        filteredData.setPredicate(person -> {
                                  if (newValue == null || newValue.isEmpty()){
                                      return true;
                                  }
                                  String lowerCaseFilter = newValue.toLowerCase();
                                  
                                  if (person.getCustomerName().toLowerCase().contains(lowerCaseFilter)){
                                      return true;
                                  }else if (String.valueOf(person.getEmail()).contains(lowerCaseFilter)){
                                      return true;
                                  }else if (String.valueOf(person.getPurchaseDate()).toLowerCase().contains(lowerCaseFilter)){
                                      return true;
                                  }else if (String.valueOf(person.getPhoneno()).toLowerCase().contains(lowerCaseFilter)){
                                      return true;
                                  }else return String.valueOf(person.getPaidDate()).toLowerCase().contains(lowerCaseFilter);
            
                                  });
        });
        SortedList<RecordStore> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(RecordTable.comparatorProperty());
        RecordTable.setItems(sortedData);
    }
  
    private void openCalculatorWindow(String resource, String title) throws IOException{
        root = FXMLLoader.load(getClass().getResource(resource));
        fxmlFile = new Scene(root);
        window = new Stage();
        window.setScene(fxmlFile);
        window.initModality(Modality.APPLICATION_MODAL);
        window.initStyle(StageStyle.UTILITY);
        window.setAlwaysOnTop(true);
        window.setIconified(false);
        window.setTitle(title);
        window.showAndWait();
    }
    
    private void DisablePastDate(){       
       Date.setDayCellFactory(picker -> new DateCell() {
        public void updateItem(LocalDate date, boolean empty) {
            super.updateItem(date, empty);
            LocalDate today = LocalDate.now();

            setDisable(empty || date.compareTo(today) < 0 );
        }
    });
    }

    @FXML
    private void Invoicebtn(ActionEvent event) {             
        InvoicePage.setVisible(true);
        recordsPage.setVisible(false);
        PrintPage.setVisible(false);
        StorePage.setVisible(false);
        textfilled.setText("");
        
        if(list.isEmpty()){
            checkBox.setDisable(true);
            Save.setDisable(true);
        }
    }

    @FXML
    private void Recordbtn(ActionEvent event) {
        InvoicePage.setVisible(false);
        recordsPage.setVisible(true);
        PrintPage.setVisible(false);
        StorePage.setVisible(false);
        textfilled.setText("");
    }
    
    

    @FXML
    private void CloseProgrambtn(ActionEvent event) {
       System.exit(0);
       Platform.exit();
    }
    
      //Customer Details Enable Method
    private void EnableCustomer(){
        invoicenumber.setDisable(false);
        customerEmail.setDisable(false);
        senderEmail.setDisable(false);
        Currrentcustomer.setDisable(false);
        Date.setDisable(false);
    }
    
    
    private void SumOutput(double FC, double SC){
        subtotalLabel.setText(choiceBox.getValue() + " " + quantityFormat.format(SC));
        subtotalLabel.setVisible(true);
        totalLabel.setText(choiceBox.getValue() + " " + quantityFormat.format(FC));
        totalLabel.setVisible(true);
    }
    
    private void clearItemErrorFields() {
        ErrorCustomer.setText("");
        ErrorItem.setText("");
        ErrorPrice.setText("");
        ErrorDes1.setText("");
    }
    
    private void clearQErrorFields() {
        Errorquantity.setText("");
    }
    
    private boolean validateInvoiceItem() {
        clearItemErrorFields();

        boolean valid = true;
        
        //itemBox = null;
        // Item Validation
        String text = itemBox.getText();
        Pattern i = Pattern.compile("[a-zA-Z- ]+");
        Matcher t = i.matcher(text);       
        if (text == null || text.trim().isEmpty()) {
            ErrorItem.setText("Item not specified !");
            ErrorItem.setVisible(true);
            valid = false;
        }else if (t.find() && t.group().equals(text)){
          
        }else{
            ErrorItem.setText("Item cannot be a number !");
            ErrorItem.setVisible(true);
            valid = false;
        }
        // Item Validation Ends
        
        // Price Validation
        String price = priceBox.getText();
        Pattern r = Pattern.compile("[0-9-.]+");
        Matcher e = r.matcher(price);      
        if (price == null || price.trim().isEmpty()) {
            ErrorPrice.setText("Price not specified !");
            ErrorPrice.setVisible(true);
            valid = false;
        }else if (e.find() && e.group().equals(price)){
        
        }else{
            ErrorPrice.setText("Please Enter vaild Price !");
            ErrorPrice.setVisible(true);
            valid = false;
        }
        // Price Validation Ends
        
        // Details Box = null
        String detail = desBox.getText();
        if (detail == null || detail.trim().isEmpty()) {
            ErrorDes1.setText("Descprition not specified !");
            ErrorDes1.setVisible(true);
            valid = false;
        }
        // Details Box = null Ends
        
        // Customer Name Validation
        String customer = Currrentcustomer.getText();
        Pattern p = Pattern.compile("[a-zA-Z- ]+");
        Matcher m = p.matcher(customer);       
        if (customer == null || customer.trim().isEmpty()) {
            ErrorCustomer.setText("Customer name not specified !");
            ErrorCustomer.setVisible(true);
            valid = false;
        }else if (m.find() && m.group().equals(customer)){
          
        }else{
            ErrorCustomer.setText("Please Enter vaild Customer name !");
            ErrorCustomer.setVisible(true);
            valid = false;
        }
        // Customer Name Validation Ends
        
        // Quantity Validation
        String quantity = qtyBox.getText();
        Pattern q = Pattern.compile("[0-9]+");
        Matcher n = q.matcher(quantity);     
        if (quantity == null || quantity.trim().isEmpty()){
            Errorquantity.setText("Not valid!");
            Errorquantity.setVisible(true);
            valid = false;
        }else if (n.find() && n.group().equals(quantity)){
          
        }else{
            Errorquantity.setText("Not valid!");
            Errorquantity.setVisible(true);
            valid = false;
        }
        // Quantity Validation Ends 
        
        String Phoneno = CustomerPhoneNumber.getText();
        Pattern z = Pattern.compile("[0-9]+");
        Matcher x = z.matcher(Phoneno);     
        if (Phoneno == null || Phoneno.trim().isEmpty()){
            ErrorPhoneno.setText("Field is Empty !");
            ErrorPhoneno.setVisible(true);
            valid = false;
        }else if (x.find() && x.group().equals(Phoneno)){
            ErrorPhoneno.setVisible(false);
        }else{
            ErrorPhoneno.setText("Not a valid number !");
            ErrorPhoneno.setVisible(true);
            valid = false;
        }
        
        String emailRegex = customerEmail.getText();
        Pattern emailPat = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$",Pattern.CASE_INSENSITIVE);
        Matcher matcher = emailPat.matcher(emailRegex);
        
        if (emailRegex == null || emailRegex.trim().isEmpty()) {
            ErrorEmail.setVisible(true);
            ErrorEmail.setText("Email field is empty");
            valid = false;
        }else if (matcher.find() && matcher.group().equals(emailRegex)){ 
            ErrorEmail.setText("");
        }else{     
            ErrorEmail.setVisible(true);
            ErrorEmail.setText("example@mail.com");
            valid = false;
        }   
       
       
        return valid;
    }
    
    private boolean NegativeQuantityValidation(){
        boolean valid = true;
        
        int quanty = Integer.parseInt(qtyBox.getText());
        
        if (quanty < 1){
            Errorquantity.setText("1 or Higher!");
            Errorquantity.setVisible(true);
            valid = false;
        }
        
        return valid;
    }
    
    @FXML
    private void Addbtn(ActionEvent event) {
        if (!validateInvoiceItem()) {
            if (NegativeQuantityValidation()){
            Errorquantity.setVisible(false);
           return;
            }
            return;
        }else 
        if (!NegativeQuantityValidation()){
           return;
       } 
        
        
        double qnty = Double.parseDouble(qtyBox.getText());
        double price = Double.parseDouble(priceBox.getText());
        double total;
        
        total = qnty * price;
        
        
        FinalCost = FinalCost + total;
        SubCost = SubCost + total;
         
        itemsData tabdata;
        tabdata = new itemsData(
                itemBox.getText(),
                desBox.getText(),
                qnty,
                price,
                total
        );
        
        InvoiceTable.getItems().add(tabdata);
         
        SumOutput(FinalCost, SubCost );
        
         Delete.setDisable(false);
         checkBox.setDisable(false);
         Save.setDisable(false);
         taxbox.setDisable(false);
         
         itemBox.clear();
         desBox.clear();
         priceBox.clear();
         qtyBox.setText("1");
         
        if (NegativeQuantityValidation())
        {
            Errorquantity.setVisible(false);
        }
    }


    @FXML
    private void Deletebtn(ActionEvent event) {
        ObservableList<itemsData> productSelected, allProducts;
        allProducts = InvoiceTable.getItems();
        productSelected = InvoiceTable.getSelectionModel().getSelectedItems(); 

        
       double totalCostOfSelectedItems = 0 ;
       for (itemsData product : productSelected) {
       totalCostOfSelectedItems = totalCostOfSelectedItems  + product.getAmount();
       }
       
       FinalCost = FinalCost - totalCostOfSelectedItems ;
       SubCost = SubCost - totalCostOfSelectedItems ;

       allProducts.removeAll(productSelected);
       
       SumOutput(FinalCost, SubCost );
       
       if (list.isEmpty()){
           Delete.setDisable(true);
           checkBox.setDisable(true);
           Save.setDisable(true);
           taxbox.setDisable(true);
       }
    }
      
    private void printbill(){
        itemsData tems;
        
        List <List<String>> arrList = new ArrayList<>(); 
        
    textfilled.setText(textfilled.getText() + "*************************************************************************\n");
    textfilled.setText(textfilled.getText() + "                             "+UserNameLabel.getText()+" POSTBILL\n");
    textfilled.setText(textfilled.getText() + "*************************************************************************\n");
    textfilled.setText(textfilled.getText() + "Invoice Number: " + invoicenumber.getText() + "\n");
    textfilled.setText(textfilled.getText() + "Customer Name: " + Currrentcustomer.getText() + "\n");
    textfilled.setText(textfilled.getText() + "Phone no: " + CustomerPhoneNumber.getText() + "\t Email: " + customerEmail.getText() +"\n");
    textfilled.setText(textfilled.getText() + "Date of Purchase: " + LocalDate.now() + "\n");
    textfilled.setText(textfilled.getText() + "\n");
    textfilled.setText(textfilled.getText() + "Product" + "\t\t" + "Qty" + "\t" + "Price("+choiceBox.getValue()+")" + "\t" + "Amount("+choiceBox.getValue()+")" + "\t" + "Description" + "\n");
     
        for(int i = 0; i <InvoiceTable.getItems().size(); i++){
            tems = InvoiceTable.getItems().get(i);
            arrList.add(new ArrayList<>());
            arrList.get(i).add(tems.getItem() + "\t\t|");
            arrList.get(i).add(tems.getQuantity() + "\t|");
            arrList.get(i).add(tems.getPrice() + "\t|");
            arrList.get(i).add(tems.getAmount() + "\t|");
            arrList.get(i).add(tems.getItem_descprition() + "\n");
        }
        
        for (int i = 0; i<arrList.size(); i++){
            for (int j = 0; j < arrList.get(i).size(); j++){
                textfilled.setText(textfilled.getText() + arrList.get(i).get(j));
            }
        }
    
    textfilled.setText(textfilled.getText() + "\n");
    textfilled.setText(textfilled.getText() + "\n");
    textfilled.setText(textfilled.getText() + "\t\t\t\t\t\t" + "Subtotal: " + choiceBox.getValue() + " " + quantityFormat.format(FinalCost) + "\n");
    textfilled.setText(textfilled.getText() + "\t\t\t\t\t\t" + "Tax: " + taxbox.getValue() + "%" + "\n");
    if (FC > 0){
        textfilled.setText(textfilled.getText() + "\t\t\t\t\t\t" + "Balance: " + choiceBox.getValue() + " " + quantityFormat.format(FC) + "\n");
    }else{
        textfilled.setText(textfilled.getText() + "\t\t\t\t\t\t" + "Balance: " + choiceBox.getValue() + " " + quantityFormat.format(FinalCost) + "\n");
    }
    textfilled.setText(textfilled.getText() + "*************************************************************************\n");
    textfilled.setText(textfilled.getText() + "           THANK YOU FOR YOUR PURCHASE, COME AGAIN   \n");
           PrintPage.setVisible(true);
           InvoicePage.setVisible(false);
           recordsPage.setVisible(false);
    }
    
    private void AddRecord_ToTable(String DOP, String PStatus){
        itemsData tems;
        
        for(int i = 0; i <InvoiceTable.getItems().size(); i++){
            tems = InvoiceTable.getItems().get(i);
            count++;
        }
        
        conn = InvoiceDatabase.ConnectDb();
        String sql = "INSERT INTO salesrecord (ID, customer_name, phone_number, email_address, purchase_date, "
                + "paid_date, total_amount, number_of_items, status)values(?,?,?,?,?,?,?,?,?)";
        try{
            pst = conn.prepareStatement(sql);
            pst.setString(1, invoicenumber.getText());
            pst.setString(2, Currrentcustomer.getText());
            pst.setString(3, CustomerPhoneNumber.getText());
            pst.setString(4, customerEmail.getText());
            pst.setString(5, Date.getEditor().getText());
            pst.setString(6, DOP);
            pst.setString(7, String.valueOf(totalLabel.getText()));
            pst.setString(8, String.valueOf(count));
            pst.setString(9, PStatus);
            pst.execute();
        }catch (SQLException e){   
        }
        
        //ttdaysales = ttdaysales + Double.valueOf(quantityFormat.format(FinalCost));
        //BusinessName.setText(String.valueOf(quantityFormat.format(ttdaysales)));
        
                
        updatetable();
        
        StorePage.setVisible(false);
        InvoicePage.setVisible(false);
        recordsPage.setVisible(true);
        qtyBox.setText("1");
    }
    
    private void PayConfirmation(){
        int response = JOptionPane.showConfirmDialog(null, "Are the Items Paid For ?", "Confrim Payment", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        String Date_not_paid = "NOT PAID", Date_PAID = LocalDate.now().toString();
        String UnPAID_Status = "NULL", PAID_Status = "PAID";
        
        switch (response) {
            case JOptionPane.YES_OPTION:
                AddRecord_ToTable(Date_PAID, PAID_Status);
                break;
            case JOptionPane.NO_OPTION:
                AddRecord_ToTable(Date_not_paid, UnPAID_Status);
                break;
            case JOptionPane.CLOSED_OPTION:
                System.out.println("CLOSED.. Closed Selected..");
                break;
            default:
                break;
        }
        
        newinvoice();
    }

    @FXML
    private void Savebtn(ActionEvent event) throws MessagingException {
        if (list.isEmpty()) {
                JOptionPane.showMessageDialog(null, "no item added");
            } else {
                if (checkBox.isSelected()){            
                    printbill();
                    savefile();
                    try{
                    BillToMail();
                    }catch(MessagingException e){
                    }
                    PayConfirmation();
                    try{
                    clearpageCreate();
                    }catch(Exception e){
                           System.out.print(e);
                    }
                }else{
                    savefile();
                    PayConfirmation();
                    clearpageCreate();
                }
            }    
    }
    
    private void clearpageCreate(){
        count = 0;
            Currrentcustomer.clear();
            customerEmail.clear();
            CustomerPhoneNumber.clear();
            itemBox.clear();
            desBox.clear();
            priceBox.clear();
            qtyBox.setText("1");
            
            InvoiceTable.getItems().clear();
            subtotalLabel.setText("");
            subtotalLabel.setVisible(false);
            totalLabel.setText("");
            totalLabel.setVisible(false);
            
            SubCost = 0;
            FinalCost = 0;
    }
    
    private void DeleteRecord(){
        conn = InvoiceDatabase.ConnectDb();
        String sql = "DELETE FROM salesrecord where ID = ?";
        try{
            pst = conn.prepareStatement(sql);
            pst.setString(1, txt_checkID.getText()); 
            pst.execute();
            updatetable();
        }catch (SQLException ex){
            Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void Calculatorbtn(ActionEvent event) {       
            try {
                openCalculatorWindow("Calculator.fxml", "Taxage Calculator");
            } catch (IOException ex) {
                Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
            }  
    }
    
    private void BillToMail() throws AddressException, MessagingException{
                String[] mailTo = new String[2];
                mailTo[0] = customerEmail.getText();
                mailTo[1] = "sendermail@gmail.com";
		String subject = "Invoice #" + invoicenumber.getText() + " from " + UserNameLabel.getText();
		String message = "Invoice for " + subtotalLabel.getText();

		// attachments
		String[] attachFiles = new String[1];
		attachFiles[0] = "d:/" + invoicenumber.getText() +"-"+ Currrentcustomer.getText() +".txt";
                
                try {
			AttactmentSender.sendEmailWithAttachments(mailTo, subject, message, attachFiles);
			String tilte = "Invoice";
                        String mgs = "Email sent......";
                        noti.success(tilte, mgs);
		} catch (MessagingException ex) {
                        String tilte = "Invoice";
                        String mgs = "Could not send email.";
                        noti.error(tilte, mgs);
		}
                
    }

    @FXML
    private void RecordDeletebtn(ActionEvent event) {
        DeleteRecord();
    }

    @FXML
    private void RecordPaidbtn(ActionEvent event) {
        edittable();
    }
    
    private void checkinvoicenumber(){
        if (list.isEmpty()){
            conn = InvoiceDatabase.ConnectDb();
            String sql = "SELECT invoicenumber.id, salesrecord.ID FROM invoicenumber, salesrecord WHERE invoicenumber.id <> salesrecord.ID";
            try{
                pst = conn.prepareStatement(sql);
                pst.execute();
                JOptionPane.showMessageDialog(null, "invoice number hasn't been saved yet");
            } catch (SQLException ex) {
            Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else{
            int response = JOptionPane.showConfirmDialog(null, "Current Sales have not be saved yet, Would you like to Save?", 
                    "Save Invoice", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            
            switch (response) {
                case JOptionPane.YES_OPTION:
                    PayConfirmation();
                    break;
                case JOptionPane.NO_OPTION:
                     newinvoice();
                    break;
                case JOptionPane.CLOSED_OPTION:
                    System.out.println("CLOSED.. Closed Selected..");
                    break;
                default:
                    break;
            }
        }
        
    }

    private void newinvoice(){
        conn = InvoiceDatabase.ConnectDb();
        String sql = "INSERT INTO invoicenumber () VALUES ()";
        try{
            pst = conn.prepareStatement(sql);
            pst.execute();
        }catch(SQLException e){
             JOptionPane.showMessageDialog(null, e);   
        }
        
        updateinvoicenumber();
    }
    
    @FXML
    private void NewInvoicebtn(ActionEvent event) {
        checkinvoicenumber();
        clearpageCreate();
    }

    @FXML
    private void Aboutbtn(ActionEvent event) {
        try 
        {
          openCalculatorWindow("AboutPage.fxml", "About Us");
        } catch (IOException ex) {
          Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void getSelectedRecord(MouseEvent event) {
        index = RecordTable.getSelectionModel().getSelectedIndex();
        if (index <= -1){
            return;
        }
        txt_checkID.setText(RecordNumberTable.getCellData(index).toString());
    }
    
    private void savefile(){
        File file = new File("d:\\" + invoicenumber.getText() +"-"+ Currrentcustomer.getText() +".txt.");
           
            SaveFile(textfilled.getText(), file);
    }

    @FXML
    private void goodprint(ActionEvent event) {
       PrinterJob print = new PrinterJob();
       print.print(textfilled);  
    }
    
    private void SaveFile(String content, File file){
        try{
            FileWriter fileWriter;
            
            fileWriter = new FileWriter(file);
            fileWriter.write(content);
            fileWriter.close();
        }catch (IOException ex){
            Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void Previewbtn(ActionEvent event){           
            printbill();    
    }

    @FXML
    private void Settingsbtn(ActionEvent event) {
        InvoicePage.setVisible(false);
        recordsPage.setVisible(false);
        PrintPage.setVisible(false);
        StorePage.setVisible(true);
         textfilled.setText("");
    }

    @FXML
    private void storeaddbtn(ActionEvent event) {
        int stockin = 0; int store = 0;
        Additem_ToStore(stockin, store);
    }

    @FXML
    private void storedeletebtn(ActionEvent event) {
        DeleteItem();
        
    }
    
    private void Additem_ToStore(Integer stockin, Integer store){  
        conn = InvoiceDatabase.ConnectDb();
        String sql = "INSERT INTO store_stock (id , item, description, 	Price, Stock_In, Stock_Out, Store)values(?,?,?,?,?,?,?)";
        try{
            pst = conn.prepareStatement(sql);
            pst.setString(1, storebarcode.getText());
            pst.setString(2, storeitem.getText());
            pst.setString(3, storedes.getText());
            pst.setString(4, storeprice.getText());
            pst.setString(5, storestockin.getText());
            pst.setInt(6, stockin);
            pst.setInt(7, store);
            pst.execute();
            updatestore();
        }catch (SQLException e){   
        }
    
    }
    
    private void updatestore(){
        Barcode.setCellValueFactory(new PropertyValueFactory<>("Barcode"));
        storeitemR.setCellValueFactory(new PropertyValueFactory<>("storeitem"));
        storedesR.setCellValueFactory(new PropertyValueFactory<>("storedes"));
        storepriceR.setCellValueFactory(new PropertyValueFactory<>("storeprice"));
        storestockinR.setCellValueFactory(new PropertyValueFactory<>("storestockin"));
        storestockoutR.setCellValueFactory(new PropertyValueFactory<>("storestockout"));
        StoreStore.setCellValueFactory(new PropertyValueFactory<>("StoreStore"));
        
        strd = InvoiceDatabase.getStoreList();
        StoreItems.setItems(strd);
        //search_customer();
    }
    
    private void DeleteItem(){
        conn = InvoiceDatabase.ConnectDb();
        String sql = "DELETE FROM store_stock where id = ?";
        try{
            pst = conn.prepareStatement(sql);
            pst.setString(1, txt_checkID.getText()); 
            pst.execute();
            updatestore();
        }catch (SQLException ex){
            Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    private void getSelectedFromStore(MouseEvent event) {
        storeindex = StoreItems.getSelectionModel().getSelectedIndex();
        if (storeindex <= -1){
            return;
        }
        txt_checkID.setText(Barcode.getCellData(storeindex));
    }

    @FXML
    private void codekeyPressed(KeyEvent event) {
        if(event.getCode() == KeyCode.ENTER){
            String code = productcode.getText();
            
            conn = InvoiceDatabase.ConnectDb();
            String sql = "Select * FROM store_stock where id = ?";
            
            try{
                pst = conn.prepareStatement(sql);
                pst.setString(1, code); 
                rs = pst.executeQuery();
                
                if (rs.next() == false){
                    JOptionPane.showMessageDialog(null, "Product Code Not Found");
                }else{
                    String pname = rs.getString("item");
                    String pdes = rs.getString("description");
                    String pprice = rs.getString("Price");
                    
                    itemBox.setText(pname.trim());
                    desBox.setText(pdes.trim());
                    priceBox.setText(pprice.trim());
                            
                }
            }catch (SQLException ex){
                Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    /*
    private void solditem(){
        String codex = productcode.getText();
            
            conn = InvoiceDatabase.ConnectDb();
            String sql = "Select * FROM store_stock where id = ?";
            
            try{
                pst = conn.prepareStatement(sql);
                pst.setString(1, codex); 
                rs = pst.executeQuery();
                
                if (rs.next() == true){
                    int count
                }else{
                    String pname = rs.getString("item");
                    String pdes = rs.getString("description");
                    String pprice = rs.getString("Price");
                    
                    itemBox.setText(pname.trim());
                    desBox.setText(pdes.trim());
                    priceBox.setText(pprice.trim());
                            
                }
            }catch (SQLException ex){
                Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    */
}
