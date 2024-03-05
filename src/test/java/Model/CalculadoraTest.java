package Model;

import model.Calculadora;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculadoraTest {

    @Test
    void add() {
        Calculadora c = new Calculadora();
        assertEquals(4,c.add(2,2));
    }

    @Test
    void add2() {
        Calculadora c = new Calculadora();
        assertEquals(16,c.add(4,4));
    }
}