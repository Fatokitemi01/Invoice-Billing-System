/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package billinginvoice;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.animation.TranslateTransition;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.swing.JOptionPane;

/**
 *
 * @author Azu-Benson Caleb
 */
public class SignInPageController implements Initializable {
    
     Connection conn = null;
     ResultSet rs = null;
     PreparedStatement pst = null;
     private SimpleBooleanProperty showPassword;
     
     SendMail sendmail = new SendMail();
     Notification noti = new Notification();
    
    private Label label;
    @FXML
    private AnchorPane MainLayer;
    @FXML
    private AnchorPane Layer1;
    @FXML
    private HBox signinEmail;
    @FXML
    private HBox signinPassword;
    @FXML
    private JFXButton signInbtn;
    @FXML
    private JFXButton ForgetPasswordbtn;
    @FXML
    private Label signinLabel;
     //Azu-Benson Caleb's Code
    @FXML
    private HBox signUpName;
    @FXML
    private HBox SignUpEmail;
    @FXML
    private HBox SignUpPass;
    @FXML
    private Label CreateAccountLabel;
    @FXML
    private JFXButton getStartedBtn;
    @FXML
    private AnchorPane Layer2;
    @FXML
    private JFXButton signInbtn1;
    @FXML
    private Label welcomeLabel;
    @FXML
    private Label FinancialLabel;
    @FXML
    private Label ELabel;
    @FXML
    private Label enterdetailsLabel;
    @FXML
    private JFXButton signUPbtn1;
    @FXML
    private Label CLickindetailsLabel;
    @FXML
    private Label alinfoLabel;
    @FXML
    private HBox SignUpBusiness;
    @FXML
    private HBox ResetEmailAddress;
    @FXML
    private JFXButton ResetEmailSendbtn;
    @FXML
    private JFXButton ResetVaryCodebtn;
    @FXML
    private HBox NewPassWord;
    @FXML
    private HBox NewPasswordVerify;
    @FXML
    private TextField emailIN;
    @FXML
    private PasswordField passwordIN;
    @FXML
    private TextField UsernameUP;
    @FXML
    private TextField EmailUP;
    @FXML
    private PasswordField passwordUP;
    @FXML
    private TextField BusinessUP;
    @FXML
    private TextField txt_ResetEmail;
    @FXML
    private PasswordField txt_NewPassWord;
    @FXML
    private PasswordField txt_NewPasswordVerify;
    @FXML
    private Label ErrsignupUser;
    @FXML
    private Label ErrsignupPassword;
    @FXML
    private Label ErrsignupEmail;
    @FXML
    private Label ErrsignupBsn;
    @FXML
    private Label RecoverLabel;
    @FXML
    private HBox verificationcodeBOX;
    
    int randomCode;
    
    @FXML
    private JFXButton RestPasswordBtn;
    @FXML
    private TextField txt_Ver;
    
     public String useremail;
    @FXML
    private HBox spw;
    @FXML
    private HBox hpw;
    private Tooltip tooltips;
    @FXML
    private TextField UsernameUP1;
    @FXML
    private HBox SignUpPass1;
    @FXML
    private HBox SignInPass1;
    @FXML
    private TextField UsernameIn1;
    @FXML
    private HBox spw1;
    @FXML
    private HBox hpw1;
    
    
    @FXML
    private Group seesign;
    @FXML
    private Group seeforget;
    @FXML
    private HBox spw11;
    @FXML
    private HBox hpw11;
    @FXML
    private HBox resetPVerity;
    @FXML
    private TextField resetPV;
    @FXML
    private HBox resetpass;
    @FXML
    private TextField resetP;
    @FXML
    private ChoiceBox choicebox;
    
    String ds = "Empty";
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        signUpName.setVisible(false);
        getStartedBtn.setVisible(false);
        CreateAccountLabel.setVisible(false);
        SignUpPass.setVisible(false);
        SignUpEmail.setVisible(false);
        CLickindetailsLabel.setVisible(false);
        alinfoLabel.setVisible(false);    
        signInbtn1.setVisible(false);
        SignUpBusiness.setVisible(false);
        spw.setVisible(false);
        hpw.setVisible(false);
        SignUpPass1.setVisible(false);
        resetpass.setVisible(false);
        resetPVerity.setVisible(false);
        seeforget.setVisible(false);
        choicebox.setVisible(false);
        
