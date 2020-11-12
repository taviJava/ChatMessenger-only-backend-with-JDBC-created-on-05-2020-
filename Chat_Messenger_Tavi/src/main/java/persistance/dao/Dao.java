package persistance.dao;

import java.util.List;

public interface Dao <T> {

    public void add(T object);
    public void remove(int id);
    public List<T>getAll(T object);
    public void update(T object);


}
