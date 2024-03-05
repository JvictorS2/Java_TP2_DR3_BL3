package model;

import arquivo.ArquivoCsv;

public class Cliente {
	//Campos
    private String nome;
    private String cpf;
    private int id;
    private int idPassagem;

    private ArquivoCsv file;

    // Propriedades

    // Getters
    public String getCpf() {
        return cpf;
    }

    public int getIdPassagem() {
        return idPassagem;
    }

    public String getNome() {
        return nome;
    }

    public int getId() {
        return id;
    }

    // Setters
    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCpf(String cpf) {
        if(cpf.length() == 14)
        this.cpf = cpf;
    }

    public void setIdPassagem(int idPassagem) {
        this.idPassagem = idPassagem;
    }

    // Constructor
    public Cliente(ArquivoCsv file){
        this.file = file;
        this.id = 0;
        this.nome = "";
        this.cpf = "000.000.000-00";
        this.idPassagem = 0;

    }

    public Cliente(ArquivoCsv file,String nome,String cpf){
        this.file = file;
        this.nome = nome;
        this.cpf = cpf;
        idPassagem = 0;
        id = 0;
    }

    public Cliente(ArquivoCsv file,String nome,String cpf, int id, int idPassagem){
        this.file = file;
        this.nome = nome;
        this.cpf = cpf;
        this.idPassagem = idPassagem;
        this.id = id;
    }

    public Cliente(ArquivoCsv file,String[] array){
        this.file = file;
        this.id = Integer.parseInt(array[0]);
        this.nome = array[1];
        this.cpf = array[2];
        this.idPassagem = Integer.parseInt(array[3]);
    }

    // Métodos

    public void SetValues(String[] array){
        this.id = Integer.parseInt(array[0]);
        this.nome = array[1];
        this.cpf = array[2];
        this.idPassagem = Integer.parseInt(array[3]);
    }
    // Tested
    public String[] ConverteEstaClasseParaArray() {
        /*
            Convert um objeto card em um array.
        */

        String[] array = new String[4];

        // Se o id já estive sido informado irá atribuir manualmente este id ao array
        // caso o contrário será feito autamático.

        if(this.getId() != 0)
            array[0] = Integer.toString(this.getId());

        else {
            id = this.file.ObterAtualId() + 1;
            array[0] = Integer.toString(id);
        }

        array[1] = this.getNome();
        array[2] = this.getCpf();
        array[3] = Integer.toString(this.getIdPassagem());

        return array;
    }
    // Tested
    public String ConverteClienteParaString(){

        String[] clienteArray = ConverteEstaClasseParaArray();
        return this.file.ConverteArrayParaString(clienteArray);
    }

    @Override
    public String toString() {
        return "Nome: " + nome + " - Cpf: " + cpf + " - Id: " + id + " - idPassagem: " + idPassagem + " - File: " + file.getName();
    }
}