        passwordIN.textProperty().addListener((observable, oldValue, newValue) -> {
            signInbtn.setDisable(false);
        });
        
        passwordIN.setOnKeyPressed((KeyEvent event) -> {
            if (event.getCode() == KeyCode.ENTER){
                try {
                    Login(event);
                } catch (IOException ex) {
                    Logger.getLogger(SignInPageController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        
        choicebox.getItems().addAll("Admin", "Cashier");
        choicebox.setValue("Cashier");
        
        passwordUP.textProperty().bindBidirectional(UsernameUP1.textProperty());
        
        passwordIN.textProperty().bindBidirectional(UsernameIn1.textProperty());
        
        resetP.textProperty().bindBidirectional(txt_NewPassWord.textProperty());
        
        resetPV.textProperty().bindBidirectional(txt_NewPasswordVerify.textProperty());
        
        
    }
    
    private boolean valiEmail(){
        boolean valid = true;
        
        String emailRegex = emailIN.getText();
        Pattern emailPat = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$",Pattern.CASE_INSENSITIVE);
        Matcher matcher = emailPat.matcher(emailRegex);
        
        if (emailRegex == null || emailRegex.trim().isEmpty()) {
            ErrsignupUser.setText("Email field is empty");
            valid = false;
        }else if (matcher.find() && matcher.group().equals(emailRegex)){ 
            ErrsignupUser.setText("");
        }else{     
            ErrsignupUser.setText("do not meet");
            valid = false;
        }     
        
        return valid;
    }
    
    private boolean SignUpValidation(){
        boolean valid = true;
        
        String username = UsernameUP.getText();
        Pattern u = Pattern.compile("[a-zA-Z- ]+");
        Matcher n = u.matcher(username);       
        if (username== null || username.trim().isEmpty()) {
            ErrsignupUser.setText("username field is empty !");
            valid = false;
        }else if (n.find() && n.group().equals(username)){
            ErrsignupUser.setText("");
        }
        
        String emailRe = EmailUP.getText();
        Pattern ePat = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$",Pattern.CASE_INSENSITIVE);
        Matcher mcher = ePat.matcher(emailRe);
        if (emailRe == null || emailRe.trim().isEmpty()) {
            ErrsignupEmail.setText("email field is empty !");
            valid = false;
        }else if (mcher.find() && mcher.group().equals(emailRe)){ 
            ErrsignupEmail.setText("");
        }else{     
            ErrsignupEmail.setText("eample@mail.com");
            valid = false;
        }
        
        //Password validation
        String password = passwordUP.getText();
        if (password.length() > 7){
            if (checkPassword(password)){
                ErrsignupPassword.setText("");
                valid = true;
            }else{
                ErrsignupPassword.setText("must contain Caps,lowerCase and number");
                valid = false;
            }
        }else{
            ErrsignupPassword.setText("password must be 8 or more");
            valid = false;
        }

        String businessname = BusinessUP.getText();
        Pattern i = Pattern.compile("[a-zA-Z- ]+");
        Matcher t = i.matcher(businessname);       
        if (businessname == null || businessname.trim().isEmpty()) {
            ErrsignupBsn.setText("field is empty !");
            valid = false;
        }else if (t.find() && t.group().equals(businessname)){
            ErrsignupBsn.setText("");
        }
        
        
        
        return valid;
    }
    
    private boolean restverification(){
        boolean valid = true;
        
         String repass = txt_NewPassWord.getText();
        if (repass.length() > 7){
            if (checkPassword(repass)){
                ErrsignupUser.setText("");
                valid = true;
            }else{
                ErrsignupUser.setText("must contain Caps,lowerCase and number");
                valid = false;
            }
        }else{
            ErrsignupUser.setText("password must be 8 or more");
            valid = false;
        }
        
        return valid;
    }
    
    private boolean checkPassword(String Password){
        boolean hasNum = false, hasCap = false, hasLow = false; 
        char c;
        
        for (int i = 0; i < Password.length(); i++){
            c = Password.charAt(i);
            
            if (Character.isDigit(c)){
                hasNum = true;
            }else if (Character.isUpperCase(c)){
                hasCap = true;
            }else if (Character.isLowerCase(c)){
                hasLow = true;
            }if (hasNum && hasCap && hasLow){
                return true;
            }
        }
        
        return false;
    }
    
    private void slide_into_signinForm(){
        ds = "Empty";
        TranslateTransition slide = new TranslateTransition();
        slide.setDuration(Duration.seconds(0.7));
        slide.setNode(Layer2);
        
        slide.setToX(0);
        slide.play();
        
        Layer1.setTranslateX(0);
        signInbtn1.setVisible(false);
        alinfoLabel.setVisible(false);
        CLickindetailsLabel.setVisible(false);
        spw.setVisible(false);
        hpw.setVisible(false);
        
        spw1.setVisible(true);
       // hpw1.setVisible(true);
        
       
        signUpName.setVisible(false);
        getStartedBtn.setVisible(false);
        CreateAccountLabel.setVisible(false);
        SignUpPass.setVisible(false);
        signinEmail.setVisible(true);
        signinPassword.setVisible(true);
        signInbtn.setVisible(true);
        ForgetPasswordbtn.setVisible(true);
        signinLabel.setVisible(true);
        signUPbtn1.setVisible(true);
        enterdetailsLabel.setVisible(true);
        SignUpEmail.setVisible(false);
        CLickindetailsLabel.setVisible(false);
        alinfoLabel.setVisible(false);
        signInbtn1.setVisible(false);
        SignUpBusiness.setVisible(false);
        ErrsignupEmail.setText("");
        ErrsignupPassword.setText("");
        ErrsignupBsn.setText("");
        ErrsignupUser.setText("");
        UsernameUP.clear();
        passwordUP.clear();
        SignUpPass1.setVisible(false);
        choicebox.setVisible(false);
         //Azu-Benson Caleb's Code
         
        slide.setOnFinished((e->{
        
        
        }));
    }
    
    private boolean adminPermission(){
        boolean valid  = true;
        
        PasswordDiaglog pd = new PasswordDiaglog();
        Optional<String> result = pd.showAndWait();
        result.ifPresent((String password) -> {
            ds = password;
        }); 
        String cc = "admin";
        conn = InvoiceDatabase.ConnectDb();
        String sql = "Select password from users where ID = 1 ";
        try{
        pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
         while(rs.next()){ 
                if(rs.getString(1).equals(ds) || ds.equals(cc)){
                   valid = true; 
                }else{
                    String tilte = "Access";
                    String message = "Access Denied";
                    noti.error(tilte, message);
                    valid = false;
                }
            }   
        }   catch (SQLException ex) {
            Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return valid;
    }
    
    private void slide_into_signUpForm(){
        if (!adminPermission()){
           return;
        } 
        
        TranslateTransition slide = new TranslateTransition();
        slide.setDuration(Duration.seconds(0.7));
        slide.setNode(Layer2);
        
        slide.setToX(491);
        slide.play();
        
        Layer1.setTranslateX(-309);
        signInbtn1.setVisible(true);
        alinfoLabel.setVisible(true);
        CLickindetailsLabel.setVisible(true);
        spw.setVisible(true);
        
        spw1.setVisible(false);
        hpw1.setVisible(false);
       
        signUpName.setVisible(true);
        getStartedBtn.setVisible(true);
        CreateAccountLabel.setVisible(true);
        SignUpPass.setVisible(true);
        signinEmail.setVisible(false);
        signinPassword.setVisible(false);
        signInbtn.setVisible(false);
        ForgetPasswordbtn.setVisible(false);
        signinLabel.setVisible(false);
        signUPbtn1.setVisible(false);
        enterdetailsLabel.setVisible(false);
        SignUpEmail.setVisible(true);
        CLickindetailsLabel.setVisible(true);
        alinfoLabel.setVisible(true);
        signInbtn1.setVisible(true);
        SignUpBusiness.setVisible(true);
        ResetEmailAddress.setVisible(false);
        ResetEmailSendbtn.setVisible(false);
        ResetVaryCodebtn.setVisible(false);
        verificationcodeBOX.setVisible(false);
         NewPassWord.setVisible(false);
        NewPasswordVerify.setVisible(false);
        RestPasswordBtn.setVisible(false);
        ErrsignupUser.setText("");
        UsernameIn1.clear();
        passwordIN.clear();
        SignUpPass1.setVisible(true);
        choicebox.setVisible(true);
        //Azu-Benson Caleb's Code
        
        slide.setOnFinished((e->{
        
        
        }));
    }

    @FXML
    private void SlideInbtn(ActionEvent event) {
        slide_into_signinForm();
    }

    @FXML
    private void SlideUPbtn(ActionEvent event) {
        slide_into_signUpForm();
    }
    
    private void Login(KeyEvent ev) throws IOException{
        if (!valiEmail()){
           return;
        } 
  
        conn = InvoiceDatabase.ConnectDb();
        String sql = "Select * from users where email = ? and password = ?  ";
        try{
        pst = conn.prepareStatement(sql);
        pst.setString(1, emailIN.getText());
        pst.setString(2, passwordIN.getText());
        
        rs = pst.executeQuery();
         if(rs.next()){
            String tilte = "Sign In";
            String message = "Username and Password Correct";
            noti.success(tilte, message);
            
            String mail = emailIN.getText();
            String type = rs.getString(6);

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("Dashboard.fxml"));
            Parent getsdParent = loader.load();

            Scene getsdScene = new Scene(getsdParent);

            DashboardController Passing = loader.getController();
            Passing.GetUsersBusinessName(mail,type);

            Stage window = (Stage)((Node)ev.getSource()).getScene().getWindow();

            window.setScene(getsdScene);
            window.show();
        
           }else{
      
            String tilte = "Sign In";
            String message = "Error, Username and Password Wrong";
            noti.error(tilte, message);
            
        }
    }catch(SQLException e){
        JOptionPane.showMessageDialog(null, e);
    }   
    }

     @FXML
    private void AccessAccount(ActionEvent event) throws IOException{
        if (!valiEmail()){
           return;
        } 
  
        conn = InvoiceDatabase.ConnectDb();
        String sql = "Select * from users where email = ? and password = ?  ";
        try{
        pst = conn.prepareStatement(sql);
        pst.setString(1, emailIN.getText());
        pst.setString(2, passwordIN.getText());
        
        rs = pst.executeQuery();
         if(rs.next()){
            String tilte = "Sign In";
            String message = "Username and Password Correct";
            noti.success(tilte, message);
            
            String mail = emailIN.getText();
            String type = rs.getString(6);

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("Dashboard.fxml"));
            Parent getsdParent = loader.load();

            Scene getsdScene = new Scene(getsdParent);

            DashboardController Passing = loader.getController();
            Passing.GetUsersBusinessName(mail,type);

            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

            window.setScene(getsdScene);
            window.show();
        
           }else{
      
            String tilte = "Sign In";
            String message = "Error, Username and Password Wrong";
            noti.error(tilte, message);
            
        }
    }catch(SQLException e){
        JOptionPane.showMessageDialog(null, e);
    } 
    }

    @FXML
    private void FOPbtnPressed(ActionEvent event) {
        signinEmail.setVisible(false);
        signinPassword.setVisible(false);
        signInbtn.setVisible(false);
        signinLabel.setVisible(false);
        RecoverLabel.setVisible(true);
        ForgetPasswordbtn.setVisible(false);
        
        ResetEmailAddress.setVisible(true);
        ResetEmailSendbtn.setVisible(true);
        ResetVaryCodebtn.setVisible(true);
        verificationcodeBOX.setVisible(true);
        
        seesign.setVisible(false);
    }

    @FXML
    private void FOPbtnmousehover(MouseEvent event) {
        ForgetPasswordbtn.setCursor(javafx.scene.Cursor.HAND);
    }

    @FXML
    private void ResetlSendbtn(ActionEvent event) {
        conn = InvoiceDatabase.ConnectDb();
        String sql = "Select * from users where email = ? ";
        try{
        pst = conn.prepareStatement(sql);
        pst.setString(1, txt_ResetEmail.getText());
 
        rs = pst.executeQuery();
         if(rs.next()){      
            Random rand = new Random();
            randomCode = rand.nextInt(999999);
         
            sendmail.mail(txt_ResetEmail.getText(), "Resetting Code", "Your reset code is " + randomCode);
            
         }else{
             JOptionPane.showMessageDialog(null, "Your search did not return any results. Please try again with other information.", "No Search Results", 0);
         }
    }catch(SQLException e){
        JOptionPane.showMessageDialog(null, e);
    }   
      
    }

    @FXML
    private void VaryCodebtn(ActionEvent event) {
        if (Integer.valueOf(txt_Ver.getText())== randomCode){
            useremail = txt_ResetEmail.getText();
            
            NewPassWord.setVisible(true);
            NewPasswordVerify.setVisible(true);
            RestPasswordBtn.setVisible(true);

            ResetEmailAddress.setVisible(false);
            ResetEmailSendbtn.setVisible(false);
            ResetVaryCodebtn.setVisible(false);
            verificationcodeBOX.setVisible(false);
        }else{
            JOptionPane.showMessageDialog(null, "Code do not Match");     
        }
        //seeforget.setVisible(true);
        seesign.setVisible(false);
    }

    @FXML
    private void getStartedBtnPressed(ActionEvent event) {
        if (!SignUpValidation()){
           return;
        }
        
        conn = InvoiceDatabase.ConnectDb();
        String sql = "INSERT INTO users (username,email,password,business_name,type) VALUES (?,?,?,?,?)";
        try{
            pst = conn.prepareStatement(sql);
            pst.setString(1,UsernameUP.getText());
            pst.setString(2,EmailUP.getText());
            pst.setString(3,passwordUP.getText());
            pst.setString(4,BusinessUP.getText());
            pst.setString(5,choicebox.getValue().toString());
            pst.execute();
            JOptionPane.showMessageDialog(null, "Saved, You have succesfully Registered to Taxage ");
        }catch(SQLException e){
             JOptionPane.showMessageDialog(null, e);   
        }
        
        UsernameUP.setText("");
        EmailUP.setText("");       
        passwordUP.setText("");   
        BusinessUP.setText("");
                
        slide_into_signinForm();
    }

    @FXML
    private void RestPWBtnPush(ActionEvent event) {
        if (!restverification()){
           return;
        }
        
        if (txt_NewPassWord.getText().equals(txt_NewPasswordVerify.getText())){
            try{
                conn = InvoiceDatabase.ConnectDb();
                String updateQuery = "UPDATE users set password = ? WHERE email = '"+useremail+"' ";
                pst = conn.prepareStatement(updateQuery);
                pst.setString(1, txt_NewPasswordVerify.getText());
                pst.executeUpdate();
                JOptionPane.showMessageDialog(null, "Password has been reset Successfully");
            }catch(SQLException e){
                JOptionPane.showMessageDialog(null, e);   
            }
            
            signinEmail.setVisible(true);
            signinPassword.setVisible(true);
            signInbtn.setVisible(true);
            signinLabel.setVisible(true);
            RecoverLabel.setVisible(false);
            ForgetPasswordbtn.setVisible(true);

            NewPassWord.setVisible(false);
            NewPasswordVerify.setVisible(false);
            RestPasswordBtn.setVisible(false);
            seesign.setVisible(true);
            seeforget.setVisible(false);
            
          }else{
            JOptionPane.showMessageDialog(null, "Password do not Match");     
          }
   
    }
    
  
    @FXML
    private void spwpressed(MouseEvent event) {
        spw.setVisible(false);
        hpw.setVisible(true);
         
        SignUpPass1.toFront();
        SignUpPass.toBack();
        
    }

    @FXML
    private void hpwpressed(MouseEvent event) {
        spw.setVisible(true);
        hpw.setVisible(false);
        
        SignUpPass.toFront();
        SignUpPass1.toBack();
    }

    @FXML
    private void inspwpressed(MouseEvent event) {
        spw1.setVisible(false);
        hpw1.setVisible(true);
        
        SignInPass1.toFront();
        SignUpEmail.toBack();
    }

    @FXML
    private void inhpwpressed(MouseEvent event) {
        spw1.setVisible(true);
        hpw1.setVisible(false);
        
        SignUpEmail.toFront();
        SignInPass1.toBack();
    }

    @FXML
    private void inspwpressed111(MouseEvent event) {
        spw11.setVisible(false);
        hpw11.setVisible(true);
        
        resetpass.toFront();
        NewPassWord.toBack();
        
        resetPVerity.toFront();
        NewPasswordVerify.toBack();
    }

    @FXML
    private void inhpwpressed111(MouseEvent event) {
        spw11.setVisible(true);
        hpw11.setVisible(false);
        
        NewPassWord.toFront();
        resetpass.toBack();
        
        NewPasswordVerify.toFront();
        resetPVerity.toBack();
    }
}
