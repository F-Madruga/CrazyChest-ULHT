package pt.ulusofona.lp2.crazyChess;

import org.junit.Test;

import java.util.*;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class TestTorreH {

    @Test
    public void test01VerificaMovimentosPossiveis() {
        int tabuleiroMatrix [][] = new int[4][4];
        for (int x = 0; x < tabuleiroMatrix.length; x++) {
            for (int y = 0; y < tabuleiroMatrix[x].length; y++) {
                tabuleiroMatrix[x][y] = 0;
            }
        }
        TorreH torre = new TorreH(1, GestorDeJogo.TORREH, GestorDeJogo.PRETA, "TorreH");
        Map<Integer, CrazyPiece> pecas = new HashMap<>();
        pecas.put(torre.getId(), torre);
        tabuleiroMatrix[1][1] = torre.getId();
        assertTrue(torre.verificarSeMove(1,1, 3, 1, pecas, tabuleiroMatrix, 0));
        Rei rei = new Rei(1, GestorDeJogo.REI, GestorDeJogo.PRETA, "Rei");
        pecas.put(rei.getId(), rei);
        tabuleiroMatrix[2][1] = rei.getId();
        assertFalse(torre.verificarSeMove(1,1, 3, 1, pecas, tabuleiroMatrix, 0));
        tabuleiroMatrix[2][1] = 0;
        pecas.remove(rei.getId(), rei);
        assertFalse(torre.verificarSeMove(1,1, 2, 2, pecas, tabuleiroMatrix, 0));
        assertFalse(torre.verificarSeMove(1,1, 1, 2, pecas, tabuleiroMatrix, 0));
    }

    @Test
    public void test02PNGPreta(){
        TorreH torre = new TorreH(1, GestorDeJogo.TORREH, GestorDeJogo.PRETA, "TorreH");
        assertEquals("preto_torreH.png", torre.getImagePNG());
    }

    @Test
    public void test03PNGNula() {
        TorreH torre = new TorreH(1, GestorDeJogo.TORREH, -1, "TorreH");
        assertEquals(null, torre.getImagePNG());
    }

    @Test
    public void test04PNGBranca() {
        TorreH torre = new TorreH(1, GestorDeJogo.TORREH, GestorDeJogo.BRANCA, "TorreH");
        assertEquals("branco_torreH.png", torre.getImagePNG());
    }

    @Test
    public void test05Nome() {
        TorreH torre = new TorreH(1, GestorDeJogo.TORREH, GestorDeJogo.BRANCA, "TorreH");
        assertEquals("TorreH", torre.getNome());
    }

    @Test
    public void test06ValorRelativo() {
        TorreH torre = new TorreH(1, GestorDeJogo.TORREH, GestorDeJogo.BRANCA, "TorreH");
        assertEquals("3", torre.getValorRelativo());
    }

    @Test
    public void test07Sugestoes(){
        int tabuleiroMatrix [][] = new int[12][12];
        for (int x = 0; x < tabuleiroMatrix.length; x++) {
            for (int y = 0; y < tabuleiroMatrix[x].length; y++) {
                tabuleiroMatrix[x][y] = 0;
            }
        }
        Map<Integer, CrazyPiece> pecas = new HashMap<>();
        TorreH torre = new TorreH(1, GestorDeJogo.TORREH, GestorDeJogo.PRETA, "TorreH");
        pecas.put(torre.getId(), torre);
        tabuleiroMatrix[1][1] = torre.getId();
        torre.setCoordenadas(1,1);
        List<String> sugestoesRecebidas = torre.darSugestao(1,  1, pecas, tabuleiroMatrix, 0);
        List<String> sugestoesPossiveis = new ArrayList<>();
        sugestoesPossiveis.add("0, 1");
        sugestoesPossiveis.add("2, 1");
        sugestoesPossiveis.add("3, 1");
        sugestoesPossiveis.add("4, 1");
        sugestoesPossiveis.add("5, 1");
        sugestoesPossiveis.add("6, 1");
        sugestoesPossiveis.add("7, 1");
        sugestoesPossiveis.add("8, 1");
        sugestoesPossiveis.add("9, 1");
        sugestoesPossiveis.add("10, 1");
        sugestoesPossiveis.add("11, 1");
        Collections.sort(sugestoesPossiveis);
        Collections.sort(sugestoesRecebidas);
        assertThat(sugestoesRecebidas, is(sugestoesPossiveis));
    }
}
