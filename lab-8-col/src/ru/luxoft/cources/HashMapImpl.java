package ru.luxoft.cources;

import java.util.Objects;

class Entry<K, V> {
    // 1
    Object key;
    Object val;
    Entry<K, V> next;          //**

    public Entry(Object key, Object val) {
        this.key = key;
        this.val = val;
    }

    public Object setValueAndReturnOld(Object val) {
        Object tmp = this.val;
        this.val = val;
        return tmp;
    }

    public Object getKey() {
        return key;
    }

    public Object getVal() {
        return val;
    }

    public Entry<K, V> getNext() {
        return next;
    }

    public void setNext(Entry<K, V> next) {
        this.next = next;
    }

    public void setKey(Object key) {
        this.key = key;
    }
    public void setVal(Object val) {
        this.val = val;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || this.getClass().getName() != o.getClass().getName()) {
            return false;
        }
        Entry e = (Entry) o;
        if (this.key == e.key) {
            return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        int prime = 13;
        int mul = 11;
        if (key != null) {
            int hashCode = prime * mul + key.hashCode();
            return hashCode;
        }
        return 0;
    }
}

public class HashMapImpl {
    // 3
    private float loadfactor = 0.75f;
    private int capacity = 100;
    private int size = 0;
    private Entry table[] = new Entry[capacity];
    private Entry nulltable = null;
    // 4
    private int hashing(int hashCode) {
        int location = hashCode % capacity;
        System.out.println("Location :"+location);
        return location;
    }
    // 5
    public int size() {
        // TODO Auto-generated method stub
        return this.size;
    }
    // 6
    public boolean isEmpty() {
        return this.size == 0;
    }
    // 7
    public boolean containsKey(Object key) {

        if (key == null) {
            return nulltable != null;
        }
        return getEntryByKeyFromTable(key) != null;

        /*
        if(key == null) {
            if(table[0].getKey() == null) {
                return true;
            }
        }
        int location = Hashing(key.hashCode());
        Entry e = null;
        try {
            e = table[location];
        }catch(NullPointerException ex) {

        }
        if(e!= null && e.getKey() == key) {
            return true;
        }
        return false;
        */

    }

    private Entry getEntryByKeyFromTable(Object key) {
        int location = hashing(key.hashCode());
        Entry  element = table[location];
        while (element != null && !element.getKey().equals(key)) {
            element = element.getNext();
        }
        return element;
    }
    private Entry getEntryParentByKeyFromTable(Object key) {
        int location = hashing(key.hashCode());
        Entry element = table[location];
        if (element == null || element.getNext() == null) {
            return null;
        } else {
            while (element.getNext() != null && !element.getNext().getKey().equals(key)) {
                element = element.getNext();
            }
        }
        return element;
    }



    // 8
    public boolean containsValue(Object value) {
        for(int i=0; i<table.length;i++) {
            if(isValueInTable(i,value)){
                return true;
            }
        }
        return false;
    }
    private boolean isValueInTable(int location,Object value) {
        Entry element = table[location];
        while (element != null) {
            if (Objects.equals(value, element.getVal())) {
                return true;
            } else {
                element = element.getNext();
            }
        }
        return false;
    }


    // 9

    public Object get(Object key) {
            if (key == null) {
                return nulltable == null ? null : nulltable.getVal();
            } else {
                Entry entry = getEntryByKeyFromTable(key);
                return entry == null ? null : entry.getVal();
            }
        }

    private Entry getLastElementInTable(int location) {
        if (table[location] == null) {
            return null;
        } else {
            Entry element = table[location];
            while (element.getNext() != null) {
                element = element.getNext();
            }
            return element;
        }
    }


       /* Object ret = null;
        if(key == null) {
            Entry e = null;
            try{
                e = table[0];
            }catch(NullPointerException ex) {

            }
            if(e != null) {
                return  e.getVal();
            }
        } else {
            int location = hashing(key.hashCode());
            Entry e = null;
            try{
                e = table[location];
            }catch(NullPointerException ex) {

            }
            if(e!= null && e.getKey() == key) {
                return e.getVal();
            }
        }
        return ret;
        */


  // 10

    public Object put(Object key, Object val ) {
            if (size >= capacity) {
                System.out.println("Rehashing required");
                return null;
            }

            if (key == null) {
                if (nulltable == null) {
                    nulltable = new Entry<>(null, val);
                    size++;
                    return null;
                } else {
                    return nulltable.setValueAndReturnOld(val);
                }
            } else {
                Entry entry = getEntryByKeyFromTable(key);
                if (entry == null) {
                    int location = hashing(key.hashCode());
                    size++;
                    entry = getLastElementInTable(location);
                    Entry newEntry = new Entry<>(key, val);

                    if (entry == null) {
                        table[location] = newEntry;
                    } else {
                        entry.setNext(newEntry);
                    }
                    return null;
                } else {
                    return entry.setValueAndReturnOld(val);
                }
            }
        }
   /*   Object ret = null;
      if (key == null) {
          ret = putForNullKey(val);
          return ret;
      } else {
          int location = hashing(key.hashCode());
          if(location >= capacity) {
              System.out.println("Rehashing required");
              return null;
          }
          Entry e = null;
          try{
              e = table[location];
          }catch(NullPointerException ex) {

          }
          if (e!= null && e.getKey() == key) {
              ret = e.getVal();
          } else {
              Entry eNew = new Entry();
              eNew.setKey(key);
              eNew.setVal(val);
              table[location] = eNew;
              size++;
          }
      }
      return ret;

    */

    // 11
    private Object putForNullKey(Object val) {
        Entry e = null;
        try {
            e = table[0];
        }catch(NullPointerException ex) {

        }
        Object ret = null;
        if (e != null && e.getKey() == null) {
            ret = e.getVal();
            e.setVal(val);
            return ret;
        } else {
            Entry eNew = new Entry(null,val);
           // eNew.setKey(null);
           // eNew.setVal(val);
            table[0] = eNew;
            size++;
        }
        return ret;
    }
     // 12   System.out.println("for = "+ i +"-- " +table[i].getVal());
      /* public Object remove(Object key) {
         int location = hashing(key.hashCode());

         System.out.println("remove ");
         System.out.println("location = "+ location);
         Object ret = null;
        // if(table[location].getKey() == key) {
         if(table[location] != null && table[location].getKey().equals(key)) {
             for(int i=location; i<table.length-1;i++) {

                 table[i] = table[i+1];
             }
         }
         return ret;
     }
*/
         public Object remove (Object key) {
             if (key == null) {
                 if (nulltable != null) {
                     Object retValue = nulltable.getVal();
                     nulltable = null;
                     size--;
                     return retValue;
                 } else {
                     return null;
                 }
             }

             int location = hashing(key.hashCode());
             Entry  entry = getEntryParentByKeyFromTable(key);
             if (entry == null) {
                 if (table[location] != null && table[location].getKey().equals(key)) {
                     Object retValue = table[location].getVal();
                     table[location] = table[location].getNext();
                     size--;
                     return retValue;
                 } else {
                     return null;
                 }
             } else {
                Object retValue = entry.getNext().getVal();
                 entry.setNext(entry.getNext().getNext());
                 size--;
                 return retValue;
             }
         }



}
