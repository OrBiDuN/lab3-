package functions;

class FunctionNode {
    FunctionsPoint data;
    FunctionNode prev;
    FunctionNode next;

    public FunctionNode(FunctionsPoint data) {
        this.data = data;
    }
}

public class LinkedListTabulatedFunction implements TabulatedFunction {
    private FunctionNode head;
    private int size;

    public LinkedListTabulatedFunction(double leftX, double rightX, int pointsCount) {
        if (pointsCount < 2 || leftX >= rightX) {
            throw new IllegalArgumentException("Некорректные параметры.");
        }
        head = new FunctionNode(null);
        head.prev = head;
        head.next = head;
        double step = (rightX - leftX) / (pointsCount - 1);
        for (int i = 0; i < pointsCount; i++) {
            double x = leftX + i * step;
            addNodeToTail(new FunctionsPoint(x, 0));
        }
    }

    private FunctionNode addNodeToTail(FunctionsPoint point) {
        FunctionNode newNode = new FunctionNode(point);
        FunctionNode last = head.prev;
        last.next = newNode;
        newNode.prev = last;
        newNode.next = head;
        head.prev = newNode;
        size++;
        return newNode;
    }

    public double getLeftDomainBorder() {
        return head.next.data.getX();
    }

    public double getRightDomainBorder() {
        return head.prev.data.getX();
    }

    public double getFunctionValue(double x) {
        if (x < getLeftDomainBorder() || x > getRightDomainBorder()) {
            return Double.NaN;
        }
        FunctionNode current = head.next;
        while (current.next != head) {
            if (x <= current.next.data.getX()) {
                double slope = (current.next.data.getY() - current.data.getY()) /
                        (current.next.data.getX() - current.data.getX());
                return current.data.getY() + slope * (x - current.data.getX());
            }
            current = current.next;
        }
        return head.prev.data.getY();
    }

    public int getPointCount() {
        return size;
    }

    public FunctionsPoint getPoint(int index) {
        return getNodeByIndex(index).data;
    }

    public void setPoint(int index, FunctionsPoint point) throws InappropriateFunctionPointException {
        FunctionNode node = getNodeByIndex(index);
        if ((node.prev != head && point.getX() <= node.prev.data.getX()) ||
                (node.next != head && point.getX() >= node.next.data.getX())) {
            throw new InappropriateFunctionPointException("Неверное значение x.");
        }
        node.data = point;
    }

    public void setPointX(int index, double x) throws InappropriateFunctionPointException {
        setPoint(index, new FunctionsPoint(x, getPoint(index).getY()));
    }

    public void setPointY(int index, double y) {
        getPoint(index).setY(y);
    }

    public void deletePoint(int index) {
        if (size < 3) {
            throw new IllegalStateException("Недостаточно точек для удаления.");
        }
        FunctionNode nodeToRemove = getNodeByIndex(index);
        nodeToRemove.prev.next = nodeToRemove.next;
        nodeToRemove.next.prev = nodeToRemove.prev;
        size--;
    }

    public void addPoint(FunctionsPoint point) throws InappropriateFunctionPointException {
        FunctionNode current = head.next;
        while (current != head && current.data.getX() < point.getX()) {
            current = current.next;
        }
        if (current != head && current.data.getX() == point.getX()) {
            throw new InappropriateFunctionPointException("Точка с таким x уже существует.");
        }
        FunctionNode newNode = new FunctionNode(point);
        newNode.next = current;
        newNode.prev = current.prev;
        current.prev.next = newNode;
        current.prev = newNode;
        size++;
    }

    public void displayPoint() {
        System.out.print("Точки функции: ");
        FunctionNode current = head.next;
        while (current != head) {
            System.out.print(current.data + (current.next != head ? ", " : ""));
            current = current.next;
        }
        System.out.println();
    }

    private FunctionNode getNodeByIndex(int index) {
        if (index < 0 || index >= size) {
            throw new FunctionPointIndexOutOfBoundsException("Неверный индекс.");
        }
        FunctionNode current = head.next;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current;
    }
}
