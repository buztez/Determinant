package Determinant;

import java.util.Scanner; //импортитруем класс Scanner

class Kramer extends Determinant { //главный класс Kramer

    public static void main(String[] args) { //главный метод класса Kramer main

        System.out.println("РЕШЕНИЕ СИСТЕМ УРАВНЕНИЙ МЕТОДОМ КРАМЕРА");
        Scanner scanner = new Scanner(System.in); //объявление переменной scanner для ввода с клавиатуры
        int n = 0; //объявление переменной для чтения кол-ва неизвестных
        while(n <= 0){ //чтение кол-ва уравнений и неизвестных n и исключение возможности введения некорректного числа
            System.out.print("Введите количество уравнений и неизвестных n = ");
            n = scanner.nextInt();
            if(n <= 0){
                System.out.print("ВВЕДЕНО НЕКОРРЕКТНОЕ ЧИСЛО! \n");
            }
        }
        double x = 0, d = 0; //объявление переменных для хранения определителей
        double[][] A = new double[n][n], E = new double[n][n]; //объявление массивов для коэффициентов перед неизвестными
        double[] B = new double[n], C = new double[n]; //объявление массивов для хранения свободных членов и корней уравнения

        System.out.println("Введите коэффициенты A перед неизвестными x: ");
        for (int i = 0; i < n; i++) { //цикл для ввода матрицы A коэффициентов перед неизвестными
            for (int j = 0; j < n; j++) {
                System.out.printf("A[%d][%d] = ", i+1, j+1);
                A[i][j] = scanner.nextDouble(); //чтение с клавиатуры коэффициента A[i][j]
            }
        }
        System.out.println("Введите свободные члены B: ");
        for (int i = 0; i < n; i++) { //цикл для ввода матрицы В свободных членов
            System.out.printf("B[%d] = ", i+1);
            B[i] = scanner.nextDouble(); //чтение с клавиатуры свободного члена В[i]
        }
        System.out.println("Полученная система уравнений: ");
        SystemOfEquation(A, B, n); //вывод уравнения в стандартном виде систем уравнений

        d = det(A); //вычисление определителя матрицы A
        if(d == 0) { //если определитель равен нулю, система не имеет решений
            System.out.println("Определитель равен нулю. \n" +
                    "СИСТЕМА НЕ ИМЕЕТ РЕШЕНИЯ!");
            System.exit(0); //принудительное завершение программы
        }

        for (int i = 0; i < n; i++) { //цикл для нахождения корней системы
            AssignmentOfArray(E, A, n); //присвание значений матрицы A вспомогательной матрице E
            for (int j = 0; j < n; j++) { //цикл для изменения матрицы Е, чтобы найти определитель для каждого из корней уравнения
                E[j][i] = B[j]; //столбец коэффициентов при соответствующей неизвестной заменяется столбцом свободных членов системы
            }
            x = det(E); //нахождение определителя матрицы Е
            C[i] = x / d; //вычисление соответствующего корня системы уравнений
        }

        System.out.println("Корни уравнения: ");
        for (int i = 0; i < n; i++) { //цикл для выводы корней системы уравнений
            System.out.printf("Корень x(%d) = ", i+1);
            System.out.printf("%.3f \n", C[i]);
        }
    }

    private static void AssignmentOfArray(double[][] A, double[][] B, int n) { //метод для присваивания значений одной двумерной матрицы другой
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                A[i][j] = B[i][j];
            }
        }
    }

    private static void SystemOfEquation(double[][] A, double[] B, int n){ //метод для выводы системы уравнений в стандартном виде систем линейных уравнений
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.printf("%.0f * x%d ", A[i][j],j+1);
                if(j < n - 1){
                    if(A[i][j+1] >= 0){
                        System.out.print(" + ");
                    }
                }
            }
            System.out.println(" = " + B[i]);
        }
    }
}
