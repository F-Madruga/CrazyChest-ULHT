package pt.ulusofona.lp2.crazyChess;

import java.util.List;

public class PoneiMagico extends CrazyPiece {

    public PoneiMagico(int idPeca, int idTipo, int idEquipa, String alcunha) {
        super(idPeca, idTipo, idEquipa, alcunha);
    }

    @Override
    public boolean move(int xD, int yD) {
        if ((this.x - xD == -2 && this.y - yD == -2) || (this.x - xD == -2 && this.y - yD == 2) || (this.x - xD == 2 && this.y - yD == -2) || (this.x - xD == 2 && this.y - yD == 2)) {
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
        return "Ponei Mágico";
    }

    @Override
    public String getImagePNG() {
        if (this.idEquipa == 0) {
            return "trihard.png"; // mudar nome
        } else if (this.idEquipa == 1) {
            return "kappa.png"; // mudar nome
        } else {
            return null;
        }
    }
}
