export const customSocket = (onMessage: (data: any) => void, onError = (error) => {}) => {
  const socket = new WebSocket("ws://localhost:3000");
  socket.onopen = () => {
    console.log("Connected to WS server");
  }
  socket.onclose = () => {
    console.log("Disconnected from WS server");
  }
  socket.onerror = (error) => {
    console.log("WS Error: ", error);
    onError(error);
  }
  socket.onmessage = (message) => {
    console.log("Message received: ", message.data);
    const data: any = JSON.parse(message.data);
    onMessage(data);
  }
  return () => {
    socket.close();
  }
}