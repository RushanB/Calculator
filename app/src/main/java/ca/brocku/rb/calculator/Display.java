package ca.brocku.rb.calculator;

import java.util.Observable;

/**
 * Created by Rushan Benazir on 2017-10-04.
 */

public class Display extends Observable {

    private String myMessage;
    private boolean isAppendableOff;

    //constructor
    public Display() {
        //default display
        setAppendableOff();
        setMyMessage("0");
    }

    //Allows display to be changed
    public void setAppendableOn() {
        isAppendableOff = false;
    }
    public void setAppendableOff() {
        isAppendableOff = true;
    }

    public boolean isAppendableOff() {
        return isAppendableOff;
    }

    //Sets a String to be displayed
    public void setMyMessage(String myMessage) {
        this.myMessage = myMessage;
    }
    public String getMyMessage() {
        return myMessage;
    }

    //Puts message on the display, notifies calculator observer
    public void appendMessage(String myMessage) {
        if (myMessage == null) {
            return;
        }

        if (isAppendableOff()) {
            setMyMessage(myMessage);
            setAppendableOn();
        } else {
            setMyMessage(getMyMessage() + myMessage);
        }

        setChanged();
        notifyObservers();
    }



}
