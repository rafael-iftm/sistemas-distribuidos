package com.kabum.blackfriday.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kabum.blackfriday.service.PedidoServiceOtimista;
import com.kabum.blackfriday.service.PedidoServicePessimista;
import com.kabum.blackfriday.service.PedidoServiceSemLock;

@RestController
@RequestMapping("/pedido")
public class PedidoController {

    @Autowired
    private PedidoServiceSemLock pedidoServiceSemLock;

    @Autowired
    private PedidoServicePessimista pedidoServicePessimista;

    @Autowired
    private PedidoServiceOtimista pedidoServiceOtimista;

    @PostMapping("/novo")
    public ResponseEntity<?> novoPedidoSemLock(@RequestParam Long produtoId, @RequestParam int quantidade) {
        try {
            pedidoServiceSemLock.processarPedido(produtoId, quantidade);
            return ResponseEntity.ok("Pedido realizado com sucesso");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    @PostMapping("/novo_pessimista")
    public ResponseEntity<?> novoPedidoPessimista(@RequestParam Long produtoId, @RequestParam int quantidade) {
        try {
            pedidoServicePessimista.processarPedido(produtoId, quantidade);
            return ResponseEntity.ok("Pedido realizado com sucesso");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    @PostMapping("/novo_otimista")
    public ResponseEntity<?> novoPedidoOtimista(@RequestParam Long produtoId, @RequestParam int quantidade) {
        try {
            pedidoServiceOtimista.processarPedido(produtoId, quantidade);
            return ResponseEntity.ok("Pedido realizado com sucesso");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }
}