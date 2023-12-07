package app.tetris.Pieces;

import java.util.ArrayList;
import java.util.List;

public class IPiece implements Piece {

    private final List<int[][]> pieceMatrix = new ArrayList<>();

    public IPiece() {
        // добавляем вариации фигуры
        pieceMatrix.add(new int[][]{
                {0, 0, 0, 0},
                {2, 2, 2, 2},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
        });
        pieceMatrix.add(new int[][]{
                {0, 2, 0, 0},
                {0, 2, 0, 0},
                {0, 2, 0, 0},
                {0, 2, 0, 0},
        });
    }

    public List<int[][]> getPieceMatrix() {
        return pieceMatrix;
    }
}
