package beans;

import controller.ClienteJpaController;
import controller.exceptions.NonexistentEntityException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import model.Cliente;

@ManagedBean
@ViewScoped
public class ClienteBean {
    private Cliente cliente;
    private final EntityManagerFactory emf;


    public ClienteBean() {
        cliente = new Cliente();
        emf = Persistence.createEntityManagerFactory("JSF_JPA_NPU");
    }
    
    public void save(){
        ClienteJpaController dao = new ClienteJpaController(emf);
        dao.create(cliente);
    }
    public void update(){
        ClienteJpaController dao = new ClienteJpaController(emf);
        try {
            dao.edit(cliente);
        } catch (Exception ex) {
            Logger.getLogger(ClienteBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void delete(){
        ClienteJpaController dao = new ClienteJpaController(emf);
        try {
            dao.destroy(cliente.getId());
        } catch (Exception ex) {
            System.out.println("Erro:"+ex.getLocalizedMessage());
        }
    }
    public String count(){
        ClienteJpaController dao = new ClienteJpaController(emf);
        return ""+dao.getClienteCount();
    }
    

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
       public List<Cliente> list(){
        ClienteJpaController dao = new ClienteJpaController(emf);
        return dao.findClienteEntities();   
    }
     
}
