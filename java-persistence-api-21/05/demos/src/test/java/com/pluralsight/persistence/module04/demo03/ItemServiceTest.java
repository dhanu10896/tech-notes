package com.pluralsight.persistence.module04.demo03;

import com.pluralsight.persistence.module04.Book;
import com.pluralsight.persistence.module04.CD;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import static org.junit.Assert.assertNotNull;

/**
 * @author Antonio Goncalves
 *         http://www.antoniogoncalves.org
 *         --
 */
public class ItemServiceTest {

  // ======================================
  // =             Attributes             =
  // ======================================

  private static EntityManagerFactory emf;
  private static EntityManager em;
  private static EntityTransaction tx;

  // ======================================
  // =          Lifecycle Methods         =
  // ======================================

  @Before
  public void initEntityManager() {
    emf = Persistence.createEntityManagerFactory("module04-persistence-unit");
    em = emf.createEntityManager();
    tx = em.getTransaction();
  }

  @After
  public void closeEntityManager() {
    if (em != null) em.close();
    if (emf != null) emf.close();
  }

  // ======================================
  // =              Methods               =
  // ======================================

  @Test
  public void shouldManageItem() {

    ItemService service = new ItemService(em);

    // Creates and persists a CD
    tx.begin();
    CD cd = new CD("Sergent Pepper", "Pop/Rock");
    cd = service.createCD(cd);
    tx.commit();
    assertNotNull(cd);

    // Creates and persists a Book
    tx.begin();
    Book book = new Book("H2G2", "1234-4567-5678");
    book = service.createBook(book);
    tx.commit();
    assertNotNull(book);
  }
}