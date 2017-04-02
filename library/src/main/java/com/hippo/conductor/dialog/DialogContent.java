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
 * Created by Hippo on 4/1/2017.
 */

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.widget.FrameLayout;

// The actual content of dialog, direct child of DialogRoot.
class DialogContent extends FrameLayout {

  private final TypedValue minWidthMajor = new TypedValue();
  private final TypedValue minWidthMinor = new TypedValue();

  public DialogContent(@NonNull Context context) {
    super(context);
    init(context);
  }

  public DialogContent(@NonNull Context context,
      @Nullable AttributeSet attrs) {
    super(context, attrs);
    init(context);
  }

  public DialogContent(@NonNull Context context,
      @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    init(context);
  }

  private void init(Context context) {
    Resources.Theme theme = context.getTheme();
    theme.resolveAttribute(android.R.attr.windowMinWidthMajor, minWidthMajor, true);
    theme.resolveAttribute(android.R.attr.windowMinWidthMinor, minWidthMinor, true);

    Drawable drawable = ResourcesUtils.getAttrDrawable(context, android.R.attr.windowBackground);
    ViewCompat.setBackground(this, drawable);
  }

  @Override
  protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
    super.onMeasure(widthMeasureSpec, heightMeasureSpec);

    final int widthMode = MeasureSpec.getMode(widthMeasureSpec);
    final boolean isPortrait =
        getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT;
    final TypedValue tv = isPortrait ? minWidthMinor : minWidthMajor;
    boolean measure = false;

    if (widthMode == MeasureSpec.AT_MOST && tv.type != TypedValue.TYPE_NULL) {
      final int min;
      final DisplayMetrics metrics = getContext().getResources().getDisplayMetrics();
      if (tv.type == TypedValue.TYPE_DIMENSION) {
        min = (int) tv.getDimension(metrics);
      } else if (tv.type == TypedValue.TYPE_FRACTION) {
        min = (int) tv.getFraction(metrics.widthPixels, metrics.widthPixels);
      } else {
        min = 0;
      }

      if (getMeasuredWidth() < min) {
        widthMeasureSpec = MeasureSpec.makeMeasureSpec(min, MeasureSpec.EXACTLY);
        measure = true;
      }
    }

    if (measure) {
      super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
  }
}
