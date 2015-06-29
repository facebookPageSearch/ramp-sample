/**
 * 
 */
package ramp.sample.emoji;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.stereotype.Repository;

/**
 * @author Rama Palaniappan
 * @since 29-Jun-2015
 */
@Repository("appDAO")
public class UsersDAO {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	private static final String SELECT_USER_BY_ID_QUERY = "select * from users where id=?";
	private static final String SELECT_USER_QUERY = "select id, email_id, avatar from users limit 100";
	private static final String INSERT_INTO_USER_QUERY = "insert into users (email_id, avatar) values(?, ?)";
	private static final String TRUNCATE_USER_QUERY = "truncate users";

	private final Log LOG = LogFactory.getLog(this.getClass());

	public User find(Integer id) {
		ParameterizedRowMapper<User> mapper = new UserMapper();
		User user = jdbcTemplate.queryForObject(SELECT_USER_BY_ID_QUERY,
				new Object[] { id }, mapper);
		return user;
	}

	public List<User> find() {
		ParameterizedRowMapper<User> mapper = new UserMapper();
		List<User> users = jdbcTemplate.query(SELECT_USER_QUERY, mapper);
		return users;
	}

	public void save(String emailId, String avatar) {
		int numberOfRecordsInserted = jdbcTemplate.update(
				INSERT_INTO_USER_QUERY, new Object[] { emailId, avatar });
		LOG.debug("Number of records inserted: " + numberOfRecordsInserted);
	}
	
	
	//Convenient method to truncate all data
	public void truncate() {
		jdbcTemplate.update(TRUNCATE_USER_QUERY);
	}

	class UserMapper implements ParameterizedRowMapper<User> {
		public User mapRow(ResultSet rs, int rowNum) throws SQLException {
			User user = new User();
			user.setId(rs.getInt("id"));
			user.setEmailId(rs.getString("email_id"));
			user.setAvatar(rs.getString("avatar"));
			return user;
		}
	}

}
