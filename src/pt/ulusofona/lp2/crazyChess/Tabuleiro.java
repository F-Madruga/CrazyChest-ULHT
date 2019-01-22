package pt.ulusofona.lp2.crazyChess;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class Tabuleiro {

    public static int compararPecasPorCapturas(CrazyPiece peca1, CrazyPiece peca2) {
        if (peca2.getCapturas().size() - peca1.getCapturas().size() != 0) {
            return peca2.getCapturas().size() - peca1.getCapturas().size();
        }
        return peca1.getAlcunha().compareTo(peca2.getAlcunha());
    }

    public static int compararPecasPorPontos(CrazyPiece peca1, CrazyPiece peca2) {
        if (peca2.getPontos() - peca1.getPontos() != 0) {
            return peca2.getPontos() - peca1.getPontos();
        }
        return peca1.getAlcunha().compareTo(peca2.getAlcunha());
    }

    public static String capturasToString(CrazyPiece peca) {
        return peca.getIdEquipa() + ":" + peca.getAlcunha() + ":" + peca.getPontos() + ":" + peca.getCapturas().size();
    }

    public static String jogadasToString(CrazyPiece peca) {
        return peca.getIdEquipa() + ":" + peca.getAlcunha() + ":" + peca.getJogadasInvalidas() + ":" + peca.getJogadasValidas();
    }

    public static boolean existemCoordenadas(int x, int y, int tamanho) {
        if (x >= 0 && x < tamanho && y >= 0 && y < tamanho) {
            return true;
        } else {
            return false;
        }
    }

    private int tamanho;
    private Map<Integer, CrazyPiece> pecas;
    private int [][] tabuleiro;
    private int [][] tabuleiroAnterior;
    private GestorDeJogo gestor;
    public boolean fazerUndo;
    public CrazyPiece ultimaPecaCapturada;
    public CrazyPiece ultimaPecaJogada;

    public Tabuleiro(int tamanho) {
        this.tamanho = tamanho;
        this.tabuleiro = new int[tamanho][tamanho];
        this.tabuleiroAnterior = new int[tamanho][tamanho];
        this.pecas = new HashMap<>();
        this.gestor = new GestorDeJogo();
        this.fazerUndo = false;
        this.ultimaPecaCapturada = null;
        Joker.ROTACAOTIPOPECA = gestor.getTurno();
    }

    public int getTamanho() {
        return tamanho;
    }

    public void acrescentaPeca(CrazyPiece peca) {
        this.pecas.put(peca.getId(), peca);
    }

    public void colocarNoTabuleiro(int id, int x, int y ) {
        this.tabuleiro[x][y] = id;
        this.tabuleiroAnterior[x][y] = id;
        if (id != 0) {
            this.gestor.contaPeca();
            this.pecas.get(id).setCoordenadas(x, y);
            if (pecas.get(id).getIdTipo() == GestorDeJogo.REI) {
                this.gestor.contaRei(pecas.get(id).getIdEquipa());
            }
        }
    }

    public List<CrazyPiece> getPecas() {
        return new ArrayList<CrazyPiece>(pecas.values());
    }

    public CrazyPiece getPeca(int x, int y) {
        if (existemCoordenadas(x, y, this.tamanho)) {
            if (this.tabuleiro[x][y] != 0) {
                return this.pecas.get(this.tabuleiro[x][y]);
            }
        }
        return null;
    }

    public boolean possoTerminarJogo() {
        return gestor.possoTerminar();
    }

    public int quemEstaAJogar() {
        return this.gestor.quemEstaAJogar();
    }

    public List<String> getResultado() {
        return this.gestor.getResultado();
    }

    public void undo() {
        if(this.fazerUndo && gestor.getTurno() > 0) {
            this.gestor.undo();
            for (CrazyPiece peca: getPecas()) {
                peca.undo(this.ultimaPecaCapturada, this.ultimaPecaJogada);
                ultimaPecaCapturada = null;
                ultimaPecaJogada = null;
            }
            for (int x = 0; x < this.tamanho; x++) {
                for (int y = 0; y < this.tamanho; y++) {
                    this.tabuleiro[x][y] = this.tabuleiroAnterior[x][y];
                }
            }
            this.fazerUndo = false;
        }
    }

    public List<Sugestao> obterSugestoesJogada(int xO, int yO) {
        Joker.ROTACAOTIPOPECA = gestor.getTurno();
        List<Sugestao> sugestoes = new ArrayList<>();
        if (existemCoordenadas(xO, yO, this.tamanho)) {
            if (this.tabuleiro[xO][yO] != 0) {
                if (this.pecas.get(this.tabuleiro[xO][yO]).getIdEquipa() == quemEstaAJogar()) {
                    return this.pecas.get(this.tabuleiro[xO][yO]).darSugestao(xO, yO, this.pecas, this.tabuleiro, this.gestor.getTurno());
                }
            }
        }
        return sugestoes;
    }

    public boolean processaJogada(int xO, int yO, int xD, int yD) {
        Joker.ROTACAOTIPOPECA = gestor.getTurno();
        if (existemCoordenadas(xO, yO, this.tamanho) && existemCoordenadas(xD, yD, this.tamanho)) {
            if (this.tabuleiro[xO][yO] != 0) {
                if (this.pecas.get(this.tabuleiro[xO][yO]).getIdEquipa() == quemEstaAJogar()) {
                    if (this.pecas.get(this.tabuleiro[xO][yO]).verificarSeMove(xO, yO, xD, yD, this.pecas, this.tabuleiro, this.gestor.getTurno())) {
                        if (this.tabuleiro[xD][yD] == 0) {
                            atualizarAnterior();
                            this.gestor.naoHouveCaptura();
                            this.tabuleiro[xD][yD] = this.tabuleiro[xO][yO];
                            this.pecas.get(this.tabuleiro[xD][yD]).setCoordenadas(xD, yD);
                            this.tabuleiro[xO][yO]  = 0;
                            this.fazerUndo = true;
                            this.pecas.get(tabuleiro[xD][yD]).contarJogadaValida();
                            this.ultimaPecaJogada = this.pecas.get(tabuleiro[xD][yD]);
                            this.ultimaPecaCapturada = null;
                            return true;
                        }
                        else {
                            if (this.pecas.get(this.tabuleiro[xD][yD]).getIdEquipa() != quemEstaAJogar()) {
                                atualizarAnterior();
                                this.pecas.get(tabuleiro[xO][yO]).captura(this.pecas.get(tabuleiro[xD][yD]));
                                this.ultimaPecaCapturada = this.pecas.get(tabuleiro[xD][yD]);
                                this.gestor.adicionarCaptura( this.pecas.get(this.tabuleiro[xD][yD]).getIdTipo());
                                this.pecas.get(this.tabuleiro[xD][yD]).resetCoordenadas();
                                this.tabuleiro[xD][yD] = this.tabuleiro[xO][yO];
                                this.pecas.get(this.tabuleiro[xD][yD]).setCoordenadas(xD, yD);
                                this.tabuleiro[xO][yO]  = 0;
                                this.fazerUndo = true;
                                this.pecas.get(tabuleiro[xD][yD]).contarJogadaValida();
                                this.ultimaPecaJogada = this.pecas.get(tabuleiro[xD][yD]);
                                return true;
                            }
                        }
                    }
                    this.pecas.get(tabuleiro[xO][yO]).contarJogadaInvalida();
                }
            }
        }
        this.gestor.invalidarJogada();
        return false;
    }

    public void load(String [] dados) {
        this.gestor.loadCapturas(GestorDeJogo.PRETA, Integer.parseInt(dados[2]));
        this.gestor.loadJogadasValidas(GestorDeJogo.PRETA, Integer.parseInt(dados[1]));
        this.gestor.loadJogadasInvalidas(GestorDeJogo.PRETA, Integer.parseInt(dados[3]));
        this.gestor.loadCapturas(GestorDeJogo.BRANCA, Integer.parseInt(dados[5]));
        this.gestor.loadJogadasValidas(GestorDeJogo.BRANCA, Integer.parseInt(dados[4]));
        this.gestor.loadJogadasInvalidas(GestorDeJogo.BRANCA, Integer.parseInt(dados[6]));
        this.gestor.loadTurnoSemCapturas(Integer.parseInt(dados[7]));
        this.gestor.loadEquipaAJogar();
    }

    public boolean gravarJogo(File ficheiroDestino) {
        try {
            FileWriter writer = new FileWriter (ficheiroDestino);
            String newLine = System.getProperty("line.separator");
            writer.write(Integer.toString(this.tamanho));
            writer.write(newLine);
            writer.write(Integer.toString(this.pecas.size()));
            writer.write(newLine);
            for (CrazyPiece peca: getPecas()) {
                writer.write(Integer.toString(peca.getId()) + ":" + Integer.toString(peca.getIdTipo()) + ":" + Integer.toString(peca.getIdEquipa()) + ":" + peca.getAlcunha());
                writer.write(newLine);
            }
            boolean primeiro = true;
            for (int x = 0; x < this.tabuleiro.length; x++) {
                primeiro = true;
                for (int y = 0; y < this.tabuleiro[x].length; y++) {
                    if (!primeiro) {
                        writer.write(":");
                    }
                    primeiro = false;
                    writer.write(Integer.toString(this.tabuleiro[y][x]));
                }
                writer.write(newLine);
            }
            writer.write(Integer.toString(quemEstaAJogar()) + ":" + Integer.toString(this.gestor.getJogadasValidas().get(-GestorDeJogo.PRETA)) + ":" + Integer.toString(this.gestor.getCapturas().get(-GestorDeJogo.PRETA)) + ":" + Integer.toString(this.gestor.getJogadasInvalidas().get(GestorDeJogo.PRETA)) + ":" + Integer.toString(this.gestor.getJogadasValidas().get(-GestorDeJogo.BRANCA)) + ":" + Integer.toString(this.gestor.getCapturas().get(-GestorDeJogo.BRANCA)) + ":" + Integer.toString(this.gestor.getJogadasInvalidas().get(GestorDeJogo.BRANCA)) + ":" + Integer.toString(this.gestor.getTurnoSemCapturas()));
            writer.close();
            return true;
        }
        catch (IOException exception) {
            return false;
        }
    }

    public void atualizarAnterior(){
        for (int x = 0; x < this.tamanho; x++) {
            for (int y = 0; y < this.tamanho; y++) {
                this.tabuleiroAnterior[x][y] = this.tabuleiro[x][y];
            }
        }
    }

    public Map<Integer, CrazyPiece> getPecasMap() {
        return pecas;
    }

    public int[][] getTabuleiro() {
        return tabuleiro;
    }

    public int[][] getTabuleiroAnterior() {
        return tabuleiroAnterior;
    }

   public Map<String, List<String>> getEstatisticas() {
        Map<String, List<String>> estatisticas = new HashMap<>();

        estatisticas.put("top5Capturas", getPecas().stream()
                .sorted(Tabuleiro::compararPecasPorCapturas)
                .limit(5)
                .map(Tabuleiro::capturasToString)
                .collect(Collectors.toList()));

        estatisticas.put("top5Pontos", getPecas().stream()
                .sorted(Tabuleiro::compararPecasPorPontos)
                .limit(5)
                .map(Tabuleiro::capturasToString)
                .collect(Collectors.toList()));

        estatisticas.put("pecasMais5Capturas", getPecas().stream()
                .filter((p) -> p.getCapturas().size() > 5)
                .map(Tabuleiro::capturasToString)
                .collect(Collectors.toList()));

        estatisticas.put("3PecasMaisBaralhadas", getPecas().stream()
                .sorted((p1, p2) -> p2.getJogadasInvalidas() - p1.getJogadasInvalidas())
                .limit(3)
                .map(Tabuleiro::jogadasToString)
                .collect(Collectors.toList()));

        Map<Integer, Integer> numCapturaTipo = new HashMap<>();
        getPecas().stream()
                .forEach((p) -> numCapturaTipo.put(p.getIdTipo(), 0));
        getPecas().stream()
                .forEach((p) -> numCapturaTipo.put(p.getIdTipo(), numCapturaTipo.get(p.getIdTipo()) + p.getCapturas().size()));
        List<String> tiposPecaCapturados = new ArrayList<>();
        new ArrayList<Integer>(numCapturaTipo.keySet()).stream()
               .filter((n) -> numCapturaTipo.get(n) > 0)
               .sorted((n1, n2) -> numCapturaTipo.get(n2) - numCapturaTipo.get(n1))
               .forEach((n) -> tiposPecaCapturados.add(n + ":" + numCapturaTipo.get(n)));
        estatisticas.put("tiposPecaCapturados", tiposPecaCapturados);
        return estatisticas;
    }
}
