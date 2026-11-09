/**
 * Autor: Henry Wong
 * GitHub: hwongu
 *
 * Esta clase define los pasos de prueba de integracion para la entidad Cliente usando Cucumber.
 * Cada metodo representa un paso de una prueba BDD para verificar operaciones CRUD.
 */
package com.hwongu.integration.service.steps;

import com.hwongu.model.Cliente;
import com.hwongu.repo.IClienteRepo;
import com.hwongu.service.impl.ClienteServiceImpl;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class ClienteServiceSteps {

    @Autowired
    private ClienteServiceImpl clienteService;

    @Autowired
    private IClienteRepo clienteRepo;

    private Cliente cliente;

    /**
     * Crea un nuevo cliente en memoria con el nombre especificado en el paso.
     *
     * @param name nombre del cliente.
     */
    @Given("a new cliente with name {string}")
    public void a_new_cliente_with_name(String name) {
        int suffix = ThreadLocalRandom.current().nextInt(10000000, 99999999);
        cliente = new Cliente();
        cliente.setName(name);
        cliente.setLastName("Test");
        cliente.setDni(String.valueOf(suffix));
        cliente.setEmail("cliente" + suffix + "@mail.com");
        cliente.setBirthDate(LocalDate.of(2000, 1, 1));
        cliente.setEnabled(true);
    }

    /**
     * Guarda el cliente previamente creado en base de datos usando el servicio.
     */
    @When("I save the cliente")
    public void i_save_the_cliente() {
        try {
            cliente = clienteService.save(cliente);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Verifica que el cliente fue guardado con el nombre esperado.
     *
     * @param expectedName nombre esperado.
     */
    @Then("the cliente should be saved with name {string}")
    public void the_cliente_should_be_saved_with_name(String expectedName) {
        if (cliente.getIdCliente() == null) {
            i_save_the_cliente();
        }
        List<Cliente> clientes = clienteRepo.findByNameLikeOrderByName("%" + expectedName + "%");
        assertFalse(clientes.isEmpty());
        assertTrue(clientes.stream().anyMatch(a -> expectedName.equals(a.getName())));
    }

    /**
     * Actualiza el nombre del cliente con un nuevo valor.
     *
     * @param updatedName nuevo nombre.
     */
    @When("I update the cliente name to {string}")
    public void i_update_the_cliente_name_to(String updatedName) {
        cliente.setName(updatedName);
        try {
            cliente = clienteService.update(cliente, cliente.getIdCliente());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Verifica que el cliente fue actualizado con el nuevo nombre.
     *
     * @param updatedName nombre actualizado esperado.
     */
    @Then("the cliente should be updated with the name {string}")
    public void the_cliente_should_be_updated_with_the_name(String updatedName) {
        Cliente updatedCliente = clienteRepo.findById(cliente.getIdCliente()).orElse(null);
        assertNotNull(updatedCliente, "The updated cliente was not found in the database.");
        assertEquals(updatedName, updatedCliente.getName());
    }

    /**
     * Verifica que existen clientes en el sistema.
     */
    @Given("there are clientes in the system")
    public void there_are_clientes_in_the_system() {
        if (clienteRepo.findAll().isEmpty()) {
            a_new_cliente_with_name("Base");
            i_save_the_cliente();
        }
        assertFalse(clienteRepo.findAll().isEmpty());
    }

    /**
     * Verifica que la lista de clientes leida no este vacia.
     */
    @Then("I should see a non-empty list of clientes")
    public void i_should_see_a_non_empty_list_of_clientes() {
        try {
            assertFalse(clienteService.readAll().isEmpty());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Elimina el cliente previamente cargado en memoria.
     */
    @When("I delete the cliente")
    public void i_delete_the_cliente() {
        try {
            clienteService.delete(cliente.getIdCliente());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Verifica que el cliente eliminado ya no exista en base de datos.
     */
    @Then("the cliente should no longer exist in the system")
    public void the_cliente_should_no_longer_exist_in_the_system() {
        assertFalse(clienteRepo.existsById(cliente.getIdCliente()));
    }
}

