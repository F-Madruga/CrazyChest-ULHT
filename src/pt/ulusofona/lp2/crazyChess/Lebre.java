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

    public List<String> darSugestoes(List<CrazyPiece> pecas, int turno, int tamanho) {
        List<String> sugestoes = new ArrayList<>();
        if (turno % 2 == 0) {
            for (int x = -1; x <= 1; x++) {
                for (int y = -1; y <= 1; y++) {
                    if (x != 0 && x == y || x == -y) {
                        sugestoes.add(this.x + x + ", " + this.y + y);
                    }
                }
            }
        }
        return sugestoes;
    }
}

