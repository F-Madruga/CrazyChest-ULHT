package pt.ulusofona.lp2.crazyChess;

import java.util.ArrayList;
import java.util.List;

public class TorreV extends CrazyPiece {

    public TorreV(int idPeca, int idTipo, int idEquipa, String alcunha) {
        super(idPeca, idTipo, idEquipa, alcunha);
    }

    protected String getValorRelativo() {
        return "3";
    }

    protected String getNome() {
        return "TorreV";
    }

    public boolean verificarSeMove(int xD, int yD, List<CrazyPiece> pecas, int turno) {
        if (this.y != yD && this.x == xD) {
            int x = this.x;
            int y = this.y;
            int direcaoVertical; // 1 = baixo  -1 = cima
            if (this.y > yD) {
                direcaoVertical = -1;
            } else {
                direcaoVertical = 1;
            }
            while (y != yD) {
                y += direcaoVertical;
                if (y != yD) {
                    for (CrazyPiece peca : pecas) {
                        if (peca.getX() == x && peca.getY() == y){
                            return  false;
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
                    sugestoes.add(x + ", " + y);
                }
            }
        }
        return sugestoes;
    }
}
