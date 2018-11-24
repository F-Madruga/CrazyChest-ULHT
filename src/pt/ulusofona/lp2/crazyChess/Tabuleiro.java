package pt.ulusofona.lp2.crazyChess;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Tabuleiro {
    int tamanho;
    List<CrazyPiece> pecas;
    Map<Integer,List<Boolean>> jogadas;
    int turno;
    int numTurnosSemCapturas;

    Tabuleiro(int tamanho) {
        this.tamanho = tamanho;
        this.pecas = new ArrayList<>();
        this.turno = 0;
        this.jogadas = new HashMap<>();
        this.numTurnosSemCapturas = 0;
    }

    void inserirPeca(CrazyPiece peca, int x, int y) {
        peca.setCoordenadas(x, y);
        this.pecas.add(peca);
    }

    List<CrazyPiece> getPecas() {
        return this.pecas;
    }

    CrazyPiece getPeca(int x, int y) {
        for (CrazyPiece peca: this.pecas) {
            if (peca.getX() == x && peca.getY() == y) {
                return peca;
            }
        }
        return null;
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

    boolean processaJogada(int xO, int yO, int xD, int yD) {
        if (xO >= 0 && xO < this.tamanho && xD >= 0 && xD < this.tamanho && yO >=0 && yO < this.tamanho && yD >= 0 && yD < this.tamanho) {
            CrazyPiece origem = this.getPeca(xO, yO);
            if (origem != null && origem.getIdEquipa() == this.getIdEquipaAJogar()) {
                CrazyPiece destino = this.getPeca(xD,yD);
                if (destino == null) {
                    if (origem.move(xD, yD)) {
                        numTurnosSemCapturas++;
                        turno++;
                        if (!this.jogadas.containsKey(origem.getIdEquipa())) {
                            this.jogadas.put(origem.getIdEquipa(),new ArrayList<>());
                        }
                        this.jogadas.get(origem.getIdEquipa()).add(true);
                        return true;
                    }
                } else if (origem.getIdEquipa() != destino.getIdEquipa()) {
                    if (origem.move(xD, yD)) {
                        destino.capturada();
                        numTurnosSemCapturas = 0;
                        turno++;
                        if (!this.jogadas.containsKey(origem.getIdEquipa())) {
                            this.jogadas.put(origem.getIdEquipa(),new ArrayList<>());
                        }
                        this.jogadas.get(origem.getIdEquipa()).add(true);
                        return true;
                    }
                }
                else {
                    if (!this.jogadas.containsKey(origem.getIdEquipa())) {
                        this.jogadas.put(origem.getIdEquipa(),new ArrayList<>());
                    }
                    this.jogadas.get(origem.getIdEquipa()).add(false);
                }
            }
        }
        return false;
    }

   /* boolean jogoTerminado() {

    }*/
}