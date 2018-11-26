package pt.ulusofona.lp2.crazyChess;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


public class TesteCrazyPiece {
    @Test
    public void test01Move(){
        CrazyPiece rei = new CrazyPiece(0,0,1, "rei");
        rei.setCoordenadas(0,0);
        assertTrue(rei.move(1,1));
    }
    @Test
    public void test02Move(){
        CrazyPiece rei = new CrazyPiece(0,0,1, "rei");
        rei.setCoordenadas(2,3);
        assertFalse(rei.move(2,1));
    }
    @Test
    public void test03Move() {
        CrazyPiece rei = new CrazyPiece(0, 0, 1, "rei");
        rei.setCoordenadas(1, 0);
        assertTrue(rei.move(1, 1));
    }
}