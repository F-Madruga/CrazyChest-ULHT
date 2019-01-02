package pt.ulusofona.lp2.crazyChess;

import org.junit.Test;

import java.util.*;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class TestPadreDaVila {

    @Test
    public void test01VerificaMovimentosPossiveis() {
        int tabuleiroMatrix [][] = new int[8][8];
        for (int x = 0; x < tabuleiroMatrix.length; x++) {
            for (int y = 0; y < tabuleiroMatrix[x].length; y++) {
                tabuleiroMatrix[x][y] = 0;
            }
        }
        PadreDaVila padre = new PadreDaVila(1, GestorDeJogo.PADREDAVILA, GestorDeJogo.PRETA, "Padre da Vila");
        Map<Integer, CrazyPiece> pecas = new HashMap<>();
        pecas.put(padre.getId(), padre);
        tabuleiroMatrix[4][4] = padre.getId();
        padre.setCoordenadas(4,4);
        assertTrue(padre.verificarSeMove(4,4, 3, 3, pecas, tabuleiroMatrix, 0));
        assertTrue(padre.verificarSeMove(4,4, 2, 2, pecas, tabuleiroMatrix, 0));
        assertTrue(padre.verificarSeMove(4,4, 1, 1, pecas, tabuleiroMatrix, 0));
        assertTrue(padre.verificarSeMove(4,4, 5, 5, pecas, tabuleiroMatrix, 0));
        assertTrue(padre.verificarSeMove(4,4, 6, 6, pecas, tabuleiroMatrix, 0));
        assertTrue(padre.verificarSeMove(4,4, 7, 7, pecas, tabuleiroMatrix, 0));
        assertTrue(padre.verificarSeMove(4,4, 3, 5, pecas, tabuleiroMatrix, 0));
        assertTrue(padre.verificarSeMove(4,4, 2, 6, pecas, tabuleiroMatrix, 0));
        assertTrue(padre.verificarSeMove(4,4, 1, 7, pecas, tabuleiroMatrix, 0));
        assertTrue(padre.verificarSeMove(4,4, 5, 3, pecas, tabuleiroMatrix, 0));
        assertTrue(padre.verificarSeMove(4,4, 6, 2, pecas, tabuleiroMatrix, 0));
        assertTrue(padre.verificarSeMove(4,4, 7, 1, pecas, tabuleiroMatrix, 0));
    }

    @Test
    public void test02VerificaMovimentosComPecas(){
        int tabuleiroMatrix [][] = new int[8][8];
        for (int x = 0; x < tabuleiroMatrix.length; x++) {
            for (int y = 0; y < tabuleiroMatrix[x].length; y++) {
                tabuleiroMatrix[x][y] = 0;
            }
        }
        PadreDaVila padre = new PadreDaVila(1, GestorDeJogo.PADREDAVILA, GestorDeJogo.PRETA, "Padre da Vila");
        Map<Integer, CrazyPiece> pecas = new HashMap<>();
        pecas.put(padre.getId(), padre);
        tabuleiroMatrix[4][4] = padre.getId();
        padre.setCoordenadas(4,4);
        Rei rei = new Rei(2, GestorDeJogo.REI, GestorDeJogo.PRETA, "Rei");
        pecas.put(rei.getId(), rei);
        for (int i = 1; i <= 2; i++) {
            tabuleiroMatrix[4 + i][4 + i] = rei.getId();
            assertFalse(padre.verificarSeMove(4, 4, 4 + (i + 1), 4 + (i + 1), pecas, tabuleiroMatrix, 0));
            tabuleiroMatrix[4 + i][4 + i] = 0;
            tabuleiroMatrix[4 - i][4 + i] = rei.getId();
            assertFalse(padre.verificarSeMove(4, 4, 4 - (i + 1), 4 + (i + 1), pecas, tabuleiroMatrix, 0));
            tabuleiroMatrix[4 - i][4 + i] = 0;
            tabuleiroMatrix[4 - i][4 - i] = rei.getId();
            assertFalse(padre.verificarSeMove(4, 4, 4 - (i + 1), 4 - (i + 1), pecas, tabuleiroMatrix, 0));
            tabuleiroMatrix[4 - i][4 - i] = 0;
            tabuleiroMatrix[4 + i][4 - i] = rei.getId();
            assertFalse(padre.verificarSeMove(4, 4, 4 + (i + 1), 4 - (i + 1), pecas, tabuleiroMatrix, 0));
            tabuleiroMatrix[4 + i][4 - i] = 0;
        }
    }

    @Test
    public void test03DistanciaRainha(){
        int tabuleiroMatrix [][] = new int[8][8];
        for (int x = 0; x < tabuleiroMatrix.length; x++) {
            for (int y = 0; y < tabuleiroMatrix[x].length; y++) {
                tabuleiroMatrix[x][y] = 0;
            }
        }
        PadreDaVila padre = new PadreDaVila(1, GestorDeJogo.PADREDAVILA, GestorDeJogo.PRETA, "Padre da Vila");
        Rainha rainhaPreta = new Rainha(2, GestorDeJogo.RAINHA, GestorDeJogo.PRETA, "Rainha Preta");
        Rainha rainhaBranca = new Rainha (3, GestorDeJogo.RAINHA, GestorDeJogo.BRANCA, "Rainha Branca");
        Map<Integer, CrazyPiece> pecas = new HashMap<>();
        pecas.put(padre.getId(), padre);
        pecas.put(rainhaBranca.getId(), rainhaBranca);
        pecas.put(rainhaPreta.getId(), rainhaPreta);
        tabuleiroMatrix[4][4] = padre.getId();
        tabuleiroMatrix[5][4] = rainhaBranca.getId();
        assertFalse(padre.verificarSeMove(4, 4, 5, 5, pecas, tabuleiroMatrix, 0));
        tabuleiroMatrix[5][4] = 0;
        tabuleiroMatrix[5][4] = rainhaPreta.getId();
        assertTrue(padre.verificarSeMove(4, 4, 5, 5, pecas, tabuleiroMatrix, 0));
        tabuleiroMatrix[5][4] = 0;

        tabuleiroMatrix[6][4] = rainhaBranca.getId();
        assertFalse(padre.verificarSeMove(4, 4, 5, 5, pecas, tabuleiroMatrix, 0));
        tabuleiroMatrix[6][4] = 0;
        tabuleiroMatrix[6][4] = rainhaPreta.getId();
        assertTrue(padre.verificarSeMove(4, 4, 5, 5, pecas, tabuleiroMatrix, 0));
        tabuleiroMatrix[6][4] = 0;

        tabuleiroMatrix[4][5] = rainhaBranca.getId();
        assertFalse(padre.verificarSeMove(4, 4, 5, 5, pecas, tabuleiroMatrix, 0));
        tabuleiroMatrix[4][5] = 0;
        tabuleiroMatrix[4][5] = rainhaPreta.getId();
        assertTrue(padre.verificarSeMove(4, 4, 5, 5, pecas, tabuleiroMatrix, 0));
        tabuleiroMatrix[4][5] = 0;

        tabuleiroMatrix[6][5] = rainhaBranca.getId();
        assertFalse(padre.verificarSeMove(4, 4, 5, 5, pecas, tabuleiroMatrix, 0));
        tabuleiroMatrix[6][5] = 0;
        tabuleiroMatrix[6][5] = rainhaPreta.getId();
        assertTrue(padre.verificarSeMove(4, 4, 5, 5, pecas, tabuleiroMatrix, 0));
        tabuleiroMatrix[6][5] = 0;

        tabuleiroMatrix[4][6] = rainhaBranca.getId();
        assertFalse(padre.verificarSeMove(4, 4, 5, 5, pecas, tabuleiroMatrix, 0));
        tabuleiroMatrix[4][6] = 0;
        tabuleiroMatrix[4][6] = rainhaPreta.getId();
        assertTrue(padre.verificarSeMove(4, 4, 5, 5, pecas, tabuleiroMatrix, 0));
        tabuleiroMatrix[4][6] = 0;

        tabuleiroMatrix[5][6] = rainhaBranca.getId();
        assertFalse(padre.verificarSeMove(4, 4, 5, 5, pecas, tabuleiroMatrix, 0));
        tabuleiroMatrix[5][6] = 0;
        tabuleiroMatrix[5][6] = rainhaPreta.getId();
        assertTrue(padre.verificarSeMove(4, 4, 5, 5, pecas, tabuleiroMatrix, 0));
        tabuleiroMatrix[5][6] = 0;

        tabuleiroMatrix[6][6] = rainhaBranca.getId();
        assertFalse(padre.verificarSeMove(4, 4, 5, 5, pecas, tabuleiroMatrix, 0));
        tabuleiroMatrix[6][6] = 0;
        tabuleiroMatrix[6][6] = rainhaPreta.getId();
        assertTrue(padre.verificarSeMove(4, 4, 5, 5, pecas, tabuleiroMatrix, 0));
        tabuleiroMatrix[6][6] = 0;
    }

    @Test
    public void test04PNGPreta() {
        PadreDaVila padre = new PadreDaVila(1, GestorDeJogo.PADREDAVILA, GestorDeJogo.PRETA, "Padre da Vila");
        assertEquals("preto_padre.png", padre.getImagePNG());
    }

    @Test
    public void test05PNGNula() {
        PadreDaVila padre = new PadreDaVila(1, GestorDeJogo.PADREDAVILA, -1, "Padre da Vila");
        assertEquals(null, padre.getImagePNG());
    }

    @Test
    public void test06PNGBranca() {
        PadreDaVila padre = new PadreDaVila(1, GestorDeJogo.PADREDAVILA, GestorDeJogo.BRANCA, "Padre da Vila");
        assertEquals("branco_padre.png", padre.getImagePNG());
    }

    @Test
    public void test07Nome() {
        PadreDaVila padre = new PadreDaVila(1, GestorDeJogo.PADREDAVILA, GestorDeJogo.PRETA, "Padre da Vila");
        assertEquals("Padre da Vila", padre.getNome());
    }

    @Test
    public void test08ValorRelativo() {
        PadreDaVila padre = new PadreDaVila(1, GestorDeJogo.PADREDAVILA, GestorDeJogo.PRETA, "Padre da Vila");
        assertEquals("3", padre.getValorRelativo());
    }

    @Test
    public void test09Sugestoes(){
        int tabuleiroMatrix [][] = new int[8][8];
        for (int x = 0; x < tabuleiroMatrix.length; x++) {
            for (int y = 0; y < tabuleiroMatrix[x].length; y++) {
                tabuleiroMatrix[x][y] = 0;
            }
        }
        Map<Integer, CrazyPiece> pecas = new HashMap<>();
        PadreDaVila padre = new PadreDaVila(1, GestorDeJogo.PADREDAVILA, GestorDeJogo.PRETA, "Padre da Vila");
        pecas.put(padre.getId(), padre);
        tabuleiroMatrix[4][4] = padre.getId();
        List<String> sugestoesRecebidas = padre.darSugestao(4,  4, pecas, tabuleiroMatrix, 0);
        List<String> sugestoesPossiveis = new ArrayList<>();
        sugestoesPossiveis.add("1, 1");
        sugestoesPossiveis.add("1, 7");
        sugestoesPossiveis.add("2, 2");
        sugestoesPossiveis.add("2, 6");
        sugestoesPossiveis.add("3, 3");
        sugestoesPossiveis.add("3, 5");
        sugestoesPossiveis.add("5, 3");
        sugestoesPossiveis.add("5, 5");
        sugestoesPossiveis.add("6, 2");
        sugestoesPossiveis.add("6, 6");
        sugestoesPossiveis.add("7, 1");
        sugestoesPossiveis.add("7, 7");
        Collections.sort(sugestoesPossiveis);
        Collections.sort(sugestoesRecebidas);
        assertThat(sugestoesRecebidas, is(sugestoesPossiveis));
    }

    @Test
    public void test03DistanciaJokerComMascaraRainha(){
        int tabuleiroMatrix [][] = new int[8][8];
        for (int x = 0; x < tabuleiroMatrix.length; x++) {
            for (int y = 0; y < tabuleiroMatrix[x].length; y++) {
                tabuleiroMatrix[x][y] = 0;
            }
        }
        PadreDaVila padre = new PadreDaVila(1, GestorDeJogo.PADREDAVILA, GestorDeJogo.PRETA, "Padre da Vila");
        Joker rainhaPreta = new Joker(2, GestorDeJogo.JOKER, GestorDeJogo.PRETA, "Rainha Preta");
        Joker rainhaBranca = new Joker (3, GestorDeJogo.JOKER, GestorDeJogo.BRANCA, "Rainha Branca");
        Map<Integer, CrazyPiece> pecas = new HashMap<>();
        pecas.put(padre.getId(), padre);
        pecas.put(rainhaBranca.getId(), rainhaBranca);
        pecas.put(rainhaPreta.getId(), rainhaPreta);
        tabuleiroMatrix[4][4] = padre.getId();
        tabuleiroMatrix[5][4] = rainhaBranca.getId();
        assertFalse(padre.verificarSeMove(4, 4, 5, 5, pecas, tabuleiroMatrix, 0));
        tabuleiroMatrix[5][4] = 0;
        tabuleiroMatrix[5][4] = rainhaPreta.getId();
        assertTrue(padre.verificarSeMove(4, 4, 5, 5, pecas, tabuleiroMatrix, 0));
        tabuleiroMatrix[5][4] = 0;

        tabuleiroMatrix[6][4] = rainhaBranca.getId();
        assertFalse(padre.verificarSeMove(4, 4, 5, 5, pecas, tabuleiroMatrix, 0));
        tabuleiroMatrix[6][4] = 0;
        tabuleiroMatrix[6][4] = rainhaPreta.getId();
        assertTrue(padre.verificarSeMove(4, 4, 5, 5, pecas, tabuleiroMatrix, 0));
        tabuleiroMatrix[6][4] = 0;

        tabuleiroMatrix[4][5] = rainhaBranca.getId();
        assertFalse(padre.verificarSeMove(4, 4, 5, 5, pecas, tabuleiroMatrix, 0));
        tabuleiroMatrix[4][5] = 0;
        tabuleiroMatrix[4][5] = rainhaPreta.getId();
        assertTrue(padre.verificarSeMove(4, 4, 5, 5, pecas, tabuleiroMatrix, 0));
        tabuleiroMatrix[4][5] = 0;

        tabuleiroMatrix[6][5] = rainhaBranca.getId();
        assertFalse(padre.verificarSeMove(4, 4, 5, 5, pecas, tabuleiroMatrix, 0));
        tabuleiroMatrix[6][5] = 0;
        tabuleiroMatrix[6][5] = rainhaPreta.getId();
        assertTrue(padre.verificarSeMove(4, 4, 5, 5, pecas, tabuleiroMatrix, 0));
        tabuleiroMatrix[6][5] = 0;

        tabuleiroMatrix[4][6] = rainhaBranca.getId();
        assertFalse(padre.verificarSeMove(4, 4, 5, 5, pecas, tabuleiroMatrix, 0));
        tabuleiroMatrix[4][6] = 0;
        tabuleiroMatrix[4][6] = rainhaPreta.getId();
        assertTrue(padre.verificarSeMove(4, 4, 5, 5, pecas, tabuleiroMatrix, 0));
        tabuleiroMatrix[4][6] = 0;

        tabuleiroMatrix[5][6] = rainhaBranca.getId();
        assertFalse(padre.verificarSeMove(4, 4, 5, 5, pecas, tabuleiroMatrix, 0));
        tabuleiroMatrix[5][6] = 0;
        tabuleiroMatrix[5][6] = rainhaPreta.getId();
        assertTrue(padre.verificarSeMove(4, 4, 5, 5, pecas, tabuleiroMatrix, 0));
        tabuleiroMatrix[5][6] = 0;

        tabuleiroMatrix[6][6] = rainhaBranca.getId();
        assertFalse(padre.verificarSeMove(4, 4, 5, 5, pecas, tabuleiroMatrix, 0));
        tabuleiroMatrix[6][6] = 0;
        tabuleiroMatrix[6][6] = rainhaPreta.getId();
        assertTrue(padre.verificarSeMove(4, 4, 5, 5, pecas, tabuleiroMatrix, 0));
        tabuleiroMatrix[6][6] = 0;
    }
}
