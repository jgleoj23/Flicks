// Generated code from Butter Knife. Do not modify!
package com.example.jgardi.flicks;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.google.android.youtube.player.YouTubePlayerView;
import java.lang.IllegalStateException;
import java.lang.Override;

public class MovieTrailerActivity_ViewBinding<T extends MovieTrailerActivity> implements Unbinder {
  protected T target;

  @UiThread
  public MovieTrailerActivity_ViewBinding(T target, View source) {
    this.target = target;

    target.player = Utils.findRequiredViewAsType(source, R.id.player, "field 'player'", YouTubePlayerView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.player = null;

    this.target = null;
  }
}
