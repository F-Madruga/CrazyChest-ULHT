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
            int nrReisHorizontalVertical = 0;
            int nrReisVerticalHorizontal = 0;

                for (CrazyPiece peca : pecas){
                    if (xD > x && yD < y) {
                        if (peca.getIdTipo() == 0 && (peca.getX() == x && (peca.getY() == y - 1 || peca.getY() == y - 2)) || (peca.getX() == x + 1 && peca.getY() == y - 2)) {
                            nrReisVerticalHorizontal++;
                        }
                        if (peca.getIdTipo() == 0 && ((peca.getX() == x + 1 || peca.getX() == x + 2) && peca.getY() == y) || (peca.getX() == x + 2 && peca.getY() == y - 1)) {
                            nrReisHorizontalVertical++;
                        }
                    } else if (xD < x && yD < y) {
                        if (peca.getIdTipo() == 0 && (peca.getX() == x && (peca.getY() == y - 1 || peca.getY() == y - 2)) || (peca.getX() == x - 1 && peca.getY() == y - 2)) {
                            nrReisVerticalHorizontal++;
                        }
                        if (peca.getIdTipo() == 0 && ((peca.getX() == x - 1 || peca.getX() == x - 2) && peca.getY() == y) || (peca.getX() == x - 2 && peca.getY() == y - 1)) {
                            nrReisHorizontalVertical++;
                        }
                    } else if (xD < x && yD > y) {
                        if (peca.getIdTipo() == 0 && ((peca.getX() == x - 1 || peca.getX() == x - 2) && peca.getY() == y) || (peca.getX() == x - 2 && peca.getY() == y + 1)){
                            nrReisVerticalHorizontal++;
                        }
                        if (peca.getIdTipo() == 0 && (peca.getX() == x && (peca.getY() == y + 1 || peca.getY() == y + 2)) || (peca.getX() == x - 1 && peca.getY() == y + 2)) {
                            nrReisHorizontalVertical++;
                        }
                    } else if (xD > x && yD > y){
                        if(peca.getIdTipo() == 0 && (peca.getX() == x && (peca.getY() == y + 1 || peca.getY() == y + 2))|| (peca.getX() == x + 1 && peca.getY() == y + 2)){
                            nrReisVerticalHorizontal++;
                        }
                        if(peca.getIdTipo() == 0 && ((peca.getX() == x + 1 || peca.getX() == x + 2) && peca.getY() == y) || (peca.getX() == x + 2 && peca.getY() == y + 1)){
                            nrReisHorizontalVertical++;
                        }
                    }
                    if (nrReisVerticalHorizontal >= 1 && nrReisHorizontalVertical >= 1) {
                        return false;
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

}
