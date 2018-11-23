package pt.ulusofona.lp2.crazyChess;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Simulador {
    Tabuleiro tabuleiro;

    Simulador() {

    }

    boolean iniciaJogo(File ficheiroInicial) {
        try {
            Scanner scanner = new Scanner(ficheiroInicial);
            Map<Integer, CrazyPiece> pecas = new HashMap<>();
            int numPecas = 0;
            int numLinha = 0;
            while (scanner.hasNextLine()) {
                String linha = scanner.nextLine();
                if (numLinha == 0) {
                    this.tabuleiro = new Tabuleiro(Integer.parseInt(linha));
                }
                else if (numLinha == 1) {
                    numPecas = Integer.parseInt(linha);
                }
                else {
                    String dados [] = linha.split(":");
                    //Caracterizaçao das pecas
                    if (numLinha < numPecas + 2) {
                        CrazyPiece peca = new CrazyPiece(Integer.parseInt(dados[0]), Integer.parseInt(dados[1]), Integer.parseInt(dados[2]), dados[3]);
                        pecas.put(Integer.parseInt(dados[0]), peca);
                    }
                    //Estado inicial do tabuleiro
                    else {
                        for (int coluna = 0; coluna < dados.length; coluna++) {
                            if (Integer.parseInt(dados[coluna]) != 0) {
                                CrazyPiece peca = pecas.get(Integer.parseInt(dados[coluna]));
                                peca.setCoordenadas(coluna,numLinha - numPecas - 2);
                                this.tabuleiro.inserirPeca(peca);
                            }
                        }
                    }
                }
                numLinha++;
            }
            scanner.close();
            return true;
        }
        catch (FileNotFoundException exception) {
            return false;
        }
    }
}
