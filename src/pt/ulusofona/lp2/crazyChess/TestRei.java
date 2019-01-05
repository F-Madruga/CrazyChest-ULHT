package pt.ulusofona.lp2.crazyChess;

import org.junit.Test;

import java.util.*;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class TestRei {

    @Test
    public void test01PNGPreta() {
        Rei rei = new Rei(1, GestorDeJogo.REI, GestorDeJogo.PRETA, "Rei");
        assertEquals("preto_rei.png", rei.getImagePNG());
    }

    @Test
    public void test02PNGNula() {
        Rei rei = new Rei(1, GestorDeJogo.REI, -1, "Rei");
        assertEquals(null, rei.getImagePNG());
    }

    @Test
    public void test03PNGBranca() {
        Rei rei = new Rei(1, GestorDeJogo.REI, GestorDeJogo.BRANCA, "Rei");
        assertEquals("branco_rei.png", rei.getImagePNG());
    }

    @Test
    public void test04Nome() {
        Rei rei = new Rei(1, GestorDeJogo.REI, GestorDeJogo.BRANCA, "Rei");
        assertEquals("Rei", rei.getNome());
    }

    @Test
    public void test05ValorRelativo() {
        Rei rei = new Rei(1, GestorDeJogo.REI, GestorDeJogo.BRANCA, "Rei");
        assertEquals("(infinito)", rei.getValorRelativo());
    }

    @Test
    public void test06Sugestoes(){
        int tabuleiroMatrix [][] = new int[4][4];
        for (int x = 0; x < tabuleiroMatrix.length; x++) {
            for (int y = 0; y < tabuleiroMatrix[x].length; y++) {
                tabuleiroMatrix[x][y] = 0;
            }
        }
        Rei rei = new Rei(1, GestorDeJogo.REI, GestorDeJogo.PRETA, "Rei");
        Map<Integer, CrazyPiece> pecas = new HashMap<>();
        pecas.put(rei.getId(), rei);
        tabuleiroMatrix[1][1] = rei.getId();
        rei.setCoordenadas(1,1);
        List<String> sugestoesRecebidas = rei.darSugestao(1,  1, pecas, tabuleiroMatrix, 0);
        List<String> sugestoesPossiveis = new ArrayList<>();
        sugestoesPossiveis.add("0, 0");
        sugestoesPossiveis.add("0, 1");
        sugestoesPossiveis.add("0, 2");
        sugestoesPossiveis.add("1, 0");
        sugestoesPossiveis.add("1, 2");
        sugestoesPossiveis.add("2, 0");
        sugestoesPossiveis.add("2, 1");
        sugestoesPossiveis.add("2, 2");
        Collections.sort(sugestoesPossiveis);
        Collections.sort(sugestoesRecebidas);
        assertThat(sugestoesRecebidas, is(sugestoesPossiveis));
    }

    @Test
    public void test07SugestaoComCaptura(){
        int tabuleiroMatrix [][] = new int[4][4];
        for (int x = 0; x < tabuleiroMatrix.length; x++) {
            for (int y = 0; y < tabuleiroMatrix[x].length; y++) {
                tabuleiroMatrix[x][y] = 0;
            }
        }
        Rei rei = new Rei(1, GestorDeJogo.REI, GestorDeJogo.PRETA, "Rei");
        Rei rei2 = new Rei(2, GestorDeJogo.REI, GestorDeJogo.BRANCA, "Rei");
        Map<Integer, CrazyPiece> pecas = new HashMap<>();
        pecas.put(rei.getId(), rei);
        pecas.put(rei2.getId(), rei2);
        tabuleiroMatrix[1][1] = rei.getId();
        tabuleiroMatrix[2][2] = rei2.getId();
        List<String> sugestoesPossiveis = new ArrayList<>();
        sugestoesPossiveis.add("0, 0");
        sugestoesPossiveis.add("0, 1");
        sugestoesPossiveis.add("0, 2");
        sugestoesPossiveis.add("1, 0");
        sugestoesPossiveis.add("1, 2");
        sugestoesPossiveis.add("2, 0");
        sugestoesPossiveis.add("2, 1");
        sugestoesPossiveis.add("2, 2");
        List<String> sugestoesObtidas = rei.darSugestao(1,1, pecas,tabuleiroMatrix,0);
        Collections.sort(sugestoesPossiveis);
        Collections.sort(sugestoesObtidas);
        assertThat(sugestoesObtidas, is(sugestoesPossiveis));
    }
}
