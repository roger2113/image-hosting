package gulmak.mak.imagehosting.repository.jpa;

import gulmak.mak.imagehosting.domain.User;
import gulmak.mak.imagehosting.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public class UserRepositoryImpl implements UserRepository {

    private static final Logger logger = LoggerFactory.getLogger(UserRepositoryImpl.class);

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<User> findById(int id){
        return Optional.ofNullable(entityManager.find(User.class, id));
    }

    @Override
    public Optional<User> findByLogin(String login){
        Query query = this.entityManager.createQuery("SELECT user FROM User user WHERE user.login LIKE :login");
        query.setParameter("login", login);
        return Optional.ofNullable((User)query.getSingleResult());
    }

    @Override
    public Optional<User> findByEmail(String email) throws DataAccessException {
        Query query = this.entityManager.createQuery("SELECT user FROM User user WHERE user.email LIKE :email");
        query.setParameter("email", email);
        return Optional.ofNullable((User)query.getSingleResult());    }

    @Override
    public List<User> findAll(){
        Query query = this.entityManager.createQuery("SELECT users FROM User users");
        return query.getResultList();
    }

    @Override
    public Integer save(User user){
        if (user.getId() == null) {
            logger.info("Registering user: " + user);
            entityManager.persist(user);
        } else {
            logger.info("Updating user: " + user);
            entityManager.merge(user);
        }
        return user.getId();
    }

    @Override
    public void delete(int id){
        this.entityManager.remove(entityManager.find(User.class, id));
    }

}
