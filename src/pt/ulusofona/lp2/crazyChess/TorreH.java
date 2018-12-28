package pt.ulusofona.lp2.crazyChess;

import java.util.ArrayList;
import java.util.List;

public class TorreH extends CrazyPiece {

    public TorreH(int idPeca, int idTipo, int idEquipa, String alcunha) {
        super(idPeca, idTipo, idEquipa, alcunha);
        this.valorRelativo = "3";
    }

    @Override
    public boolean move(int xD, int yD) {
        if (this.x != xD && this.y == yD) {
            atualizarAnterior();
            this.setCoordenadas(xD, yD);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public List<String> darSugestao() {
        List<String> sugestoes = new ArrayList<>();
        for (int i = -12; i <= 12; i++) {
            if (i != 0) {
                sugestoes.add(this.x + i + ", " + this.y);
            }
        }
        return sugestoes;
    }

    @Override
    public String getNome() {
        return "TorreH";
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
