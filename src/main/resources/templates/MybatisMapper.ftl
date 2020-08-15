package ${configInfo.packageDao};


public interface MybatisMapper<T> {

    int insert(T t);

    int update(T t);

    int delete(Object t);

    T find(Object id);
}
