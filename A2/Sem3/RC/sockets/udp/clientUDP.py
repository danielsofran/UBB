import socket

HOST = "192.168.43.129"
PORT = 9779

with socket.socket(socket.AF_INET, socket.SOCK_DGRAM) as sock:
    for i in range(10 ** 5):
        sir = f"Client {i}"
        len_b = len(sir).to_bytes(4, 'big')
        sock.sendto(len_b, (HOST, PORT))
        sock.sendto(sir.encode(), (HOST, PORT))

        # astea is auxiliare
        len_b, addr_srv = sock.recvfrom(4)
        length = int.from_bytes(len_b, 'big')
        sir, addr_srv = sock.recvfrom(length)
        print(f"Raspuns de la server: {sir.decode()}")

