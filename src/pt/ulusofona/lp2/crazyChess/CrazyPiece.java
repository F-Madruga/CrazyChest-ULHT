package pt.ulusofona.lp2.crazyChess;

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

    public CrazyPiece(int idPeca, int idTipo, int idEquipa, String alcunha) {
        this.idPeca = idPeca;
        this.idTipo = idTipo;
        this.idEquipa = idEquipa;
        this.alcunha = alcunha;
        this.x = -1;
        this.y = -1;
        this.xAnterior = -1;
        this.yAnterior = -1;
    }

    public void setCoordenadas(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getId() {
        return idPeca;
    }

    public int getIdEquipa() {
        return idEquipa;
    }

    protected void atualizarAnterior() {
        this.xAnterior = x;
        this.yAnterior = y;
    }

    public void undo() {
        if (this.xAnterior > 0 && this.yAnterior > 0) {
            this.x = xAnterior;
            this.y = yAnterior;
            this.xAnterior = -1;
            this.yAnterior = -1;
        }
    }

    @Override
    public String toString() {
        if (x == -1 && y == -1) {
            return this.idPeca + " | " + this.getNome() + " | " + this.idEquipa + " | " + this.alcunha + " @ (n/a)";
        }
        else {
            return this.idPeca + " | " + this.getNome() + " | " + this.idEquipa + " | " + this.alcunha + " @ (" + this.x + ", " + this.y + ")";
        }
    }

    public abstract String getImagePNG();

    abstract boolean move(int xD, int yD);

    abstract List<String> darSugestao();

    abstract String getNome();
}