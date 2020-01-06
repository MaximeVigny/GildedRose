public class CorruptedItems {
    Item item;
    Boolean isCorrupted;

    /** Constructeur **/
    public CorruptedItems(Item item, Boolean isCorrupted)
    {
        this.item = item;
        this.isCorrupted = isCorrupted;
    }


    /** Partie getters **/
    public Item getCorruptedItems()
    {
        return item;
    }

    public Boolean getIsCorrupted()
    {
        return this.isCorrupted;
    }

    public Object getName() {
        return this.item.getName();
    }

    public Item getItem() {
        return this.item;
    }
}
