package com.monapp.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.monapp.entity.Appartement;

@Transactional
@Repository
public class AppartementDAOJPA implements AppartementDAO {

	@PersistenceContext
	EntityManager em;

	@Override
	public List<Appartement> findAll() {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Appartement> crit = cb.createQuery(Appartement.class);
		Root<Appartement> r = crit.from(Appartement.class);
		
		crit.select(r);
		
		return em.createQuery(crit).getResultList();
	}

	@Override
	public Appartement save(Appartement entity) {
		em.persist(entity);
		return entity;
	}

	@Override
	public void delete(Appartement entity) {
		Appartement eMerged = em.merge(entity);
		em.remove(eMerged);
		
	}
	
	@Override
	public Appartement findByPrimaryKey(Integer id) {
		return em.find(Appartement.class, id);
	}

	@Override
	public Appartement update(Appartement entity) {
		return em.merge(entity);
	}


}
