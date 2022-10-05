import java.util.Scanner;


public class JogoDaVelha {
    static int rodada = 0;
    static int pontosPrimeiroJogador = 0;
    static int pontosSegundoJogador = 0;
    static String nomePrimeiroJogador;
    static String nomeSegundoJogador;
    static boolean vencedor = false;
    static boolean empate = false;
    static Scanner input = new Scanner(System.in);
    static char[][] tabuleiro = {
            {'1', '|', '2', '|', '3'},
            {'4', '|', '5', '|', '6'},
            {'7', '|', '8', '|', '9'}};

    public static void main(String[] args) {

        menuJogo();
        System.out.println("Novo jogo iniciado:");
        System.out.println();
        iniciarJogo();
        input.close();
    }

    public static void iniciarJogo() {
        imprimeTabuleiro(tabuleiro);
        Scanner jogada = new Scanner(System.in);

        while (!vencedor || !empate) {

            System.out.println(nomePrimeiroJogador + ", escolha e digite o número de uma posição vazia:");
            char posicaoJogador1 = jogada.next().charAt(0);

            posicaoValidaJogador1(posicaoJogador1);

            String jogadorChar = "XXX";
            rodada++;

            verificarGanhador(tabuleiro, jogadorChar, nomePrimeiroJogador);
            if (vencedor) {
                pontosPrimeiroJogador++;
                getPlacar(nomePrimeiroJogador, pontosPrimeiroJogador, nomeSegundoJogador, pontosSegundoJogador);
                break;
            }

            if (empate) break;

            System.out.println(nomeSegundoJogador + ", escolha e digite o número de uma posição vazia:");
            char posicaoJogador2 = jogada.next().charAt(0);

            posicaoValidaJogador2(posicaoJogador2);

            jogadorChar = "OOO";
            rodada++;

            verificarGanhador(tabuleiro, jogadorChar, nomeSegundoJogador);
            if (vencedor) {
                pontosSegundoJogador++;
                getPlacar(nomeSegundoJogador, pontosSegundoJogador, nomePrimeiroJogador, pontosPrimeiroJogador);
                break;
            }
            if (empate) break;
        }
    }


    public static void menuJogo() {
        System.out.println("Bem vindo ao Jogo da Velha! Gostaria de iniciar uma partida?");
        System.out.println("| 1 - SIM   | 2 - NÃO  |");

        switch (input.next().charAt(0)) {
            case '1':
                System.out.println("Insira os dados dos jogadores: ");
                obterInformacaoJogador();
                break;
            case '2':
                encerrarJogo();
                break;
            default:
                System.out.println("Opção inválida! Digite um número válido.");
                main(null);
                break;
        }

    }

    public static void encerrarJogo() {
        System.out.println("Ah, que pena! Até a próxima!");
        System. exit(1);
    }

    public static void obterInformacaoJogador() {


        System.out.println("Qual o nome do primeiro jogador? ");
        nomePrimeiroJogador = input.next();
        System.out.println(nomePrimeiroJogador + ", você será o X !");

        System.out.println("Qual o nome do segundo jogador? ");
        nomeSegundoJogador = input.next();
        System.out.println(nomeSegundoJogador + ", você será o O !");

    }

