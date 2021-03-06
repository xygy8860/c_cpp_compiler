
/*
 * Copyright 2018 Mr Duy
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.jecelyin.editor.v2.io;

/**
 * Created by jecelyin on 16/3/13.
 */
public final class CharArrayBuffer {
    private char[] buffer;
    private int len;

    public CharArrayBuffer(int capacity) {
        if(capacity < 0) {
            throw new IllegalArgumentException("Buffer capacity may not be negative");
        } else {
            this.buffer = new char[capacity];
        }
    }

    private void expand(int newlen) {
        char[] newbuffer = new char[Math.max(this.buffer.length << 1, newlen)];
        System.arraycopy(this.buffer, 0, newbuffer, 0, this.len);
        this.buffer = newbuffer;
    }

    public void append(char[] b, int off, int len) {
        if(b != null) {
            if(off >= 0 && off <= b.length && len >= 0 && off + len >= 0 && off + len <= b.length) {
                if(len != 0) {
                    int newlen = this.len + len;
                    if(newlen > this.buffer.length) {
                        this.expand(newlen);
                    }

                    System.arraycopy(b, off, this.buffer, this.len, len);
                    this.len = newlen;
                }
            } else {
                throw new IndexOutOfBoundsException();
            }
        }
    }

    public void clear() {
        this.len = 0;
    }

    public int length() {
        return this.len;
    }

    public char charAt(int i) {
        return this.buffer[i];
    }

    public char[] buffer() {
        return this.buffer;
    }

    public String toString() {
        return new String(this.buffer, 0, this.len);
    }
}
