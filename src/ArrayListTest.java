import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import utility.collection.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

import static junit.framework.TestCase.assertEquals;

public class ArrayListTest {
  private ArrayList arrayList = new ArrayList();

  @BeforeEach void setUp()
  {
    System.out.println("--> setUp()");
    arrayList = new ArrayList();
  }

  @AfterEach void tearDown(){
    System.out.println("<-- tearDown()");;}

  @Test public void addZero()
  {
    assertEquals(0, arrayList.size());
  }

  @Test public void addOne()
  {
    arrayList.add(3);
    assertEquals(3, arrayList.get(0));
  }

  @Test public void addMany()
  {
    for (int i = 0; i < 100; i++)
    {
      arrayList.add(i);
      assertEquals(i, arrayList.get(i));
    }
    assertEquals(100, arrayList.size());
  }

  @Test public void addBoundary()
  {
    assertThrows(IndexOutOfBoundsException.class, () -> {
      arrayList.add(-1, 3);
    });

    arrayList.add(0, 3);
    assertEquals(3, arrayList.get(0));

    arrayList.add(1, 4);
    assertEquals(4, arrayList.get(1));

    assertThrows(IndexOutOfBoundsException.class, () -> {
      arrayList.add(100, 1);
    });
  }

  @Test public void addExceptions()
  {
    assertThrows(IndexOutOfBoundsException.class, () -> {
      arrayList.add(-1, 3);
    });

    assertThrows(IndexOutOfBoundsException.class, () -> {
      arrayList.add(100, 1);
    });
  }

  @Test public void getZero() // Expected IndexOutOfBoundsException got IllegalStateException
  {
    assertThrows(IndexOutOfBoundsException.class, () -> {
      arrayList.get(0);
    });
  }

  @Test
  public void getOne()
  {
    arrayList.add(1);
    assertEquals(1,arrayList.get(0));
  }

  @Test public void getMany()
  {
    for (int i = 0; i < 100; i++)
    {
      arrayList.add(i);
      assertEquals(i, arrayList.get(i));
    }
  }

  @Test public void getBoundary()
  {
    assertThrows(IndexOutOfBoundsException.class, () -> {
      arrayList.get(-1);
    });

    for (int i = 0; i < 6; i++)
    {
      arrayList.add(i);
      assertEquals(i, arrayList.get(i));
    }
    System.out.println(arrayList.size());
    assertThrows(IndexOutOfBoundsException.class, () -> {
      arrayList.get(7);
    });
  }

  @Test public void getExceptions() // Expected IndexOutOfBoundsException got IllegalStateException
  {
    for (int i = 0; i < 100; i++)
    {
      arrayList.add(i);
    }

    assertThrows(IndexOutOfBoundsException.class, () -> {
      arrayList.get(-2);
    });

    assertThrows(IndexOutOfBoundsException.class, () -> {
      arrayList.get(102);
    });
  }

  @Test public void removeElementAtWrongIndex()
  {
    System.out.println(arrayList.size());
    assertThrows(IndexOutOfBoundsException.class, () -> {
      arrayList.remove(3);
    });
  }

  @Test public void gettingIndexOfUnspecifiedElement()
  {
    for (int i = 0; i < 5; i++)
    {
      arrayList.add("Element " + i);
    }
    assertEquals(-1, arrayList.indexOf("Element 10"));
  }

  @Test public void gettingIndexOfElement()
  {
    for (int i = 0; i < 5; i++)
    {
      arrayList.add("Element " + i);
    }
    assertEquals(2, arrayList.indexOf("Element 2"));
  }

  @Test public void containsTrue()
  {
    for (int i = 0; i < 5; i++)
    {
      arrayList.add("Element " + i);
    }
    assertTrue(arrayList.contains("Element 2"));
  }

  @Test public void containsFalse()
  {
    for (int i = 0; i < 5; i++)
    {
      arrayList.add("Element " + i);
    }
    assertFalse(arrayList.contains("Element 100"));
  }

  @Test // returns true but should be false
  public void isEmptyWhenFull()
  {
    for (int i = 0; i < 100; i++)
    {
      arrayList.add("Element");
    }
    System.out.println(arrayList.size());
    System.out.println(arrayList.isEmpty());
    assertFalse(arrayList.isEmpty());
  }

  @Test public void isEmptyWhenEmpty()
  {
    assertTrue(arrayList.isEmpty());
    assertTrue(arrayList.isEmpty());
  }

