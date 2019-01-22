package pt.ulusofona.lp2.crazyChess;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public abstract class CrazyPiece {

    protected int idPeca;
    protected int idTipo;
    protected int idEquipa;
    protected String alcunha;
    protected String coordenadas;
    protected String coordenadasAnterior;
    protected int jogadasInvalidas;
    protected int jogadasValidas;
    protected List<CrazyPiece> capturas;

    public CrazyPiece(int idPeca, int idTipo, int idEquipa, String alcunha) {
        this.idPeca = idPeca;
        this.idTipo = idTipo;
        this.idEquipa = idEquipa;
        this.alcunha = alcunha;
        this.coordenadas = "(n/a)";
        this.coordenadasAnterior = this.coordenadas;
        this.jogadasInvalidas = 0;
        this.jogadasValidas = 0;
        this.capturas = new ArrayList<>();
    }

    @Override
    public boolean equals(Object obj) {
        CrazyPiece peca = (CrazyPiece) obj;
        return this.idPeca == peca.getId();
    }

    public int getId() {
        return idPeca;
    }

    public int getIdTipo() {
        return idTipo;
    }

    public int getIdEquipa() {
        return idEquipa;
    }

    public String getAlcunha() {
        return alcunha;
    }

    public void setCoordenadas(int x, int y) {
        this.coordenadasAnterior = this.coordenadas;
        this.coordenadas = "(" + x + ", " + y + ")";
    }

    public void resetCoordenadas() {
        this.coordenadasAnterior = this.coordenadas;
        this.coordenadas = "(n/a)";
    }

    public void undo(CrazyPiece ultimaPecaCapturada, CrazyPiece ultimaPecaJogada) {
        this.coordenadas = this.coordenadasAnterior;
        if (ultimaPecaCapturada != null) {
            this.capturas.remove(ultimaPecaCapturada);
        }
        if (ultimaPecaJogada.equals(this)) {
            this.jogadasValidas--;
        }
    }

    public List<Comparable> darSugestao(int xO, int yO, Map<Integer, CrazyPiece> pecas, int [][] tabuleiro, int turno) {
        List<Comparable> sugestoes = new ArrayList<>();
        if (Tabuleiro.existemCoordenadas(xO, yO, tabuleiro.length)) {
            if (tabuleiro[xO][yO] == this.idPeca) {
                for (int x = 0; x < tabuleiro.length; x++) {
                    for (int y = 0; y < tabuleiro[x].length; y++) {
                        if (tabuleiro[x][y] == 0) {
                            if (verificarSeMove(xO, yO, x, y, pecas, tabuleiro, turno)) {
                                sugestoes.add(new Sugestao(x, y));
                            }
                        }
                        else if (pecas.get(tabuleiro[x][y]).getIdEquipa() != this.idEquipa) {
                            if (verificarSeMove(xO, yO, x, y, pecas, tabuleiro, turno)) {
                                Sugestao sugestao = new Sugestao(x, y);
                                sugestao.setPontos(pecas.get(tabuleiro[x][y]).getValorRelativo());
                                sugestoes.add(sugestao);
                            }
                        }
                    }
                }
            }
        }
        return sugestoes;
    }

    protected abstract int getValorRelativo();

    protected abstract String getNome();

    public abstract String getImagePNG();

    @Override
    public String toString() {
        return this.idPeca + " | " + this.getNome() + " | " + this.getValorRelativo() + " | " + this.idEquipa + " | " + this.alcunha + " @ " + this.coordenadas;
    }

    public abstract boolean verificarSeMove(int xO, int yO, int xD, int yD, Map<Integer, CrazyPiece> pecas, int [][] tabuleiro, int turno);

    protected boolean moveHorizontal(int yO, int yD) {
        return yO == yD;
    }

    protected boolean moveVertical(int xO, int xD) {
        return xO == xD;
    }

    protected boolean moveDiagonal(int xO, int yO, int xD, int yD) {
        int distanciaH = xD - xO;
        int distanciaV = yD - yO;
        if (distanciaH < 0) {
            distanciaH = -distanciaH;
        }
        if (distanciaV < 0) {
            distanciaV = -distanciaV;
        }
        return distanciaH == distanciaV;
    }

    protected boolean move(int xO, int yO, int xD, int yD) {
        return xO != xD || yO != yD;
    }

    protected boolean moveDentroLimite(int xO, int yO, int xD, int yD, int limite) {
        int distanciaH = xD - xO;
        int distanciaV = yD - yO;
        if (distanciaH < 0) {
            distanciaH = -distanciaH;
        }
        if (distanciaV < 0) {
            distanciaV = -distanciaV;
        }
        return distanciaH <= limite && distanciaV <= limite;
    }

    protected List<CrazyPiece> getPecasNoCaminho(int xO, int yO, int xD, int yD, Map<Integer, CrazyPiece> pecas, int [][] tabuleiro) {
        List<CrazyPiece> pecasNoCaminho = new ArrayList<>();
        int direcaoHorizontal;
        int direcaoVertical;
        if (xO > xD) {
            direcaoHorizontal = -1;
        } else if (xO < xD){
            direcaoHorizontal = 1;
        }
        else {
            direcaoHorizontal = 0;
        }
        if (yO > yD) {
            direcaoVertical = -1;
        } else if (yO < yD){
            direcaoVertical = 1;
        }
        else {
            direcaoVertical = 0;
        }
        xO += direcaoHorizontal;
        yO += direcaoVertical;
        while (xO != xD || yO != yD) {
            if (Tabuleiro.existemCoordenadas(xO, yO, tabuleiro.length)) {
                if (tabuleiro[xO][yO] != 0) {
                    pecasNoCaminho.add(pecas.get(tabuleiro[xO][yO]));
                }
            }
            xO += direcaoHorizontal;
            yO += direcaoVertical;
        }
        return pecasNoCaminho;
    }

    protected List<CrazyPiece> getPecasNumRaio(int x, int y, int raio, int [][] tabuleiro, Map<Integer, CrazyPiece> pecas) {
        List<CrazyPiece> pecasAvolta = new ArrayList<>();
        for (int i = -raio; i <= raio; i++) {
            for (int j = -raio; j <= raio; j++) {
                if (i != 0 || j != 0) {
                    if (Tabuleiro.existemCoordenadas(x + i, y + j, tabuleiro.length)) {
                        if (tabuleiro[x + i][y + j] != 0) {
                            pecasAvolta.add(pecas.get(tabuleiro[x + i][y + j]));
                        }
                    }
                }
            }
        }
        return pecasAvolta;
    }

    public void contarJogadaInvalida() {
        this.jogadasInvalidas++;
    }

    public void contarJogadaValida() {
        this.jogadasValidas++;
    }

    public void captura(CrazyPiece peca) {
        this.capturas.add(peca);
    }

    public int getJogadasInvalidas() {
        return jogadasInvalidas;
    }

    public int getJogadasValidas() {
        return jogadasValidas;
    }

    public List<CrazyPiece> getCapturas() {
        return capturas;
    }

    public int getPontos() {
        int pontos = 0;
        for (CrazyPiece peca: capturas) {
            pontos += peca.getValorRelativo();
        }
        return pontos;
    }

    public double getRacio() {
        if ((this.jogadasInvalidas + this.jogadasValidas) > 0) {
            return (double) (this.jogadasInvalidas / (this.jogadasInvalidas + this.jogadasValidas));
        }
        return 0;
    }
}
