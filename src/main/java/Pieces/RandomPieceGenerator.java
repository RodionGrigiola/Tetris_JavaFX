package Pieces;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class RandomPieceGenerator {

    private final List<Piece> pieceList;
    private final Deque<Piece> nextPieces = new ArrayDeque<>();

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

    public Piece getPiece() {
        return nextPieces.poll();
    }





}