  @Test //Method always returns true
  public void isFullWhenEmpty()
  {
    assertEquals(true,arrayList.isFull());
  }

  @Test public void sizeWhenEmpty()
  {
    assertEquals(0, arrayList.size());
  }

  @Test public void size()
  {
    for (int i = 0; i < 7; i++)
    {
      arrayList.add(i);
    }
    assertEquals(7, arrayList.size());
  }

  @Test
  public void add1Item() {
    arrayList.add("add");
    assertEquals(true,arrayList.get(0).equals("add"));
  }

  @Test
  public void add2Items() {
    arrayList.add("A");
    arrayList.add("C");
    assertEquals(true,arrayList.get(0).equals("A")||arrayList.get(1).equals("C"));
  }

  @Test
  public void add1ItemAtIndex() {
    arrayList.add(0, "add");
    assertEquals(true,arrayList.get(0).equals("add"));
  }

  @Test
  public void add2ItemsAtIndex() {
    arrayList.add(0, "A");
    arrayList.add(1, "B");
  }

  @Test
  public void isContain() {
    arrayList.add("ABC");
    arrayList.add(123);

    assertEquals(false, arrayList.contains("BCD"));
    assertEquals(true, arrayList.contains("ABC"));
    assertEquals(false, arrayList.contains(222));
    assertEquals(true, arrayList.contains(123));
  }

  @Test
  public void get() {
    arrayList.add("ABC");
    arrayList.add(123);
    arrayList.add(0, "A");
    arrayList.add(1, "B");
    arrayList.add(0, "add");

    assertEquals("A", arrayList.get(1));
    assertEquals("add", arrayList.get(0));
  }

  @Test
  public void indexOf() {
    arrayList.add("ABC");
    arrayList.add(123);
    arrayList.add(0, "A");
    arrayList.add(1, "B");
    arrayList.add(0, "add");

    assertEquals(0, arrayList.indexOf("add"));
    assertEquals(1, arrayList.indexOf("A"));
  }

  @Test
  public void isEmpty() {
    assertEquals(true, arrayList.isEmpty());
  }

  @Test
  public void isEmpty2() {
    arrayList.add("ABC");
    arrayList.add(123);
    arrayList.add(0, "A");
    arrayList.add(1, "B");
    arrayList.add(0, "add");

    assertEquals(false, arrayList.isEmpty());
  }

  @Test
  public void isFull2() {
    ArrayList list = new ArrayList();
    //default capacity is 100

    for (int i = 0; i <99 ; i++)
    {
      list.add(i);
    }

    assertEquals(true, list.isFull());

    //        @Override
    //        public boolean isFull()
    //        {
    //            return true;
    //        }
    // in the jar this is the code of this method, it will always return true
  }

  @Test
  public void remove() {
    arrayList.add("ABC");
    arrayList.add("123");
    arrayList.add(0, "A");
    arrayList.add(1, "B");
    arrayList.add(0, "add");

    arrayList.remove(0);
    assertEquals(false, arrayList.contains("add"));

    arrayList.remove(1);
    assertEquals(false, arrayList.contains("B"));

    arrayList.remove("123");
    assertEquals(false, arrayList.contains("123"));
  }

  @Test
  public void set() {
    arrayList.add("ABC");
    arrayList.add(123);
    arrayList.add(0, "A");
    arrayList.add(1, "B");
    arrayList.add(0, "add");

    arrayList.set(0, "change");
    assertEquals(false, arrayList.contains("add"));
    assertEquals("change", arrayList.get(0));
  }

  @Test
  public void size1() {
    assertEquals(0, arrayList.size());
  }

  @Test
  public void size2() {
    arrayList.add("ABC");
    arrayList.add(123);
    arrayList.add(0, "A");
    arrayList.add(1, "B");
    arrayList.add(0, "add");

    assertEquals(5, arrayList.size());
  }

  @Test
  public void listToString() {
    arrayList.add("ABC");
    arrayList.add(123);
    arrayList.add(0, "A");
    arrayList.add(1, "B");
    arrayList.add(0, "add");

    System.out.println(arrayList.toString());
  }

  @Test
  public void remove1() {
    for (int i = 0; i < 4; i++) {
      arrayList.add(i);
    }
    for (int i = 0; i < 2; i++) {
      arrayList.remove(0);
    }
    System.out.println(arrayList.size());
  }
}