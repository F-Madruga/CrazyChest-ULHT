package pt.ulusofona.lp2.crazyChess;

import java.util.ArrayList;
import java.util.List;

public class Tabuleiro {
    private int tamanho;
    private List<CrazyPiece> pecas;

    public Tabuleiro(int tamanho) {
        this.tamanho = tamanho;
        this.pecas = new ArrayList<>();
    }

    public void inserirPeca(CrazyPiece peca) {
        this.pecas.add(peca);
    }

    public List<CrazyPiece> getPecas() {
        return this.pecas;
    }

    public CrazyPiece getPecaById(int idPeca) {
        for (CrazyPiece peca: this.pecas) {
            if(peca.getId() == idPeca) {
                return peca;
            }
        }
        return null;
    }

    public CrazyPiece getPeca(int x, int y) {
        for (CrazyPiece peca: this.pecas) {
            if (peca != null && peca.getX() == x && peca.getY() == y) {
                return peca;
            }
        }
        return null;
    }

    public int getTamanho() {
        return this.tamanho;
    }

    public boolean existemCoordenadas(int x, int y) {
        if(x >= 0 && x < this.tamanho && y >= 0 && y < this.tamanho) {
            return true;
        }
        else {
            return false;
        }
    }

    public void removerPeca(CrazyPiece peca) {
        peca.setUltimaInteracao(GestorDeJogo.turno);
        peca.setCoordenadas(-1, -1);
    }

    public void anularJogadaAnterior() {
        for (CrazyPiece peca: this.pecas) {
            if (peca.getUltimaInteracao() == GestorDeJogo.turno) {
                peca.undo();
            }
        }
    }
}