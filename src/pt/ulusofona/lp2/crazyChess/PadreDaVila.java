package pt.ulusofona.lp2.crazyChess;

import java.util.Map;

public class PadreDaVila extends CrazyPiece {

    public PadreDaVila(int idPeca, int idTipo, int idEquipa, String alcunha) {
        super(idPeca, idTipo, idEquipa, alcunha);
    }

    @Override
    protected String getValorRelativo() {
        return "3";
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
        if ((xO - xD <= 3 && xO - xD >= -3) && (yO - yD <= 3 && yO - yD >= -3) && (xO - xD == yO - yD || xO - xD == -(yO - yD))) {
            for (int x = -1; x <= 1; x++) {
                for (int y = -1; y <= 1; y++) {
                    if (x + xD >= 0 && yD >= 0 && xD + x != 0 && yD + y != y) {
                        if (Tabuleiro.existemCoordenadas(xD + x, yD + y, tabuleiro.length)) {
                            if (tabuleiro[xD + x][yD + y] != 0) {
                                if (pecas.get(tabuleiro[xD + x][yD + y]).getIdEquipa() != this.idEquipa && (pecas.get(tabuleiro[xD + x][yD + y]).getIdTipo() == GestorDeJogo.RAINHA || pecas.get(tabuleiro[xD + x][yD + y]).getIdTipo() == GestorDeJogo.JOKER)) {
                                    if (pecas.get(tabuleiro[xD + x][yD + y]).getIdTipo() == GestorDeJogo.RAINHA) {
                                        return false;
                                    } else {
                                        Joker joker = new Joker(0, GestorDeJogo.JOKER, 0, "Palhaço");
                                        if (joker.getMascara().getIdTipo() == GestorDeJogo.RAINHA) {
                                            return false;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            int x = xO;
            int y = yO;
            int direcaoHorizontal; // 1 = direita  -1 = esquerda
            if (xO > xD) {
                direcaoHorizontal = -1;
            } else {
                direcaoHorizontal = 1;
            }
            int direcaoVertical; // 1 = baixo  -1 = cima
            if (yO > yD) {
                direcaoVertical = -1;
            } else {
                direcaoVertical = 1;
            }
            while (x != xD && y != yD) {
                x += direcaoHorizontal;
                y += direcaoVertical;
                if (Tabuleiro.existemCoordenadas(x, y, tabuleiro.length)) {
                    if (tabuleiro[x][y] != 0) {
                        return false;
                    }
                }
            }
            return true;
        } else {
            return false;
        }
    }
}
