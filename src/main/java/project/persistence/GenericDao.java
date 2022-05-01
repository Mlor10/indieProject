package project.persistence;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.path.json.JsonPath;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Root;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * A generic DAO
 */
public class GenericDao<T> {
    private Class<T> type;
    private final Logger logger = LogManager.getLogger(this.getClass());

    /**
     * Instantiates a new Generic dao
     */
    public GenericDao(Class<T> type) {
        this.type = type;
    }

    /**
     * Returns an open session from the SessionFactory
     */
    private Session getSession() {
        return SessionFactoryProvider.getSessionFactory().openSession();
    }

    /**
     * Get entity id
     * @param id entity id
     * @return id
     */
    public <T>T getById(int id) {
        Session session = getSession();
        T entity = (T)session.get(type, id);
        session.close();
        return entity;
    }

    /**
     * Return a list of all entities
     * @return all users
     */
    public List<T> getAllEntities() {

        Session session = getSession();

        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<T> query = builder.createQuery(type);
        Root<T> root = query.from(type);
        List<T> entities = session.createQuery(query).getResultList();

        session.close();

        return entities;
    }

    /**
     * Delete a entity
     * @param entity entity to be deleted
     */
    public void delete(T entity) {
        Session session = getSession();
        Transaction transaction = session.beginTransaction();
        session.delete(entity);
        transaction.commit();
        session.close();
    }

    /**
     * insert entity
     * @param entity entity to be inserted
     * @return id of entity inserted
     */
    public int insert(T entity) {
        int id = 0;
        Session session = getSession();
        Transaction transaction = session.beginTransaction();
        id = (int)session.save(entity);
        transaction.commit();
        session.close();
        return id;
    }

    /**
     * update entity
     * @param entity to be updated
     */
    public void saveOrUpdate(T entity) {
        Session session = getSession();
        Transaction transaction = session.beginTransaction();
        session.saveOrUpdate(entity);
        transaction.commit();
        session.close();
    }

    /**
     * Get entities by property value and entered value
     * @param propertyName property name of entity
     * @param value search value
     * @return list of searched entities
     */
    public List<T> getByPropertyLike(String propertyName, String value) {
        Session session = getSession();

        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<T> query = builder.createQuery(type);
        Root<T> root = query.from(type);
        Expression<String> propertyPath = root.get(propertyName);

        query.where(builder.like(propertyPath, "%" + value + "%"));

        List<T> entities = session.createQuery(query).getResultList();
        session.close();
        return entities;
    }

    /**
     * Get entities by property value and entered value
     * @param propertyName property name of entity
     * @param value search value
     * @return list of searched entities
     */
    public List<T> getByPropertyEqual(String propertyName, String value) {
        Session session = getSession();

        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<T> query = builder.createQuery(type);
        Root<T> root = query.from(type);
        Expression<String> propertyPath = root.get(propertyName);

        query.where(builder.like(propertyPath, value));

        List<T> entities = session.createQuery(query).getResultList();
        session.close();
        return entities;
    }

    /**
     * Gets a list of api response objects
     * @param url api url path
     * @param param api param
     * @param paramValue api param value
     * @return list of api response objects
     */
    public List<T> getResponseWithParam(String url, String param, String paramValue) {
        List<T> entities = new ArrayList<>();
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(url + param + paramValue);
        String response = target.request(MediaType.APPLICATION_JSON).get(String.class);
        ObjectMapper mapper = new ObjectMapper();
        try {
            entities = mapper.reader().forType(new TypeReference<List<T>>() {
            }).readValue(response);
        } catch (JsonProcessingException jsonProcessingException) {
            logger.error("Json Processing Exception: ", jsonProcessingException);
        }
        return entities;
    }

    /**
     * Converts a list of api response objects to an array of json items
     * @param responseList list of api response objects
     * @return array of json items
     */
    public String createResponseJson(List<T> responseList) {
        String jsonResponse = null;
        ObjectMapper mapper = new ObjectMapper();
        try {
            jsonResponse = mapper.writeValueAsString(responseList);
        } catch (IOException exception) {
            logger.error("Exception: ", exception);
        }
        return jsonResponse;
    }

    /**
     * Grabs property values from a json object
     * @param jsonFormat json object
     * @param jsonProperty json property to retrieve
     * @return
     */
    public List<String> getJsonProperty(String jsonFormat, String jsonProperty) {
        List<String> entities = JsonPath.from(jsonFormat).get(jsonProperty);
        return entities;
    }
}
