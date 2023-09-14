package controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import model.ListGame; // Imports the ListGame class

public class ListGameHelper {
	// Creates EntityManager
    static EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("GameList");

    // Inserts game into database
    public void insertGame(ListGame li) {
        EntityManager em = emfactory.createEntityManager();
        em.getTransaction().begin();
        em.persist(li);
        em.getTransaction().commit();
        em.close();
    }
    
    // Prints all games in database 
    public List<ListGame> showAllGames() {
        EntityManager em = emfactory.createEntityManager();
        String jpql = "SELECT i FROM ListGame i"; // JPQL query to select all games
        TypedQuery<ListGame> query = em.createQuery(jpql, ListGame.class);
        List<ListGame> allGames = query.getResultList();
        em.close(); // Close the EntityManager when done
        return allGames;
    }
    
    // Deletes all games from database
    public void deleteGame(ListGame toDelete) {
        EntityManager em = emfactory.createEntityManager();
        em.getTransaction().begin();

        TypedQuery<ListGame> typedQuery = em.createQuery(
            "SELECT li FROM ListGame li WHERE li.title = :selectedTitle AND li.publisher = :selectedPublisher",
            ListGame.class
        );

        // Substitutes parameter with actual data from the toDelete game
        typedQuery.setParameter("selectedTitle", toDelete.getTitle());
        typedQuery.setParameter("selectedPublisher", toDelete.getPublisher());

        // We only want one result, but handle the case where no result is found
        try {
            typedQuery.setMaxResults(1);
            ListGame result = typedQuery.getSingleResult();

            // Remove it
            em.remove(result);
        } catch (NoResultException e) {
            // Handle the case where no matching entity is found
            System.out.println("No matching entity found for deletion.");
        }

        em.getTransaction().commit();
        em.close();
    }

    
    
// searches for game by title
    public List<ListGame> searchForGameByTitle(String titleName) {
        EntityManager em = emfactory.createEntityManager();
        em.getTransaction().begin();
        TypedQuery<ListGame> typedQuery = em.createQuery("select li from ListGame li where li.title = :selectedTitle", ListGame.class);
        typedQuery.setParameter("selectedTitle", titleName);
        List<ListGame> foundGames = typedQuery.getResultList();
        em.close();
        return foundGames;
    }
    
// searches for game by publisher
    public List<ListGame> searchForGameByPublisher(String publisherName) {
        EntityManager em = emfactory.createEntityManager();
        em.getTransaction().begin();
        TypedQuery<ListGame> typedQuery = em.createQuery("select li from ListGame li where li.publisher = :selectedPublisher", ListGame.class);
        typedQuery.setParameter("selectedPublisher", publisherName);
        List<ListGame> foundGames = typedQuery.getResultList();
        em.close();
        return foundGames;
    }
    
    // searches for game by ID
    public ListGame searchForGameById(int idToEdit) {
        EntityManager em = emfactory.createEntityManager();
        em.getTransaction().begin();
        ListGame found = em.find(ListGame.class, idToEdit);
        em.close();
        return found;
    }
    
    // updates the game in the database
    public void updateGame(ListGame toEdit) {
        EntityManager em = emfactory.createEntityManager();
        em.getTransaction().begin();
        em.merge(toEdit);
        em.getTransaction().commit();
        em.close();
    }
    
    // closes the menu
    public void cleanUp() {
        emfactory.close();
    }

}