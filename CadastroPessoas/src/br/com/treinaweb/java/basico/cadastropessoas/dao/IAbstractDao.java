package br.com.treinaweb.java.basico.cadastropessoas.dao;

import java.sql.SQLException;
import java.util.List;

public interface IAbstractDao<T> {

	List<T> all() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException;

	T findById(int id) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException;

	void insert(T entidade) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException;

	void update(T entidade) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException;

	void delete(T entidade) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException;

}
