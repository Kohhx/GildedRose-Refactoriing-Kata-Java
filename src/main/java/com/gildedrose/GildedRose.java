package com.gildedrose;

class GildedRose {
    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        Normal normal = new Normal();
        AgedBrie ageBrie = new AgedBrie();
        Backstage backstage = new Backstage();
        Sulfuras sulfuras = new Sulfuras();

        for (Item item: items) {
            if (isAgedBrie(item)) {
                ageBrie.update(item);
            } else if (isBackstage(item)) {
                backstage.update(item);
            } else if (isSulfuras(item)) {
                sulfuras.update(item);
            } else {
                normal.update(item);
            }
        }
    }

    public static boolean isAgedBrie(Item item) {
        return item.name.equals("Aged Brie");
    }

    public static boolean isBackstage(Item item) {
        return item.name.equals("Backstage passes to a TAFKAL80ETC concert");
    }

    public static boolean isSulfuras(Item item) {
        return item.name.equals("Sulfuras, Hand of Ragnaros");
    }

}
class Normal {

    public void update(Item item) {
        decrementQuality(item, 1);
        decrementSellIn(item, 1);
        if (item.sellIn < 0) {
            decrementQuality(item, 1);
        }
    }

    // All utility functions below
    protected void decrementSellIn(Item item, int num) {
        item.sellIn -= num;
    }

    protected void decrementQuality(Item item, int num) {
        item.quality -= num;
        if (item.quality < 0) {
            item.quality = 0;
        }
    }

    protected void incrementQuality(Item item, int num) {
        item.quality += num;
        if (item.quality > 50) {
            item.quality = 50;
        }
    }
}

class AgedBrie extends Normal{
    @Override
    public void update(Item item) {
        incrementQuality(item, 1);
        decrementSellIn(item, 1);
        if (item.sellIn < 0) {
            incrementQuality(item, 1);
        }
    }

}

class Backstage extends Normal{
    @Override
    public void update(Item item) {
        updateQualityBefore(item);
        decrementSellIn(item, 1);
        if (item.sellIn < 0) {
            item.quality = 0;
        }
    }

    public void updateQualityBefore(Item item) {
            if (item.sellIn < 6) {
                incrementQuality(item, 3);
            } else if (item.sellIn < 11) {
                incrementQuality(item, 2);
            } else {
                incrementQuality(item, 1);
            }
    }
}

class Sulfuras extends Normal{
    @Override
    public void update(Item item) {
    }

}
