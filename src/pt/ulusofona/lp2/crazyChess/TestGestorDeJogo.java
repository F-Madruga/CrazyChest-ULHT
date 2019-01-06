package pt.ulusofona.lp2.crazyChess;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class TestGestorDeJogo {

    @Test
    public void test01GetTurno() {
        GestorDeJogo gestor = new GestorDeJogo();
        assertEquals(0, gestor.getTurno());
        gestor.naoHouveCaptura();
        assertEquals(1, gestor.getTurno());
    }

    @Test
    public void test02QuemEstaAJogar() {
        GestorDeJogo gestor = new GestorDeJogo();
        assertEquals(GestorDeJogo.PRETA, gestor.quemEstaAJogar());
        gestor.naoHouveCaptura();
        assertEquals(GestorDeJogo.BRANCA, gestor.quemEstaAJogar());
        gestor.naoHouveCaptura();
        assertEquals(GestorDeJogo.PRETA, gestor.quemEstaAJogar());
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
        int jogadasValidasPreta = gestor.getJogadasValidas().get(-GestorDeJogo.PRETA);
        int jogadasValidasBranca = gestor.getJogadasValidas().get(-GestorDeJogo.BRANCA);
        assertEquals(0, jogadasValidasPreta);
        assertEquals(0, jogadasValidasBranca);
        gestor.validarJogada();
        assertEquals(1, gestor.getTurno());
        jogadasValidasPreta = gestor.getJogadasValidas().get(-GestorDeJogo.PRETA);
        jogadasValidasBranca = gestor.getJogadasValidas().get(-GestorDeJogo.BRANCA);
        int jogada = gestor.getJogadasValidas().get(0);
        assertEquals(1, jogadasValidasPreta);
        assertEquals(0, jogadasValidasBranca);
        assertEquals(GestorDeJogo.PRETA, jogada);
        gestor.naoHouveCaptura();
        jogadasValidasPreta = gestor.getJogadasValidas().get(-GestorDeJogo.PRETA);
        jogadasValidasBranca = gestor.getJogadasValidas().get(-GestorDeJogo.BRANCA);
        jogada = gestor.getJogadasValidas().get(1);
        assertEquals(1, jogadasValidasPreta);
        assertEquals(1, jogadasValidasBranca);
        assertEquals(GestorDeJogo.BRANCA, jogada);
    }

    @Test
    public void test05NaoHouveCaptura() {
        GestorDeJogo gestor = new GestorDeJogo();
        assertEquals(0, gestor.getTurnoSemCapturasAnterior());
        assertEquals(0, gestor.getTurnoSemCapturas());
        gestor.naoHouveCaptura();
        assertEquals(0, gestor.getTurnoSemCapturasAnterior());
        assertEquals(0, gestor.getTurnoSemCapturas());
        int capturasPreta = gestor.getCapturas().get(-GestorDeJogo.PRETA);
        int capturasBranca = gestor.getCapturas().get(-GestorDeJogo.BRANCA);
        assertEquals(0, capturasBranca);
        assertEquals(0, capturasPreta);
        gestor.naoHouveCaptura();
        assertEquals(0, gestor.getTurnoSemCapturasAnterior());
        assertEquals(0, gestor.getTurnoSemCapturas());
        assertEquals(0, capturasBranca);
        assertEquals(0, capturasPreta);
        gestor.adicionarCaptura(GestorDeJogo.RAINHA);
        assertEquals(0, gestor.getTurnoSemCapturasAnterior());
        assertEquals(0, gestor.getTurnoSemCapturas());
        capturasPreta = gestor.getCapturas().get(-GestorDeJogo.PRETA);
        capturasBranca = gestor.getCapturas().get(-GestorDeJogo.BRANCA);
        assertEquals(0, capturasBranca);
        assertEquals(1, capturasPreta);
        gestor.naoHouveCaptura();
        assertEquals(0, gestor.getTurnoSemCapturasAnterior());
        assertEquals(1, gestor.getTurnoSemCapturas());
        capturasPreta = gestor.getCapturas().get(-GestorDeJogo.PRETA);
        capturasBranca = gestor.getCapturas().get(-GestorDeJogo.BRANCA);
        assertEquals(0, capturasBranca);
        assertEquals(1, capturasPreta);
        gestor.naoHouveCaptura();
        assertEquals(1, gestor.getTurnoSemCapturasAnterior());
        assertEquals(2, gestor.getTurnoSemCapturas());
        capturasPreta = gestor.getCapturas().get(-GestorDeJogo.PRETA);
        capturasBranca = gestor.getCapturas().get(-GestorDeJogo.BRANCA);
        assertEquals(0, capturasBranca);
        assertEquals(1, capturasPreta);
    }

    @Test
    public void test06AdicionarCaptura() {
        GestorDeJogo gestor = new GestorDeJogo();
        int capturasPreta = gestor.getCapturas().get(-GestorDeJogo.PRETA);
        int capturasBranca = gestor.getCapturas().get(-GestorDeJogo.BRANCA);
        assertEquals(0, capturasPreta);
        assertEquals(0, capturasBranca);
        int reisEquipaBranca = gestor.getNumReis().get(GestorDeJogo.BRANCA);
        int reisEquipaBrancaAnterior = gestor.getNumReis().get(-GestorDeJogo.BRANCA);
        assertEquals(0, reisEquipaBranca);
        assertEquals(0, reisEquipaBrancaAnterior);
        gestor.contaRei(GestorDeJogo.BRANCA);
        reisEquipaBranca = gestor.getNumReis().get(GestorDeJogo.BRANCA);
        reisEquipaBrancaAnterior = gestor.getNumReis().get(-GestorDeJogo.BRANCA);
        assertEquals(1, reisEquipaBranca);
        assertEquals(1, reisEquipaBrancaAnterior);
        gestor.adicionarCaptura(GestorDeJogo.REI);
        capturasPreta = gestor.getCapturas().get(-GestorDeJogo.PRETA);
        capturasBranca = gestor.getCapturas().get(-GestorDeJogo.BRANCA);
        int jogada = gestor.getCapturas().get(0);
        assertEquals(1, capturasPreta);
        assertEquals(0, capturasBranca);
        assertEquals(GestorDeJogo.PRETA, jogada);
        reisEquipaBranca = gestor.getNumReis().get(GestorDeJogo.BRANCA);
        reisEquipaBrancaAnterior = gestor.getNumReis().get(-GestorDeJogo.BRANCA);
        assertEquals(0, reisEquipaBranca);
        assertEquals(1, reisEquipaBrancaAnterior);
        assertEquals(0, gestor.getTurnoSemCapturas());
        assertEquals(0, gestor.getTurnoSemCapturasAnterior());
        gestor.adicionarCaptura(GestorDeJogo.RAINHA);
        capturasPreta = gestor.getCapturas().get(-GestorDeJogo.PRETA);
        capturasBranca = gestor.getCapturas().get(-GestorDeJogo.BRANCA);
        jogada = gestor.getCapturas().get(1);
        assertEquals(1, capturasPreta);
        assertEquals(1, capturasBranca);
        assertEquals(GestorDeJogo.BRANCA, jogada);
        int reisEquipaPreta = gestor.getNumReis().get(GestorDeJogo.PRETA);
        int reisEquipaPretaAnterior = gestor.getNumReis().get(-GestorDeJogo.PRETA);
        assertEquals(0, reisEquipaPreta);
        assertEquals(0, reisEquipaPretaAnterior);
        assertEquals(0, gestor.getTurnoSemCapturas());
        assertEquals(0, gestor.getTurnoSemCapturasAnterior());
    }

    @Test
    public void test07InvalidarJogada() {
        GestorDeJogo gestor = new GestorDeJogo();
        int jogadasInvalidasPreta = gestor.getJogadasInvalidas().get(GestorDeJogo.PRETA);
        int jogadasInvalidasBranca = gestor.getJogadasInvalidas().get(GestorDeJogo.PRETA);
        assertEquals(0, jogadasInvalidasPreta);
        assertEquals(0, jogadasInvalidasBranca);
        gestor.invalidarJogada();
        jogadasInvalidasPreta = gestor.getJogadasInvalidas().get(GestorDeJogo.PRETA);
        jogadasInvalidasBranca = gestor.getJogadasInvalidas().get(GestorDeJogo.BRANCA);
        assertEquals(1, jogadasInvalidasPreta);
        assertEquals(0, jogadasInvalidasBranca);
        gestor.invalidarJogada();
        jogadasInvalidasPreta = gestor.getJogadasInvalidas().get(GestorDeJogo.PRETA);
        jogadasInvalidasBranca = gestor.getJogadasInvalidas().get(GestorDeJogo.BRANCA);
        assertEquals(2, jogadasInvalidasPreta);
        assertEquals(0, jogadasInvalidasBranca);
        gestor.naoHouveCaptura();
        gestor.invalidarJogada();
        jogadasInvalidasPreta = gestor.getJogadasInvalidas().get(GestorDeJogo.PRETA);
        jogadasInvalidasBranca = gestor.getJogadasInvalidas().get(GestorDeJogo.BRANCA);
        assertEquals(2, jogadasInvalidasPreta);
        assertEquals(1, jogadasInvalidasBranca);
    }

    @Test
    public void tes08Undo() {
        GestorDeJogo gestor = new GestorDeJogo();
        gestor.contaRei(GestorDeJogo.PRETA);
        gestor.naoHouveCaptura();
        gestor.invalidarJogada();
        gestor.adicionarCaptura(GestorDeJogo.REI);
        assertEquals(2, gestor.getTurno());
        assertEquals(0, gestor.getTurnoSemCapturas());
        assertEquals(0, gestor.getTurnoSemCapturasAnterior());
        int numReis = gestor.getNumReis().get(GestorDeJogo.PRETA);
        int numReisAnterior = gestor.getNumReis().get(-GestorDeJogo.PRETA);
        assertEquals(0, numReis);
        assertEquals(1, numReisAnterior);
        int numCapturas = gestor.getCapturas().get(-GestorDeJogo.BRANCA);
        assertTrue(!gestor.getCapturas().containsKey(0));
        assertEquals(1, numCapturas);
        int jogadasValidas = gestor.getJogadasValidas().get(-GestorDeJogo.BRANCA);
        assertEquals(1, jogadasValidas);
        assertTrue(gestor.getJogadasValidas().containsKey(1));
        int jogadasInvalidas = gestor.getJogadasInvalidas().get(GestorDeJogo.BRANCA);
        assertEquals(1, jogadasInvalidas);
        gestor.undo();
        assertEquals(1, gestor.getTurno());
        assertEquals(0, gestor.getTurnoSemCapturas());
        assertEquals(0, gestor.getTurnoSemCapturasAnterior());
        numReis = gestor.getNumReis().get(GestorDeJogo.PRETA);
        numReisAnterior = gestor.getNumReis().get(-GestorDeJogo.PRETA);
        assertEquals(1, numReis);
        assertEquals(1, numReisAnterior);
        numCapturas = gestor.getCapturas().get(-GestorDeJogo.BRANCA);
        assertTrue(!gestor.getCapturas().containsKey(1));
        assertEquals(0, numCapturas);
        jogadasValidas = gestor.getJogadasValidas().get(-GestorDeJogo.BRANCA);
        assertEquals(0, jogadasValidas);
        assertTrue(!gestor.getJogadasValidas().containsKey(1));
        jogadasInvalidas = gestor.getJogadasInvalidas().get(GestorDeJogo.BRANCA);
        assertEquals(1, jogadasInvalidas);
    }

    @Test
    public void test09PossoTerminar() {
        GestorDeJogo gestor = new GestorDeJogo();
        assertTrue(gestor.getNumReis().get(GestorDeJogo.BRANCA) == 0);
        assertTrue(gestor.getNumReis().get(GestorDeJogo.PRETA) == 0);
        assertTrue(gestor.possoTerminar());
        gestor.contaPeca();
        gestor.contaPeca();
        gestor.contaRei(GestorDeJogo.PRETA);
        gestor.contaRei(GestorDeJogo.BRANCA);
        assertTrue(gestor.getNumReis().get(GestorDeJogo.BRANCA) == 1);
        assertTrue(gestor.getNumReis().get(GestorDeJogo.PRETA) == 1);
        assertTrue(gestor.possoTerminar());
        gestor.contaRei(GestorDeJogo.PRETA);
        assertTrue(gestor.getNumReis().get(GestorDeJogo.BRANCA) == 1);
        assertTrue(gestor.getNumReis().get(GestorDeJogo.PRETA) == 2);
        assertFalse(gestor.possoTerminar());
        gestor.adicionarCaptura(GestorDeJogo.REI);
        assertTrue(gestor.getNumReis().get(GestorDeJogo.BRANCA) == 0);
        assertTrue(gestor.getNumReis().get(GestorDeJogo.PRETA) == 2);
        assertTrue(gestor.possoTerminar());
        gestor.contaRei(GestorDeJogo.BRANCA);
        for (int i = 0; i < 10; i++) {
            gestor.naoHouveCaptura();
        }
        assertTrue(gestor.possoTerminar());
    }

    @Test
    public void test10GetResultados() {
        GestorDeJogo gestor = new GestorDeJogo();
        assertTrue(gestor.getNumReis().get(GestorDeJogo.BRANCA) == 0);
        assertTrue(gestor.getNumReis().get(GestorDeJogo.PRETA) == 0);

        gestor.contaRei(GestorDeJogo.PRETA);
        gestor.contaRei(GestorDeJogo.BRANCA);
        assertTrue(gestor.getNumReis().get(GestorDeJogo.BRANCA) == 1);
        assertTrue(gestor.getNumReis().get(GestorDeJogo.PRETA) == 1);
        List<String> listaEsperada = new ArrayList<>();
        listaEsperada.add("JOGO DE CRAZY CHESS");
        listaEsperada.add("Resultado: EMPATE");
        listaEsperada.add("---");
        listaEsperada.add("Equipa das Pretas");
        listaEsperada.add(" Capturas: 0");
        listaEsperada.add(" Jogadas válidas: 0");
        listaEsperada.add(" Tentativas inválidas: 0");
        listaEsperada.add("Equipa das Brancas");
        listaEsperada.add(" Capturas: 0");
        listaEsperada.add(" Jogadas válidas: 0");
        listaEsperada.add(" Tentativas inválidas: 0");
        gestor.possoTerminar();
        List<String> listaObtida = gestor.getResultado();
        assertThat(listaObtida, is (listaEsperada));
        gestor.contaRei(GestorDeJogo.PRETA);
        assertTrue(gestor.getNumReis().get(GestorDeJogo.BRANCA) == 1);
        assertTrue(gestor.getNumReis().get(GestorDeJogo.PRETA) == 2);
        gestor.adicionarCaptura(GestorDeJogo.REI);
        assertTrue(gestor.getNumReis().get(GestorDeJogo.BRANCA) == 0);
        assertTrue(gestor.getNumReis().get(GestorDeJogo.PRETA) == 2);
        listaEsperada = new ArrayList<>();
        listaEsperada.add("JOGO DE CRAZY CHESS");
        listaEsperada.add("Resultado: VENCERAM AS PRETAS");
        listaEsperada.add("---");
        listaEsperada.add("Equipa das Pretas");
        listaEsperada.add(" Capturas: 1");
        listaEsperada.add(" Jogadas válidas: 1");
        listaEsperada.add(" Tentativas inválidas: 0");
        listaEsperada.add("Equipa das Brancas");
        listaEsperada.add(" Capturas: 0");
        listaEsperada.add(" Jogadas válidas: 0");
        listaEsperada.add(" Tentativas inválidas: 0");
        gestor.possoTerminar();
        listaObtida = gestor.getResultado();
        assertThat(listaObtida, is (listaEsperada));
        gestor.contaRei(GestorDeJogo.BRANCA);
        for (int i = 0; i < 10; i++) {
            gestor.naoHouveCaptura();
        }
        listaEsperada = new ArrayList<>();
        listaEsperada.add("JOGO DE CRAZY CHESS");
        listaEsperada.add("Resultado: EMPATE");
        listaEsperada.add("---");
        listaEsperada.add("Equipa das Pretas");
        listaEsperada.add(" Capturas: 1");
        listaEsperada.add(" Jogadas válidas: 6");
        listaEsperada.add(" Tentativas inválidas: 0");
        listaEsperada.add("Equipa das Brancas");
        listaEsperada.add(" Capturas: 0");
        listaEsperada.add(" Jogadas válidas: 5");
        listaEsperada.add(" Tentativas inválidas: 0");
        gestor.possoTerminar();
        listaObtida = gestor.getResultado();
        assertThat(listaObtida, is (listaEsperada));
    }

    @Test
    public void test11LoadEquipaAJogar() {
        GestorDeJogo gestor = new GestorDeJogo();
        gestor.validarJogada();
        gestor.validarJogada();
        gestor.validarJogada();
        gestor.loadEquipaAJogar();
        assertEquals(3, gestor.getTurno());
        gestor.validarJogada();
        gestor.validarJogada();
        assertEquals(5, gestor.getTurno());
    }

    @Test
    public void test12LoadJogadasValidas() {
        GestorDeJogo gestor = new GestorDeJogo();
        gestor.loadJogadasValidas(GestorDeJogo.PRETA, 3);
        gestor.loadJogadasValidas(GestorDeJogo.BRANCA, 9);
        int jogadasValidasBrancas = gestor.getJogadasValidas().get(-GestorDeJogo.BRANCA);
        int jogadasValidasPreta = gestor.getJogadasValidas().get(-GestorDeJogo.PRETA);
        assertEquals(9, jogadasValidasBrancas);
        assertEquals(3, jogadasValidasPreta);
        gestor.loadJogadasValidas(GestorDeJogo.PRETA, 0);
        gestor.loadJogadasValidas(GestorDeJogo.BRANCA, 8);
        jogadasValidasBrancas = gestor.getJogadasValidas().get(-GestorDeJogo.BRANCA);
        jogadasValidasPreta = gestor.getJogadasValidas().get(-GestorDeJogo.PRETA);
        assertEquals(8, jogadasValidasBrancas);
        assertEquals(0, jogadasValidasPreta);
    }

    @Test
    public void test13LoadJogadasInvalidas() {
        GestorDeJogo gestor = new GestorDeJogo();
        gestor.loadJogadasInvalidas(GestorDeJogo.PRETA, 3);
        gestor.loadJogadasInvalidas(GestorDeJogo.BRANCA, 9);
        int jogadasInalidasBrancas = gestor.getJogadasInvalidas().get(GestorDeJogo.BRANCA);
        int jogadasInalidasPreta = gestor.getJogadasInvalidas().get(GestorDeJogo.PRETA);
        assertEquals(9, jogadasInalidasBrancas);
        assertEquals(3, jogadasInalidasPreta);
        gestor.loadJogadasInvalidas(GestorDeJogo.PRETA, 0);
        gestor.loadJogadasInvalidas(GestorDeJogo.BRANCA, 8);
        jogadasInalidasBrancas = gestor.getJogadasInvalidas().get(GestorDeJogo.BRANCA);
        jogadasInalidasPreta = gestor.getJogadasInvalidas().get(GestorDeJogo.PRETA);
        assertEquals(8, jogadasInalidasBrancas);
        assertEquals(0, jogadasInalidasPreta);
    }

    @Test
    public void test14LoadCapturas() {
        GestorDeJogo gestor = new GestorDeJogo();
        gestor.loadCapturas(GestorDeJogo.PRETA, 3);
        gestor.loadCapturas(GestorDeJogo.BRANCA, 9);
        int capturasBrancas = gestor.getCapturas().get(-GestorDeJogo.BRANCA);
        int capturasPreta = gestor.getCapturas().get(-GestorDeJogo.PRETA);
        assertEquals(9, capturasBrancas);
        assertEquals(3, capturasPreta);
        gestor.loadCapturas(GestorDeJogo.PRETA, 0);
        gestor.loadCapturas(GestorDeJogo.BRANCA, 8);
        capturasBrancas = gestor.getCapturas().get(-GestorDeJogo.BRANCA);
        capturasPreta = gestor.getCapturas().get(-GestorDeJogo.PRETA);
        assertEquals(8, capturasBrancas);
        assertEquals(0, capturasPreta);
    }
}
