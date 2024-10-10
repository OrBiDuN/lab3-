package functions;

public class ArrayTabulatedFunction implements TabulatedFunction {
    private FunctionsPoint[] points;
    private int pointCount;

    public ArrayTabulatedFunction(double leftX, double rightX, int pointsCount) {
        if (pointsCount < 2 || leftX >= rightX) {
            throw new IllegalArgumentException("Некорректные параметры.");
        }
        points = new FunctionsPoint[pointsCount];
        pointCount = pointsCount;
        double step = (rightX - leftX) / (pointsCount - 1);
        for (int i = 0; i < pointsCount; i++) {
            double x = leftX + i * step;
            points[i] = new FunctionsPoint(x, 0);
        }
    }

    public ArrayTabulatedFunction(double leftX, double rightX, double[] values) {
        if (values.length < 2 || leftX >= rightX) {
            throw new IllegalArgumentException("Некорректные параметры.");
        }
        points = new FunctionsPoint[values.length];
        pointCount = values.length;
        double step = (rightX - leftX) / (values.length - 1);
        for (int i = 0; i < values.length; i++) {
            double x = leftX + i * step;
            points[i] = new FunctionsPoint(x, values[i]);
        }
    }

    public double getLeftDomainBorder() {
        return points[0].getX();
    }

    public double getRightDomainBorder() {
        return points[pointCount - 1].getX();
    }

    public double getFunctionValue(double x) {
        if (x < getLeftDomainBorder() || x > getRightDomainBorder()) {
            return Double.NaN;
        }
        for (int i = 1; i < pointCount; i++) {
            if (x <= points[i].getX()) {
                FunctionsPoint point1 = points[i - 1];
                FunctionsPoint point2 = points[i];
                double slope = (point2.getY() - point1.getY()) / (point2.getX() - point1.getX());
                return point1.getY() + slope * (x - point1.getX());
            }
        }
        return points[pointCount - 1].getY();
    }

    public int getPointCount() {
        return pointCount;
    }

    public FunctionsPoint getPoint(int index) {
        checkIndex(index);
        return points[index];
    }

    public void setPoint(int index, FunctionsPoint point) throws InappropriateFunctionPointException {
        checkIndex(index);
        if ((index > 0 && point.getX() <= points[index - 1].getX()) ||
                (index < pointCount - 1 && point.getX() >= points[index + 1].getX())) {
            throw new InappropriateFunctionPointException("Координата x не соответствует ограничениям.");
        }
        points[index] = point;
    }

    public void setPointX(int index, double x) throws InappropriateFunctionPointException {
        checkIndex(index);
        if ((index > 0 && x <= points[index - 1].getX()) || (index < pointCount - 1 && x >= points[index + 1].getX())) {
            throw new InappropriateFunctionPointException("Неверное значение x.");
        }
        points[index].setX(x);
    }

    public void setPointY(int index, double y) {
        checkIndex(index);
        points[index].setY(y);
    }

    public void deletePoint(int index) {
        if (pointCount < 3) {
            throw new IllegalStateException("Недостаточно точек для удаления.");
        }
        checkIndex(index);
        System.arraycopy(points, index + 1, points, index, pointCount - index - 1);
        pointCount--;
    }

    public void addPoint(FunctionsPoint point) throws InappropriateFunctionPointException {
        if (pointCount == points.length) {
            FunctionsPoint[] newPoints = new FunctionsPoint[points.length + 1];
            System.arraycopy(points, 0, newPoints, 0, points.length);
            points = newPoints;
        }
        int insertIndex = 0;
        while (insertIndex < pointCount && points[insertIndex].getX() < point.getX()) {
            insertIndex++;
        }
        if (insertIndex < pointCount && points[insertIndex].getX() == point.getX()) {
            throw new InappropriateFunctionPointException("Точка с таким x уже существует.");
        }
        System.arraycopy(points, insertIndex, points, insertIndex + 1, pointCount - insertIndex);
        points[insertIndex] = new FunctionsPoint(point);
        pointCount++;
    }

    public void displayPoint() {
        System.out.print("Точки функции: ");
        for (int i = 0; i < pointCount; i++) {
            System.out.print(points[i] + (i < pointCount - 1 ? ", " : ""));
        }
        System.out.println();
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= pointCount) {
            throw new FunctionPointIndexOutOfBoundsException("Недопустимый индекс.");
        }
    }
}
