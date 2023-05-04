package ImportacioDAO;

public interface DAO<T> {
//CRUD
    void create(T t);
    void read(T t);
    void update(T t);
    void delete(T t);
}
