
package model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Embeddable
public class ItemPedido implements Serializable, Comparable<ItemPedido>{
    private static final long serialVersionUID = 1L;
    
    @ManyToOne
    @JoinColumn( name = "id_produto" )
    private Produto produto;
    @Column( name = "preco_unitario" )
    @NotNull
    private Double precoUnitario;
    @NotNull
    private Integer quantidade;
    @Column( name = "preco_total" )
    @NotNull
    private Double precoTotal;
    
    public ItemPedido(){};
    
    public ItemPedido( Produto produto ){
        this.produto = produto;
    }
    
    public ItemPedido( Produto produto, Integer quantidade ){
        this.produto = produto;
        this.precoUnitario = produto.getPreco();
        this.quantidade = quantidade;
        calcularTotal();
    }
    
    public void calcularTotal(){
        precoTotal = precoUnitario * quantidade;
    }
    
    public void atualizarQuantidade( Integer novaQuantidade ){
        this.quantidade = novaQuantidade;
        calcularTotal();
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Double getPrecoUnitario() {
        return precoUnitario;
    }

    public void setPrecoUnitario(Double precoUnitario) {
        this.precoUnitario = precoUnitario;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Double getPrecoTotal() {
        return precoTotal;
    }

    public void setPrecoTotal(Double precoTotal) {
        this.precoTotal = precoTotal;
    }
    
    @Override
    public int compareTo(ItemPedido o) {
        return produto.getTitulo().compareTo( o.getProduto().getTitulo() );
    }
      
}
