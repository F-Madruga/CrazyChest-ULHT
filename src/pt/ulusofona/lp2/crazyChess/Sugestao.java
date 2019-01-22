package pt.ulusofona.lp2.crazyChess;

public class Sugestao implements  Comparable<Sugestao> {
    private int x;
    private int y;
    private int pontos;

    public Sugestao (int x, int y) {
        this.x = x;
        this.y = y;
        this.pontos = 0;
    }

    public void setPontos(int pontos) {
        this.pontos = pontos;
    }

    @Override
    public int compareTo(Sugestao outraSugestão) {
        return outraSugestão.pontos - this.pontos;
    }

    @Override
    public String toString() {
        if (this.pontos == 1000) {
            return this.x + ", " + this.y + ", " + "(infinito)";
        }
        return this.x + ", " + this.y + ", " + this.pontos;
    }

    @Override
    public boolean equals(Object obj) {
        Sugestao sugestao = (Sugestao)obj;
        return this.x == sugestao.x && this.y == sugestao.y && this.pontos == sugestao.pontos;
    }
}
