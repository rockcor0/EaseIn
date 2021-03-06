package com.easein;

import com.parse.ParseClassName;
import com.parse.ParseGeoPoint;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

/**
 * Data model for a post.
 */
@ParseClassName("Posts")
public class Post extends ParseObject {

  public static ParseQuery<Post> getQuery() {
    return ParseQuery.getQuery(Post.class);
  }

  public String getText() {
    return getString("text");
  }

  public void setText(String value) {
    put("text", value);
  }

  public String getTipoEspacio() {
    return getString("tipoEspacio");
  }

  public void setTipoEspacio(String value) {
    put("tipoEspacio", value);
  }

  public ParseUser getUser() {
    return getParseUser("user");
  }

  public void setUser(ParseUser value) {
    put("user", value);
  }

  public ParseGeoPoint getLocation() {
    return getParseGeoPoint("location");
  }

  public void setLocation(ParseGeoPoint value) {
    put("location", value);
  }

  //TODO
}
