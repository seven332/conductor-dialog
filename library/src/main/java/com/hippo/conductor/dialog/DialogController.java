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
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.StyleRes;
import android.util.TypedValue;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.bluelinelabs.conductor.Controller;

/**
 * {@code DialogController} shows a view in dialog style.
 */
public class DialogController extends Controller {

  private static final String KEY_THEME_ID = "DialogController:theme_id";

  private static Bundle buildArgs(int themeId) {
    Bundle bundle = new Bundle();
    bundle.putInt(KEY_THEME_ID, themeId);
    return bundle;
  }

  private int themeId;
  private int actualThemeId;

  /**
   * Creates a dialog controller that uses the default dialog theme.
   */
  public DialogController() {
    this(0);
  }

  /**
   * Creates a dialog controller that uses a custom dialog style.
   */
  public DialogController(@StyleRes int themeId) {
    this(buildArgs(themeId));
  }

  public DialogController(Bundle bundle) {
    super(bundle);
    themeId = bundle.getInt(KEY_THEME_ID);
  }

  /**
   * Returns the theme ID passed in constructor.
   * {@code 0} if using the default dialog theme.
   */
  public int getThemeId() {
    return themeId;
  }

  /**
   * Returns the actual theme ID.
   * It resolve the default theme.
   */
  public int getActualThemeId() {
    final TypedValue outValue = new TypedValue();
    getActivity().getTheme().resolveAttribute(android.R.attr.dialogTheme, outValue, true);
    return outValue.resourceId;
  }

  // Applies theme to LayoutInflater
  private LayoutInflater resolveLayoutInflater(LayoutInflater inflater) {
    if (actualThemeId == 0) {
      actualThemeId = getActualThemeId();
    }
    if (actualThemeId != 0) {
      Context contextThemeWrapper = new ContextThemeWrapper(inflater.getContext(), actualThemeId);
      inflater = inflater.cloneInContext(contextThemeWrapper);
    }
    return inflater;
  }

  @NonNull
  @Override
  protected final View onCreateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container) {
    inflater = resolveLayoutInflater(inflater);
    View view = inflater.inflate(R.layout.controller_dialog, container, false);

    ViewGroup content = (ViewGroup) view.findViewById(R.id.dialog_content);
    onSetContentView(inflater, content);

    return view;
  }

  /**
   * Creates dialog content view and adds it to {@code parent}.
   *
   * @param inflater The LayoutInflater that should be used to inflate views
   * @param parent The parent view to add content view
   */
  protected void onSetContentView(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {}
}
