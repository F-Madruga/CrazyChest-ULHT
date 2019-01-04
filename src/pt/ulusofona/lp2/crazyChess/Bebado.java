package pt.ulusofona.lp2.crazyChess;

import java.util.Map;
import java.util.Random;

public class Bebado extends CrazyPiece {

    boolean [] probabilidade;

    public Bebado(int idPeca, int idTipo, int idEquipa, String alcunha) {
        super(idPeca, idTipo, idEquipa, alcunha);
        this.probabilidade = new boolean[] {true, true, false, false, false};
    }

    @Override
    protected String getValorRelativo() {
        return "(mão direita é de penalti)";
    }

    @Override
    protected String getNome() {
        return "Bêbado";
    }

    @Override
    public String getImagePNG() {
        if (this.idEquipa == GestorDeJogo.PRETA) {
            return "preto_bebado.png";
        }
        else if (this.idEquipa == GestorDeJogo.BRANCA) {
            return "branco_bebado.png";
        }
        else {
            return null;
        }
    }

    @Override
    public boolean verificarSeMove(int xO, int yO, int xD, int yD, Map<Integer, CrazyPiece> pecas, int[][] tabuleiro, int turno) {
        if (!move(xO, yO, xD, yD)) {
            return false;
        }
        if (tabuleiro[xD][yD] != 0) {
            if (pecas.get(tabuleiro[xD][yD]).getIdEquipa() == this.idEquipa || pecas.get(tabuleiro[xD][yD]).getIdTipo() == GestorDeJogo.REI) {
                return false;
            }
        }
        Random random = new Random();
        int low = 0;
        int high = probabilidade.length;
        int result = random.nextInt(high-low) + low;
        if (!this.probabilidade[result]) {
            Tabuleiro.ALTERARCOORDENADASDESTINO = true;
            low = 0;
            high = tabuleiro.length;
            int x = random.nextInt(high-low) + low;
            int y = random.nextInt(high-low) + low;
            while (tabuleiro[x][y] != 0) {
                x = random.nextInt(high-low) + low;
                y = random.nextInt(high-low) + low;
            }
            Tabuleiro.NOVOX = x;
            Tabuleiro.NOVOY = y;
        }
        return true;
    }
}
