import java.io.IOException;

public class Lab1 {
    public static void main(String[] args) throws IOException {
        Point3d myPoint1 = new Point3d(Double.parseDouble(args[0]), Double.parseDouble(args[1]), Double.parseDouble(args[2]));
        Point3d myPoint2 = new Point3d(Double.parseDouble(args[3]), Double.parseDouble(args[4]), Double.parseDouble(args[5]));
        Point3d myPoint3 = new Point3d(Double.parseDouble(args[6]), Double.parseDouble(args[7]), Double.parseDouble(args[8]));
        if (Point3d.comparePoint3d(myPoint1, myPoint2) || Point3d.comparePoint3d(myPoint1, myPoint3) || Point3d.comparePoint3d(myPoint2, myPoint3)) {
            System.out.println("Невозможно найти площадь");
        } else {
            System.out.println(computerArea(myPoint1, myPoint2, myPoint3));
        }
        public static double computerArea (Point3d myPint1, Point3d myPint2, Point3d myPint3){
            double AB = Math.sqrt(Math.pow(myPint1.getX() - myPint2.getX(), 2) + Math.pow(myPint1.getY() - myPint2.getY(), 2) + Math.pow(myPint1.getZ() - myPint1.getZ(), 2));
            double BC = Math.sqrt(Math.pow(myPint2.getX() - myPint3.getX(), 2) + Math.pow(myPint2.getY() - myPint3.getY(), 2) + Math.pow(myPint2.getZ() - myPint3.getZ(), 2));
            double CA = Math.sqrt(Math.pow(myPint3.getX() - myPint1.getX(), 2) + Math.pow(myPint3.getY() - myPint1.getY(), 2) + Math.pow(myPint3.getZ() - myPint1.getZ(), 2));
            double p = (AB + BC + CA) / 2;
            double s = Math.sqrt(p * (p - AB) * (p - BC) * (p - CA));
            return s;
        }
    }
}