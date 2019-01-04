package pt.ulusofona.lp2.crazyChess;

import java.util.Map;

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

    @Override
    public boolean verificarSeMove(int xO, int yO, int xD, int yD, Map<Integer, CrazyPiece> pecas, int[][] tabuleiro, int turno) {
        if (move(xO, yO, xD, yD) && (moveHorizontal(yO, yD) || moveVertical(xO, xD) || moveDiagonal(xO, yO, xD, yD)) && moveDentroLimite(xO, yO, xD, yD, 1)) {
            return true;
        }
        return false;
    }
}
