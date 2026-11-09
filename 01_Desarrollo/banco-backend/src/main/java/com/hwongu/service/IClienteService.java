package com.hwongu.service;

import com.hwongu.model.Cliente;

import java.util.List;

public interface IClienteService extends ICRUDService<Cliente, Integer> {

    List<Cliente> findByNameLike(String clienteName);

    List<Cliente> findClienteByNameIsEnabled(String clienteName);
}

