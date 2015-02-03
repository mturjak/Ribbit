package com.newtpond.ribbt;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.parse.ParseUser;
import com.squareup.picasso.Picasso;

import java.util.Collections;
import java.util.List;

import fr.tkeunebr.gravatar.Gravatar;

final class GravatarAdapter extends BaseAdapter {
  private final Context mContext;
  private final LayoutInflater mLayoutInflater;
  private final int mAvatarImageViewPixelSize;
  private List<ParseUser> mUsers = Collections.emptyList();

  public GravatarAdapter(Context context) {
    mContext = context;
    mLayoutInflater = LayoutInflater.from(context);
    mAvatarImageViewPixelSize = context.getResources().getDimensionPixelSize(R.dimen.avatar_image_view_size);
  }

  public void updateUsers(List<ParseUser> users) {
    mUsers = users;
    notifyDataSetChanged();
  }

  @Override
  public int getCount() {
    return mUsers.size();
  }

  @Override
  public ParseUser getItem(int position) {
    return mUsers.get(position);
  }

  @Override
  public long getItemId(int position) {
    return position;
  }

  @Override
  public View getView(int position, View convertView, ViewGroup parent) {
    if (convertView == null) {
      convertView = mLayoutInflater.inflate(R.layout.user_list_item, null);
    }

    ParseUser user = getItem(position);

    String gravatarUrl = Gravatar.init().with(user.getEmail()).force404().size(mAvatarImageViewPixelSize).build();
    Picasso.with(mContext)
            .load(gravatarUrl)
            .placeholder(R.drawable.ic_contact_picture)
            .error(R.drawable.ic_contact_picture)
            .into((ImageView) convertView.findViewById(R.id.user_avatar));

    ((TextView) convertView.findViewById(R.id.user_name)).setText(user.getUsername());

    return convertView;
  }
}
