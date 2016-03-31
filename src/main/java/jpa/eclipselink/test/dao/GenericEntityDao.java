package jpa.eclipselink.test.dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import jpa.eclipselink.test.domain.GenericEntity;

@Stateless
public class GenericEntityDao<T extends GenericEntity>
{
  /**
   * Automatically injected {@link javax.persistence.EntityManager}.
   */
  @PersistenceContext(unitName = "persistenceUnit")
  private EntityManager entityManager;

  public void clear()
  {
    entityManager.clear();
  }

  public T create(final T entity)
  {
    entityManager.persist(entity);
    return entity;
  }

  /**
   * @see EntityManager#createNamedQuery(String)
   */
  protected Query createNamedQuery(final String name)
  {
    return entityManager.createNamedQuery(name);
  }

  /**
   * @see EntityManager#createQuery(CriteriaQuery)
   */
  protected TypedQuery<T> createQuery(final CriteriaQuery<T> criteriaQuery)
  {
    return entityManager.createQuery(criteriaQuery);
  }

  public T delete(final T entity)
  {
    entityManager.remove(update(entity));
    return entity;
  }

  public T findByPredicate(final Root<T> root, final CriteriaQuery<T> query,
      final Predicate where)
  {

    return entityManager.createQuery(query.select(root).where(where))
        .getSingleResult();
  }

  public void flush()
  {
    entityManager.flush();
  }

  /**
   * Get the automatically injected {@link javax.persistence.EntityManager}.
   */
  protected EntityManager getEntityManager()
  {
    return entityManager;
  }

  public T refresh(final T entity)
  {
    entityManager.refresh(entity);
    return entity;
  }

  public T update(final T entity)
  {
    return entityManager.merge(entity);
  }
}
