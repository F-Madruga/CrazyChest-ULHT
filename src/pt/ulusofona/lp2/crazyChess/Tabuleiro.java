package pt.ulusofona.lp2.crazyChess;

import java.util.ArrayList;
import java.util.List;

public class Tabuleiro {
    int tamanho;
    List<CrazyPiece> pecas;
    int turno;

    Tabuleiro(int tamanho) {
        this.tamanho = tamanho;
        this.turno = 1;
        this.pecas = new ArrayList<>();
    }

    public List<CrazyPiece> getPecas() {
        return pecas;
    }

    public int getTamanho() {
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