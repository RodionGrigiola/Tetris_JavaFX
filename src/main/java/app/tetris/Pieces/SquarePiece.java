package app.tetris.Pieces;

import java.util.ArrayList;
import java.util.List;

public class SquarePiece implements Piece {

    private final List<int[][]> pieceMatrix = new ArrayList<>();

    public SquarePiece() {
        pieceMatrix.add(new int[][]{
                {0, 0, 0, 0},
                {0, 1, 1, 0},
                {0, 1, 1, 0},
                {0, 0, 0, 0},
        });
    }

    public List<int[][]> getPieceMatrix() {
        return pieceMatrix;
    }



}
