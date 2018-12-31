package pt.ulusofona.lp2.crazyChess;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Simulador {

    private Tabuleiro tabuleiro;

    public CrazyPiece definirPeca(int idPeca, int idTipo, int idEquipa, String alcunha) {
        CrazyPiece peca = null;
        if (idTipo == GestorDeJogo.REI) {
            peca = new Rei(idPeca, idTipo, idEquipa, alcunha);
        }
        if (idTipo == GestorDeJogo.RAINHA) {
            peca = new Rainha(idPeca, idTipo, idEquipa, alcunha);
        }
        if (idTipo == GestorDeJogo.PONEIMAGICO) {
            peca = new PoneiMagico(idPeca, idTipo, idEquipa, alcunha);
        }
        if (idTipo == GestorDeJogo.PADREDAVILA) {
            peca = new PadreDaVila(idPeca, idTipo, idEquipa, alcunha);
        }
        if (idTipo == GestorDeJogo.TORREH) {
            peca = new TorreH(idPeca, idTipo, idEquipa, alcunha);
        }
        if (idTipo == GestorDeJogo.TORREV) {
            peca = new TorreV(idPeca, idTipo, idEquipa, alcunha);
        }
        if (idTipo == GestorDeJogo.LEBRE) {
            peca = new Lebre(idPeca, idTipo, idEquipa, alcunha);
        }
        if (idTipo == GestorDeJogo.JOKER) {
            peca = new Joker(idPeca, idTipo, idEquipa, alcunha);
        }
        return peca;
    }

    public boolean iniciaJogo(File ficheiroInicial) {
        try {
            Scanner scanner = new Scanner(ficheiroInicial);
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
                        CrazyPiece peca = definirPeca(Integer.parseInt(dados[0]), Integer.parseInt(dados[1]), Integer.parseInt(dados[2]), dados[3]);
                        if (peca != null) {
                            this.tabuleiro.acrescentaPeca(peca);
                        }
                    }
                    //Estado inicial do tabuleiro
                    else {
                        for (int coluna = 0; coluna < dados.length; coluna++) {
                            if (Integer.parseInt(dados[coluna]) != 0) {
                                this.tabuleiro.colocarNoTabuleiro(Integer.parseInt(dados[coluna]), coluna,numLinha - numPecas - 2);
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

    public boolean processaJogada(int xO, int yO, int xD, int yD) {
        return this.tabuleiro.processaJogada(xO, yO, xD, yD);
    }

    public List<CrazyPiece> getPecasMalucas() {
        return this.tabuleiro.getPecas();
    }

    public boolean jogoTerminado() {
        return this.tabuleiro.possoTerminarJogo();
    }

    public List<String> getAutores() {
        List<String> autores = new ArrayList<>();
        String autor = "Francisco Silva";
        autores.add(autor);
        autor = "Rodrigo Cassanheira";
        autores.add(autor);
        return autores;
    }

    public List<String> getResultados() {
        return this.tabuleiro.getResultado();
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
        return this.tabuleiro.quemEstaAJogar();
    }

    public List<String> obterSugestoesJogada(int xO, int yO) {
        return this.tabuleiro.obterSugestoesJogada(xO, yO);
    }

    public void anularJogadaAnterior() {
        this.tabuleiro.undo();
    }

    public boolean gravarJogo(File ficheiroDestino) {
        return this.tabuleiro.gravarJogo(ficheiroDestino);
    } //TODO
}
