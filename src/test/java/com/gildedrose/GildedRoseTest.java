package com.gildedrose;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class GildedRoseTest {

    @Test
    void foo() {
        Item[] items = new Item[] { new Item("foo", 0, 0) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals("foo", app.items[0].name);
    }

    @Test
    void hasToString() {
        Item[] items = new Item[] { new Item("foo", 10, 10) };
        GildedRose app = new GildedRose(items);
        assertEquals("foo, 10, 10", app.items[0].toString());
    }

    @Test
    void hasSellInValue() {
        Item[] items = new Item[] { new Item("foo", 5, 0) };
        GildedRose app = new GildedRose(items);
        assertEquals(5, app.items[0].sellIn);
    }

    // Test Sell In
    @Test
    void sellInValueOneDay() {
        Item[] items = new Item[] { new Item("foo", 5, 0) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(4, app.items[0].sellIn);
    }

    // Test each item has a value
    @Test
    void hasQualityValue() {
        Item[] items = new Item[] { new Item("foo", 5, 10) };
        GildedRose app = new GildedRose(items);
        assertEquals(10, app.items[0].quality);
    }

    // Test Quality of item name "foo"
    @Test
    void qualityValueOneDay() {
        Item[] items = new Item[] { new Item("foo", 5, 10) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(9, app.items[0].quality);
    }

    // Test case for "Aged Brie"

    // Test Case - Name = " Aged Brie" , Quality increases as he gets older
    @Test
    void qualityValueAgedBrieQulaityIncreaseUpdate() {
        Item[] items = new Item[] { new Item("Aged Brie", 5, 10) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(11, app.items[0].quality);
    }

    // Test Case - Name = " Aged Brie" , Quality never exceed 50
    @Test
    void qualityValueAgedBrieNeverExceed50() {
        Item[] items = new Item[] { new Item("Aged Brie", 5, 50) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(50, app.items[0].quality);
    }

    // Test Case - Name = " Aged Brie" , Quality increases by 1 even when sellin < 0
    @Test
    void qualityValueAgedBrieQualityIncreasesSellIn0() {
        Item[] items = new Item[] { new Item("Aged Brie", 0, 10) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(12, app.items[0].quality);
    }


    // Item Quality should not be more than 50
    @Test
    void qualityNotExceed50() {
        Item[] items = new Item[] { new Item("foo", 5, 55) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertFalse(app.items[0].quality < 50, "Quality must be not more than 50");
    }

    @Test
    void qualityDegradeTwice() {
        Item[] items = { new Item("foo",0,10) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(8, app.items[0].quality);
    }

    // Quality of item is never negative
    @Test
    void hasQualityNeverZero() {
        Item[] items = { new Item("foo",0,0) };
        GildedRose app = new GildedRose(items);
        assertEquals(0, app.items[0].quality);
    }

    // Quality of item is never negative after update
    @Test
    void QualityNeverZeroUpdate() {
        Item[] items = { new Item("foo",0,0) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        app.updateQuality();
        assertEquals(0, app.items[0].quality);
    }

    // "Sulfuras", being a legendary item, never  decreases in Quality
    @Test
    void sulfurasQualityNeverDecrease() {
        Item[] items = { new Item("Sulfuras, Hand of Ragnaros",10,10) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        app.updateQuality();
        assertEquals(10, app.items[0].quality);
    }

    // "Sulfuras", being a legendary item, never has to be sold
    @Test
    void sulfurasSellInNeverDecrease() {
        Item[] items = { new Item("Sulfuras, Hand of Ragnaros",10,10) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        app.updateQuality();
        assertEquals(10, app.items[0].sellIn);
    }

    /*
     * "Backstage passes", like aged brie, increases in Quality as its SellIn value approaches; ]
     * Increase by 2 after each update if sellIn 10 days or less
     */
    @Test
    void backstageQualityIncreases2() {
        Item[] items = { new Item("Backstage passes to a TAFKAL80ETC concert",10,10) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        app.updateQuality();
        assertEquals(14, app.items[0].quality);
    }

    /*
     * "Backstage passes", like aged brie, increases in Quality as its SellIn value approaches; ]
     * Increase by 2 after each update if sellIn 10 days or less
     */
    @Test
    void backstageQualityIncreases3() {
        Item[] items = { new Item("Backstage passes to a TAFKAL80ETC concert",5,10) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(13, app.items[0].quality);
    }

    /*
     * "Backstage passes", like aged brie, increases in Quality as its SellIn value approaches; ]
     * Increase by 2 after each update if sellIn 10 days or less
     */
    @Test
    void backstageQualityLoseAllQuality() {
        Item[] items = { new Item("Backstage passes to a TAFKAL80ETC concert",0,10) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(0, app.items[0].quality);
    }

}


