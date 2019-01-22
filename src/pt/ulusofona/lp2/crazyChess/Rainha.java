package pt.ulusofona.lp2.crazyChess;

import java.util.List;
import java.util.Map;

public class Rainha extends CrazyPiece {

    public Rainha(int idPeca, int idTipo, int idEquipa, String alcunha) {
        super(idPeca, idTipo, idEquipa, alcunha);
    }

    @Override
    protected int getValorRelativo() {
        return 8;
    }

    @Override
    protected String getNome() {
        return "Rainha";
    }

    @Override
    public String getImagePNG() {
        if (this.idEquipa == GestorDeJogo.PRETA) {
            return "preto_rainha.png";
        } else if (this.idEquipa == GestorDeJogo.BRANCA) {
            return "branco_rainha.png";
        } else {
            return null;
        }
    }

    @Override
    public boolean verificarSeMove(int xO, int yO, int xD, int yD, Map<Integer, CrazyPiece> pecas, int[][] tabuleiro, int turno) {
        if (move(xO, yO, xD, yD) && moveDentroLimite(xO, yO, xD, yD, 5) && (moveVertical(xO, xD) || moveHorizontal(yO, yD) || moveDiagonal(xO, yO, xD, yD))) {
            if (tabuleiro[xD][yD] != 0) {
                if (pecas.get(tabuleiro[xD][yD]).getIdTipo() == this.idTipo) {
                    return false;
                }
            }
            List<CrazyPiece> pecasNoCaminho = getPecasNoCaminho(xO, yO, xD, yD, pecas, tabuleiro);
            if (pecasNoCaminho.isEmpty()) {
                List<CrazyPiece> pecasAvoltaDestino = getPecasNumRaio(xD, yD, 1, tabuleiro, pecas);
                if (pecasAvoltaDestino.isEmpty()) {
                    return true;
                }
                else {
                    for (CrazyPiece peca: pecasAvoltaDestino) {
                        if (peca.getIdEquipa() != this.idEquipa && (peca.getIdTipo() == GestorDeJogo.PADREDAVILA || peca.getIdTipo() == GestorDeJogo.JOKER)) {
                            if (peca.getIdTipo() == GestorDeJogo.PADREDAVILA) {
                                return false;
                            }
                            else {
                                Joker joker = new Joker(peca.getId(), GestorDeJogo.JOKER, peca.getIdEquipa(), peca.getAlcunha());
                                if (joker.getMascara().getIdTipo() == GestorDeJogo.PADREDAVILA) {
                                    return false;
                                }
                            }
                        }
                    }
                }
                return true;
            }
        }
        return false;
    }
}