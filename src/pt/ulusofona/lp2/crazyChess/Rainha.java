package pt.ulusofona.lp2.crazyChess;

import java.util.ArrayList;
import java.util.List;

public class Rainha extends CrazyPiece{

    public Rainha(int idPeca, int idTipo, int idEquipa, String alcunha) {
        super(idPeca, idTipo, idEquipa, alcunha);
    }

    protected String getValorRelativo(){
        return "8";
    }

    protected String getNome(){
        return "Rainha";
    }

    public boolean verificarSeMove(int xD, int yD, List<CrazyPiece> pecas, int turno){
       if (((this.x != xD  && this.y + yD == this.y) || (this.x + xD == this.x && this.y != yD) || (this.x + xD == this.y + yD) || (this.x + xD == -(this.y + yD))) && ((this.x - xD <= 5 || this.x - xD >= -5) && (this.y - yD <= 5 || this.y - yD >= -5))) {

        }
    }

    public String getImagePNG(){

    }

    public List<String> darSugestoes(List<CrazyPiece> pecas, int turno, int tamanho){
        List<String> sugestoes = new ArrayList<>();
        return sugestoes;
    }
}

