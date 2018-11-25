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
        this.x = -1;
        this.y = -1;
    }

    void setCoordenadas(int x, int y) {
        this.x = x;
        this.y = y;
    }

    int getX() {
        return x;
    }

    int getY() {
        return y;
    }

    public int getId() {
        return idPeca;
    }

    int getIdEquipa() {
        return idEquipa;
    }

    public String getImagePNG() {
        if (this.idEquipa == 0) {
            return "trihard.png";
        }
        else if (this.idEquipa == 1) {
            return "kappa.png";
        }
        else {
            return null;
        }
    }

    boolean move(int xD, int yD) {
        if ((this.x - xD == 1 || this.x - xD == -1 || this.y - yD == 1 || this.y - yD == -1) && (this.x != xD || this.y != yD)) {
            this.setCoordenadas(xD,yD);
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public String toString() {
        if (x == -1 && y == -1) {
            return this.idPeca + " | " + this.idTipo + " | " + this.idEquipa + " | " + this.alcunha + " @ (n/a)";
        }
        else {
            return this.idPeca + " | " + this.idTipo + " | " + this.idEquipa + " | " + this.alcunha + " @ (" + this.x + ", " + this.y + ")";
        }
    }
}
