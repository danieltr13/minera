/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minera.dao;

import minera.entities.ReporteCongestion;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author Alfon
 */
public class RepositoryRC implements BaseRepository<ReporteCongestion>{
    private static RepositoryRC repositoryRC;

    private RepositoryRC() {
    }
    
    public static RepositoryRC getRepositoryRC(){
        if (repositoryRC==null) {
            repositoryRC= new RepositoryRC();
        }
        return repositoryRC;
    }
    
    @Override
    public boolean guardar(ReporteCongestion entidad) {
        EntityManager entityManager = this.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(entidad);
        entityManager.getTransaction().commit();
        entityManager.close();
        return true;
    }

    @Override
    public boolean actualizar(ReporteCongestion entidad) {
        EntityManager entityManager = this.createEntityManager();
        entityManager.getTransaction().begin();
        ReporteCongestion rCongestion = entityManager.find(ReporteCongestion.class, entidad.getId());
        if (rCongestion != null) {
            rCongestion.setCausa(entidad.getCausa());
            rCongestion.setEventualidad(entidad.getEventualidad());
            rCongestion.setUbicacion(entidad.getUbicacion());
            rCongestion.setMatriculas(entidad.getMatricula());
            entityManager.merge(rCongestion);
            entityManager.getTransaction().commit();
            entityManager.close();
            return true;
        }
        entityManager.getTransaction().commit();
        entityManager.close();
        return false;
    }

    @Override
    public ReporteCongestion buscarporID(long id) {
        EntityManager entityManager = this.createEntityManager();
        entityManager.getTransaction().begin();
        ReporteCongestion rCongestion = entityManager.find(ReporteCongestion.class, id);
        entityManager.getTransaction().commit();
        entityManager.close();
        return rCongestion;
    }

    @Override
    public ArrayList<ReporteCongestion> buscarTodas() {
        EntityManager em = this.createEntityManager();
        em.getTransaction().begin();
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(ReporteCongestion.class));
        Query query = em.createQuery(cq);
        ArrayList<ReporteCongestion> reportesCongestion = new ArrayList<>(query.getResultList());
        em.getTransaction().commit();
        em.close();
        return reportesCongestion;

    }

    @Override
    public boolean eliminar(long id) {
        EntityManager em = this.createEntityManager();
        em.getTransaction().begin();
        ReporteCongestion reporteCongestion = em.find(ReporteCongestion.class, id);
        if (reporteCongestion != null) {
            em.remove(reporteCongestion);
            em.getTransaction().commit();
            em.close();
            return true;
        }
        em.getTransaction().commit();
        em.close();
        return false;
    }

    @Override
    public List<ReporteCongestion> buscarComo(String busqueda) {
        EntityManager em = this.createEntityManager();
        em.getTransaction().begin();
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<ReporteCongestion> reporteCongestion = builder.createQuery(ReporteCongestion.class);
        Root<ReporteCongestion> root = reporteCongestion.from(ReporteCongestion.class);
        reporteCongestion = reporteCongestion.select(root).where(builder.like(root.get("matriculas"), "%" + busqueda + "%"));
        TypedQuery<ReporteCongestion> typedQuery = em.createQuery(reporteCongestion);
        ArrayList<ReporteCongestion> reportesM = new ArrayList<>(typedQuery.getResultList());
        em.getTransaction().commit();
        em.close();
        return reportesM;
    }
    
}
