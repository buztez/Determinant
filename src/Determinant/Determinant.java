package Determinant;

public class Determinant { //всопомгательный класс Детерминант

    final static double det(double[][] A){ //рекурсивный метод для вычисления определителя
        final int n; //объявление переменной n для длины массива
        n = A.length; //присваивание длины массива
        if(n == 1) return A[0][0]; //условие при одном уравнении с одной неизвесной
        double ans = 0; //объявление переменной для хранения значения определителя
        double[][] B = new double[n-1][n-1]; //объявление вспомогательного массива B
        int l = 1; //объявление переменной для знакочередования в вычислении определителя
        for(int i = 0; i < n; ++i){ //основной цикл вычисления определителя

            int x = 0, y = 0; //объявления переменных для итерации вспомогательного массива B
            for(int j = 1; j < n; ++j){ //цикл для вынесения минора матрицы
                for(int k = 0; k < n; ++k){
                    if(i == k) continue;
                    B[x][y] = A[j][k];
                    ++y;
                    if(y == n - 1){
                        y = 0;
                        ++x;
                    }
                }
            }
            ans += l * A[0][i] * det(B); //вычисление определителя при определении минимального минора
            l *= (-1); //меняем знак у переменной знакочередования
        }
        return ans; //возвращаем значение определителя
    }
}

