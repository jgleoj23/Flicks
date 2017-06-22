// Generated code from Butter Knife. Do not modify!
package com.example.jgardi.flicks;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import java.lang.IllegalStateException;
import java.lang.Override;

public class MovieAdapter$ViewHolder_ViewBinding<T extends MovieAdapter.ViewHolder> implements Unbinder {
  protected T target;

  @UiThread
  public MovieAdapter$ViewHolder_ViewBinding(T target, View source) {
    this.target = target;

    target.ivPosterImage = Utils.findOptionalViewAsType(source, R.id.ivPosterImage, "field 'ivPosterImage'", ImageView.class);
    target.ivBackdropImage = Utils.findOptionalViewAsType(source, R.id.ivBackdropImage, "field 'ivBackdropImage'", ImageView.class);
    target.tvTitle = Utils.findRequiredViewAsType(source, R.id.tvTitle, "field 'tvTitle'", TextView.class);
    target.tvOverview = Utils.findRequiredViewAsType(source, R.id.tvOverview, "field 'tvOverview'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.ivPosterImage = null;
    target.ivBackdropImage = null;
    target.tvTitle = null;
    target.tvOverview = null;

    this.target = null;
  }
}
