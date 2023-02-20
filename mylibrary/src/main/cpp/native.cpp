#include <jni.h>
#include <string>


void __attribute__((constructor)) calledFirst();
void __attribute__((constructor)) Java_com_example_mylibrary_jjava_traverseViewTree();
std::string hello = "";
int m=1;

void calledFirst(){
    m+=5;
}

extern "C"
JNIEXPORT jstring JNICALL
Java_com_example_mylibrary_jjava_stringFromJNI(JNIEnv *env, jclass clazz) {
    for(int i=0;i<m;i++){hello+="hello!";}
    return env->NewStringUTF(hello.c_str());
}





extern "C" {
JNIEXPORT void JNICALL
Java_com_example_mylibrary_jjava_traverseViewTree(JNIEnv *env, jclass thiz) {
    // Получаем класс
    jclass clazz = env->GetObjectClass(thiz);
    // Получаем метод
    jmethodID method = env->GetMethodID(clazz, "timer", "()V");
    // Вызываем Java-функцию
    env->CallVoidMethod(thiz, method);
}
}





