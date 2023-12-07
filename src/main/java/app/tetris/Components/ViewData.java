package app.tetris.Components;

public class ViewData {

    private final int[][] nextPieceData;
    private final int[][] pieceData;
    private final int xPosition;
    private final int yPosition;

    public ViewData(int[][] pieceData,
                    int xPosition,
                    int yPosition,
                    int[][] nextPieceData
    ) {
        this.pieceData = pieceData;
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.nextPieceData = nextPieceData;
    }


    public int[][] getNextPieceData() { return nextPieceData; }
    public int[][] getPieceData() {
        return pieceData;
    }
    public int getXPosition() {
        return xPosition;
    }
    public int getYPosition() {
        return yPosition;
    }

}
