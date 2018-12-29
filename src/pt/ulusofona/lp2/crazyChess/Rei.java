package pt.ulusofona.lp2.crazyChess;

import java.util.ArrayList;
import java.util.List;

public class Rei extends CrazyPiece {

    public Rei(int idPeca, int idTipo, int idEquipa, String alcunha) {
        super(idPeca, idTipo, idEquipa, alcunha);
    }

    @Override
    protected String getValorRelativo() {
        return "(infinito)";
    }

    @Override
    protected String getNome() {
        return "Rei";
    }

    @Override
    public boolean verificarSeMove(int xD, int yD, List<CrazyPiece> pecas, int turno) {
        if ((this.x - xD == 1 || this.x - xD == -1 || this.y - yD == 1 || this.y - yD == -1) && (this.x != xD || this.y != yD)) {
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public String getImagePNG() {
        if (this.idEquipa == GestorDeJogo.preta) {
            return "trihard.png";
        } else if (this.idEquipa == GestorDeJogo.branca) {
            return "kappa.png";
        } else {
            return null;
        }
    }

    @Override
    public List<String> darSugestoes(List<CrazyPiece> pecas, int turno, int tamanho) {
        List<String> sugestoes = new ArrayList<>();
        for (int x = -1; x <= 1; x++) {
            for (int y = -1; y <= 1; y++) {
                if ((x != 0 || y != 0) && this.x + x >= 0 && this.x + x < tamanho && this.y + y >= 0 && this.y + y < tamanho) {
                    sugestoes.add(this.x + x + ", " + this.y + y);
                }
            }
        }
        return sugestoes;
    }
}

