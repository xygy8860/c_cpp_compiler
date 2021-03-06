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

package com.jecelyin.editor.v2.adapter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.jecelyin.editor.v2.common.TabCloseListener;
import com.jecelyin.editor.v2.ui.editor.EditorDelegate;

import java.io.File;

/**
 * Created by Duy on 25-Apr-18.
 */
public interface IEditorPagerAdapter {

    void removeAll(TabCloseListener tabCloseListener);

    void newEditor(@NonNull File file, int offset, String encoding);

    @Nullable
    EditorDelegate getCurrentEditorDelegate();

    TabAdapter.TabInfo[] getTabInfoList();

    void removeEditor(int position, TabCloseListener listener);

    @Nullable
    EditorDelegate getEditorDelegateAt(int index);

    void updateDescriptor(String file, String encoding);
}
