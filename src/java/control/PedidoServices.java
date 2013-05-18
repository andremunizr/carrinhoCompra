
package control;

import exception.ClienteNaoEncontradoException;
import java.util.Date;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import model.Cliente;
import model.Pedido;

@Stateless
public class PedidoServices {

    @PersistenceContext
    EntityManager em;
    
    @Inject 
    private ClienteServices clienteServices;
    
    public Pedido criarPedido( Pedido pedido, String emailCliente, String senhaCliente ) 
           throws ClienteNaoEncontradoException{
        Cliente cliente = clienteServices.findByEmailAndSenha( emailCliente, senhaCliente );
        if( cliente == null ){ throw new ClienteNaoEncontradoException(); }
        pedido.setData( new Date() );
        pedido.setCliente( cliente );
        em.persist( pedido );
        return pedido;
    }
}    
