/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package billinginvoice;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

public class PasswordDiaglog extends Dialog<String> {
  private PasswordField passwordField;

  public PasswordDiaglog() {
    setTitle("Password");
    setHeaderText("Please enter adminstrator password.");

    ButtonType passwordButtonType = new ButtonType("Access", ButtonData.OK_DONE);
    getDialogPane().getButtonTypes().addAll(passwordButtonType, ButtonType.CANCEL);

    passwordField = new PasswordField();
    passwordField.setPromptText("Password");

    HBox hBox = new HBox();
    hBox.getChildren().add(passwordField);
    hBox.setPadding(new Insets(20));

    HBox.setHgrow(passwordField, Priority.ALWAYS);

    getDialogPane().setContent(hBox);

    Platform.runLater(() -> passwordField.requestFocus());

    setResultConverter(dialogButton -> {
      if (dialogButton == passwordButtonType) {
        return passwordField.getText();
      }
      return null;
    });
  }

  public PasswordField getPasswordField() {
    return passwordField;
  }
}
