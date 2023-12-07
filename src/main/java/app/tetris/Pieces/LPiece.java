package app.tetris.Pieces;

import java.util.ArrayList;
import java.util.List;

public class LPiece implements Piece {
    private final List<int[][]> pieceMatrix = new ArrayList<>();

    public LPiece() {
        pieceMatrix.add(new int[][] {
                {0, 0, 0, 0},
                {0, 4, 4, 4},
                {0, 4, 0, 0},
                {0, 0, 0, 0}
        });
        pieceMatrix.add(new int[][] {
                {0, 0, 0, 0},
                {0, 4, 4, 0},
                {0, 0, 4, 0},
                {0, 0, 4, 0}
        });
        pieceMatrix.add(new int[][] {
                {0, 0, 0, 0},
                {0, 0, 4, 0},
                {4, 4, 4, 0},
                {0, 0, 0, 0}
        });
        pieceMatrix.add(new int[][] {
                {0, 4, 0, 0},
                {0, 4, 0, 0},
                {0, 4, 4, 0},
                {0, 0, 0, 0}
        });
    }

    public List<int[][]> getPieceMatrix() {
        return pieceMatrix;
    }
}
