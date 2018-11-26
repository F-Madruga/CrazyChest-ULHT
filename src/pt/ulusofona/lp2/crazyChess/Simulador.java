package pt.ulusofona.lp2.crazyChess;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Simulador {
    Tabuleiro tabuleiro;
    GestorDeJogo gestor;
    List<CrazyPiece> pecas = new ArrayList<>();

    public boolean iniciaJogo(File ficheiroInicial) {
        try {
            Scanner scanner = new Scanner(ficheiroInicial);
            Map<Integer, CrazyPiece> pecas = new HashMap<>();
            int numPecas = 0;
            int numLinha = 0;
            int numPretas = 0;
            int numBrancas = 0;
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
                        this.pecas.add(peca);
                        pecas.put(Integer.parseInt(dados[0]), peca);
                    }
                    //Estado inicial do tabuleiro
                    else {
                        for (int coluna = 0; coluna < dados.length; coluna++) {
                            if (Integer.parseInt(dados[coluna]) != 0) {
                                CrazyPiece peca = pecas.get(Integer.parseInt(dados[coluna]));
                                this.tabuleiro.inserirPeca(peca, coluna,numLinha - numPecas - 2);
                                if (peca.getIdEquipa() == 0) {
                                    numPretas++;
                                }
                                if (peca.getIdEquipa() == 1) {
                                    numBrancas++;
                                }
                            }
                        }
                    }
                }
                numLinha++;
            }
            scanner.close();
            this.gestor = new GestorDeJogo(numPretas,numBrancas);
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
        return this.pecas;
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
        return this.gestor.quemEstaAJogar();
    }

    public List<String> getAutores() {
        List<String> autores = new ArrayList<>();
        String autor = "Francisco Silva";
        autores.add(autor);
        autor = "Rodrigo Cassanheira";
        autores.add(autor);
        return autores;
    }

    public boolean processaJogada(int xO, int yO, int xD, int yD) {
        if (this.tabuleiro.existemCoordenadas(xO,yO) && this.tabuleiro.existemCoordenadas(xD,yD)) {
            CrazyPiece origem = this.tabuleiro.getPeca(xO,yO);
            if(origem != null && origem.getIdEquipa() == this.getIDEquipaAJogar()) {
                CrazyPiece destino = this.tabuleiro.getPeca(xD,yD);
                if (destino == null || destino.getIdEquipa() != this.getIDEquipaAJogar()) {
                    if (origem.move(xD, yD)) {
                        if (destino != null) {
                            this.tabuleiro.removerPeca(destino);
                            this.gestor.adicionaCaptura(getIDEquipaAJogar());
                        }
                        else {
                            System.out.println("não houve cptura");
                            this.gestor.naoHouveCaptura();
                        }
                        this.gestor.validaJogada(getIDEquipaAJogar());
                        return true;
                    }
                }
            }
        }
        this.gestor.invalidaJogada(this.getIDEquipaAJogar());
        return false;
    }

    public boolean jogoTerminado() {
        return this.gestor.possoTerminarJogo();
    }

    public List<String> getResultados() {
        List<String> resultados = new ArrayList<>();
        String resultado = "JOGO DE CRAZY CHESS";
        resultados.add(resultado);
        resultado = "Resultado: ";
        if (this.gestor.getResultado() == 0) {
            resultado += "VENCERAM AS PRETAS";
        }
        if (this.gestor.getResultado() == 1) {
            resultado += "VENCERAM AS BRANCAS";
        }
        if (this.gestor.getResultado() == -1) {
            resultado += "EMPATE";
        }
        resultados.add(resultado);
        resultado = "---";
        resultados.add(resultado);
        resultado = "Equipa das Pretas";
        resultados.add(resultado);
        resultado = Integer.toString(gestor.getCapturas(0));
        resultados.add(resultado);
        resultado = Integer.toString(gestor.getJogadasValidas(0));
        resultados.add(resultado);
        resultado = Integer.toString(gestor.getJogadasInvalidas(0));
        resultados.add(resultado);
        resultado = "Equipa das Brancas";
        resultados.add(resultado);
        resultado = Integer.toString(gestor.getCapturas(1));
        resultados.add(resultado);
        resultado = Integer.toString(gestor.getJogadasValidas(1));
        resultados.add(resultado);
        resultado = Integer.toString(gestor.getJogadasInvalidas(1));
        resultados.add(resultado);
        return resultados;
    }
}