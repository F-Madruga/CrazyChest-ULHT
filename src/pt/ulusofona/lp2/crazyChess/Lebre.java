package pt.ulusofona.lp2.crazyChess;

import java.util.ArrayList;
import java.util.List;

public class Lebre extends CrazyPiece {
    public Lebre(int idPeca, int idTipo, int idEquipa, String alcunha) {
        super(idPeca, idTipo, idEquipa, alcunha);
        this.valorRelativo = "2";
    }

    @Override
    public boolean move(int xD, int yD) {
        if ((this.x - xD == 1 || this.x - xD == -1) && (this.y - yD == 1 || this.y - yD == -1)) {
            this.atualizarAnterior();
            this.setCoordenadas(xD,yD);
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public List<String> darSugestao() {
        List<String> sugestoes = new ArrayList<>();
        for (int i= -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (j != 0 && i != 0) {
                    sugestoes.add(this.x + i + ", " + this.y + j);
                }
            }
        }
        return sugestoes;
    }

    @Override
    public String getNome() {
        return "Lebre";
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
