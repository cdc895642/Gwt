package database;

import com.mySampleApplication.hibernate.db.tables.User;
import com.mySampleApplication.hibernate.util.HibernateUtil;
import com.mySampleApplication.security.PasswordUtils;
import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;
import org.junit.*;
import org.junit.runners.MethodSorters;

import static org.junit.Assert.*;

import java.util.List;

/**
 * test for initial operations with DB
 * Created by cdc89 on 11.12.2016.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class LocalHibernateTest {

    public static SessionFactory sessionFactory;
    public static Session session;

    @BeforeClass
    public static void openSession() {
        //sessionFactory = new Configuration().configure("test/config/hibernate.cfg.xml").buildSessionFactory();
        sessionFactory = HibernateUtil.getSessionFactory();
        session = sessionFactory.openSession();
    }

    @AfterClass
    public static void closeSession() {
        SQLQuery query = session.createSQLQuery("SHUTDOWN");
        query.executeUpdate();
        session.close();
        sessionFactory.close();
    }

    /**
     * test for initial operations with DB (create DB, table and insert into the table)
     * test passed successfully
     */
    @Ignore
    @Test
    public void test1_createDbStructure() {
        session.beginTransaction();
        User user = new User();
        user.setLogin("ivan");
        user.setName("Иван");
        String hashPassword = PasswordUtils.getHashPassword(user.getName(), "secret");
        user.setPassword(hashPassword);
        session.saveOrUpdate(user);
        user = new User();
        user.setLogin("john");
        user.setName("John");
        hashPassword = PasswordUtils.getHashPassword(user.getName(), "smith");
        user.setPassword(hashPassword);
        session.saveOrUpdate(user);
        session.getTransaction().commit();
        Criteria criteria = session.createCriteria(User.class);
        List<User> users = criteria.list();
        for (User u : users) {
            System.out.println(u.getName()+" - "+u.getPassword());
        }
        assertEquals(2, users.size());
    }

    /**
     * test for initial operations with DB (select data fromm user table)
     * test passed successfully
     */
    @Ignore
    @Test
    public void test2_getRecordTest() {
        Criteria criteria = session.createCriteria(User.class);
        criteria.add(Restrictions.sqlRestriction("name = 'Иван'"));
        List<User> users = criteria.list();
        assertEquals("Иван", users.get(0).getName());
        assertEquals("ivan", users.get(0).getLogin());
        boolean checkPassword=PasswordUtils.checkPassword("Иван", "secret",users.get(0).getPassword());
        assertTrue(checkPassword);
    }
}
