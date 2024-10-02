package com.example.demo.controller;

import com.example.demo.entity.Produto;
import com.example.demo.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/produto")
public class ProdutoController {
    @Autowired
    private ProdutoService service ;

    @PostMapping(value = "/save")
    public ResponseEntity<Produto> salvaProduto(@RequestBody Produto produto) throws Exception {
        produto = service.save(produto);
        return ResponseEntity.ok().body(produto);
    }

    @PostMapping(value = "/{id}")
    public ResponseEntity<Produto> buscarProduto(@PathVariable Long id){
        Produto produto = service.FindById(id);
        return ResponseEntity.ok().body(produto);
    }

    @PostMapping(value = "/busca-todos")
    public ResponseEntity<List<Produto>> buscaTodosProduto(){
        List<Produto> produtos =service.FindAll();
        return ResponseEntity.ok().body(produtos);
    }
}
