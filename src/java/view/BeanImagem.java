
package view;

import java.io.FileInputStream;
import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

@Named(value = "beanImagem")
@RequestScoped
public class BeanImagem {

    private static final String PATH_IMAGES = "/resources/img/";
    
    public StreamedContent getImage() throws IOException{
        
        FacesContext ctx = FacesContext.getCurrentInstance();
        DefaultStreamedContent ct = new DefaultStreamedContent();
        ct.setContentType("image/jpg");
        
        if( ctx.getRenderResponse() ){ return ct; }
        else{ 
            String name = ctx.getExternalContext().getRequestParameterMap().get("name");
            ct.setStream( new FileInputStream( PATH_IMAGES + name ) );
            return ct;
        }
    }
}
