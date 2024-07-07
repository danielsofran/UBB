#include "tests.h"
#include "uiconsole.h"

int main() {
    test();
    FileRepository<Locatar> repo("OwnFiles/repo.txt");
    Service service{repo};
    ConsoleUI console{service};
    console.run();
    return 0;
}
