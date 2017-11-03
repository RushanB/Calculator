package ca.brocku.rb.calculator;

/**
 * Created by Rushan Benazir on 2017-10-16.
 */

public class Subtraction extends Operation {

    //constructor
    public Subtraction() {

    }

    //Subtracts two numbers and returns result as subclass of abstract class Number
    @Override
    public  Number doCalculation(Number first, Number second){
        Double result = first.doubleValue() - second.doubleValue();

        return  format(result);
    }
}
