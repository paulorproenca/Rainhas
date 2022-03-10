package isep.dei;


public class ProblemaRainhas {
    private int DIM = 8; // dimensao do tabuleiro
    private boolean diag1[] = new boolean[DIM*2 - 1]; // diagonais de cima para a esquerda podem tomar valores de [0,14] col+lin=k
    private boolean diag2[] = new boolean[DIM*2 - 1]; // diagonais d cima para a direita podem tomar valores de [-7, 7] col-lin=k (é feita a alteração de escala para 0, 14
    private boolean linhas[] = new boolean[DIM]; // para guardar/verificar ocupação da linha
    private int posicao[] = new int [DIM]; //posição da rainha para cada coluna (solução)

    private void inicializa_tabuleiro(){
        // limpa o tabuleiro
        for (int i = 0; i < DIM; i++) {
            linhas[i]= true;
            posicao[i]= 0;
        }
        for (int i = 0; i < 2*DIM - 1; i++) {
            diag1[i] = diag2[i] = true;
        }
    }

    private void coloca_rainha(int lin, int col){
        posicao[col] = lin;
        linhas[lin] = false;
        diag1[col + lin] = false;
        diag2[col - lin + DIM - 1] = false;
    }

    private void retira_rainha(int lin, int col){
        linhas[lin] = true;
        diag1[col + lin] = true;
        diag2[col - lin + DIM - 1] = true;
    }

    private int encontra_solucoes(int col, int contador){
        for (int lin = 0; lin < DIM; lin++) {
            if (linhas[lin] && diag1[col + lin] && diag2[col-lin + DIM - 1]){
                coloca_rainha(lin, col);
                if (col < DIM - 1){
                    contador = encontra_solucoes(col +1, contador);
                }
                else {
                    contador++;
                    print_tabuleiro(contador);
                }
                retira_rainha(lin, col);
            }
        }
        return contador;
    }

    private void print_tabuleiro(int numero){
        System.out.println("\nSolucao numero " + numero);
        System.out.println();
        for (int lin = 0; lin < DIM; lin++) {
            for (int col = 0; col < DIM; col++) {
                if (posicao[col] == lin){
                    System.out.print("|R");
                }
                else{
                    System.out.print("|_");
                }
            }
            System.out.println("|");
        }
        System.out.println();
    }

    public void solve(){
        inicializa_tabuleiro();
        System.out.println(encontra_solucoes(0, 0) + " solucoes encontradas");
    }
}
