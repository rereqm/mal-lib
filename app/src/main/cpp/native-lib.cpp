/*#include <jni.h>
#include <string>


void __attribute__((constructor)) calledFirst();
std::string hello = "";
int m=1;

void calledFirst(){
    m+=1;
}

extern "C"
JNIEXPORT jstring JNICALL
Java_com_example_ndktest_MainActivity_stringFromJNI(JNIEnv *env, jobject thiz) {
    for(int i=0;i<m;i++){hello+="hello!";}
    return env->NewStringUTF(hello.c_str());


}*/