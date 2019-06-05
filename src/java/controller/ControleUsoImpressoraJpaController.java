/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import controller.exceptions.NonexistentEntityException;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import model.ControleUsoImpressora;

public class ControleUsoImpressoraJpaController implements Serializable {

    public ControleUsoImpressoraJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(ControleUsoImpressora impressao) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(impressao);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(ControleUsoImpressora impressao) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            impressao = em.merge(impressao);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = impressao.getId();
                if (findCliente(id) == null) {
                    throw new NonexistentEntityException("The impressao with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ControleUsoImpressora impressao;
            try {
                impressao = em.getReference(ControleUsoImpressora.class, id);
                impressao.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The impressao with id " + id + " no longer exists.", enfe);
            }
            em.remove(impressao);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<ControleUsoImpressora> findImpressaoEntities() {
        return findImpressaoEntities(true, -1, -1);
    }

    public List<ControleUsoImpressora> findImpressaoEntities(int maxResults, int firstResult) {
        return findImpressaoEntities(false, maxResults, firstResult);
    }

    private List<ControleUsoImpressora> findImpressaoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(ControleUsoImpressora.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public ControleUsoImpressora findCliente(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ControleUsoImpressora.class, id);
        } finally {
            em.close();
        }
    }

    public int getClienteCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<ControleUsoImpressora> rt = cq.from(ControleUsoImpressora.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    
    
}
