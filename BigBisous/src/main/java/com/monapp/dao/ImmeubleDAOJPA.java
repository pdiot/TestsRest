package com.monapp.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.monapp.entity.Immeuble;

@Transactional
@Repository
public class ImmeubleDAOJPA implements ImmeubleDAO {

	@PersistenceContext
	EntityManager em;

	@Override
	public List<Immeuble> findAll() {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Immeuble> crit = cb.createQuery(Immeuble.class);
		Root<Immeuble> r = crit.from(Immeuble.class);
		
		crit.select(r);
		
		return em.createQuery(crit).getResultList();
	}

	@Override
	public Immeuble save(Immeuble entity) {
		em.persist(entity);
		return entity;
	}

	@Override
	public void delete(Immeuble entity) {
		Immeuble eMerged = em.merge(entity);
		em.remove(eMerged);
		
	}
	
	@Override
	public Immeuble findByPrimaryKey(Integer id) {
		return em.find(Immeuble.class, id);
	}

	@Override
	public Immeuble update(Immeuble entity) {
		return em.merge(entity);
	}


}
