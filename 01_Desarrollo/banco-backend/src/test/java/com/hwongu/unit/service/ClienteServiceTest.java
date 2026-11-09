/**
 * Autor: Henry Wong
 * GitHub: hwongu
 *
 * Esta clase contiene pruebas unitarias para el servicio ClienteServiceImpl, que maneja
 * la lógica de negocio para la entidad Cliente. Se utiliza Mockito para simular el comportamiento
 * del repositorio IClienteRepo y verificar las operaciones CRUD y búsquedas personalizadas.
 */
package com.hwongu.unit.service;

import com.hwongu.model.Cliente;
import com.hwongu.repo.IClienteRepo;
import com.hwongu.service.impl.ClienteServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class ClienteServiceTest {

    private ClienteServiceImpl service;

    @Mock
    private IClienteRepo repo;

    private Cliente CLIENTE_1;
    private Cliente CLIENTE_2;
    private Cliente CLIENTE_3;

    /**
     * Metodo de configuración inicial que se ejecuta antes de cada prueba.
     * Configura los objetos de prueba, simula respuestas del repositorio y asigna
     * la instancia del servicio a probar.
     */
    @BeforeEach
    public void init() {
        this.service = new ClienteServiceImpl(repo);

        CLIENTE_1 = new Cliente(1, "Carlos", "Pérez", "12345678", "carlos.perez@correo.com", LocalDate.of(2000, 5, 10), true);
        CLIENTE_2 = new Cliente(2, "Carla", "Gómez", "87654321", "carla.gomez@correo.com", LocalDate.of(2001, 8, 20), true);
        CLIENTE_3 = new Cliente(3, "Carmen", "Ruiz", "11223344", "carmen.ruiz@correo.com", LocalDate.of(1999, 3, 15), true);

        List<Cliente> clientes = List.of(CLIENTE_1, CLIENTE_2, CLIENTE_3);
        Mockito.when(repo.findAll()).thenReturn(clientes);
        Mockito.when(repo.findById(any())).thenReturn(Optional.of(CLIENTE_1));
        Mockito.when(repo.save(any())).thenReturn(CLIENTE_1);
        Mockito.when(repo.findByNameLikeOrderByName(any())).thenReturn(clientes);
        Mockito.when(repo.findClienteByNameEnabled(any(), any(Boolean.class))).thenReturn(clientes);
    }

    /**
     * Prueba unitaria para el metodo save, que verifica que el cliente guardado
     * no sea nulo.
     */
    @Test
    void save() throws Exception {
        Cliente response = service.save(CLIENTE_1);
        assertNotNull(response);
    }

    /**
     * Prueba unitaria para el metodo update, que verifica que el cliente actualizado
     * no sea nulo.
     */
    @Test
    void update() throws Exception {
        Cliente response = service.update(CLIENTE_1, 1);
        assertNotNull(response);
    }

    /**
     * Prueba unitaria para el metodo delete, que verifica que el metodo deleteById
     * del repositorio se haya invocado una vez.
     */
    @Test
    void delete() throws Exception {
        service.delete(1);
        verify(repo, times(1)).deleteById(1);
    }

    /**
     * Prueba unitaria para el metodo readAll, que verifica que la cantidad de clientes
     * leídos sea igual a 3.
     */
    @Test
    void readAllTest() throws Exception {
        List<Cliente> response = service.readAll();
        assertEquals(3, response.size());
    }

    /**
     * Prueba unitaria para el metodo readById, que verifica que el cliente leído
     * no sea nulo.
     */
    @Test
    void readByIdTest() throws Exception {
        final int id = 1;
        Cliente response = service.readById(id);
        assertNotNull(response);
    }

    /**
     * Prueba unitaria para el metodo findByNameLike, que verifica que la búsqueda
     * por nombre retorne resultados.
     */
    @Test
    void findByNameLikeTest() {
        List<Cliente> response = service.findByNameLike("Car");
        assertEquals(3, response.size());
    }

    /**
     * Prueba unitaria para el metodo findClienteByNameIsEnabled, que verifica que
     * la búsqueda de clientes habilitados retorne resultados.
     */
    @Test
    void findClienteByNameIsEnabledTest() {
        List<Cliente> response = service.findClienteByNameIsEnabled("Car");
        assertEquals(3, response.size());
    }
}

