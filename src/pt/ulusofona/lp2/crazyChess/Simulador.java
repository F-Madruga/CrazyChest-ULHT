package pt.ulusofona.lp2.crazyChess;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Simulador {
    Tabuleiro tabuleiro;

    public boolean iniciaJogo(File ficheiroInicial) {
        try {
            Scanner scanner = new Scanner(ficheiroInicial);
            Map<Integer, CrazyPiece> pecas = new HashMap<>();
            int numPecas = 0;
            int numLinha = 0;
            while (scanner.hasNextLine()) {
                String linha = scanner.nextLine();
                if (numLinha == 0) {
                    System.out.printf("Tamanho do Tabuleiro = " + Integer.parseInt(linha));
                    this.tabuleiro = new Tabuleiro(Integer.parseInt(linha));
                }
                else if (numLinha == 1) {
                    System.out.println("Numero de peças = " + Integer.parseInt(linha));
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
                                this.tabuleiro.inserirPeca(peca, coluna,numLinha - numPecas - 2);
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

    public int getTamanhoTabuleiro() {
        return this.tabuleiro.getTamanho();
    }

    public List<CrazyPiece> getPecasMalucas() {
        return this.tabuleiro.getPecas();
    }

    public int getIDPeca(int x, int y) {
        CrazyPiece peca = this.tabuleiro.getPeca(x, y);
        if (peca == null) {
            return 0;
        }
        else {
            return peca.getId();
        }
    }

    public int getIDEquipaAJogar() {
        return this.tabuleiro.getIdEquipaAJogar();
    }

    public List<String> getAutores() {
        List<String> autores = new ArrayList<>();
        String autor1 = "Francisco Silva";
        autores.add(autor1);
        String autor2 = "Rodrigo Cassanheira";
        autores.add(autor2);
        return autores;
    }

    public boolean processaJogada(int xO, int yO, int xD, int yD) {
        return this.tabuleiro.processaJogada(xO, yO, xD, yD);
    }
}
