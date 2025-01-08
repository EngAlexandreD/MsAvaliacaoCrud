/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.avaliacao.teste.dxc.controller;

import com.avaliacao.teste.dxc.model.Cliente;
import com.avaliacao.teste.dxc.service.ClienteService;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.test.context.ContextConfiguration;

/**
 *
 * @author engal
 */
@WebMvcTest(ClienteController.class)
@ContextConfiguration
class ClienteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ClienteService clienteService; 

    @Test
    @WithMockUser(username = "sa", roles = {"USER"})
    void deveAdicionarClienteComSucesso() throws Exception {
        Cliente cliente = new Cliente();
        cliente.setNome("João");
        cliente.setEmail("joao@example.com");

        when(clienteService.salvar(any(Cliente.class))).thenReturn(cliente);

        mockMvc.perform(post("/api/clientes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(cliente))
                .with(org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf())) // Adiciona token CSRF
                .andExpect(status().isCreated());
    }

    @Test
    @WithMockUser(username = "sa", roles = {"USER"})
    void deveBuscarClientePorNome() throws Exception {
        Cliente cliente1 = new Cliente();
        cliente1.setNome("João");
        cliente1.setEmail("joao@example.com");
        Cliente cliente2 = new Cliente();
        cliente2.setNome("João");
        cliente2.setEmail("joaodasilva@example.com");
        List<Cliente> clientes = Arrays.asList(cliente1, cliente2);
        when(clienteService.buscarPorNome("João")).thenReturn(clientes);
        Map<String, String> payload = new HashMap<>();
        payload.put("nome", "João");
        mockMvc.perform(post("/buscarPorNome").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(payload)).with(org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf())); // Adiciona token CSRF .andExpect(status().isOk()); }
    }
}
