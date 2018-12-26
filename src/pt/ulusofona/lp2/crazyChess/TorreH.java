package pt.ulusofona.lp2.crazyChess;

import java.util.List;

public class TorreH extends CrazyPiece {

    public TorreH(int idPeca, int idTipo, int idEquipa, String alcunha) {
        super(idPeca, idTipo, idEquipa, alcunha);
    }

    @Override
    public boolean move(int xD, int yD) {
        //TODO
    }

    @Override
    public List<String> darSugestao() {
        //TODO
        return null;
    }

    @Override
    public String getNome() {
        return "TorreH";
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
