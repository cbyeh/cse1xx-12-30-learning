#include <iostream>
using namespace std;

int main() {
    int x = 0x1234;
    int & y = x;
    y++;
    int * z = &y;
    cout<<hex<<x<<endl;
    cout<<hex<<y<<endl;
    cout<<hex<<*z<<endl;
}