package functions;

public class Main {
    public static void main(String[] args) {
        System.out.println("Тесты для ArrayTabulatedFunction:");
        testArrayTabulatedFunction();

        System.out.println("\nТесты для LinkedListTabulatedFunction:");
        testLinkedListTabulatedFunction();
    }

    public static void testArrayTabulatedFunction() {
        // Создание функции с массивом значений
        double[] values = {0, 1, 4, 9, 16}; // Значения y
        ArrayTabulatedFunction arrayFunc = new ArrayTabulatedFunction(0, 4, values);

        // Вывод точек функции
        arrayFunc.displayPoint();

        // Проверка границ домена
        System.out.println("Левая граница: " + arrayFunc.getLeftDomainBorder());
        System.out.println("Правая граница: " + arrayFunc.getRightDomainBorder());

        // Проверка значений функции
        System.out.println("Значение функции в точке x = 2: " + arrayFunc.getFunctionValue(2));

        // Изменение значения точки
        arrayFunc.setPointY(2, 8);
        System.out.println("Новое значение y в точке x = 2: " + arrayFunc.getPoint(2).getY());

        // Добавление новой точки
        try {
            arrayFunc.addPoint(new FunctionsPoint(5, 25));
            System.out.println("После добавления точки (5, 25): ");
            arrayFunc.displayPoint();
        } catch (InappropriateFunctionPointException e) {
            System.out.println(e.getMessage());
        }

        // Удаление точки
        arrayFunc.deletePoint(0);
        System.out.println("После удаления первой точки: ");
        arrayFunc.displayPoint();
    }

    public static void testLinkedListTabulatedFunction() {
        // Создание функции с заданным количеством точек и интервалом
        LinkedListTabulatedFunction linkedListFunc = new LinkedListTabulatedFunction(0, 4, 5);

        // Вывод точек функции
        linkedListFunc.displayPoint();

        // Проверка границ домена
        System.out.println("Левая граница: " + linkedListFunc.getLeftDomainBorder());
        System.out.println("Правая граница: " + linkedListFunc.getRightDomainBorder());

        // Проверка значений функции
        System.out.println("Значение функции в точке x = 2: " + linkedListFunc.getFunctionValue(2));

        // Изменение значения точки
        linkedListFunc.setPointY(2, 10);
        System.out.println("Новое значение y в точке x = 2: " + linkedListFunc.getPoint(2).getY());

        // Добавление новой точки
        try {
            linkedListFunc.addPoint(new FunctionsPoint(5, 20));
            System.out.println("После добавления точки (5, 20): ");
            linkedListFunc.displayPoint();
        } catch (InappropriateFunctionPointException e) {
            System.out.println(e.getMessage());
        }

        // Удаление точки
        linkedListFunc.deletePoint(1);
        System.out.println("После удаления второй точки: ");
        linkedListFunc.displayPoint();
    }
}
