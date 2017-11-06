package gulmak.mak.imagehosting.repository;

import gulmak.mak.imagehosting.domain.ImageElement;
import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Set;

@Repository
public interface ImageElementRepository {

    ImageElement findById(int id) throws DataAccessException;

    List<ImageElement> findByIds(Set<Integer> ids) throws DataAccessException;

    List<ImageElement> findByUserId(int userId) throws DataAccessException;

    List<ImageElement> findThumbnailsByUserId(int userId) throws DataAccessException;

    List<ImageElement> findThumbnailsByGalleryId(int galleryId) throws DataAccessException;

    void save(ImageElement imageElement) throws DataAccessException;

    List<ImageElement> findAll() throws DataAccessException;

    void delete(int id) throws DataAccessException;

}
