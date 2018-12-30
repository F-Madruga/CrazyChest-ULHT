package pt.ulusofona.lp2.crazyChess;

import java.util.ArrayList;
import java.util.List;

public class Lebre extends CrazyPiece {

    public Lebre(int idPeca, int idTipo, int idEquipa, String alcunha) {
        super(idPeca, idTipo, idEquipa, alcunha);
    }

    protected String getValorRelativo(){
        return "2";
    }

    protected String getNome(){
        return "Lebre";
    }

    public boolean verificarSeMove(int xD, int yD, List<CrazyPiece> pecas, int turno) {
        if (turno % 2 == 0 && (this.x != xD || this.y != yD) && (this.x + xD == this.y + yD || this.x + xD == -(this.y + yD)) && (this.x - xD == 1 || this.x - xD == -1)) {
            return true;
        } else {
            return false;
        }
    }

    public String getImagePNG(){
        return null;
    }
}

