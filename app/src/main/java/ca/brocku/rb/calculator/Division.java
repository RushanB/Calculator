package ca.brocku.rb.calculator;

/**
 * Created by Rushan Benazir on 2017-10-16.
 */

public class Division extends Operation {

    //constructor
    public Division() {

    }

    //Divides two numbers, checks for divison by zero
    @Override
    public Number doCalculation(Number first, Number second) throws Exception{

        if (second.doubleValue() == 0) {
            throw new Exception("Error - Division by Zero");
        } else {
            Double result = first.doubleValue() / second.doubleValue();

            return format(result);
        }
    }

}
