package app.tetris.Components;

import app.tetris.Pieces.Piece;
import app.tetris.Pieces.RandomPieceGenerator;

import java.awt.Point;

public class SimpleBoard {

    private final int width;
    private final int height;
    private int [][] currentGameMatrix;
    private final RandomPieceGenerator pieceGenerator;
    private Piece piece;
    private int currentShape = 0;
    private final Score score;
    private Point currentOffset;

    public SimpleBoard(int width, int height) {
        this.width = width;
        this.height = height;
        this.currentGameMatrix = new int[height][width];
        this.pieceGenerator = new RandomPieceGenerator();
        score = new Score();
    }

    public boolean createNewPiece() {
        currentShape = 0;
        Piece newPiece = pieceGenerator.getPiece();
        setPiece(newPiece);
        currentOffset = new Point(4,0);
        return MatrixOperations.intersects(currentGameMatrix,
                getCurrentShape(),
                currentOffset.x,
                currentOffset.y);
    }

    public boolean movePieceDown() {
        Point p = new Point(currentOffset);
        p.translate(0,1);
        currentOffset = p;
        boolean collision = MatrixOperations.intersects(currentGameMatrix, getCurrentShape(), p.x, p.y);
        if(collision) return false;
        return true;
    }

    public void movePieceLeft() {
        Point p = new Point(currentOffset);
        p.translate(-1,0);
        boolean conflict = MatrixOperations.intersects(currentGameMatrix, getCurrentShape(), p.x, p.y);
        if(!conflict) currentOffset = p;;
    }

    public void movePieceRight() {
        Point p = new Point(currentOffset);
        p.translate(1,0);
        boolean conflict = MatrixOperations.intersects(currentGameMatrix, getCurrentShape(), p.x, p.y);
        if(!conflict)  currentOffset = p;
    }

    public Score getScore() {
        return score;
    }

    public ViewData getViewData() {
        return new ViewData(getCurrentShape(),
                currentOffset.x,
                currentOffset.y,
                pieceGenerator.getNextPiece().getPieceMatrix().get(0) );
    }

    public int[][] getBoardMatrix() {
        // Возвращаем текущую матрицу
        return this.currentGameMatrix;
    }

    public int[][] getCurrentShape() {
        // Возвращаем текущую форму фигуры
        return this.piece.getPieceMatrix().get(currentShape);
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
        currentOffset = new Point(4, 0);
    }

    public void mergePieceToBackground() {
        currentGameMatrix = MatrixOperations.merge(currentGameMatrix, getCurrentShape(), currentOffset.x, currentOffset.y);
    }

    public NextShapeInfo getNextShape() {
        int nextShape = currentShape;
        nextShape = ++nextShape % piece.getPieceMatrix().size();
        return new NextShapeInfo(piece.getPieceMatrix().get(nextShape), nextShape);
    }

    public void rotatePiece() {
        // Функция поворота фигуры
        NextShapeInfo nextShape = getNextShape();
        boolean conflict = MatrixOperations.intersects(currentGameMatrix,
                nextShape.getShape(),
                currentOffset.x,
                currentOffset.y);
        if (!conflict) setCurrentShape(nextShape.getPosition());
    }

    public void setCurrentShape(int currentShape) {
        this.currentShape = currentShape;
    }

    public ClearRow clearRows() {
        ClearRow clearRow = MatrixOperations.checkRemoving(currentGameMatrix);
        currentGameMatrix = clearRow.getClearedRowsMatrix();
        return clearRow;
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }


}
