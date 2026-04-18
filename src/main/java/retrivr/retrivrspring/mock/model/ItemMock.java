package retrivr.retrivrspring.mock.model;

public class ItemMock {

  private final Long id;
  private final Long organizationId;
  private final String organizationName;
  private final String name;
  private final String description;
  private final int totalQuantity;
  private int availableQuantity;

  public ItemMock(
      Long id,
      Long organizationId,
      String organizationName,
      String name,
      String description,
      int totalQuantity,
      int availableQuantity
  ) {
    this.id = id;
    this.organizationId = organizationId;
    this.organizationName = organizationName;
    this.name = name;
    this.description = description;
    this.totalQuantity = totalQuantity;
    this.availableQuantity = availableQuantity;
  }

  public Long id() {
    return id;
  }

  public Long organizationId() {
    return organizationId;
  }

  public String organizationName() {
    return organizationName;
  }

  public String name() {
    return name;
  }

  public String description() {
    return description;
  }

  public int totalQuantity() {
    return totalQuantity;
  }

  public int availableQuantity() {
    return availableQuantity;
  }

  public void decreaseAvailableQuantity() {
    availableQuantity -= 1;
  }

  public void increaseAvailableQuantity() {
    availableQuantity += 1;
  }
}
