package christmas;

import christmas.Present;
import christmas.PresentBag;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Collection;

public class ChristmasTests {
    private Present present;
    private PresentBag presentBag;

    @Before
    public void createInstances() {
        this.presentBag = new PresentBag();
        this.present = new Present("Name", 1.5);
    }

    @Test(expected = NullPointerException.class)
    public void shouldThrowExceptionIfNullPresent() {
        this.presentBag.create(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionIfTwoSamePresentAreAdd() {
        this.presentBag.create(present);
        this.presentBag.create(present);
    }

    @Test
    public void shouldAddCorrectly() {
        String message = this.presentBag.create(present);
        String result = "Successfully added present Name with magic 1.50";
        Assert.assertEquals(message,result);
    }

    @Test
    public void shouldGetCorrectSize() {
        this.presentBag.create(present);
        this.presentBag.create(new Present("Ivo",1.2));
        int resultSize = this.presentBag.getCount();
        Assert.assertEquals(resultSize,2);
    }

    @Test(expected = NullPointerException.class)
    public void shouldThrowExceptionRemoveFirst() {

        this.presentBag.remove(null);

    }

    @Test(expected = NullPointerException.class)
    public void shouldThrowExceptionRemoveSecond() {

        this.presentBag.remove("              ");

    }

    @Test
    public void removeMethodShouldWorkCorrectly() {

        this.presentBag.create(present);
        this.presentBag.create(new Present("Ivo",1.2));

        boolean result = this.presentBag.remove("Ivo");

        Assert.assertTrue(result);
    }

    @Test
    public void removeMethodShouldWorkCorrectlyAndDecreaseSizeByOne() {

        this.presentBag.create(present);
        this.presentBag.create(new Present("Ivo",1.2));

        boolean result = this.presentBag.remove("Ivo");

        int size = this.presentBag.getCount();
        Assert.assertEquals(size,1);
    }


    @Test
    public void getLeastMagicShouldWork() {

        this.presentBag.create(present);
        this.presentBag.create(new Present("Ivo",1.20));

        Present presentWithLeastMagic = this.presentBag.getPresentWithLeastMagic();


        Assert.assertEquals(presentWithLeastMagic.getMagic(),1,20);
    }

    @Test
    public void getLeastMagicShouldWorkWithNull() {

        Present present = this.presentBag.getPresentWithLeastMagic();


        Assert.assertEquals(present,null);
    }


    @Test
    public void getPresentShouldWork() {

        this.presentBag.create(present);
        this.presentBag.create(new Present("Ivo",1.20));

        Present present = this.presentBag.getPresent("Ivo");

        Assert.assertEquals(present.getName(),"Ivo");
    }

    @Test
    public void getPresentShouldWorkWithNull() {

        Present present = this.presentBag.getPresent("Nobody!!!");

        Assert.assertEquals(present,null);
    }

    @Test(expected = Exception.class)
    public void getUnmodifiableCollectionShouldThrowExceptionClear() {

        Collection<Present> presents = this.presentBag.getPresents();
        presents.clear();
    }

    @Test(expected = Exception.class)
    public void getUnmodifiableCollectionShouldThrowExceptionAdd() {

        Collection<Present> presents = this.presentBag.getPresents();
        presents.add(present);
    }

    @Test(expected = Exception.class)
    public void getUnmodifiableCollectionShouldThrowExceptionRemove() {

        Collection<Present> presents = this.presentBag.getPresents();
        presents.remove("Nobody!!!");
    }

}
