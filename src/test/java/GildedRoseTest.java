import org.junit.Test;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;


public class GildedRoseTest {


	@Test
	public void Sulfuras_should_keep_quality() {

		GildedRose inn = new GildedRose();

		CorruptedItems sulfuras = inn.get("Sulfuras, Hand of Ragnaros");
		int originQuality = sulfuras.getItem().quality;
		for(int i=0; i < 100; i++)
		{
			inn.updateQuality();
			assertThat(sulfuras.getItem().quality).isEqualTo(originQuality);
		}
	}

	@Test
	public void Quality_should_never_be_negative() {

		GildedRose inn = new GildedRose();
		List<CorruptedItems> items = inn.getItem();

		for(int i=0; i < 100; i++)
		{
			inn.updateQuality();
			for (CorruptedItems it:items
				 ) {
				assertThat(it.getItem().getQuality()).isGreaterThan(-1);
			}
		}
	}

	@Test
	public void Quality_should_never_be_more_than_50(){

		GildedRose inn = new GildedRose();
		List<CorruptedItems> items = inn.getItem();

		for(int i=0; i < 100; i++)
		{
			inn.updateQuality();
			for (CorruptedItems it:items
			) {
				if (!LegendaryItems.isLegendary(it))
				{
					assertThat(it.getItem().getQuality()).isLessThan(51);
				}
			}
		}
	}


	@Test
	public void Aged_Brie_should_increase_in_quality_the_older_it_gets(){

		GildedRose inn = new GildedRose();
		CorruptedItems brie = inn.get("Aged Brie");
		int quality = brie.getItem().quality;

		for(int i=0; i < 100; i++)
		{
			inn.updateQuality();
			if(quality < 50)
				assertThat(brie.getItem().quality).isGreaterThan(quality);
		 	quality = brie.getItem().quality;
		}
	}

	@Test
	public void Sulfuras_cant_be_sold(){

		GildedRose inn = new GildedRose();
		List<CorruptedItems> items = inn.getItem();

		for(int i =0; i < 100; i++)
		{
			inn.updateQuality();
			for (CorruptedItems it:items
			) {
				if (LegendaryItems.isLegendary(it))
				{
					assertThat(it.getItem().getName()).isEqualTo("Sulfuras, Hand of Ragnaros");
				}
			}
		}
	}

	@Test
	public void Quality_degrades_twice_as_fast_when_the_sell_by_date_has_passed(){

		GildedRose inn = new GildedRose();
		List<CorruptedItems> items = inn.getItem();

		for(int i =0; i < 100; i++)
		{
			inn.updateQuality();

			for (CorruptedItems it:items)
			{
				if(it.getItem().getSellIn() == 0){
					// assertThat(it.getItem().quality).isEqualTo();
				}
			}
		}
	}
}
