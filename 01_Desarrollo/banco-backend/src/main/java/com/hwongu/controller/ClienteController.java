package com.hwongu.controller;

import com.hwongu.dto.ClienteDTO;
import com.hwongu.model.Cliente;
import com.hwongu.service.IClienteService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    private final IClienteService clienteService;
    private final ModelMapper modelMapper;

    public ClienteController(IClienteService clienteService, @Qualifier("modelMapper") ModelMapper modelMapper) {
        this.clienteService = clienteService;
        this.modelMapper = modelMapper;
    }

    @PostMapping("/create")
    public ResponseEntity<ClienteDTO> createCliente(@Valid @RequestBody ClienteDTO dto) throws Exception{
        Cliente objectCreated = this.clienteService.save(this.convertToEntity(dto));
        ClienteDTO object = this.convertToDto(objectCreated);
        return new ResponseEntity<>(object, HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ClienteDTO> updateCliente(@PathVariable("id") Integer id, @Valid @RequestBody ClienteDTO dto) throws Exception{
        dto.setIdCliente(id);
        Cliente objectUpdated = this.clienteService.update(this.convertToEntity(dto), id);
        ClienteDTO object = this.convertToDto(objectUpdated);
        return new ResponseEntity<>(object, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws Exception{
        this.clienteService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/readAll")
    public ResponseEntity<List<ClienteDTO>> readAll() throws Exception{
        List<ClienteDTO> list = this.clienteService.readAll().stream().map(this::convertToDto).toList();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/readById/{id}")
    public ResponseEntity<ClienteDTO> readById(@PathVariable("id") Integer id) throws Exception{
        ClienteDTO object = this.convertToDto(this.clienteService.readById(id));
        return new ResponseEntity<>(object, HttpStatus.OK);
    }

    @GetMapping("/find/byName")
    public ResponseEntity<List<ClienteDTO>> findByName(@RequestParam("name") String name) throws Exception{
        List<ClienteDTO> list = this.clienteService.findByNameLike(name).stream().map(this::convertToDto).toList();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/find/byNameIsEnabled")
    public ResponseEntity<List<ClienteDTO>> findClienteByNameIsEnabled(@RequestParam("name") String name) throws Exception{
        List<ClienteDTO> list = this.clienteService.findClienteByNameIsEnabled(name).stream().map(this::convertToDto).toList();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    private ClienteDTO convertToDto(Cliente obj){
        return this.modelMapper.map(obj, ClienteDTO.class);
    }

    private Cliente convertToEntity(ClienteDTO dto){
        return this.modelMapper.map(dto, Cliente.class);
    }
}

