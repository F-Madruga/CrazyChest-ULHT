package pt.ulusofona.lp2.crazyChess;

import java.io.IOException;

public class InvalidSimulatorInputException extends IOException {
    private int nrLinha;
    private int esperado;
    private int obtido;

    public InvalidSimulatorInputException(int nrLinha, int esperado, int obtido) {
        this.nrLinha = nrLinha + 1;
        this.esperado = esperado;
        this.obtido = obtido;
    }

    public int getLinhaErro() {
        return nrLinha;
    }

    public String getDescricaoProblema() {
        if (esperado < obtido) {
            return "DADOS A MAIS (Esperava: " + esperado + " ; Obtive: " + obtido + ")";
        }
        if (esperado > obtido) {
            return "DADOS A MENOS (Esperava: " + esperado + " ; Obtive: " + obtido + ")";
        }
        return "";
    }
}
