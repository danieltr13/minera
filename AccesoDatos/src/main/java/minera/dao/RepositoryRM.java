/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minera.dao;

import minera.entities.ReporteMaterial;
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
public class RepositoryRM implements BaseRepository<ReporteMaterial> {

    private static RepositoryRM repositoryRM;

    private RepositoryRM() {
    }

    public static RepositoryRM getRepositoryRM() {
        if (repositoryRM == null) {
            repositoryRM = new RepositoryRM();
        }
        return repositoryRM;
    }

    @Override
    public boolean guardar(ReporteMaterial entidad) {
        EntityManager entityManager = this.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(entidad);
        entityManager.getTransaction().commit();
        entityManager.close();
        return true;
    }

    @Override
    public boolean actualizar(ReporteMaterial entidad) {
        EntityManager entityManager = this.createEntityManager();
        entityManager.getTransaction().begin();
        ReporteMaterial material = entityManager.find(ReporteMaterial.class, entidad.getId());
        if (material != null) {
            material.setCantidad(entidad.getCantidad());
            material.setTipo(entidad.getTipo());
            material.setVehiculo(entidad.getVehiculo());
            entityManager.merge(material);
            entityManager.getTransaction().commit();
            entityManager.close();
            return true;
        }
        entityManager.getTransaction().commit();
        entityManager.close();
        return false;
    }

    @Override
    public ReporteMaterial buscarporID(long id) {
        EntityManager entityManager = this.createEntityManager();
        entityManager.getTransaction().begin();
        ReporteMaterial rMaterial = entityManager.find(ReporteMaterial.class, id);
        entityManager.getTransaction().commit();
        entityManager.close();
        return rMaterial;
    }

    @Override
    public ArrayList<ReporteMaterial> buscarTodas() {
        EntityManager em = this.createEntityManager();
        em.getTransaction().begin();
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(ReporteMaterial.class));
        Query query = em.createQuery(cq);
        ArrayList<ReporteMaterial> reporteMateriales = new ArrayList<>(query.getResultList());
        em.getTransaction().commit();
        em.close();
        return reporteMateriales;
    }

    @Override
    public boolean eliminar(long id) {
        EntityManager em = this.createEntityManager();
        em.getTransaction().begin();
        ReporteMaterial reporteMaterial = em.find(ReporteMaterial.class, id);
        if (reporteMaterial != null) {
            em.remove(reporteMaterial);
            em.getTransaction().commit();
            em.close();
            return true;
        }
        em.getTransaction().commit();
        em.close();
        return false;
    }

    @Override
    public List<ReporteMaterial> buscarComo(String busqueda) {
        EntityManager em = this.createEntityManager();
        em.getTransaction().begin();
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<ReporteMaterial> reporteMaterial = builder.createQuery(ReporteMaterial.class);
        Root<ReporteMaterial> root = reporteMaterial.from(ReporteMaterial.class);
        reporteMaterial = reporteMaterial.select(root).where(builder.like(root.get("tipo"), "%" + busqueda + "%"));
        TypedQuery<ReporteMaterial> typedQuery = em.createQuery(reporteMaterial);
        ArrayList<ReporteMaterial> reportesM = new ArrayList<>(typedQuery.getResultList());
        em.getTransaction().commit();
        em.close();
        return reportesM;
    }

}
