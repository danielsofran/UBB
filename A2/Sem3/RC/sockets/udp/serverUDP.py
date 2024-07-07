import socket

HOST = "0.0.0.0"
PORT = 9779

with socket.socket(socket.AF_INET, socket.SOCK_DGRAM, 0) as sock:
    sock.bind((HOST, PORT))
    print("Server started")
    cnt = 0
    while True:
        length_b, addr = sock.recvfrom(4)
        length = int.from_bytes(length_b, 'big')
        sir, addr = sock.recvfrom(length)
        sir = sir.decode()

        ans = f"Client nr {cnt}!"
        sock.sendto(len(ans).to_bytes(4, 'big'), addr)
        sock.sendto(ans.encode(), addr)
        cnt += 1
        print(f"Clientul {addr[0]} a scris: {sir}")