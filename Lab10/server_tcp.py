import socket
import os
server = socket.socket(socket.AF_INET, socket.SOCK_STREAM)

server.bind(("127.0.0.1", 9090))

while(True):
    server.listen(5)
    (clientConnected, clientAddress) = server.accept()
    print("Accepted a connection request from %s:%s"%(clientAddress[0], clientAddress[1]))
    dataFromClient = clientConnected.recv(1024)
    filename = dataFromClient.decode()
    print(filename)
    if filename in os.listdir():
        f = open(filename, "rb")
        while True:
            l = f.read(1024)
            while l:
                print(l)
                clientConnected.send(l)
                l = f.read(1024)
            if not l:
                f.close()
                clientConnected.close()
                break

server.close()