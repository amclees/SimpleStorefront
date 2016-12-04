package simplestorefront.models;

import java.util.Date;

public class LogItem {

  private Date timeStamp;
  private String name;
  private String description;

  public LogItem(String name, String description, Date date) {
    this.name = name;
    this.description = description;
    this.timeStamp = date;
  }

  public Date getTimeStamp() {
    return timeStamp;
  }

  public String getName() {
    return name;
  }

  public String getDescription() {
    return description;
  }
}
