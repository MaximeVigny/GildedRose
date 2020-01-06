import java.util.List;

public class LegendaryItems {
    public static String Sulfuras = "Sulfuras, Hand of Ragnaros";
    // A remplacer par un Array de légendaire si on en ajoute plus par la suite

    public static Boolean isLegendary(List<CorruptedItems> items, int indice){
        return Sulfuras.equals(items.get(indice).getCorruptedItems().getName());
    }

    public static Boolean isLegendary(CorruptedItems item)
    {
        return Sulfuras.equals(item.getName());
    }
}
