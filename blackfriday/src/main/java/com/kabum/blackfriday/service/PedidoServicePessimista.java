package com.kabum.blackfriday.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kabum.blackfriday.model.Pedido;
import com.kabum.blackfriday.model.Produto;
import com.kabum.blackfriday.repository.PedidoRepository;
import com.kabum.blackfriday.repository.ProdutoRepository;

import jakarta.transaction.Transactional;

@Service
public class PedidoServicePessimista {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private PedidoRepository pedidoRepository;

    @Transactional
    public void processarPedido(Long produtoId, int quantidade) {
        Produto produto = produtoRepository.lockProdutoById(produtoId);

        if (produto.getUnidadesEmEstoque() < quantidade) {
            throw new RuntimeException("Estoque insuficiente");
        }

        produto.setUnidadesEmEstoque(produto.getUnidadesEmEstoque() - quantidade);
        produtoRepository.save(produto);

        Pedido pedido = new Pedido();
        pedido.setProduto(produto);
        pedido.setQuantidade(quantidade);
        pedido.setValorTotal(quantidade * produto.getPreco());
        pedidoRepository.save(pedido);
    }
}