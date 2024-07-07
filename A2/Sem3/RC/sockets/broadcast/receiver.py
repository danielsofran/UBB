import socket

HOST = '0.0.0.0'
PORT = 9779

with socket.socket(socket.AF_INET, socket.SOCK_DGRAM) as sock:
    sock.bind((HOST, PORT))

    len_b, addr = sock.recvfrom(4)
    length = int.from_bytes(len_b, 'big')
    bsir, addr = sock.recvfrom(length)

    ans = f"OK"
    sock.sendto(len(ans).to_bytes(4, 'big'), addr)
    sock.sendto(ans.encode(), addr)
    print(f"Broadcasted: {bsir.decode()}")