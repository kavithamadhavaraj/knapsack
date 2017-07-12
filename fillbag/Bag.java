package fillbag;
import java.io.BufferedReader;
import java.io.File;
import java.util.Scanner;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import java.util.Comparator;

public class Bag{
	public static final int MAX_BAG_WEIGHT = 100;
	public static Comparator<Item> customComparator = new Comparator<Item>() {
        @Override
        public int compare(Item o1, Item o2)
        {
			int result = Float.compare(o2.getCost(), o1.getCost());
	       	return result == 0 ? Float.compare(o1.getWeight(), o2.getWeight()) : result;
        }
    };
	public static List<Integer> fillBag(Float bag_weight, List<Item> items){
		List<Integer> selected = new ArrayList<>();
		Collections.sort(items, customComparator);
		for (Item item : items) {
			if(item.getWeight() <= bag_weight){
				bag_weight-= item.getWeight();
				selected.add(item.getIndex());
			}		
		}
		return selected;
	}
    public static void main(String[] args) throws Exception {
        try {
			Scanner sc = new Scanner(System.in);
           	System.out.println("Please enter a valid path:");
            File f = new File(sc.nextLine());
			BufferedReader b = new BufferedReader(new FileReader(f));
			String readLine;
			Float bag_weight;
			List<Item> items;
            while ((readLine = b.readLine()) != null) {				
				items = new ArrayList<>();
				String[] testCase = readLine.split(" : ");
				bag_weight = Float.parseFloat(testCase[0]);
				if(bag_weight <= MAX_BAG_WEIGHT){
					for(String item : testCase[1].split(" ")){
						String[] listItems = (item.replaceAll("[^0-9/.,]+","")).split(",");					
						int index = Integer.parseInt(listItems[0]);
						Float mass = Float.parseFloat(listItems[1]);
						Float price = Float.parseFloat(listItems[2]);
						if(mass <= 100.0 && price <= 100.0){
							Item currItem = new Item();
							currItem.setCost(price);
							currItem.setIndex(index);
							currItem.setWeight(mass);
							items.add(currItem);
						}
						else{
							System.out.println("Error: Case Item exceeds value of 100. Ignoring.");
							items.clear();
							break;
						}						
					}
					if(items.size() > 0)
						System.out.println(fillBag(bag_weight, items).toString().replaceAll("[\\s\\[\\]]", ""));			
				}
				else{
                	System.out.println("Error: Case exceeds maximum bag capacity of 100. Ignoring.");
				}
            }

        }catch(FileNotFoundException e){		
        	System.out.println("Error: Invalid path.");

		}catch (Exception e) {
            e.printStackTrace();
        }

    }
}
