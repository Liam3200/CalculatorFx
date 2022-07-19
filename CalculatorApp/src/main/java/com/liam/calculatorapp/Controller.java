package com.liam.calculatorapp;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Font;

import java.text.DecimalFormat;
import java.text.ParsePosition;
import java.util.Objects;

public class Controller {
    //User Input Field
    @FXML
    private TextField userInput;

    //Private fields
    private final static double SCI_THRESHOLD = 1000000000; //(1 BILLION)

    private int operation = 0;

    private double firstNumber = 0;
    private double secondNumber = 0;

    private Calculation calculate = Calculation.getInstance();

    //When the percentage button is pressed:
    //convert the number to a percent in decimal form
    @FXML
    public void percentOf() {
        double percentage = (Double.parseDouble(userInput.getText()))/100;
        userInput.setText(String.valueOf(percentage));
    }

    //When the negative/positive button is pressed:
    //makes the number in the user input field either positive or negative
    @FXML
    public void makeNegative() {
        if (userInput.getText().equals("0")) {
            return;
        }
        if (userInput.getText().contains("-")) {
            userInput.setText(userInput.getText().replaceAll("-",""));
        } else {
            userInput.setText("-"+userInput.getText());
        }
    }

    //Clears the user field and removes the operation
    @FXML
    public void allClearField() {
        userInput.setText("0");
        resizeFont();
        operation = 0;
    }

    public void clearNumber() {
        userInput.setText("0");
        resizeFont();
    }

    //Resize the number
    //Check the size of the number and change the font size if the length is over a certain length
    public void resizeFont() {
        if (userInput.getText().length() < 5 ){
            userInput.setFont(Font.font(59));
        } else if (userInput.getText().length() < 13){
            userInput.setFont(Font.font(30));
        } else if (userInput.getText().length() < 20){
            userInput.setFont(Font.font(20));
        } else if (userInput.getText().length() < 30){
            userInput.setFont(Font.font(10));
        } else if (userInput.getText().length() < 80){
            userInput.setFont(Font.font(5));
        } else {
            clearNumber();
        }
    }

    //Checks the number and solution and if it is above a specific number, this will convert
    // the number into scientific notation
    public void sciNotate() {
        if (Double.parseDouble(userInput.getText()) > SCI_THRESHOLD) {
            double num = Double.parseDouble(userInput.getText());
            int exp =  userInput.getText().length()-1;
            num /= (Math.pow(10,exp));
            userInput.setText(num+"E"+exp);
        }
    }

//    //verify if userInput text field is in scientific notation
//    // and if it is then change back to a double for calculations
//    public void verifySciNote() {
//        if (!userInput.getText().contains("E")) {
//            return;
//        }
//        String[] s1 = userInput.getText().split("E");
//
//    }

    //adds a decimal after the number in the userInput field when the decimal button is pressed
    @FXML
    public void addDecimal() {
        userInput.setText(userInput.getText()+".");
    }

    //Operations:
    @FXML
    public void subtract() {
        firstNumber = Double.parseDouble(userInput.getText());
        clearNumber();
        operation = 1;

    }
    @FXML
    public void add() {
        firstNumber = Double.parseDouble(userInput.getText());
        clearNumber();
        operation = 2;
    }
    @FXML
    public void multiply() {
        firstNumber = Double.parseDouble(userInput.getText());
        clearNumber();
        operation = 3;
    }
    @FXML
    public void divide() {
        firstNumber = Double.parseDouble(userInput.getText());
        clearNumber();
        operation = 4;
    }
    //Equals: grabs the second number then it takes the two numbers and the private operator value
    // and gets a double from the calculation class, which it then changes to a string and
    // displays the outcome to the userInput field
    @FXML
    public void equals() {
        if (userInput.getText().isEmpty()) {
            secondNumber = 0;
        } else {
            secondNumber = Double.parseDouble(userInput.getText());
        }
        double result = calculate.calculate(operation,firstNumber,secondNumber);
        if (Double.toString(result).contains("E")) {
            DecimalFormat df = new DecimalFormat();
            Number number = df.parse(Double.toString(result), new ParsePosition(0));
            result = number.intValue();
        }
        userInput.setText(Double.toString(result).replaceAll(".0$",""));
        sciNotate();
        resizeFont();
    }

    //Adds the number button pressed to the end of the user input field respectively (0-9)
    private void buttonPressed(String s) {
        if (userInput.getText().equals("0")) {
            userInput.setText(s);
            return;
        }
        userInput.setText(userInput.getText()+s);
        resizeFont();
    }

    @FXML
    public void zeroPressed() {
        buttonPressed("0");
    }
    @FXML
    public void onePressed() {
        buttonPressed("1");
    }
    @FXML
    public void twoPressed() {
        buttonPressed("2");
    }
    @FXML
    public void threePressed() {
        buttonPressed("3");
    }
    @FXML
    public void fourPressed() {
        buttonPressed("4");
    }
    @FXML
    public void fivePressed() {
        buttonPressed("5");
    }
    @FXML
    public void sixPressed() {
        buttonPressed("6");
    }
    @FXML
    public void sevenPressed() {
        buttonPressed("7");
    }
    @FXML
    public void eightPressed() {
        buttonPressed("8");
    }
    @FXML
    public void ninePressed() {
        buttonPressed("9");
    }

    //Handles the keys typed on the keyboard and how those correspond to the different numbers/operators
    @FXML
    public void onNumberPressed(KeyEvent key) {
        //Digits 0-9
        if (key.getCode()== KeyCode.DIGIT0) {
            zeroPressed();
        } else if (key.getCode()== KeyCode.DIGIT1) {
            onePressed();
        } else if (key.getCode()== KeyCode.DIGIT2) {
            twoPressed();
        }else if (key.getCode()== KeyCode.DIGIT3) {
            threePressed();
        }else if (key.getCode()== KeyCode.DIGIT4) {
            fourPressed();
        }else if (key.getCode()== KeyCode.DIGIT5 && !key.isShiftDown()) {
            fivePressed();
        }else if (key.getCode()== KeyCode.DIGIT6) {
            sixPressed();
        }else if (key.getCode()== KeyCode.DIGIT7) {
            sevenPressed();
        }else if (key.getCode()== KeyCode.DIGIT8 && !key.isShiftDown()) {
            eightPressed();
        }else if (key.getCode()== KeyCode.DIGIT9) {
            ninePressed();
        }

        //Delete
        else if (key.getCode()==KeyCode.BACK_SPACE) {
            if (!Objects.equals(userInput.getText(), "0")) {
                if (userInput.getText().length() == 1) {
                    userInput.setText("0");
                } else {
                    userInput.setText(userInput.getText().substring(0, userInput.getText().length() - 1));
                }
            }
        }

        //Operators
        else if (key.getCode()== KeyCode.MINUS) {
            subtract();
        } else if (key.getCode()== KeyCode.EQUALS && key.isShiftDown()) {
            add();
        } else if (key.getCode()== KeyCode.DIGIT8) {
            multiply();
        } else if (key.getCode()== KeyCode.SLASH) {
            divide();
        } else if (key.getCode()== KeyCode.EQUALS || key.getCode()== KeyCode.ENTER) {
            equals();
        }

        //Decimal
        else if (key.getCode()== KeyCode.PERIOD) {
            addDecimal();
        }

        //Percent
        else if (key.getCode()== KeyCode.DIGIT5) {
            percentOf();
        }
    }
}