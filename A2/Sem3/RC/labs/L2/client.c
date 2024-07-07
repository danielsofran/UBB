// socket, send, recv
#include <sys/types.h>
#include <sys/socket.h>

// hton, ntoh
#include <arpa/inet.h>

// ?
#include <netinet/in.h>
#include <netinet/ip.h>

#include <unistd.h>
#include <stdlib.h>
#include <stdio.h>
#include <string.h>

void get_from_arg(int argc, char** argv, char** ip, uint16_t* port){	
	if(argc < 3) {
		perror("Introduceti: [ip] [port]!");
		exit(1);
	}

	*ip = argv[1];
	sscanf(argv[2], "%hu", port);
}

int Connect(char* ip, uint16_t port)
{
	int sock;
	struct sockaddr_in server;
	
	sock = socket(AF_INET, SOCK_STREAM, 0);
	if(sock < 0) {
		perror("Eroare la crearea socketului client\n");
		exit(1);
	}
	
	memset(&server, 0, sizeof(server));
	server.sin_port = htons(port);
	server.sin_family = AF_INET;
	server.sin_addr.s_addr = inet_addr(ip);

	int conn = connect(sock, (struct sockaddr *)&server, sizeof(server));
	if(conn < 0){
		perror("Eroare la conectarea la server\n");
		exit(1);
	}

	return sock;
}

//char sir[256];

//void get_string(){
//	FILE* f = fopen("file.txt", "r");
//	fgets(sir, 256, f);
//	sir[255]='\0';
//	fclose(f);
//}

int main(int argc, char** argv, char** env){
	char* ip;
	uint16_t port;
	get_from_arg(argc, argv, &ip, &port);
		
	int sock = Connect(ip, port);
	
	char sir[] = "Hello! I am a C client.\n";
	//get_string();
	int l=strlen(sir);
	int l_cv=htonl(l);
	
	int res;
	res = send(sock, &l_cv, sizeof(int), 0); // e bai daca e neg
	res = send(sock, sir, sizeof(char)*(l+1), 0);
	// recv(sock, &var_vc, size, 0);
	// var = ntohs(var_cv); -- deconversie daca e numeric
	close(sock);
	
	return 0;
}	
	
	

