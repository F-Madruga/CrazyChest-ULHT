package pt.ulusofona.lp2.crazyChess;

import java.util.ArrayList;
import java.util.List;

public class Tabuleiro {
    int tamanho;
    CrazyPiece pecas [][];
    List<CrazyPiece> pecasPretasCapturadas;
    List<CrazyPiece> pecasBrancasCapturadas;
    int turno;
    int resultado;

    Tabuleiro(int tamanho) {
        this.tamanho = tamanho;
        this.turno = 1;
        this.pecas = new CrazyPiece[tamanho][tamanho];
        this.pecasPretasCapturadas = new ArrayList<>();
        this.pecasBrancasCapturadas = new ArrayList<>();
        this.resultado = -1;
    }

    void inserirPeca(CrazyPiece peca, int x, int y) {
        if (peca.getIdEquipa() == 0) {
        }
        if (peca.getIdEquipa() == 1) {
        }
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

    CrazyPiece getPeca(int x, int y) {
        return this.pecas[x][y];
    }

    int getTamanho() {
        return tamanho;
    }

    int getIdEquipaAJogar() {
        if (turno % 2 == 0) {
            return 0;
        }
        else {
            return 1;
        }
    }

    void removePeca(CrazyPiece peca) {
        if (peca.idEquipa == 0) {
            this.pecasPretasCapturadas.add(peca);
        }
        if (peca.idEquipa == 1) {
            this.pecasBrancasCapturadas.add(peca);
        }
    }

    boolean processaJogada(int xO, int yO, int xD, int yD) {
        if (xO >= 0 && xD < this.tamanho && yO >=0 && yD < this.tamanho) {
            if (this.getPeca(xD,yD) == null) {
                return this.getPeca(xO, yO).move(xD, yD);
            }
            else {
                if (this.getPeca(xO,yO).idEquipa != this.getPeca(xD,yD).idEquipa) {
                    this.removePeca(this.getPeca(xD,yD));
                    return this.getPeca(xO,yO).move(xD,yD);
                }
            }
        }
        return false;
    }
}