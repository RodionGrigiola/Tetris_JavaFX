package app.tetris.Pieces;

import java.util.ArrayList;
import java.util.List;

public class ZPiece implements Piece {
    private final List<int[][]> pieceMatrix = new ArrayList<>();

    public ZPiece() {
        pieceMatrix.add(new int[][] {
                {0, 0, 0, 0},
                {7, 7, 0, 0},
                {0, 7, 7, 0},
                {0, 0, 0, 0}
        });
        pieceMatrix.add(new int[][] {
                {0, 7, 0, 0},
                {7, 7, 0, 0},
                {7, 0, 0, 0},
                {0, 0, 0, 0}
        });
    }

    public List<int[][]> getPieceMatrix() {
        return pieceMatrix;
    }

}
