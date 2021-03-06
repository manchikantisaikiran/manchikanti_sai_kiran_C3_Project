import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.*;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

class RestaurantTest {
    Restaurant restaurant;
    //REFACTOR ALL THE REPEATED LINES OF CODE
    @BeforeEach()
    public void beforeEachTest(){
        LocalTime openingTime = LocalTime.parse("10:30:00");
        LocalTime closingTime = LocalTime.parse("22:00:00");
        restaurant = new Restaurant("Amelie's cafe","Chennai",openingTime,closingTime);
        restaurant.addToMenu("Sweet corn soup",119);
        restaurant.addToMenu("Vegetable lasagne", 269);

    }

    //>>>>>>>>>>>>>>>>>>>>>>>>>OPEN/CLOSED<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    //-------FOR THE 2 TESTS BELOW, YOU MAY USE THE CONCEPT OF MOCKING, IF YOU RUN INTO ANY TROUBLE
    @Test
    public void is_restaurant_open_should_return_true_if_time_is_between_opening_and_closing_time(){
        //Test Case passes only if the local time is between 10:30:00 and 22:00:00
        //other than above timings the Restaurant will be closed so Test Case Fails
         assertTrue(restaurant.isRestaurantOpen());
    }

    @Test
    public void is_restaurant_open_should_return_false_if_time_is_outside_opening_and_closing_time(){
        //Test Case passes only if the local time is outside 10:30:00 and 22:00:00
        //other than above timings the Restaurant will be opened so Test Case Fails
        assertFalse(restaurant.isRestaurantOpen());
    }

    //<<<<<<<<<<<<<<<<<<<<<<<<<OPEN/CLOSED>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>


    //>>>>>>>>>>>>>>>>>>>>>>>>>>>MENU<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    @Test
    public void adding_item_to_menu_should_increase_menu_size_by_1(){
        int initialMenuSize = restaurant.getMenu().size();
        restaurant.addToMenu("Sizzling brownie",319);
        assertEquals(initialMenuSize+1,restaurant.getMenu().size());
    }
    @Test
    public void removing_item_from_menu_should_decrease_menu_size_by_1() throws itemNotFoundException {
        int initialMenuSize = restaurant.getMenu().size();
        restaurant.removeFromMenu("Vegetable lasagne");
        assertEquals(initialMenuSize-1,restaurant.getMenu().size());
    }
    @Test
    public void removing_item_that_does_not_exist_should_throw_exception() {
        assertThrows(itemNotFoundException.class,
                ()->restaurant.removeFromMenu("French fries"));
    }
    //<<<<<<<<<<<<<<<<<<<<<<<MENU>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

    @Test
    public void when_an_item_is_added_to_menu_total_menu_cost_should_be_increased_by_price_of_added_item() {
        int totalCost = restaurant.totalOrderCost(restaurant.getMenu());
        restaurant.addToMenu("Sizzling brownie",319);
        assertEquals(totalCost + 319,restaurant.totalOrderCost(restaurant.getMenu()));
    }

    @Test
    public void when_an_item_is_removed_from_menu_total_menu_cost_should_be_decreased_by_price_of_removed_item() throws itemNotFoundException {
        int totalCost = restaurant.totalOrderCost(restaurant.getMenu());
        restaurant.removeFromMenu("Vegetable lasagne");
        assertEquals(totalCost - 269,restaurant.totalOrderCost(restaurant.getMenu()));
    }

    @Test
    public void when_no_items_in_menu_then_the_order_total_should_be_zero() {
        int totalCost = restaurant.totalOrderCost(restaurant.getMenu());
        assertEquals(totalCost,0);
    }

}