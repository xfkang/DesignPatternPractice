/*
 * Copyright (C) 2006 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.itbird.design.adapter.listview.source;


/**
 * Extended {@link Adapter} that is the bridge between a {@link ListView}
 * and the data that backs the list. Frequently that data comes from a Cursor,
 * but that is not
 * required. The ListView can display any data provided that it is wrapped in a
 * ListAdapter.
 */
public interface ListAdapter extends Adapter {

}
