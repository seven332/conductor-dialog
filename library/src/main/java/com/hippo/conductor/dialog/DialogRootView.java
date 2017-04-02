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

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;

// The root of dialog, shows dim effect, handles cancelled-on-touch-outside
class DialogRootView extends FrameLayout {

  private DialogController dialog;
  private View content;
  private boolean cancelledOnTouchOutside;

  public DialogRootView(@NonNull Context context) {
    super(context);
    init(context);
  }

  public DialogRootView(@NonNull Context context,
      @Nullable AttributeSet attrs) {
    super(context, attrs);
    init(context);
  }

  public DialogRootView(@NonNull Context context,
      @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    init(context);
  }

  private void init(Context context) {
    final float dimAmount =
        ResourcesUtils.getAttrFloat(context, android.R.attr.backgroundDimAmount);
    final int alpha = (int) (255 * dimAmount);
    setBackgroundColor(Color.argb(alpha, 0, 0, 0));
  }

  void setDialog(DialogController dialog) {
    this.dialog = dialog;
  }

  public void setCancelledOnTouchOutside(boolean cancel) {
    cancelledOnTouchOutside = cancel;
  }

  private void ensureContent() {
    if (content == null) {
      if (getChildCount() == 0) {
        throw new IllegalStateException("DialogRoot should contain a DialogContent");
      }
      content = getChildAt(0);
    }
  }

  private boolean isUnderView(View view, MotionEvent event) {
    float x = event.getX();
    float y = event.getY();
    return x >= view.getLeft() && x < view.getRight()
        && y >= view.getTop() && y < view.getBottom();
  }

  private void cancel() {
    if (dialog != null) {
      dialog.cancel();
    }
  }

  @Override
  public boolean onTouchEvent(MotionEvent event) {
    if (cancelledOnTouchOutside && event.getActionMasked() == MotionEvent.ACTION_DOWN) {
      ensureContent();
      if (!isUnderView(content, event)) {
        cancel();
      }
    }
    // Always return true to avoid touch through
    return true;
  }
}
