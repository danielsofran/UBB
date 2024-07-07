#include <stdio.h>
#pragma warning (disable: 4996)
/*
se da un nr nat n
fol urm struct pt a stoca si a afisa cifrele nr dat*/
typedef struct {
	int lg;
	int elems[15];
} MyVector;

MyVector creeaza_vector(){
	/**
	* creeaza un vector de lg 0 si fara elem
	* @ return: MyVector
	* postconditii: v are lg 0 si nu cont elemente
	*/
	MyVector v;
	v.lg = 0;
	return v;
}

void adauga(MyVector* v, int elem_to_add){
	/**
	* adauga la v elem_to_add
	* 
	* postconditii: elem_to_add a fost adaugat la vectorul v
	*/
	v->elems[v->lg] = elem_to_add;
	v->lg++;
}

MyVector get_cifre(int n) {
	/**
	* transform n in MyVector de cifre
	* @param n: int
	*/
	MyVector v = creeaza_vector();
	for (; n; n/=10) adauga(&v, n % 10);
	return v;
}


void menu()
{
	while (1) {
		printf("1. Afiseaza cifrele\n0.Iesire\n\n");
		int cmd;
		scanf("%d", &cmd);
		if(cmd==0) break;
		if (cmd == 1)
		{
			printf("Nr dat este: ");
			int n;
			scanf("%d", &n);
			MyVector rez = get_cifre(n);
			for (int i = rez.lg - 1; i >= 0; --i)
				printf("rez[%d]=%d\n", rez.lg - i, rez.elems[i]);
		}
	}
}

int main1() {
	menu();
	return 0;
}