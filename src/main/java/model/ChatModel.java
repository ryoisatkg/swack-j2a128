package model;

import java.util.ArrayList;
import java.util.List;

import bean.ChatLog;
import bean.Room;
import dao.ChatDAO;
import exception.SwackException;

/**
 * チャット機能を実行するクラス
 */
public class ChatModel {

	public Room getRoom(String roomId, String userId) throws SwackException {
		return new ChatDAO().getRoom(roomId, userId);
	}

	public ArrayList<Room> getRoomList(String userId) throws SwackException {
		return new ChatDAO().getRoomList(userId);
	}

	public ArrayList<Room> getDirectList(String userId) throws SwackException {
		return new ChatDAO().getDirectList(userId);
	}

	public List<ChatLog> getChatlogList(String roomId) throws SwackException {
		return new ChatDAO().getChatlogList(roomId);
	}
	public void saveChatLog(String roomId, String userId, String message) throws SwackException {
		new ChatDAO().saveChatlog(roomId, userId, message);
	}

}