package ru.luxoft.cources;

public class Main {

    public static void main(String[] args) {
		Jeans jeans1; //= new Jeans();
		MyArrayList1<Jeans> myArrayList = new MyArrayList1<>();
		MyArrayList1<Integer> myArrayList2 = new MyArrayList1<>();
 		int j = 65;
	    float p = 30;
	    String s = "";
        String NameJeans = "AAA";

		for (int i = 0; i < 10; i++) {

		jeans1 = new Jeans();
		NameJeans  +=  (char)j  ;
		j ++;
		jeans1.setModel(NameJeans);
		jeans1.setPrice(p+j);
		jeans1.setCount(i);
	    s = "XXX";
		jeans1.setSize(s);

		myArrayList.add(jeans1);

	    }

		for (int i = 0; i < myArrayList.size(); i++) {
			System.out.println(myArrayList.get(i));
		}
		myArrayList.remove(0);
		myArrayList.remove(5);
		myArrayList.remove(12);
		for (int i = 0; i < myArrayList.size(); i++) {
			System.out.println(myArrayList.get(i));
		}
		System.out.println("----------------- myArrayList2 -------------------");
		myArrayList2.add(1);
	    myArrayList2.add(2);
		myArrayList2.add(3);
		myArrayList2.add(4);
		myArrayList2.add(5);
		myArrayList2.add(6);
		myArrayList2.add(7);
		myArrayList2.add(8);
		myArrayList2.add(9);
		myArrayList2.add(10);
		myArrayList2.add(11);
		myArrayList2.add(12);
		myArrayList2.add(13);
		myArrayList2.add(14);
		myArrayList2.add(15);


		for (int i = 0; i < myArrayList2.size(); i++) {
			System.out.println(myArrayList2.get(i));
		}
	 	myArrayList2.remove(0);
		myArrayList2.remove(5);
		myArrayList2.remove(12);

		for (int i = 0; i < myArrayList2.size(); i++) {
			System.out.println(myArrayList2.get(i));
		}

    }
}
