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

package com.hippo.conductor.dialog.demo;

/*
 * Created by Hippo on 4/2/2017.
 */

import android.os.Bundle;
import android.support.annotation.Keep;
import android.support.annotation.NonNull;
import android.support.annotation.StyleRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import com.bluelinelabs.conductor.Router;

public class CustomDialog extends AlertDialogController {

  public CustomDialog() {
    super();
  }

  public CustomDialog(@StyleRes int themeId) {
    super(themeId);
  }

  @Keep
  public CustomDialog(Bundle bundle) {
    super(bundle);
  }

  @Override
  protected View onCreateContentView(
      @NonNull LayoutInflater inflater, @NonNull ViewGroup container) {
    View view = inflater.inflate(R.layout.dialog_custom, container, false);
    Button button = (Button) view.findViewById(R.id.dismiss);
    button.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Router router = getRouter();
        if (router != null) {
          router.popController(CustomDialog.this);
        }
      }
    });
    return view;
  }
}
