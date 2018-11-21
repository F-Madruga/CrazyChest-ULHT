package pt.ulusofona.lp2.crazyChess;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

public class Simulador {
    Tabuleiro tabuleiro;
    int numPecas;
    int turno = 0;

    public boolean iniciaJogo(File ficheiroInicial) {
        try {
            Scanner leitorFicheiro = new Scanner(ficheiroInicial);
            int numLinha = 0;
            while (leitorFicheiro.hasNextLine()) {
                String linha = leitorFicheiro.nextLine();
                if (numLinha == 0) {
                    this.tabuleiro = new Tabuleiro(Integer.parseInt(linha));
                } else if (numLinha == 1) {
                    this.numPecas = Integer.parseInt(linha);
                } else {
                    String dados[] = linha.split(":");
                    CrazyPiece peca = new CrazyPiece(Integer.parseInt(dados[0]), Integer.parseInt(dados[1]), Integer.parseInt(dados[2]), dados[3]);
                    tabuleiro.adicionarPeca(peca);
                }
                System.out.printf(linha);
                numLinha++;
            }
            leitorFicheiro.close();
            return true;
        } catch (FileNotFoundException exception) {
            return false;
        }
    }

    public int getTamanhoTabuleiro() {
        return this.tabuleiro.getTamanho();
    }

    public int equipaAJogar() {
        if (this.turno % 2 == 0) {
            return 0;
        }
        else {
            return 1;
        }
    }

    public boolean processaJogada(int xO, int yO, int xD, int Yd) {
        if (this.tabuleiro.coordenadasExitem(xO, yO) && this.tabuleiro.coordenadasExitem(xD, Yd)) {
            for (CrazyPiece peca: this.tabuleiro.getPecas()) {

            }
        }
        else {
            return false;
        }
    }

    public List<CrazyPiece> getPecaMaluca() {
        return this.tabuleiro.getPecas();
    }
}
