package app.tetris.Components;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

// Класс для работы с матрицами
// Реализованы операции слияния, удаления заполненного ряда,
// проверки на коллизию

public class MatrixOperations {

    public static boolean intersects(int[][] matrix, int[][] piece, int x, int y) {
        // проверяем коллизию
        for (int i = 0; i < piece.length; i++) {
            for (int j = 0; j < piece[i].length; j++) {
                int targetX = x + i;
                int targetY = y + j;
                // Проверяем будет ли коллизия с границами или другими фигурами
                if (piece[j][i] != 0 && (outOfBounds(matrix, targetX, targetY) || matrix[targetY][targetX] != 0)) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean outOfBounds(int[][] matrix, int targetX, int targetY) {
        // Проверка на границы слева, снизу и справа
        if (targetX >= 0 && targetY < matrix.length && targetX < matrix[targetY].length) return false;
        return true;
    }

    // вызываем данную функцию когда фигура не может опустить ниже => была коллизия
    public static int[][] merge(int[][] currentGameMatrix, int[][] piece, int x, int y) {
        int[][] copy = copy(currentGameMatrix); // копируем игровую матрицу
        // добавляем текущую фигуру в эту копию и возвращаем ее
        for (int i = 0; i < piece.length; i++) {
            for (int j = 0; j < piece[i].length; j++) {
                int targetX = x + i;
                int targetY = y + j;
                if (piece[j][i] != 0) {
                    copy[targetY - 1][targetX] = piece[j][i];
                }
            }
        }
        return copy;
    }

    public static int[][] copy(int[][] original) {
        int[][] copyMatrix = new int[original.length][];
        for (int i = 0; i < original.length; i++) {
            int[] tempMatrix = original[i];
            copyMatrix[i] = new int[tempMatrix.length];
            System.arraycopy(tempMatrix, 0, copyMatrix[i], 0, tempMatrix.length);
        }
        return copyMatrix;
    }

    public static ClearRow checkRemoving(int[][] matrix) {
        int[][] tmp = new int[matrix.length][matrix[0].length];
        List<Integer> clearedRows = new ArrayList<>(); // Добавляем номера рядов, которые нужно удолить
        Deque<int[]> newRows = new ArrayDeque<>(); // Добавляем все остальные ряды по порядку

        for(int i = 0; i < matrix.length; i++) {
            int[] tmpRow = new int[matrix[i].length];
            boolean rowToClear = true;
            for(int j = 0; j < matrix[0].length; j++) {
                if(matrix[i][j] == 0) {
                    // Если хотя бы один элемент равен нулю, то в ряду есть пустой элемент - не удаляем
                    rowToClear = false;
                }
                tmpRow[j] = matrix[i][j]; // сохраняем все элементы ряда сюда
            }

            if(rowToClear) {
                clearedRows.add(i); // добавляем номер ряда на удаление
            } else {
                newRows.add(tmpRow); // добавляем весь ряд как есть в дек
            }
        }

        // Берем ряды из дека начиная с конца и добавляем их в конец новой матрицы
        for(int i = matrix.length - 1; i >= 0; i--) {
            int[] row = newRows.pollLast();
            if(row != null) {
                tmp[i] = row;
            }
            else {
                break;
            }
        }

        int scoreBonus = 50 * clearedRows.size(); // считаем бонус скор
        return new ClearRow(clearedRows.size(), tmp, scoreBonus);
    }

}
