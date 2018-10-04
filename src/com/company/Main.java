package com.company;

import java.security.InvalidParameterException;

public class Main {

//    public static void main(String[] args) {
//        try {
//            if (args.length != 2) {
//                throw new AmountOfParametersException("You gave " + args.length + " parameter(s), expected 2!");
//            }
//            var x = Double.parseDouble(args[0]);
//            var eps = Double.parseDouble(args[1]);
//            var series = new Series(eps);
//            System.out.println("Sum of your series = " + series.sum(new SeriesMember1(x)));
//        } catch (AmountOfParametersException exception) {
//            System.out.println(exception.getMessage());
//        } catch (NumberFormatException exception) {
//            System.out.println("Invalid parameters.");
//        } catch (SeriesException exception) {
//            System.out.println("Convergence exception. " + exception.getMessage());
//        }
//    }

    public static void main(String[] args) {
            double eps=1e-6,x=0.9;
            var series = new Series(eps);
            try {
                System.out.println("Sum of your series = " + series.sum(new SeriesMember8(x)));
            } catch (SeriesException e){
                System.out.println(e.getMessage());
            }
    }
}

class AmountOfParametersException extends InvalidParameterException {
    AmountOfParametersException(String message) {
        super(message);
    }
}