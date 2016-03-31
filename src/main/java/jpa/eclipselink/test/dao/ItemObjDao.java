package jpa.eclipselink.test.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import jpa.eclipselink.test.domain.ItemObj;
import jpa.eclipselink.test.domain.UserObj;

@Stateless
public class ItemObjDao extends GenericEntityDao<ItemObj>
{
  public List<ItemObj> findByOwners(final UserObj user)
  {
    final EntityManager entityManager = getEntityManager();
    final CriteriaBuilder builder = entityManager.getCriteriaBuilder();
    final CriteriaQuery<ItemObj> criteriaQuery = builder
        .createQuery(ItemObj.class);
    final Root<ItemObj> root = criteriaQuery.from(ItemObj.class);

    return entityManager.createQuery(
        criteriaQuery.select(root).where(
            builder.isMember(user, root.<List<UserObj>>get("owners"))))
        .getResultList();
  }

  public List<ItemObj> findByParticipants(final UserObj user)
  {
    final EntityManager entityManager = getEntityManager();
    final CriteriaBuilder builder = entityManager.getCriteriaBuilder();
    final CriteriaQuery<ItemObj> criteriaQuery = builder
        .createQuery(ItemObj.class);
    final Root<ItemObj> root = criteriaQuery.from(ItemObj.class);

    return entityManager.createQuery(
        criteriaQuery.select(root).where(
            builder.isMember(user, root.<List<UserObj>>get("participants"))))
        .getResultList();
  }

  public List<ItemObj> findByUsers(final UserObj user)
  {
    final EntityManager entityManager = getEntityManager();
    final CriteriaBuilder builder = entityManager.getCriteriaBuilder();
    final CriteriaQuery<ItemObj> criteriaQuery = builder
        .createQuery(ItemObj.class);
    final Root<ItemObj> root = criteriaQuery.from(ItemObj.class);

    return entityManager.createQuery(
        criteriaQuery.select(root)
            .where(
                builder.or(
                    builder.isMember(user, root.<List<UserObj>>get("owners")),
                    builder.isMember(user,
                        root.<List<UserObj>>get("participants")))))
        .getResultList();
  }
}
