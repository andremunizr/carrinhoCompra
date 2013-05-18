
package control;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import model.Produto;

@Stateless
public class ProdutoServices {

    @PersistenceContext
    EntityManager em;
    
    @SuppressWarnings( "unchecked" )
    public List<Produto> findAll(){
        return em.createQuery( "SELECT p FROM Produto p ORDER BY p.titulo" ).getResultList();
    }

}
