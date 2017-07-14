package cn.db.psql;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * 复杂条件的查询
 */
@Repository("personJpaSpecificationExecutor")
public interface PersonJpaSpecificationExecutor extends
		JpaRepository<Person, Integer>, JpaSpecificationExecutor<Person> {
	Page<Person> findAll(Specification<Person> spec, Pageable pageable); // 分页按条件查询

	List<Person> findAll(Specification<Person> spec);

}
