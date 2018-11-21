package pt.ulusofona.lp2.crazyChess;

public class CrazyPiece {
    int id;
    int idTipo;
    int idEquipa;
    String alcunha;
    String imagePNG;
    int posX;
    int posY;
    boolean capturada;

    CrazyPiece(int id, int idTipo, int idEquipa, String alcunha) {
        this.id = id;
        this.idTipo = idTipo;
        this.idEquipa = idEquipa;
        this.alcunha = alcunha;
        this.capturada = false;
        if(idEquipa == 0) {
            this.imagePNG = null;
        }
        if (idEquipa == 1) {
            this.imagePNG = null;
        }
    }

    public boolean isCapturada() {
        return capturada;
    }

    public int getIdEquipa() {
        return idEquipa;
    }

    public String getAlcunha() {
        return alcunha;
    }

    public int getID() {
        return id;
    }

    public String getImagePNG() {
        return imagePNG;
    }

    @Override
    public String toString() {
        if (this.isCapturada()) {
            return this.id + " | " + this.idTipo + " | " + this.idEquipa + " | " + this.alcunha + " @ (" + this.posX + ", " + this.posY + ")";
        }
        else return this.id + " | " + this.idTipo + " | " + this.idEquipa + " | " + this.alcunha + " @ (n/a)";
    }
}
