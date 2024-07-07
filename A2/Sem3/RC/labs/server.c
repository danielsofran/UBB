// standard
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <string.h>

#include <arpa/inet.h>
#include <netinet/in.h>
#include <netinet/ip.h>

#include <sys/types.h>
#include <sys/socket.h>

#include <signal.h>

int BACKLOG = 5;
#define LGMAX 256

uint16_t get_from_arg(int argc, char** argv){
    if(argc < 2) {
        perror("Introduceti: [port]!");
        return 1;
    }

    uint16_t port = -1;
    sscanf(argv[1], "%hu", &port);

        if(argc >=3 ) {
                sscanf(argv[2], "%u",&BACKLOG);
        }
        return port;
}

int CreateSocket(int port)
{
    int sock;
    struct sockaddr_in server;

    sock = socket(AF_INET, SOCK_STREAM, 0);
    if(sock < 0) {
        perror("Eroare la crearea socketului server\n");
        exit(1);
    }

    memset(&server, 0, sizeof(server));
    server.sin_port = htons(port);
    server.sin_family = AF_INET;
    server.sin_addr.s_addr = INADDR_ANY; // orice adresa
                                                                                 // ip pt adresa unui client specific
        int bres = bind(sock, (struct sockaddr*) &server, sizeof(server));
        if(bres < 0){
                perror("Eroare la bind\n");
                exit(1);
        }

        listen(sock, BACKLOG);

    return sock;
}

void processClient(int client_sock){
        int lg;
        int res = recv(client_sock, &lg, sizeof(int), MSG_WAITALL);
        if(res < 0){
                perror("Can't receive length from client!");
                close(client_sock);
        }
        printf("Received %u\nntohl: %u\n", lg, ntohl(lg));
        lg = ntohl(lg);

        char sir[LGMAX];
        res = recv(client_sock, sir, lg, MSG_WAITALL);
        sir[lg] = '\0';
        printf("Client said: %s\n", sir);

        close(client_sock);
}

int main(int argc, char** argv) {
        uint16_t port = get_from_arg(argc, argv);
        int sock = CreateSocket(port);

        struct sockaddr_in client;
        memset(&client, 0, sizeof(client));
        int client_l = sizeof(client);
        int client_sock = -1;

        signal(SIGCHLD, SIG_IGN); // sa nu apara zombi
        while(1){
                client_sock = accept(sock, (struct sockaddr*)&client, &client_l);
                char* clientIp = inet_ntoa(client.sin_addr);
                printf("Clientul cu ip-ul %s s-a conectat\n", clientIp);

                int pid = fork();
                if(pid < 0){
                        perror("fork()");
                        continue;
                }
                if(pid==0){
                        processClient(client_sock);
                        exit(0); // success
                }
        }
        return 0;
}