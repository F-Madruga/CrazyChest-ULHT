package pt.ulusofona.lp2.crazyChess;

import java.util.ArrayList;
import java.util.List;

public class GestorDeJogo {
    public final static int preta = 10;
    public final static int branca = 20;
    public final static int rei = 0;

    private int numPretas;
    private int numBrancas;
    private int turno;
    private int turnoSemCapturasAnterior;
    private int turnoSemCapturas;
    private List<CrazyPiece> capturas;
    private List<Integer> jogadasValidas;
    private List<Integer> jogadasInvalidas;
    private int resultado;

    public GestorDeJogo() {
        this.numPretas = 0;
        this.numBrancas = 0;
        this.turno = 0;
        this.turnoSemCapturas = 0;
        this.turnoSemCapturasAnterior = 0;
        this.resultado = -1;
        this.capturas = new ArrayList<>();
        this.jogadasValidas = new ArrayList<>();
        this.jogadasInvalidas = new ArrayList<>();
    }

    public int quemEstaAjogar() {
        if (turno % 2 == 0) {
            return preta;
        }
        else {
            return branca;
        }
    }

    public void adicionarPeca(int idEquipa) {
        if (idEquipa == preta) {
            numPretas++;
        }
        if (idEquipa == branca) {
            numBrancas++;
        }
    }

    public void adicionarCaptura(CrazyPiece peca) {
        this.capturas.add(peca);
        if (peca.getIdTipo() == rei) {
            if (quemEstaAjogar() == preta) {
                this.numBrancas--;
            }
            else {
                this.numPretas--;
            }
        }
        turnoSemCapturasAnterior = turnoSemCapturas;
        turnoSemCapturas = 0;
    }

    public void validaJogada() {
        this.jogadasValidas.add(quemEstaAjogar());
        turno++;
    }

    public void invalidaJogada() {
        this.jogadasInvalidas.add(quemEstaAjogar());
    }

    public void naoHouveCaptura() {
        turnoSemCapturasAnterior = turnoSemCapturas;
        turnoSemCapturas++;
    }

    public boolean possoTerminar() {
        if (turnoSemCapturas >= 10 || (numBrancas == 1 && numPretas == 1) || numBrancas <= 0 || numPretas <= 0) {
            if (numPretas >= 1 && numBrancas <= 0) {
                resultado = preta;
            } else if (numBrancas >= 1 && numPretas <= 0) {
                resultado = branca;
            } else {
                this.resultado = -1;
            }
            return true;
        }
        return false;
    }

    public int getTurno() {
        return this.turno;
    }

    public List<String> getResultado () {
        List<String> resultados = new ArrayList<>();
        String resultado = "JOGO DE CRAZY CHESS";
        resultados.add(resultado);
        resultado = "Resultado: ";
        if (this.resultado == preta) {
            resultado += "VENCERAM AS PRETAS";
        }
        if (this.resultado == branca) {
            resultado += "VENCERAM AS BRANCAS";
        }
        if (this.resultado == -1) {
            resultado += "EMPATE";
        }
        int capturasBrancas = 0;
        int capturaPretas = 0;
        for (CrazyPiece peca: capturas) {
            if (peca.getIdEquipa() == branca) {
                capturasBrancas++;
            }
            else {
                capturaPretas++;
            }
        }
        int validasBrancas = 0;
        int validasPretas = 0;
        for (Integer integer: jogadasValidas) {
            if (integer == branca) {
                validasBrancas++;
            }
            else {
                validasPretas++;
            }
        }
        int inValidasBrancas = 0;
        int inValidasPretas = 0;
        for (Integer integer: jogadasInvalidas) {
            if (integer == branca) {
                inValidasBrancas++;
            }
            else {
                inValidasPretas++;
            }
        }
        resultados.add(resultado);
        resultado = "---";
        resultados.add(resultado);
        resultado = "Equipa das Pretas";
        resultados.add(resultado);
        resultado = Integer.toString(capturaPretas);
        resultados.add(resultado);
        resultado = Integer.toString(validasPretas);
        resultados.add(resultado);
        resultado = Integer.toString(inValidasPretas);
        resultados.add(resultado);
        resultado = "Equipa das Brancas";
        resultados.add(resultado);
        resultado = Integer.toString(capturasBrancas);
        resultados.add(resultado);
        resultado = Integer.toString(validasBrancas);
        resultados.add(resultado);
        resultado = Integer.toString(inValidasBrancas);
        resultados.add(resultado);
        return resultados;
    }

    public void undo() {
        turno--;
        turnoSemCapturas = turnoSemCapturasAnterior;
        jogadasValidas.remove(turno);
        if (capturas.get(turno).getIdTipo() == rei) {
            if (capturas.get(turno).getIdEquipa() == preta) {
                numBrancas++;
            }
            else {
                numPretas++;
            }
        }
        capturas.remove(turno);
    }
}
