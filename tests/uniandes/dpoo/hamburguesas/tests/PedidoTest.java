package uniandes.dpoo.hamburguesas.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import uniandes.dpoo.hamburguesas.mundo.Pedido;
import uniandes.dpoo.hamburguesas.mundo.ProductoMenu;

public class PedidoTest {
    private Pedido pedido;
    private ProductoMenu producto1;
    private ProductoMenu producto2;
    
    private static final String NOMBRE_CLIENTE = "Valeria Martínez";
    private static final String DIRECCION_CLIENTE = "Calle 160 #73-60";
    private static final String NOMBRE_PRODUCTO1 = "todoterreno";
    private static final int PRECIO_PRODUCTO1 = 25000;
    private static final String NOMBRE_PRODUCTO2 = "gaseosa";
    private static final int PRECIO_PRODUCTO2 = 5000;
    private static final double IVA = 0.19;

    @BeforeEach
    void setUp() {
        pedido = new Pedido(NOMBRE_CLIENTE, DIRECCION_CLIENTE);
        producto1 = new ProductoMenu(NOMBRE_PRODUCTO1, PRECIO_PRODUCTO1);
        producto2 = new ProductoMenu(NOMBRE_PRODUCTO2, PRECIO_PRODUCTO2);
        pedido.agregarProducto(producto1);
        pedido.agregarProducto(producto2);
    }

    @AfterEach
    void tearDown() {
        pedido = null;
        producto1 = null;
        producto2 = null;
    }
    @Test
    void testGetNombreCliente() {
        assertEquals(NOMBRE_CLIENTE, pedido.getNombreCliente(), "El nombre del cliente no coincide");
    }

    @Test
    void testAgregarProducto() {
        int precio= PRECIO_PRODUCTO1+PRECIO_PRODUCTO2;
        int precioIVA = (int)(precio*IVA);
        int precioTotal = precio+ precioIVA;
        assertEquals(precioTotal, pedido.getPrecioTotalPedido(), "El precio total del pedido no es correcto");
    }

    @Test
    void testGenerarTextoFactura() {
        String texto = pedido.generarTextoFactura();
        assertTrue(texto.contains("Cliente: " + NOMBRE_CLIENTE), "La factura debe tener el nombre del cliente");
        assertTrue(texto.contains("Dirección: " + DIRECCION_CLIENTE), "La factura debe tener la dirección");
        assertTrue(texto.contains(NOMBRE_PRODUCTO1), "La factura debe tener el nombre del producto 1");
        assertTrue(texto.contains(NOMBRE_PRODUCTO2), "La factura debe tener el nombre del producto 2");
        int precio= PRECIO_PRODUCTO1 + PRECIO_PRODUCTO2;
        int precioIVA = (int)(precio* IVA);
        int precioTotal = precio+ precioIVA;
        assertTrue(texto.contains("Precio Neto:  " + precio), "La factura debe contener el precio neto");
        assertTrue(texto.contains("IVA:          "+ precioIVA), "La factura debe tener el IVA aplicado");
        assertTrue(texto.contains("Precio Total: " + precioTotal), "La factura debe tener el precio total");
    }

    @Test
    void testGuardarFactura() throws FileNotFoundException, IOException {
        File archivo = File.createTempFile("factura_test", ".txt");
        archivo.deleteOnExit();
        pedido.guardarFactura(archivo);
        String contenido = new String(Files.readAllBytes(Paths.get(archivo.getAbsolutePath())));
        assertEquals(pedido.generarTextoFactura(), contenido, "El contenido de la factura no coincide con el texto generado");
    }
}
