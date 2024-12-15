package com.kabum.blackfriday;

import com.kabum.blackfriday.model.Pedido;
import com.kabum.blackfriday.model.Produto;
import com.kabum.blackfriday.repository.PedidoRepository;
import com.kabum.blackfriday.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DatabasePopulatorRunner implements CommandLineRunner {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private PedidoRepository pedidoRepository;

    @Override
    public void run(String... args) throws Exception {
        // Criando e salvando Produtos
        Produto produto1 = new Produto();
        produto1.setNome("Produto 1");
        produto1.setPreco(19.99);
        produto1.setUnidadesEmEstoque(100);
        produtoRepository.save(produto1);

        Produto produto2 = new Produto();
        produto2.setNome("Produto 2");
        produto2.setPreco(29.99);
        produto2.setUnidadesEmEstoque(50);
        produtoRepository.save(produto2);

        Produto produto3 = new Produto();
        produto3.setNome("Produto 3");
        produto3.setPreco(39.99);
        produto3.setUnidadesEmEstoque(30);
        produtoRepository.save(produto3);

        Pedido pedido1 = new Pedido();
        pedido1.setProduto(produto1);
        pedido1.setQuantidade(2);
        pedido1.setValorTotal(produto1.getPreco() * pedido1.getQuantidade());
        pedidoRepository.save(pedido1);

        Pedido pedido2 = new Pedido();
        pedido2.setProduto(produto2);
        pedido2.setQuantidade(3);
        pedido2.setValorTotal(produto2.getPreco() * pedido2.getQuantidade());
        pedidoRepository.save(pedido2);

        Pedido pedido3 = new Pedido();
        pedido3.setProduto(produto3);
        pedido3.setQuantidade(1);
        pedido3.setValorTotal(produto3.getPreco() * pedido3.getQuantidade());
        pedidoRepository.save(pedido3);

        System.out.println("Banco de dados populado com dados iniciais.");
    }
}
