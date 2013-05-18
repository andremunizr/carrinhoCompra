
package control;

import exception.ClienteExistenteException;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import model.Cliente;

@Stateless
public class ClienteServices {

    @PersistenceContext
    EntityManager em;
    
    public Cliente findByEmailAndSenha( String email, String senha ){
        Query query = em.createQuery("SELECT c FROM Cliente c WHERE c.email = :email AND c.senha = :senha");
        query.setParameter("email",email);
        query.setParameter("senha",senha);
        try{
            return (Cliente) query.getSingleResult();
        }
        catch(NoResultException e){
            return null;
        }
    }
    
    public Cliente adicionar( Cliente cliente ) throws ClienteExistenteException{
        try{
            em.persist(cliente);
            return cliente;
        }
        catch(PersistenceException e){
            throw new ClienteExistenteException();
        }
    }

}
