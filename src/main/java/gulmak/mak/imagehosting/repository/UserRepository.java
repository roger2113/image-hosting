package gulmak.mak.imagehosting.repository;

import gulmak.mak.imagehosting.domain.User;
import org.springframework.dao.DataAccessException;

import java.util.Collection;
import java.util.List;

public interface UserRepository {

    User findById(int id) throws DataAccessException;

    User findByLogin(String login) throws DataAccessException;

    User findByEmail(String email) throws DataAccessException;

    Integer save(User user) throws DataAccessException;

    List<User> findAll() throws DataAccessException;

    void delete(int id) throws DataAccessException;
}
