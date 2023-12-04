package Pieces;

import java.util.ArrayList;
import java.util.List;

public class JPiece implements Piece {
    private final List<int[][]> pieceMatrix = new ArrayList<>();

    public JPiece() {
        pieceMatrix.add(new int[][]{
                {0, 0, 0, 0},
                {3, 3, 3, 0},
                {0, 0, 3, 0},
                {0, 0, 0, 0},
        });
        pieceMatrix.add(new int[][]{
                {0, 0, 0, 0},
                {0, 3, 3, 0},
                {0, 3, 0, 0},
                {0, 3, 0, 0},
        });
        pieceMatrix.add(new int[][]{
                {0, 0, 0, 0},
                {0, 3, 0, 0},
                {0, 3, 3, 3},
                {0, 0, 0, 0},
        });
        pieceMatrix.add(new int[][]{
                {0, 0, 3, 0},
                {0, 0, 3, 0},
                {0, 3, 3, 0},
                {0, 0, 0, 0},
        });
    }

    public List<int[][]> getPieceMatrix() {
        return pieceMatrix;
    }
}
