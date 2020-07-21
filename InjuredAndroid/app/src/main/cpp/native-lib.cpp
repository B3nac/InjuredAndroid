#include <jni.h>
#include <string>
#include "encrypt.hpp"
#include <android/log.h>

using namespace std;

extern "C" JNIEXPORT jstring JNICALL
Java_b3nac_injuredandroid_AssemblyActivity_stringFromJNI(
        JNIEnv *env,
        jobject /* this */) {

    char key[5] = {'M', 'A', 'D'};

    string output = "win";

    for (int i = 0; i < output.size(); i++) {
        output[i] = output[i] ^ key[i % (sizeof(key) / sizeof(char))];
    }
    const char *test = output.c_str();

    return env->NewStringUTF(test);
}

extern "C" const char* encryptDecrypt(string encryptThis) {
    char key[5] = {'M', 'A', 'D'};

    string output = encryptThis;

    for (int i = 0; i < encryptThis.size(); i++) {
        output[i] = encryptThis[i] ^ key[i % (sizeof(key) / sizeof(char))];
    }
    string test = output.c_str();

    return output.c_str();
}
