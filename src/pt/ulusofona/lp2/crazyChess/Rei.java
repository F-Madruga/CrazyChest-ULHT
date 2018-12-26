package pt.ulusofona.lp2.crazyChess;

import java.util.List;

public class Rei extends CrazyPiece {

    public Rei(int idPeca, int idTipo, int idEquipa, String alcunha) {
        super(idPeca, idTipo, idEquipa, alcunha);
    }

    @Override
    public boolean move(int xD, int yD) {
        if ((this.x - xD == 1 || this.x - xD == -1 || this.y - yD == 1 || this.y - yD == -1) && (this.x != xD || this.y != yD)) {
            atualizarAnterior();
            this.setCoordenadas(xD, yD);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public List<String> darSugestao() {
        //TODO
        return null;
    }

    @Override
    public String getNome() {
        return "Rei";
    }

    @Override
    public String getImagePNG() {
        if (this.idEquipa == 0) {
            return "trihard.png";
        } else if (this.idEquipa == 1) {
            return "kappa.png";
        } else {
            return null;
        }
    }
}
