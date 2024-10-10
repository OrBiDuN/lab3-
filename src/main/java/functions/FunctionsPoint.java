package functions;

public class FunctionsPoint {
    private double x, y;

    public FunctionsPoint(double x, double y) {
        this.x = x;
        this.y = y;
    }
    public FunctionsPoint(FunctionsPoint point) {
        this.x = point.getX();
        this.y = point.getY();
    }
    public FunctionsPoint() {
        this.x = 0;
        this.y = 0;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }
    @Override
    public String toString(){
        return "(" + x + ", " + y + ")";
    }
}