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
        return sugestoes;
    }
}
