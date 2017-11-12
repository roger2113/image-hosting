package gulmak.mak.imagehosting.repository.jpa;

import gulmak.mak.imagehosting.domain.Gallery;
import gulmak.mak.imagehosting.repository.GalleryRepository;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class GalleryRepositoryImpl implements GalleryRepository {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public Gallery findById(int id) throws DataAccessException {
        return entityManager.find(Gallery.class, id);
    }

    @Override
    public List<Gallery> findByUserId(int userId) throws DataAccessException {
        Query query = this.entityManager.createQuery("SELECT gal FROM Gallery gal WHERE gal.user.id = :userId");
        query.setParameter("userId", userId);
        return query.getResultList();
    }

    @Override
    public Integer save(Gallery gallery) throws DataAccessException {
        if(gallery.getId() == null) entityManager.persist(gallery);
        else entityManager.merge(gallery);
        return gallery.getId();
    }

    @Override
    public void delete(int id) throws DataAccessException {
        entityManager.remove(entityManager.find(Gallery.class, id));
    }
}
