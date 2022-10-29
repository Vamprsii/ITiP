public class Point3d extends Point2d{
    public static void main(String[] args) {
        Point3d myPoint = new Point3d ();//создает точку (0.0,0.0,0.0)
        Point3d myOtherPoint = new Point3d (5,3,3);//создает точку (5,3,3)
        Point3d myOtherPoint3 = new Point3d (5,3,3);//создает точку (5,3,3)
        Point3d myOtherPoint1 = new Point3d (3,2,1);//создает точку (5,3,3)
        System.out.println(distanceTo(myOtherPoint1,myOtherPoint));
        System.out.println(comparePoint3d(myOtherPoint,myOtherPoint3)+"=true");
        System.out.println(comparePoint3d(myOtherPoint,myOtherPoint1)+"=false");
    }
    private double zCoord;
    public static double distanceTo(Point3d s,Point3d g){
        double D=Math.sqrt(Math.pow(s.getX() - g.getX(),2)+Math.pow(s.getY() - g.getY(),2)+Math.pow(s.getZ() - g.getZ(),2));
        return D;
    }
    public Point3d ( double x, double y, double z) {
        super(x,y);
        zCoord = z;
    }
    public Point3d () {
        this(0.0, 0.0,0.0);
    }
    public double getZ () {
        return zCoord;
    }
    public void setZ (double val) {
        zCoord = val;
    }
    public static boolean comparePoint3d (Point3d one,Point3d two){
        int c1,c2,c3;
        c1=Double.compare(one.getX(),two.getX());
        c2=Double.compare(one.getY(),two.getY());
        c3=Double.compare(one.getZ(),two.getZ());
        return (c1==0 && c2==0 && c3==0);
    }
}