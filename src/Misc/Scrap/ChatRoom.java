package Misc.Scrap;

import java.util.ArrayList;

/**
 * @author Jacob Swineford
 */
public class ChatRoom {

    private ArrayList<String> log;
    private ArrayList<String> connectedUsers;
    private String owner;
    private String roomName;

    ChatRoom(String owner, String name) {
        log = new ArrayList<>();
        connectedUsers = new ArrayList<>();
        this.owner = owner;
        roomName = name;
    }

    public ArrayList<String> getLog() {
        return log;
    }

    public String getOwner() {
        return owner;
    }

    public String getRoomName() {
        return roomName;
    }

    public void chat(String message) {
        log.add(message);
    }

    public ArrayList<String> getConnectedUsers() {
        return connectedUsers;
    }

    public void connect(String name) {
        connectedUsers.add(name);
    }

    public void disconnect(String name) {
        connectedUsers.remove(name);
    }

//    /**
//     * Prints the given message to everyone in the poster's chat room.
//     * @param message given message
//     */
//    private static void printToChatRoom(String message, String originName) {
//        String roomName = findRoomName(originName);
//        ChatRoom pcr;
//        for (ChatRoom c: chatRooms) {
//            if (roomName.equals(c.getRoomName())) {
//                pcr = c;
//                break;
//            }
//        }
//
////        for (Socket s : clientMap.values()) {
////            if (pcr.getConnectedUsers().contains(s))
////            try {
////                PrintWriter output = new PrintWriter(s.getOutputStream(), true);
////                output.println(originName + " [" + roomName + "]: " + message);
////            } catch (IOException e) {
////                e.printStackTrace();
////            }
////
////        }
//    }
//
//    /**
//     * Finds the name of the chat room that the current client is in.
//     */
//    private static String findRoomName(String name) {
//        for (ChatRoom c : chatRooms) {
//            if (c.getConnectedUsers().contains(name))
//                return c.getRoomName();
//        }
//        return "";
//    }
}
