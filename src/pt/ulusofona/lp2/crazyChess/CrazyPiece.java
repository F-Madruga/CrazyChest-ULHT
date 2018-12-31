package pt.ulusofona.lp2.crazyChess;

import java.util.ArrayList;
import java.util.List;

public abstract class CrazyPiece {

    protected int idPeca;
    protected int idTipo;
    protected int idEquipa;
    protected String alcunha;
    protected int x;
    protected int y;
    protected int xAnterior;
    protected int yAnterior;
    protected int ultimaInteracao;

    public CrazyPiece(int idPeca, int idTipo, int idEquipa, String alcunha) {
        this.idPeca = idPeca;
        this.idTipo = idTipo;
        this.idEquipa = idEquipa;
        this.alcunha = alcunha;
        this.x = -1;
        this.y = -1;
        this.xAnterior = -1;
        this.yAnterior = -1;
        this.ultimaInteracao = -1;
    }

    public int getIdTipo() {
        return this.idTipo;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public int getIdEquipa() {
        return this.idEquipa;
    }

    public void undo(int turno) {
        if (turno == ultimaInteracao) {
            this.x = xAnterior;
            this.y = yAnterior;
        }
    }

    public void colocarNoTabuleiro(int x, int y) {
        this.x = x;
        this.y = y;
        this.xAnterior = this.x;
        this.yAnterior = this.y;
    }

    public void alterarCoordenada(int x, int y, int turno) {
        this.xAnterior = this.x;
        this.yAnterior = this.y;
        this.x = x;
        this.y = y;
        this.ultimaInteracao = turno;
    }

    public int getId() {
        return this.idPeca;
    }

    protected abstract String getValorRelativo();

    protected abstract String getNome();

    public abstract boolean verificarSeMove(int xD, int yD, List<CrazyPiece> pecas, int turno);

    public abstract String getImagePNG();

    public List<String> darSugestoes(List<CrazyPiece> pecas, int turno, int tamanho) {
        List<String> sugestoes = new ArrayList<>();
        for (int x = 0; x < tamanho; x++) {
            for (int y = 0; y < tamanho; y++) {
                if (verificarSeMove(x, y, pecas, turno)) {
                    boolean podeMover = true;
                    for (CrazyPiece peca: pecas) {
                        if (peca.getX() == x && peca.getY() == y && peca.getIdEquipa() == this.idEquipa) {
                            podeMover = false;
                        }
                    }
                    if (podeMover) {
                        sugestoes.add(x + ", " + y);
                    }
                }
            }
        }
        return sugestoes;
    }

    @Override
    public String toString() {
        String coordenadas = " @ (n/a)";
        if (x != -1 && y != -1) {
            coordenadas = " @ (" + this.x + ", " + this.y + ")";
        }
        return this.idPeca + " | " + this.getNome() + " | " + this.getValorRelativo() + " | " + this.idEquipa + " | " + this.alcunha + coordenadas;
    }

    @Override
    public boolean equals(Object obj) {
        CrazyPiece peca = (CrazyPiece) obj;
        return this.idPeca == peca.getId();
    }

    public String getAlcunha() {
        return alcunha;
    }
}