package pt.ulusofona.lp2.crazyChess;

import org.junit.Test;

import java.util.*;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;

public class TestRainha {

    @Test
    public void test01PNGPreta() {
        Rainha rainha = new Rainha(1, GestorDeJogo.RAINHA, GestorDeJogo.PRETA, "Rainha");
        assertEquals("preto_rainha.png", rainha.getImagePNG());
    }

    @Test
    public void test02PNGNula() {
        Rainha rainha = new Rainha(1, GestorDeJogo.RAINHA, -1, "Rainha");
        assertEquals(null, rainha.getImagePNG());
    }

    @Test
    public void test03PNGBranca() {
        Rainha rainha = new Rainha(1, GestorDeJogo.RAINHA, GestorDeJogo.BRANCA, "Rainha");
        assertEquals("branco_rainha.png", rainha.getImagePNG());
    }

    @Test
    public void test04Nome() {
        Rainha rainha = new Rainha(1, GestorDeJogo.RAINHA, GestorDeJogo.PRETA, "Rainha");
        assertEquals("Rainha", rainha.getNome());
    }

    @Test
    public void test05ValorRelativo() {
        Rainha rainha = new Rainha(1, GestorDeJogo.RAINHA, GestorDeJogo.PRETA, "Rainha");
        assertEquals("8", rainha.getValorRelativo());
    }

    @Test
    public void test06Sugestoes(){
        Rainha rainha = new Rainha(1, GestorDeJogo.RAINHA, GestorDeJogo.PRETA, "Rainha");
        int tabuleiroMatrix [][] = new int[12][12];
        for (int x = 0; x < tabuleiroMatrix.length; x++) {
            for (int y = 0; y < tabuleiroMatrix[x].length; y++) {
                tabuleiroMatrix[x][y] = 0;
            }
        }
        Map<Integer, CrazyPiece> pecas = new HashMap<>();
        pecas.put(rainha.getId(), rainha);
        tabuleiroMatrix[6][6] = rainha.getId();
        List<String> sugestoesRecebidas = rainha.darSugestao(6,  6, pecas, tabuleiroMatrix, 0);
        List<String> sugestoesPossiveis = new ArrayList<>();
        sugestoesPossiveis.add("1, 1");
        sugestoesPossiveis.add("1, 6");
        sugestoesPossiveis.add("1, 11");
        sugestoesPossiveis.add("2, 2");
        sugestoesPossiveis.add("2, 6");
        sugestoesPossiveis.add("2, 10");
        sugestoesPossiveis.add("3, 3");
        sugestoesPossiveis.add("3, 6");
        sugestoesPossiveis.add("3, 9");
        sugestoesPossiveis.add("4, 4");
        sugestoesPossiveis.add("4, 6");
        sugestoesPossiveis.add("4, 8");
        sugestoesPossiveis.add("5, 5");
        sugestoesPossiveis.add("5, 6");
        sugestoesPossiveis.add("5, 7");
        sugestoesPossiveis.add("6, 1");
        sugestoesPossiveis.add("6, 2");
        sugestoesPossiveis.add("6, 3");
        sugestoesPossiveis.add("6, 4");
        sugestoesPossiveis.add("6, 5");
        sugestoesPossiveis.add("6, 7");
        sugestoesPossiveis.add("6, 8");
        sugestoesPossiveis.add("6, 9");
        sugestoesPossiveis.add("6, 10");
        sugestoesPossiveis.add("6, 11");
        sugestoesPossiveis.add("7, 5");
        sugestoesPossiveis.add("7, 6");
        sugestoesPossiveis.add("7, 7");
        sugestoesPossiveis.add("8, 4");
        sugestoesPossiveis.add("8, 6");
        sugestoesPossiveis.add("8, 8");
        sugestoesPossiveis.add("9, 3");
        sugestoesPossiveis.add("9, 6");
        sugestoesPossiveis.add("9, 9");
        sugestoesPossiveis.add("10, 2");
        sugestoesPossiveis.add("10, 6");
        sugestoesPossiveis.add("10, 10");
        sugestoesPossiveis.add("11, 1");
        sugestoesPossiveis.add("11, 6");
        sugestoesPossiveis.add("11, 11");
        Collections.sort(sugestoesPossiveis);
        Collections.sort(sugestoesRecebidas);
        assertThat(sugestoesRecebidas, is(sugestoesPossiveis));
    }

