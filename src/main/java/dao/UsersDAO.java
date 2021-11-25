package dao;

import static parameter.DAOParameters.*;
import static parameter.Messages.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import bean.User;
import exception.SwackException;

public class UsersDAO {
	public User select(String mailAddress, String password) throws SwackException {
		String sql = "SELECT USERID, USERNAME FROM USERS WHERE MAILADDRESS = ? AND PASSWORD = ?";
		User user = null;

		try (Connection conn = DriverManager.getConnection(DB_ENDPOINT, DB_USERID, DB_PASSWORD)) {
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, mailAddress);
			pStmt.setString(2, password);

			ResultSet rs = pStmt.executeQuery();
			if (rs.next()) {
				String userId = rs.getString("USERID");
				String userName = rs.getString("USERNAME");
				// mask password
				user = new User(userId, userName, mailAddress, "********");
			}

		} catch (SQLException e) {
			throw new SwackException(ERR_DB_PROCESS, e);
		}
		return user;
	}


	public boolean exists(String mailAddress) throws SwackException {
		String sql = "SELECT * FROM USERS WHERE MAILADDRESS = ?";

		try (Connection conn = DriverManager.getConnection(DB_ENDPOINT, DB_USERID, DB_PASSWORD)) {
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, mailAddress);

			ResultSet rs = pStmt.executeQuery();
			if (rs.next()) {
				// exists
				return true;
			}
		} catch (SQLException e) {
			throw new SwackException(ERR_DB_PROCESS, e);
		}
		return false;
	}


	 public String selectMaxUserId() throws SwackException {
			String sql = "SELECT MAX(USERID) AS MAX_USERID FROM USERS";

			String maxUserId = null;

			try (Connection conn = DriverManager.getConnection(DB_ENDPOINT, DB_USERID, DB_PASSWORD)) {
				PreparedStatement pStmt = conn.prepareStatement(sql);

				ResultSet rs = pStmt.executeQuery();
				if (rs.next()) {
					maxUserId = rs.getString("MAX_USERID");
				}

			} catch (SQLException e) {
				throw new SwackException(ERR_DB_PROCESS, e);
			}

			return maxUserId;

		}
	 
	 public boolean insert(User user) throws SwackException {
			String sqlUserAdd = "INSERT INTO USERS(USERID, USERNAME, MAILADDRESS, PASSWORD) VALUES(?, ?, ?, ?)";
			String sqlJoinRoom = "INSERT INTO JOINROOM(ROOMID, USERID) VALUES('R0000', ?)";

			try (Connection conn = DriverManager.getConnection(DB_ENDPOINT, DB_USERID, DB_PASSWORD)) {
				PreparedStatement pStmt = conn.prepareStatement(sqlUserAdd);
				pStmt.setString(1, user.getUserId());
				pStmt.setString(2, user.getUserName());
				pStmt.setString(3, user.getMailAddress());
				pStmt.setString(4, user.getPassword());

				int result = pStmt.executeUpdate();
				if (result != 1) {
					return false;
				}

				PreparedStatement pStmt2 = conn.prepareStatement(sqlJoinRoom);
				pStmt2.setString(1, user.getUserId());

				int result2 = pStmt2.executeUpdate();
				if (result2 != 1) {
					return false;
				}

			} catch (SQLException e) {
				throw new SwackException(ERR_DB_PROCESS, e);
			}

			return true;

		}

	 
}