package uniandes.dpoo.hamburguesas.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import uniandes.dpoo.hamburguesas.mundo.ProductoMenu;

public class ProductoMenuTest {
    private ProductoMenu producto;
    private static final String NOMBRE = "casera";
    private static final int PRECIO = 23000;
    @BeforeEach
    void setUp() {
        producto = new ProductoMenu(NOMBRE, PRECIO);
    }
    @AfterEach
    void tearDown() {
        producto = null;
    }
    @Test
    void testGetNombre() {
        assertEquals(NOMBRE, producto.getNombre(), "El nombre del producto no coincide");
    }
    @Test
    void testGetPrecio() {
        assertEquals(PRECIO, producto.getPrecio(), "El precio del producto no coincide");
    }
    @Test
    void testGenerarTextoFactura() {
        String textoEsperado = NOMBRE + "\n" + "            " + PRECIO + "\n";
        assertEquals(textoEsperado, producto.generarTextoFactura(), "El texto de la factura no es lo que se esperaba");
    }
}
