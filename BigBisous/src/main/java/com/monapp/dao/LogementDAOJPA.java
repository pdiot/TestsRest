package com.monapp.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.monapp.entity.Logement;

@Transactional
@Repository
public class LogementDAOJPA implements LogementDAO {

	@PersistenceContext
	EntityManager em;

	@Override
	public List<Logement> findAll() {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Logement> crit = cb.createQuery(Logement.class);
		Root<Logement> r = crit.from(Logement.class);
		
		crit.select(r);
		
		return em.createQuery(crit).getResultList();
	}

	@Override
	public Logement save(Logement entity) {
		em.persist(entity);
		return entity;
	}

	@Override
	public void delete(Logement entity) {
		Logement eMerged = em.merge(entity);
		em.remove(eMerged);
		
	}
	
	@Override
	public Logement findByPrimaryKey(Integer id) {
		return em.find(Logement.class, id);
	}

	@Override
	public Logement update(Logement entity) {
		return em.merge(entity);
	}


}
