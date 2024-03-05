package model;

public class Passagem {
	 // Campos

    // identificador da classe
    private int id;

    // Local de partida
    private String localPartida;

    // Local de chegada
    private String localChegada;

    // Duração da viagem
    private String duracao;

    // Preço da passagem
    private Float Preco;

    // Constructor
    public Passagem(){
        this.id = 0;
        this.localChegada = "";
        this.localPartida = "";
        this.Preco = 1.00f;
    }

    // getters
    public int getId() {

        return id;
    }

    public String getDuracao() {

        return duracao;
    }

    public String getLocalPartida() {

        return localPartida;
    }

    public String getLocalChegada() {

        return localChegada;
    }

    public Float getPreco() {

        return Preco;
    }


    // Método
    public void SetValues(String[] array){
        // Set todas as informações de uma array na classe

        this.id = Integer.parseInt(array[0]);
        this.localPartida = array[1];
        this.localChegada = array[2];
        this.duracao = array[3];
        this.Preco = Float.parseFloat(array[4]);
    }

}