    public static void imprimeTabuleiro(char[][] tabuleiro) {

        for (int i = 0; i < tabuleiro.length; i++) {
            for (int j = 0; j < tabuleiro[i].length; j++) {
                System.out.print(tabuleiro[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void marcaPosicao(char[][] tabuleiro, char posicao, String jogador) {
        //Impressão da jogada no tabuleiro
        char marca = ' ';
        if (jogador.equals("jogador 1")) {
            marca = 'X';
        } else if (jogador.equals("jogador 2")) {
            marca = 'O';
        }

        switch (posicao) {
            case '1':
                tabuleiro[0][0] = marca;
                break;
            case '2':
                tabuleiro[0][2] = marca;
                break;
            case '3':
                tabuleiro[0][4] = marca;
                break;
            case '4':
                tabuleiro[1][0] = marca;
                break;
            case '5':
                tabuleiro[1][2] = marca;
                break;
            case '6':
                tabuleiro[1][4] = marca;
                break;
            case '7':
                tabuleiro[2][0] = marca;
                break;
            case '8':
                tabuleiro[2][2] = marca;
                break;
            case '9':
                tabuleiro[2][4] = marca;
                break;

        }

        imprimeTabuleiro(tabuleiro);
    }

    public static void posicaoValidaJogador1(char posicaoJogador1) {
        while ((posicaoJogador1 == '1' && (tabuleiro[0][0] == 'X' || tabuleiro[0][0] == 'O'))
                || (posicaoJogador1 == '2' && (tabuleiro[0][2] == 'X' || tabuleiro[0][2] == 'O'))
                || (posicaoJogador1 == '3' && (tabuleiro[0][4] == 'X' || tabuleiro[0][4] == 'O'))
                || (posicaoJogador1 == '4' && (tabuleiro[1][0] == 'X' || tabuleiro[1][0] == 'O'))
                || (posicaoJogador1 == '5' && (tabuleiro[1][2] == 'X' || tabuleiro[1][2] == 'O'))
                || (posicaoJogador1 == '6' && (tabuleiro[1][4] == 'X' || tabuleiro[1][4] == 'O'))
                || (posicaoJogador1 == '7' && (tabuleiro[2][0] == 'X' || tabuleiro[2][0] == 'O'))
                || (posicaoJogador1 == '8' && (tabuleiro[2][2] == 'X' || tabuleiro[2][2] == 'O'))
                || (posicaoJogador1 == '9' && (tabuleiro[2][4] == 'X' || tabuleiro[2][4] == 'O'))
                || posicaoJogador1 != '1' && posicaoJogador1 != '2' && posicaoJogador1 != '3'
                && posicaoJogador1 != '4' && posicaoJogador1 != '5' && posicaoJogador1 != '6'
                && posicaoJogador1 != '7' && posicaoJogador1 != '8' && posicaoJogador1 != '9') {
            System.out.println("Jogada inválda, escolha outra posição: ");
            Scanner jogada = new Scanner(System.in);
            posicaoJogador1 = jogada.next().charAt(0);

        }
        marcaPosicao(tabuleiro, posicaoJogador1, "jogador 1");
    }

    public static void posicaoValidaJogador2(char posicaoJogador2) {
        while ((posicaoJogador2 == '1' && (tabuleiro[0][0] == 'X' || tabuleiro[0][0] == 'O'))
                || (posicaoJogador2 == '2' && (tabuleiro[0][2] == 'X' || tabuleiro[0][2] == 'O'))
                || (posicaoJogador2 == '3' && (tabuleiro[0][4] == 'X' || tabuleiro[0][4] == 'O'))
                || (posicaoJogador2 == '4' && (tabuleiro[1][0] == 'X' || tabuleiro[1][0] == 'O'))
                || (posicaoJogador2 == '5' && (tabuleiro[1][2] == 'X' || tabuleiro[1][2] == 'O'))
                || (posicaoJogador2 == '6' && (tabuleiro[1][4] == 'X' || tabuleiro[1][4] == 'O'))
                || (posicaoJogador2 == '7' && (tabuleiro[2][0] == 'X' || tabuleiro[2][0] == 'O'))
                || (posicaoJogador2 == '8' && (tabuleiro[2][2] == 'X' || tabuleiro[2][2] == 'O'))
                || (posicaoJogador2 == '9' && (tabuleiro[2][4] == 'X' || tabuleiro[2][4] == 'O'))
                || posicaoJogador2 != '1' && posicaoJogador2 != '2' && posicaoJogador2 != '3'
                && posicaoJogador2 != '4' && posicaoJogador2 != '5' && posicaoJogador2 != '6'
                && posicaoJogador2 != '7' && posicaoJogador2 != '8' && posicaoJogador2 != '9') {
            System.out.println("Jogada inválda, escolha outra posição: ");
            Scanner jogada = new Scanner(System.in);
            posicaoJogador2 = jogada.next().charAt(0);

        }
        marcaPosicao(tabuleiro, posicaoJogador2, "jogador 2");
    }

    public static void verificarGanhador(char[][] tabuleiro, String jogadorChar, String nomeJogador) {

        String linha1 = String.valueOf((tabuleiro[0][0])) + String.valueOf((tabuleiro[0][2])) + String.valueOf((tabuleiro[0][4]));
        String linha2 = String.valueOf((tabuleiro[1][0])) + String.valueOf((tabuleiro[1][2])) + String.valueOf((tabuleiro[1][4]));
        String linha3 = String.valueOf((tabuleiro[2][0])) + String.valueOf((tabuleiro[2][2])) + String.valueOf((tabuleiro[2][4]));

        String coluna1 = String.valueOf((tabuleiro[0][0])) + String.valueOf((tabuleiro[1][0])) + String.valueOf((tabuleiro[2][0]));
        String coluna2 = String.valueOf((tabuleiro[0][2])) + String.valueOf((tabuleiro[1][2])) + String.valueOf((tabuleiro[2][2]));
        String coluna3 = String.valueOf((tabuleiro[0][4])) + String.valueOf((tabuleiro[1][4])) + String.valueOf((tabuleiro[2][4]));

        String diagonal1 = String.valueOf((tabuleiro[0][0])) + String.valueOf((tabuleiro[1][2])) + String.valueOf((tabuleiro[2][4]));
        String diagonal2 = String.valueOf((tabuleiro[0][4])) + String.valueOf((tabuleiro[1][2])) + String.valueOf((tabuleiro[2][0]));


        if (linha1.equals(jogadorChar) ||
                linha2.equals(jogadorChar) ||
                linha3.equals(jogadorChar) ||
                coluna1.equals(jogadorChar) ||
                coluna2.equals(jogadorChar) ||
                coluna3.equals(jogadorChar) ||
                diagonal1.equals(jogadorChar) ||
                diagonal2.equals(jogadorChar)) {
            vencedor = true;

            System.out.println(nomeJogador + ", você venceu!");


        } else if (rodada == 9) {
            empate = true;
            System.out.println("VELHA! Deseja jogar outra partida?");
            System.out.println("| 1 - SIM   | 2 - NÃO  |");
            switch (input.next().charAt(0)) {
                case '1':
                    resetaTabuleiro();
                    iniciarJogo();
                    break;
                case '2':
                    encerrarJogo();
                    break;
                default:
                    System.out.println("Opção inválida! Até a próxima!.");
                    System.exit(1);
                    break;
            }
        }


    }

    public static void getPlacar(String nomeJogadorVencedor, int pontosVencedor, String nomeOutroJogador, int pontosOutroJogador) {
        System.out.println("Placar:");
        System.out.println(nomeJogadorVencedor + ": " + pontosVencedor + " X " + nomeOutroJogador + ": " + pontosOutroJogador);
        System.out.println("Revanche?");
        System.out.println("| 1 - SIM   | 2 - NÃO  |");
        switch (input.next().charAt(0)) {
            case '1':

                resetaTabuleiro();
                iniciarJogo();
                break;
            case '2':
                encerrarJogo();
                break;
            default:
                System.out.println("Opção inválida! Até a próxima!.");
                System.exit(1);
                break;
        }
    }

    public static void resetaTabuleiro() {
        rodada = 0;
        vencedor = false;
        empate = false;
        tabuleiro = new char[][]{
                {'1', '|', '2', '|', '3'},
                {'4', '|', '5', '|', '6'},
                {'7', '|', '8', '|', '9'}};
    }
}