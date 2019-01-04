package pt.ulusofona.lp2.crazyChess;

import org.junit.Test;

import java.util.*;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class TestTabuleiro {

    @Test
    public void test01ExistemCoordenadas(){
        assertTrue(Tabuleiro.existemCoordenadas(0,0,2));
        assertFalse(Tabuleiro.existemCoordenadas(0,-1,2));
        assertFalse(Tabuleiro.existemCoordenadas(2,0,2));
    }

    @Test
    public void test02GetTamanho() {
        Tabuleiro tabuleiro = new Tabuleiro(4);
        assertEquals(4, tabuleiro.getTamanho());
    }

    @Test
    public void test03AcrescentaPeca() {
        Tabuleiro tabuleiro = new Tabuleiro(8);
        Rei rei = new Rei(1, GestorDeJogo.REI, GestorDeJogo.BRANCA, "Rei");
        Rainha rainha = new Rainha(2, GestorDeJogo.RAINHA, GestorDeJogo.PRETA, "Rainha");
        PadreDaVila padreDaVila = new PadreDaVila(3, GestorDeJogo.PADREDAVILA, GestorDeJogo.BRANCA, "Padre");
        HashMap<Integer, CrazyPiece> pecasEsperadas = new HashMap<>();
        pecasEsperadas.put(rei.getId(), rei);
        pecasEsperadas.put(rainha.getId(), rainha);
        pecasEsperadas.put(padreDaVila.getId(), padreDaVila);
        tabuleiro.acrescentaPeca(rainha);
        tabuleiro.acrescentaPeca(rei);
        tabuleiro.acrescentaPeca(padreDaVila);
        Map<Integer, CrazyPiece> pecasRecebidas = tabuleiro.getPecasMap();
        assertTrue(pecasEsperadas.keySet().equals(pecasRecebidas.keySet()));
    }

    @Test
    public void test04ColocarNoTabuleiro() {
        Tabuleiro tabuleiro = new Tabuleiro(8);
        Rei rei = new Rei(1, GestorDeJogo.REI, GestorDeJogo.BRANCA, "Rei");
        Rainha rainha = new Rainha(2, GestorDeJogo.RAINHA, GestorDeJogo.PRETA, "Rainha");
        PadreDaVila padreDaVila = new PadreDaVila(3, GestorDeJogo.PADREDAVILA, GestorDeJogo.BRANCA, "Padre");
        HashMap<Integer, CrazyPiece> pecasEsperadas = new HashMap<>();
        pecasEsperadas.put(rei.getId(), rei);
        pecasEsperadas.put(rainha.getId(), rainha);
        pecasEsperadas.put(padreDaVila.getId(), padreDaVila);
        tabuleiro.acrescentaPeca(rainha);
        tabuleiro.acrescentaPeca(rei);
        tabuleiro.acrescentaPeca(padreDaVila);
        int [][] tabuleiroMatrix = new int[8][8];
        for (int i = 0; i < tabuleiroMatrix.length; i++) {
            for (int j = 0; j < tabuleiroMatrix[i].length; j++) {
                tabuleiroMatrix[i][j] = 0;
            }
        }
        tabuleiroMatrix[0][0] = rei.getId();
        tabuleiroMatrix[2][2] = rainha.getId();
        tabuleiroMatrix[1][3] = padreDaVila.getId();
        tabuleiro.colocarNoTabuleiro(rei.getId(), 0, 0);
        tabuleiro.colocarNoTabuleiro(rainha.getId(), 2,2);
        tabuleiro.colocarNoTabuleiro(padreDaVila.getId(), 1, 3);
        for (int i = 0; i < tabuleiroMatrix.length; i++) {
            for (int j = 0; j < tabuleiroMatrix[i].length; j++) {
                assertTrue(tabuleiroMatrix[i][j] == tabuleiro.getTabuleiro()[i][j]);
                assertTrue(tabuleiroMatrix[i][j] == tabuleiro.getTabuleiroAnterior()[i][j]);
            }
        }
    }

    @Test
    public void test05GetPecas() {
        Tabuleiro tabuleiro = new Tabuleiro(8);
        Rei rei = new Rei(2, GestorDeJogo.REI, GestorDeJogo.BRANCA, "Rei");
        Rainha rainha = new Rainha(3, GestorDeJogo.RAINHA, GestorDeJogo.PRETA, "Rainha");
        PadreDaVila padreDaVila = new PadreDaVila(4, GestorDeJogo.PADREDAVILA, GestorDeJogo.BRANCA, "Padre");
        List<CrazyPiece> pecasEsperadas = new ArrayList<>();
        pecasEsperadas.add(rei);
        pecasEsperadas.add(rainha);
        pecasEsperadas.add(padreDaVila);
        tabuleiro.acrescentaPeca(rei);
        tabuleiro.acrescentaPeca(rainha);
        tabuleiro.acrescentaPeca(padreDaVila);
        List<CrazyPiece> pecasRecebidas = tabuleiro.getPecas();
        assertTrue(pecasEsperadas.size() == pecasRecebidas.size());
        for (int i = 0; i < pecasEsperadas.size(); i++) {
            assertTrue(pecasEsperadas.get(i) == pecasRecebidas.get(i));
        }
    }

    @Test
    public void test06GetPeca() {
        Tabuleiro tabuleiro = new Tabuleiro(12);
        Rei rei = new Rei(1, GestorDeJogo.REI, GestorDeJogo.BRANCA, "Rei");
        Rainha rainha = new Rainha(2, GestorDeJogo.RAINHA, GestorDeJogo.PRETA, "Rainha");
        PadreDaVila padreDaVila = new PadreDaVila(3, GestorDeJogo.PADREDAVILA, GestorDeJogo.BRANCA, "Padre");
        HashMap<Integer, CrazyPiece> pecasEsperadas = new HashMap<>();
        pecasEsperadas.put(rei.getId(), rei);
        pecasEsperadas.put(rainha.getId(), rainha);
        pecasEsperadas.put(padreDaVila.getId(), padreDaVila);
        tabuleiro.acrescentaPeca(rainha);
        tabuleiro.acrescentaPeca(rei);
        tabuleiro.acrescentaPeca(padreDaVila);
        int [][] tabuleiroMatrix = new int[12][12];
        for (int i = 0; i < tabuleiroMatrix.length; i++) {
            for (int j = 0; j < tabuleiroMatrix[i].length; j++) {
                tabuleiroMatrix[i][j] = 0;
                tabuleiro.colocarNoTabuleiro(0, i, j);
            }
        }
        tabuleiroMatrix[6][6] = rei.getId();
        tabuleiroMatrix[2][3] = rainha.getId();
        tabuleiroMatrix[1][5] = padreDaVila.getId();
        tabuleiro.colocarNoTabuleiro(rei.getId(), 6, 6);
        tabuleiro.colocarNoTabuleiro(rainha.getId(), 2,3);
        tabuleiro.colocarNoTabuleiro(padreDaVila.getId(), 1, 5);
        assertEquals(rei, tabuleiro.getPeca(6, 6));
        assertEquals(rainha, (tabuleiro.getPeca(2, 3)));
        assertEquals(padreDaVila, tabuleiro.getPeca(1, 5));
        assertNull(tabuleiro.getPeca(-1, 5));
        assertNull(tabuleiro.getPeca(0, 0));
    }

    @Test
    public void test07ObterSugestoesJogada() {
        Tabuleiro tabuleiro = new Tabuleiro(4);
        int [][] tabuleiroMatrix = new int[4][4];
        for (int i = 0; i < tabuleiroMatrix.length; i++) {
            for (int j = 0; j < tabuleiroMatrix[i].length; j++) {
                tabuleiroMatrix[i][j] = 0;
                tabuleiro.colocarNoTabuleiro(0, i, j);
            }
        }
        Rei rei = new Rei(1, GestorDeJogo.REI, GestorDeJogo.BRANCA, "Rei");
        tabuleiroMatrix[2][2] = rei.getId();
        tabuleiro.acrescentaPeca(rei);
        tabuleiro.colocarNoTabuleiro(rei.getId(),2,2);
        List<String> sugestaoEsperada = new ArrayList<>();
        sugestaoEsperada.add("Pedido inválido");
        assertThat(sugestaoEsperada, is (tabuleiro.obterSugestoesJogada(-1,1)));
        assertThat(sugestaoEsperada, is (tabuleiro.obterSugestoesJogada(1,1)));
        assertThat(sugestaoEsperada, is (tabuleiro.obterSugestoesJogada(2,2)));
    }

    @Test
    public void test08ProcessaJogada() {
        Tabuleiro tabuleiro = new Tabuleiro(4);
        int [][] tabuleiroMatrix = new int[4][4];
        for (int i = 0; i < tabuleiroMatrix.length; i++) {
            for (int j = 0; j < tabuleiroMatrix[i].length; j++) {
                tabuleiroMatrix[i][j] = 0;
                tabuleiro.colocarNoTabuleiro(0, i, j);
            }
        }
        Rei rei = new Rei(1, GestorDeJogo.REI, GestorDeJogo.PRETA, "Rei");
        Rei rei2 = new Rei(2, GestorDeJogo.REI, GestorDeJogo.BRANCA, "Rei2");
        Rei rei3 = new Rei(3, GestorDeJogo.REI, GestorDeJogo.PRETA, "Rei");
        tabuleiro.acrescentaPeca(rei);
        tabuleiro.acrescentaPeca(rei2);
        tabuleiro.acrescentaPeca(rei3);
        tabuleiro.colocarNoTabuleiro(rei.getId(),2,2);
        tabuleiro.colocarNoTabuleiro(rei2.getId(), 3, 3);
        tabuleiro.colocarNoTabuleiro(rei3.getId(), 2, 3);
        assertFalse(tabuleiro.processaJogada(-1, 1, 0,1));
        assertFalse(tabuleiro.processaJogada(0, 1, -1,1));
        assertFalse(tabuleiro.processaJogada(3, 3, 3,2));
        assertFalse(tabuleiro.processaJogada(1, 1, 3,2));
        assertFalse(tabuleiro.processaJogada(2, 2, 2,3));
        assertTrue(tabuleiro.processaJogada(2, 2, 1,1));
        assertTrue(tabuleiro.fazerUndo);
        assertEquals(0, tabuleiro.getTabuleiro()[2][2]);
        assertEquals(rei.getId(), tabuleiro.getTabuleiro()[1][1]);
        assertTrue(tabuleiro.processaJogada(3, 3, 2,3));
        assertTrue(tabuleiro.fazerUndo);
        assertEquals(0, tabuleiro.getTabuleiro()[3][3]);
        assertEquals(rei2.getId(), tabuleiro.getTabuleiro()[2][3]);
    }

    @Test
    public void test09AtualizarAnterior() {
        Tabuleiro tabuleiro = new Tabuleiro(4);
        int [][] tabuleiroMatrix = new int[4][4];
        for (int i = 0; i < tabuleiroMatrix.length; i++) {
            for (int j = 0; j < tabuleiroMatrix[i].length; j++) {
                tabuleiroMatrix[i][j] = 0;
                tabuleiro.colocarNoTabuleiro(0, i, j);
            }
        }
        Rei rei = new Rei(1, GestorDeJogo.REI, GestorDeJogo.PRETA, "Rei");
        Rei rei2 = new Rei(2, GestorDeJogo.REI, GestorDeJogo.BRANCA, "Rei2");
        Rei rei3 = new Rei(3, GestorDeJogo.REI, GestorDeJogo.PRETA, "Rei");
        tabuleiro.acrescentaPeca(rei);
        tabuleiro.acrescentaPeca(rei2);
        tabuleiro.acrescentaPeca(rei3);
        tabuleiro.colocarNoTabuleiro(rei.getId(),2,2);
        tabuleiro.colocarNoTabuleiro(rei2.getId(), 3, 3);
        tabuleiro.colocarNoTabuleiro(rei3.getId(), 2, 3);
        tabuleiro.atualizarAnterior();
        assertEquals(rei.getId(), tabuleiro.getTabuleiroAnterior()[2][2]);
        assertEquals(rei2.getId(), tabuleiro.getTabuleiroAnterior()[3][3]);
    }

}
