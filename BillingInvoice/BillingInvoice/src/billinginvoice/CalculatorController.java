/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package billinginvoice;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author Azu-Benson Caleb's Code
 */
public class CalculatorController implements Initializable {

    @FXML
    private TextField calculatorview;    
    @FXML
    private Label operateLabel;
    
    double num, ans;
    int calculation;
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }
    
    private void solve_operation(){
        
        
        switch(calculation){
            case 1: ans = num + Double.parseDouble(calculatorview.getText());
                    calculatorview.setText(Double.toString(ans));
                    break;
            case 2: ans = num - Double.parseDouble(calculatorview.getText());
                    calculatorview.setText(Double.toString(ans));
                    break;
            case 3: ans = num * Double.parseDouble(calculatorview.getText());
                    calculatorview.setText(Double.toString(ans));
                    break;       
            case 4: ans = num / 100 * Double.parseDouble(calculatorview.getText());
                    calculatorview.setText(Double.toString(ans));
                    break;
            case 5: ans = num / Double.parseDouble(calculatorview.getText());
                    calculatorview.setText(Double.toString(ans));
                    break;
        }
    }

    @FXML
    private void percentbuttonClicked(MouseEvent event) {
        String cal = calculatorview.getText();
        if (cal.trim().isEmpty()){
            calculatorview.setText("");
        }else{
         num = Double.parseDouble(calculatorview.getText());
         calculation = 4;
         calculatorview.setText("");
         operateLabel.setText(num + "%");
        }
    }

    @FXML
    private void clearbuttonClicked(MouseEvent event) {
        calculatorview.setText("");
         operateLabel.setText("");
    }

    @FXML
    private void backspacebuttonClicked(MouseEvent event) {
        int length = calculatorview.getText().length();
        int number = calculatorview.getText().length() - 1;
        
        String store;
        
        if (length > 0){
            StringBuilder back = new StringBuilder(calculatorview.getText());
            back.deleteCharAt(number);
            store=back.toString();
            calculatorview.setText(store);
        }
    }

    @FXML
    private void dividebuttonClicked(MouseEvent event) {
        String cal = calculatorview.getText();
        if (cal.trim().isEmpty()){
            calculatorview.setText("");
        }else{
         num = Double.parseDouble(calculatorview.getText());
         calculation = 5;
         calculatorview.setText("");
         operateLabel.setText(num + "/");
        }
    }

    @FXML
    private void num7buttonClicked(MouseEvent event) {
        calculatorview.setText(calculatorview.getText() + "7");
    }

    @FXML
    private void num8buttonClicked(MouseEvent event) {
        calculatorview.setText(calculatorview.getText() + "8");
    }

    @FXML
    private void num9buttonClicked(MouseEvent event) {
        calculatorview.setText(calculatorview.getText() + "9");
    }
    

    @FXML
    private void mutiplebuttonClicked(MouseEvent event) {
        String cal = calculatorview.getText();
        if (cal.trim().isEmpty()){
            calculatorview.setText(""); 
        }else{
         num = Double.parseDouble(calculatorview.getText());
         calculation = 3;
         calculatorview.setText("");
         operateLabel.setText(num + "*");
        }
    }

    @FXML
    private void num4buttonClicked(MouseEvent event) {
        calculatorview.setText(calculatorview.getText() + "4");
    }

    @FXML
    private void num5buttonClicked(MouseEvent event) {
        calculatorview.setText(calculatorview.getText() + "5");
    }

    @FXML
    private void num6buttonClicked(MouseEvent event) {
        calculatorview.setText(calculatorview.getText() + "6");
    }

    @FXML
    private void substractbuttonClicked(MouseEvent event) {
        String cal = calculatorview.getText();
        if (cal.trim().isEmpty()){
            calculatorview.setText("-");
        }else{
            num = Double.parseDouble(calculatorview.getText());
            calculation = 2;
            calculatorview.setText("");
            operateLabel.setText(num + "-");
        }
    }

    @FXML
    private void num1buttonClicked(MouseEvent event) {
        calculatorview.setText(calculatorview.getText() + "1");
    }

    @FXML
    private void num2buttonClicked(MouseEvent event) {
        calculatorview.setText(calculatorview.getText() + "2"); 
    }

    @FXML
    private void num3buttonClicked(MouseEvent event) {
        calculatorview.setText(calculatorview.getText() + "3");
    }

    @FXML
    private void addbuttonClicked(MouseEvent event) {
        String cal = calculatorview.getText();
        if (cal.trim().isEmpty()){
            calculatorview.setText("");
        }else{
         num = Double.parseDouble(calculatorview.getText());
         calculation = 1;
         calculatorview.setText("");
         operateLabel.setText(num + "+");
        }
    }

    @FXML
    private void num0buttonClicked(MouseEvent event) {
        calculatorview.setText(calculatorview.getText() + "0");
    }

    @FXML
    private void dotbuttonClicked(MouseEvent event) {
        calculatorview.setText(calculatorview.getText() + ".");
    }

    @FXML
    private void equalbuttonClicked(MouseEvent event) {
        solve_operation();
        operateLabel.setText("");
    }
    
}
