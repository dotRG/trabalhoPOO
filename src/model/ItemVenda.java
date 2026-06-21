package model;

import java.io.Serializable;

/**
 * Linha de uma venda: um produto, a quantidade vendida e o respetivo subtotal.
 * Uma Venda é composta por uma ou mais linhas (ItemVenda).
 */
public class ItemVenda implements Serializable {
    private static final long serialVersionUID = 10L;

    private Produto produto;
    private int quantidade;
    private double subtotal;

    public ItemVenda(Produto produto, int quantidade) {
        this.produto = produto;
        this.quantidade = quantidade;
        this.subtotal = produto.getPrecoVenda() * quantidade;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
        this.subtotal = produto.getPrecoVenda() * quantidade;
    }

    public double getSubtotal() {
        return subtotal;
    }

    @Override
    public String toString() {
        return produto.getFormato() + " x" + quantidade + " (" + String.format("%.2f", subtotal) + "€)";
    }
}
