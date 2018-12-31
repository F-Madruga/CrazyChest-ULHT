package pt.ulusofona.lp2.crazyChess;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Tabuleiro {

    private int tamanho;
    private List<CrazyPiece> pecas;
    private GestorDeJogo gestor;
    private boolean fazerUndo;

    public Tabuleiro(int tamanho) {
        this.tamanho = tamanho;
        this.pecas = new ArrayList<>();
        this.gestor = new GestorDeJogo();
        this.fazerUndo = false;
    }

    public boolean existemCoordenadas(int x, int y) {
        if (x >= 0 && x < this.tamanho && y >= 0 && y < this.tamanho) {
            return true;
        } else {
            return false;
        }
    }

    public void acrescentaPeca(CrazyPiece peca) {
        this.pecas.add(peca);
    }

    public List<CrazyPiece> getPecas() {
        return this.pecas;
    }

    public CrazyPiece getPeca(int x, int y) {
        for (CrazyPiece peca : this.pecas) {
            if (peca.getX() == x && peca.getY() == y) {
                return peca;
            }
        }
        return null;
    }

    public void colocarNoTabuleiro(int id, int x, int y) {
        if (existemCoordenadas(x, y)) {
            for (CrazyPiece peca : this.pecas) {
                if (peca.getId() == id) {
                    peca.colocarNoTabuleiro(x, y);
                    if (peca.getIdTipo() == 0) {
                        gestor.adicionarPeca(peca);
                    }
                }
            }
        }
    }

    public int getTamanho() {
        return this.tamanho;
    }

    public boolean processaJogada(int xO, int yO, int xD, int yD) {
        if (existemCoordenadas(xO, yO) && existemCoordenadas(xD, yD)) {
            CrazyPiece origem = getPeca(xO, yO);
            if (origem != null && origem.getIdEquipa() == gestor.quemEstaAjogar() && origem.verificarSeMove(xD, yD, this.pecas, this.gestor.getTurno())) {
                CrazyPiece destino = getPeca(xD, yD);
                if (destino == null || destino.getIdEquipa() != gestor.quemEstaAjogar()) {
                    if (destino != null) {
                        destino.alterarCoordenada(-1, -1, this.gestor.getTurno());
                        this.gestor.adicionarCaptura(destino);
                    } else {
                        this.gestor.naoHouveCaptura();
                    }
                    origem.alterarCoordenada(xD, yD, this.gestor.getTurno());
                    this.gestor.validaJogada();
                    this.fazerUndo = true;
                    return true;
                }
            }
        }
        this.gestor.invalidaJogada();
        return false;
    }

    public boolean possoTerminarJogo() {
        return gestor.possoTerminar();
    }

    public int quemEstaAJogar() {
        return this.gestor.quemEstaAjogar();
    }

    public List<String> getResultado() {
        return this.gestor.getResultado();
    }

    public void undo() {
        if (fazerUndo && gestor.getTurno() > 0) {
            this.gestor.undo();
            for (CrazyPiece peca : this.pecas) {
                peca.undo(this.gestor.getTurno());
            }
            fazerUndo = false;
        }
    }

    public List<String> obterSugestoesJogada(int xO, int yO) {
        CrazyPiece peca = getPeca(xO, yO);
        if (peca != null && peca.getIdEquipa() == quemEstaAJogar()) {
            return peca.darSugestoes(this.pecas, this.gestor.getTurno(), this.tamanho);
        } else {
            return new ArrayList<>();
        }
    }

    public boolean gravarJogo(File ficheiroDestino) {
        try {
            FileWriter writer = new FileWriter (ficheiroDestino);
            writer.write(this.tamanho);
            CrazyPiece [][] tabuleiro = new CrazyPiece [this.tamanho][this.tamanho];
            writer.write(this.pecas.size());
            for (CrazyPiece peca: pecas) {
                String dados = peca.getId() + ":" + peca.getIdTipo() + ":" + peca.getIdEquipa() + ":" + peca.getAlcunha();
                if (existemCoordenadas(peca.getX(), peca.getY())) {
                    tabuleiro[peca.getX()][peca.getY()] = peca;
                }
                writer.write(dados);
            }
            for (int x = 0; x < tamanho; x++) {
                for (int y = 0; y < tamanho; y++) {
                    if (tabuleiro[x][y] == null) {
                        writer.write(0);
                    }
                    else {
                        writer.write(tabuleiro[x][y].getId());
                    }
                }
            }
            writer.write(this.gestor.getTurno() + ":" + this.gestor.getTurnoSemCapturas());
            String captura = "";
            boolean primeiro = true;
            for (int i = 0; i <= this.gestor.getTurno(); i++) {
                if (!primeiro) {
                    captura = captura + ":";
                    primeiro = false;
                }
                if (this.gestor.getCapturas().get(i) != null) {
                    captura = captura + this.gestor.getCapturas().get(i).getId() + "|" + this.gestor.getCapturas().get(i).getX() + this.gestor.getCapturas().get(i).getY();
                }
                else {
                    captura = captura + "(zero)";
                }
            }
            String jogadasValidas = "";
            primeiro = true;
            for (Integer num: this.gestor.getJogadasValidas()) {
                if (!primeiro) {
                    jogadasValidas = jogadasValidas + ":";
                    primeiro = false;
                }
                jogadasValidas = jogadasValidas +  num;
            }
            String jogadasInvalidas = "";
            primeiro = true;
            for (Integer num: this.gestor.getJogadasInvalidas()) {
                if (!primeiro) {
                    jogadasInvalidas = jogadasInvalidas + ":";
                    primeiro = false;
                }
                jogadasInvalidas = jogadasInvalidas + ":" + num;
            }
            writer.write(captura);
            writer.write(jogadasValidas);
            writer.write(jogadasInvalidas);
            writer.close();
            return true;
        }
        catch (IOException exception) {
            return false;
        }
    }

}
