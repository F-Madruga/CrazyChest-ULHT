package pt.ulusofona.lp2.crazyChess;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


public class TesteSimulador {
    @Test
    public void test01ProcessaJogada(){
        Simulador simulador = new Simulador();
        Tabuleiro tabuleiro = new Tabuleiro(4);
        GestorDeJogo gestorDeJogo = new GestorDeJogo(0,0);
        simulador.setTabuleiro(tabuleiro);
        simulador.setGestor(gestorDeJogo);

        assertFalse(simulador.processaJogada(5,0,1,0));
    }
    @Test
    public void test02ProcessaJogada(){
        Simulador simulador = new Simulador();
        CrazyPiece rei = new CrazyPiece(1,0, 1, "rei");
        Tabuleiro tabuleiro = new Tabuleiro(4);
        GestorDeJogo gestorDeJogo = new GestorDeJogo(0,0);

        simulador.setGestor(gestorDeJogo);
        simulador.setTabuleiro(tabuleiro);

        simulador.gestor.setTurno(1);
        simulador.tabuleiro.inserirPeca(rei);

        assertFalse(simulador.processaJogada(0,0,1,0));
    }
    @Test
    public void test03ProcessaJogada(){
        Simulador simulador = new Simulador();
        CrazyPiece rei = new CrazyPiece(1,0, 1, "rei");
        CrazyPiece reiAliado = new CrazyPiece(2,0, 1, "reiMesmaEquipa");
        Tabuleiro tabuleiro = new Tabuleiro(4);
        GestorDeJogo gestorDeJogo = new GestorDeJogo(0,2);

        simulador.setGestor(gestorDeJogo);
        simulador.setTabuleiro(tabuleiro);

        simulador.gestor.setTurno(1);
        simulador.tabuleiro.inserirPeca(rei);
        simulador.tabuleiro.inserirPeca(reiAliado);

        rei.setCoordenadas(0,0);
        reiAliado.setCoordenadas(1,0);
        rei.move(1,0);

        assertFalse(simulador.processaJogada(0,0,1,0));
        assertEquals(2, simulador.gestor.getNumBrancas());
    }
    @Test
    public void test04ProcessaJogada(){
        Simulador simulador = new Simulador();
        CrazyPiece rei = new CrazyPiece(1,0, 1, "rei");
        CrazyPiece reiHostil = new CrazyPiece(2, 0, 0, "reiOutraEquipa");
        Tabuleiro tabuleiro = new Tabuleiro(4);
        GestorDeJogo gestorDeJogo = new GestorDeJogo(1,1);

        simulador.setGestor(gestorDeJogo);
        simulador.setTabuleiro(tabuleiro);

        simulador.gestor.setTurno(1);
        simulador.tabuleiro.inserirPeca(rei);
        simulador.tabuleiro.inserirPeca(reiHostil);

        rei.setCoordenadas(0,0);
        reiHostil.setCoordenadas(1,0);

        assertTrue(simulador.processaJogada(0,0,1,0));
        assertEquals(-1, reiHostil.getX());
        assertEquals(-1, reiHostil.getY());
        assertEquals(1, simulador.gestor.getNumBrancas());
        assertEquals(0, simulador.gestor.getNumPretas());
    }
}
