import socket

ip='172.30.113.57'
port=9779

sock = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
sock.connect((ip, port))

sir = "Python conectat"
sock.send(len(sir).to_bytes(4, 'big'))
sock.send(sir.encode())
sock.close()
