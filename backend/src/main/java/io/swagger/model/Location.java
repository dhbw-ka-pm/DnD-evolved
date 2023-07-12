package io.swagger.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * Location
 */
@Validated

public class Location   {
  @JsonProperty("x")
  private Integer X = null;

  @JsonProperty("y")
  private Integer Y = null;

  public Location(){}

  public Location(int x, int y){
    setX(x);
    setY(y);
  }


  public Integer getX() {
    return X;
  }

  public void setX(Integer X) {
    this.X = X;
  }

  public Integer getY() {
    return Y;
  }

  public void setY(Integer Y) {
    this.Y = Y;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Location location = (Location) o;
    return Objects.equals(this.X, location.X) &&
            Objects.equals(this.Y, location.Y);
  }

  @Override
  public int hashCode() {
    return Objects.hash(X, Y);
  }

  @Override
  public String toString() {

    return "class Location {\n" +
            "    X: " + toIndentedString(X) + "\n" +
            "    Y: " + toIndentedString(Y) + "\n" +
            "}";
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}