package pt.ulusofona.lp2.crazyChess;

import java.util.ArrayList;
import java.util.List;

public class PoneiMagico extends CrazyPiece{

    public PoneiMagico(int idPeca, int idTipo, int idEquipa, String alcunha) {
        super(idPeca, idTipo, idEquipa, alcunha);
    }

    protected String getValorRelativo(){
        return "5";
    }

    protected String getNome(){
        return "Ponei Mágico";
    }

    public boolean verificarSeMove(int xD, int yD, List<CrazyPiece> pecas, int turno){
        if ((this.x - xD == -2 && this.y - yD == -2) || (this.x - xD == -2 && this.y - yD == 2) || (this.x - xD == 2 && this.y - yD == -2) || (this.x - xD == 2 && this.y - yD == 2)) {
            int x = this.x;
            int y = this.y;
                for (CrazyPiece peca : pecas){
                    if (xD > x && yD < y) {
                        if (peca.getIdTipo() == 0 && (peca.getX() == x && (peca.getY() == y - 1 || peca.getY() == y - 2)) || (peca.getX() == x + 1 && peca.getY() == y - 2)) {
                            if (peca.getIdTipo() == 0 && ((peca.getX() == x + 1 || peca.getX() == x + 2) && peca.getY() == y) || (peca.getX() == x + 2 && peca.getY() == y - 1)) {
                                return false;
                            }
                        }
                    } else if (xD < x && yD < y) {
                        if (peca.getIdTipo() == 0 && (peca.getX() == x && (peca.getY() == y - 1 || peca.getY() == y - 2)) || (peca.getX() == x - 1 && peca.getY() == y - 2)) {
                            if (peca.getIdTipo() == 0 && ((peca.getX() == x - 1 || peca.getX() == x - 2) && peca.getY() == y) || (peca.getX() == x - 2 && peca.getY() == y - 1)) {
                                return false;
                            }
                        }
                    } else if (xD < x && yD > y) {
                        if (peca.getIdTipo() == 0 && ((peca.getX() == x - 1 || peca.getX() == x - 2) && peca.getY() == y)|| (peca.getX() == x - 2 && peca.getY() == y + 1)){
                            if (peca.getIdTipo() == 0 && (peca.getX() == x && (peca.getY() == y + 1 || peca.getY() == y + 2)) || (peca.getX() == x - 1 && peca.getY() == y + 2)) {
                                return false;
                            }
                        }
                    } else if (xD > x && yD > y){
                        if(peca.getIdTipo() == 0 && (peca.getX() == x && (peca.getY() == y + 1 || peca.getY() == y + 2))|| (peca.getX() == x + 1 && peca.getY() == y + 2)){
                            if(peca.getIdTipo() == 0 && ((peca.getX() == x + 1 || peca.getX() == x + 2) && peca.getY() == y) || (peca.getX() == x + 2 && peca.getY() == y + 1)){
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
