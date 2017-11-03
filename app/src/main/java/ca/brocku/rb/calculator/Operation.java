package ca.brocku.rb.calculator;

/**
 * Created by Rushan Benazir on 2017-10-04.
 */

public abstract class Operation {

//    public abstract Number doAddition(Number first, Number second) throws Exception;
//    public abstract Number doSubtraction(Number first, Number second) throws Exception;
//    public abstract Number doMultiplication(Number first, Number second) throws Exception;
//    public abstract Number doDivision(Number first, Number second) throws Exception;


    public abstract Number doCalculation(Number first, Number second) throws Exception;

    protected static Number format(Double number){
        if (number % 1 == 0) {
            return number.longValue();
        } else {
            return  number;
        }
    }


}
