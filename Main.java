import java.io.*;
import java.util.*;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Main {
	
	public static class Apple {
        private int weight = 0;
        private String color = "";

        public int getId() {
        	return 1; 
        }
        
        public Apple(int weight, String color){
            this.weight = weight;
            this.color = color;
        }

        public Integer getWeight() {
            return weight;
        }

        public void setWeight(Integer weight) {
            this.weight = weight;
        }

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }

        public String toString() {
            return "Apple{" +
                   "color='" + color + '\'' +
                   ", weight=" + weight +
                   '}';
        }
    }
	
    public static List<Apple> filterApples(List<Apple> inventory, Predicate<Apple> p){
        List<Apple> result = new ArrayList<>();
        for(Apple apple : inventory){
            if(p.test(apple)){
                result.add(apple);
            }
        }
        return result;
    }       
	
    public static boolean isGreenApple(Apple apple) {
        return "green".equals(apple.getColor()); 
    }

    public static boolean isHeavyApple(Apple apple) {
        return apple.getWeight() > 150;
    }
    
    public static void main(String [] args) {
    	
		// -----------------------------------------------------------
    	// Filtering Examples
		// -----------------------------------------------------------
    	
        List<Apple> inventory = Arrays.asList(new Apple(80,"green"),
                new Apple(155, "green"),
                new Apple(120, "red"));	

		// [Apple{color='green', weight=80}, Apple{color='green', weight=155}]
		List<Apple> greenApples = filterApples(inventory, Main::isGreenApple);
		System.out.println(greenApples);
		
		// [Apple{color='green', weight=155}]
		List<Apple> heavyApples = filterApples(inventory, Main::isHeavyApple);
		System.out.println(heavyApples);
		
		// [Apple{color='green', weight=80}, Apple{color='green', weight=155}]
		List<Apple> greenApples2 = filterApples(inventory, (Apple a) -> "green".equals(a.getColor()));
		System.out.println(greenApples2);
		
		// [Apple{color='green', weight=155}]
		List<Apple> heavyApples2 = filterApples(inventory, (Apple a) -> a.getWeight() > 150);
		System.out.println(heavyApples2);
		
		// []
		List<Apple> weirdApples = filterApples(inventory, (Apple a) -> a.getWeight() < 80 || 
		                                         "brown".equals(a.getColor()));

		// -----------------------------------------------------------
    	// Sorting Examples 
		// -----------------------------------------------------------
		System.out.println("SORTING EXAMPLES");
		
    	// Old way of sorting
		inventory.sort(new Comparator<Apple>() {
			@Override
			public int compare(Apple o1, Apple o2) {
				return new Integer(o1.getWeight()).compareTo(o2.getWeight());
			}
		});
		inventory.stream().forEach(System.out::println);
    			
    	// In Java 8 you can use static method Comparator.comparing to do comparison by a single field 
    	// will do Integer.compareTo(Integer)
		inventory.sort(Comparator.comparing(Apple::getWeight));
		inventory.stream().forEach(System.out::println);
		
    }
}