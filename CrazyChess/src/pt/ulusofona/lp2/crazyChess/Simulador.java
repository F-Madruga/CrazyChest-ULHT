package pt.ulusofona.lp2.crazyChess;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Simulador {
    Tabuleiro tabuleiro;
    int turno = 0;

    public boolean iniciaJogo(File ficheiroInicial) {
        Map<Integer,CrazyPiece> pecas = new HashMap<>();
        try {
            Scanner leitorFicheiro = new Scanner(ficheiroInicial);
            int numPecas = 0;
            int numLinha = 0;
            while (leitorFicheiro.hasNextLine()) {
                String linha = leitorFicheiro.nextLine();
                if (numLinha == 0) {
                    this.tabuleiro = new Tabuleiro(Integer.parseInt(linha));
                } else if (numLinha == 1) {
                    numPecas = Integer.parseInt(linha);
                }
                else {
                    String dados[] = linha.split(":");
                    if (numLinha == numPecas + 2) {
                        for (int i = 0; i < dados.length; i++){
                            if (Integer.parseInt(dados[i]) != 0) {
                                CrazyPiece peca = pecas.get(dados[i]);
                                peca.setCoordenadas(i,numLinha - numPecas - 2);
                                tabuleiro.adicionarPeca(peca);
                            }
                        }
                    }
                    else {
                        CrazyPiece peca = new CrazyPiece(Integer.parseInt(dados[0]), Integer.parseInt(dados[1]), Integer.parseInt(dados[2]), dados[3]);
                        pecas.put(Integer.parseInt(dados[0]), peca);
                    }
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
            for (CrazyPiece piece: this.tabuleiro.getPieces()) {
                if (piece.getPosX() == xO && piece.getPosY() == yO && piece.getIdEquipa() == this.equipaAJogar()) {
                    if(piece.movePiece(xO,yO,xD,Yd)){
                        return true;
                    }
                    else {
                        return false;
                    }
                }
                else {
                    return false;
                }
            }
        }
        else {
            return false;
        }
        return false;
    }

    public List<CrazyPiece> getPecaMaluca() {
        return this.tabuleiro.getPieces();
    }
}
