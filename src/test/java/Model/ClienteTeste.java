package Model;
import arquivo.ArquivoCsv;

import static org.junit.jupiter.api.Assertions.*;

import model.Cliente;
import org.junit.jupiter.api.Test;


class ClienteTeste {
    ArquivoCsv file = new ArquivoCsv("TP1_Java","Cliente","csv","src\\main\\java\\data\\cliente.csv");

    // ConverteEstaClasseParaArray()
    @Test
    void ConverteClasseVaziaEmArray() {
        var cliente = new Cliente(file);

        // id - nome - cpf - passagemid
        String[] array = new String[4];

        int id = file.ObterAtualId() + 1;
        array[0] = Integer.toString(id);

        array[1] = "";
        array[2] = "000.000.000-00";
        array[3] = "0";

        assertArrayEquals(array,cliente.ConverteEstaClasseParaArray());

    }

    @Test
    void ConverteClasseComValoresEmArray() {

        // id - nome - cpf - passagemid
        String[] array = new String[4];

        array[0] = "100";

        array[1] = "Victor";
        array[2] = "123.456.789-70";
        array[3] = "12";

        var cliente = new Cliente(file,array);

        assertArrayEquals(array,cliente.ConverteEstaClasseParaArray());

    }

    // ConverteClienteParaString()
    @Test
    void ConverteClienteVazioEmString(){
        var cliente = new Cliente(file);
        String id = Integer.toString(file.ObterAtualId() + 1);

        String test = id + ",,000.000.000-00,0";

        assertEquals(test,cliente.ConverteClienteParaString());
    }

    @Test
    void ConverteClienteComValorEmString(){
        // id - nome - cpf - passagemid

        var cliente = new Cliente(file,"Santos","197.765.311-89");
        String id = Integer.toString(file.ObterAtualId() + 1);
        String test = id + ",Santos,197.765.311-89,0";

        assertEquals(test,cliente.ConverteClienteParaString());
    }

    // SetValues()
    @Test
    void SetValuesClienteVazio(){
        // id - nome - cpf - passagemid
        String[] array = new String[4];
        var cliente = new Cliente(file);

        array[0] = "137";

        array[1] = "Santos";
        array[2] = "197.765.311-89";
        array[3] = "98";

        cliente.SetValues(array);

        String test = "137,Santos,197.765.311-89,98";

        assertEquals(test,cliente.ConverteClienteParaString());
    }

    @Test
    void SetValuesClientePreenchido(){
        // id - nome - cpf - passagemid
        var cliente = new Cliente(file);
        cliente.setNome("Gilzon");
        cliente.setCpf("100.200.300.90");
        cliente.setIdPassagem(1);

        String[] array = new String[4];

        array[0] = "139";

        array[1] = "Elberth";
        array[2] = "197.765.311-89";
        array[3] = "12";

        cliente.SetValues(array);

        String test = "139,Elberth,197.765.311-89,12";

        assertEquals(test,cliente.ConverteClienteParaString());
    }

    // ToString
    @Test
    void ToString(){
        // id - nome - cpf - passagemid

        var cliente = new Cliente(file,"Santos","123.456.789-00",7,9);
        String test = "Nome: Santos - Cpf: 123.456.789-00 - Id: 7 - idPassagem: 9 - File: Cliente";

        assertEquals(test,cliente.toString());
    }

    // Constructor
    @Test
    void ConstructorNomeCpf(){
        // id - nome - cpf - passagemid

        var cliente = new Cliente(file,"Nicolas","193.456.789-60");
        String test = "Nome: Nicolas - Cpf: 193.456.789-60 - Id: 0 - idPassagem: 0 - File: Cliente";

        assertEquals(test,cliente.toString());
    }
}
