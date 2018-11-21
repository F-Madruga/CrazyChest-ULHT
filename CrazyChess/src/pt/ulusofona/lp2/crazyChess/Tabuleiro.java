package pt.ulusofona.lp2.crazyChess;

import java.util.ArrayList;
import java.util.List;

public class Tabuleiro {
    List<CrazyPiece> pecas;
    int tamanho;

    Tabuleiro (int tamanho) {
        this.tamanho = tamanho;
        this.pecas = new ArrayList<>();
    }

    public int getTamanho() {
        return tamanho;
    }

    public List<CrazyPiece> getPecas() {
        return pecas;
    }

    void adicionarPeca(CrazyPiece peca) {
        this.pecas.add(peca);
    }

    boolean coordenadasExitem(int x, int y) {
        if (x >= 0 && y >= 0 && x < this.getTamanho() - 1 && y < this.getTamanho() - 1) {
            return true;
        }
        else {
            return false;
        }
    }
}
