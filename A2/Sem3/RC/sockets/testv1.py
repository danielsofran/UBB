import socket
import select
import sys


def createMessage(message):
    if len(message) > 255:
        message = message[:255]
    elif len(message) < 255:
        message += (255-len(message))*'\0'
    message += '\0'
    return message.encode()


if len(sys.argv) < 4:
    print("Usage: python3 client.py <ip server> <port TCP/UDP> <port BROADCAST>")
    sys.exit(1)

HOST = sys.argv[1]
HOST_Broadcast = '0.0.0.0'
PORT = int(sys.argv[2])
PORT_Broadcast = int(sys.argv[3])

sockTCP = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
sockUDP = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
sockBroadcast = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
sockBroadcast.setsockopt(socket.SOL_SOCKET, socket.SO_BROADCAST, 1)
sockBroadcast.setsockopt(socket.SOL_SOCKET, socket.SO_REUSEADDR, 1)

sockTCP.connect((HOST, PORT))
sockBroadcast.bind((HOST_Broadcast, PORT_Broadcast))

print("Connected to server")

inputs = [sys.stdin, sockTCP, sockBroadcast]
outputs = []
errors = []


def getNrClients(nr_bytes=4, guaranteed=True, encoding='big'):
    sockUDP.sendto(createMessage('get'), (HOST, PORT))
    if guaranteed:
        data = b''
        while len(data) < nr_bytes:
            data += sockUDP.recv(nr_bytes - len(data))
        return int.from_bytes(data, encoding)
    else:
        data = sockUDP.recv(nr_bytes)
        return int.from_bytes(data, encoding)

def quitServer():
    sockTCP.close()
    sockBroadcast.close()
    sockUDP.close()
    print("Quitting...")
    sys.exit(0)


while True:
    readables, _, __ = select.select(inputs, outputs, errors)
    for readable in readables:
        if readable == sys.stdin:  # daca am primit input de la tastatura
            sir = sys.stdin.readline()[:-1]
            if sir == 'exit':  # daca am primit comanda de iesire
                quitServer()
            elif sir == 'get':  # daca am primit comanda de get
                nr = getNrClients()
                print(f"Numarul de clienti conectati este {nr}")
            else:  # daca am primit un mesaj, il trimit catre server
                sockTCP.send(createMessage(sir), 0)
        elif readable == sockTCP:  # daca am primit un mesaj de la server
            data = sockTCP.recv(276, 0)
            if not data:  # daca serverul a inchis conexiunea
                print("Serverul TCP a inchis conexiunea")
                quitServer()
            else:  # daca am primit un mesaj de la server
                print(data.decode()[:-1])
        elif readable == sockBroadcast:  # daca am primit un mesaj de la server prin broadcast
            data_b, addr = sockBroadcast.recvfrom(256)
            print("BROADCASTED: "+data_b.decode()[:-1])
