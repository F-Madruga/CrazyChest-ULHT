package pt.ulusofona.lp2.crazyChess;

import java.util.ArrayList;
import java.util.List;

public class Tabuleiro {
    int tamanho;
    CrazyPiece pecas [][];
    int turno;

    Tabuleiro(int tamanho) {
        this.tamanho = tamanho;
        this.turno = 1;
        this.pecas = new CrazyPiece[tamanho][tamanho];
    }

    void inserirPeca(CrazyPiece peca, int x, int y) {
        this.pecas[x][y] = peca;
    }

    List<CrazyPiece> getPecas() {
        List<CrazyPiece> pieces = new ArrayList<>();
        for (int coluna = 0; coluna < this.tamanho; coluna++) {
            for (int linha = 0; linha < this.tamanho; linha++) {
                if (this.pecas[coluna][linha] != null) {
                    pieces.add(this.pecas[coluna][linha]);
                }
            }
        }
        return pieces;
    }

    int getTamanho() {
        return tamanho;
    }

    int getIdEquipaAJogar() {
        if (turno % 2 == 0) {
            return 1;
        }
        else {
            return 0;
        }
    }
}