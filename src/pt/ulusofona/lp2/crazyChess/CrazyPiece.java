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

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    void setCoordenadas(int x, int y) {
        this.posX = x;
        this.posY = y;
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

    boolean movePiece(int xO, int yO, int xD, int yD) {
        if (((xD == xO + 1 || xD == xO - 1) && yO == yD) || (yD == yO + 1 || (yD == yO - 1) && xO == xD) || (xD == xO + 1 || xD == xO - 1) && (yD == yO + 1 || (yD == yO - 1))) {
            this.setCoordenadas(xD,xO);
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public String toString() {
        if (this.isCapturada()) {
            return this.id + " | " + this.idTipo + " | " + this.idEquipa + " | " + this.alcunha + " @ (" + this.posX + ", " + this.posY + ")";
        }
        else return this.id + " | " + this.idTipo + " | " + this.idEquipa + " | " + this.alcunha + " @ (n/a)";
    }
}
