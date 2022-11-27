import java.awt.geom.Rectangle2D;

public class BurningShip extends FractalGenerator {

    public static final int MAX_ITERATIONS = 2000; //Константа с максимальным количеством итераций

    @Override
    public void getInitialRange(Rectangle2D.Double range) { //установка начального диапазона в (-2 - 2.5i) - (2 + 1.5i)
        range.x = -2;
        range.y = -2.5;
        range.height = 4;
        range.width = 4;
    }

    @Override
    public int numIterations(double x, double y) { //итеративная функция для фрактала BurningShip
        double re = 0;
        double im = 0;
        int counter = 0;
        while (counter < MAX_ITERATIONS) {
            counter++;
            double newRe = re * re - im * im + x;
            double newIm = Math.abs(2 * re * im) + y;
            re = newRe;
            im = newIm;
            if (re * re + im * im > 4) {
                break;
            }
        }
        if (counter == MAX_ITERATIONS) {
            return -1;
        }
        else {
            return counter;
        }
    }
    @Override
    public String toString() {
        return "Burning Ship";
    }
}


