package Filter;

import java.util.List;

public interface Filter<T> {
    T apply(T data);
}
