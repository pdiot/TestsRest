package com.monapp.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.monapp.entity.Client;

@Transactional
@Repository
public class ClientDAOJPA implements ClientDAO {

	@PersistenceContext
	EntityManager em;

	@Override
	public List<Client> findAll() {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Client> crit = cb.createQuery(Client.class);
		Root<Client> r = crit.from(Client.class);
		
		crit.select(r);
		
		return em.createQuery(crit).getResultList();
	}

	@Override
	public Client save(Client entity) {
		em.persist(entity);
		return entity;
	}

	@Override
	public void delete(Client entity) {
		Client eMerged = em.merge(entity);
		em.remove(eMerged);
		
	}
	
	@Override
	public Client findByPrimaryKey(Integer id) {
		return em.find(Client.class, id);
	}

	@Override
	public Client update(Client entity) {
		return em.merge(entity);
	}


}
