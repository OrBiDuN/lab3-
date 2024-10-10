package functions;

public interface TabulatedFunction {
    double getLeftDomainBorder();
    double getRightDomainBorder();
    double getFunctionValue(double x);
    int getPointCount();
    FunctionsPoint getPoint(int index);
    void setPoint(int index, FunctionsPoint point) throws InappropriateFunctionPointException;
    void setPointX(int index, double x) throws InappropriateFunctionPointException;
    void setPointY(int index, double y);
    void deletePoint(int index);
    void addPoint(FunctionsPoint point) throws InappropriateFunctionPointException;
    void displayPoint();
}
