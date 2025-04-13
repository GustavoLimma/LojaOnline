package br.ufrn.lojaonline.model;

import java.util.HashMap;
import java.util.Map;

public class Carrinho {

    private Map<Long, Integer> itens = new HashMap<>(); // ProdutoID -> Quantidade

    public void adicionarItem(Long produtoId) {
        itens.put(produtoId, itens.getOrDefault(produtoId, 0) + 1);
    }

    public void removerItem(Long produtoId) {
        if (itens.containsKey(produtoId)) {
            int qtd = itens.get(produtoId);
            if (qtd > 1) {
                itens.put(produtoId, qtd - 1);
            } else {
                itens.remove(produtoId);
            }
        }
    }

    public Map<Long, Integer> getItens() {
        return itens;
    }

    public void limpar() {
        itens.clear();
    }
}
