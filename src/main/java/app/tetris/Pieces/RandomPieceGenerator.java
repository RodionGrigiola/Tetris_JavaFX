package app.tetris.Pieces;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class RandomPieceGenerator {

    private final List<Piece> pieceList; // Лист всех фигур
    private final Deque<Piece> nextPieces = new ArrayDeque<>(); // Дек некст фигур

    public RandomPieceGenerator() {
        this.pieceList = new ArrayList<>();
        this.pieceList.add(new IPiece());
        this.pieceList.add(new JPiece());
        this.pieceList.add(new LPiece());
        this.pieceList.add(new SquarePiece());
        this.pieceList.add(new SPiece());
        this.pieceList.add(new TPiece());
        this.pieceList.add(new ZPiece());
        nextPieces.add(pieceList.get(ThreadLocalRandom.current().nextInt(pieceList.size())));
        nextPieces.add(pieceList.get(ThreadLocalRandom.current().nextInt(pieceList.size())));
    }

    public Piece getNextPiece() {
        return nextPieces.peek();
    }

    public Piece getPiece() {
        if(nextPieces.size() <= 1) {
            // в деке всегда должно что-то быть, если поллим фигуру, то добавляем новую
            nextPieces.add(pieceList.get(ThreadLocalRandom.current().nextInt(pieceList.size())));
        }
        return nextPieces.poll();
    }


}
