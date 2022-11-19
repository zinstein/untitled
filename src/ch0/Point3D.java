package ch0;

class Point3D extends Point2D {
    int z;
    Point3D(int x,int y,int z){
        super(x,y);
        this.z = z;
    }
    Point3D(Point2D p,int z){
        super(p.x,p.y);
        this.z = z;
    }
    public void offset(int a,int b,int c) {
        super.offset(a, b);
        z += c;
    }
    public void distance(Point3D p3) {
        double dx=this.x-p3.x;
        double dy=this.y-p3.y;
        double dz=this.z-p3.z;
        double d=Math.sqrt(Math.pow(dx,2)+Math.pow(dy,2)+Math.pow(dz,2));
        System.out.println("距离为："+d);
    }
    public static void main(String[] args) {
        Point2D p2d1=new Point2D(0,0), p2d2=new Point2D(0,0);
        p2d2.offset(3,4);
        p2d1.distance(p2d2);

        Point3D p3d1=new Point3D(0,0,0), p3d2=new Point3D(p2d2,0);
        p3d2.offset(0,0,12);
        p3d1.distance(p3d2);
    }
}
