package pt.ulusofona.lp2.crazyChess;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class TestCrazyPiece {
    @Test
    public void test01MoveHorizonal(){
        Rei rei = new Rei(1, 0, GestorDeJogo.BRANCA, "Rei" );
        assertTrue(rei.moveHorizontal(1, 1));
        assertFalse(rei.moveHorizontal(1, 0));
        assertFalse(rei.moveHorizontal(2, 3));
    }

    @Test
    public void test02MoveVertical(){
        Rei rei = new Rei(1, 0, GestorDeJogo.BRANCA, "Rei" );
        assertTrue(rei.moveVertical(1, 1));
        assertFalse(rei.moveVertical(2, 1));
        assertFalse(rei.moveVertical(1, 3));
    }

    @Test
    public void test03MoveDiagonal(){
        Rei rei = new Rei(1, 0, GestorDeJogo.BRANCA, "Rei" );
        assertTrue(rei.moveDiagonal(1,1,2,2));
        assertTrue(rei.moveDiagonal(1,1,0,0));
        assertFalse(rei.moveDiagonal(0,1,0,2));
        assertFalse(rei.moveDiagonal(0,1,1,1));
        assertFalse(rei.moveDiagonal(3,1,2,3));
    }

    @Test
    public void test04Move(){
        Rei rei = new Rei(1, 0, GestorDeJogo.BRANCA, "Rei" );
        assertTrue(rei.move(1,3,4,2));
        assertFalse(rei.move(1,3,1,3));
        assertFalse(rei.move(2,5,2,5));
    }

    @Test
    public void test05MoveDentroLimite(){
        Rei rei = new Rei(1, 0, GestorDeJogo.BRANCA, "Rei" );
        assertTrue(rei.moveDentroLimite(1,1,2,2,1));
        assertTrue(rei.moveDentroLimite(1,1,0,2,1));
        assertTrue(rei.moveDentroLimite(3,2,0,5,3));
        assertFalse(rei.moveDentroLimite(0,0,3,3,2));
    }

    @Test
    public void test06GetPecasNoCaminho(){
        Map<Integer, CrazyPiece> pecas = new HashMap<>();
        int tabuleiroMatrix [][] = new int[8][8];
        for (int x = 0; x < tabuleiroMatrix.length; x++) {
            for (int y = 0; y < tabuleiroMatrix[x].length; y++) {
                tabuleiroMatrix[x][y] = 0;
            }
        }
        Rei rei = new Rei(1, 0, GestorDeJogo.BRANCA, "Rei" );
        Lebre lebre = new Lebre(1, GestorDeJogo.LEBRE, GestorDeJogo.PRETA, "Lebre");
        pecas.put(rei.getId(), rei);
        pecas.put(lebre.getId(), lebre);
        tabuleiroMatrix[2][0] = rei.getId();
        tabuleiroMatrix[2][1] = lebre.getId();
        List<CrazyPiece> listaEsperada = new ArrayList<>();
        listaEsperada.add(lebre);
        assertThat(rei.getPecasNoCaminho(2,0,2,3, pecas, tabuleiroMatrix), is(listaEsperada));
        assertTrue(rei.getPecasNoCaminho(2,0,3,1, pecas, tabuleiroMatrix).isEmpty());
    }
}
