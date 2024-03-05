package Model;

import model.Passagem;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PassagemTest {

    private static Passagem passagem;

    @BeforeAll
    static void InitialClass(){
        passagem = new Passagem();
    }

    // Setters
    @Test
    void setters() {
        passagem.setId(10);
        passagem.setDuracao("100");
        passagem.setLocalPartida("São Paulo - Guarulhos");
        passagem.setLocalChegada("Rio de Janeiro");
        passagem.setPreco(100F);

        assertAll(() -> {
            assertEquals(10,passagem.getId());
            assertEquals("100",passagem.getDuracao());
            assertEquals("São Paulo - Guarulhos",passagem.getLocalPartida());
            assertEquals("Rio de Janeiro",passagem.getLocalChegada());
            assertEquals(100F,passagem.getPreco());

        });

    }

    @Test
    void settersNaoEsperados() {
        passagem.setId(-10);
        passagem.setDuracao("");
        passagem.setLocalPartida("");
        passagem.setLocalChegada("");
        passagem.setPreco(-100F);

        assertAll(() -> {
            assertEquals(-10,passagem.getId());
            assertEquals("",passagem.getDuracao());
            assertEquals("",passagem.getLocalPartida());
            assertEquals("",passagem.getLocalChegada());
            assertEquals(-100F,passagem.getPreco());

        });

    }

    // setValues

    @Test
    void SetValoresPorArray(){
        String[] array = new String[5];
        array[0] = "10";
        array[1] =  "São Paulo - Guarulhos";
        array[2] = "Rio de Janeiro";
        array[3] = "1h30m";
        array[4] = "100";

        passagem.SetValues(array);

        assertAll(() -> {
            assertEquals(10,passagem.getId());
            assertEquals("1h30m",passagem.getDuracao());
            assertEquals("São Paulo - Guarulhos",passagem.getLocalPartida());
            assertEquals("Rio de Janeiro",passagem.getLocalChegada());
            assertEquals(100F,passagem.getPreco());

        });
    }


}