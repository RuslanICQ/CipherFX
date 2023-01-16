package ru.ucoz.karte.cipherfx;

import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;

import java.io.*;
import java.net.URL;
import java.util.Date;

import java.awt.datatransfer.StringSelection;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.util.ResourceBundle;

public class CipherFXController implements Initializable {
    public Pane mainPane;
    public Pane smallPane;
    public Button openInputTextFileButton;
    public Button openKeyTextFileButton;
    public TextArea inputTextArea;
    public TextArea keyTextArea;
    public TextArea outputTextArea;
    public CheckBox inputTextCheckBox;
    public CheckBox keyCheckBox;
    public CheckBox outputTextCheckBox;
    public RadioButton mode1RadioButton;
    public RadioButton mode2RadioButton;
    public RadioButton mode3RadioButton;
    public static CipherFXController controller;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        CipherFXController.controller = this;
    }

    public void cipherButtonClick() {
        if (mode1RadioButton.isSelected()) outputTextArea.setText(Crypto.CipherMode1(inputTextArea.getText(), keyTextArea.getText()));
        if (mode2RadioButton.isSelected()) outputTextArea.setText(Crypto.CipherMode2(inputTextArea.getText(), keyTextArea.getText()));
        if (mode3RadioButton.isSelected()) outputTextArea.setText(Crypto.CipherMode3(inputTextArea.getText(), keyTextArea.getText()));
    }

    public void deCipherButtonClick() {
        if (mode1RadioButton.isSelected()) outputTextArea.setText(Crypto.DecipherMode1(inputTextArea.getText(), keyTextArea.getText()));
        if (mode2RadioButton.isSelected()) outputTextArea.setText(Crypto.DecipherMode2(inputTextArea.getText(), keyTextArea.getText()));
        if (mode3RadioButton.isSelected()) outputTextArea.setText(Crypto.DecipherMode3(inputTextArea.getText(), keyTextArea.getText()));
    }

    public void clearButtonClick() {
        inputTextArea.setText("");
        outputTextArea.setText("");
    }

    public void clearAllButtonClick() {
        inputTextArea.setText("");
        keyTextArea.setText("");
        outputTextArea.setText("");
    }

    public void exitButtonClick() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
                "Exit program ?",
                ButtonType.YES,
                ButtonType.CANCEL);
        alert.showAndWait();
        if (alert.getResult() == ButtonType.YES) {
            System.exit(0);
        }
    }

    public void copyButtonClick() {
        String myString = inputTextArea.getText()+"\n\n"
                            + keyTextArea.getText()+"\n\n"
                            + outputTextArea.getText();
        StringSelection stringSelection = new StringSelection(myString);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(stringSelection, null);

        Alert alert = new Alert(Alert.AlertType.INFORMATION,
                "All fields have been copied.",
                ButtonType.OK);
        alert.showAndWait();
    }


    public void saveButtonClick() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
                "Save file(s) ?",
                ButtonType.YES,
                ButtonType.CANCEL);
        alert.showAndWait();
        if (alert.getResult() == ButtonType.YES) {
            Date dateTime = new Date();
            String s = dateTime.toString();
            if (inputTextCheckBox.isSelected()) Save(s, "Input_text");
            if (keyCheckBox.isSelected()) Save(s, "Key");
            if (outputTextCheckBox.isSelected()) Save(s, "Output_text");
        }
    }

    public void openInputTextFileButtonClick(){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Input File");
        fileChooser.setInitialDirectory(new File(System.getProperty("user.dir")));
        File file = fileChooser.showOpenDialog(null);
        if (file != null) {
            inputTextArea.clear();
            inputTextArea.appendText(Read(file.getName()));
        }
    }

    public void openKeyTextFileButtonClick(){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Key File");
        fileChooser.setInitialDirectory(new File(System.getProperty("user.dir")));
        File file = fileChooser.showOpenDialog(null);
        if (file != null) {
            keyTextArea.clear();
            keyTextArea.appendText(Read(file.getName()));
        }
    }

    void Save(String file_name, String type) {
        String formatted_file_name=file_name.replace(':', '.');
        try{
            if (type.equals("Input_text")) {
                FileWriter writer = new FileWriter("INPUT_TEXT_"+formatted_file_name+".txt", true);
                writer.append(inputTextArea.getText());
                writer.close();
            }
            if (type.equals("Key")) {
                FileWriter writer = new FileWriter("KEY_"+formatted_file_name+".txt", true);
                writer.append(keyTextArea.getText());
                writer.close();
            }
            if (type.equals("Output_text")) {
                FileWriter writer = new FileWriter("OUTPUT_TEXT_"+formatted_file_name+".txt", true);
                writer.append(outputTextArea.getText());
                writer.close();
            }
        } catch(IOException ex) {
            ex.printStackTrace();
        }
    }

    String Read(String file_name){
        String res="";
        try {
            FileReader reader = new FileReader(file_name);
            BufferedReader bufReader = new BufferedReader(reader);
            String line = bufReader.readLine();
            while (line != null) {
                res += line;
                line = bufReader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return res;
    }

}