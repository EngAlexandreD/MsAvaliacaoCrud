/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.avaliacao.teste.dxc.service;

import com.avaliacao.teste.dxc.model.Cliente;
import com.avaliacao.teste.dxc.repository.ClienteRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author engal
 */
class ClienteServiceTest {

    @Mock
    private ClienteRepository clienteRepository;

    @InjectMocks
    private ClienteService clienteService;

    @Test
    void deveSalvarCadastroClienteComSucesso() {
        MockitoAnnotations.openMocks(this);

        Cliente cliente = new Cliente();
        cliente.setNome("Maria");

        when(clienteRepository.save(cliente)).thenReturn(cliente);

        Cliente clienteSalvo = clienteService.salvar(cliente);

        assertNotNull(clienteSalvo);
        assertEquals("Maria", clienteSalvo.getNome());
    }
}