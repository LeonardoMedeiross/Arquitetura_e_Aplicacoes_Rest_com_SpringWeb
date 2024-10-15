package com.example.demo.service;

import com.example.demo.entity.Produto;
import com.example.demo.exception.ProductNullException;
import com.example.demo.exception.ProductPriceException;
import com.example.demo.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {
    @Autowired
    private ProdutoRepository repository; // Injeção de dependência do repositório de produtos

    // Método para salvar um produto, lança exceções em caso de erro
    public Produto save(Produto produto) throws Exception {
        // Verifica se o nome ou preço do produto é nulo
        if (produto.getNome() == null || produto.getPreco() == null) {
            throw new ProductNullException(); // Lança exceção se algum campo obrigatório for nulo
        }

        // Verifica se o preço é negativo
        if (produto.getPreco() < 0) {
            throw new ProductPriceException(); // Lança exceção se o preço for negativo
        }

        // Salva e retorna o produto
        return repository.save(produto);
    }

    // Método para buscar um produto por ID
    public Produto findById(Long id) {
        // Retorna o produto encontrado ou null se não existir
        return repository.findById(id).orElse(null);
    }

    // Método para buscar todos os produtos
    public List<Produto> findAll() {
        // Retorna a lista de produtos
        return repository.findAll();
    }
}