    @Test
    public void test07PactoEntreRainhas(){
        int tabuleiroMatrix [][] = new int[4][4];
        for (int x = 0; x < tabuleiroMatrix.length; x++) {
            for (int y = 0; y < tabuleiroMatrix[x].length; y++) {
                tabuleiroMatrix[x][y] = 0;
            }
        }
        Rainha rainhaPreta = new Rainha(1, GestorDeJogo.RAINHA, GestorDeJogo.PRETA, "Rainha");
        Rainha rainhaBranca = new Rainha(1, GestorDeJogo.RAINHA, GestorDeJogo.BRANCA, "Rainha");
        Map<Integer, CrazyPiece> pecas = new HashMap<>();
        pecas.put(rainhaBranca.getId(), rainhaBranca);
        pecas.put(rainhaPreta.getId(), rainhaPreta);
        tabuleiroMatrix[2][2] = rainhaBranca.getId();
        tabuleiroMatrix[1][1] = rainhaPreta.getId();
        assertFalse(rainhaPreta.verificarSeMove(1,1, 2, 2, pecas, tabuleiroMatrix, 0));
    }

    @Test
    public void test08MovimentosImpossiveis(){
        int tabuleiroMatrix [][] = new int[12][12];
        for (int x = 0; x < tabuleiroMatrix.length; x++) {
            for (int y = 0; y < tabuleiroMatrix[x].length; y++) {
                tabuleiroMatrix[x][y] = 0;
            }
        }
        Rainha rainha = new Rainha(1, GestorDeJogo.RAINHA, GestorDeJogo.PRETA, "Rainha");
        Rei rei = new Rei(2, GestorDeJogo.REI, GestorDeJogo.PRETA, "Rei");
        Rei king = new Rei(3, GestorDeJogo.REI, GestorDeJogo.PRETA, "King");
        Map<Integer, CrazyPiece> pecas = new HashMap<>();
        pecas.put(rei.getId(), rei);
        pecas.put(king.getId(), king);
        pecas.put(rainha.getId(), rainha);
        tabuleiroMatrix[6][6] = rainha.getId();
        for (int i = 1; i <= 4; i++) {
            tabuleiroMatrix[6 + i][6] = rei.getId();
            tabuleiroMatrix[6 - i][6] = king.getId();
            assertFalse(rainha.verificarSeMove(6, 6, 0, 6, pecas, tabuleiroMatrix, 0));
            assertFalse(rainha.verificarSeMove(6, 6, 11, 6, pecas, tabuleiroMatrix, 0));
            tabuleiroMatrix[6 + i][6] = 0;
            tabuleiroMatrix[6 - i][6] = 0;
        }
        for (int i = 1; i <= 4; i++) {
            tabuleiroMatrix[6][6 + i] = rei.getId();
            tabuleiroMatrix[6][6 - i] = king.getId();
            assertFalse(rainha.verificarSeMove(6, 6, 6, 0, pecas, tabuleiroMatrix, 0));
            assertFalse(rainha.verificarSeMove(6, 6, 6, 11, pecas, tabuleiroMatrix, 0));
            tabuleiroMatrix[6][6 + i] = 0;
            tabuleiroMatrix[6][6 - i] = 0;
        }
        for (int i = 1; i <= 4; i++) {
            tabuleiroMatrix[6 + i][6 + i] = rei.getId();
            assertFalse(rainha.verificarSeMove(6, 6, 6 + (i + 1), 6 + (i + 1), pecas, tabuleiroMatrix, 0));
            tabuleiroMatrix[6 + i][6 + i] = 0;
            tabuleiroMatrix[6 - i][6 + i] = rei.getId();
            assertFalse(rainha.verificarSeMove(6, 6, 6 - (i + 1), 6 + (i + 1), pecas, tabuleiroMatrix, 0));
            tabuleiroMatrix[6 - i][6 + i] = 0;
            tabuleiroMatrix[6 - i][6 - i] = rei.getId();
            assertFalse(rainha.verificarSeMove(6, 6, 6 - (i + 1), 6 - (i + 1), pecas, tabuleiroMatrix, 0));
            tabuleiroMatrix[6 - i][6 - i] = 0;
            tabuleiroMatrix[6 + i][6 - i] = rei.getId();
            assertFalse(rainha.verificarSeMove(6, 6, 6 + (i + 1), 6 - (i + 1), pecas, tabuleiroMatrix, 0));
            tabuleiroMatrix[6 + i][6 - i] = 0;
        }
    }
}
