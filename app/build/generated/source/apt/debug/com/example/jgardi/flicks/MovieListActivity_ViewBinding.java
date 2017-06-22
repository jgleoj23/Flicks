// Generated code from Butter Knife. Do not modify!
package com.example.jgardi.flicks;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import java.lang.IllegalStateException;
import java.lang.Override;

public class MovieListActivity_ViewBinding<T extends MovieListActivity> implements Unbinder {
  protected T target;

  @UiThread
  public MovieListActivity_ViewBinding(T target, View source) {
    this.target = target;

    target.rvMovies = Utils.findRequiredViewAsType(source, R.id.rvMovies, "field 'rvMovies'", RecyclerView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.rvMovies = null;

    this.target = null;
  }
}
