package pt.ulusofona.lp2.crazyChess;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class TestLebre {
    @Test
    public void test01VerificaMovimentosPossiveis() {
        int tabuleiroMatrix [][] = new int[4][4];
        for (int x = 0; x < tabuleiroMatrix.length; x++) {
            for (int y = 0; y < tabuleiroMatrix[x].length; y++) {
                tabuleiroMatrix[x][y] = 0;
            }
        }
        Lebre lebre = new Lebre(1, GestorDeJogo.LEBRE, GestorDeJogo.PRETA, "Lebre");
        Map<Integer, CrazyPiece> pecas = new HashMap<>();
        pecas.put(lebre.getId(), lebre);
        tabuleiroMatrix[0][0] = lebre.getId();
        lebre.setCoordenadas(0,0);
        assertTrue(lebre.verificarSeMove(0,0, 1, 1, pecas, tabuleiroMatrix, 0));
        tabuleiroMatrix[0][0] = 0;
        tabuleiroMatrix[1][1] = lebre.getId();
        lebre.setCoordenadas(1,1);
        assertTrue(lebre.verificarSeMove(1,1, 0, 0, pecas, tabuleiroMatrix, 0));
        assertTrue(lebre.verificarSeMove(1,1, 2, 0, pecas, tabuleiroMatrix, 0));
        assertTrue(lebre.verificarSeMove(1,1, 0, 2, pecas, tabuleiroMatrix, 0));
    }
    @Test
    public void test02VerificaMovimentoTurnoErrado() {
        int tabuleiroMatrix [][] = new int[4][4];
        for (int x = 0; x < tabuleiroMatrix.length; x++) {
            for (int y = 0; y < tabuleiroMatrix[x].length; y++) {
                tabuleiroMatrix[x][y] = 0;
            }
        }
        Lebre lebre = new Lebre(1, GestorDeJogo.LEBRE, GestorDeJogo.BRANCA, "Lebre");
        Map<Integer, CrazyPiece> pecas = new HashMap<>();
        pecas.put(lebre.getId(), lebre);
        tabuleiroMatrix[0][0] = lebre.getId();
        lebre.setCoordenadas(0,0);
        assertFalse(lebre.verificarSeMove(0,0, 1, 1, pecas, tabuleiroMatrix, 1));
    }
    @Test
    public void test03VerificaMovimentoDuasCasas() {
        int tabuleiroMatrix [][] = new int[4][4];
        for (int x = 0; x < tabuleiroMatrix.length; x++) {
            for (int y = 0; y < tabuleiroMatrix[x].length; y++) {
                tabuleiroMatrix[x][y] = 0;
            }
        }
        Lebre lebre = new Lebre(1, GestorDeJogo.LEBRE, GestorDeJogo.PRETA, "Lebre");
        Map<Integer, CrazyPiece> pecas = new HashMap<>();
        pecas.put(lebre.getId(), lebre);
        tabuleiroMatrix[1][1] = lebre.getId();
        lebre.setCoordenadas(1,1);
        assertFalse(lebre.verificarSeMove(1,1, 3, 3, pecas, tabuleiroMatrix, 0));
    }
    @Test
    public void test04VerificaMovimentoVertical() {
        int tabuleiroMatrix [][] = new int[4][4];
        for (int x = 0; x < tabuleiroMatrix.length; x++) {
            for (int y = 0; y < tabuleiroMatrix[x].length; y++) {
                tabuleiroMatrix[x][y] = 0;
            }
        }
        Lebre lebre = new Lebre(1, GestorDeJogo.LEBRE, GestorDeJogo.PRETA, "Lebre");
        Map<Integer, CrazyPiece> pecas = new HashMap<>();
        pecas.put(lebre.getId(), lebre);
        tabuleiroMatrix[2][2] = lebre.getId();
        lebre.setCoordenadas(2,2);
        assertFalse(lebre.verificarSeMove(2,2, 3, 2, pecas, tabuleiroMatrix, 0));
        assertFalse(lebre.verificarSeMove(2,2, 1, 2, pecas, tabuleiroMatrix, 0));
    }
    @Test
    public void test05VerificaMovimentoHorizontal() {
        int tabuleiroMatrix [][] = new int[4][4];
        for (int x = 0; x < tabuleiroMatrix.length; x++) {
            for (int y = 0; y < tabuleiroMatrix[x].length; y++) {
                tabuleiroMatrix[x][y] = 0;
            }
        }
        Lebre lebre = new Lebre(1, GestorDeJogo.LEBRE, GestorDeJogo.PRETA, "Lebre");
        Map<Integer, CrazyPiece> pecas = new HashMap<>();
        pecas.put(lebre.getId(), lebre);
        tabuleiroMatrix[2][2] = lebre.getId();
        lebre.setCoordenadas(2,2);
        assertFalse(lebre.verificarSeMove(2,2, 2, 3, pecas, tabuleiroMatrix, 0));
        assertFalse(lebre.verificarSeMove(2,2, 2, 1, pecas, tabuleiroMatrix, 0));
    }
    @Test
    public void test05PNGPreta() {
        Lebre lebre = new Lebre(1, GestorDeJogo.LEBRE, GestorDeJogo.PRETA, "Lebre");
        assertEquals("preto_lebre.png", lebre.getImagePNG());
    }
    @Test
    public void test06PNGNula() {
        Lebre lebre = new Lebre(1, GestorDeJogo.LEBRE, -1, "Lebre");
        assertEquals(null, lebre.getImagePNG());
    }
    @Test
    public void test07PNGBranca() {
        Lebre lebre = new Lebre(1, GestorDeJogo.LEBRE, GestorDeJogo.BRANCA, "Lebre");
        assertEquals("branco_lebre.png", lebre.getImagePNG());
    }
    @Test
    public void test08Nome() {
        Lebre lebre = new Lebre(1, GestorDeJogo.LEBRE, GestorDeJogo.BRANCA, "Lebre");
        assertEquals("Lebre", lebre.getNome());
    }
    @Test
    public void test09ValorRelativo() {
        Lebre lebre = new Lebre(1, GestorDeJogo.LEBRE, GestorDeJogo.BRANCA, "Lebre");
        assertEquals("2", lebre.getValorRelativo());
    }
    @Test
    public void test10Sugestoes() {
        Lebre lebre = new Lebre(1, GestorDeJogo.LEBRE, GestorDeJogo.BRANCA, "Lebre");
        int tabuleiroMatrix [][] = new int[4][4];
        for (int x = 0; x < tabuleiroMatrix.length; x++) {
            for (int y = 0; y < tabuleiroMatrix[x].length; y++) {
                tabuleiroMatrix[x][y] = 0;
            }
        }
        Map<Integer, CrazyPiece> pecas = new HashMap<>();
        pecas.put(lebre.getId(), lebre);
        tabuleiroMatrix[1][1] = lebre.getId();
        lebre.setCoordenadas(1,1);
        List<String> sugestoesRecebidas = lebre.darSugestao(1,  1, pecas, tabuleiroMatrix, 0);
        List<String> sugestoesPossiveis = new ArrayList<>();
        sugestoesPossiveis.add("0, 0");
        sugestoesPossiveis.add("0, 2");
        sugestoesPossiveis.add("2, 0");
        sugestoesPossiveis.add("2, 2");
        assertThat(sugestoesRecebidas, is(sugestoesPossiveis));
    }
}
