package pt.ulusofona.lp2.crazyChess;

import java.util.ArrayList;
import java.util.List;

public class Rei extends CrazyPiece {

    public Rei(int idPeca, int idTipo, int idEquipa, String alcunha) {
        super(idPeca, idTipo, idEquipa, alcunha);
    }

    @Override
    protected String getValorRelativo() {
        return "(infinito)";
    }

    @Override
    protected String getNome() {
        return "Rei";
    }

    @Override
    public boolean verificarSeMove(int xD, int yD, List<CrazyPiece> pecas, int turno) {
        if ((this.x - xD == 1 && (this.y - yD <= 1 && this.y - yD >= -1)) || (this.x - xD == 0 && (this.y - yD == 1 || this.y - yD == -1)) || (this.x - xD == -1 && (this.y - yD <= 1 && this.y - yD >= -1))) {
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public String getImagePNG() {
        if (this.idEquipa == GestorDeJogo.preta) {
            return "trihard.png";
        } else if (this.idEquipa == GestorDeJogo.branca) {
            return "kappa.png";
        } else {
            return null;
        }
    }
}

