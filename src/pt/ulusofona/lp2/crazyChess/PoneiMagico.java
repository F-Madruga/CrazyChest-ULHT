package pt.ulusofona.lp2.crazyChess;

import java.util.ArrayList;
import java.util.List;

public class PoneiMagico extends CrazyPiece {

    public PoneiMagico(int idPeca, int idTipo, int idEquipa, String alcunha) {
        super(idPeca, idTipo, idEquipa, alcunha);
        this.valorRelativo = "5";
    }

    @Override
    public boolean move(int xD, int yD) {
        if ((this.x - xD == -2 && this.y - yD == -2) || (this.x - xD == -2 && this.y - yD == 2) || (this.x - xD == 2 && this.y - yD == -2) || (this.x - xD == 2 && this.y - yD == 2)) {
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
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (i != 0 && j != 0) {
                    sugestoes.add(this.x + 2 * i + ", " + this.y + 2 * j);
                }
            }
        }
        return sugestoes;
    }

    @Override
    public String getNome() {
        return "Ponei Mágico";
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
