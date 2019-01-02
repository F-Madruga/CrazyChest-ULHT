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

    private int turno;
    private int turnoSemCapturas;
    private int turnoSemCapturasAnterior;
    private int resultado;
    private Map<Integer, Integer> numReis; //key = idEquipa -> value = numero de reis no turno atual e key = -idEquipa -> value = numero de reis no turno anterior
    private Map<Integer, Integer> capturas; // key = -idEquipa -> value = numero de capturas da equipa, key = turno -> value = equipa que capturou naquele turno
    private Map<Integer, Integer> jogadasValidas; // key = -idEquipa -> value = numero de jogadas valida da equipa, key = turno -> value = equipa que fez jogadas valida naquele turno
    private Map<Integer, Integer> jogadasInvalidas;// key = idEquipa -> value = numero de jogadas invalidas

    public GestorDeJogo() {
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
        this.jogadasInvalidas.put(PRETA, 0);
        this.jogadasInvalidas.put(BRANCA, 0);
        this.numReis = new HashMap<>();
        this.numReis.put(PRETA, 0);
        this.numReis.put(BRANCA, 0);
        this.numReis.put(-PRETA, 0);
        this.numReis.put(-BRANCA, 0);
    }

    public int getTurno() {
        return turno;
    }

    public int quemEstaAJogar() {
        if (turno % 2 == 0) {
            return PRETA;
        }
        else {
            return BRANCA;
        }
    }

    public void contaRei(int idEquipa) {
        if (idEquipa == PRETA || idEquipa == BRANCA) {
            int numRei = this.numReis.get(idEquipa) + 1;
            this.numReis.put(idEquipa, numRei);
            this.numReis.put(-idEquipa, numRei);
        }
    }

    public void validarJogada() {
        int jogadasValidas = this.jogadasValidas.get(-quemEstaAJogar()) + 1;
        this.jogadasValidas.put(-quemEstaAJogar(), jogadasValidas);
        this.jogadasValidas.put(this.turno, quemEstaAJogar());
        turno++;
        Joker.ROTACAOTIPOPECA = turno;
    }

    public void naoHouveCaptura() {
        this.turnoSemCapturasAnterior = this.turnoSemCapturas;
        this.turnoSemCapturas++;
        int numReisAnterior = this.numReis.get(quemEstaAJogar());
        this.numReis.put(-quemEstaAJogar(), numReisAnterior);
        validarJogada();
    }

    public void adicionarCaptura(int idTipo) {
        int capturas = this.capturas.get(-quemEstaAJogar()) + 1;
        this.capturas.put(-quemEstaAJogar(), capturas);
        this.capturas.put(this.turno, quemEstaAJogar());
        if (idTipo == REI) {
            int idEquipa;
            if (quemEstaAJogar() == PRETA) {
                idEquipa = BRANCA;
            } else {
                idEquipa = PRETA;
            }
            int numReis = this.numReis.get(idEquipa);
            this.numReis.put(-idEquipa, numReis);
            numReis -= 1;
            this.numReis.put(idEquipa, numReis);
        }
        this.turnoSemCapturasAnterior = this.turnoSemCapturas;
        this.turnoSemCapturas = 0;
        validarJogada();
    }

    public void invalidarJogada() {
        int jogadasInvalidas = this.jogadasInvalidas.get(quemEstaAJogar()) + 1;
        this.jogadasInvalidas.put(quemEstaAJogar(), jogadasInvalidas);
    }

    public boolean possoTerminar() {
        if (this.turnoSemCapturas >= 10 || (this.numReis.get(BRANCA) == 1 && this.numReis.get(PRETA) == 1) || this.numReis.get(BRANCA) <= 0 || this.numReis.get(PRETA) <= 0) {
            if (this.numReis.get(PRETA) >= 1 && this.numReis.get(BRANCA) <= 0) {
                this.resultado = PRETA;
            } else if (this.numReis.get(PRETA) >= 1 && this.numReis.get(BRANCA) <= 0) {
                this.resultado = BRANCA;
            } else {
                this.resultado = -1;
            }
            return true;
        }
        return false;
    }

    public List<String> getResultado () {
        List<String> resultados = new ArrayList<>();
        resultados.add("JOGO DE CRAZY CHESS");
        if (this.resultado == PRETA) {
            resultados.add("Resultado: VENCERAM AS PRETAS");
        }
        if (this.resultado == BRANCA) {
            resultados.add("Resultado: VENCERAM AS BRANCAS");
        }
        if (this.resultado == -1) {
            resultados.add("Resultado: EMPATE");
        }
        resultados.add("---");
        resultados.add("Equipa das Pretas");
        resultados.add(Integer.toString(this.capturas.get(-PRETA)));
        resultados.add(Integer.toString(this.jogadasValidas.get(-PRETA)));
        resultados.add(Integer.toString(this.jogadasInvalidas.get(PRETA)));
        resultados.add("Equipa das Brancas");
        resultados.add(Integer.toString(this.capturas.get(-BRANCA)));
        resultados.add(Integer.toString(this.jogadasValidas.get(-BRANCA)));
        resultados.add(Integer.toString(this.jogadasInvalidas.get(BRANCA)));
        return resultados;
    }

    public void undo() {
        if (turno > 0) {
            this.turno--;
            Joker.ROTACAOTIPOPECA = turno;
            this.turnoSemCapturas = this.turnoSemCapturasAnterior;
            int idEquipa;
            if (quemEstaAJogar() == PRETA) {
                idEquipa = BRANCA;
            } else {
                idEquipa = PRETA;
            }
            int numReis = this.numReis.get(-idEquipa);
            this.numReis.put(idEquipa, numReis);
            if (this.capturas.containsKey(turno)) {
                this.capturas.remove(turno);
                int capturas = this.capturas.get(-quemEstaAJogar()) - 1;
                this.capturas.put(-quemEstaAJogar(), capturas);
            }
            if (this.jogadasValidas.containsKey(turno)) {
                this.jogadasValidas.remove(turno);
                int jogadasValidas = this.jogadasValidas.get(-quemEstaAJogar()) - 1;
                this.jogadasValidas.put(-quemEstaAJogar(), jogadasValidas);
            }
        }
    }

    public void loadEquipaAJogar() {
        int jogadasValidasPreto = jogadasValidas.get(-PRETA);
        int jogadasValidasBranco = jogadasValidas.get(-BRANCA);
        turno = jogadasValidasBranco + jogadasValidasPreto;
        Joker.ROTACAOTIPOPECA = turno;
    }

    public void loadCapturas(int idEquipa, int numCapturas) {
        this.capturas.put(-idEquipa, numCapturas);
    }

    public void loadJogadasValidas(int idEquipa, int numJogadasValidas) {
        this.jogadasValidas.put(-idEquipa, numJogadasValidas);
    }

    public void loadJogadasInvalidas(int idEquipa, int numJogadasInvalidas) {
        this.jogadasInvalidas.put(idEquipa, numJogadasInvalidas);
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

    public Map<Integer, Integer> getNumReis() {
        return numReis;
    }

    public int getTurnoSemCapturas() {
        return turnoSemCapturas;
    }

    public int getTurnoSemCapturasAnterior() {
        return turnoSemCapturasAnterior;
    }
}
