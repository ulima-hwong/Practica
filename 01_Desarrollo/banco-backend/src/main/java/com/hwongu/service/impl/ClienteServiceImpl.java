package com.hwongu.service.impl;

import com.hwongu.model.Cliente;
import com.hwongu.repo.IClienteRepo;
import com.hwongu.repo.IPostgreRepo;
import com.hwongu.service.IClienteService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteServiceImpl extends CRUDImpl<Cliente, Integer> implements IClienteService {

    private final IClienteRepo clienteRepository;

    public ClienteServiceImpl(IClienteRepo clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @Override
    protected IPostgreRepo<Cliente, Integer> getPostgreRepo() {
        return this.clienteRepository;
    }

    @Override
    public List<Cliente> findByNameLike(String clienteName) {
        return this.clienteRepository.findByNameLikeOrderByName("%" + clienteName + "%");
    }

    @Override
    public List<Cliente> findClienteByNameIsEnabled(String clienteName) {
        return this.clienteRepository.findClienteByNameEnabled("%" + clienteName + "%", true);
    }
}

