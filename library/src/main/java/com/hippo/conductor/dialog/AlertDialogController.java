/*
 * Copyright 2017 Hippo Seven
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

package com.hippo.conductor.dialog;

/*
 * Created by Hippo on 4/2/2017.
 */

import android.util.TypedValue;

/**
 * {@code AlertDialogController} uses {@link android.R.attr#alertDialogTheme}.
 */
public class AlertDialogController extends DialogController {

  @Override
  public int getActualThemeId() {
    final int themeId = getThemeId();
    if (themeId == 0) {
      final TypedValue outValue = new TypedValue();
      getActivity().getTheme().resolveAttribute(android.R.attr.alertDialogTheme, outValue, true);
      return outValue.resourceId;
    } else {
      return themeId;
    }
  }
}
