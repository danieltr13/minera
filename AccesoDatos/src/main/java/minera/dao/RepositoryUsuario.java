/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minera.dao;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import minera.entities.ReporteMaterial;
import minera.entities.Usuario;

/**
 *
 * @author Alfon
 */
public class RepositoryUsuario implements BaseRepository<Usuario> {

    private static RepositoryUsuario repositoryUsuario;

    private RepositoryUsuario() {

    }

    public static RepositoryUsuario getInstance() {
        if (repositoryUsuario == null) {
            repositoryUsuario = new RepositoryUsuario();
        }
        return repositoryUsuario;
    }

    @Override
    public boolean guardar(Usuario entidad) {
        EntityManager entityManager = this.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(entidad);
        entityManager.getTransaction().commit();
        entityManager.close();
        return true;
    }

    @Override
    public boolean actualizar(Usuario entidad) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Usuario buscarporID(long id) {
        EntityManager entityManager = this.createEntityManager();
        entityManager.getTransaction().begin();
        Usuario user = entityManager.find(Usuario.class, id);
        entityManager.getTransaction().commit();
        entityManager.close();
        return user;
    }

    @Override
    public ArrayList<Usuario> buscarTodas() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean eliminar(long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Usuario> buscarComo(String busqueda) {
        EntityManager em = this.createEntityManager();
        em.getTransaction().begin();
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Usuario> usuarioBuscar = builder.createQuery(Usuario.class);
        Root<Usuario> root = usuarioBuscar.from(Usuario.class);
        usuarioBuscar = usuarioBuscar.select(root).where(builder.like(root.get("name"), "%" + busqueda + "%"));
        TypedQuery<Usuario> typedQuery = em.createQuery(usuarioBuscar);
        ArrayList<Usuario> reportesM = new ArrayList<>(typedQuery.getResultList());
        em.getTransaction().commit();
        em.close();
        return reportesM;

    }

}
