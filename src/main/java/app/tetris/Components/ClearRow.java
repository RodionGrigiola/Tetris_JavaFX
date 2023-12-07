package app.tetris.Components;

// Класс содержит информацию о количестве рядов к удалению,
// матрице, в которой ряды уже удалены и бонусу к скору

public class ClearRow {
    private final int scoreBonus;
    private final int linesRemoved;
    private final int [][] clearedRowsMatrix;

    public ClearRow(int linesRemoved, int[][] clearedRowsMatrix, int scoreBonus) {
        this.linesRemoved = linesRemoved;
        this.clearedRowsMatrix = clearedRowsMatrix;
        this.scoreBonus = scoreBonus;
    }

    public int getScoreBonus() {
        return scoreBonus;
    }

    public int getLinesRemoved() {
        return linesRemoved;
    }

    public int[][] getClearedRowsMatrix() {
        return clearedRowsMatrix;
    }
}
