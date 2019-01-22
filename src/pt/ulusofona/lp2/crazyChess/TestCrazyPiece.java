package pt.ulusofona.lp2.crazyChess;

import org.junit.Test;

import java.util.*;

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
        assertTrue(rei.moveDiagonal(2,3,1,4));
        assertTrue(rei.moveDiagonal(3,2,4,3));
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
        assertTrue(rei.moveDentroLimite(3,2,2,1,3));
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
        tabuleiroMatrix[2][0] = 0;
        tabuleiroMatrix[2][1] = 0;
        tabuleiroMatrix[2][1] = rei.getId();
        tabuleiroMatrix[1][1] = lebre.getId();
        listaEsperada.add(rei);
        assertThat(rei.getPecasNoCaminho(3,1,0,1, pecas, tabuleiroMatrix), is(listaEsperada));
        tabuleiroMatrix[2][1] = 0;
        tabuleiroMatrix[1][1] = 0;
        tabuleiroMatrix[3][3] = rei.getId();
        tabuleiroMatrix[1][1] = lebre.getId();
        assertThat(rei.getPecasNoCaminho(4,4,0,0, pecas, tabuleiroMatrix), is(listaEsperada));
    }

    @Test
    public void test07GetPecasNumRaio() {
        Map<Integer, CrazyPiece> pecas = new HashMap<>();
        Rainha rainha = new Rainha(1, 1, GestorDeJogo.BRANCA, "Rainha");
        Rei rei = new Rei(2, 0, GestorDeJogo.PRETA, "Rei");
        int tabuleiroMatrix[][] = new int[8][8];
        for (int x = 0; x < tabuleiroMatrix.length; x++) {
            for (int y = 0; y < tabuleiroMatrix[x].length; y++) {
                tabuleiroMatrix[x][y] = 0;
            }
        }
        pecas.put(rei.getId(), rei);
        tabuleiroMatrix[4][5] = rei.getId();
        List<CrazyPiece> pecasNumRaio = rainha.getPecasNumRaio(4, 4, 1, tabuleiroMatrix, pecas);
        List<CrazyPiece> pecasPossiveis = new ArrayList<>();
        pecasPossiveis.add(rei);
        boolean pecasIguais = true;
        for (CrazyPiece peca : pecasNumRaio) {
            if (!pecasPossiveis.contains(peca)) {
                pecasIguais = false;
            }
        }
        assertTrue(pecasIguais);

        pecasPossiveis = new ArrayList<>();
        tabuleiroMatrix[0][2] = rei.getId();
        tabuleiroMatrix[4][5] = 0;
        pecasPossiveis.add(rei);
        pecasNumRaio = rainha.getPecasNumRaio(0, 0, 2, tabuleiroMatrix, pecas);
        pecasIguais = true;
        for (CrazyPiece peca : pecasNumRaio) {
            if (!pecasPossiveis.contains(peca)) {
                pecasIguais = false;
            }
        }
        assertTrue(pecasIguais);

        tabuleiroMatrix[0][2] = 0;
        pecasNumRaio = rainha.getPecasNumRaio(4, 4, 2, tabuleiroMatrix, pecas);
        assertTrue(pecasNumRaio.isEmpty());

        pecasIguais = true;
        pecasPossiveis = new ArrayList<>();
        tabuleiroMatrix[4][0] = rei.getId();
        pecasPossiveis.add(rei);
        pecasNumRaio = rainha.getPecasNumRaio(7, 0, 3, tabuleiroMatrix, pecas);
        for (CrazyPiece peca : pecasNumRaio) {
            if (!pecasPossiveis.contains(peca)) {
                pecasIguais = false;
            }
        }
        assertTrue(pecasIguais);

        pecasIguais = true;
        pecasPossiveis = new ArrayList<>();
        tabuleiroMatrix[4][0] = 0;
        tabuleiroMatrix[7][7] = rei.getId();
        pecasPossiveis.add(rei);
        pecasNumRaio = rainha.getPecasNumRaio(7, 7, 3, tabuleiroMatrix, pecas);
        for (CrazyPiece peca : pecasNumRaio) {
            if (!pecasPossiveis.contains(peca)) {
                pecasIguais = false;
            }
        }
        assertTrue(pecasIguais);
    }
}