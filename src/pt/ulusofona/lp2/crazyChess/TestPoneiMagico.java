package pt.ulusofona.lp2.crazyChess;

import com.sun.org.apache.regexp.internal.RE;
import org.junit.Test;

import java.util.*;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class TestPoneiMagico {
    @Test
    public void test01PNGPreta() {
        PoneiMagico ponei = new PoneiMagico(1, GestorDeJogo.PONEIMAGICO, GestorDeJogo.PRETA, "Ponei Mágico");
        assertEquals("preto_ponei.png", ponei.getImagePNG());
    }
    @Test
    public void test02PNGNula() {
        PoneiMagico ponei = new PoneiMagico(1, GestorDeJogo.PONEIMAGICO, -1, "Ponei Mágico");
        assertEquals(null, ponei.getImagePNG());
    }
    @Test
    public void test03PNGBranca() {
        PoneiMagico ponei = new PoneiMagico(1, GestorDeJogo.PONEIMAGICO, GestorDeJogo.BRANCA, "Ponei Mágico");
        assertEquals("branco_ponei.png", ponei.getImagePNG());
    }
    @Test
    public void test04Nome() {
        PoneiMagico ponei = new PoneiMagico(1, GestorDeJogo.PONEIMAGICO, GestorDeJogo.PRETA, "Ponei Mágico");
        assertEquals("Ponei Mágico", ponei.getNome());
    }
    @Test
    public void test05ValorRelativo() {
        PoneiMagico ponei = new PoneiMagico(1, GestorDeJogo.PONEIMAGICO, GestorDeJogo.PRETA, "Ponei Mágico");
        assertEquals("5", ponei.getValorRelativo());
    }
    @Test
    public void test06Sugestoes(){
        PoneiMagico ponei = new PoneiMagico(1, GestorDeJogo.PONEIMAGICO, GestorDeJogo.PRETA, "Ponei Mágico");
        int tabuleiroMatrix [][] = new int[8][8];
        for (int x = 0; x < tabuleiroMatrix.length; x++) {
            for (int y = 0; y < tabuleiroMatrix[x].length; y++) {
                tabuleiroMatrix[x][y] = 0;
            }
        }
        Map<Integer, CrazyPiece> pecas = new HashMap<>();
        pecas.put(ponei.getId(), ponei);
        tabuleiroMatrix[4][4] = ponei.getId();
        List<String> sugestoesRecebidas = ponei.darSugestao(4,  4, pecas, tabuleiroMatrix, 0);
        List<String> sugestoesPossiveis = new ArrayList<>();
        sugestoesPossiveis.add("2, 2");
        sugestoesPossiveis.add("2, 6");
        sugestoesPossiveis.add("6, 2");
        sugestoesPossiveis.add("6, 6");
        Collections.sort(sugestoesPossiveis);
        Collections.sort(sugestoesRecebidas);
        assertThat(sugestoesRecebidas, is(sugestoesPossiveis));
    }
    @Test
    public void test07SaltarReis(){
        PoneiMagico ponei = new PoneiMagico(1, GestorDeJogo.PONEIMAGICO, GestorDeJogo.PRETA, "Ponei Mágico");
        Map<Integer, CrazyPiece> pecas = new HashMap<>();
        pecas.put(ponei.getId(), ponei);
        int tabuleiroMatrix [][] = new int[8][8];
        for (int x = 0; x < tabuleiroMatrix.length; x++) {
            for (int y = 0; y < tabuleiroMatrix[x].length; y++) {
                tabuleiroMatrix[x][y] = 0;
            }
        }
        tabuleiroMatrix[4][4] = ponei.getId();
        Rei rei = new Rei(2, GestorDeJogo.REI, GestorDeJogo.PRETA, "Rei");
        Rei rei2 = new Rei(3, GestorDeJogo.REI, GestorDeJogo.PRETA, "Rei2");
        pecas.put(rei.getId(), rei);
        pecas.put(rei2.getId(), rei2);
        tabuleiroMatrix[3][4] = rei.getId();
        tabuleiroMatrix[4][3] = rei2.getId();
        assertFalse(ponei.verificarSeMove(4,4, 2, 2, pecas, tabuleiroMatrix, 0));
        tabuleiroMatrix[3][4] = 0;
        tabuleiroMatrix[4][3] = 0;
        tabuleiroMatrix[2][4] = rei.getId();
        tabuleiroMatrix[4][2] = rei2.getId();
        assertFalse(ponei.verificarSeMove(4,4, 2, 2, pecas, tabuleiroMatrix, 0));
        tabuleiroMatrix[2][4] = 0;
        tabuleiroMatrix[4][2] = 0;
        tabuleiroMatrix[2][3] = rei.getId();
        tabuleiroMatrix[3][2] = rei2.getId();
        assertFalse(ponei.verificarSeMove(4,4, 2, 2, pecas, tabuleiroMatrix, 0));
        tabuleiroMatrix[2][3] = 0;
        tabuleiroMatrix[3][2] = 0;

        tabuleiroMatrix[4][3] = rei.getId();
        tabuleiroMatrix[5][4] = rei2.getId();
        assertFalse(ponei.verificarSeMove(4,4, 6, 1, pecas, tabuleiroMatrix, 0));
        tabuleiroMatrix[4][3] = 0;
        tabuleiroMatrix[5][4] = 0;
        tabuleiroMatrix[4][2] = rei.getId();
        tabuleiroMatrix[6][4] = rei2.getId();
        assertFalse(ponei.verificarSeMove(4,4, 6, 1, pecas, tabuleiroMatrix, 0));
        tabuleiroMatrix[6][4] = 0;
        tabuleiroMatrix[4][2] = 0;
        tabuleiroMatrix[6][3] = rei.getId();
        tabuleiroMatrix[5][2] = rei2.getId();
        assertFalse(ponei.verificarSeMove(4,4, 6, 1, pecas, tabuleiroMatrix, 0));
        tabuleiroMatrix[6][3] = 0;
        tabuleiroMatrix[5][2] = 0;

        tabuleiroMatrix[3][4] = rei.getId();
        tabuleiroMatrix[4][5] = rei2.getId();
        assertFalse(ponei.verificarSeMove(4,4, 2, 6, pecas, tabuleiroMatrix, 0));
        tabuleiroMatrix[4][5] = 0;
        tabuleiroMatrix[3][4] = 0;
        tabuleiroMatrix[4][6] = rei.getId();
        tabuleiroMatrix[2][4] = rei2.getId();
        assertFalse(ponei.verificarSeMove(4,4, 2, 6, pecas, tabuleiroMatrix, 0));
        tabuleiroMatrix[2][4] = 0;
        tabuleiroMatrix[4][6] = 0;
        tabuleiroMatrix[2][5] = rei.getId();
        tabuleiroMatrix[3][6] = rei2.getId();
        assertFalse(ponei.verificarSeMove(4,4, 2, 6, pecas, tabuleiroMatrix, 0));
        tabuleiroMatrix[2][5] = 0;
        tabuleiroMatrix[3][6] = 0;

        tabuleiroMatrix[5][4] = rei.getId();
        tabuleiroMatrix[4][5] = rei2.getId();
        assertFalse(ponei.verificarSeMove(4,4, 6, 6, pecas, tabuleiroMatrix, 0));
        tabuleiroMatrix[4][5] = 0;
        tabuleiroMatrix[5][4] = 0;
        tabuleiroMatrix[4][6] = rei.getId();
        tabuleiroMatrix[6][4] = rei2.getId();
        assertFalse(ponei.verificarSeMove(4,4, 6, 6, pecas, tabuleiroMatrix, 0));
        tabuleiroMatrix[6][4] = 0;
        tabuleiroMatrix[4][6] = 0;
        tabuleiroMatrix[6][5] = rei.getId();
        tabuleiroMatrix[5][6] = rei2.getId();
        assertFalse(ponei.verificarSeMove(4,4, 6, 6, pecas, tabuleiroMatrix, 0));
        tabuleiroMatrix[6][5] = 0;
        tabuleiroMatrix[5][6] = 0;

        tabuleiroMatrix[4][3] = rei.getId();
        assertTrue(ponei.verificarSeMove(4,4, 2, 2, pecas, tabuleiroMatrix, 0));
        tabuleiroMatrix[4][3] = 0;
        tabuleiroMatrix[4][2] = rei.getId();
        assertTrue(ponei.verificarSeMove(4,4, 2, 2, pecas, tabuleiroMatrix, 0));
        tabuleiroMatrix[4][2] = 0;
        tabuleiroMatrix[3][2] = rei.getId();
        assertTrue(ponei.verificarSeMove(4,4, 2, 2, pecas, tabuleiroMatrix, 0));
        tabuleiroMatrix[3][2] = 0;

        tabuleiroMatrix[3][4] = rei.getId();
        assertTrue(ponei.verificarSeMove(4,4, 2, 2, pecas, tabuleiroMatrix, 0));
        tabuleiroMatrix[3][4] = 0;
        tabuleiroMatrix[2][4] = rei.getId();
        assertTrue(ponei.verificarSeMove(4,4, 2, 2, pecas, tabuleiroMatrix, 0));
        tabuleiroMatrix[2][4] = 0;
        tabuleiroMatrix[2][3] = rei.getId();
        assertTrue(ponei.verificarSeMove(4,4, 2, 2, pecas, tabuleiroMatrix, 0));
        tabuleiroMatrix[2][3] = 0;
    }
    @Test
    public void test08SemSugestoes(){
        PoneiMagico ponei = new PoneiMagico(1, GestorDeJogo.PONEIMAGICO, GestorDeJogo.PRETA, "Ponei Mágico");
        int tabuleiroMatrix [][] = new int[8][8];
        for (int x = 0; x < tabuleiroMatrix.length; x++) {
            for (int y = 0; y < tabuleiroMatrix[x].length; y++) {
                tabuleiroMatrix[x][y] = 0;
            }
        }
        Map<Integer, CrazyPiece> pecas = new HashMap<>();
        pecas.put(ponei.getId(), ponei);
        tabuleiroMatrix[4][4] = ponei.getId();
        Rei rei = new Rei(2, GestorDeJogo.REI, GestorDeJogo.PRETA, "Rei");
        Rei rei2 = new Rei(3, GestorDeJogo.REI, GestorDeJogo.PRETA, "Rei2");
        Rei rei3 = new Rei(4, GestorDeJogo.REI, GestorDeJogo.PRETA, "Rei3");
        Rei rei4 = new Rei(5, GestorDeJogo.REI, GestorDeJogo.PRETA, "Rei4");
        pecas.put(rei.getId(), rei);
        pecas.put(rei2.getId(), rei2);
        pecas.put(rei3.getId(), rei3);
        pecas.put(rei4.getId(), rei4);
        tabuleiroMatrix[4][3] = rei.getId();
        tabuleiroMatrix[3][4] = rei2.getId();
        tabuleiroMatrix[4][5] = rei3.getId();
        tabuleiroMatrix[5][4] = rei4.getId();
        List<String> sugestoesPossiveis = new ArrayList<>();
        List<String> sugestoesRecebidas = ponei.darSugestao(4,4,pecas, tabuleiroMatrix, 0);
        assertThat(sugestoesRecebidas, is(sugestoesPossiveis));
    }
}
