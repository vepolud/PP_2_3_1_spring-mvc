package web.dao;

import web.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

//    private SessionFactory sessionFactory;
    @PersistenceContext
    EntityManager entityManager;

//    @Autowired
//    UserDaoImp(EntityManager entityManager) {
//        this.entityManager = entityManager;
//    }

    @Override
    public void addUser(User user) {
        entityManager.persist(user);
    }

    @Override
    public void updateUser(User user) {
        User updatableUser = getUserById(user.getId());
        updatableUser.setName(user.getName());
        updatableUser.setEmail((user.getEmail()));
        entityManager.merge(updatableUser);
    }

    @Override
    public User getUserById(int id) {
        User user = entityManager.find(User.class, id);
        if (user == null) {
            throw new EntityNotFoundException("Can't find User for ID "
                    + id);
        }
        return user;
    }

    @Override
    public void removeUser(int id) {
        entityManager.remove(getUserById(id));
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> listUsers() {
        return entityManager.createQuery("from User", User.class).getResultList();
    }

//    @Override
//    public void add(User user) {
//        entityManager.persist(user);
//    }
//
//    @Override
//    public User getUser(String model, int series) {
//        String HQL = "from Car car LEFT OUTER JOIN FETCH car.user WHERE car.model =:CarModel AND car.series =:CarSeries";
//        Car car = entityManager.createQuery(HQL, Car.class).setParameter("CarModel", model).setParameter("CarSeries", series).getSingleResult();
//        return car.getUser();
//    }
//
//    @Override
//    public void addCar(Car car) {
//        entityManager.persist(car);
//    }
//
//    @Override
//    @SuppressWarnings("unchecked")
//    public List<User> listUsers() {
//        TypedQuery<User> query = entityManager.createQuery("from User", User.class);
//        return query.getResultList();
//    }
}
