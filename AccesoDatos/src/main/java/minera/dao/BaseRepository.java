/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minera.dao;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Alfon
 * @param <T> 
 */
public interface BaseRepository<T> {

    public abstract boolean guardar(T entidad);

    public abstract boolean actualizar(T entidad);

    public abstract T buscarporID(long id);

    public abstract ArrayList<T> buscarTodas();

    public abstract boolean eliminar(long id);

    public abstract List<T> buscarComo(String busqueda);

    default EntityManager createEntityManager() {
        EntityManagerFactory managerFactory = Persistence.createEntityManagerFactory("minera_AccesoDatos_jar_1.0PU");
        EntityManager entityManager = managerFactory.createEntityManager();
        return entityManager;
    }
}
