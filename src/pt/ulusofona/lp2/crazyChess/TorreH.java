package pt.ulusofona.lp2.crazyChess;

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
    protected String getValorRelativo() {
        return "3";
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
        if (yO == yD && xO != xD) {
            int direcao = 1;
            if (xO > xD) {
                direcao = -1;
            }
            while (xO != xD) {
                if (Tabuleiro.existemCoordenadas(xO, yO, tabuleiro.length)) {
                    if (tabuleiro[xO][yO] != 0 && tabuleiro[xO][yO] != this.idPeca) {
                        return false;
                    }
                }
                xO += direcao;
            }
            return true;
        }
        return false;
    }
}
