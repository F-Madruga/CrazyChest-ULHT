package pt.ulusofona.lp2.crazyChess;

import java.util.ArrayList;
import java.util.List;

public class Tabuleiro {

    private int tamanho;
    private List<CrazyPiece> pecas;
    private GestorDeJogo gestor;
    private boolean fazerUndo;

    public Tabuleiro(int tamanho) {
        this.tamanho = tamanho;
        this.pecas = new ArrayList<>();
        this.gestor = new GestorDeJogo();
        this.fazerUndo = true;
    }

    public boolean existemCoordenadas(int x, int y) {
        if(x >= 0 && x < this.tamanho && y >= 0 && y < this.tamanho) {
            return true;
        }
        else {
            return false;
        }
    }

    public void acrescentaPeca(CrazyPiece peca) {
        this.pecas.add(peca);
    }

    public List<CrazyPiece> getPecas() {
        return this.pecas;
    }

    public CrazyPiece getPeca(int x, int y) {
        for (CrazyPiece peca: this.pecas) {
            if (peca.getX() == x && peca.getY() == y) {
                return peca;
            }
        }
        return null;
    }

    public void colocarNoTabuleiro(int id, int x, int y) {
        if (existemCoordenadas(x, y)) {
            for (CrazyPiece peca : this.pecas) {
                if (peca.getId() == id) {
                    peca.colocarNoTabuleiro(x, y);
                    if (peca.getIdTipo() == 0) {
                        gestor.adicionarPeca(peca);
                    }
                }
            }
        }
    }

    public int getTamanho() {
        return this.tamanho;
    }

    public boolean processaJogada(int xO, int yO, int xD, int yD) {
        if (existemCoordenadas(xO, yO) && existemCoordenadas(xD, yD)) {
            CrazyPiece origem = getPeca(xO, yO);
            if (origem != null && origem.getIdEquipa() == gestor.quemEstaAjogar() && origem.verificarSeMove(xD, yD, this.pecas, this.gestor.getTurno())) {
                CrazyPiece destino = getPeca(xD, yD);
                if (destino == null || destino.getIdEquipa() != gestor.quemEstaAjogar()) {
                    if (destino != null) {
                        destino.alterarCoordenada(-1, -1, this.gestor.getTurno());
                        this.gestor.adicionarCaptura(destino);
                    }
                    else {
                        this.gestor.naoHouveCaptura();
                    }
                    origem.alterarCoordenada(xD, yD, this.gestor.getTurno());
                    this.gestor.validaJogada();
                    this.fazerUndo = true;
                    return true;
                }
            }
        }
        this.gestor.invalidaJogada();
        return false;
    }

    public boolean possoTerminarJogo() {
        return gestor.possoTerminar();
    }

    public int quemEstaAJogar() {
        return this.gestor.quemEstaAjogar();
    }

    public List<String> getResultado() {
        return this.gestor.getResultado();
    }

    public void undo() {
        if (fazerUndo) {
            this.gestor.undo();
            for (CrazyPiece peca : this.pecas) {
                peca.undo(this.gestor.getTurno());
            }
            fazerUndo = false;
        }
    }

    public List<String> obterSugestoesJogada(int xO, int yO) {
        CrazyPiece peca = getPeca(xO, yO);
        if (peca != null && peca.getIdEquipa() == quemEstaAJogar()) {
            return peca.darSugestoes(this.pecas, this.gestor.getTurno(), this.tamanho);
        }
        else {
            return new ArrayList<>();
        }
    }
}
