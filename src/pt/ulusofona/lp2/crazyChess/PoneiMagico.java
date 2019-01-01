package pt.ulusofona.lp2.crazyChess;

import java.util.Map;

public class PoneiMagico extends CrazyPiece {

    public PoneiMagico(int idPeca, int idTipo, int idEquipa, String alcunha) {
        super(idPeca, idTipo, idEquipa, alcunha);
    }

    @Override
    protected String getValorRelativo(){
        return "5";
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
        if ((xO - xD == -2 && yO - yD == -2) || (xO - xD == -2 && yO - yD == 2) || (xO - xD == 2 && yO - yD == -2) || (xO - xD == 2 && yO - yD == 2)) {
            int x = xO;
            int y = yO;
            int nrReisHorizontalVertical = 0;
            int nrReisVerticalHorizontal = 0;
            boolean primeiroCaminho = true;
            int direcaoHorizontal;
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
            //horizontal -> vertical
            for (int i = 0; i < 2; i++) {
                x += direcaoHorizontal;
                if (Tabuleiro.existemCoordenadas(x,y,tabuleiro.length)) {
                    if (tabuleiro[x][y] != 0) {
                        if (pecas.get(tabuleiro[x][y]).getIdTipo() == GestorDeJogo.REI) {
                            primeiroCaminho = false;
                        }
                    }
                }
            }
            if (primeiroCaminho) {
                for (int i = 0; i < 1; i++) {
                    y += direcaoVertical;
                    if (Tabuleiro.existemCoordenadas(x, y, tabuleiro.length)) {
                        if (tabuleiro[x][y] != 0) {
                            if (pecas.get(tabuleiro[x][y]).getIdTipo() == GestorDeJogo.REI) {
                                primeiroCaminho = false;
                            }
                        }
                    }
                }
            }
            if (primeiroCaminho) {
                return true;
            }
            x = xO;
            y = yO;
            //vertica -> horizontal
            for (int i = 0; i < 2; i++) {
                y += direcaoVertical;
                if (Tabuleiro.existemCoordenadas(x,y,tabuleiro.length)) {
                    if (tabuleiro[x][y] != 0) {
                        if (pecas.get(tabuleiro[x][y]).getIdTipo() == GestorDeJogo.REI) {
                            return false;
                        }
                    }
                }
            }
            for (int i = 0; i < 1; i++) {
                x += direcaoHorizontal;
                if (Tabuleiro.existemCoordenadas(x,y,tabuleiro.length)) {
                    if (tabuleiro[x][y] != 0) {
                        if (pecas.get(tabuleiro[x][y]).getIdTipo() == GestorDeJogo.REI) {
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
