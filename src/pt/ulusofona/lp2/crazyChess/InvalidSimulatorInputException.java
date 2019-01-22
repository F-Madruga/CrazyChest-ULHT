package pt.ulusofona.lp2.crazyChess;

import java.io.IOException;

public class InvalidSimulatorInputException extends IOException {
    private int nrLinha;
    private String linha[];
    private int nrPecas;
    private int tamanho;

    public InvalidSimulatorInputException(int nrLinha, String linha[], int nrPecas, int tamanho) {
        this.nrLinha = nrLinha;
        this.linha = linha;
        this.nrPecas = nrPecas;
        this.tamanho = tamanho;
    }

    public int getLinhaErro() {
        return nrLinha;
    }

    public String getDescricaoProblema() {
        int esperado;
        if (nrLinha == 0 || nrLinha == 1) {
            esperado = 1;
            if (linha.length > esperado) {
                return "DADOS A MAIS (Esperava: "+ esperado + " ; Obtive: " + linha.length + ")";
            }
            if (linha.length < esperado) {
                return "DADOS A MENOS (Esperava: "+ esperado + " ; Obtive: " + linha.length + ")";
            }
        }
        else if (nrLinha >= 2 && nrLinha < nrPecas + 2) {
            esperado = 3;
            if (linha.length > esperado) {
                return "DADOS A MAIS (Esperava: "+ esperado + " ; Obtive: " + linha.length + ")";
            }
            if (linha.length < esperado) {
                return "DADOS A MENOS (Esperava: "+ esperado + " ; Obtive: " + linha.length + ")";
            }
        }
        else if (nrLinha >= nrPecas + 2 && nrLinha < nrPecas + 2 + tamanho) {
            esperado = tamanho - 1;
            if (linha.length > esperado) {
                return "DADOS A MAIS (Esperava: "+ esperado + " ; Obtive: " + linha.length + ")";
            }
            if (linha.length < esperado) {
                return "DADOS A MENOS (Esperava: "+ esperado + " ; Obtive: " + linha.length + ")";
            }
        }
        else {
            esperado = 7;
            if (linha.length > esperado) {
                return "DADOS A MAIS (Esperava: "+ esperado + " ; Obtive: " + linha.length + ")";
            }
            if (linha.length < esperado) {
                return "DADOS A MENOS (Esperava: "+ esperado + " ; Obtive: " + linha.length + ")";
            }
        }
        return "";
    }
}
