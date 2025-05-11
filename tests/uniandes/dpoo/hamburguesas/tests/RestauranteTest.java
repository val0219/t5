package uniandes.dpoo.hamburguesas.tests;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import uniandes.dpoo.hamburguesas.excepciones.HamburguesaException;
import uniandes.dpoo.hamburguesas.mundo.*;

import java.io.File;
import java.io.IOException;

public class RestauranteTest {

    private Restaurante restaurante;
    private File archivoIngredientes;
    private File archivoMenu;
    private File archivoCombos;

    @Before
    public void setUp() throws IOException, HamburguesaException {
        restaurante = new Restaurante();
        archivoIngredientes = new File("data/ingredientes.txt");
        archivoMenu = new File("data/menu.txt");
        archivoCombos = new File("data/combos.txt");
        restaurante.cargarInformacionRestaurante(archivoIngredientes, archivoMenu, archivoCombos);
    }

    @Test
    public void testCargarMenu() {
        assertFalse("El menú base no debe estar vacío", restaurante.getMenuBase().isEmpty());
    }

    @Test
    public void testIniciarYObtenerPedidoEnCurso() throws Exception {
        restaurante.iniciarPedido("Fredy", "Calle 153");
        Pedido pedido = restaurante.getPedidoEnCurso();
        assertNotNull("Debe haber un pedido en curso", pedido);
        assertEquals("Fredy", pedido.getNombreCliente());
    }

    @Test
    public void testAgregarProductoAlPedido() throws Exception {
        restaurante.iniciarPedido("Carolina", "Calle 138");
        Producto producto = restaurante.getMenuBase().get(2);
        restaurante.agregarElementoAlPedido(producto.getNombre());
        Pedido pedido = restaurante.getPedidoEnCurso();
        assertEquals(1, pedido.getItemsPedido().size());
    }

    @Test
    public void testCerrarYGuardarPedido() throws Exception {
        restaurante.iniciarPedido("Antonio", "Calle 170");
        Producto producto = restaurante.getMenuBase().get(4);
        restaurante.agregarElementoAlPedido(producto.getNombre());
        restaurante.cerrarYGuardarPedido();
        assertNull("No debe haber pedido en curso después de cerrar", restaurante.getPedidoEnCurso());
    }
}