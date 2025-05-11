package uniandes.dpoo.hamburguesas.tests;
import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import uniandes.dpoo.hamburguesas.mundo.*;

public class ProductoAjustadoTest {
    private ProductoMenu productoBase;
    private ProductoAjustado productoAjustado;
    private Ingrediente ingredienteAdicional;
    private Ingrediente ingredienteEliminado;
    private static final String NOMBRE_PRODUCTO = "casera";
    private static final int PRECIO_PRODUCTO = 23000;
    private static final String INGREDIENTE_ADICIONAL = "piña";
    private static final int PRECIO_INGREDIENTE_ADICIONAL = 2500;
    private static final String INGREDIENTE_ELIMINADO = "cebolla";
    private static final int PRECIO_INGREDIENTE_ELIMINADO = 1000;
    
    @BeforeEach
    void setUp() {
        productoBase = new ProductoMenu(NOMBRE_PRODUCTO, PRECIO_PRODUCTO);
        productoAjustado = new ProductoAjustado(productoBase);
        ingredienteAdicional = new Ingrediente(INGREDIENTE_ADICIONAL, PRECIO_INGREDIENTE_ADICIONAL);
        ingredienteEliminado = new Ingrediente(INGREDIENTE_ELIMINADO, PRECIO_INGREDIENTE_ELIMINADO);
    }
    @AfterEach
    void tearDown() {
        productoBase = null;
        productoAjustado = null;
        ingredienteAdicional = null;
        ingredienteEliminado = null;
    }
    @Test
    public void testPrecioConUnIngredienteAgregado() {
        ProductoMenu base = new ProductoMenu(NOMBRE_PRODUCTO, PRECIO_PRODUCTO);
        Ingrediente piña = new Ingrediente(INGREDIENTE_ADICIONAL, PRECIO_INGREDIENTE_ADICIONAL);
        ProductoAjustado producto = new ProductoAjustado(base);
        producto.agregarIngrediente(piña);
        assertEquals(PRECIO_PRODUCTO+PRECIO_INGREDIENTE_ADICIONAL, producto.getPrecio());
    }
    @Test
    public void testTextoFacturaIncluyeIngrediente() {
        ProductoMenu base = new ProductoMenu(NOMBRE_PRODUCTO, PRECIO_PRODUCTO);
        Ingrediente piña = new Ingrediente(INGREDIENTE_ADICIONAL, PRECIO_INGREDIENTE_ADICIONAL);
        ProductoAjustado ajustado = new ProductoAjustado(base);
        ajustado.agregarIngrediente(piña);
        String factura = ajustado.generarTextoFactura();
        assertTrue(factura.contains("piña"));
    }

    @Test
    public void testEliminarIngredienteNoAfectaPrecio() {
        ProductoMenu base = new ProductoMenu(NOMBRE_PRODUCTO, PRECIO_PRODUCTO);
        Ingrediente cebolla = new Ingrediente(INGREDIENTE_ELIMINADO,PRECIO_INGREDIENTE_ELIMINADO);
        ProductoAjustado ajustado = new ProductoAjustado(base);
        ajustado.quitarIngrediente(cebolla);
        assertEquals(PRECIO_PRODUCTO, ajustado.getPrecio());
    }
}

