package cn.db.psql;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

public class PersonSpecification implements Specification<Person> {
	private String _username;
	private String _stype;
	private Integer _cityId;

	public PersonSpecification(String username, String stype, Integer cityId) {
		this._username = username;
		this._stype = stype;
		this._cityId = cityId;
	}

	public Predicate toPredicate(Root<Person> root, CriteriaQuery<?> query,
			CriteriaBuilder cb) {

		Path<String> username = root.get("username");

		Path<String> stype = root.get("stype");
		Path<Integer> city_id = root.get("city_id");

		List<Predicate> list = new ArrayList<Predicate>();

		if (null != _username && _username.trim().length() > 0) {
			list.add(cb.like(username.as(String.class), "%" + _username + "%"));
		}
		if (null != _stype && _stype.trim().length() > 0) {
			list.add(cb.like(stype.as(String.class), "%" + _stype + "%"));
		}
		if (_cityId > 0) {
			list.add(cb.equal(city_id.as(Integer.class), _cityId));
		}
		Predicate[] parray = new Predicate[list.size()];

		return cb.and(list.toArray(parray));

	}

	public String get_username() {
		return _username;
	}

	public void set_username(String _username) {
		this._username = _username;
	}

	public String get_stype() {
		return _stype;
	}

	public void set_stype(String _stype) {
		this._stype = _stype;
	}

	public Integer get_cityId() {
		return _cityId;
	}

	public void set_cityId(Integer _cityId) {
		this._cityId = _cityId;
	}
}
