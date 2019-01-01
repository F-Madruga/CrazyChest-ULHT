package pt.ulusofona.lp2.crazyChess;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertFalse;

public class TestJoker {
    @Test
    public void test01VerificaMovimentoForaTabuleiro() {
        int tabuleiroMatrix [][] = new int[4][4];
        for (int x = 0; x < tabuleiroMatrix.length; x++) {
            for (int y = 0; y < tabuleiroMatrix[x].length; y++) {
                tabuleiroMatrix[x][y] = 0;
            }
        }
        Joker joker = new Joker(1, GestorDeJogo.JOKER, GestorDeJogo.PRETA, "Joker");
        Map<Integer, CrazyPiece> pecas = new HashMap<>();
        pecas.put(joker.getId(), joker);
        tabuleiroMatrix[0][0] = joker.getId();
        joker.setCoordenadas(0,0);
        Joker.ROTACAOTIPOPECA = 0; //turno
        assertFalse(joker.verificarSeMove(0,0, 2, 1, pecas, tabuleiroMatrix, 0));
    }
}
