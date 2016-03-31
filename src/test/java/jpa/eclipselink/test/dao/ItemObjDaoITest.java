package jpa.eclipselink.test.dao;

import java.util.Collection;
import java.util.LinkedList;

import javax.ejb.EJB;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import jpa.eclipselink.test.domain.ItemObj;
import jpa.eclipselink.test.domain.UserObj;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(Arquillian.class)
public class ItemObjDaoITest
{
  @Deployment
  public static JavaArchive createDeployment()
  {
    return ShrinkWrap.create(JavaArchive.class)
        .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
        .addPackages(true, new String[] { "jpa.eclipselink.test" });
  }

  @PersistenceContext
  protected EntityManager entityManager;

  @EJB
  protected ItemObjDao    itemObjDao;

  @EJB
  protected UserObjDao    userObjDao;

  @Test
  public void testByOwners()
  {
    final UserObj user1 = userObjDao.create(new UserObj("user1"));
    Assert.assertNotNull(user1);

    final ItemObj itemObj = itemObjDao.create(new ItemObj("item1", user1));
    Assert.assertNotNull(itemObj);
    Assert.assertNotNull(itemObj.getSummary());

    final Collection<ItemObj> result = itemObjDao.findByOwners(user1);

    Assert.assertNotNull(result);
    Assert.assertEquals(1, result.size());
  }

  @Test
  public void testByParticipants()
  {
    final UserObj user2 = userObjDao.create(new UserObj("user2"));
    Assert.assertNotNull(user2);

    final ItemObj itemObj = itemObjDao.create(new ItemObj("item2",
        new LinkedList<UserObj>(), user2));
    Assert.assertNotNull(itemObj);
    Assert.assertNotNull(itemObj.getSummary());

    final Collection<ItemObj> result = itemObjDao.findByParticipants(user2);

    Assert.assertNotNull(result);
    Assert.assertEquals(1, result.size());
  }

  @Test
  public void testByUsers()
  {
    final UserObj user3 = userObjDao.create(new UserObj("user3"));
    Assert.assertNotNull(user3);

    // user3 will be the owner of ItemObj item3
    final ItemObj itemObj = itemObjDao.create(new ItemObj("item3", user3));
    Assert.assertNotNull(itemObj);
    Assert.assertNotNull(itemObj.getSummary());

    final Collection<ItemObj> result = itemObjDao.findByUsers(user3);

    Assert.assertNotNull(result);
    Assert.assertEquals(1, result.size());
  }
}
