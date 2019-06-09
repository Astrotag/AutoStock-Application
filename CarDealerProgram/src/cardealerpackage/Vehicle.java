package cardealerpackage;

public class Vehicle {

	private String carMake;
	private String carModel;
	private int carMileage;
	private double engineSize;
	private int previousOwners;
	private String motStatus;
	
	/*
	 * The vehicle object is called and created when a new vehicle is added, and when it is read back to the vehicle array list. It can be removed from the table and CSV file.
	 */
	
	public Vehicle(String carMake, String carModel, int carMileage, double engineSize, int previousOwners, String motStatus) {
		
		this.carMake = carMake;
		this.carModel = carModel;
		this.carMileage = carMileage;
		this.engineSize = engineSize;
		this.previousOwners = previousOwners;
		this.motStatus = motStatus;
	}//end Vehicle constructor
	
	public String getCarMake() {
		return carMake;
	}//end getcarMileageMake

	public void setCarMake(String carMileageMake) {
		this.carMake = carMileageMake;
	}//end setcarMileageMake

	public String getCarModel() {
		return carModel;
	}//end getcarMileageModel

	public void setCarModel(String carMileageModel) {
		this.carModel = carMileageModel;
	}//end setcarMileageModel

	public int getCarMileage() {
		return carMileage;
	}//end getcarMileage

	public void setCarMileage(int carMileage) {
		this.carMileage = carMileage;
	}//end setcarMileageMileage

	public double getEngineSize() {
		return engineSize;
	}//end getEngineSize

	public void setEngineSize(double engineSize) {
		this.engineSize = engineSize;
	}//end setEngineSize

	public int getPreviousOwners() {
		return previousOwners;
	}//end getPreviousOwners

	public void setPreviousOwners(int previousOwners) {
		this.previousOwners = previousOwners;
	}//end setPreviousOwners

	public String getMotStatus() {
		return motStatus;
	}//end getMotStatus

	public void setMotStatus(String motStatus) {
		this.motStatus = motStatus;
	}//end setMotStatus
	
	@Override
	public String toString() {
		return carMake + " " + carModel + " with " + carMileage + " miles -" + engineSize + "l with " + motStatus + " and " + previousOwners + " previous owners";
	}//end toString method
}//end class
