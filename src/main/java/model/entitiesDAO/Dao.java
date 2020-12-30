package model.entitiesDAO;

import java.util.List;

public interface Dao<T> {
	
	public boolean insert(T obj);
	public boolean update(T obj);
	public List<T> findAll();
	public boolean delete(T obj);

}
