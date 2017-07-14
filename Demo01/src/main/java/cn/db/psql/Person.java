package cn.db.psql;

import javax.persistence.*;

@Entity
@Table(name = "ts_person")
public class Person {

	@Column
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer userid;

	@Column
	private String username;

	@Column
	private String stype;

	@Column
	private Integer city_id;

	public Integer getUserid() {
		return userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getStype() {
		return stype;
	}

	public void setStype(String stype) {
		this.stype = stype;
	}

	public Integer getCity_id() {
		return city_id;
	}

	public void setCity_id(Integer city_id) {
		this.city_id = city_id;
	}

	public String toString() {
		return "[userid=" + userid + ",username=" + username + ",stype="
				+ stype + ",city_id=" + city_id + "]";
	}
}
