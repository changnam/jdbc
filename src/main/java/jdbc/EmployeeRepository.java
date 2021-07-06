package jdbc;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Long>{
    /** No need to define findAll() here, because
     *  inherited from JpaRepository with many other basic JPA operations.**/
    //public List<Employee> findAll();

    /** spring-jpa-data understands this method name,
     *  because it supports the resolution of specific keywords inside method names. **/
    public List<Employee> findByNameContainingIgnoreCase(String searchString);

    /** You can define a JPA query.**/
    @Query("select p from Employee p where p.name = :name")
    public List<Employee> findByNameIs(@Param("name") String name);
}
