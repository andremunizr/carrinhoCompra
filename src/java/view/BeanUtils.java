
package view;

import java.util.MissingResourceException;
import java.util.ResourceBundle;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.context.FacesContext;

@Named(value = "beanUtils")
@ApplicationScoped
public class BeanUtils {
    
    private ResourceBundle bundle;
    
    @PostConstruct
    public void init(){
        this.bundle = ResourceBundle.getBundle( "messages", FacesContext
                                                           .getCurrentInstance()
                                                           .getViewRoot()
                                                           .getLocale() );
    }
    
    public String getMessage( String chave ){
        
        String message;
        try{
            message = bundle.getString( chave );
        }
        catch( MissingResourceException e ){
            return "?? chave " + chave + " inexistente ??";
        }
        return message;
    }    
}  
