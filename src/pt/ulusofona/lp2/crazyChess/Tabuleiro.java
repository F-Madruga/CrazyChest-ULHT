package pt.ulusofona.lp2.crazyChess;

import java.util.ArrayList;
import java.util.List;

public class Tabuleiro {
    public static int tamanho;
    public static List<CrazyPiece> pecas;

    public Tabuleiro(int size) {
        tamanho = size;
        pecas = new ArrayList<>();
    }

    public void inserirPeca(CrazyPiece peca) {
        pecas.add(peca);
    }

    public List<CrazyPiece> getPecas() {
        return pecas;
    }

    public CrazyPiece getPecaById(int idPeca) {
        for (CrazyPiece peca: pecas) {
            if(peca.getId() == idPeca) {
                return peca;
            }
        }
        return null;
    }

    public CrazyPiece getPeca(int x, int y) {
        for (CrazyPiece peca: pecas) {
            if (peca != null && peca.getX() == x && peca.getY() == y) {
                return peca;
            }
        }
        return null;
    }

    public int getTamanho() {
        return tamanho;
    }

    public boolean existemCoordenadas(int x, int y) {
        if(x >= 0 && x < tamanho && y >= 0 && y < tamanho) {
            return true;
        }
        else {
            return false;
        }
    }

    public void removerPeca(CrazyPiece peca) {
        peca.setUltimaInteracao(GestorDeJogo.turno);
        peca.setCoordenadas(-1, -1);
    }

    public void anularJogadaAnterior() {
        for (CrazyPiece peca: pecas) {
            if (peca.getUltimaInteracao() == GestorDeJogo.turno) {
                peca.undo();
            }
        }
    }

    public List<String> obterSugestoesJogada(CrazyPiece peca) {
        List<String> sugestoes;
        sugestoes = peca.darSugestao();
        return sugestoes;
    }
}