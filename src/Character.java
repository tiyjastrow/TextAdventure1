/**
 * Created by johnjastrow on 2/7/17.
 */
public class Character {
    String name;
    int health;
    int damage;

    public void battle(Character enemy) {
        System.out.printf("%s appears!\n", enemy.name);

        while (bothPlayersAlive(enemy)){
            inflictDamage(this, enemy);
            inflictDamage(enemy, this);
            showHealthStatus(enemy);
        }

        showWhenDead(this);
        showWhenDead(enemy);
    }

    private void showWhenDead(Character player) {
        String message = "%s has died.\n";
        if (player.health <= 0){
            System.out.printf(message, player.name);
        }
    }


    private void inflictDamage(Character fromPlayer, Character toPlayer){
        toPlayer.health = toPlayer.health - fromPlayer.damage;
    }

    private boolean bothPlayersAlive(Character player2) {
        return health > 0 && player2.health > 0;
    }

    private void showHealthStatus(Character otherPlayer) {
        System.out.printf("%s's health: %d\n",  otherPlayer.name,  otherPlayer.health);
        System.out.printf("%s's health: %d\n", this.name, this.health);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
