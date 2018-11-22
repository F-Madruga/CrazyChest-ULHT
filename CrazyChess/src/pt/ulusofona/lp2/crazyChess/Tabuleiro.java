package pt.ulusofona.lp2.crazyChess;

import java.util.ArrayList;
import java.util.List;

public class Tabuleiro {
    int tamanho;
    List<CrazyPiece> pieces;

    Tabuleiro (int tamanho) {
        this.tamanho = tamanho;
        this.pieces = new ArrayList<>();
    }

    public int getTamanho() {
        return tamanho;
    }

    public List<CrazyPiece> getPieces() {
        return pieces;
    }

    void adicionarPeca(CrazyPiece peca) {
        this.pieces.add(peca);
    }

    boolean coordenadasExitem(int x, int y) {
        if (x >= 0 && y >= 0 && x < this.getTamanho() - 1 && y < this.getTamanho() - 1) {
            return true;
        }
        else {
            return false;
        }
    }

    boolean existePecaDaEquipaAtiva(int idEquipa, int x, int y) {
        for (CrazyPiece piece: this.pieces) {
            if (piece.getPosX() == x && piece.getPosY() == y) {
                return true;
            }
        }
        return false;
    }
}
