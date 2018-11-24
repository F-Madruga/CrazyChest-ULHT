package pt.ulusofona.lp2.crazyChess;

import java.util.ArrayList;
import java.util.List;

public class Tabuleiro {
    int tamanho;
    List<CrazyPiece> pecas;

    Tabuleiro(int tamanho) {
        this.tamanho = tamanho;
        this.pecas = new ArrayList<>();
    }

    void inserirPeca(CrazyPiece peca, int x, int y) {
        if (x < this.tamanho && x >= 0 && y < this.tamanho && y >= 0) {
            peca.setCoordenadas(x, y);
            this.pecas.add(peca);
        }
    }

    List<CrazyPiece> getPecas() {
        List<CrazyPiece> pecasEmJogo = new ArrayList<>();
        return this.pecas;
    }

    CrazyPiece getPeca(int x, int y) {
        for (CrazyPiece peca: this.pecas) {
            if (peca != null && peca.getX() == x && peca.getY() == y) {
                return peca;
            }
        }
        return null;
    }

    int getTamanho() {
        return this.tamanho;
    }

    boolean existemCoordenadas(int x, int y) {
        if(x >= 0 && x < this.tamanho && y >= 0 && y < this.tamanho) {
            return true;
        }
        else {
            return false;
        }
    }
}