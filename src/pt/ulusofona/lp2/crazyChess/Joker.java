package pt.ulusofona.lp2.crazyChess;

import java.util.Map;

public class Joker extends CrazyPiece {

    public static int ROTACAOTIPOPECA = 0;

    private CrazyPiece [] mascaras;

    public Joker(int idPeca, int idTipo, int idEquipa, String alcunha) {
        super(idPeca, idTipo, idEquipa, alcunha);
        this.mascaras = new CrazyPiece[] {new Rainha(idPeca, GestorDeJogo.RAINHA, idEquipa, alcunha) ,new PoneiMagico(idPeca, GestorDeJogo.PONEIMAGICO, idEquipa, alcunha) , new PadreDaVila(idPeca, GestorDeJogo.PADREDAVILA, idEquipa, alcunha), new TorreH(idPeca, GestorDeJogo.TORREH, idEquipa, alcunha), new TorreV(idPeca, GestorDeJogo.TORREV, idEquipa, alcunha), new Lebre(idPeca, GestorDeJogo.LEBRE, idEquipa, alcunha)};
    }

    protected String getValorRelativo(){
        return "4";
    }

    protected String getNome(){
        return "Joker/" + getMascara().getNome();
    }

    public boolean verificarSeMove(int xO, int yO, int xD, int yD, Map<Integer, CrazyPiece> pecas, int [][] tabuleiro, int turno){
        return getMascara().verificarSeMove(xO, yO, xD, yD, pecas, tabuleiro, turno);
    }

    @Override
    public String getImagePNG() {
        if (this.idEquipa == GestorDeJogo.PRETA) {
            return "preto_joker.png";
        }
        else if (this.idEquipa == GestorDeJogo.BRANCA) {
            return "branco_joker.png";
        }
        else {
            return null;
        }
    }

    public CrazyPiece getMascara() {
        int index = ROTACAOTIPOPECA;
        while (index >= mascaras.length) {
            index -= mascaras.length;
        }
        CrazyPiece peca = mascaras[index];
        return peca;
    }
}
