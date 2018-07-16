package repository;

import model.Item;

import javax.persistence.*;
import java.util.List;

public class ItemRepository {

    private static EntityManagerFactory factory;

    private static EntityManager manager;

    public ItemRepository(){
        factory = Persistence.createEntityManagerFactory("item");
        manager = factory.createEntityManager();
    }

    public Item findByUrl(String url){
        Query query = manager.createQuery("SELECT i FROM Item AS i WHERE i.url = :url");
        query.setParameter("url", url);
        return ((List<Item>) query.getResultList()).get(0);
    }

    public List<Item> findAll(){
        Query query = manager.createQuery("SELECT i FROM Item AS i");
        return (List<Item>) query.getResultList();
    }

    public void save(Item item){
        manager.getTransaction().begin();
        manager.persist(item);
        manager.getTransaction().commit();
    }

    public void update(Item item){
        manager.getTransaction().begin();
        manager.merge(item);
        manager.getTransaction().commit();
    }

    public void saveAll(List<Item> items){
        for(Item item : items) {
            try {
                save(item);
            } catch (RollbackException e){
                item.setId(findByUrl(item.getUrl()).getId());
                update(item);
            }
        }
    }

}
