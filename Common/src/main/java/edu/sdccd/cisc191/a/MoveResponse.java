package edu.sdccd.cisc191.a;
import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.*;
public class MoveResponse {
  private static final ObjectMapper objectMapper = new ObjectMapper();
  public int row;
  public int col;
  public char piece;
  public boolean valid = false;
  public char winner;
  public MoveResponse(int row, int col, char piece, boolean valid, char winner) {
    this.row = row;
    this.col = col;
    this.piece = piece;
    this.valid = valid;
    this.winner = winner;
  }
  protected MoveResponse() {}
  @JsonIgnore
  public String toJSON() throws Exception {
    return objectMapper.writeValueAsString(this);
  }
  public static MoveResponse fromJSON(String input) throws Exception {
    return objectMapper.readValue(input, MoveResponse.class);
  }
}
