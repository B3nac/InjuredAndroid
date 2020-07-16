//
// Created by b3nac on 6/28/20.
//

#include <string>
#include "encrypt.hpp"

using namespace std;

extern "C" const char* encryptDecrypt(string encryptThis) {
    char key[5] = {'W', 'I', 'N'}; //Can be any chars, and any size array

    string output = encryptThis;

    for (int i = 0; i < encryptThis.size(); i++) {
        output[i] = encryptThis[i] ^ key[i % (sizeof(key) / sizeof(char))];
    }
    return output.c_str();
}
