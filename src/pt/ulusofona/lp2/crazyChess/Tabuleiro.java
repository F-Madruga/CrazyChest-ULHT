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

    void inserirPeca(CrazyPiece peca) {
        this.pecas.add(peca);
    }

    List<CrazyPiece> getPecas() {
        return this.pecas;
    }

    CrazyPiece getPecaById(int idPeca) {
        for (CrazyPiece peca: this.pecas) {
            if(peca.getId() == idPeca) {
                return peca;
            }
        }
        return null;
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

    void removerPeca(CrazyPiece peca) {
        peca.setCoordenadas(-1, -1);
    }

}