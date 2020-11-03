package com.codehub.repository.lib;

import java.util.List;
import java.util.Optional;

public interface IRepository<T, K> {

      Optional<T> findById(K id) ;

      boolean deleteById(K id);
  //    boolean updateById(K id);
      List<T> findAll() ;
      Optional<T> save(T t) ;
      Optional<T> findByName(String name);

}
