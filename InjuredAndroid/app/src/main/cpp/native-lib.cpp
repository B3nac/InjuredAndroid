#include <jni.h>
#include <string>
#include "encrypt.hpp"
#include <android/log.h>

using namespace std;

extern "C" JNIEXPORT jstring JNICALL
Java_b3nac_injuredandroid_AssemblyActivity_stringFromJNI(
        JNIEnv *env,
        jobject /* this */) {

    string hello = "win";
    string test = encryptDecrypt(hello);

    return env->NewStringUTF(test.c_str());
}

extern "C" const char* encryptDecrypt(string encryptThis) {
    char key[5] = {'M', 'A', 'D'};

    string output = encryptThis;

    __android_log_print(ANDROID_LOG_INFO, "sometag", "test string = %s", output.c_str());

    for (int i = 0; i < encryptThis.size(); i++) {
        output[i] = encryptThis[i] ^ key[i % (sizeof(key) / sizeof(char))];
    }
    string test = output.c_str();

    __android_log_print(ANDROID_LOG_INFO, "sometag", "Another test string = %s", test.c_str());

    return output.c_str();


}
