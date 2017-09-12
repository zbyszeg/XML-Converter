#define _WIN32_WINNT 0x500
#include <iostream>
#include <cstdlib>
#include <fstream>
#include <windows.h>

using namespace std;

int main()
{
    cout << "********************************\n";
    cout << "* Sopoltrad Analyse Convertert *\n";
    cout << "* ---------------------------- *\n";
    cout << "*  http://github.com/zbyszeg   *\n";
    cout << "* ---------------------------- *\n";
    cout << "*        September '17         *\n";
    cout << "********************************\n\n";

    ShowWindow(GetConsoleWindow(), 0);

    string dir, command;
    fstream file;

    system("@cd > dir.t");

    file.open("dir.t", ios::in);
    getline(file, dir);
    command = "java -jar -Dfile.encoding=UTF-8 C:\\SopoltradStudio\\sac.jar \""+dir+"\"";
    file.close();

    system("@del dir.t");
    system(command.c_str());

    return 0;
}
