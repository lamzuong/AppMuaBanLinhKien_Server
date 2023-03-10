package util;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class HibernateUtil {
	private static HibernateUtil instance;
	private EntityManager entityManager;

	public HibernateUtil() {
		entityManager = Persistence.createEntityManagerFactory("QLMuaBanLinhKien").createEntityManager();
	}

	public synchronized static HibernateUtil getInstance() {
		if (instance == null)
			instance = new HibernateUtil();
		return instance;
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}
}
