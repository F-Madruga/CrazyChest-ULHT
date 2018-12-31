package pt.ulusofona.lp2.crazyChess;

import java.util.List;

public class PadreDaVila extends CrazyPiece {

    public PadreDaVila(int idPeca, int idTipo, int idEquipa, String alcunha) {
        super(idPeca, idTipo, idEquipa, alcunha);
    }

    protected String getValorRelativo() {
        return "3";
    }

    protected String getNome() {
        return "Padre da Vila";
    }

    public boolean verificarSeMove(int xD, int yD, List<CrazyPiece> pecas, int turno) {
        if ((this.x - xD <= 3 && this.x - xD >= -3) && (this.y - yD <= 3 && this.y - yD >= -3) && (this.x - xD == this.y - yD || this.x - xD == -(this.y - yD))) {
            for (int x = -2; x <= 2; x++) {
                for (int y = -2; y <= 2; y++) {
                    if (x + xD >= 0 && yD >= 0 && xD + x != 0 && yD + y != y) {
                        for (CrazyPiece peca: pecas) {
                            if (peca.getX() == xD + x && peca.getY() == yD + y  && peca.getIdEquipa() != this.idEquipa && peca.getIdTipo() == GestorDeJogo.RAINHA) {
                                return false;
                            }
                        }
                    }
                }
            }
            int x = this.x;
            int y = this.y;
            boolean existeRainha = false;
            int direcaoHorizontal; // 1 = direita  -1 = esquerda
            if (this.x > xD) {
                direcaoHorizontal = -1;
            } else {
                direcaoHorizontal = 1;
            }
            int direcaoVertical; // 1 = baixo  -1 = cima
            if (this.y > yD) {
                direcaoVertical = -1;
            } else {
                direcaoVertical = 1;
            }
            while (x != xD && y != yD) {
                x += direcaoHorizontal;
                y += direcaoVertical;
                for (CrazyPiece peca : pecas) {
                    if (peca.getX() == x && peca.getY() == y) {
                        return false;
                    }
                }
            }
            return true;
        } else {
            return false;
        }
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
}
