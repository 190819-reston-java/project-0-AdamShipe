package com.revature.repository;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import com.revature.model.Transaction;
import com.revature.service.AccountService;
import com.revature.utilities.ConnectionUtility;
import com.revature.utilities.StreamCloser;

public class TransactionDAOImplAJDBC implements TransactionDAO {

	@Override
	public List<Transaction> getTransactions() {
		PreparedStatement stmt = null;
		ResultSet resultSet = null;
		Connection conn = null;
		
		final String query = "SELECT * FROM transactions WHERE account_id=? ORDER BY date_time DESC;";

		List<Transaction> transactions = new ArrayList<Transaction>();

		try {
			conn = ConnectionUtility.getConnection();
			stmt = conn.prepareStatement(query);
			stmt.setInt(1, AccountService.selectedAccount.getId());
			stmt.execute();
			resultSet=stmt.getResultSet();

			while (resultSet.next()) {
				transactions.add(createTransactionFromRS(resultSet));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			StreamCloser.close(resultSet);
			StreamCloser.close(stmt);
			StreamCloser.close(conn);
		}
		return transactions;
	}

	@Override
	public boolean newTransaction(Transaction t) {
		return false;
	}

	private Transaction createTransactionFromRS(ResultSet resultSet) throws SQLException {
		return new Transaction(resultSet.getInt("id"), resultSet.getInt("account_id"),
				resultSet.getString("short_desc"), resultSet.getString("detail_desc"), resultSet.getString("date_time"),
				resultSet.getDouble("value"));
	}

	public static boolean onlineTransaction(int ua, String sd, String dd, double d) {
		Connection conn = null;
		PreparedStatement stmt = null;

		String query = "INSERT INTO transactions VALUES (DEFAULT, ?, ?, ?, (current_timestamp), ?);";

		try {
			conn = ConnectionUtility.getConnection();
			stmt = conn.prepareStatement(query);
			stmt.setInt(1, ua);
			stmt.setString(2, sd);
			stmt.setString(3, dd);
//			stmt.setString(4, t.getDateTime());
			stmt.setDouble(4, d);
			stmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			StreamCloser.close(stmt);
			StreamCloser.close(conn);
		}

		return false;
	}
	
}
