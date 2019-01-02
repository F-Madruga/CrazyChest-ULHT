package pt.ulusofona.lp2.crazyChess;

import org.junit.Test;

import java.util.*;

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
        Lebre lebre = new Lebre(1, GestorDeJogo.LEBRE, GestorDeJogo.PRETA, "Lebre");
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
        sugestoesPossiveis.add("2, 2");
        sugestoesPossiveis.add("2, 0");
        Collections.sort(sugestoesPossiveis);
        Collections.sort(sugestoesRecebidas);
        assertThat(sugestoesRecebidas, is(sugestoesPossiveis));
    }

    @Test
    public void test11SetCoordenadas(){
        Lebre lebre = new Lebre(1, GestorDeJogo.LEBRE, GestorDeJogo.PRETA, "Lebre");
        lebre.setCoordenadas(1,2);
        assertEquals("(1, 2)", lebre.coordenadas);
        assertEquals("(n/a)", lebre.coordenadasAnterior);
    }

    @Test
    public void test12ResetCoordenadas(){
        Lebre lebre = new Lebre(1, GestorDeJogo.LEBRE, GestorDeJogo.PRETA, "Lebre");
        lebre.setCoordenadas(1,2);
        lebre.resetCoordenadas();
        assertEquals("(n/a)", lebre.coordenadas);
        assertEquals("(1, 2)", lebre.coordenadasAnterior);
    }

    @Test
    public void test13Undo(){
        Lebre lebre = new Lebre(1, GestorDeJogo.LEBRE, GestorDeJogo.PRETA, "Lebre");
        lebre.setCoordenadas(1,2);
        lebre.undo();
        assertEquals("(n/a)", lebre.coordenadas);
    }

    @Test
    public void test14ToString(){
        Lebre lebre = new Lebre(1, GestorDeJogo.LEBRE, GestorDeJogo.PRETA, "Lebre");
        assertEquals(lebre.getId() + " | " + lebre.getNome() + " | " + lebre.getValorRelativo() + " | " + lebre.getIdEquipa() + " | " + lebre.getAlcunha() + " @ (n/a)", lebre.toString());
    }

    @Test
    public void test15GetID(){
        Lebre lebre = new Lebre(1, GestorDeJogo.LEBRE, GestorDeJogo.PRETA, "Lebre");
        assertEquals(1, lebre.getId());
    }

    @Test
    public void test16GetIDTipo(){
        Lebre lebre = new Lebre(1, GestorDeJogo.LEBRE, GestorDeJogo.PRETA, "Lebre");
        assertEquals(GestorDeJogo.LEBRE, lebre.getIdTipo());
    }

    @Test
    public void test17GetAlcunha(){
        Lebre lebre = new Lebre(1, GestorDeJogo.LEBRE, GestorDeJogo.PRETA, "Coelho");
        assertEquals("Coelho", lebre.getAlcunha());
    }

    @Test
    public void test18GetIDEquipa(){
        Lebre lebre = new Lebre(1, GestorDeJogo.LEBRE, GestorDeJogo.PRETA, "Lebre");
        assertEquals(GestorDeJogo.PRETA, lebre.getIdEquipa());
    }

    @Test
    public void test19Equals(){
        Lebre lebre = new Lebre(1, GestorDeJogo.LEBRE, GestorDeJogo.PRETA, "Lebre");
        Lebre coelho = new Lebre(1, GestorDeJogo.LEBRE, GestorDeJogo.PRETA, "Coelho");
        assertTrue(lebre.equals(coelho));
    }
}
