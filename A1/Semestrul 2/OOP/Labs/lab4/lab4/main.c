#include <stdio.h> 
#include <stdlib.h>
#include <string.h>
#include "testing.h"
#include "ui.h"
#include "player.h"

void solveTests() {
	testCreatePlayer();
	testEquals();
    testUpdatePlayer();
	testCreateEmptyList();
	testAddPlayer();
	testFindPlayer();
	testDeletePlayer();
	testMakeAdd();
	testMakeDelete();
    testMakeUpdate();
	testGreaterAndCompare();
	testSortList();
	testFilter();
}

int main() {
	solveTests();
	runConsole();

	return 0;
}