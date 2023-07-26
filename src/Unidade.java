import java.util.ArrayList;

public abstract class Unidade {
    private double vida;
    private double ataque;
    private double defesa;
    private Posicao posicao;
    private String cor;
    private String simbolo;
    ArrayList<Unidade> listaUnidades = new ArrayList<>();
     ArrayList<Posicao> possiveisMovimentos = new ArrayList<>();

    public Unidade(double ataque, double defesa, double vida, String cor, Posicao posicao, String simbolo) {
        this.ataque = ataque;
        this.defesa = defesa;
        this.vida = vida;
        this.cor = cor;
        this.posicao = posicao;
        this.simbolo = simbolo;
        listaUnidades.add(this);
    }



    public void movimentar(Posicao posicaoDoMovimento, Posicao posicaoDeOrigem, Tabuleiro tabuleiro) {
        posicaoDeOrigem.setUnidade(null);
        posicaoDoMovimento.setUnidade(this);
        setPosicao(posicaoDoMovimento);
        atualizaTabuleiro(tabuleiro,posicaoDeOrigem);

    }

    public abstract boolean atacar(Tabuleiro tabuleiro, Jogador jogador, Posicao posicao);


    public String getCor() {
        return cor;
    }

    public double getVida() {
        return vida;
    }

    public double getDefesa() {
        return defesa;
    }

    public void setVida(double vida) {
        this.vida = vida;
    }

    public Posicao getPosicao() {
        return posicao;
    }

    public void setDefesa(double defesa) {
        this.defesa = defesa;
    }

    public double getAtaque() {
        return ataque;
    }


    @Override
    public String toString() {
        return "Unidade" + this.getClass();

    }

    private void possiveisMovimentos(Tabuleiro tabuleiro) {

        possiveisMovimentos.removeAll(possiveisMovimentos);
        int posicaoNoTabuleiro = tabuleiro.getListaDePosicaoes().indexOf(this.posicao);

        ArrayList<Posicao> posicaoTabuleiro = tabuleiro.getListaDePosicaoes();


        if (posicaoTabuleiro.get(posicaoNoTabuleiro).getUnidade().getCor().equals("Azul")) {
            if (posicaoTabuleiro.get(posicaoNoTabuleiro + 5).getUnidade() == null) {
                possiveisMovimentos.add(tabuleiro.getListaDePosicaoes().get(posicaoNoTabuleiro + 5));
            }
            if (!verificaExtremidade(posicaoNoTabuleiro) &&
                    posicaoTabuleiro.get(posicaoNoTabuleiro + 4).getUnidade() == null) {
                possiveisMovimentos.add(tabuleiro.getListaDePosicaoes().get(posicaoNoTabuleiro + 4));
            }
            if (!verificaExtremidade(posicaoNoTabuleiro+1) &&
                    posicaoTabuleiro.get(posicaoNoTabuleiro + 6).getUnidade() == null) {
                possiveisMovimentos.add(tabuleiro.getListaDePosicaoes().get(posicaoNoTabuleiro + 6));
            }
        }

        if (posicaoTabuleiro.get(posicaoNoTabuleiro).getUnidade().getCor().equals("Vermelho")) {
            if (posicaoTabuleiro.get(posicaoNoTabuleiro - 5).getUnidade() == null) {
                possiveisMovimentos.add(tabuleiro.getListaDePosicaoes().get(posicaoNoTabuleiro - 5));
            }
            if (!verificaExtremidade(posicaoNoTabuleiro+1) &&
                    posicaoTabuleiro.get(posicaoNoTabuleiro - 4).getUnidade() == null) {
                possiveisMovimentos.add(tabuleiro.getListaDePosicaoes().get(posicaoNoTabuleiro - 4));
            }
            if ( !verificaExtremidade(posicaoNoTabuleiro) &&
                    posicaoTabuleiro.get(posicaoNoTabuleiro - 6).getUnidade() == null) {
                possiveisMovimentos.add(tabuleiro.getListaDePosicaoes().get(posicaoNoTabuleiro - 6));
            }
        }

        if(!verificaExtremidade(posicaoNoTabuleiro+1) &&
                posicaoTabuleiro.get(posicaoNoTabuleiro + 1).getUnidade() == null){

            possiveisMovimentos.add(tabuleiro.getListaDePosicaoes().get(posicaoNoTabuleiro+1));

        }
        if( !verificaExtremidade(posicaoNoTabuleiro) &&
                posicaoTabuleiro.get(posicaoNoTabuleiro -1).getUnidade() == null){

            possiveisMovimentos.add(tabuleiro.getListaDePosicaoes().get(posicaoNoTabuleiro-1));

        }

    }

    public void setPosicao(Posicao posicao) {
        this.posicao = posicao;

    }

    public void atualizaTabuleiro(Tabuleiro tabuleiro, Posicao posicaoDeOrigem) {

        tabuleiro.atualizaTabuleiro(tabuleiro, this.getPosicao(), posicaoDeOrigem);
    }

    public String getSimbolo() {
        return simbolo;
    }

     public ArrayList<String> toStringPossiveisMovimentos(Tabuleiro tabuleiro){
        this.possiveisMovimentos(tabuleiro);
        ArrayList<String> listaDepossiveisMovimentos = new ArrayList<>();

        for (int i = 0; i <possiveisMovimentos.size() ; i++) {
            int indiceParaEscolha= possiveisMovimentos.indexOf(this.getPosicao());
            listaDepossiveisMovimentos.add(""+indiceParaEscolha);
        }



        return listaDepossiveisMovimentos;
    }

    private boolean verificaExtremidade( int index) {
        return (index) % 5 == 0;
    }

}
