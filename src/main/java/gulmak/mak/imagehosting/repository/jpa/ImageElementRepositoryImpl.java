package gulmak.mak.imagehosting.repository.jpa;

import gulmak.mak.imagehosting.domain.ImageElement;
import gulmak.mak.imagehosting.domain.User;
import gulmak.mak.imagehosting.repository.ImageElementRepository;
import gulmak.mak.imagehosting.service.ImageElementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Repository
public class ImageElementRepositoryImpl implements ImageElementRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public ImageElement findById(int id) throws DataAccessException {
        return entityManager.find(ImageElement.class, id);
    }

    @Override
    public List<ImageElement> findByIds(Set<Integer> ids) throws DataAccessException {
        Query query = this.entityManager.createQuery("SELECT img FROM ImageElement img WHERE img.id in :ids");
        query.setParameter("ids", ids);
        return query.getResultList();
    }

    @Override
    public List<ImageElement> findByUserId(int userId) throws DataAccessException {
        Query query = this.entityManager.createQuery("SELECT img FROM ImageElement img WHERE img.user.id = :userId");
        query.setParameter("userId", userId);
        return query.getResultList();
    }

    @Override
    public List<ImageElement> findThumbnailsByUserId(int userId) throws DataAccessException {
        Query query = this.entityManager.createQuery("SELECT img.name, img.thumbnail FROM ImageElement img WHERE img.user.id = :userId");
        query.setParameter("userId", userId);
        return query.getResultList();
    }

    @Override
    public List<ImageElement> findThumbnailsByGalleryId(int galleryId) throws DataAccessException {
        Query query = this.entityManager.createQuery("SELECT img.name, img.thumbnail FROM ImageElement img JOIN img.galleries gallery WHERE gallery.id = :galleryId");
        query.setParameter("galleryId", galleryId);
        return query.getResultList();
    }

    @Override
    public void save(ImageElement imageElement) throws DataAccessException {
            entityManager.persist(imageElement);
    }

    @Override
    public List<ImageElement> findAll() throws DataAccessException {
        Query query = this.entityManager.createQuery("SELECT images FROM ImageElement images");
        return query.getResultList();
    }

    @Override
    public void delete(int id) throws DataAccessException {
        entityManager.remove(entityManager.find(ImageElement.class, id));
    }
}
