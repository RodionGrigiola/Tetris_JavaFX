package app.tetris.Pieces;

import java.util.ArrayList;
import java.util.List;

public class TPiece implements Piece {
    private final List<int[][]> pieceMatrix = new ArrayList<>();

    public TPiece() {
        pieceMatrix.add(new int[][] {
                {0, 0, 0, 0},
                {6, 6, 6, 0},
                {0, 6, 0, 0},
                {0, 0, 0, 0}
        });
        pieceMatrix.add(new int[][] {
                {0, 6, 0, 0},
                {0, 6, 6, 0},
                {0, 6, 0, 0},
                {0, 0, 0, 0}
        });
        pieceMatrix.add(new int[][] {
                {0, 6, 0, 0},
                {6, 6, 6, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0}
        });
        pieceMatrix.add(new int[][] {
                {0, 6, 0, 0},
                {6, 6, 0, 0},
                {0, 6, 0, 0},
                {0, 0, 0, 0}
        });
    }

    public List<int[][]> getPieceMatrix() {
        return pieceMatrix;
    }
}
