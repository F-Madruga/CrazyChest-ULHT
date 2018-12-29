package pt.ulusofona.lp2.crazyChess;

import java.util.ArrayList;
import java.util.List;

public class Rainha extends CrazyPiece{

    public Rainha(int idPeca, int idTipo, int idEquipa, String alcunha) {
        super(idPeca, idTipo, idEquipa, alcunha);
    }

    protected String getValorRelativo() {
        return "8";
    }

    protected String getNome() {
        return "Rainha";
    }

    public boolean verificarSeMove(int xD, int yD, List<CrazyPiece> pecas, int turno) {
        if (((this.x != xD && this.y + yD == this.y) || (this.x + xD == this.x && this.y != yD) || (this.x + xD == this.y + yD) || (this.x + xD == -(this.y + yD))) && ((this.x - xD <= 5 || this.x - xD >= -5) && (this.y - yD <= 5 || this.y - yD >= -5))) {
            int x = this.x;
            int y = this.y;
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
            while (x != xD || y != yD) {
                if (x != xD) {
                    x += direcaoHorizontal;
                }
                if (y != yD) {
                    y += direcaoVertical;
                }
                if (x != xD || y != yD) {
                    for (CrazyPiece peca : pecas) {
                        if (peca.getX() == x && peca.getY() == y) {
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

    public String getImagePNG() {
        return null;
    }

    public List<String> darSugestoes(List<CrazyPiece> pecas, int turno, int tamanho) {
        List<String> sugestoes = new ArrayList<>();
        for (int x = 0; x < tamanho; x++) {
            for (int y = 0; y < tamanho; y++) {
                if (verificarSeMove(x, y, pecas, turno)) {
                    boolean existePecaMesmaEquipa = false;
                    for (CrazyPiece peca : pecas) {
                        if (peca.getX() == this.x && peca.getY() == this.y && peca.getIdEquipa() == this.idEquipa) {
                            existePecaMesmaEquipa = true;
                            break;
                        }
                    }
                    if (!existePecaMesmaEquipa) {
                        sugestoes.add(x + ", " + y);
                    }
                }
            }
        }
        return sugestoes;
    }
}

