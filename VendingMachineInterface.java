
public class VendingMachineInterface {

	private VendingMachineController controller = new VendingMachineController();
	public int selectedProduct;
	public CoinBundle change;
	public int remaining;

	public void setRemain(int i) {
		this.remaining = i;
	}

	public int getRemain() {
		return remaining;
	}

	public int getChangeValue() {
		return change.getTotal();
	}

	public void displayProducts() {
		System.out.println(" *********************************************");
		System.out.println("     WELCOME TO THE VENDING MACHINE           ");
		System.out.println(" *********************************************");
		System.out.println("            Products available:               ");
		System.out.println("                                              ");
		for (Product product : Product.values()) {
			if (!Product.EMPTY.equals(product)) {
				System.out.println("     " + product.getSelectionNumber() + "  " + product.name() + " - Price: "
						+ product.getPrice() + "   ");
			}
		}
		System.out.println("                                              ");
		System.out.println(" Please select your product: ");

	}

	public void selectProduct(int product) {
		this.selectedProduct = product;
	}

	public void displayEnterCoinsMessage() {
		System.out.println(" Please enter coins as follows: ");
		System.out.println(
				" num of 5 cents coins,num of 10 cents coins,num of 20 cents coins,num of 50 cents coins,num of 100 cents coins  ");
		System.out.println("                                              ");
		System.out.println(" Example: If you would like to enter 2 ten cents coins: 0,2,0,0,0");
		System.out.println("Plese enter coins:");

	}

	public void enterCoins(int... coins) {
		VendingMachineRequest request = new VendingMachineRequest(selectedProduct, coins);
		change = controller.calculateChange(request);

	}

	public void enterCoins1(int remaining, int... coins) {
		VendingMachineRequest request = new VendingMachineRequest(selectedProduct, coins);
		change = controller.calculateRemainingChange(request, remaining);

	}

	public void displayChangeMessage() {

		System.out.println("                                              ");
		System.out.println("Your change is :" + change.getTotal() + " cents split as follows: ");
		System.out.println("    100 cents coins: " + change.number100CentsCoins);
		System.out.println("    50 cents coins: " + change.number50CentsCoins);
		System.out.println("    20 cents coins: " + change.number20CentsCoins);
		System.out.println("    10 cents coins: " + change.number10CentsCoins);
		System.out.println("    5 cents coins: " + change.number5CentsCoins);
		System.out.println(" *********************************************");
		System.out.println("       THANK YOU FOR SHOPPING WITH US!        ");

	}

	public void displayChangeMessage1() {

		System.out.println("                                              ");
		System.out.println("You are missing :" + (-1 * change.getTotal()) + " cents for the selected product.\n");

	}

}
