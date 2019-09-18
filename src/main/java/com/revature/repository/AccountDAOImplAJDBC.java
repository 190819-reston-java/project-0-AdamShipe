package com.revature.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.revature.model.Account;
import com.revature.utilities.ConnectionUtility;
import com.revature.utilities.StreamCloser;

public class AccountDAOImplAJDBC implements AccountDAO {

	@Override
	public Account getAccount(int id) {
		Account account = null;

		try (Connection conn = ConnectionUtility.getConnection()) {
			String query = "SELECT * FROM accounts WHERE id=?;";
			try (PreparedStatement stmt = conn.prepareStatement(query)) {
				stmt.setLong(1, id);
				if (stmt.execute()) {
					try (ResultSet resultSet = stmt.getResultSet()) {
						if (resultSet.next()) {
							account = createAccountFromRS(resultSet);
						}
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return account;
	}

	@Override
	public Account getAccount(String userName) {
		ResultSet resultSet = null;

		PreparedStatement statement = null;

		Account account = null;

		try (Connection conn = ConnectionUtility.getConnection()) {
			statement = conn.prepareStatement("SELECT * FROM accounts WHERE user_name=?;");
			statement.setString(1, userName);

			if (statement.execute()) {
				resultSet = statement.getResultSet();
				if (resultSet.next()) {
					account = createAccountFromRS(resultSet);
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return account;
	}

	@Override
	public boolean newAccount(Account a) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateAccount(Account a) {
		Connection conn = null;
		PreparedStatement stmt = null;

		final String query = "UPDATE accounts SET user_name=?, user_password=?,"
				+ " first_name=?, last_name=?, email=?, balance=?, animation_pref=? WHERE id=?;";
		try {
			conn = ConnectionUtility.getConnection();
			stmt = conn.prepareStatement(query);
			stmt.setString(1, a.getUserName());
			stmt.setString(2, a.getPassword());
			stmt.setString(3, a.getFirstName());
			stmt.setString(4, a.getLastName());
			stmt.setString(5, a.getEmail());
			stmt.setDouble(6, a.getBalance());
			stmt.setBoolean(7, a.getAnimationPref());
			stmt.setInt(8, a.getId());

			stmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			StreamCloser.close(stmt);
			StreamCloser.close(conn);
		}

		return true;
	}

	@Override
	public boolean deleteAccount(Account a) {
		return false;
	}

	@Override
	public List<Account> getAccounts() {
		return null;
	}

	private Account createAccountFromRS(ResultSet resultSet) throws SQLException {
		return new Account(resultSet.getInt("id"), resultSet.getString("user_name"),
				resultSet.getString("user_password"), resultSet.getString("first_name"),
				resultSet.getString("last_name"), resultSet.getString("birth_date"), resultSet.getString("email"),
				resultSet.getDouble("balance"), resultSet.getBoolean("animation_pref"));
	}

}
