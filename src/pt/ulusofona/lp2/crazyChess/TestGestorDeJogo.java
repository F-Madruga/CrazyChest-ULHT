package pt.ulusofona.lp2.crazyChess;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TestGestorDeJogo {

    @Test
    public void test01GetTurno() {
        GestorDeJogo gestor = new GestorDeJogo();
        assertEquals(0, gestor.getTurno());
    }

    @Test
    public void test02QuemEstaAJogar() {
        GestorDeJogo gestor = new GestorDeJogo();
        assertEquals(GestorDeJogo.PRETA, gestor.quemEstaAJogar());
        gestor.naoHouveCaptura();
        assertEquals(GestorDeJogo.BRANCA, gestor.quemEstaAJogar());
    }

    @Test
    public void test03ContaRei() {
        GestorDeJogo gestor = new GestorDeJogo();
        int numReisEsperado = gestor.getNumReis().get(GestorDeJogo.PRETA);
        int numReisAnteriorEsperado = gestor.getNumReis().get(-GestorDeJogo.PRETA);
        assertEquals(0, numReisEsperado);
        assertEquals(0, numReisAnteriorEsperado);
        gestor.contaRei(GestorDeJogo.PRETA);
        numReisEsperado = gestor.getNumReis().get(GestorDeJogo.PRETA);
        numReisAnteriorEsperado = gestor.getNumReis().get(-GestorDeJogo.PRETA);
        assertEquals(1, numReisEsperado);
        assertEquals(1, numReisAnteriorEsperado);
        gestor.contaRei(GestorDeJogo.PRETA);
        numReisEsperado = gestor.getNumReis().get(GestorDeJogo.PRETA);
        numReisAnteriorEsperado = gestor.getNumReis().get(-GestorDeJogo.PRETA);
        assertEquals(2, numReisEsperado);
        assertEquals(2, numReisAnteriorEsperado);
        numReisEsperado = gestor.getNumReis().get(GestorDeJogo.BRANCA);
        numReisAnteriorEsperado = gestor.getNumReis().get(-GestorDeJogo.BRANCA);
        assertEquals(0, numReisEsperado);
        assertEquals(0, numReisAnteriorEsperado);
        gestor.contaRei(GestorDeJogo.BRANCA);
        numReisEsperado = gestor.getNumReis().get(GestorDeJogo.BRANCA);
        numReisAnteriorEsperado = gestor.getNumReis().get(-GestorDeJogo.BRANCA);
        assertEquals(1, numReisEsperado);
        assertEquals(1, numReisAnteriorEsperado);
        gestor.contaRei(GestorDeJogo.BRANCA);
        numReisEsperado = gestor.getNumReis().get(GestorDeJogo.BRANCA);
        numReisAnteriorEsperado = gestor.getNumReis().get(-GestorDeJogo.BRANCA);
        assertEquals(2, numReisEsperado);
        assertEquals(2, numReisAnteriorEsperado);
    }

    @Test
    public void tes04ValidarJogada() {
        GestorDeJogo gestor = new GestorDeJogo();
        gestor.validarJogada();
        assertEquals(1, gestor.getTurno());
        int jogadasValidas = gestor.getJogadasValidas().get(-GestorDeJogo.PRETA);
        int jogada = gestor.getJogadasValidas().get(0);
        assertEquals(1, jogadasValidas);
        assertEquals(GestorDeJogo.PRETA, jogada);
    }

    @Test
    public void test05NaoHouveCaptura() {
        GestorDeJogo gestor = new GestorDeJogo();
        gestor.naoHouveCaptura();
        assertEquals(0, gestor.getTurnoSemCapturasAnterior());
        assertEquals(0, gestor.getTurnoSemCapturas());
        gestor.adicionarCaptura(GestorDeJogo.RAINHA);
        gestor.naoHouveCaptura();
        assertEquals(0, gestor.getTurnoSemCapturasAnterior());
        assertEquals(1, gestor.getTurnoSemCapturas());
        int numReisAnterior = gestor.getNumReis().get(-GestorDeJogo.PRETA);
        assertEquals(0, numReisAnterior);
    }

    @Test
    public void test06AdicionarCaptura() {
        GestorDeJogo gestor = new GestorDeJogo();
        gestor.contaRei(GestorDeJogo.BRANCA);
        gestor.adicionarCaptura(GestorDeJogo.REI);
        int capturas = gestor.getCapturas().get(-GestorDeJogo.PRETA);
        int jogada = gestor.getCapturas().get(0);
        assertEquals(1, capturas);
        assertEquals(GestorDeJogo.PRETA, jogada);
        int reisEquipaOposta = gestor.getNumReis().get(GestorDeJogo.BRANCA);
        int reisEquipaOpostaAnterior = gestor.getNumReis().get(-GestorDeJogo.BRANCA);
        assertEquals(0, reisEquipaOposta);
        assertEquals(1, reisEquipaOpostaAnterior);
        assertEquals(0, gestor.getTurnoSemCapturas());
        assertEquals(0, gestor.getTurnoSemCapturasAnterior());
    }

    @Test
    public void test07InvalidarJogada() {
        GestorDeJogo gestor = new GestorDeJogo();
        gestor.invalidarJogada();
        int jogadasInvalidas = gestor.getJogadasInvalidas().get(GestorDeJogo.PRETA);
        assertEquals(1, jogadasInvalidas);
    }

    @Test
    public void tes08Undo() {
        GestorDeJogo gestor = new GestorDeJogo();
        gestor.contaRei(GestorDeJogo.PRETA);
        gestor.naoHouveCaptura();
        gestor.invalidarJogada();
        gestor.adicionarCaptura(GestorDeJogo.REI);
        gestor.undo();
        assertEquals(1, gestor.getTurno());
        assertEquals(0, gestor.getTurnoSemCapturas());
        assertEquals(0, gestor.getTurnoSemCapturasAnterior());
        int numReis = gestor.getNumReis().get(GestorDeJogo.PRETA);
        int numReisAnterior = gestor.getNumReis().get(-GestorDeJogo.PRETA);
        assertEquals(1, numReis);
        assertEquals(1, numReisAnterior);
        int numCapturas = gestor.getCapturas().get(-GestorDeJogo.BRANCA);
        assertTrue(!gestor.getCapturas().containsKey(1));
        assertEquals(0, numCapturas);
        int jogadasValidas = gestor.getJogadasValidas().get(-GestorDeJogo.BRANCA);
        assertEquals(0, jogadasValidas);
        assertTrue(!gestor.getJogadasValidas().containsKey(1));
        int jogadasInvalidas = gestor.getJogadasInvalidas().get(GestorDeJogo.BRANCA);
        assertEquals(1, jogadasInvalidas);
    }
}
