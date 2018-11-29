package pt.ulusofona.lp2.crazyChess;

import org.junit.Test;
import static org.junit.Assert.assertFalse;

public class TesteSimulador {
    @Test
    public void test01ProcessaJogada(){
        Simulador simulador = new Simulador();
        Tabuleiro tabuleiro = new Tabuleiro(4);
        simulador.setTabuleiro(tabuleiro);
        CrazyPiece rei = new CrazyPiece(2,0, 0, "tropa");
        rei.setCoordenadas(0,1);
        GestorDeJogo gestorDeJogo = new GestorDeJogo(0,0);
        simulador.setGestor(gestorDeJogo);
        assertFalse(simulador.processaJogada(0,1,-1,1));
    }

    @Test
    public void test02ProcessaJogada(){
        Simulador simulador = new Simulador();
        CrazyPiece rei = new CrazyPiece(1,0, 0, "rei");
        rei.setCoordenadas(1,1);
        Tabuleiro tabuleiro = new Tabuleiro(4);
        simulador.setTabuleiro(tabuleiro);
        tabuleiro.inserirPeca(rei);
        GestorDeJogo gestorDeJogo = new GestorDeJogo(0,1);
        simulador.setGestor(gestorDeJogo);
        assertFalse(simulador.processaJogada(1,1, 3,1));
    }
}
