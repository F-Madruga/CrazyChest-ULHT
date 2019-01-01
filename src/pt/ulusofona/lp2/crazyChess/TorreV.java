package pt.ulusofona.lp2.crazyChess;

import java.util.Map;

public class TorreV extends CrazyPiece {

    public TorreV(int idPeca, int idTipo, int idEquipa, String alcunha) {
        super(idPeca, idTipo, idEquipa, alcunha);
    }

    @Override
    protected String getValorRelativo() {
        return "3";
    }

    @Override
    protected String getNome() {
        return "TorreV";
    }

    @Override
    public String getImagePNG() {
        if (this.idEquipa == GestorDeJogo.PRETA) {
            return "preto_torreV.png";
        }
        else if (this.idEquipa == GestorDeJogo.BRANCA) {
            return "branco_torreV.png";
        }
        else {
            return null;
        }
    }

    @Override
    public boolean verificarSeMove(int xO, int yO, int xD, int yD, Map<Integer, CrazyPiece> pecas, int[][] tabuleiro, int turno) {
        if (xO == xD && yO != yD) {
            int direcao = 1;
            if (yO > yD) {
                direcao = -1;
            }
            while (yO != yD) {
                if (Tabuleiro.existemCoordenadas(xO, yO, tabuleiro.length)) {
                    if (tabuleiro[xO][yO] != 0 && tabuleiro[xO][yO] != this.idPeca) {
                        return false;
                    }
                }
                yO += direcao;
            }
            return true;
        }
        return false;
    }
}
