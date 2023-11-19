/*
package com.senai.JOGGAR;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class TesteEvento {

    @Test
    public void testeTituloNotNull() {
        String titulo = "Futebol de Areia na Praia do Cagão";
        assertNotNull(titulo);
    }

    @Test
    public void testValidDate() {
        assertTrue(isValidDateFormat("25/12/2023"));
    }

    @Test
    public void testInvalidDate() {
        assertFalse(isValidDateFormat("32/13/2023"));
    }

    public boolean isValidDateFormat(String date) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            sdf.setLenient(false);
            sdf.parse(date);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }
}
*/
