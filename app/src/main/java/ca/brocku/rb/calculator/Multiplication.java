package ca.brocku.rb.calculator;

/**
 * Created by Rushan Benazir on 2017-10-16.
 */

public class Multiplication extends Operation {

    //constructor
    public Multiplication() {

    }

    //Multiplies two numbers
    @Override
    public Number doCalculation(Number first, Number second){
        Double result = first.doubleValue() * second.doubleValue();

        return format(result);
    }

}
