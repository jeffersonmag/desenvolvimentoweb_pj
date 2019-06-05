package beans;

import controller.CadastroImpressoraJpaController;
import controller.exceptions.NonexistentEntityException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import model.CadastroImpressora;


@ManagedBean
@ViewScoped
public class CadastroImpressoraBean {
    private CadastroImpressora impressora; 
    private final EntityManagerFactory emf;


    public CadastroImpressoraBean() {
        impressora = new CadastroImpressora();
        emf = Persistence.createEntityManagerFactory("JSF_JPA_NPU");
    }
    
    public void save(){
        CadastroImpressoraJpaController dao = new CadastroImpressoraJpaController(emf);
        dao.create(impressora);
    }
    public void update(){
        CadastroImpressoraJpaController dao = new CadastroImpressoraJpaController(emf);
        try {
            dao.edit(impressora);
        } catch (Exception ex) {
            Logger.getLogger(ImpressaoBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void delete(){
        CadastroImpressoraJpaController dao = new CadastroImpressoraJpaController(emf);
        try {
            dao.destroy(impressora.getId());
        } catch (Exception ex) {
            System.out.println("Erro:"+ex.getLocalizedMessage());
        }
    }
    public String count(){
        CadastroImpressoraJpaController dao = new CadastroImpressoraJpaController(emf);
        return ""+dao.getClienteCount();
    }
    

    public CadastroImpressora getImpressao() {
        return impressora;
    }

    public void setImpressao(CadastroImpressora impressora) {
        this.impressora = impressora;
    }
       public List<CadastroImpressora> list(){
        CadastroImpressoraJpaController dao = new CadastroImpressoraJpaController(emf);
        return dao.findImpressaoEntities();   
    }
     
}
