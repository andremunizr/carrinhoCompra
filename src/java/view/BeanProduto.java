
package view;

import control.ProdutoServices;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import model.Produto;

@Named(value = "beanProduto")
@RequestScoped
public class BeanProduto {

    @Inject
    private ProdutoServices produtoServices;
    private List<Produto> produtos;
    
    @PostConstruct
    public void init(){
        produtos = produtoServices.findAll();
    }
    
    public List<Produto> getProdutos(){
        return produtos;
    }
}
