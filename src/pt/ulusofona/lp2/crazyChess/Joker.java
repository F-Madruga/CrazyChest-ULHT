package pt.ulusofona.lp2.crazyChess;

import java.util.List;

public class Joker extends CrazyPiece {

    public Joker(int idPeca, int idTipo, int idEquipa, String alcunha) {
        super(idPeca, idTipo, idEquipa, alcunha);
        this.valorRelativo = "4";
    }

    @Override
    public boolean move(int xD, int yD) {
        //TODO
    }

    @Override
    public List<String> darSugestao() {
        //TODO
        return null;
    }

    @Override
    public String getNome() {
        return "Joker/" + //Mudar aqui;
    }
    @Override
    public String getImagePNG() {
        if (this.idEquipa == GestorDeJogo.preta) {
            return "trihard.png"; // mudar nome
        } else if (this.idEquipa == GestorDeJogo.branca) {
            return "kappa.png"; // mudar nome
        } else {
            return null;
        }
    }
}
