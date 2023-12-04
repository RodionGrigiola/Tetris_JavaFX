package Pieces;

import java.util.ArrayList;
import java.util.List;

public class SPiece implements Piece  {

    private final List<int[][]> pieceMatrix = new ArrayList<>();

    public SPiece() {
        pieceMatrix.add(new int[][] {
                {0, 0, 0, 0},
                {0, 5, 5, 0},
                {5, 5, 0, 0},
                {0, 0, 0, 0}
        });
        pieceMatrix.add(new int[][] {
                {5, 0, 0, 0},
                {5, 5, 0, 0},
                {0, 5, 0, 0},
                {0, 0, 0, 0}
        });

    }

    public List<int[][]> getPieceMatrix() {
        return pieceMatrix;
    }

}
