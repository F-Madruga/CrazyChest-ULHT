package pt.ulusofona.lp2.crazyChess;

import java.util.HashMap;
import java.util.Map;

public class GestorDeJogo {
    Map<Integer,Integer> capturas, jogadasValidas, jogadasInvalidas;
    int numPretas, numBrancas , turno, numTurnoSemCapturas, resultado;

    GestorDeJogo(int numPretas, int numBrancas) {
        this.numPretas = numPretas;
        this.numBrancas = numBrancas;
        this.capturas = new HashMap<>();
        this.jogadasValidas = new HashMap<>();
        this.jogadasInvalidas = new HashMap<>();
        this.turno = 0;
        this.numTurnoSemCapturas = 0;
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
        this.numTurnoSemCapturas++;
    }

    void adicionaCaptura(int equipaQueCaptura) {
        if (equipaQueCaptura == 0) {
            numBrancas--;
        }
        if (equipaQueCaptura == 1) {
            numPretas--;
        }
        if (!this.capturas.containsKey(equipaQueCaptura)) {
            this.capturas.put(equipaQueCaptura, 0);
        }
        this.capturas.put(equipaQueCaptura, this.capturas.get(equipaQueCaptura) + 1);
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
        if (numTurnoSemCapturas >= 10 || (numBrancas == 1 && numPretas == 1) || numBrancas <= 0 || numPretas <= 0) {
            if (numBrancas <= 0) {
                resultado = 0;
            }
            else if (numPretas <= 0) {
                resultado = 1;
            }
            else {
                this.resultado = -1;
            }
            return true;
        }
        else {
            return false;
        }
    }
}
