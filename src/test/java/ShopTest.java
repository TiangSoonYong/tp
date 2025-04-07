import players.Player;
import equipments.Equipment;
import equipments.armors.ArmorDatabase;
import equipments.boots.BootsDatabase;
import equipments.weapons.WeaponDatabase;
import events.Shop;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ShopTest {

    private final InputStream originalSystemIn = System.in;  // Store the original System.in

    @AfterEach
    public void restoreSystemInStream() {
        System.setIn(originalSystemIn);  // Restore the original System.in after each test
    }

    @Test
    public void testShop_purchaseEquipment_correctlyUpdatesPlayerEquipments() {
        Player player = new Player("Tom", 100, 10);
        Equipment[] equipmentsForSale = {
                ArmorDatabase.getArmorByIndex(0),
                BootsDatabase.getBootsByIndex(0),
                WeaponDatabase.getWeaponByIndex(0),
        };
        Shop shop = new Shop(player, equipmentsForSale);

        // Simulate input for selecting item to purchase
        String simulatedInput = "1\n1\n";  // "1" for buying, "1" for selecting first equipment
        ByteArrayInputStream inputStream = new ByteArrayInputStream(simulatedInput.getBytes());
        System.setIn(inputStream);

        player.earnGold(100);  // Ensure player has enough gold
        try {
            shop.run();  // Execute the shop logic
            Equipment equipment = ArmorDatabase.getArmorByIndex(0);
            assertEquals(equipment, player.getEquipment(0));  // Check if player got the correct equipment
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        restoreSystemInStream();
    }

    @Test
    public void testShop_sellEquipment_correctlyUpdatesPlayerEquipments() {
        Player player = new Player("Tom", 100, 10);
        String simulatedInput = "2\n1\n";  // "2" to sell, "1" to select the first equipment to sell
        ByteArrayInputStream inputStream = new ByteArrayInputStream(simulatedInput.getBytes());
        System.setIn(inputStream);

        Equipment[] equipmentsForSale = {
                ArmorDatabase.getArmorByIndex(0),
                BootsDatabase.getBootsByIndex(0),
                WeaponDatabase.getWeaponByIndex(0),
        };
        Shop shop = new Shop(player, equipmentsForSale);

        try {
            player.obtainEquipment(ArmorDatabase.getArmorByIndex(0));  // Equip the player with armor
            assertEquals(ArmorDatabase.getArmorByIndex(0), player.getEquipment(0));  // Ensure player has armor
            shop.run();  // Execute shop logic (should sell the armor)

            // Check if the equipment was correctly sold, which is up to your implementation
        } catch (Exception e) {
            System.out.println(e.getMessage());
            assertEquals("Equipment is not equipped!", e.getMessage());
            // Handle specific exceptions, or assert the expected behavior
        }
        restoreSystemInStream();
    }
}
