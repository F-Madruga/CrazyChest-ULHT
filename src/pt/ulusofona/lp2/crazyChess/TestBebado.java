package pt.ulusofona.lp2.crazyChess;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class TestBebado {
    @Test
    public void test01MovimentosPossiveis(){
        Bebado bebado = new Bebado(1, 8, GestorDeJogo.PRETA, "Enologo Por Hobbie");
        int tabuleiroMatrix [][] = new int[4][4];
        for (int x = 0; x < tabuleiroMatrix.length; x++) {
            for (int y = 0; y < tabuleiroMatrix[x].length; y++) {
                tabuleiroMatrix[x][y] = 0;
            }
        }
        Rei rei = new Rei(1, GestorDeJogo.REI, GestorDeJogo.BRANCA, "Rei");
        Map<Integer, CrazyPiece> pecas = new HashMap<>();
        pecas.put(rei.getId(), rei);
        tabuleiroMatrix[1][1] = rei.getId();
        tabuleiroMatrix[0][0] = bebado.getId();
        assertFalse(bebado.verificarSeMove(0, 0, 1, 1, pecas, tabuleiroMatrix ,0));
        tabuleiroMatrix[1][1] = 0;

        assertFalse(bebado.verificarSeMove(0, 0, 0, 0, pecas, tabuleiroMatrix ,0));

        Lebre lebre = new Lebre(2, GestorDeJogo.LEBRE, GestorDeJogo.PRETA, "Lebre");
        pecas.put(lebre.getId(), lebre);
        tabuleiroMatrix[3][1] = lebre.getId();
        assertFalse(bebado.verificarSeMove(0, 0, 3, 1, pecas, tabuleiroMatrix ,0));

        assertTrue(bebado.verificarSeMove(0, 0, 1, 2, pecas, tabuleiroMatrix ,0));
    }

    @Test
    public void test02GetValorRelativo(){
        Bebado bebado = new Bebado(1, 8, GestorDeJogo.PRETA, "Enologo Por Hobbie");
        assertEquals("(mão direita é de penalti)", bebado.getValorRelativo());
    }

    @Test
    public void test03GetNome(){
        Bebado bebado = new Bebado(1, 8, GestorDeJogo.PRETA, "Enologo Por Hobbie");
        assertEquals("Bêbado", bebado.getNome());
    }

    @Test
    public void test04GetPNGBranca(){
        Bebado bebado = new Bebado(1, 8, GestorDeJogo.BRANCA, "Enologo Por Hobbie");
        assertEquals("branco_bebado.png", bebado.getImagePNG());
    }

    @Test
    public void test05GetPNGPreta(){
        Bebado bebado = new Bebado(1, 8, GestorDeJogo.PRETA, "Enologo Por Hobbie");
        assertEquals("preto_bebado.png", bebado.getImagePNG());
    }

    @Test
    public void test06GetPNGNula(){
        Bebado bebado = new Bebado(1, 8, -1, "Enologo Por Hobbie");
        assertNull(bebado.getImagePNG());
    }
}
