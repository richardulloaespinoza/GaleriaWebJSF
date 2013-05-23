package galeria.hibernate;

import java.util.List;

import galeria.model.Imagen;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.FlushModeType;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class HibernateHelper {

	protected EntityManager entityManager;

	public HibernateHelper() {
		super();
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("galeria");
		this.entityManager = emf.createEntityManager();
	}
	
	public String persist(Imagen imagen){
		this.entityManager.getTransaction().begin(); // Abre una transaccion
		this.entityManager.setFlushMode(FlushModeType.COMMIT);
		this.entityManager.persist(imagen); //Intenta crear en la BD
		this.entityManager.getTransaction().commit(); // Vuelca sobre la base de datos
		this.entityManager.close();
		return "persisted";
	}
	
	public String update(Imagen imagen){
		this.entityManager.getTransaction().begin(); // Abre una transaccion
		this.entityManager.setFlushMode(FlushModeType.COMMIT);
		this.entityManager.merge(imagen); //Intenta crear en la BD
		this.entityManager.getTransaction().commit(); // Vuelca sobre la base de datos
		this.entityManager.close();
		return "updated";
	}
	
	public String delete(Imagen imagen){
		this.entityManager.getTransaction().begin(); // Abre una transaccion
		this.entityManager.setFlushMode(FlushModeType.COMMIT);
		this.entityManager.remove(imagen); //Intenta crear en la BD
		this.entityManager.getTransaction().commit(); // Vuelca sobre la base de datos
		this.entityManager.close();
		return "deleted";
	}
	
	public List<Imagen> getListaImagenes(){
		StringBuffer sql = new StringBuffer("SELECT imagen FROM Imagen imagen");
		Query sqlQuery = this.entityManager.createQuery(sql.toString());
		return (List<Imagen>) sqlQuery.getResultList();
	}
}
