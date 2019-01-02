package pt.ulusofona.lp2.crazyChess;

import org.junit.Test;

import java.util.*;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class TestTorreV {
    @Test
    public void test01VerificaMovimentosPossiveis() {
        int tabuleiroMatrix [][] = new int[4][4];
        for (int x = 0; x < tabuleiroMatrix.length; x++) {
            for (int y = 0; y < tabuleiroMatrix[x].length; y++) {
                tabuleiroMatrix[x][y] = 0;
            }
        }
        TorreV torre = new TorreV(1, GestorDeJogo.TORREV, GestorDeJogo.PRETA, "TorreV");
        Map<Integer, CrazyPiece> pecas = new HashMap<>();
        pecas.put(torre.getId(), torre);
        tabuleiroMatrix[1][1] = torre.getId();
        torre.setCoordenadas(1,1);
        assertTrue(torre.verificarSeMove(1,1, 1, 0, pecas, tabuleiroMatrix, 0));
        assertTrue(torre.verificarSeMove(1,1, 1, 2, pecas, tabuleiroMatrix, 0));
        assertTrue(torre.verificarSeMove(1,1, 1, 3, pecas, tabuleiroMatrix, 0));
    }
    @Test
    public void test02VerificaMovimentosComPecasNoMeio() {
        int tabuleiroMatrix [][] = new int[12][12];
        for (int x = 0; x < tabuleiroMatrix.length; x++) {
            for (int y = 0; y < tabuleiroMatrix[x].length; y++) {
                tabuleiroMatrix[x][y] = 0;
            }
        }
        Map<Integer, CrazyPiece> pecas = new HashMap<>();
        TorreV torre = new TorreV(1, GestorDeJogo.TORREV, GestorDeJogo.PRETA, "TorreV");
        Rei rei = new Rei(2, GestorDeJogo.REI, GestorDeJogo.PRETA, "Rei");
        Rei king = new Rei(3, GestorDeJogo.REI, GestorDeJogo.PRETA, "King");
        pecas.put(rei.getId(), rei);
        pecas.put(king.getId(), king);
        pecas.put(torre.getId(), torre);
        tabuleiroMatrix[6][6] = torre.getId();
        for (int i = 1; i <= 4; i++) {
            tabuleiroMatrix[6][6 + i] = rei.getId();
            tabuleiroMatrix[6][6 - i] = king.getId();
            assertFalse(torre.verificarSeMove(6, 6, 6, 0, pecas, tabuleiroMatrix, 0));
            assertFalse(torre.verificarSeMove(6, 6, 6, 11, pecas, tabuleiroMatrix, 0));
            tabuleiroMatrix[6][6 + i] = 0;
            tabuleiroMatrix[6][6 - i] = 0;
        }
    }
    @Test
    public void test03PNGPreta(){
        TorreV torre = new TorreV(1, GestorDeJogo.TORREV, GestorDeJogo.PRETA, "TorreV");
        assertEquals("preto_torreV.png", torre.getImagePNG());
    }
    @Test
    public void test04PNGNula() {
        TorreV torre = new TorreV(1, GestorDeJogo.TORREV, -1, "TorreV");
        assertEquals(null, torre.getImagePNG());
    }
    @Test
    public void test05PNGBranca() {
        TorreV torre = new TorreV(1, GestorDeJogo.TORREV, GestorDeJogo.BRANCA, "TorreV");
        assertEquals("branco_torreV.png", torre.getImagePNG());
    }
    @Test
    public void test06Nome() {
        TorreV torre = new TorreV(1, GestorDeJogo.TORREV, GestorDeJogo.BRANCA, "TorreV");
        assertEquals("TorreV", torre.getNome());
    }
    @Test
    public void test07ValorRelativo() {
        TorreV torre = new TorreV(1, GestorDeJogo.TORREV, GestorDeJogo.BRANCA, "TorreV");
        assertEquals("3", torre.getValorRelativo());
    }
    @Test
    public void test08Sugestoes(){
        int tabuleiroMatrix [][] = new int[12][12];
        for (int x = 0; x < tabuleiroMatrix.length; x++) {
            for (int y = 0; y < tabuleiroMatrix[x].length; y++) {
                tabuleiroMatrix[x][y] = 0;
            }
        }
        Map<Integer, CrazyPiece> pecas = new HashMap<>();
        TorreV torre = new TorreV(1, GestorDeJogo.TORREV, GestorDeJogo.PRETA, "TorreV");
        pecas.put(torre.getId(), torre);
        tabuleiroMatrix[1][1] = torre.getId();
        torre.setCoordenadas(1,1);
        List<String> sugestoesRecebidas = torre.darSugestao(1,  1, pecas, tabuleiroMatrix, 0);
        List<String> sugestoesPossiveis = new ArrayList<>();
        sugestoesPossiveis.add("1, 0");
        sugestoesPossiveis.add("1, 2");
        sugestoesPossiveis.add("1, 3");
        sugestoesPossiveis.add("1, 4");
        sugestoesPossiveis.add("1, 5");
        sugestoesPossiveis.add("1, 6");
        sugestoesPossiveis.add("1, 7");
        sugestoesPossiveis.add("1, 8");
        sugestoesPossiveis.add("1, 9");
        sugestoesPossiveis.add("1, 10");
        sugestoesPossiveis.add("1, 11");
        Collections.sort(sugestoesPossiveis);
        Collections.sort(sugestoesRecebidas);
        assertThat(sugestoesRecebidas, is(sugestoesPossiveis));
    }
}
