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
            String newLine = System.getProperty("line.separator");
            String tamanho = this.tamanho +"";
            writer.write(tamanho);
            writer.write(newLine);
            String numPecas = this.pecas.size() + "";
            writer.write(numPecas);
            writer.write(newLine);
            for (CrazyPiece peca: pecas) {
                String id = peca.getId() + "";
                String tipo = peca.getIdTipo() + "";
                String equipa = peca.getIdEquipa() + "";
                String alcunha = peca.getAlcunha() + "";
                String dados = id  + ":" + tipo + ":" + equipa + ":" + alcunha;
                writer.write(dados);
                writer.write(newLine);
            }
            for (int x = 0; x < this.tamanho; x++) {
                boolean primeiro = true;
                for (int y = 0; y < this.tamanho; y++) {
                    if (!primeiro) {
                        writer.write(":");
                    }
                    boolean existePeca = false;
                    for (CrazyPiece peca:this.pecas) {
                        if (peca.getX() == y && peca.getY() == x) {
                            String piece = peca.getId() + "";
                            writer.write(piece);
                            existePeca = true;
                            break;
                        }
                    }
                    if (!existePeca) {
                        writer.write("0");
                    }
                    primeiro = false;
                }
                writer.write(newLine);
            }
            String quemEstaAJogar = quemEstaAJogar() + "";
            String capturaPreto = gestor.getCapturas().get(-GestorDeJogo.PRETA) + "";
            String validasPreto = gestor.getJogadasValidas().get(-GestorDeJogo.PRETA) + "";
            String invalidasPreto = gestor.getJogadasInvalidas().get(-GestorDeJogo.PRETA) + "";
            String capturaBranco = gestor.getCapturas().get(-GestorDeJogo.BRANCA) + "";
            String validasBranco = gestor.getJogadasValidas().get(-GestorDeJogo.BRANCA) + "";
            String invalidasBranco = gestor.getJogadasInvalidas().get(-GestorDeJogo.BRANCA) + "";
            writer.write(quemEstaAJogar + ":" + capturaPreto + ":" + validasPreto + ":" + invalidasPreto  + ":" + capturaBranco + ":" + validasBranco + ":" + invalidasBranco);
            writer.write(newLine);
            writer.close();
            return true;
        }
        catch (IOException exception) {
            return false;
        }
    }

    public void load(String [] dados) {
        gestor.setEquipaAJogar(Integer.parseInt(dados[0]));
        gestor.setCapturas(GestorDeJogo.PRETA, Integer.parseInt(dados[1]));
        gestor.setJogadasValidas(GestorDeJogo.PRETA, Integer.parseInt(dados[2]));
        gestor.setJogadasInvalidas(GestorDeJogo.PRETA, Integer.parseInt(dados[3]));
        gestor.setCapturas(GestorDeJogo.BRANCA, Integer.parseInt(dados[4]));
        gestor.setJogadasValidas(GestorDeJogo.BRANCA, Integer.parseInt(dados[5]));
        gestor.setJogadasInvalidas(GestorDeJogo.BRANCA, Integer.parseInt(dados[6]));
    }
}
