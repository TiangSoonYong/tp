package Game.Characters;

import Exceptions.RolladieException;

import java.util.ArrayList;
import java.util.List;

public class EnemyDatabase {
    private static final ArrayList<Enemy> enemyList = new ArrayList<>();

    // Static block to initialize predefined enemies
    static {
        enemyList.add(new Enemy(new int[]{50}, 20, 10, "Goblin"));
        enemyList.add(new Enemy(new int[]{50}, 10, 20, "Zombie"));
        enemyList.add(new Enemy(new int[]{50, 50, 50}, 60, 30, "Goblin Gang"));
        enemyList.add(new Enemy(new int[]{50, 50, 50}, 30, 60, "Zombie Outbreak"));
        enemyList.add(new Enemy(new int[]{300}, 150, 150, "Dragon"));
        enemyList.add(new Enemy(new int[]{100, 100, 100}, 333, 333, "Hydra"));
    }

    // Get all enemies
    public static ArrayList<Enemy> getAllEnemy() {
        return enemyList;
    }

    /**
     * Find enemy based on enemyName
     *
     * @param name Name of the armor being queried
     * @return Corresponding armor
     */
    public static Enemy getEnemyByName(String name) throws RolladieException {
        for (Enemy enemy : enemyList) {
            if (enemy.getEnemyName().equalsIgnoreCase(name)) {
                return enemy;
            }
        }
        throw new RolladieException("Enemy not found!");
    }

    /**
     * Selects enemy by given index
     * @param index
     * @return Enemy object
     */
    public static Enemy getEnemyByIndex(int index) {
        return enemyList.get(index);
    }
}
