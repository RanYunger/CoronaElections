package ID318783479_ID316334473;

import java.time.YearMonth;
import java.util.Scanner;

// Helper class, should aid the Elections main class (and other classes ass well...?)
public class TUI {
	// Constants

	public static void runElectionProcess(YearMonth electionsDate) {
		// TODO: implement this method
	}

	public static int menu(Scanner scan, boolean electionsOccurred) {
		System.out.println("To add a ballot, enter 1");
		System.out.println("To add a citizen, enter 2");
		System.out.println("To add a party, enter 3");
		System.out.println("To add a candidate, enter 4");
		System.out.println("To see all ballots, enter 5");
		System.out.println("To see all citizens, enter 6");
		System.out.println("To see all parties, enter 7");
		System.out.println("To start the election process, enter 8");
		if (electionsOccurred)
			System.out.println("To see the election results, enter 9");
		System.out.println("To exit, enter 10");
		
		return scan.nextInt();
	}
	public static int[] quickSort(int arr[], int low, int high) { 
		int pi;
		
		if(low < high) {
			pi = partition(arr, low, high); 
			quickSort(arr, low, pi-1);
			quickSort(arr, pi+1, high);
		}
		
		return arr;
	} 
	private static int partition(int arr[], int low, int high) { 
		int pivot = arr[high], i = low - 1, temp;
		for (int j=low; j<high; j++) { 
			if (arr[j] < pivot) {
				i++; 
				temp = arr[i]; 
				arr[i] = arr[j]; 
				arr[j] = temp; 
			}
		}
		temp = arr[i+1]; 
		arr[i+1] = arr[high]; 
		arr[high] = temp; 
		
		return i+1;
	}
}
