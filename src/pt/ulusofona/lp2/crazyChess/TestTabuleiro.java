package pt.ulusofona.lp2.crazyChess;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TestTabuleiro {
    @Test
    public void test01ExistemCoordenadas(){
        assertTrue(Tabuleiro.existemCoordenadas(0,0,2));
        assertFalse(Tabuleiro.existemCoordenadas(0,-1,2));
        assertFalse(Tabuleiro.existemCoordenadas(2,0,2));
    }
}
