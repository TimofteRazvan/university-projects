#include "UI.h"

int main()
{
    Service serv;
    UI ui(serv);
    ui.start();
    return 0;
}
