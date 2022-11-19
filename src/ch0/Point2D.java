package ch0;

public class Point2D {
    int x,y;
    Point2D(int x,int y){
        this.x = x;
        this.y = y;
    }
    public void offset(int a,int b) {
        this.x += a;
        this.y += b;
    }
    public void distance(Point2D p2) {
        double dx=this.x-p2.x;
        double dy=this.y-p2.y;
        double d=Math.sqrt(Math.pow(dx,2)+Math.pow(dy,2));
        System.out.println("距离为："+d);
    }
}
