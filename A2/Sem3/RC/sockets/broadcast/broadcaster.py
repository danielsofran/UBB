import socket

HOST_BRD = '192.168.43.255'
PORT = 9779

with socket.socket(socket.AF_INET, socket.SOCK_DGRAM) as sock:
    sock.setsockopt(socket.SOL_SOCKET, socket.SO_BROADCAST, 1)

    sir = input("trm la toti: ")
    sock.sendto(len(sir).to_bytes(4, 'big'), (HOST_BRD, PORT))
    sock.sendto(sir.encode(), (HOST_BRD, PORT))

    while True:
        response, addr = sock.recvfrom(4)
        length = int.from_bytes(response, 'big')
        sir_b, addr = sock.recvfrom(length)
        sir = sir_b.decode()
        print(f"{addr[0]}: {sir}")