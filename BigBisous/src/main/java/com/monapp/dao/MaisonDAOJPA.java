package com.monapp.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.monapp.entity.Maison;

@Transactional
@Repository
public class MaisonDAOJPA implements MaisonDAO {

	@PersistenceContext
	EntityManager em;

	@Override
	public List<Maison> findAll() {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Maison> crit = cb.createQuery(Maison.class);
		Root<Maison> r = crit.from(Maison.class);
		
		crit.select(r);
		
		return em.createQuery(crit).getResultList();
	}

	@Override
	public Maison save(Maison entity) {
		em.persist(entity);
		return entity;
	}

	@Override
	public void delete(Maison entity) {
		Maison eMerged = em.merge(entity);
		em.remove(eMerged);
		
	}
	
	@Override
	public Maison findByPrimaryKey(Integer id) {
		return em.find(Maison.class, id);
	}

	@Override
	public Maison update(Maison entity) {
		return em.merge(entity);
	}


}
