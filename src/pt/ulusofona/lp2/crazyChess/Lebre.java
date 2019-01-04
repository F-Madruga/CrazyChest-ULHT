package pt.ulusofona.lp2.crazyChess;

import java.util.Map;

public class Lebre extends CrazyPiece {

    public Lebre(int idPeca, int idTipo, int idEquipa, String alcunha) {
        super(idPeca, idTipo, idEquipa, alcunha);
    }

    @Override
    protected String getValorRelativo(){
        return "2";
    }

    @Override
    protected String getNome(){
        return "Lebre";
    }

    @Override
    public String getImagePNG() {
        if (this.idEquipa == GestorDeJogo.PRETA) {
            return "preto_lebre.png";
        }
        else if (this.idEquipa == GestorDeJogo.BRANCA) {
            return "branco_lebre.png";
        }
        else {
            return null;
        }
    }

    @Override
    public boolean verificarSeMove(int xO, int yO, int xD, int yD, Map<Integer, CrazyPiece> pecas, int[][] tabuleiro, int turno) {
        if (turno % 2 == 0 && move(xO, yO, xD, yD) && moveDiagonal(xO, yO, xD, yD) && moveDentroLimite(xO, yO, xD, yD, 1)){
            return true;
        }
        return false;
    }
}
