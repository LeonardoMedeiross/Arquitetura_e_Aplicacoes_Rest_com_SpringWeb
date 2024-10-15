package com.example.demo.controller;

import com.example.demo.entity.Pedido;
import com.example.demo.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController // Indica que esta classe é um controlador REST
@RequestMapping("/pedidos") // Define a rota base para este controlador
public class PedidoController {

    @Autowired // Injeta a instância do PedidoService automaticamente
    private PedidoService servico;

    // Método para salvar um novo pedido
    @PostMapping(value = "/save") // Mapeia requisições POST para "/pedidos/save"
    public ResponseEntity<Pedido> salvaPedido(@RequestBody Pedido pedido) {
        // Chama o método save do serviço, que salva o pedido e retorna o pedido salvo
        pedido = servico.save(pedido);
        // Retorna uma resposta 200 OK com o pedido salvo no corpo da resposta
        return ResponseEntity.ok().body(pedido);
    }

    // Método para buscar um pedido pelo ID
    @GetMapping(value = "/{id}") // Mapeia requisições GET para "/pedidos/{id}"
    public ResponseEntity<Pedido> buscarPedido(@PathVariable Long id) {
        // Chama o método findById do serviço, que retorna um Optional<Pedido>
        Optional<Pedido> optionalPedido = servico.findById(id);

        // Verifica se o pedido existe
        if (optionalPedido.isPresent()) { // Se o pedido foi encontrado
            // Retorna uma resposta 200 OK com o pedido no corpo da resposta
            return ResponseEntity.ok().body(optionalPedido.get());
        } else {
            // Retorna uma resposta 404 Not Found se o pedido não for encontrado
            return ResponseEntity.notFound().build();
        }
    }
}
