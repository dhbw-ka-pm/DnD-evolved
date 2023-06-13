package io.swagger.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * Location
 */
@Validated
@javax.annotation.processing.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2023-06-11T16:16:55.027943953Z[GMT]")


public class Location {
  @JsonProperty("X")
  private Integer X = null;

  @JsonProperty("Y")
  private Integer Y = null;

  public Location (Integer X, Integer Y) {
    this.X = X;
    this.Y = Y;
  }

  /**
   * Get Y
   *
   * @return Y
   **/
  @Schema(description = "")

  public Integer getY() {
    return Y;
  }

  public void setY(Integer Y) {
    this.Y = Y;
  }

  public Integer getX() {
    return X;
  }

  public void setX(Integer X) {
    this.X = X;
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
    StringBuilder sb = new StringBuilder();
    sb.append("class Location {\n");

    sb.append("    X: ").append(toIndentedString(X)).append("\n");
    sb.append("    Y: ").append(toIndentedString(Y)).append("\n");
    sb.append("}");
    return sb.toString();
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
