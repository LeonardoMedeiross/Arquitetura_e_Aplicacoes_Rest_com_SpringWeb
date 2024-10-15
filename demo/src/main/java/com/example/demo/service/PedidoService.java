package com.example.demo.service;

import com.example.demo.entity.Pedido;
import com.example.demo.entity.Produto;
import com.example.demo.repository.PedidoRepository;
import com.example.demo.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    public Pedido save(Pedido pedido) {
        Set<Produto> produtos = new HashSet<>(); // Cria um conjunto para armazenar produtos associados

        pedido.setDataPedido(LocalDateTime.now()); // Define a data do pedido como o horário atual
        // Adiciona cada produto ao conjunto de produtos, buscando pelo ID
        pedido.getProdutos().forEach(produto -> {
            produtos.add(produtoRepository.getById(produto.getId()));
        });
        pedido.setProdutos(produtos); // Define o conjunto de produtos no pedido

        return pedidoRepository.save(pedido); // Salva o pedido e retorna o pedido salvo
    }

    public Optional<Pedido> findById(Long id) {
        return pedidoRepository.findById(id); // Retorna o pedido envolto em um Optional
    }

    public List<Pedido> findAll() {
        return pedidoRepository.findAll(); // Retorna uma lista de todos os pedidos
    }
}
