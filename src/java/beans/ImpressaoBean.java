package beans;

import controller.ImpressaoJpaController;
import controller.exceptions.NonexistentEntityException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import model.Impressao;

@ManagedBean
@ViewScoped
public class ImpressaoBean {
    private Impressao impressao;
    private final EntityManagerFactory emf;


    public ImpressaoBean() {
        impressao = new Impressao();
        emf = Persistence.createEntityManagerFactory("JSF_JPA_NPU");
    }
    
    public void save(){
        ImpressaoJpaController dao = new ImpressaoJpaController(emf);
        dao.create(impressao);
    }
    public void update(){
        ImpressaoJpaController dao = new ImpressaoJpaController(emf);
        try {
            dao.edit(impressao);
        } catch (Exception ex) {
            Logger.getLogger(ClienteBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void delete(){
        ImpressaoJpaController dao = new ImpressaoJpaController(emf);
        try {
            dao.destroy(impressao.getId());
        } catch (Exception ex) {
            System.out.println("Erro:"+ex.getLocalizedMessage());
        }
    }
    public String count(){
        ImpressaoJpaController dao = new ImpressaoJpaController(emf);
        return ""+dao.getClienteCount();
    }
    

    public Impressao getImpressao() {
        return impressao;
    }

    public void setImpressao(Impressao cliente) {
        this.impressao = cliente;
    }
       public List<Impressao> list(){
        ImpressaoJpaController dao = new ImpressaoJpaController(emf);
        return dao.findImpressaoEntities();   
    }
     
}
