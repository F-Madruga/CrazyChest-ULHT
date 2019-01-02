package pt.ulusofona.lp2.crazyChess;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TestJoker {
    @Test
    public void test01PNGPreta() {
        Joker joker = new Joker(1, GestorDeJogo.JOKER, GestorDeJogo.PRETA, "Joker");
        assertEquals("preto_joker.png", joker.getImagePNG());
    }
    @Test
    public void test02PNGNula() {
        Joker joker = new Joker(1, GestorDeJogo.JOKER, -1, "Joker");
        assertEquals(null, joker.getImagePNG());
    }
    @Test
    public void test03PNGBranca() {
        Joker joker = new Joker(1, GestorDeJogo.JOKER, GestorDeJogo.BRANCA, "Joker");
        assertEquals("branco_joker.png", joker.getImagePNG());
    }
    @Test
    public void test04Nome() {
        Joker joker = new Joker(1, GestorDeJogo.JOKER, GestorDeJogo.PRETA, "Joker");
        assertEquals("Joker/Rainha", joker.getNome());
        Joker.ROTACAOTIPOPECA++;
        assertEquals("Joker/Ponei Mágico", joker.getNome());
        Joker.ROTACAOTIPOPECA++;
        assertEquals("Joker/Padre da Vila", joker.getNome());
        Joker.ROTACAOTIPOPECA++;
        assertEquals("Joker/TorreH", joker.getNome());
        Joker.ROTACAOTIPOPECA++;
        assertEquals("Joker/TorreV", joker.getNome());
        Joker.ROTACAOTIPOPECA++;
        assertEquals("Joker/Lebre", joker.getNome());
        Joker.ROTACAOTIPOPECA = 0;
    }
    @Test
    public void test05ValorRelativo() {
        Joker joker = new Joker(1, GestorDeJogo.JOKER, GestorDeJogo.PRETA, "Joker");
        assertEquals("4", joker.getValorRelativo());
    }
    @Test
    public void test06GetMascara(){
        Joker joker = new Joker(1, GestorDeJogo.JOKER, GestorDeJogo.PRETA, "Joker");
        assertEquals(GestorDeJogo.RAINHA, joker.getMascara().getIdTipo());
        Joker.ROTACAOTIPOPECA++;
        assertEquals(GestorDeJogo.PONEIMAGICO, joker.getMascara().getIdTipo());
        Joker.ROTACAOTIPOPECA++;
        assertEquals(GestorDeJogo.PADREDAVILA, joker.getMascara().getIdTipo());
        Joker.ROTACAOTIPOPECA++;
        assertEquals(GestorDeJogo.TORREH, joker.getMascara().getIdTipo());
        Joker.ROTACAOTIPOPECA++;
        assertEquals(GestorDeJogo.TORREV, joker.getMascara().getIdTipo());
        Joker.ROTACAOTIPOPECA++;
        assertEquals(GestorDeJogo.LEBRE, joker.getMascara().getIdTipo());
        Joker.ROTACAOTIPOPECA++;
        assertEquals(GestorDeJogo.RAINHA, joker.getMascara().getIdTipo());
        Joker.ROTACAOTIPOPECA++;
        assertEquals(GestorDeJogo.PONEIMAGICO, joker.getMascara().getIdTipo());
        Joker.ROTACAOTIPOPECA++;
        assertEquals(GestorDeJogo.PADREDAVILA, joker.getMascara().getIdTipo());
        Joker.ROTACAOTIPOPECA++;
        assertEquals(GestorDeJogo.TORREH, joker.getMascara().getIdTipo());
        Joker.ROTACAOTIPOPECA++;
        assertEquals(GestorDeJogo.TORREV, joker.getMascara().getIdTipo());
        Joker.ROTACAOTIPOPECA++;
        assertEquals(GestorDeJogo.LEBRE, joker.getMascara().getIdTipo());
        Joker.ROTACAOTIPOPECA++;
        assertEquals(GestorDeJogo.RAINHA, joker.getMascara().getIdTipo());
        Joker.ROTACAOTIPOPECA = 0;
    }
    @Test
    public void test07Movimentos(){
        Joker joker = new Joker(1, GestorDeJogo.JOKER, GestorDeJogo.PRETA, "Joker");
        int tabuleiroMatrix [][] = new int[8][8];
        for (int x = 0; x < tabuleiroMatrix.length; x++) {
            for (int y = 0; y < tabuleiroMatrix[x].length; y++) {
                tabuleiroMatrix[x][y] = 0;
            }
        }
        Map<Integer, CrazyPiece> pecas = new HashMap<>();
        pecas.put(joker.getId(), joker);
        tabuleiroMatrix[4][4] = joker.getId();
        assertTrue(joker.verificarSeMove(4,4, 5,5, pecas, tabuleiroMatrix, 0));
        Joker.ROTACAOTIPOPECA++;
        assertTrue(joker.verificarSeMove(4,4, 6,6, pecas, tabuleiroMatrix, 0));
        Joker.ROTACAOTIPOPECA++;
        assertTrue(joker.verificarSeMove(4,4, 6,6, pecas, tabuleiroMatrix, 0));
        Joker.ROTACAOTIPOPECA++;
        assertTrue(joker.verificarSeMove(4,4, 5,4, pecas, tabuleiroMatrix, 0));
        Joker.ROTACAOTIPOPECA++;
        assertTrue(joker.verificarSeMove(4,4, 4,5, pecas, tabuleiroMatrix, 0));
        Joker.ROTACAOTIPOPECA++;
        assertTrue(joker.verificarSeMove(4,4, 5,5, pecas, tabuleiroMatrix, 0));
        Joker.ROTACAOTIPOPECA = 0;
    }
}
