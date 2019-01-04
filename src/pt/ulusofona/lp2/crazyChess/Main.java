package pt.ulusofona.lp2.crazyChess;

public class Main {
    public static void main(String[] args) {

        TestGestorDeJogo testGestorDeJogo = new TestGestorDeJogo();
        testGestorDeJogo.test01GetTurno();
        testGestorDeJogo.test02QuemEstaAJogar();
        testGestorDeJogo.test03ContaRei();
        testGestorDeJogo.tes04ValidarJogada();
        testGestorDeJogo.test05NaoHouveCaptura();
        testGestorDeJogo.test06AdicionarCaptura();
        testGestorDeJogo.test07InvalidarJogada();
        testGestorDeJogo.tes08Undo();
        testGestorDeJogo.test09PossoTerminar();
        testGestorDeJogo.test10GetResultados();
        testGestorDeJogo.test11LoadEquipaAJogar();
        testGestorDeJogo.test12LoadJogadasValidas();
        testGestorDeJogo.test13LoadJogadasInvalidas();
        testGestorDeJogo.test14LoadCapturas();

        TestTabuleiro testTabuleiro = new TestTabuleiro();
        testTabuleiro.test01ExistemCoordenadas();
        testTabuleiro.test02GetTamanho();
        testTabuleiro.test03AcrescentaPeca();
        testTabuleiro.test04ColocarNoTabuleiro();
        testTabuleiro.test05GetPecas();
        testTabuleiro.test06GetPeca();
        testTabuleiro.test07ObterSugestoesJogada();
        testTabuleiro.test08ProcessaJogada();
        testTabuleiro.test09AtualizarAnterior();

        TestCrazyPiece testCrazyPiece = new TestCrazyPiece();
        testCrazyPiece.test01MoveHorizonal();
        testCrazyPiece.test02MoveVertical();
        testCrazyPiece.test03MoveDiagonal();
        testCrazyPiece.test04Move();
    }
}
