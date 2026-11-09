package com.hwongu.repo;

import com.hwongu.model.Cliente;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IClienteRepo extends IPostgreRepo<Cliente, Integer> {

    List<Cliente> findByNameLikeOrderByName(String clienteName);

    @Query("FROM Cliente a where a.name like :clienteName and a.enabled = :clienteEnabled")
    List<Cliente> findClienteByNameEnabled(@Param("clienteName") String clienteName, @Param("clienteEnabled") boolean clienteEnabled);
}

