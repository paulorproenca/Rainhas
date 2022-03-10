package isep.dei;

class ProblemaTorreHanoi {
    int NUM_DISCOS = 4;
    int NUM_TORRES = 3;
    int torres[][] = new int[NUM_DISCOS][NUM_TORRES];

    private void limpa_torres(){
        for (int lin = 0; lin < NUM_DISCOS; lin++) {
            torres[lin][0] = lin + 1;
            for (int col = 1; col < NUM_TORRES; col++) {
                torres[lin][col] = 0;
            }
        }
    }

    private void move_disco(int disco, int torre_origem, int torre_destino){
        for (int i = 0; i < NUM_DISCOS; i++) {
            if(torres[i][torre_origem] == disco){
                torres[i][torre_origem] = 0;
                break;
            }
        }
        for (int i = NUM_DISCOS - 1; i >= 0; i--) {
            if(torres[i][torre_destino] == 0){
                torres[i][torre_destino] = disco;
                break;
            }
        }
        mostra_torres();
    }

    private void mostra_torres(){
        for (int lin = 0; lin < NUM_DISCOS; lin++) {
            System.out.print("|");
            for (int col = 0; col < NUM_TORRES; col++) {
                if(torres[lin][col] == 0){
                    System.out.print(" |");
                }
                else {
                    System.out.print(torres[lin][col] + "|");
                }
            }
            System.out.println();
        }
        for (int col = 0; col < NUM_TORRES; col++) {
            System.out.print(" ^");
        }
        System.out.println();
    }

    private void encontra_solucao(int disco, int torre_origem, int torre_destino, int torre_auxiliar)
    {
        if (disco == 0)
        {
            return;
        }
        encontra_solucao(disco - 1, torre_origem, torre_auxiliar, torre_destino);
        System.out.println("Move disco "+ disco + " da torre " +
                torre_origem +" para a torre " + torre_destino );
        move_disco(disco, torre_origem, torre_destino);
        encontra_solucao(disco - 1, torre_auxiliar, torre_destino, torre_origem);
    }

    // Driver code
    public void  solve()
    {
        limpa_torres();
        mostra_torres();
        encontra_solucao(NUM_DISCOS, 0, 2, 1);
    }
}