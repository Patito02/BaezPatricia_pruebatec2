package com.hackaboss.pruebatecnica2.persistencia;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.hackaboss.pruebatecnica2.logica.Ciudadano;
import com.hackaboss.pruebatecnica2.logica.Turno;
import com.hackaboss.pruebatecnica2.persistencia.exceptions.NonexistentEntityException;
import java.time.LocalDate;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;


public class TurnoJpaController implements Serializable {

    public TurnoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    public TurnoJpaController() {
        this.emf = Persistence.createEntityManagerFactory("turnero_PU");
    }
    
    public void create(Turno turno) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Ciudadano ciudadano = turno.getCiudadano();
            if (ciudadano != null) {
                ciudadano = em.getReference(ciudadano.getClass(), ciudadano.getId());
                turno.setCiudadano(ciudadano);
            }
            em.persist(turno);
            if (ciudadano != null) {
                ciudadano.getListaTurnos().add(turno);
                ciudadano = em.merge(ciudadano);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Turno turno) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Turno persistentTurno = em.find(Turno.class, turno.getId());
            Ciudadano ciudadanoOld = persistentTurno.getCiudadano();
            Ciudadano ciudadanoNew = turno.getCiudadano();
            if (ciudadanoNew != null) {
                ciudadanoNew = em.getReference(ciudadanoNew.getClass(), ciudadanoNew.getId());
                turno.setCiudadano(ciudadanoNew);
            }
            turno = em.merge(turno);
            if (ciudadanoOld != null && !ciudadanoOld.equals(ciudadanoNew)) {
                ciudadanoOld.getListaTurnos().remove(turno);
                ciudadanoOld = em.merge(ciudadanoOld);
            }
            if (ciudadanoNew != null && !ciudadanoNew.equals(ciudadanoOld)) {
                ciudadanoNew.getListaTurnos().add(turno);
                ciudadanoNew = em.merge(ciudadanoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = turno.getId();
                if (findTurno(id) == null) {
                    throw new NonexistentEntityException("The turno with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(int id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Turno turno;
            try {
                turno = em.getReference(Turno.class, id);
                turno.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The turno with id " + id + " no longer exists.", enfe);
            }
            Ciudadano ciudadano = turno.getCiudadano();
            if (ciudadano != null) {
                ciudadano.getListaTurnos().remove(turno);
                ciudadano = em.merge(ciudadano);
            }
            em.remove(turno);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Turno> findTurnoEntities() {
        return findTurnoEntities(true, -1, -1);
    }

    public List<Turno> findTurnoEntities(int maxResults, int firstResult) {
        return findTurnoEntities(false, maxResults, firstResult);
    }

    private List<Turno> findTurnoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Turno.class));
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

    public Turno findTurno(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Turno.class, id);
        } finally {
            em.close();
        }
    }

    public int getTurnoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Turno> rt = cq.from(Turno.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
    public List<Turno> traerTurnosHabilitados() {
        EntityManager em = getEntityManager();
        Query query = em.createQuery("SELECT t FROM Turno t WHERE t.habilitado = :habilitado");
        query.setParameter("habilitado", true);
        return query.getResultList();
    }
    
    public Turno traerTurnoHabilitado(int id) {
        EntityManager em = getEntityManager();
        Query query = em.createQuery("SELECT t FROM Turno t WHERE t.id = :id AND t.habilitado = :habilitado");
        query.setParameter("id", id);
        query.setParameter("habilitado", true);
        try {
            return (Turno) query.getSingleResult();
        } catch (NoResultException ex) {
            return null; 
        } finally {
            em.close(); 
        }
    }
    
    public List<Turno> findByFecha(LocalDate fecha) {
        EntityManager em = getEntityManager();
        Query query = em.createQuery("SELECT t FROM Turno t WHERE t.fecha = :fecha AND t.habilitado = :habilitado");
        query.setParameter("fecha", fecha);
        query.setParameter("habilitado", true);
        return query.getResultList();
    }
    
}
