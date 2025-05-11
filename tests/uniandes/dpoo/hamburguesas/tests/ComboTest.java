package uniandes.dpoo.hamburguesas.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import uniandes.dpoo.hamburguesas.mundo.Combo;
import uniandes.dpoo.hamburguesas.mundo.ProductoMenu;

public class ComboTest {
    private Combo combo;
    private ProductoMenu producto1;
    private ProductoMenu producto2;
    private ArrayList<ProductoMenu> productos;
    
    private static final String NOMBRE_COMBO = "combo todoterreno";
    private static final double DESCUENTO = 7/100;
    private static final String PRODUCTO1 = "todoterreno";
    private static final int PRECIO_PRODUCTO1 = 25000;
    private static final String PRODUCTO2 = "papas grandes";
    private static final int PRECIO_PRODUCTO2 = 6900;

    @BeforeEach
    void setUp() {
        producto1 = new ProductoMenu(PRODUCTO1, PRECIO_PRODUCTO1);
        producto2 = new ProductoMenu(PRODUCTO2, PRECIO_PRODUCTO2);
        productos = new ArrayList<>();
        productos.add(producto1);
        productos.add(producto2);
        combo = new Combo(NOMBRE_COMBO, DESCUENTO, productos);
    }

    @AfterEach
    void tearDown() {
        combo = null;
        producto1 = null;
        producto2 = null;
        productos = null;
    }

    @Test
    void testGetNombre() {
        assertEquals(NOMBRE_COMBO, combo.getNombre(), "El nombre del combo no coincide");
    }

    @Test
    void testGetPrecio() {
        int precioTotal = PRECIO_PRODUCTO1 + PRECIO_PRODUCTO2;
        int precioEsperado = (int)(precioTotal * (1-DESCUENTO));
        assertEquals(precioEsperado, combo.getPrecio(), "El precio del combo con descuento no es el esperado");
    }

    @Test
    void testGenerarTextoFactura() {
        String texto = combo.generarTextoFactura();
        assertTrue(texto.contains(NOMBRE_COMBO), "La factura debe contener el nombre del combo");
        assertTrue(texto.contains(String.valueOf(DESCUENTO)), "La factura debe mostrar el descuento aplicado");
        assertTrue(texto.contains(String.valueOf(combo.getPrecio())), "La factura debe incluir el precio final del combo");
    }
}