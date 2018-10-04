package com.company;

class Series implements SeriesBase {
    private double eps;

    Series(double eps) {

        this.eps = eps;
    }

    @Override
    public double sum(SeriesMemberBase member) {
        double sum = 0.;
        while (Math.abs(member.next()) >= eps) {
            sum += member.current();
        }
        return sum;
    }
}


class SeriesException extends Exception {
    SeriesException(String message) {
        super(message);
    }
}


class SeriesMember3 implements SeriesMemberBase {
    int getK() {
        return k;
    }

    public String toString() {
        return k + " " + value;
    }

    private int k;
    private double value;

    SeriesMember3(double x) {
        this.k = 0;
        value = -1 * x * x * Math.pow(4. / 3, 4 * k + 2);
    }

    @Override
    public double next() {
        k++;
        return value *= -1. * Math.pow(4. / 3, 4) / (2 * k) / (2 * k + 1);
    }

    @Override
    public double current() {
        return value;
    }
}

class SeriesMember1 implements SeriesMemberBase {
    private double value, x6kPlus3, x;
    private int k;

    SeriesMember1(double x) throws SeriesMember1ConvergenceException {
        if (Math.abs(x) >= 1)
            throw new SeriesMember1ConvergenceException("Series sum[x^(3k^2)] doesn't converge with x = " + x);
        this.k = 0;
        this.value = 1.;
        this.x6kPlus3 = Math.pow(x, 3);
        this.x = x;
    }

    @Override
    public double next() {
        k++;
        value *= x6kPlus3;
        x6kPlus3 *= Math.pow(x, 6);
        return value;
    }

    @Override
    public double current() {
        return value;
    }

}

class SeriesMember1ConvergenceException extends SeriesException {

    SeriesMember1ConvergenceException(String message) {
        super(message);
    }
}


class SeriesMember2 implements SeriesMemberBase {

    private double value, x, sqrtAbsXPlus2;

    private int k;

    SeriesMember2(double x) throws SeriesMember2ZeroDivisionException {
        if (x == 0)
            throw new SeriesMember2ZeroDivisionException("Trying to divide by zero.");
        this.x = x;
        k = 3;
        sqrtAbsXPlus2 = Math.sqrt(Math.abs(x) + 2);
        value = sqrtAbsXPlus2 / x / k / k;
    }

    @Override
    public double next() {
        k++;
        return value = sqrtAbsXPlus2 / x / k / k;
    }

    @Override
    public double current() {
        return value;
    }
}

class SeriesMember2ZeroDivisionException extends SeriesException {

    SeriesMember2ZeroDivisionException(String message) {
        super(message);
    }
}

class SeriesMember4 implements SeriesMemberBase {

    private double value, minusXDiv3, minusXDiv3PowK;

    private int k;

    SeriesMember4(double x) throws SeriesMember4ConvergenceException {
        if (Math.abs(x) >= 3)
            throw new SeriesMember4ConvergenceException("Series sum[(-1)^k(k+1)x^k/(3^k)] doesn't converge with x = " + x);
        k = 0;
        minusXDiv3 = -1 * x / 3.;
        minusXDiv3PowK = 1;
        value = 1;
    }

    @Override
    public double next() {
        k++;
        minusXDiv3PowK *= minusXDiv3;
        return value = minusXDiv3PowK * (k + 1.);
    }

    @Override
    public double current() {
        return value;
    }
}

class SeriesMember4ConvergenceException extends SeriesException {

    SeriesMember4ConvergenceException(String message) {
        super(message);
    }
}


class SeriesMember8 implements SeriesMemberBase {

    private double value, minusXPowK, x;

    private int k;

    SeriesMember8(double x) throws SeriesMember8ConvergenceException {
        if (Math.abs(x) > 1 || x == -1)
            throw new SeriesMember8ConvergenceException("Series sum[(-1)^k*x^k/k] doesn't converge with x = " + x);
        k = 0;
        minusXPowK = 1;
        this.x = x;
        value = Double.POSITIVE_INFINITY;
    }

    @Override
    public double next() {
        k++;
        minusXPowK *= x * -1;
        return value = minusXPowK / k;
    }

    @Override
    public double current() {
        return value;
    }
}

class SeriesMember8ConvergenceException extends SeriesException {

    SeriesMember8ConvergenceException(String message) {
        super(message);
    }
}