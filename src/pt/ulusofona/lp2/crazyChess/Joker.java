package pt.ulusofona.lp2.crazyChess;

import java.util.ArrayList;
import java.util.List;

public class Joker extends CrazyPiece{

    public static int rotacaoTipoPeca = 0;
    private CrazyPiece [] mascaras;

    public Joker(int idPeca, int idTipo, int idEquipa, String alcunha) {
        super(idPeca, idTipo, idEquipa, alcunha);
        this.mascaras = new CrazyPiece[] {new Rainha(idPeca, 1, idEquipa, alcunha) ,new PoneiMagico(idPeca, 2, idEquipa, alcunha) , new PadreDaVila(idPeca, 3, idEquipa, alcunha), new TorreH(idPeca, 4, idEquipa, alcunha), new TorreV(idPeca, 5, idEquipa, alcunha), new Lebre(idPeca, 6, idEquipa, alcunha)};
    }

    protected String getValorRelativo(){
        return "4";
    }

    protected String getNome(){
        return "Joker/" + getMascara().getNome();
    }

    public boolean verificarSeMove(int xD, int yD, List<CrazyPiece> pecas, int turno){
        return getMascara().verificarSeMove(xD, yD, pecas, turno);
    }

    public String getImagePNG(){
        return null;
    }

    private CrazyPiece getMascara() {
        int index = rotacaoTipoPeca;
        while (index > mascaras.length) {
            index -= mascaras.length;
        }
        CrazyPiece peca = mascaras[index];
        peca.colocarNoTabuleiro(this.x, this.y);
        return peca;
    }
}

