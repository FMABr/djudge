package model.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaQuery;

import model.bean.ModelBean;

public class GenericDAO<Entity extends ModelBean<Id>, Id> {
	
	
	private Class<Entity> persistedClass;
	
	public GenericDAO(Class<Entity> persistedClass) {
		this.persistedClass = persistedClass;
	}
	
	public void save(Entity entity) {
		EntityManager em = EntityManagerProvider.getEntityManager();
		em.getTransaction().begin();
		em.persist(entity);
		em.flush();
		em.getTransaction().commit();
		em.close();
	}
	
	public Entity find(Entity entity) {
		EntityManager em = EntityManagerProvider.getEntityManager();
		return em.find(persistedClass, entity.getPK());
	}

    public Entity findById(Id id) {
    	EntityManager em = EntityManagerProvider.getEntityManager();
		return em.find(persistedClass, id);
	}
    
    public List<Entity> findAll() {
    	EntityManager em = EntityManagerProvider.getEntityManager();
		CriteriaQuery<Entity> criteria = em.getCriteriaBuilder().createQuery(persistedClass);
		criteria.from(persistedClass);
		return em.createQuery(criteria).getResultList();
	}
    
    public void update(Entity entity) {
    	EntityManager em = EntityManagerProvider.getEntityManager();
		em.getTransaction().begin();
		em.merge(entity);
		em.flush();
		em.getTransaction().commit();
		em.close();
	}   

    public void remove(Entity entity) {
    	EntityManager em = EntityManagerProvider.getEntityManager();
		em.getTransaction().begin();
		em.remove(entity);
		em.flush();
		em.getTransaction().commit();
		em.close();
	}
    
    public void removeById(Id id) {
    	Entity entidade = findById(id);	
		remove(entidade);
	}
}
