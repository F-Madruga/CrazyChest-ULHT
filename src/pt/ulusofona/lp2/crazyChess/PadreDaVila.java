package pt.ulusofona.lp2.crazyChess;

import java.util.ArrayList;
import java.util.List;

public class PadreDaVila extends CrazyPiece {

    public PadreDaVila(int idPeca, int idTipo, int idEquipa, String alcunha) {
        super(idPeca, idTipo, idEquipa, alcunha);
        this.valorRelativo = "3";
    }

    @Override
    public boolean move(int xD, int yD) {
        if ((this.x - xD <= 3 && this.x - xD >= -3) && (this.y - yD <= 3 && this.y - yD >= -3) && (this.x - xD == this.y - yD || this.x - xD == -(this.y - yD))) {
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
        for (int i = -3; i <= 3; i++) {
            for (int j = -3; j <= 3; j++) {
                if ((i == j || i == -j) && i != 0 && j != 0) {
                    sugestoes.add(this.x + i + ", " + this.y + j);
                }
            }
        }
        return sugestoes;
    }

    @Override
    public String getNome() {
        return "Padre da Vila";
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
