package pt.ulusofona.lp2.crazyChess;

import java.util.ArrayList;
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
                    if (peca.getIdTipo() == 1 && peca.getIdEquipa() != this.idEquipa) {
                        if (peca.getX() <= this.x + 2 || peca.getX() >= this.x - 2 || peca.getY() <= this.y + 2 || peca.getY() >= this.y - 2) {
                            existeRainha = true;
                            break;
                        }
                    }
                    if (peca.getX() == x && peca.getY() == y && !existeRainha) {
                        return false;
                    }
                }
            }
            return true;
        } else {
            return false;
        }
    }

    public String getImagePNG() {
        return null;
    }

    public List<String> darSugestoes(List<CrazyPiece> pecas, int turno, int tamanho) {
        List<String> sugestoes = new ArrayList<>();
        for (int x = 0; x < tamanho; x++) {
            for (int y = 0; y < tamanho; y++) {
                if (verificarSeMove(x, y, pecas, turno)) {
                    boolean existePecaMesmaEquipa = false;
                    boolean existeRainhaAdversario = false;
                    for (CrazyPiece peca : pecas) {
                        if (peca.getX() == this.x && peca.getY() == this.y && peca.getIdEquipa() == this.idEquipa) {
                            existePecaMesmaEquipa = true;
                            break;
                        }
                        if (peca.getIdTipo() == 1 && peca.getIdEquipa() != this.idEquipa) {
                            if (peca.getX() <= this.x + 2 || peca.getX() >= this.x - 2 || peca.getY() <= this.y + 2 || peca.getY() >= this.y - 2) {
                                existeRainhaAdversario = true;
                                break;
                            }
                        }
                    }
                    if (!existePecaMesmaEquipa && !existeRainhaAdversario) {
                        sugestoes.add(x + ", " + y);
                    }

                }
            }
        }
        return sugestoes;
    }
}
