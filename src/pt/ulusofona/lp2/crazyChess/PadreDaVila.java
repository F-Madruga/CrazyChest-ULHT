package pt.ulusofona.lp2.crazyChess;

import java.util.List;
import java.util.Map;

public class PadreDaVila extends CrazyPiece {

    public PadreDaVila(int idPeca, int idTipo, int idEquipa, String alcunha) {
        super(idPeca, idTipo, idEquipa, alcunha);
    }

    @Override
    protected int getValorRelativo() {
        return 3;
    }

    @Override
    protected String getNome() {
        return "Padre da Vila";
    }

    @Override
    public String getImagePNG() {
        if (this.idEquipa == GestorDeJogo.PRETA) {
            return "preto_padre.png";
        }
        else if (this.idEquipa == GestorDeJogo.BRANCA) {
            return "branco_padre.png";
        }
        else {
            return null;
        }
    }

    @Override
    public boolean verificarSeMove(int xO, int yO, int xD, int yD, Map<Integer, CrazyPiece> pecas, int[][] tabuleiro, int turno) {
        if (move(xO, yO, xD, yD) && moveDiagonal(xO, yO, xD, yD) && moveDentroLimite(xO, yO, xD, yD, 3)) {
            List<CrazyPiece> pecasNoCaminho = getPecasNoCaminho(xO, yO, xD, yD, pecas, tabuleiro);
            if (pecasNoCaminho.isEmpty()) {
                List<CrazyPiece> pecasAvoltaDestino = getPecasNumRaio(xD, yD, 1, tabuleiro, pecas);
                if (pecasAvoltaDestino.isEmpty()) {
                    return true;
                }
                else {
                    for (CrazyPiece peca: pecasAvoltaDestino) {
                        if (peca.getIdEquipa() != this.idEquipa && (peca.getIdTipo() == GestorDeJogo.RAINHA || peca.getIdTipo() == GestorDeJogo.JOKER)) {
                            if (peca.getIdTipo() == GestorDeJogo.RAINHA) {
                                return false;
                            }
                            else {
                                Joker joker = new Joker(peca.getId(), GestorDeJogo.JOKER, peca.getIdEquipa(), peca.getAlcunha());
                                if (joker.getMascara().getIdTipo() == GestorDeJogo.RAINHA) {
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
