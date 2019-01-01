package pt.ulusofona.lp2.crazyChess;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public abstract class CrazyPiece {

    protected int idPeca;
    protected int idTipo;
    protected int idEquipa;
    protected String alcunha;
    protected String coordenadas;
    protected String coordenadasAnterior;

    public CrazyPiece(int idPeca, int idTipo, int idEquipa, String alcunha) {
        this.idPeca = idPeca;
        this.idTipo = idTipo;
        this.idEquipa = idEquipa;
        this.alcunha = alcunha;
        this.coordenadas = "(n/a)";
        this.coordenadasAnterior = this.coordenadas;
    }

    @Override
    public boolean equals(Object obj) {
        CrazyPiece peca = (CrazyPiece) obj;
        return this.idPeca == peca.getId();
    }

    public int getId() {
        return idPeca;
    }

    public int getIdTipo() {
        return idTipo;
    }

    public int getIdEquipa() {
        return idEquipa;
    }

    public String getAlcunha() {
        return alcunha;
    }

    public void setCoordenadas(int x, int y) {
        this.coordenadasAnterior = this.coordenadas;
        this.coordenadas = "(" + x + ", " + y + ")";
    }

    public void resetCoordenadas() {
        this.coordenadasAnterior = this.coordenadas;
        this.coordenadas = "(n/a)";
    }

    public void undo() {
        this.coordenadas = this.coordenadasAnterior;
    }

    public List<String> darSugestao(int xO, int yO, Map<Integer, CrazyPiece> pecas, int [][] tabuleiro, int turno) {
        List<String> sugestoes = new ArrayList<>();
        if (Tabuleiro.existemCoordenadas(xO, yO, tabuleiro.length)) {
            if (tabuleiro[xO][yO] == this.idPeca) {
                for (int x = 0; x < tabuleiro.length; x++) {
                    for (int y = 0; y < tabuleiro[x].length; y++) {
                        if (tabuleiro[x][y] == 0) {
                            if (verificarSeMove(xO, yO, x, y, pecas, tabuleiro, turno)) {
                                sugestoes.add(x + ", " + y);
                            }
                        }
                        else if (pecas.get(tabuleiro[x][y]).getIdEquipa() != this.idEquipa) {
                            if (verificarSeMove(xO, yO, x, y, pecas, tabuleiro, turno)) {
                                sugestoes.add(x + ", " + y);
                            }
                        }
                    }
                }
            }
        }
        return sugestoes;
    }

    protected abstract String getValorRelativo();

    protected abstract String getNome();

    public abstract String getImagePNG();

    @Override
    public String toString() {
        return this.idPeca + " | " + this.getNome() + " | " + this.getValorRelativo() + " | " + this.idEquipa + " | " + this.alcunha + " @ " + this.coordenadas;
    }

    public abstract boolean verificarSeMove(int xO, int yO, int xD, int yD, Map<Integer, CrazyPiece> pecas, int [][] tabuleiro, int turno);

}
