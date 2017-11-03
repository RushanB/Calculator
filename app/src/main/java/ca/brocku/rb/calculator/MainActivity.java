package ca.brocku.rb.calculator;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.view.View.OnClickListener;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Observer;
import java.util.Observable;

public class MainActivity extends Activity implements Observer, OnClickListener {

    //private instances
    private Calculator calculator;


    //Initialize the Calculator model and the onClickListeners
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //instantiate calculator
        calculator = new Calculator();
        calculator.addObserver(this);
        setOnClickButtonListener(R.id.button0);
        setOnClickButtonListener(R.id.button1);
        setOnClickButtonListener(R.id.button2);
        setOnClickButtonListener(R.id.button3);
        setOnClickButtonListener(R.id.button4);
        setOnClickButtonListener(R.id.button5);
        setOnClickButtonListener(R.id.button6);
        setOnClickButtonListener(R.id.button7);
        setOnClickButtonListener(R.id.button8);
        setOnClickButtonListener(R.id.button9);
        setOnClickButtonListener(R.id.plus_button);
        setOnClickButtonListener(R.id.minus_button);
        setOnClickButtonListener(R.id.multiply_button);
        setOnClickButtonListener(R.id.divide_button);
        setOnClickButtonListener(R.id.decimal_button);
        setOnClickButtonListener(R.id.equals_button);
        setOnClickButtonListener(R.id.clear_button);
        setOnClickButtonListener(R.id.open_bracket);
        setOnClickButtonListener(R.id.close_bracket);
//        setOnClickButtonListener(R.id.mode_button);

    }

    //Calculator updates the observer which updates the display with the value stored
    @Override
    public void update(Observable observable, Object data) {
        TextView display = (TextView) this.findViewById(R.id.display);
        Calculator calc = (Calculator) observable;

        display.setText(calc.getDisplay());

        if(calc.getMessage() != null) {
            Toast toast = Toast.makeText(getApplicationContext(), calc.getMessage(), Toast.LENGTH_SHORT);
            toast.show();

            calc.setMessage(null);
        }
    }

    //Method to set listener based on ButtonId
    private void setOnClickButtonListener(int id) {
        Button button = (Button) this.findViewById(id);
        button.setOnClickListener(this);
    }

    //Set checked or unchecked
    public void radioButtonClicked(View v) {
        RadioButton rb = (RadioButton) v;

        // Is the button now checked?
        boolean checked = ((RadioButton) v).isChecked();

        // Check radio button was clicked
        switch(rb.getId()) {
            case R.id.mode_button:
                if (checked) {
                    //change to formula mode
                    rb.setSelected(true);
                    rb.setChecked(true);
                    calculator.isForumalaModeOn = true;
                    break;
                } else {
                    rb.setSelected(false);
                    rb.setChecked(false);
                    calculator.isForumalaModeOn = false;
                }
        }
    }

    //Event handler calls methods when buttons are pressed
    @Override
    public void onClick(View v) {

        Button button = (Button) v;

        switch (button.getId()) {
            case R.id.clear_button:
                calculator.clear();
                break;
            case R.id.plus_button:
                calculator.addition();
                break;
            case R.id.minus_button:
                calculator.subtraction();
                break;
            case R.id.multiply_button:
                calculator.multiplication();
                break;
            case R.id.divide_button:
                calculator.division();
                break;
            case R.id.decimal_button:
                calculator.appendDecimal();
                break;
            case R.id.equals_button:
                calculator.calculate();
                break;
            case R.id.open_bracket:
                calculator.appendOpenBracket();
                break;
            case R.id.close_bracket:
                calculator.appendCloseBracket();
                break;
            default:
                calculator.appendDisplay(button.getText().toString());
                break;
        }
    }

}
