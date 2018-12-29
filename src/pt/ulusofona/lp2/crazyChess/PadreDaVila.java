package pt.ulusofona.lp2.crazyChess;

import java.util.ArrayList;
import java.util.List;

public class PadreDaVila extends CrazyPiece {

    public PadreDaVila(int idPeca, int idTipo, int idEquipa, String alcunha) {
        super(idPeca, idTipo, idEquipa, alcunha);
    }

    protected String getValorRelativo(){
    }

    protected String getNome(){
    }

    public boolean verificarSeMove(int xD, int yD, List<CrazyPiece> pecas, int turno){

    }

    public String getImagePNG(){

    }

    public List<String> darSugestoes(List<CrazyPiece> pecas, int turno, int tamanho){
        List<String> sugestoes = new ArrayList<>();
        return sugestoes;
    }
}
