package cn.db.psql;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * 简单的查询
 */
@Repository("personRepository")
public interface PersonRepository extends JpaRepository<Person, Integer> {
	@Query("select a from Person a where a.userid = :userid")
	public Person findByUserid(@Param("userid") Integer userid);

	@Query("select a from Person a where a.userid = :userid And username like  %:username%")
	public List<Person> findByCondition(@Param("userid") Integer userid,
			@Param("username") String username);

	@Query("select a from Person a where  username like  %:username%")
	public List<Person> findByUserName(@Param("username") String username);
}
