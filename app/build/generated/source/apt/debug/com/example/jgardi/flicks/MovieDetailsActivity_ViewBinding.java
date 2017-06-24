// Generated code from Butter Knife. Do not modify!
package com.example.jgardi.flicks;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import java.lang.IllegalStateException;
import java.lang.Override;

public class MovieDetailsActivity_ViewBinding<T extends MovieDetailsActivity> implements Unbinder {
  protected T target;

  @UiThread
  public MovieDetailsActivity_ViewBinding(T target, View source) {
    this.target = target;

    target.previewImage = Utils.findRequiredViewAsType(source, R.id.ivPreviewImage, "field 'previewImage'", ImageView.class);
    target.tvTitle = Utils.findRequiredViewAsType(source, R.id.tvTitle, "field 'tvTitle'", TextView.class);
    target.tvOverview = Utils.findRequiredViewAsType(source, R.id.tvOverview, "field 'tvOverview'", TextView.class);
    target.rbVoteAverage = Utils.findRequiredViewAsType(source, R.id.rbVoteAverage, "field 'rbVoteAverage'", RatingBar.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.previewImage = null;
    target.tvTitle = null;
    target.tvOverview = null;
    target.rbVoteAverage = null;

    this.target = null;
  }
}
