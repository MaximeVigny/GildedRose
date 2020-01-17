import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class GildedRose {

    private static List<CorruptedItems> items = null;
    private static int MAX_QUALITY = 50;

    public GildedRose() {

        System.out.println("Leeerrooy Jennnkinns !");

        items = new ArrayList<CorruptedItems>();
        items.add(new CorruptedItems(new Item("+5 Dexterity Vest", 10, 20), false));
        items.add(new CorruptedItems(new Item("Aged Brie", 2, 0), false));
        items.add(new CorruptedItems(new Item("Elixir of the Mongoose", 5, 7), false));
        items.add(new CorruptedItems(new Item("Sulfuras, Hand of Ragnaros", 0, 80), false));
        items.add(new CorruptedItems(new Item("Backstage passes to a TAFKAL80ETC concert", 15, 20), false));
        items.add(new CorruptedItems(new Item("Conjured Mana Cake", 3, 6), true));

    }


    public void updateQuality() {
        for (int i = 0; i < items.size(); i++) {

            if("Aged Brie".equals(getNameObject(i)))
                updateCheeseQuality(i);
            else if("Backstage passes to a TAFKAL80ETC concert".equals(getNameObject(i)))
                updateConcertQuality(i);
            else
                updateStandardItem(i);

            if (!LegendaryItems.isLegendary(items, i)) {
                setSellInObject(i, getSellInObject(i) - 1);
            }
        }
    }

    /**
     * Méthode qui augmente de 1 la qualité d'un Objet s'il n'a pas dépasser MAX_QUALITY
     **/
    public void increaseQualityIfNotMax(int indice) {
        if (getQualityObject(indice) < MAX_QUALITY) {
            setQualityObject(indice, getQualityObject(indice) + 1);
        }
    }

    /**
     * Méthode qui décroît la qualité d'un objet de 1, ou 2 si l'objet est conjuré
     **/
    public void updateAgeObject(int indice) {
        if (getQualityObject(indice) > 0) {
            if (!LegendaryItems.isLegendary(items, indice)) {
                if (items.get(indice).getIsCorrupted()) {
                    setQualityObject(indice, getQualityObject(indice) - 2);
                } else {
                    setQualityObject(indice, getQualityObject(indice) - 1);
                }
            }
        }
    }


    /** Update Cheese **/
    public void updateCheeseQuality(int indice) {
        increaseQualityIfNotMax(indice);
        if (getSellInObject(indice) < 0)
            increaseQualityIfNotMax(indice);
    }

    // if ("Backstage passes to a TAFKAL80ETC concert".equals(getNameObject(i))) {
    /** Update Concert **/
    public void updateConcertQuality(int indice) {
        increaseQualityIfNotMax(indice);

        if (getSellInObject(indice) < 11)
            increaseQualityIfNotMax(indice);

        if (getSellInObject(indice) < 6)
            increaseQualityIfNotMax(indice);

        if (getSellInObject(indice) < 0)
            setQualityObject(indice, getQualityObject(indice) - getQualityObject(indice));
    }

    public void updateStandardItem(int indice) {
        updateAgeObject(indice);

        if (getSellInObject(indice) < 0) {
            updateAgeObject(indice);
        }
    }


    /** Méthode qui retourne le nom d'un Objet par apport à sa position indice dans items **/
    public String getNameObject(int indice)
    {
        return items.get(indice).getCorruptedItems().getName();
    }

    /** Méthode qui retourne la valeur de la qualité d'un Objet d'items avec comme paramètre l'indice d'items **/
    public int getQualityObject(int indice)
    {
        return items.get(indice).getCorruptedItems().getQuality();
    }

    /** Méthode qui applique quality à un Objet par apport à son indice dans items   **/
    public void setQualityObject(int indice, int quality)
    {
        items.get(indice).getCorruptedItems().setQuality(quality);
    }

    /** Méthode qui retourne la valeur de vente d'un Objet **/
    public int getSellInObject(int indice)
    {
        return items.get(indice).getCorruptedItems().getSellIn();
    }

    /** Méthode qui applique la valeur newSell à un Objet **/
    public void setSellInObject(int indice, int newSell)
    {
        items.get(indice).getCorruptedItems().setSellIn(newSell);
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