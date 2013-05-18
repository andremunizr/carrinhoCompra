
package view;

import control.ClienteServices;
import control.PedidoServices;
import exception.ClienteExistenteException;
import exception.ClienteNaoEncontradoException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import model.Cliente;
import model.ItemPedido;
import model.Pedido;
import model.Produto;

@Named(value = "beanCarrinho")
@SessionScoped
public class BeanCarrinho implements Serializable {

    private Pedido pedidoCarrinho;
    private Integer idPedidoGerado;
    private Cliente cliente;
    private Produto produtoRemover;
    
    @Inject
    private transient ClienteServices clienteServices;    
    @Inject
    private transient PedidoServices pedidoServices;
    @Inject
    private transient BeanUtils utilsMB;
    
    @PostConstruct
    public void init(){
        pedidoCarrinho = new Pedido();
        cliente = new Cliente();
    }
    
    public String adicionarItem( Produto produto ){
        
        pedidoCarrinho.adicionarItem(produto,1);
        return "carrinho?faces-redirect=true";
    }
    
    public void removerItem(){
        pedidoCarrinho.removerItem( produtoRemover );
    }
    
    public void atualizarQuantidadeItem( Produto produto, Integer novaQuantidade ){
        pedidoCarrinho.atualizarQuantidade( produto, novaQuantidade );
    }
    
    public String fecharPedidoUsuarioExistente(){
        return fecharPedido();
    }
    
    public String fecharPedidoNovoUsuario(){
        try{
            cliente = clienteServices.adicionar( cliente );
        }catch( ClienteExistenteException e ){
            adicionarMensagem( FacesMessage.SEVERITY_ERROR, "cliente-existente" );
            return null;
        }
        return fecharPedido();
    }
    
    private String fecharPedido(){
        try{
            pedidoCarrinho = pedidoServices.criarPedido( pedidoCarrinho, cliente.getEmail(), cliente.getSenha() );
            idPedidoGerado = pedidoCarrinho.getId();
            init();
            return "pedidoFechado?faces-redirect=true";
        }catch( ClienteNaoEncontradoException e ){
            adicionarMensagem( FacesMessage.SEVERITY_ERROR, "cliente-nao-encontrado" );
            return null;
        }
    }
    
    private void adicionarMensagem( FacesMessage.Severity severidade, String chave ){
        FacesContext facesContext = FacesContext.getCurrentInstance();
        facesContext.addMessage( null, new FacesMessage( severidade, utilsMB.getMessage(chave), null ) );
    }
    
    public void recalcularTotal( ItemPedido itemPedido ){
        itemPedido.calcularTotal();
        pedidoCarrinho.calcularTotal();
    }
    
    public boolean temItems(){
        return pedidoCarrinho.getItens().size() > 0;
    }

    public Pedido getPedidoCarrinho() {
        return pedidoCarrinho;
    }

    public void setPedidoCarrinho(Pedido pedidoCarrinho) {
        this.pedidoCarrinho = pedidoCarrinho;
    }

    public Integer getIdPedidoGerado() {
        return idPedidoGerado;
    }

    public void setIdPedidoGerado(Integer idPedidoGerado) {
        this.idPedidoGerado = idPedidoGerado;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Produto getProdutoRemover() {
        return produtoRemover;
    }

    public void setProdutoRemover(Produto produtoRemover) {
        this.produtoRemover = produtoRemover;
    }

    public BeanUtils getBeanUtils(){
        return utilsMB;
    }

    public void setBeanUtils(BeanUtils utilsMB) {
        this.utilsMB = utilsMB;
    }
    
}
