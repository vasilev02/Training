package halfLife;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class PlayerTests {

    private Gun gunOne;
    private Gun gunTwo;
    private Player player;

    @Before
    public void implementFields(){

        this.gunOne = new Gun("Makarov",10);
        this.gunTwo = new Gun("Cob",5);

        this.player = new Player("Gosho",100);
    }

    @Test
    public void testGetUsername(){

        String username = player.getUsername();
        Assert.assertEquals("Gosho",username);
    }

    @Test
    public void testGetHealth(){

        int health = player.getHealth();
        Assert.assertEquals(100,health);
    }

    @Test(expected = NullPointerException.class)
    public void testInvalidNameWithNull(){

        Player player = new Player(null,100);
    }

    @Test(expected = NullPointerException.class)
    public void testInvalidNameWithEmptyName(){

        Player player = new Player("",100);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidHealth(){

        Player player = new Player("Stamat",-1);
    }

    @Test(expected = NullPointerException.class)
    public void testAddGun(){

        this.player.addGun(null);
    }

    @Test
    public void testAddGunWork(){

        this.player.addGun(gunOne);
        Assert.assertEquals(1,this.player.getGuns().size());
    }

    @Test
    public void testRemoveGunWork(){

        this.player.addGun(gunOne);
        this.player.addGun(gunTwo);
        boolean result = this.player.removeGun(gunOne);
        Assert.assertTrue(result);
    }

    @Test
    public void getCollection(){

        List<Gun> guns = this.player.getGuns();
        this.player.addGun(gunOne);
        Assert.assertEquals(1,guns.size());
    }


    @Test
    public void takeDamage(){

        Player player = new Player("Nasko",100);

        player.takeDamage(10);
        Assert.assertEquals(90,player.getHealth());
    }

    @Test(expected = IllegalStateException.class)
    public void takeDamageNotWorking(){

        Player player = new Player("Nasko",0);

        player.takeDamage(10);

    }

    @Test
    public void takeDamageWorking(){

        Player player = new Player("Nasko",5);

        player.takeDamage(10);
        Assert.assertEquals(0,player.getHealth());
    }


    @Test
    public void getGunWorking(){

        Player player = new Player("Nasko",5);

        Gun result = player.getGun("no");
        Assert.assertEquals(null,result);
    }

    @Test
    public void getGunWorking1(){

        Player player = new Player("Nasko",5);
        player.addGun(gunOne);

        Gun result = player.getGun("Makarov");
        Assert.assertEquals("Makarov",result.getName());
    }


}
