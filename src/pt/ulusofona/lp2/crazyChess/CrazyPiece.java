package pt.ulusofona.lp2.crazyChess;

public class CrazyPiece {
    int idPeca;
    int idTipo;
    int idEquipa;
    String alcunha;
    int x;
    int y;

    CrazyPiece(int idPeca, int idTipo, int idEquipa, String alcunha) {
        this.idPeca = idPeca;
        this.idTipo = idTipo;
        this.idEquipa = idEquipa;
        this.alcunha = alcunha;
    }

    void setCoordenadas(int x, int y) {
        this.x = x;
        this.y = y;
    }

    int getId() {
        return idPeca;
    }

    String getImagePNG() {
        return null;
    }

    @Override
    public String toString() {
        return this.idPeca + " | " + this.idTipo + " | " + this.idEquipa + " | " + this.alcunha + " @ (" + this.x+ ", " + this.y + ")";
    }
}
