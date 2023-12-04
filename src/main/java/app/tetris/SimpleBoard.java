package app.tetris;

import Pieces.Piece;
import Pieces.RandomPieceGenerator;

public class SimpleBoard {

    private final int width;
    private final int height;
    private int [][] currentGameMatrix;
    private final RandomPieceGenerator pieceGenerator;
    private Piece piece;
    private int currentShape = 0;

    public SimpleBoard(int width, int height) {
        this.width = width;
        this.height = height;
        this.currentGameMatrix = new int[width][height];
        this.pieceGenerator = new RandomPieceGenerator();
    }

    public boolean createNewPiece() {
        Piece newPiece = pieceGenerator.getPiece();
        setPiece(newPiece);
        return true;
    }

    public int[][] getBoardMatrix() {
        return this.currentGameMatrix;
    }

    public int[][] getCurrentShape() {
        return this.piece.getPieceMatrix().get(currentShape);
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }


}
