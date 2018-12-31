package pt.ulusofona.lp2.crazyChess;

import java.util.List;

public class Joker extends CrazyPiece{

    public static int rotacaoTipoPeca = 0;
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

    public boolean verificarSeMove(int xD, int yD, List<CrazyPiece> pecas, int turno){
        return getMascara().verificarSeMove(xD, yD, pecas, turno);
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

