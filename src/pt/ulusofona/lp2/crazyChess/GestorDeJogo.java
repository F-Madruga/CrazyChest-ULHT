package pt.ulusofona.lp2.crazyChess;

import java.util.HashMap;
import java.util.Map;

public class GestorDeJogo {
    Map<Integer,Integer> capturas, jogadasValidas, jogadasInvalidas, numPecas;
    int /*numPretas, numBrancas ,*/ turno, numTurnoSemCapturas, resultado;

    GestorDeJogo(/*int numPretas, int numBrancas*/Map<Integer,Integer> numPecas) {
        //this.numPretas = numPretas;
        //this.numBrancas = numBrancas;
        this.capturas = new HashMap<>();
        this.jogadasValidas = new HashMap<>();
        this.jogadasInvalidas = new HashMap<>();
        this.turno = 0;
        this.numTurnoSemCapturas = 0;
        this.numPecas = numPecas;
    }

    int getCapturas(int idEquipa) {
        if (this.capturas.containsKey(idEquipa)) {
            return this.capturas.get(idEquipa);
        }
        else {
            return 0;
        }
    }

    int getJogadasValidas(int idEquipa) {
        if (this.jogadasValidas.containsKey(idEquipa)) {
            return this.jogadasValidas.get(idEquipa);
        }
        else {
            return 0;
        }
    }

    int getJogadasInvalidas(int idEquipa) {
        if (this.jogadasInvalidas.containsKey(idEquipa)) {
            return this.jogadasInvalidas.get(idEquipa);
        }
        else {
            return 0;
        }
    }

    int getResultado () {
        return this.resultado;
    }

    public int quemEstaAJogar() {
        if (this.turno % 2 == 0) {
            return 0;
        }
        else {
            return 1;
        }
    }

    void naoHouveCaptura() {
        if (this.capturas.get(0) != null || this.capturas.get(1) != null) {
            this.numTurnoSemCapturas++;
        }
    }

    void adicionaCaptura(int equipaQueJoga) {
        this.numPecas.put(equipaQueJoga,this.numPecas.get(equipaQueJoga) - 1);
        /*if (equipaQueJoga == 0) {
            numBrancas--;
        }
        if (equipaQueJoga == 1) {
            numPretas--;
        }*/
        if (!this.capturas.containsKey(equipaQueJoga)) {
            this.capturas.put(equipaQueJoga, 0);
        }
        this.capturas.put(equipaQueJoga, this.capturas.get(equipaQueJoga) + 1);
        this.numTurnoSemCapturas = 0;
    }

    void validaJogada(int equipaQueJoga) {
        if (!this.jogadasValidas.containsKey(equipaQueJoga)) {
            this.jogadasValidas.put(equipaQueJoga,0);
        }
        this.jogadasValidas.put(equipaQueJoga,this.jogadasValidas.get(equipaQueJoga) + 1);
        this.turno++;
    }

    void invalidaJogada(int equipaQueJoga) {
        if (!this.jogadasInvalidas.containsKey(equipaQueJoga)) {
            this.jogadasInvalidas.put(equipaQueJoga,0);
        }
        this.jogadasInvalidas.put(equipaQueJoga,this.jogadasInvalidas.get(equipaQueJoga) + 1);
    }

    boolean possoTerminarJogo() {
        if (numTurnoSemCapturas >= 10 || (/*numBrancas*/this.numPecas.get(1) == 1 && /*numPretas*/ this.numPecas.get(0) == 1) || /*numBrancas*/ this.numPecas.get(1) <= 0 || /*numPretas*/ this.numPecas.get(0) <= 0) {
            if (/*numPretas*/this.numPecas.get(0) >= 1 && /*numBrancas*/this.numPecas.get(1) <= 0) {
                resultado = 0;
            } else if (/*numBrancas*/this.numPecas.get(1) >= 1 && /*numPretas*/this.numPecas.get(0) <= 0) {
                resultado = 1;
            } else {
                this.resultado = -1;
            }
            return true;
        }
        return false;
    }
}