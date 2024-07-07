import sys

import select
import socket

ip = '192.168.43.129'
port = 9700

sock = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
# sock.setsockopt(socket.SOL_SOCKET, socket.SO_REUSEADDR, 1) - doar pe server
sock.connect((ip, port))

inputs = [sys.stdin, sock]
outputs = []
errors = []

while True:
    readables, _, __ = select.select(inputs, [], [])
    for readable in readables:
        if readable == sys.stdin:
            sir = sys.stdin.readline()[:-1]
            sir += (256-len(sir))*'\0'
            sock.send(sir.encode(), 0)
        elif readable == sock:
            data = sock.recv(256, 0)
            if not data:
                print("Serverul a inchis conexiunea")
                sock.close()
                sys.exit(0)
            print(data.decode())

