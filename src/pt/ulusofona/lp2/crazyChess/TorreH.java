package pt.ulusofona.lp2.crazyChess;

import java.util.ArrayList;
import java.util.List;

public class TorreH extends CrazyPiece {

    public TorreH(int idPeca, int idTipo, int idEquipa, String alcunha) {
        super(idPeca, idTipo, idEquipa, alcunha);
    }

    protected String getValorRelativo(){
        return "3";
    }

    protected String getNome(){
        return "TorreH";
    }

    public boolean verificarSeMove(int xD, int yD, List<CrazyPiece> pecas, int turno){
        if (this.x != xD && this.y == yD) {
            int x = this.x;
            int y = this.y;
            int direcaoHorizontal; // 1 = direita  -1 = esquerda
            if (this.x > xD) {
                direcaoHorizontal = -1;
            } else {
                direcaoHorizontal = 1;
            }
            while (x != xD) {
                x += direcaoHorizontal;
                if (x != xD) {
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

    public String getImagePNG(){
        return null;
    }

    public List<String> darSugestoes(List<CrazyPiece> pecas, int turno, int tamanho){
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
