package ca.brocku.rb.calculator;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by RB on 2017-09-30.
 */

public class Calculator extends Observable implements Observer {

    //Properties
    private boolean hasUpdated;
    private boolean hasDecimal;
    private boolean hasBracket;
    public boolean isForumalaModeOn;
    private Display display;
    private Display resultDisplay;
    private Number operand;
    private Operation operation;
    private String myMessage;


    //initialize the calculator
    public Calculator() {
        display = new Display();
        resultDisplay = new Display();
        setOperand(null);
        setOperation(null);
        setMessage(null);

        hasUpdated = false;
        hasDecimal = false;
        hasBracket = false;
        isForumalaModeOn = false;

        display.addObserver(this);
        resultDisplay.addObserver(this);
    }


    //Calculate the operand and operation in memory and display the result
    public void calculate() {
        Number result;

        if(getOperation() == null) {
            return;
        }

        if (getOperand() == null) {
            setOperand(getDisplay());

        }

        if (hasUpdated) {
            try {
                Number first = getOperand();
                setOperand(getDisplay());
                result = getOperation().doCalculation(first, getOperand());
                setOperation(null);
                setOperand(null);
                appendDisplay(result, true);
//                appendResultDisplay(result, true);
                hasDecimal = false;
                hasUpdated = false;
                hasBracket = false;
                isForumalaModeOn = false;
            } catch (Exception except) {
                setMessage(except.getMessage());
                setChanged();
                notifyObservers();
            }
        } else if ((hasUpdated) && (isForumalaModeOn)) {
            try {
                Number first = getOperand();
                setOperand(getDisplay());
                result = getOperation().doCalculation(first, getOperand());
                setOperation(null);
                setOperand(null);
                appendDisplay(result,false);
                hasDecimal = false;
                hasUpdated = true;
                hasBracket = false;
                isForumalaModeOn = true;
            } catch (Exception except) {
                setMessage(except.getMessage());
                setChanged();
                notifyObservers();
            }
        }
    }

    //Get the text to be displayed
    public String getDisplay() {
        return display.getMyMessage();
    }

    //Append the text to the display - Overloads previous methods
    public void appendDisplay(String myMessage) {
        appendDisplay(myMessage, false);
    }

    //Append the text to the display, if true then text will be replaced
    public void appendDisplay(String myMessage, boolean isReplaced) {
        if (isReplaced) {
            display.setMyMessage(myMessage);
            display.setAppendableOff();
        } else {
            display.appendMessage(myMessage);
        }

        setChanged();
        notifyObservers();
    }

    //Append the text to the result Display
    public void appendResultDisplay(String myMessage, boolean isReplaced) {
        if(isReplaced) {
            resultDisplay.setMyMessage(myMessage);
            resultDisplay.setAppendableOff();
        } else {
            resultDisplay.appendMessage(myMessage);
        }

        setChanged();
        notifyObservers();
    }

    //Append the text text to the display, if true then text will be replaced -Overloads previous method
    //Checks to see if number passed is larger than the number allowed on the display
    public void appendDisplay(Number number, boolean isReplaced) {
        String myMessage;

        if (number.doubleValue() > 99999999) {
            myMessage = formatNumber(number);
        } else {
            myMessage = number.toString();
        }

        appendDisplay(myMessage, isReplaced);
    }

    //The number formatted by being passed into scientific notation
    public String formatNumber(Number number) {
        NumberFormat myFormat = new DecimalFormat("0.####E0");
        return myFormat.format(number.doubleValue());
    }

    //Addition
    public void addition() {
        setOperation(new Addition());
    }

    //Multiplication
    public void multiplication(){
        setOperation(new Multiplication());
    }

    //Subtraction
    public void subtraction(){
        setOperation(new Subtraction());
    }

    //Division
    public void division(){
        setOperation(new Division());
    }

    //Clear
    public void clear() {
        setOperand(null);
        setOperation(null);
        appendDisplay("0", true);
        hasDecimal = false;
        hasBracket = false;
    }

    //Decimal
    //append decimal to display if it doesn't already exists
    public void appendDecimal() {
        if (!hasDecimal) {
            appendDisplay(".");
            hasDecimal = true;
        }
    }

    //Brackets
    //append brackets to display
    public  void appendOpenBracket() {
        if(!hasBracket) {
            appendDisplay("(");
            hasBracket = true;
        }
    }

    public void appendCloseBracket() {
        if(hasBracket) {
            appendDisplay(")");
            hasBracket = false;
        }
    }

    //Check to see if divisible by 1
    private static boolean isInteger(Double number) {
        return number % 1 == 0;
    }

    //Sets the operand
    private void setOperand(String operand) {
        if (operand == null){
            this.operand = null;
            return;
        }

        Double number = Double.valueOf(operand);

        if (isInteger(number)) {
            this.operand = number.intValue();
        } else {
            this.operand = number;
        }
    }

    //Gets the operand
    public Number getOperand() {
        return operand;
    }


    //Assigns an operation to the Operation type
    private void setOperation(Operation operation) {
        if (operation == null) {
            this.operation = null;
            return;
        }

        calculate();
        setOperand(getDisplay());
        this.operation = operation;
        display.setAppendableOff();

    }

    //Gets a reference to the Operation
    private Operation getOperation() {
        return operation;
    }


    //Gets the Message to be popped
    public String getMessage() {
        return myMessage;
    }
    //Sets the Message to be popped
    public void setMessage(String message) {
        if(message == null) {
            myMessage = null;
        } else {
            myMessage = message;
        }
    }

    //Update the display
    @Override
    public void update(Observable observable, Object data) {
        hasUpdated = true;
    }
}
