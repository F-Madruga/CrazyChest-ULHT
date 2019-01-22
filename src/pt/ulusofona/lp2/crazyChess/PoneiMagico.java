package pt.ulusofona.lp2.crazyChess;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PoneiMagico extends CrazyPiece {

    public PoneiMagico(int idPeca, int idTipo, int idEquipa, String alcunha) {
        super(idPeca, idTipo, idEquipa, alcunha);
    }

    @Override
    protected int getValorRelativo(){
        return 5;
    }

    @Override
    protected String getNome(){
        return "Ponei Mágico";
    }

    @Override
    public String getImagePNG() {
        if (this.idEquipa == GestorDeJogo.PRETA) {
            return "preto_ponei.png";
        } else if (this.idEquipa == GestorDeJogo.BRANCA) {
            return "branco_ponei.png";
        } else {
            return null;
        }
    }

    @Override
    public boolean verificarSeMove(int xO, int yO, int xD, int yD, Map<Integer, CrazyPiece> pecas, int[][] tabuleiro, int turno) {
        if (move(xO, yO, xD, yD) && moveDiagonal(xO, yO, xD, yD) && (moveDentroLimite(xO, yO, xD, yD, 2) && !moveDentroLimite(xO, yO, xD, yD, 1))) {
            int direcaoH;
            int direcaoV;
            if (xO > xD) {
                direcaoH = -1;
            } else {
                direcaoH = 1;
            }
            if (yO > yD) {
                direcaoV = -1;
            } else {
                direcaoV = 1;
            }
            boolean primeiroCaminhoEPossivel = true;
            List<CrazyPiece> pecasNoCaminho = new ArrayList<>();
            pecasNoCaminho.addAll(getPecasNoCaminho(xO, yO, xD + direcaoH, yO, pecas, tabuleiro));
            pecasNoCaminho.addAll(getPecasNoCaminho(xD, yO - direcaoV, xD, yD, pecas, tabuleiro));
            if (pecasNoCaminho.isEmpty()) {
                return true;
            }
            else {
                for (CrazyPiece peca: pecasNoCaminho) {
                    if (peca.getIdTipo() == GestorDeJogo.REI) {
                        primeiroCaminhoEPossivel = false;
                    }
                }
            }
            if (primeiroCaminhoEPossivel) {
                return true;
            }
            List<CrazyPiece> pecasNoCaminho2 = new ArrayList<>();
            pecasNoCaminho2.addAll(getPecasNoCaminho(xO, yO, xO, yD + direcaoV, pecas, tabuleiro));
            pecasNoCaminho2.addAll(getPecasNoCaminho(xO - direcaoH, yD, xD, yD, pecas, tabuleiro));
            if (pecasNoCaminho2.isEmpty()) {
                return true;
            }
            else {
                for (CrazyPiece peca: pecasNoCaminho2) {
                    if (peca.getIdTipo() == GestorDeJogo.REI) {
                        return false;
                    }
                }
            }
            return true;
        }
        return false;
    }
}
