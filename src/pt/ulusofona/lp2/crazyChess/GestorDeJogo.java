package pt.ulusofona.lp2.crazyChess;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GestorDeJogo {

    public final static int PRETA = 10;
    public final static int BRANCA = 20;

    public final static int REI = 0;
    public final static int RAINHA = 1;
    public final static int PONEIMAGICO = 2;
    public final static int PADREDAVILA = 3;
    public final static int TORREH = 4;
    public final static int TORREV = 5;
    public final static int LEBRE = 6;
    public final static int JOKER = 7;

    private int numPretas;
    private int numBrancas;
    private int turno;
    private int turnoSemCapturasAnterior;
    private int turnoSemCapturas;
    private Map<Integer, Integer> capturas;
    private Map<Integer, Integer> jogadasValidas;
    private Map<Integer, Integer> jogadasInvalidas;
    private int resultado;

    public GestorDeJogo() {
        this.numPretas = 0;
        this.numBrancas = 0;
        this.turno = 0;
        this.turnoSemCapturas = 0;
        this.turnoSemCapturasAnterior = 0;
        this.resultado = -1;
        this.capturas = new HashMap<>();
        this.capturas.put(-PRETA, 0);
        this.capturas.put(-BRANCA, 0);
        this.jogadasValidas = new HashMap<>();
        this.jogadasValidas.put(-PRETA, 0);
        this.jogadasValidas.put(-BRANCA, 0);
        this.jogadasInvalidas = new HashMap<>();
        this.jogadasInvalidas.put(-PRETA, 0);
        this.jogadasInvalidas.put(-BRANCA, 0);
    }

    public int quemEstaAjogar() {
        if (turno % 2 == 0) {
            return PRETA;
        }
        else {
            return BRANCA;
        }
    }

    public void adicionarPeca(CrazyPiece peca) {
        if (peca.getIdTipo() == REI) {
            if (peca.getIdEquipa() == PRETA) {
                numPretas++;
            }
            if (peca.getIdEquipa() == BRANCA) {
                numBrancas++;
            }
        }
    }

    public void adicionarCaptura(CrazyPiece peca) {
        if (peca.getIdEquipa() == PRETA) {
            int captura = this.capturas.get(-BRANCA) + 1;
            capturas.put(-BRANCA, captura);
            capturas.put(turno, BRANCA);
        }
        if (peca.getIdEquipa() == BRANCA) {
            int captura = this.capturas.get(-PRETA) + 1;
            capturas.put(-PRETA, captura);
            capturas.put(turno, PRETA);
        }
        if (peca.getIdTipo() == REI) {
            if (quemEstaAjogar() == PRETA) {
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
        if (quemEstaAjogar() == PRETA) {
            int valida = jogadasValidas.get(-PRETA) + 1;
            jogadasValidas.put(-PRETA, valida);
            jogadasValidas.put(turno, PRETA);
        }
        else {
            int valida = jogadasValidas.get(-BRANCA) + 1;
            jogadasValidas.put(-BRANCA, valida);
            jogadasValidas.put(turno, BRANCA);
        }
        turno++;
        Joker.rotacaoTipoPeca = turno;
    }

    public void invalidaJogada() {
        if (quemEstaAjogar() == PRETA) {
            int valida = jogadasInvalidas.get(-PRETA) + 1;
            jogadasInvalidas.put(-PRETA, valida);
            jogadasInvalidas.put(turno, PRETA);
        }
        else {
            int valida = jogadasInvalidas.get(-BRANCA) + 1;
            jogadasInvalidas.put(-BRANCA, valida);
            jogadasInvalidas.put(turno, BRANCA);
        }
    }

    public void naoHouveCaptura() {
        turnoSemCapturasAnterior = turnoSemCapturas;
        turnoSemCapturas++;
    }

    public boolean possoTerminar() {
        if (turnoSemCapturas >= 10 || (numBrancas == 1 && numPretas == 1) || numBrancas <= 0 || numPretas <= 0) {
            if (numPretas >= 1 && numBrancas <= 0) {
                resultado = PRETA;
            } else if (numBrancas >= 1 && numPretas <= 0) {
                resultado = BRANCA;
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
        if (this.resultado == PRETA) {
            resultado += "VENCERAM AS PRETAS";
        }
        if (this.resultado == BRANCA) {
            resultado += "VENCERAM AS BRANCAS";
        }
        if (this.resultado == -1) {
            resultado += "EMPATE";
        }
        int capturasBrancas = capturas.get(-BRANCA);
        int capturaPretas = capturas.get(-PRETA);
        int validasBrancas = jogadasValidas.get(-BRANCA);
        int validasPretas = jogadasValidas.get(-PRETA);
        int inValidasBrancas = jogadasInvalidas.get(-BRANCA);
        int inValidasPretas = jogadasInvalidas.get(-PRETA);
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
        Joker.rotacaoTipoPeca = turno;
        turnoSemCapturas = turnoSemCapturasAnterior;
        if (capturas.containsKey(turno)) {
            int equipa = capturas.get(turno);
            int captura = capturas.get(-equipa) - 1;
            capturas.put(-equipa, captura);
            capturas.remove(turno);
        }
        if (jogadasValidas.containsKey(turno)) {
            int equipa = jogadasValidas.get(turno);
            int captura = jogadasValidas.get(-equipa) - 1;
            jogadasValidas.put(-equipa, captura);
            jogadasValidas.remove(turno);
        }
    }

    public int getTurnoSemCapturas() {
        return turnoSemCapturas;
    }

    public Map<Integer, Integer> getCapturas() {
        return capturas;
    }

    public Map<Integer, Integer> getJogadasValidas() {
        return jogadasValidas;
    }

    public Map<Integer, Integer> getJogadasInvalidas() {
        return jogadasInvalidas;
    }

    public void setEquipaAJogar(int equipaAJogar) {
        if (equipaAJogar == PRETA) {
            this.turno = 0;
        }
        else {
            this.turno = 1;
        }
    }

    public void setCapturas(int equipa, int numCapturas) {
        capturas.put(-equipa, numCapturas);
    }

    public void setJogadasValidas(int equipa, int numCapturas) {
        jogadasValidas.put(-equipa, numCapturas);
    }

    public void setJogadasInvalidas(int equipa, int numCapturas) {
        jogadasInvalidas.put(-equipa, numCapturas);
    }
}
