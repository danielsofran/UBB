import socket

HOST = "0.0.0.0"
PORT = 9779
BACKLOG = 5

sock = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
sock.bind((HOST, PORT))
sock.listen(BACKLOG)

while True:
    conn, addr = sock.accept()
    length_b = conn.recv(4)  # primim un int
    length = int.from_bytes(length_b, 'big')
    str_b = conn.recv(length)
    print(str_b.decode())
    conn.close()