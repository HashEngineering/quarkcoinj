/*
 * Copyright 2014 Hash Engineering Solutions.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

#include "hashblock.h"
#include <inttypes.h>

#include <jni.h>

jbyteArray JNICALL hash9_native_old(JNIEnv *env, jclass cls, jbyteArray header)
{
    jint Plen = (env)->GetArrayLength(header);
    jbyte *P = (env)->GetByteArrayElements(header, NULL);
    //uint8_t *buf = malloc(sizeof(uint8_t) * dkLen);
    jbyteArray DK = NULL;

    if (P)
	{

	uint256 result = Hash9(P, P+Plen);

    DK = (env)->NewByteArray(32);
    if (DK)
	{
		(env)->SetByteArrayRegion(DK, 0, 32, (jbyte *) result.begin());
	}


    if (P) (env)->ReleaseByteArrayElements(header, P, JNI_ABORT);
    //if (buf) free(buf);
	}
    return DK;
}

jbyteArray JNICALL hash9_native(JNIEnv *env, jclass cls, jbyteArray header, jint offset, jint length)
{
    //jint Plen = (env)->GetArrayLength(header);
    jbyte *P = (env)->GetByteArrayElements(header, NULL);
    jbyteArray DK = NULL;

    if (P)
	{
	    jbyte * start = P + offset;

	
	    uint256 result = Hash9(P + offset, P + offset + length);

        DK = (env)->NewByteArray(32); // 256 bit hash value
        if (DK)
        {
            (env)->SetByteArrayRegion(DK, 0, 32, (jbyte *) result.begin());
        }


        if (P) (env)->ReleaseByteArrayElements(header, P, JNI_ABORT);

	}
    return DK;
}

static const JNINativeMethod methods[] = {
    { "hash9_native", "([BII)[B", (void *) hash9_native },
    { "hash9_native_old", "([B)[B", (void *) hash9_native_old }
};

jint JNI_OnLoad(JavaVM *vm, void *reserved) {
    JNIEnv *env;

    if ((vm)->GetEnv((void **) &env, JNI_VERSION_1_6) != JNI_OK) {
        return -1;
    }

    jclass cls = (env)->FindClass("com/hashengineering/crypto/Hash9");
    int r = (env)->RegisterNatives(cls, methods, sizeof(methods)/sizeof(methods[0]));

    return (r == JNI_OK) ? JNI_VERSION_1_6 : -1;
}