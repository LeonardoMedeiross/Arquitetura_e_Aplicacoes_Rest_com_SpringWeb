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
    private ProdutoRepository repository ;

    public Produto save(Produto produto) throws Exception
    {
        if (produto.getNome() == null || produto.getPreco() == null)
            throw new ProductNullException();

        if (produto.getPreco()< 0 ) {
            throw new ProductPriceException();
        }
        return  repository.save(produto);
    }


    public Produto FindById(Long id)
    {
        return repository.findById(id).orElse(null);

    }
    public List<Produto>  FindAll()
    {
        return repository.findAll();

    }
}
