import socket

client = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
client.connect(("127.0.0.1",9090))
data = "Hello.txt"
client.send(data.encode())
with open("fileFromServer.txt", 'wb') as f:
    print('File opened, writing data to file...')
    while True:
        data = client.recv(1024)
        print(data)
        if not data:
            f.close()
            print('File closed')
            break
        f.write(data)
    print("Done")

print('Successfully got the file')
client.close()
print('connection closed')