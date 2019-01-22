package pt.ulusofona.lp2.crazyChess;

import java.util.List;
import java.util.Map;

public class TorreH extends CrazyPiece {

    public TorreH(int idPeca, int idTipo, int idEquipa, String alcunha) {
        super(idPeca, idTipo, idEquipa, alcunha);
    }

    @Override
    protected String getNome() {
        return "TorreH";
    }

    @Override
    protected int getValorRelativo() {
        return 3;
    }

    @Override
    public String getImagePNG() {
        if (this.idEquipa == GestorDeJogo.PRETA) {
            return "preto_torreH.png";
        }
        else if (this.idEquipa == GestorDeJogo.BRANCA) {
            return "branco_torreH.png";
        }
        else {
            return null;
        }
    }

    @Override
    public boolean verificarSeMove(int xO, int yO, int xD, int yD, Map<Integer, CrazyPiece> pecas, int[][] tabuleiro, int turno) {
        if (move(xO, yO, xD, yD) && moveHorizontal(yO, yD)) {
            List<CrazyPiece> pecasNoCaminho = getPecasNoCaminho(xO, yO, xD, yD, pecas, tabuleiro);
            if (pecasNoCaminho.isEmpty()) {
                return true;
            }
        }
        return false;
    }
}
