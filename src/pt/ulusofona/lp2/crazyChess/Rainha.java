package pt.ulusofona.lp2.crazyChess;

import java.util.Map;

public class Rainha extends CrazyPiece {

    public Rainha(int idPeca, int idTipo, int idEquipa, String alcunha) {
        super(idPeca, idTipo, idEquipa, alcunha);
    }

    @Override
    protected String getValorRelativo() {
        return "8";
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
        if (((xO - xD <= 5 && xO - xD >= -5) && (yO - yD <= 5 && yO - yD >= -5)) && ((xO != xD && yO == yD) || (xO == xD && yO != yD) || (xO - xD == yO - yD) || (xO - xD == -(yO - yD)))) {
            if (tabuleiro[xD][yD] != 0) {
                if (pecas.get(tabuleiro[xD][yD]).getIdTipo() == this.idTipo || pecas.get(tabuleiro[xD][yD]).getIdTipo() == GestorDeJogo.JOKER) {
                    if (pecas.get(tabuleiro[xD][yD]).getIdTipo() == this.idTipo) {
                        return false;
                    } else {
                        Joker joker = new Joker(0, GestorDeJogo.JOKER, 0, "Virtual");
                        if (joker.getMascara().getIdTipo() == this.idTipo) {
                            return false;
                        }
                    }
                }
            }
            for (int x = -1; x <= 1; x++) {
                for (int y = -1; y <= 1; y++) {
                    if (Tabuleiro.existemCoordenadas(xD + x, yD + y, tabuleiro.length)) {
                        if (tabuleiro[xD + x][yD + y] != 0) {
                            if (pecas.get(tabuleiro[xD + x][yD + y]).getIdEquipa() != this.idEquipa && ((pecas.get(tabuleiro[xD + x][yD + y]).getIdTipo() == GestorDeJogo.PADREDAVILA || pecas.get(tabuleiro[xD + x][yD + y]).getIdTipo() == GestorDeJogo.JOKER))) {
                                if (pecas.get(tabuleiro[xD + x][yD + y]).getIdTipo() == GestorDeJogo.PADREDAVILA) {
                                    System.out.println("padre " + xD + x + ", " + yD + y);
                                    return false;
                                } else {
                                    Joker joker = new Joker(0, GestorDeJogo.JOKER, 0, "Batman");
                                    if (joker.getMascara().getIdTipo() == GestorDeJogo.PADREDAVILA) {
                                        System.out.println("Joker padre " + xD + x + ", " + yD + y);
                                        return false;
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
            while (x != xD || y != yD) {
                if (x != xD) {
                    x += direcaoHorizontal;
                }
                if (y != yD) {
                    y += direcaoVertical;
                }
                if (x != xD || y != yD) {
                    if (Tabuleiro.existemCoordenadas(x, y, tabuleiro.length)) {
                        if (tabuleiro[x][y] != 0) {
                            return false;
                        }
                    }
                }
            }
            return true;
        } else {
            return false;
        }
    }
}