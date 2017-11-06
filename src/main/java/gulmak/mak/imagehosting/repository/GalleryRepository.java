package gulmak.mak.imagehosting.repository;


import gulmak.mak.imagehosting.domain.Gallery;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface GalleryRepository {

    Gallery findById(int id) throws DataAccessException;

    List<Gallery> findByUserId(int userId) throws DataAccessException;

    Integer save(Gallery gallery) throws DataAccessException;

    void delete(int id) throws DataAccessException;
}
