import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class GildedRose {

    private static List<CorruptedItems> items = null;

    public GildedRose() {

        System.out.println("Leeerrooy Jennnkinns !");

        items = new ArrayList<CorruptedItems>();
        items.add(new CorruptedItems(new Item("+5 Dexterity Vest", 10, 20), false));
        items.add(new CorruptedItems(new Item("Aged Brie", 2, 0),false));
        items.add(new CorruptedItems(new Item("Elixir of the Mongoose", 5, 7), false));
        items.add(new CorruptedItems(new Item("Sulfuras, Hand of Ragnaros", 0, 80), false));
        items.add(new CorruptedItems(new Item("Backstage passes to a TAFKAL80ETC concert", 15, 20), false));
        items.add(new CorruptedItems(new Item("Conjured Mana Cake", 3, 6), true));

    }


    public static void updateQuality()
    {
        for (int i = 0; i < items.size(); i++)
        {
            if ((!"Aged Brie".equals(items.get(i).getCorruptedItems().getName())) && !"Backstage passes to a TAFKAL80ETC concert".equals(items.get(i).getCorruptedItems().getName()))
            {
                if (items.get(i).getCorruptedItems().getQuality() > 0)
                {
                    if (!LegendaryItems.isLegendary(items,i))
                    {
                        if (items.get(i).getIsCorrupted()){
                            items.get(i).getCorruptedItems().setQuality(items.get(i).getCorruptedItems().getQuality() - 2);
                        }
                        else {
                            items.get(i).getCorruptedItems().setQuality(items.get(i).getCorruptedItems().getQuality() - 1);
                        }
                    }
                }
            }
            else
            {
                if (items.get(i).getCorruptedItems().getQuality() < 50)
                {
                    items.get(i).getCorruptedItems().setQuality(items.get(i).getCorruptedItems().getQuality() + 1);

                    if ("Backstage passes to a TAFKAL80ETC concert".equals(items.get(i).getCorruptedItems().getName()))
                    {
                        if (items.get(i).getCorruptedItems().getSellIn() < 11)
                        {
                            if (items.get(i).getCorruptedItems().getQuality() < 50)
                            {
                                items.get(i).getCorruptedItems().setQuality(items.get(i).getCorruptedItems().getQuality() + 1);
                            }
                        }

                        if (items.get(i).getCorruptedItems().getSellIn() < 6)
                        {
                            if (items.get(i).getCorruptedItems().getQuality() < 50)
                            {
                                items.get(i).getCorruptedItems().setQuality(items.get(i).getCorruptedItems().getQuality() + 1);
                            }
                        }
                    }
                }
            }

            if (!LegendaryItems.isLegendary(items,i))
            {
                items.get(i).getCorruptedItems().setSellIn(items.get(i).getCorruptedItems().getSellIn() - 1);
            }

            if (items.get(i).getCorruptedItems().getSellIn() < 0)
            {
                if (!"Aged Brie".equals(items.get(i).getCorruptedItems().getName()))
                {
                    if (!"Backstage passes to a TAFKAL80ETC concert".equals(items.get(i).getCorruptedItems().getName()))
                    {
                        if (items.get(i).getCorruptedItems().getQuality() > 0)
                        {
                            // (!LegendaryItem.isLegendary(items,i)) ?  :
                            if (!LegendaryItems.isLegendary(items,i))
                            {
                                if (items.get(i).getIsCorrupted())
                                    items.get(i).getCorruptedItems().setQuality(items.get(i).getCorruptedItems().getQuality() - 2);
                                else
                                    items.get(i).getCorruptedItems().setQuality(items.get(i).getCorruptedItems().getQuality() - 1);
                            }
                        }
                    }
                    else
                    {
                        items.get(i).getCorruptedItems().setQuality(items.get(i).getCorruptedItems().getQuality() - items.get(i).getCorruptedItems().getQuality());
                    }
                }
                else
                {
                    if (items.get(i).getCorruptedItems().getQuality() < 50)
                    {
                        items.get(i).getCorruptedItems().setQuality(items.get(i).getCorruptedItems().getQuality() + 1);
                    }
                }
            }
        }
    }

    public CorruptedItems get(String key) {

        Optional<CorruptedItems> optional = items.stream()
                .filter(items -> items.getName().equals(key))
                .findFirst();

        if(optional.isPresent()){
            return optional.get();
        } else {
            throw new IllegalStateException("Not found in shop");
        }
    }

    public List<CorruptedItems> getItem() {
        return this.items;
    }
}