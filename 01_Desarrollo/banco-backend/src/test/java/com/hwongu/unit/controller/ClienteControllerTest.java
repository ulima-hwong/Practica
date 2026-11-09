/**
 * Autor: Henry Wong
 * GitHub: hwongu
 *
 * Esta clase contiene pruebas unitarias para el controlador ClienteController utilizando
 * MockMvc para simular solicitudes HTTP y verificar el comportamiento del controlador.
 * Se prueban operaciones de creacion, actualizacion, eliminacion y lectura de clientes.
 */
package com.hwongu.unit.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hwongu.controller.ClienteController;
import com.hwongu.dto.ClienteDTO;
import com.hwongu.model.Cliente;
import com.hwongu.service.IClienteService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDate;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ClienteController.class)
@Import(ClienteControllerTest.TestConfig.class)
public class ClienteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IClienteService clienteService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ObjectMapper objectMapper;

    Cliente CLIENTE_1 = new Cliente(1, "Carlos", "Perez", "12345678", "carlos.perez@correo.com", LocalDate.of(2000, 5, 10), true);
    Cliente CLIENTE_2 = new Cliente(2, "Carla", "Gomez", "87654321", "carla.gomez@correo.com", LocalDate.of(2001, 8, 20), true);
    Cliente CLIENTE_3 = new Cliente(3, "Carmen", "Ruiz", "11223344", "carmen.ruiz@correo.com", LocalDate.of(1999, 3, 15), true);
    ClienteDTO CLIENTEDTO_1 = new ClienteDTO(1, "Carlos", "Perez", "12345678", "carlos.perez@correo.com", LocalDate.of(2000, 5, 10), true);
    ClienteDTO CLIENTEDTO_2 = new ClienteDTO(2, "Carla", "Gomez", "87654321", "carla.gomez@correo.com", LocalDate.of(2001, 8, 20), true);
    ClienteDTO CLIENTEDTO_3 = new ClienteDTO(3, "Carmen", "Ruiz", "11223344", "carmen.ruiz@correo.com", LocalDate.of(1999, 3, 15), true);

    @TestConfiguration
    static class TestConfig {
        @Bean(name = "modelMapper")
        public ModelMapper modelMapper() {
            return new ModelMapper();
        }
    }

    @Test
    void createTest() throws Exception {
        Mockito.when(clienteService.save(any())).thenReturn(CLIENTE_3);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
                .post("/clientes/create")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(CLIENTEDTO_3));

        mockMvc.perform(mockRequest)
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nameCliente", is("Carmen")))
                .andExpect(jsonPath("$.enabledCliente", is(true)));
    }

    @Test
    void updateTest() throws Exception {
        int ID = 2;

        Mockito.when(clienteService.update(any(), any())).thenReturn(CLIENTE_2);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
                .put("/clientes/update/" + ID)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(CLIENTEDTO_2));

        mockMvc.perform(mockRequest)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nameCliente", is("Carla")))
                .andExpect(jsonPath("$.enabledCliente", is(true)));
    }

    @Test
    public void deleteTest() throws Exception {
        final int ID_CLIENTE = 1;

        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/clientes/delete/" + ID_CLIENTE)
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isNoContent());
    }

    /**
     * Prueba unitaria para la lectura de un cliente por su ID mediante una solicitud GET.
     * Verifica que la respuesta tenga un estado HTTP 200 (OK) y que el nombre del cliente
     * leido sea correcto.
     */
    @Test
    void readById() throws Exception {
        final int ID = 1;

        Mockito.when(clienteService.readById(any())).thenReturn(CLIENTE_1);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/clientes/readById/" + ID)
                        .content(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nameCliente", is("Carlos")));
    }
}

