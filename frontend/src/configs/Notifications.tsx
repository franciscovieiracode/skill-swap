import { useContext, useEffect } from "react";
import { Client } from "@stomp/stompjs";
import { AuthContext } from "../context/AuthContext";
import toast from "react-hot-toast";

interface NotificationPayload {
  from?: string;
  message: string;
  skillId?: string;
}

export default function Notifications() {
  const auth = useContext(AuthContext);
  if (!auth) return null;
  const { user, loading } = auth;
  console.log(user);
  

  useEffect(() => {
    if (loading || !user) return;

const stompClient = new Client({
  brokerURL: "ws://localhost:8080/ws",
  reconnectDelay: 5000,
  debug: (str) => console.log(str),
});

    stompClient.onConnect = () => {
      console.log("Connected to WebSocket");

      stompClient.subscribe(`/topic/notifications/${user.email}`, (message) => {
        let payload: NotificationPayload;
        try {
          payload = JSON.parse(message.body);
        } catch {
          payload = { message: message.body };
        }
        toast(payload.message); // ✅ Show toast instead of blocking UI
      });
    };

    stompClient.activate();

    return () => {
      stompClient.deactivate(); // ✅ No async return
    };
  }, [loading, user]);

  return null; // ✅ No UI needed since toast handles notifications
}
