package pt.ulusofona.lp2.crazyChess;

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
    @Override
    public String getImagePNG() {
        if (this.idEquipa == GestorDeJogo.PRETA) {
            return "preto_torreV.png";
        }
        else if (this.idEquipa == GestorDeJogo.BRANCA) {
            return "branco_torreV.png";
        }
        else {
            return null;
        }
    }

}
