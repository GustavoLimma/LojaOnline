package br.ufrn.lojaonline.model;

import java.util.ArrayList;
import java.util.List;

public class Carrinho {
    private List<Produto> produtos = new ArrayList<>();
    public void addProduto(Produto p) { produtos.add(p); }
    public void removeProduto(int id) { /* lógica para remover */ }
    public List<Produto> getProdutos() { return produtos; }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }
}
