package pt.ulusofona.lp2.crazyChess;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Simulador {
    Tabuleiro tabuleiro;

    Simulador() {
        this.tabuleiro = new Tabuleiro();
    }

    boolean iniciaJogo(File ficheiroInicial) {
        try {
            Scanner scanner = new Scanner(ficheiroInicial);
            Map<Integer, CrazyPiece> pecas = new HashMap<>();
            int tamanhoTabuleiro;
            int numPecas = 0;
            int numLinha = 0;
            while (scanner.hasNextLine()) {
                String linha = scanner.nextLine();
                if (numLinha == 0) {
                    tamanhoTabuleiro = Integer.parseInt(linha);
                }
                else if (numLinha == 1) {
                    numPecas = Integer.parseInt(linha);
                }
                else {
                    String dados [] = linha.split(":");
                    if (numLinha < numPecas + 2) {
                        CrazyPiece peca = new CrazyPiece(Integer.parseInt(dados[0]), Integer.parseInt(dados[1]), Integer.parseInt(dados[2]), dados[3]);
                        pecas.put(Integer.parseInt(dados[0]), peca);
                    }
                    else {

                    }
                }
                numLinha++;
            }
            scanner.close();
        }
        catch (FileNotFoundException exception) {
            return false;
        }
    }
}
