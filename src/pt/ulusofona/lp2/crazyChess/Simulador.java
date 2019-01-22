package pt.ulusofona.lp2.crazyChess;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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

    public void iniciaJogo(File ficheiroInicial) throws InvalidSimulatorInputException, IOException {
        try {
            Scanner scanner = new Scanner(ficheiroInicial);
            int numPecas = 0;
            int numLinha = 0;
            while (scanner.hasNextLine()) {
                String linha = scanner.nextLine();
                if (numLinha == 0) {
                    if (linha.equals("")) {
                        throw new InvalidSimulatorInputException(numLinha, 1, 0);
                    }
                    if (linha.split(":").length != 1) {
                        throw new InvalidSimulatorInputException(numLinha, 1, linha.split(":").length);
                    }
                    this.tabuleiro = new Tabuleiro(Integer.parseInt(linha));
                }
                else if (numLinha == 1) {

                    if (linha.equals("")) {
                        throw new InvalidSimulatorInputException(numLinha, 1, 0);
                    }
                    if (linha.split(":").length != 1) {
                        throw new InvalidSimulatorInputException(numLinha, 1, linha.split(":").length);
                    }
                    numPecas = Integer.parseInt(linha);
                }
                else {
                    String dados [] = linha.split(":");
                    //Caracterizaçao das pecas
                    if (numLinha < numPecas + 2) {
                        if (linha.equals("")) {
                            throw new InvalidSimulatorInputException(numLinha, 4, 0);
                        }
                        if (dados.length != 4) {
                            throw new InvalidSimulatorInputException(numLinha, 4, dados.length);
                        }
                        CrazyPiece peca = definirPeca(Integer.parseInt(dados[0]), Integer.parseInt(dados[1]), Integer.parseInt(dados[2]), dados[3]);
                        if (peca != null) {
                            this.tabuleiro.acrescentaPeca(peca);
                        }
                    }
                    //Estado inicial do tabuleiro
                    else if (numLinha < numPecas + 2 + tabuleiro.getTamanho()) {
                        if (linha.equals("")) {
                            throw new InvalidSimulatorInputException(numLinha, tabuleiro.getTamanho(), 0);
                        }
                        if (dados.length != tabuleiro.getTamanho()) {
                            throw new InvalidSimulatorInputException(numLinha, tabuleiro.getTamanho(), dados.length);
                        }
                        for (int coluna = 0; coluna < dados.length; coluna++) {
                            this.tabuleiro.colocarNoTabuleiro(Integer.parseInt(dados[coluna]), coluna,numLinha - numPecas - 2);
                        }
                    }
                    else {
                        if (!linha.equals("") && dados.length != 8) {
                            throw new InvalidSimulatorInputException(numLinha, 8, dados.length);
                        }
                        tabuleiro.load(dados);
                    }
                }
                numLinha++;
            }
            scanner.close();
        }
        catch (FileNotFoundException exception) {
            throw new IOException();
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
        autores.add("Francisco Silva");
        autores.add("Rodrigo Cassanheira");
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

    public List<Sugestao> obterSugestoesJogada(int xO, int yO) {
        return this.tabuleiro.obterSugestoesJogada(xO, yO);
    }

    public void anularJogadaAnterior() {
        this.tabuleiro.undo();
    }

    public boolean gravarJogo(File ficheiroDestino) {
        return this.tabuleiro.gravarJogo(ficheiroDestino);
    }

    public Map<String, List<String>> getEstatisticas() {
        return tabuleiro.getEstatisticas();
    }
}
