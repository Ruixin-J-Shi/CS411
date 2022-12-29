
import java.util.Scanner;

public class Main {

	public static void print() {
		String functions = "1. For Customer\n" + "2. For Staff\n" + "3. To end";
		System.out.println(functions);
	}

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		Scanner scaner = new Scanner(System.in);
		int[] inv = new int[10];

		while (true) {
			// the while loop that allows the user to continuely apply functions, until they
			// choose to end
			// the service

			print();
			System.out.print("\nPlease select the service that you want to conduct:\n");
			int choice = scan.nextInt();

			System.out.println("\n");
			if (choice == 1) {

				VendingMachineInterface machineInterface = new VendingMachineInterface();

				machineInterface.displayProducts();

				int i = 0;
				for (Product product : Product.values()) {
					if (!Product.EMPTY.equals(product)) {
						System.out.println("     " + product.getSelectionNumber() + "  " + product.name()
								+ " - Number left: " + inv[i] + "   ");
					}
					i++;
				}

				boolean isallEmpty = true;
				for (int y = 0; y < inv.length; y++) {
					if (inv[y] != 0) {
						isallEmpty = false;
					}
				}

				if (isallEmpty) {
					System.out.println("\nSorry, we are out of stock right now.");
				} else {
					String selectedProduct = scaner.nextLine();
					machineInterface.selectProduct(Integer.parseInt(selectedProduct));

					while (inv[machineInterface.selectedProduct - 1] <= 0) {

						System.out.println("\nSorry, we are out of stock of this product, please select another.");
						selectedProduct = scaner.nextLine();
						machineInterface.selectProduct(Integer.parseInt(selectedProduct));
					}

					machineInterface.displayEnterCoinsMessage();

					String userEnteredCoins = scaner.nextLine();
					int[] enteredCoins = Coin.parseCoins(userEnteredCoins);
					machineInterface.enterCoins(enteredCoins);

					if (machineInterface.change.getTotal() >= 0) {
						machineInterface.displayChangeMessage();
					} else {
						int current = machineInterface.change.getTotal();

						int[] total = Coin.parseCoins(userEnteredCoins);

						while (current < 0) {
							machineInterface.displayChangeMessage1();
							String userEnteredCoins1 = scaner.nextLine();
							int[] enteredCoins1 = Coin.parseCoins(userEnteredCoins1);
							total[0] += enteredCoins1[0];
							total[1] += enteredCoins1[1];
							total[2] += enteredCoins1[2];
							total[3] += enteredCoins1[3];
							total[4] += enteredCoins1[4];
							machineInterface.enterCoins(total);
							current = machineInterface.change.getTotal();

						}
						machineInterface.displayChangeMessage();

					}

					inv[machineInterface.selectedProduct - 1] -= 1;
				}
			}
			if (choice == 2) {
				System.out.println(" *********************************************");
				System.out.println("       VENDING MACHINE RESUPPLY SYSTEM        ");
				System.out.println(" *********************************************");
				System.out.println("                                              ");
				System.out.println("       resupply?\n		1.yes 2.no        ");
				int option = scan.nextInt();
				if (option == 1) {
					for (int y = 0; y < inv.length; y++) {
						inv[y] = 20;
					}
					System.out.println("       Resupply Successful!        ");
				}

			}

			else if (choice == 3) {
				System.out.println("Now the service will shut off");
				break;
			}

			System.out.println("\n\nPlease make another choice\n");
		}
		System.out.println("System stopped.");
		scan.close();
		scaner.close();

	}
}
