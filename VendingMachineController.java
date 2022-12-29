
public class VendingMachineController {

	private Calculator calculator = new Calculator();

	public CoinBundle calculateChange(VendingMachineRequest request) {
		int total = calculator.calculateTotal(request.enteredCoins);
		int totalChange = total - request.product.getPrice();
		return calculator.calculateChange(totalChange);
	}

	public CoinBundle calculateRemainingChange(VendingMachineRequest request, int remaining) {
		int total = calculator.calculateTotal(request.enteredCoins);
		int totalChange = total - remaining;
		return calculator.calculateChange(totalChange);
	}

	public int calculateRemaining(VendingMachineRequest request) {
		int total = calculator.calculateTotal(request.enteredCoins);
		return total;
	}
}
