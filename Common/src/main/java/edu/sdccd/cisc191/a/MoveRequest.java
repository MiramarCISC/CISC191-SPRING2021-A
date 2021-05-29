// Author: Muaaz Khan
// Description: A move request from a client
package edu.sdccd.cisc191.a;
import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.*;
public class MoveRequest {
  private static final ObjectMapper objectMapper = new ObjectMapper();
  public int row;
  public int col;
  public MoveRequest(int row, int col) {
    this.row = row;
    this.col = col;
  }
  protected MoveRequest() {}
  @JsonIgnore
  public String toJSON() throws Exception {
    return objectMapper.writeValueAsString(this);
  }
  public static MoveRequest fromJSON(String input) throws Exception {
    return objectMapper.readValue(input, MoveRequest.class);
  }
}
