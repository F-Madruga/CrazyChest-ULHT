package pt.ulusofona.lp2.crazyChess;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


public class TesteSimulador {
    @Test
    public void test01ProcessaJogada(){
        Simulador simulador = new Simulador();
        GestorDeJogo gestorDeJogo = new GestorDeJogo(0,0);
        Tabuleiro tabuleiro = new Tabuleiro(4);

        simulador.tabuleiro = tabuleiro;
        simulador.gestor = gestorDeJogo;
        assertFalse(simulador.processaJogada(5,0,1,0));
    }
    @Test
    public void test02ProcessaJogada(){
        Simulador simulador = new Simulador();
        GestorDeJogo gestorDeJogo = new GestorDeJogo(0,0);
        Tabuleiro tabuleiro = new Tabuleiro(4);
        CrazyPiece rei = new CrazyPiece(1,0, 1, "rei");

        gestorDeJogo.turno = 1;
        tabuleiro.pecas.add(rei);
        simulador.tabuleiro = tabuleiro;
        simulador.gestor = gestorDeJogo;

        assertFalse(simulador.processaJogada(0,0,1,0));
    }
    @Test
    public void test03ProcessaJogada(){
        Simulador simulador = new Simulador();
        GestorDeJogo gestorDeJogo = new GestorDeJogo(0,0);
        Tabuleiro tabuleiro = new Tabuleiro(4);
        CrazyPiece rei = new CrazyPiece(1,0, 1, "rei");
        CrazyPiece reiAliado = new CrazyPiece(2,0, 1, "reiMesmaEquipa");

        rei.setCoordenadas(0,0);
        reiAliado.setCoordenadas(1,0);
        rei.move(1,0);

        gestorDeJogo.turno = 2;
        tabuleiro.pecas.add(rei);
        simulador.tabuleiro = tabuleiro;
        simulador.gestor = gestorDeJogo;

        assertFalse(simulador.processaJogada(0,0,1,0));
    }
}