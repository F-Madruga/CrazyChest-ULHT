package pt.ulusofona.lp2.crazyChess;

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
        if (this.idEquipa == GestorDeJogo.PRETA) {
            return "preto_rei.png";
        }
        else if (this.idEquipa == GestorDeJogo.BRANCA) {
            return "branco_rei.png";
        }
        else {
            return null;
        }
    }
}

