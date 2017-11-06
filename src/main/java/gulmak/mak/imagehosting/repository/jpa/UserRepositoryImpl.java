package gulmak.mak.imagehosting.repository.jpa;

import gulmak.mak.imagehosting.domain.User;
import gulmak.mak.imagehosting.repository.UserRepository;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Collection;
import java.util.List;

@Repository
public class UserRepositoryImpl implements UserRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public User findById(int id){
        return entityManager.find(User.class, id);
    }

    @Override
    public User findByLogin(String login){
        Query query = this.entityManager.createQuery("SELECT user FROM User user WHERE user.login LIKE :login");
        query.setParameter("login", login);
        return (User)query.getSingleResult();
    }

    @Override
    public User findByEmail(String email) throws DataAccessException {
        Query query = this.entityManager.createQuery("SELECT user FROM User user WHERE user.email LIKE :email");
        query.setParameter("email", email);
        return (User)query.getSingleResult();    }

    @Override
    public List<User> findAll(){
        Query query = this.entityManager.createQuery("SELECT users FROM User users");
        return query.getResultList();
    }

    @Override
    public Integer save(User user){
        if (user.getId() == null) {
            entityManager.persist(user);
        } else {
            entityManager.merge(user);
        }
        return user.getId();
    }

    @Override
    public void delete(int id){
        this.entityManager.remove(entityManager.find(User.class, id));
    }

}
