package com.foodquick;

public class Restaurant {
	
	 // Attributes
	 private String restaurantName;
	 private String phoneNumber;
	 private String location;
	  
	 // Constructor
	 public Restaurant(String restaurantName, String phoneNumber, String location) {
			this.restaurantName = restaurantName;
			this.phoneNumber = phoneNumber;
			this.location = location;			
		}

		//Get and Set methods
		public String getRestaurantName() {
			return restaurantName;
		}

		public void setRestaurantName(String restaurantName) {
			this.restaurantName = restaurantName;
			}

		public String getPhoneNumber() {
			return phoneNumber;
		}

		public void setPhoneNumber(String phoneNumber) {
			this.phoneNumber = phoneNumber;
		}

		public String getLocation() {
			return location;
		}

		public void setLocation(String location) {
			this.location = location;
		}

	    //toString for selecting the restaurant to complete the order from
		@Override
		public String toString(){
	 		return String.format("%s, %s, %s", restaurantName, location, phoneNumber);
		}
}
