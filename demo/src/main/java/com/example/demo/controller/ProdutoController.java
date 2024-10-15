package com.example.demo.controller;

import com.example.demo.entity.Produto;
import com.example.demo.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // Indica que esta classe é um controlador REST
@RequestMapping(value = "/produto") // Define o caminho base para este controlador
public class ProdutoController {

    @Autowired // Injeção de dependência do serviço de produtos
    private ProdutoService service;

    @PostMapping(value = "/save") // Define que este método responde a requisições POST no endpoint /save
    public ResponseEntity<Produto> salvaProduto(@RequestBody Produto produto) throws Exception {
        // Chama o método save do serviço para salvar o produto
        produto = service.save(produto);
        return ResponseEntity.ok().body(produto); // Retorna uma resposta 200 OK com o produto salvo
    }

    @GetMapping(value = "/{id}") // Define que este método responde a requisições GET para buscar um produto por ID
    public ResponseEntity<Produto> buscarProduto(@PathVariable Long id) {
        Produto produto = service.findById(id); // Chama o método findById do serviço para buscar o produto

        // Verifica se o produto foi encontrado
        if (produto == null) {
            return ResponseEntity.notFound().build(); // Retorna 404 NOT FOUND se o produto não existir
        }

        return ResponseEntity.ok().body(produto); // Retorna o produto encontrado
    }

    @GetMapping(value = "/all") // Define que este método responde a requisições GET para buscar todos os produtos
    public ResponseEntity<List<Produto>> buscaTodosProduto() {
        List<Produto> produtos = service.findAll(); // Chama o método findAll do serviço para obter todos os produtos
        return ResponseEntity.ok().body(produtos); // Retorna a lista de produtos
    }
}
